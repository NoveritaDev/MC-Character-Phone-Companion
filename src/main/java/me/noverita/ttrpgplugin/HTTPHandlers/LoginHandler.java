package me.noverita.ttrpgplugin.HTTPHandlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import me.noverita.ttrpgplugin.DataClasses.SessionStorage;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class LoginHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        UUID uuid = UUID.randomUUID();
        SessionStorage.sessions.put(uuid,"Noverita");
        exchange.getResponseHeaders().add("Set-Cookie","name="+uuid);

        String htmlResponse = """
                <head>
                  <meta http-equiv="refresh" content="1; URL=localhost:9001/character" />
                </head>
                <body>
                  <p>If you are not redirected in 3 seconds, <a href="localhost:9001/character">click here</a>.</p>
                </body>
                """;

        OutputStream outputStream = exchange.getResponseBody();
        exchange.sendResponseHeaders(200, htmlResponse.length());
        outputStream.write(htmlResponse.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
