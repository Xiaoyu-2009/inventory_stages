package inventory.stages;

import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;

@OnlyIn(Dist.CLIENT)
public class ClientInventoryHandler {
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onGuiMouseClick(ScreenEvent.MouseButtonPressed.Pre event) {
        if (!(event.getScreen() instanceof InventoryScreen inventoryScreen)) return;
        
        Player player = Minecraft.getInstance().player;
        if (player == null) return;
        
        Slot hoveredSlot = inventoryScreen.getSlotUnderMouse();
        if (hoveredSlot != null && hoveredSlot.container == player.getInventory()) {
            int slotIndex = hoveredSlot.getSlotIndex();
            
            if (slotIndex >= 9 && slotIndex <= 35) {
                int row = (slotIndex - 9) / 9;
                if (row < InventoryHandler.INVENTORY_STAGES.length) {
                    if (!GameStageHelper.hasStage(player, InventoryHandler.INVENTORY_STAGES[row])) {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
}