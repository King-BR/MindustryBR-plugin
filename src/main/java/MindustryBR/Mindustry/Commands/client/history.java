package MindustryBR.Mindustry.Commands.client;

import MindustryBR.internal.Classes.Commands.ClientCommand;
import arc.util.Nullable;
import mindustry.gen.Player;

import static MindustryBR.Main.activeHistoryPlayers;

public class history implements ClientCommand {
    @Nullable
    public static final String params = null;
    @Nullable
    public static final String desc = "Ativa o historico do bloco.";

    public static void run(String[] args, Player player) {
        if (activeHistoryPlayers.contains(player)) {
            activeHistoryPlayers.remove(player);
            player.sendMessage("[yellow]Historico [red]desativado[yellow].");
        } else {
            activeHistoryPlayers.add(player);
            player.sendMessage("[yellow]Historico [green]ativado[yellow]. Clique em um bloco para ver o historico");
        }
    }
}
