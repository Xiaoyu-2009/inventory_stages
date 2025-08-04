package inventory.stages.kubejs;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import dev.latvian.mods.kubejs.player.PlayerEventJS;
import net.minecraft.server.level.ServerPlayer;
import inventory.stages.InventoryStages;

public interface InventoryStagesEvents {
    EventGroup GROUP = EventGroup.of("PlayerEvents");
    EventHandler LOGGED_IN = GROUP.server("loggedIn", () -> PlayerLoggedInEventJS.class);

    class PlayerLoggedInEventJS extends PlayerEventJS {
        private final ServerPlayer serverPlayer;

        public PlayerLoggedInEventJS(ServerPlayer player) {
            this.serverPlayer = player;
        }

        @Override
        public ServerPlayer getEntity() {
            return serverPlayer;
        }

        public void setRow1Stage(String stageName) {
            InventoryStages.setRow1Stage(stageName);
        }

        public void setRow2Stage(String stageName) {
            InventoryStages.setRow2Stage(stageName);
        }

        public void setRow3Stage(String stageName) {
            InventoryStages.setRow3Stage(stageName);
        }
    }
}