package ThunderCore.Managers.GameManagers.BedWarsManager.SkyWarsShop;

import ThunderCore.Managers.GameManagers.BedWarsManager.BedWarsGameForm;
import ThunderCore.Managers.GameManagers.BedWarsManager.BedWarsManager;
import ThunderCore.Managers.GameManagers.BedWarsManager.BedWarsTeamForm;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShopGUI {

    public static void banGUI(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 54, Component.text(ChatColor.GOLD + "Shop Menu"));
        for (int i = 0; i < 54; i++) {
            ItemStack blackGlass = new ItemStack(Material.BLACK_STAINED_GLASS, 1);
            ItemMeta emptyMeta = blackGlass.getItemMeta();
            emptyMeta.displayName(Component.text("-"));
            blackGlass.setItemMeta(emptyMeta);
            inventory.setItem(i, blackGlass);
        }
        ItemStack wool = null;
        ItemStack glass = null;
        for (BedWarsGameForm gameForm : BedWarsManager.get().getActiveGames()) {
            BedWarsTeamForm game = gameForm.getTeamByPlayer(player);
            if (game != null) {
                switch (game.getTeamColor()) {
                    case "blue" -> {
                        wool = new ItemStack(Material.BLUE_WOOL, 16);
                        glass = new ItemStack(Material.BLUE_STAINED_GLASS, 8);
                    }
                    case "red" -> {
                        wool = new ItemStack(Material.RED_WOOL, 16);
                        glass = new ItemStack(Material.RED_STAINED_GLASS, 8);
                    }
                    case "green" -> {
                        wool = new ItemStack(Material.GREEN_WOOL, 16);
                        glass = new ItemStack(Material.GREEN_STAINED_GLASS, 8);
                    }
                    case "yellow" -> {
                        wool = new ItemStack(Material.YELLOW_WOOL, 16);
                        glass = new ItemStack(Material.YELLOW_STAINED_GLASS, 8);
                    }
                    default -> { return; }
                }
            }
        }
        assert wool != null;
        //Prices will need to be balanced
        ItemMeta woolMeta = wool.getItemMeta();
        woolMeta.displayName(Component.text("Wool (4 Iron)"));
        wool.setItemMeta(woolMeta);
        inventory.setItem(1, wool);

        ItemStack wood = new ItemStack(Material.OAK_WOOD, 8);
        ItemMeta woodMeta = wood.getItemMeta();
        woodMeta.displayName(Component.text("Wood (8 Iron)"));
        wood.setItemMeta(woodMeta);
        inventory.setItem(2, wood);

        ItemStack endStone = new ItemStack(Material.OAK_WOOD, 8);
        ItemMeta endStoneMeta = endStone.getItemMeta();
        endStoneMeta.displayName(Component.text("EndStone (4 Gold)"));
        endStone.setItemMeta(endStoneMeta);
        inventory.setItem(3, endStone);

        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.displayName(Component.text("Glass (6 Iron)"));
        glass.setItemMeta(glassMeta);
        inventory.setItem(4, glass);

        player.openInventory(inventory);
    }

}
