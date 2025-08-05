package inventory.stages;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.server.level.ServerPlayer;
import net.darkhax.gamestages.GameStageHelper;
import inventory.stages.kubejs.InventoryStagesEvents;

@Mod("inventory_stages")
public class InventoryStages {
    public InventoryStages() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(new InventoryHandler());
        MinecraftForge.EVENT_BUS.register(this);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            MinecraftForge.EVENT_BUS.register(new ClientInventoryHandler());
        }
    }

    private void setup(final FMLCommonSetupEvent event) {
        resetToDefaults();
    }

    public static void resetToDefaults() {
        InventoryHandler.INVENTORY_STAGES[0] = "inventory_row_1";
        InventoryHandler.INVENTORY_STAGES[1] = "inventory_row_2";
        InventoryHandler.INVENTORY_STAGES[2] = "inventory_row_3";
        InventoryHandler.INVENTORY_STAGES[3] = "inventory_hotbar";
    }

    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            applyDefaultConfiguration(serverPlayer);
        }
    }
    
    private static void applyDefaultConfiguration(ServerPlayer serverPlayer) {
        resetToDefaults();
        if (InventoryHandler.INVENTORY_STAGES[3] != null) {
            GameStageHelper.addStage(serverPlayer, InventoryHandler.INVENTORY_STAGES[3]);
        }
        
        InventoryStagesEvents.LOGGED_IN.post(new InventoryStagesEvents.PlayerLoggedInEventJS(serverPlayer));
    }
    
    public static void reloadConfiguration(ServerPlayer player) {
        applyDefaultConfiguration(player);
    }

    public static void setRow1Stage(String stageName) {
        InventoryHandler.INVENTORY_STAGES[0] = stageName;
    }

    public static void setRow2Stage(String stageName) {
        InventoryHandler.INVENTORY_STAGES[1] = stageName;
    }

    public static void setRow3Stage(String stageName) {
        InventoryHandler.INVENTORY_STAGES[2] = stageName;
    }

    public static void setRow4Stage(String stageName) {
        InventoryHandler.INVENTORY_STAGES[3] = stageName;
    }

    public static class InventoryStagesEventHandler {
        private final ServerPlayer serverPlayer;

        public InventoryStagesEventHandler(ServerPlayer player) {
            this.serverPlayer = player;
        }

        public void addSetRow1Stage(String stageName) {
            setRow1Stage(stageName);
            GameStageHelper.addStage(serverPlayer, stageName);
        }

        public void addSetRow2Stage(String stageName) {
            setRow2Stage(stageName);
            GameStageHelper.addStage(serverPlayer, stageName);
        }

        public void addSetRow3Stage(String stageName) {
            setRow3Stage(stageName);
            GameStageHelper.addStage(serverPlayer, stageName);
        }

        public void removeSetRow1Stage(String stageName) {
            GameStageHelper.removeStage(serverPlayer, stageName);
        }

        public void removeSetRow2Stage(String stageName) {
            GameStageHelper.removeStage(serverPlayer, stageName);
        }

        public void removeSetRow3Stage(String stageName) {
            GameStageHelper.removeStage(serverPlayer, stageName);
        }
    }
}