package msi.schneeeule.windSource;

import com.google.gson.JsonObject;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Tree {

    // Maker

    public static ItemStack createItemStack(Material material, String displayName) {
        return createItemStack(material, displayName, 1);
    }
    public static ItemStack createItemStack(Material material, String displayName, int amount) {
        ItemStack itemStack = new ItemStack(material);
        itemStack.setAmount(amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(Enid.translateStringToTextComponent(displayName, true));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack createItemStack(Material material, String displayName, String lore) {
        return createItemStack(material, displayName, lore, 1);
    }
    public static ItemStack createItemStack(Material material, String displayName, String lore, int amount) {
        ItemStack itemStack = new ItemStack(material);
        itemStack.setAmount(amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(Enid.translateStringToTextComponent(displayName, true));
        if (lore != null) {
            String[] loreParts = lore.split("\\n");
            List list = Arrays.asList(loreParts);
            itemMeta.setLore(list);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack createItemStack(Material material, String displayName, String lore, int amount, Plugin plugin, String key) {
        ItemStack itemStack = new ItemStack(material);
        itemStack.setAmount(amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(Enid.translateStringToTextComponent(displayName, true));

        if (lore != null) {
            String[] loreParts = lore.split("\\n");
            List list = Arrays.asList(loreParts);
            itemMeta.setLore(list);
        }
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, key), PersistentDataType.BOOLEAN, true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static MerchantRecipe newMerchantRecipe(ItemStack demand, ItemStack ingredient, ItemStack ingredient2) {
        MerchantRecipe recipe = new MerchantRecipe(demand, Integer.MAX_VALUE);
        recipe.addIngredient(ingredient);
        if (ingredient2 != null) recipe.addIngredient(ingredient2);
        return recipe;
    }
    public static MerchantRecipe newMerchantRecipe(ItemStack demand, ItemStack ingredient, ItemStack ingredient2, int maxUses, boolean allowDiscounts) {
        MerchantRecipe recipe = new MerchantRecipe(demand, maxUses);
        recipe.addIngredient(ingredient);
        if (ingredient2 != null) recipe.addIngredient(ingredient2);
        recipe.setIgnoreDiscounts(allowDiscounts);
        return recipe;
    }

    public static void sendMessageToDiscordWebhook(String webhookLink, String message)  {
        try {
            URL url = new URL(webhookLink);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("content", message);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("User-Agent", "Java-DiscordWebhook");
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            OutputStream stream = connection.getOutputStream();
            stream.write(jsonObject.toString().getBytes());
            stream.flush();
            stream.close();
            connection.getInputStream().close();
            connection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
