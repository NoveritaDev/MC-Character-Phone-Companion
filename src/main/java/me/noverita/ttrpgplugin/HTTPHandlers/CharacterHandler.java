package me.noverita.ttrpgplugin.HTTPHandlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import me.noverita.ttrpgplugin.DataClasses.CharacterDataHandler;
import me.noverita.ttrpgplugin.DataClasses.SessionStorage;
import me.noverita.ttrpgplugin.DataClasses.SkillData;
import me.noverita.ttrpgplugin.DataClasses.TTRPGCharacter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class CharacterHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String character = SessionStorage.sessions.get(UUID.fromString(exchange.getRequestHeaders().get("Cookie").get(0).substring(5)));

        OutputStream outputStream = exchange.getResponseBody();
        StringBuilder htmlBuilder = new StringBuilder();

        TTRPGCharacter characterData = CharacterDataHandler.getInstance().getCharacter(character);

        htmlBuilder.append("<html>")
                .append("<body>")
                .append("<h1>")
                .append(characterData.getName())
                .append("</h1>")
                .append("<p>")
                .append(characterData.getDescription())
                .append("</p>")
                .append("<ul>");

        //htmlBuilder.append("<form action=\"/roll\" method=\"post\">");
        SkillData skills = characterData.getSkills();
        for (String key: skills.skillRanks.keySet()) {
            htmlBuilder.append("<li><button onclick=\"sendRoll('")
                    .append(key)
                    .append("')\">")
                    .append(key)
                    .append(" - ")
                    .append(skills.skillRanks.get(key))
                    .append(" ranks")
                    .append("</button></li>");
        }
       // htmlBuilder.append("</form>");

        htmlBuilder.append("</ul>")
                .append("</body>")
                .append("<script>")
                .append("""
                        function sendRoll(key) {
                            var xhr = new XMLHttpRequest();
                            xhr.open("POST", "/roll", true);
                            xhr.setRequestHeader('Content-Type', 'application/json');
                            xhr.send(key));
                        }
                        """)
                .append("</script>")
                .append("</html>");

        // encode HTML content
        String htmlResponse = htmlBuilder.toString();

        // this line is a must
        exchange.sendResponseHeaders(200, htmlResponse.length());
        outputStream.write(htmlResponse.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
