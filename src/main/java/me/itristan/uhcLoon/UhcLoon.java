package me.itristan.uhcLoon;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class UhcLoon extends JavaPlugin {

    private GameManager gameManager;

    @Override
    public void onEnable() {
        // Инициализация GameManager
        gameManager = new GameManager(this);
        gameManager.setupWorld();

        // Регистрация событий
        World world = GameManager.getWorldManager().getWorld();

        Bukkit.getPluginManager().registerEvents(new PlayerListener(this), this);

        // Регистрация команды
        getCommand("startuhc").setExecutor(gameManager);

        getLogger().info("UHC плагин успешно активирован!");
    }

    @Override
    public void onDisable() {
        getLogger().info("UHC плагин отключён!");
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
