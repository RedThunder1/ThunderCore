package ThunderCore.Commands.ReportCommand.ViewReports;

import ThunderCore.Managers.ReportManager.ReportManager;
import ThunderCore.Managers.ReportManager.ReportRecord;
import ThunderCore.ThunderCore;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ViewReportsGUI {

    public static void reportsGui(Player player) {
        Inventory inventory = ThunderCore.get().getServer().createInventory(null, 54, Component.text(ChatColor.RED + "" + ChatColor.BOLD + "Reports Menu"));

        for(ReportRecord report : ReportManager.get().getReports()) {
            ItemStack itemStack = new ItemStack(Material.REDSTONE_BLOCK);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.displayName(Component.text(ChatColor.RED + "" + ChatColor.BOLD + "Player Report!"));
            List<Component> lore = new ArrayList<>();
            lore.add(Component.text("Reporter: " + report.reporter().getName()));
            lore.add(Component.text("Reported: " + report.reported().getName()));
            lore.add(Component.text("Reason: " + report.reason()));
            lore.add(Component.text("ID: " + report.id()));
            itemMeta.lore(lore);
            itemStack.setItemMeta(itemMeta);
            inventory.addItem(itemStack);
        }

        player.openInventory(inventory);
    }

}
