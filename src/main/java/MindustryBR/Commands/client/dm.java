package MindustryBR.Commands.client;

import mindustry.gen.Groups;
import mindustry.gen.Player;

public class dm {
    public dm(String[] args, Player player) {
        // Find player by name
        Player other = Groups.player.find(p -> p.name.toLowerCase().contains(args[0].toLowerCase()));

        // Give error message if player isn't found
        if(other == null){
            player.sendMessage("[scarlet]Nenhum jogador encontrado com esse nome!");
            return;
        }

        // Send the other player a private message
        other.sendMessage("[lightgray](DM)[] " + player.name + "[white]:[] " + args[1]);
        // Send a message to the player that used the command
        player.sendMessage("[lightgray](DM)[] " + player.name + "[white]:[] " + args[1]);
    }
}
