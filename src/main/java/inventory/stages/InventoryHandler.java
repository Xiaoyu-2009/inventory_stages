package inventory.stages;

import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;

public class InventoryHandler {

    public static String[] INVENTORY_STAGES = {
        "inventory_row_1",
        "inventory_row_2", 
        "inventory_row_3"
    };

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onItemPickup(EntityItemPickupEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        
        ItemStack itemStack = event.getItem().getItem();
        
        if (!canPickupItem(player, itemStack)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (!(event.player instanceof ServerPlayer)) return;
        ServerPlayer player = (ServerPlayer) event.player;
        
        for (int row = 0; row < 3; row++) {
            if (!GameStageHelper.hasStage(player, INVENTORY_STAGES[row])) {
                for (int col = 0; col < 9; col++) {
                    int slotIndex = 9 + (row * 9) + col;
                    ItemStack itemInSlot = player.getInventory().getItem(slotIndex);
                    
                    if (!itemInSlot.isEmpty()) {
                        if (!moveItemToHotbar(player, itemInSlot)) {
                            player.drop(itemInSlot, false);
                        }
                        player.getInventory().setItem(slotIndex, ItemStack.EMPTY);
                    }
                }
            }
        }
    }

    private boolean canPickupItem(Player player, ItemStack itemStack) {
        for (int i = 0; i < 9; i++) {
            ItemStack slotItem = player.getInventory().getItem(i);
            if (slotItem.isEmpty() || (ItemStack.isSameItemSameTags(slotItem, itemStack) && 
                slotItem.getCount() + itemStack.getCount() <= slotItem.getMaxStackSize())) {
                return true;
            }
        }
        
        for (int row = 0; row < 3; row++) {
            if (GameStageHelper.hasStage(player, INVENTORY_STAGES[row])) {
                for (int col = 0; col < 9; col++) {
                    int slotIndex = 9 + (row * 9) + col;
                    ItemStack slotItem = player.getInventory().getItem(slotIndex);
                    if (slotItem.isEmpty() || (ItemStack.isSameItemSameTags(slotItem, itemStack) && 
                        slotItem.getCount() + itemStack.getCount() <= slotItem.getMaxStackSize())) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }

    private boolean moveItemToHotbar(Player player, ItemStack itemStack) {
        for (int i = 0; i < 9; i++) {
            ItemStack slotItem = player.getInventory().getItem(i);
            if (slotItem.isEmpty()) {
                player.getInventory().setItem(i, itemStack.copy());
                return true;
            } else if (ItemStack.isSameItemSameTags(slotItem, itemStack) && 
                      slotItem.getCount() + itemStack.getCount() <= slotItem.getMaxStackSize()) {
                slotItem.grow(itemStack.getCount());
                return true;
            }
        }
        return false;
    }
}