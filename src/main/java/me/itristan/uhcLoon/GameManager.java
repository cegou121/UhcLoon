package me.itristan.uhcLoon;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GameManager implements CommandExecutor {

    private final UhcLoon plugin;
    private final WorldManager worldManager;

    public GameManager(UhcLoon plugin) {
        this.plugin = plugin;
        this.worldManager = new WorldManager();
    }

    public static WorldManager getWorldManager() {
        return null;
    }

    public void setupWorld() {
        worldManager.setupWorld();
    }

    // Запуск UHC

    public void startGame() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            // убираем у всех вещы
            player.getInventory().clear();

            // даем киты
            player.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
            player.getInventory().addItem(new ItemStack(Material.APPLE, 5));
            player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 10));



            double x = (Math.random() * 1000) - 500;
            double z = (Math.random() * 1000) - 500;
            double y = worldManager.getWorld().getHighestBlockYAt((int) x, (int) z);
            player.teleport(new org.bukkit.Location(worldManager.getWorld(), x, y, z));

            player.sendMessage("Игра UHC началась! Удачи!");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("startuhc") && sender.isOp()) {
            startGame();
            return true;
        }
        return false;
    }
}
