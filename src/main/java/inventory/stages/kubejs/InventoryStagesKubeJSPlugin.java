package inventory.stages.kubejs;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import inventory.stages.InventoryStages;

public class InventoryStagesKubeJSPlugin extends KubeJSPlugin {
    
    @Override
    public void registerEvents() {
        InventoryStagesEvents.GROUP.register();
    }
    
    @Override
    public void registerBindings(BindingsEvent event) {
        event.add("InventoryStages", InventoryStages.class);
    }
}