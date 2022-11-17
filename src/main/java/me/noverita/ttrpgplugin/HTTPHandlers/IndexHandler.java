package me.noverita.ttrpgplugin.HTTPHandlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class IndexHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        OutputStream outputStream = exchange.getResponseBody();
        StringBuilder htmlBuilder = new StringBuilder();

        htmlBuilder.append("<html>")
                .append("<body>")
                .append("<h1>")
                .append("TTRPG In Minecraft")
                .append("</h1>")
                .append("<p>This is a suplemental tool to hopefully make doing RP in Minecraft a bit smoother.<br>Hopefully.<br>You can always use the in game commands if this doesn't work for you.<br>You'll need to create an account in game using the /register <password> command.</p>")
                .append("<h2>Login</h2>")
                .append("""
                        <form method="POST" action="/login">
                            <label for="uname"><b>Username</b></label>
                            <input type="text" placeholder="Enter Username" name="uname" required><br>

                            <label for="psw"><b>Password</b></label>
                            <input type="password" placeholder="Enter Password" name="psw" required><br>

                            <button type="submit">Login</button>
                        </form>
                        """)
                .append("</body>")
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
