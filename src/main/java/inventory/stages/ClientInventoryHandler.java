package inventory.stages;

import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
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
            if (slotIndex >= 0 && slotIndex <= 8) {
                if (InventoryHandler.INVENTORY_STAGES.length > 3 &&
                    InventoryHandler.INVENTORY_STAGES[3] != null && !
                    GameStageHelper.hasStage(player, InventoryHandler.INVENTORY_STAGES[3])) {
                    event.setCanceled(true);
                }
            }
            else if (slotIndex >= 9 && slotIndex <= 35) {
                int row = (slotIndex - 9) / 9;
                if (row < InventoryHandler.INVENTORY_STAGES.length) {
                    if (InventoryHandler.INVENTORY_STAGES[row] == null || !
                    GameStageHelper.hasStage(player, InventoryHandler.INVENTORY_STAGES[row])) {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onGuiRender(ScreenEvent.Render.Post event) {
        if (!(event.getScreen() instanceof InventoryScreen inventoryScreen)) return;
        
        Player player = Minecraft.getInstance().player;
        if (player == null) return;

        GuiGraphics guiGraphics = event.getGuiGraphics();
        int guiLeft = inventoryScreen.getGuiLeft();
        int guiTop = inventoryScreen.getGuiTop();

        if (InventoryHandler.INVENTORY_STAGES.length > 3 &&
            InventoryHandler.INVENTORY_STAGES[3] != null && !
            GameStageHelper.hasStage(player, InventoryHandler.INVENTORY_STAGES[3])) {
            for (int col = 0; col < 9; col++) {
                int x = guiLeft + 8 + (col * 18);
                int y = guiTop + 142;
                renderDisabledSlot(guiGraphics, x, y);
            }
        }
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                int x = guiLeft + 8 + (col * 18);
                int y = guiTop + 84 + (row * 18);

                if (row < InventoryHandler.INVENTORY_STAGES.length) {
                    if (InventoryHandler.INVENTORY_STAGES[row] == null || !
                    GameStageHelper.hasStage(player, InventoryHandler.INVENTORY_STAGES[row])) {
                        renderDisabledSlot(guiGraphics, x, y);
                    }
                }
            }
        }
    }

    private void renderDisabledSlot(GuiGraphics guiGraphics, int x, int y) {
        guiGraphics.fill(x, y, x + 16, y + 16, 0x80000000);
        guiGraphics.fill(x, y, x + 16, y + 1, 0xFF000000);
        guiGraphics.fill(x, y + 15, x + 16, y + 16, 0xFF000000);
        guiGraphics.fill(x, y, x + 1, y + 16, 0xFF000000);
        guiGraphics.fill(x + 15, y, x + 16, y + 16, 0xFF000000);

        for (int i = 0; i < 12; i++) {
            guiGraphics.fill(x + 2 + i, y + 2 + i, x + 3 + i, y + 3 + i, 0xFF000000);
            guiGraphics.fill(x + 13 - i, y + 2 + i, x + 14 - i, y + 3 + i, 0xFF000000);
        }
    }
}