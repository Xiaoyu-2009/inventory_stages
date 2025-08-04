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
import inventory.stages.kubejs.InventoryStagesKubeJSPlugin;
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
    
    private void setup(final FMLCommonSetupEvent event) {}
    
    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            InventoryStagesEvents.LOGGED_IN.post(new InventoryStagesEvents.PlayerLoggedInEventJS(serverPlayer));
        }
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
}