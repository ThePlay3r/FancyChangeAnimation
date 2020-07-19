package me.pljr.fancychangeanimation.listeners;

import me.pljr.fancychangeanimation.FancyChangeAnimation;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerExpChangeListener implements Listener {
    private final FancyChangeAnimation plugin = FancyChangeAnimation.getInstance();

    @EventHandler
    public void onChange(PlayerExpChangeEvent event){
        new BukkitRunnable() {
            final Player player = event.getPlayer();
            int amount = event.getAmount();
            @Override
            public void run() {
                if (amount==0){
                    cancel();
                    return;
                }
                player.giveExp(1);
                amount--;
            }
        }.runTaskTimerAsynchronously(plugin, 0, FancyChangeAnimation.getSpeed());
        event.setAmount(0);
    }
}
