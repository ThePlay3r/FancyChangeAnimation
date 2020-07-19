package me.pljr.fancychangeanimation.listeners;

import me.pljr.fancychangeanimation.FancyChangeAnimation;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class FoodLevelChangeListener implements Listener {
    private final FancyChangeAnimation plugin = FancyChangeAnimation.getInstance();

    @EventHandler
    public void onChange(FoodLevelChangeEvent event){
        if (!(event.getEntity() instanceof Player)) return;
        final Player player = (Player) event.getEntity();
        final int setFoodLevel = event.getFoodLevel();
        final int currentFoodLevel = player.getFoodLevel();
        if (setFoodLevel < currentFoodLevel) return;
        event.setCancelled(true);
        new BukkitRunnable() {
            int addedFoodLevel = setFoodLevel-currentFoodLevel;
            @Override
            public void run() {
                if (addedFoodLevel==0 || player.getFoodLevel()+1 > 20){
                    cancel();
                    return;
                }
                player.setFoodLevel(player.getFoodLevel()+1);
                addedFoodLevel--;
            }
        }.runTaskTimerAsynchronously(plugin, 0, FancyChangeAnimation.getSpeed());
    }
}
