package me.noverita.ttrpgplugin.DataClasses;

import java.util.Map;
import java.util.UUID;

public class TTRPGCharacter {
    private String name;
    private String description;
    private SkillData skills;
    private UUID mcuuid;

    public TTRPGCharacter(String name, String description) {
        this.name = name;
        this.description = description;
        this.skills = new SkillData();
        this.mcuuid = UUID.fromString("448340f6e5b24b288404daaac771c669");
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public SkillData getSkills() {
        return skills;
    }

    public UUID getMCUUID() {
        return mcuuid;
    }
}
