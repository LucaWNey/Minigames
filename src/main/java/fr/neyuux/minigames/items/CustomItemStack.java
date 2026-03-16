package fr.neyuux.minigames.items;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import fr.neyuux.minigames.GameListener;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.*;

public class CustomItemStack extends GameListener {

    private static final List<CustomItemStack> USE_LIST = new ArrayList<>();


    private final ItemStack item;
    private boolean usable;

    public CustomItemStack(Material m) {
        item = new ItemStack(m);
    }

    public CustomItemStack(Material m, int amount) {
        item = new ItemStack(m, amount);
    }

    public CustomItemStack(Material m, int amount, byte data) {
        item = new ItemStack(m, amount, data);
    }

    public CustomItemStack(Material m, int amount, String string) {
        item = new ItemStack(m, amount);
        this.setDisplayName(string);
    }

    public CustomItemStack(ItemStack i) {
        item = new ItemStack(i);
    }

    public CustomItemStack putAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public CustomItemStack setDamage(int damage) {
        item.setDurability((short) damage);
        return this;
    }

    public CustomItemStack setDisplayName(String displayname) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        item.setItemMeta(meta);
        return this;
    }

    public CustomItemStack setSkullOwner(String owner) {
        String display = getDisplayName();
        SkullMeta im = (SkullMeta) item.getItemMeta();
        im.setDisplayName(display);
        im.setOwner(owner);
        item.setItemMeta(im);
        return this;
    }

    public CustomItemStack setLore(List<String> lore) {
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);
        return this;
    }

    public CustomItemStack setLoreLine(int line, String text) {
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        lore.set(line, text);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return this;
    }

    public CustomItemStack addLore(String text) {
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        if (lore == null) lore = new ArrayList<>();
        lore.add(text);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return this;
    }

    public CustomItemStack setType(Material type) {
        item.setType(type);
        return this;
    }

    public CustomItemStack addItemFlags(ItemFlag... flags) {
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(flags);
        item.setItemMeta(meta);
        return this;
    }

    public CustomItemStack removeItemFlags(ItemFlag... flags) {
        ItemMeta meta = item.getItemMeta();
        meta.removeItemFlags(flags);
        item.setItemMeta(meta);
        return this;
    }

    public CustomItemStack setEnchantments(Map<Enchantment, Integer> enchantments) {
        item.addUnsafeEnchantments(enchantments);
        return this;
    }

    public CustomItemStack addEnchantmentV(Enchantment ench, int lvl) {
        item.addUnsafeEnchantment(ench, lvl);
        return this;
    }

    public CustomItemStack addGlowEffect() {
        return this.addEnchantmentV(Enchantment.DURABILITY, 1).addItemFlags(ItemFlag.HIDE_ENCHANTS);
    }

    public CustomItemStack setUnbreakable(boolean unbreakable) {
        ItemMeta meta = item.getItemMeta();
        meta.spigot().setUnbreakable(unbreakable);
        item.setItemMeta(meta);
        return this;
    }

    public CustomItemStack setUsable() {
        this.usable = true;
        return this;
    }

    /**
     * Set the head texture. Works on skulls only.
     *
     * @param texture The hash of the texture.
     * @return Le {@link CustomItemStack} actuel
     * @author MaygoDev
     */
    public CustomItemStack setTexture(String texture) {
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        PropertyMap propertyMap = profile.getProperties();
        propertyMap.put("textures", new Property("textures", texture));
        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        Class<?> c_skullMeta = skullMeta.getClass();
        try {
            Field f_profile = c_skullMeta.getDeclaredField("profile");
            f_profile.setAccessible(true);
            f_profile.set(skullMeta, profile);
            f_profile.setAccessible(false);
            item.setItemMeta(skullMeta);
            return this;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public boolean isUnbreakable() {
        return item.getItemMeta().spigot().isUnbreakable();
    }

    public String getDisplayName() {
        return item.getItemMeta().getDisplayName();
    }

    public List<String> getLore() {
        return item.getItemMeta().getLore();
    }

    public CustomItemStack setLore(String... lore) {
        return this.setLore(Arrays.asList(lore));
    }

    public CustomItemStack clone() {
        return new CustomItemStack(item);
    }

    public void use(HumanEntity player, Event event) {
        if (event instanceof InventoryClickEvent)
            ((InventoryClickEvent) event).setCancelled(true);
    }


    @SuppressWarnings("deprecation")
    public boolean isCustomSimilar(ItemStack stack) {
        if (stack == null) return false;
        else if (stack == item) return true;
        else {
            return item.getTypeId() == stack.getTypeId() && item.getDurability() == stack.getDurability() && item.hasItemMeta() == stack.hasItemMeta() && (!item.hasItemMeta() || !item.getItemMeta().hasLore() || !item.getItemMeta().hasDisplayName() || (item.getItemMeta().getDisplayName().equals(stack.getItemMeta().getDisplayName()) && item.getItemMeta().getLore().equals(stack.getItemMeta().getLore())));
        }
    }


    public ItemStack build() {
        if (usable && USE_LIST.stream().noneMatch(customItemStack -> customItemStack.isCustomSimilar(item)))
            USE_LIST.add(this);

        return item;
    }


    public static int getSlot(Inventory inv, CustomItemStack customItemStack) {
        int slot = -1;
        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack item = inv.getItem(i);
            if (item == null) continue;
            if (customItemStack.isCustomSimilar(item))
                slot = i;
        }
        return slot;
    }


    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item == null) return;

        try {
            for (CustomItemStack customitem : CustomItemStack.getItemList())
                if (customitem.isCustomSimilar(item)) {
                    event.setCancelled(true);
                    customitem.use(player, event);
                    return;
                }
        } catch (ConcurrentModificationException ignored) {

        }
    }

    @EventHandler
    public void onClickInv(InventoryClickEvent event) {
        HumanEntity human = event.getWhoClicked();
        ItemStack item = event.getCurrentItem();

        if (item == null) return;

        try {
            for (CustomItemStack customitem : CustomItemStack.getItemList())
                if (customitem.isCustomSimilar(item)) {
                    event.setCancelled(true);
                    customitem.use(human, event);
                }
        } catch (ConcurrentModificationException ignored) {}
    }


    public static List<CustomItemStack> getItemList() {
        return USE_LIST;
    }
}