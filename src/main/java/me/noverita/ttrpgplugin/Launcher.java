package me.noverita.ttrpgplugin;

import com.sun.net.httpserver.HttpServer;
import me.noverita.ttrpgplugin.HTTPHandlers.CharacterHandler;
import me.noverita.ttrpgplugin.HTTPHandlers.IndexHandler;
import me.noverita.ttrpgplugin.HTTPHandlers.LoginHandler;
import me.noverita.ttrpgplugin.HTTPHandlers.RollHandler;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Launcher {
    public static void launch() {
        try {
            Bukkit.getLogger().info("Starting web server.");
            HttpServer server = HttpServer.create(new InetSocketAddress("142.44.191.81", 8671),10);
            Bukkit.getLogger().info("Starting web server.");
            server.createContext("/", new IndexHandler());
            Bukkit.getLogger().info("Root route established");
            server.createContext("/login", new LoginHandler());
            Bukkit.getLogger().info("Login route established");
            server.createContext("/roll", new RollHandler());
            Bukkit.getLogger().info("Roll route established");
            server.createContext("/character", new CharacterHandler());
            Bukkit.getLogger().info("Character route established");
            server.setExecutor(null);
            Bukkit.getLogger().info("Executor set to  null");
            server.start();
            Bukkit.getLogger().info("Web server started.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
