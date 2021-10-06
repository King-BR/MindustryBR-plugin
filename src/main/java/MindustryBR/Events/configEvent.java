package MindustryBR.Events;

import MindustryBR.internal.classes.history.LimitedQueue;
import MindustryBR.internal.classes.history.entry.BaseEntry;
import MindustryBR.internal.classes.history.entry.ConfigEntry;
import arc.struct.Seq;
import mindustry.Vars;
import mindustry.game.EventType;
import mindustry.gen.Building;
import mindustry.type.Category;
import mindustry.world.Tile;
import org.javacord.api.DiscordApi;
import org.json.JSONObject;

import static MindustryBR.Main.worldHistory;

public class configEvent {
    public static void run (DiscordApi bot, JSONObject config, EventType.ConfigEvent e) {
        if (e.player == null) return;
        if (e.tile.tile.x > worldHistory.length || e.tile.tile.y > worldHistory[0].length) return;
        System.out.println(e.tile.tile.x + " " + e.tile.tile.y + " " + e.value + " " + (e.value != null ? e.value.getClass().getSimpleName(): null));

        LimitedQueue<BaseEntry> tileHistory = worldHistory[e.tile.tile.x][e.tile.tile.y];
        boolean connect = true;

        if (!tileHistory.isEmpty() && tileHistory.getLast() instanceof ConfigEntry) {
            ConfigEntry lastConfigEntry = ((ConfigEntry) tileHistory.getLast());

            connect = !(lastConfigEntry.value == e.value && lastConfigEntry.connect);
        }

        Tile target = null;
        if (e.value != null && e.value.getClass().getSimpleName().equals("Integer")) target = Vars.world.tile((int) e.value);

        BaseEntry historyEntry = new ConfigEntry(e.player, e.tile, e.value, connect, target);
        Seq<Building> linkedTile = e.tile.getPowerConnections(new Seq<>());

        worldHistory[e.tile.tile.x][e.tile.tile.y].add(historyEntry);

        if (e.tile.block().category.equals(Category.units) || e.tile.block().category.equals(Category.distribution)) return;

        for (Building tile : linkedTile) {
            BaseEntry historyEntry2 = new ConfigEntry(e.player, target != null ? target.build : e.tile, e.value, connect, e.tile.tile());
            worldHistory[tile.tile.x][tile.tile.y].add(historyEntry2);
        }
    }
}
