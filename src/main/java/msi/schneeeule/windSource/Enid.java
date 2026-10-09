package msi.schneeeule.windSource;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Enid {

    // Colours

    public static String translateColour(String message) {
        return translateColour('&', message);
    }
    public static String translateColour(String message, boolean ampersand) {
        return translateColour(ampersand ? '&' : '§', message);
    }
    public static String translateColour(String message, Character character) {
        return translateColour(character, message);
    }
    public static String translateColour(Character character, String message) {
        Pattern pattern = Pattern.compile(character + "#[a-fA-F0-9]{6}");
        Matcher matcher = pattern.matcher(message);

        while (matcher.find()) {
            String colour = message.substring(matcher.start(), matcher.end());
            message = message.replace(colour, net.md_5.bungee.api.ChatColor.of(colour.substring(1)).toString());
            matcher = pattern.matcher(message);
        }

        return ChatColor.translateAlternateColorCodes(character, message);
    }

    // Wind Components

    public static TextComponent translateStringToTextComponent(String string) {
        return (TextComponent) MiniMessage.miniMessage().deserialize(prepareStringForMiniMessage(string, '&'));
    }
    public static TextComponent translateStringToTextComponent(String string, boolean ampersand) {
        return (TextComponent) MiniMessage.miniMessage().deserialize(prepareStringForMiniMessage(string, ampersand ? '&' : '§'));
    }

    public static String prepareStringForMiniMessage(String string) {
        return prepareStringForMiniMessage(string, '§');
    }
    public static String prepareStringForMiniMessage(String string, boolean ampersand) {
        return prepareStringForMiniMessage(string, ampersand ? '&' : '§');
    }
    public static String prepareStringForMiniMessage(String string, Character character) {
        string = translateColour(character, string);

        string = string
                .replace("§0", "<reset><black>")        .replace(character + "0", "<reset><black>")
                .replace("§1", "<reset><dark_blue>")    .replace(character + "1", "<reset><dark_blue>")
                .replace("§2", "<reset><dark_green>")   .replace(character + "2", "<reset><dark_green>")
                .replace("§3", "<reset><dark_aqua>")    .replace(character + "3", "<reset><dark_aqua>")
                .replace("§4", "<reset><dark_red>")     .replace(character + "4", "<reset><dark_red>")
                .replace("§5", "<reset><dark_purple>")  .replace(character + "5", "<reset><dark_purple>")
                .replace("§6", "<reset><gold>")         .replace(character + "6", "<reset><gold>")
                .replace("§7", "<reset><grey>")         .replace(character + "7", "<reset><grey>")
                .replace("§8", "<reset><dark_grey>")    .replace(character + "8", "<reset><dark_grey>")
                .replace("§9", "<reset><blue>")         .replace(character + "9", "<reset><blue>")
                .replace("§a", "<reset><green>")        .replace(character + "a", "<reset><green>")
                .replace("§b", "<reset><aqua>")         .replace(character + "b", "<reset><aqua>")
                .replace("§c", "<reset><red>")          .replace(character + "c", "<reset><red>")
                .replace("§d", "<reset><light_purple>") .replace(character + "d", "<reset><light_purple>")
                .replace("§e", "<reset><yellow>")       .replace(character + "e", "<reset><yellow>")
                .replace("§f", "<reset><white>")        .replace(character + "f", "<reset><white>")
                .replace("§k", "<obfuscated>")          .replace(character + "k", "<obfuscated>")
                .replace("§l", "<bold>")                .replace(character + "l", "<bold>")
                .replace("§m", "<strikethrough>")       .replace(character + "m", "<strikethrough>")
                .replace("§n", "<underlined>")          .replace(character + "n", "<underlined>")
                .replace("§o", "<italic>")              .replace(character + "o", "<italic>")
                .replace("§r", "<reset>")               .replace(character + "r", "<reset>");

        return string;
    }

    public static TextComponent replaceContentInTextComponent(TextComponent component, String target, String replacement) {
        return (TextComponent) component.replaceText(
                TextReplacementConfig.builder()
                        .matchLiteral(target)
                        .replacement(Component.text(replacement))
                        .build()
        );
    }


    public static TextComponent replaceContentInTextComponent(TextComponent component, String target, Component replacement) {
        return (TextComponent) component.replaceText(
                TextReplacementConfig.builder()
                        .matchLiteral(target)
                        .replacement(replacement)
                        .build()
        );
    }

    public static void whistle(Player player, String message) {
        whistle(player, message, true);
    }
    public static void whistle(Player player, String message, boolean ampersand) {
        player.sendMessage(translateStringToTextComponent(message, true));
    }


}
