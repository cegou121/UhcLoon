package me.itristan.uhcLoon;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    private final UhcLoon plugin;

    public PlayerListener(UhcLoon plugin) {
        this.plugin = plugin;
    }

    // Обработка смерти игрока
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        var player = event.getEntity();

        // Сообщение игроку
        player.sendMessage("§6§lUHC §8§l× {player} §fумер не известной смертью");
        player.knockback(0,17,230);

        // Удаляем игрока через 1 секунду
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            player.sendMessage("ты проебал катку часувую");
            player.kickPlayer("Ты выбыл из UHC режима. Удачи в следующей игре!");
        }, 20L); // 1 секунда
    }

    // Приветствие нового игрока
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        var player = event.getPlayer();
        player.sendMessage("Добро пожаловать на сервер UHC! Ожидайте начала игры.");
        player.sendMessage("§6§lUHC §8§l× §fИгра начнется через §e{time} сек.");
        player.sendMessage("До начала игры: §a❺");
        player.sendMessage("До начала игры: §e❹");
        player.sendMessage("До начала игры: §6❸");
        player.sendMessage("До начала игры: §c❷");
        player.sendMessage("До начала игры: §4❶");

    }
}

