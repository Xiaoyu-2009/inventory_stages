package inventory.stages.kubejs;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import dev.latvian.mods.kubejs.player.PlayerEventJS;
import net.minecraft.server.level.ServerPlayer;
import inventory.stages.InventoryStages;

public interface InventoryStagesEvents {
    EventGroup GROUP = EventGroup.of("InventoryStagesEvents");
    EventHandler LOGGED_IN = GROUP.server("playerLoggedIn", () -> PlayerLoggedInEventJS.class);

    class PlayerLoggedInEventJS extends PlayerEventJS {
        private final ServerPlayer serverPlayer;

        public PlayerLoggedInEventJS(ServerPlayer player) {
            super();
            this.serverPlayer = player;
        }

        @Override
        public ServerPlayer getEntity() {
            return serverPlayer;
        }

        public void addSetRow1Stage(String stageName) {
            InventoryStages.setRow1Stage(stageName);
        }

        public void addSetRow2Stage(String stageName) {
            InventoryStages.setRow2Stage(stageName);
        }

        public void addSetRow3Stage(String stageName) {
            InventoryStages.setRow3Stage(stageName);
        }

        public void addSetRow4Stage(String stageName) {
            InventoryStages.setRow4Stage(stageName);
        }

        public void removeSetRow1Stage(String stageName) {
            InventoryStages.setRow1Stage(null);
        }

        public void removeSetRow2Stage(String stageName) {
            InventoryStages.setRow2Stage(null);
        }

        public void removeSetRow3Stage(String stageName) {
            InventoryStages.setRow3Stage(null);
        }

        public void removeSetRow4Stage(String stageName) {
            InventoryStages.setRow4Stage(null);
        }
        
        public void reloadForCurrentPlayer() {
            InventoryStages.reloadConfiguration(serverPlayer);
        }
    }
}