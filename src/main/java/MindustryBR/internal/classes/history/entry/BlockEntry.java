package MindustryBR.internal.classes.history.entry;

import mindustry.game.EventType;
import mindustry.gen.Player;
import mindustry.world.Tile;

import static MindustryBR.internal.util.Util.getLocalized;

public class BlockEntry implements BaseEntry {
    public Player player;
    public Tile tile;
    public boolean breaking;

    public BlockEntry(EventType.BlockBuildEndEvent e) {
        this.player = e.unit.getPlayer();
        this.tile = e.tile;
        this.breaking = e.breaking;
    }

    @Override
    public String getMessage() {
        return this.getMessage(true);
    }

    @Override
    public String getMessage(boolean withName) {
        if (breaking) return "[red]- [white]" + (withName ? player.name + " " : "(" + tile.x + "," + tile.y + ") ") + "quebrou esse bloco";
        else return "[green]+ [white]" + (withName ? player.name + " " : "(" + tile.x + "," + tile.y + ") ") + "construiu " + (tile.block() != null ? "[purple]" + getLocalized(tile.block().name) + "[white]": "esse bloco");
    }
}
