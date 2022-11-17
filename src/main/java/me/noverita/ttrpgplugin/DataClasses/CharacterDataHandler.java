package me.noverita.ttrpgplugin.DataClasses;

import java.util.HashMap;
import java.util.Map;

public class CharacterDataHandler {
    private static CharacterDataHandler instance;

    public static CharacterDataHandler getInstance() {
        if (instance == null) {
            instance = new CharacterDataHandler();
        }
        return instance;
    }

    private Map<String, TTRPGCharacter> characters;

    private CharacterDataHandler() {
        characters = new HashMap<>();
        characters.put("Noverita", new TTRPGCharacter("Noverita", "A default character."));
        characters.put("Nova", new TTRPGCharacter("Nova", "A slightly more interesting character."));
    }

    public TTRPGCharacter getCharacter(String name) {
        return characters.get(name);
    }
}
