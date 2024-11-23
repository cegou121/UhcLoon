package me.itristan.uhcLoon;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldManager {

    private JavaPlugin plugin = null;
    private final World world;
    private final WorldBorder border;

    public WorldManager() {
        this.plugin = plugin;
        this.world = Bukkit.getWorlds().get(0); // Первый мир
        this.border = world.getWorldBorder();
    }

    public void setupWorld() {
        if (world == null) {
            Bukkit.getLogger().warning("Мир не найден! Проверьте настройки сервера.");
            return;
        }


        world.setGameRule(GameRule.NATURAL_REGENERATION, false);

        border.setCenter(0, 0);
        border.setSize(1000); // Начальный размер 1000x1000

        // Установить день
        world.setTime(1000);

        // Отключить дожд
        world.setStorm(false);

        startBorderShrinkTimer();
    }

    private void startBorderShrinkTimer() {
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            // Включить PvP после 10 секнуд
            enablePvP();
            Bukkit.broadcastMessage("&l !PvP включено! Граница начинает двигаться.");

            startBorderMovement();
        }, 20L * 60 * 10);
    }

    private void enablePvP() {
        world.setPVP(true); // Включить PvP
    }

    private void startBorderMovement() {
        int totalShrinks = 10; // Сколько раз граница сожмётся (1000 -> 50)
        double shrinkAmount = 100; // Сколько блоков сужается каждый раз
        int interval = 20 * 60 * 10; // Интервал в 10 минут

        Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
            private int shrinkCount = 0;

            @Override
            public void run() {
                if (shrinkCount >= totalShrinks) {
                    Bukkit.broadcastMessage("Граница достигла минимального размера! Финальная схватка начинается.");
                    Bukkit.getScheduler().cancelTasks(plugin);
                    return;
                }

                double newSize = border.getSize() - shrinkAmount;
                border.setSize(newSize, 60);
                Bukkit.broadcastMessage("Граница уменьшилась до " + (int) newSize + "x" + (int) newSize + "!");
                shrinkCount++;
            }
        }, 0L, interval);
    }

    public World getWorld() {
        return null;
    }
}
