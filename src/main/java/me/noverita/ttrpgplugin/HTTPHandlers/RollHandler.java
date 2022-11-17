package me.noverita.ttrpgplugin.HTTPHandlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import me.noverita.ttrpgplugin.DataClasses.CharacterDataHandler;
import me.noverita.ttrpgplugin.DataClasses.SessionStorage;
import me.noverita.ttrpgplugin.DataClasses.TTRPGCharacter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.UUID;

public class RollHandler implements HttpHandler {
    private final Random rng = new Random();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        InputStreamReader isr =  new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        String value = br.readLine();
        System.out.println(value);

        String characterName = SessionStorage.sessions.get(UUID.fromString(exchange.getRequestHeaders().get("Cookie").get(0).substring(5)));
        TTRPGCharacter characterData = CharacterDataHandler.getInstance().getCharacter(characterName);
        UUID mcUUID = characterData.getMCUUID();

        int modifier = characterData.getSkills().skillRanks.get(value);
        int roll = rng.nextInt(20) + 1;
        String message = Bukkit.getPlayer(mcUUID).getDisplayName() + " rolled a " + (roll + modifier) + " (" + roll + " + " + modifier + ")";

        for (Player p: Bukkit.getOnlinePlayers()) {
            p.sendMessage(message);
        }

        exchange.sendResponseHeaders(200, 0);
        OutputStream os = exchange.getResponseBody();
        os.write(new byte[0]);
        os.close();
    }
}
