package MindustryBR.Events;

import mindustry.game.EventType;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.json.JSONObject;

import java.awt.*;
import java.util.Optional;

public class playerBan {
    public static void run (DiscordApi bot, JSONObject config, EventType.PlayerBanEvent e) {
        Optional<ServerTextChannel> c1 = bot.getServerTextChannelById(config.getJSONObject("discord").getString("mod_channel_id"));
        if (c1.isEmpty()) return;
        ServerTextChannel c2 = c1.get();

        EmbedBuilder embed = new EmbedBuilder()
                .setTitle(e.player.name() + " foi banido do servidor " + config.getString("name"))
                .setDescription("UUID: `" + e.player.getInfo().id + "`\n" +
                        "Nomes usados: `" + e.player.getInfo().names.toString(", ") + "`\n" +
                        "Entrou " + e.player.getInfo().timesJoined + " vez(es)\n" +
                        "Kickado " + e.player.getInfo().timesKicked + " vez(es)\n")
                .setFooter(config.getString("ip"))
                .setColor(Color.red)
                .setTimestampToNow();

        new MessageBuilder()
                .setEmbed(embed)
                .send(c2)
                .join();
    }
}