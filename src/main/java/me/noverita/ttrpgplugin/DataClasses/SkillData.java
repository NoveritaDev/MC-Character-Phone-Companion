package me.noverita.ttrpgplugin.DataClasses;

import java.util.HashMap;
import java.util.Map;

public class SkillData {
    public Map<String, Integer> skillRanks;

    public SkillData() {
        skillRanks = new HashMap<>();

        skillRanks.put("Acrobatics",0);
        skillRanks.put("Animal Handling",0);
        skillRanks.put("Arcana",0);
        skillRanks.put("Athletics",0);
        skillRanks.put("Deception",0);
        skillRanks.put("History",0);
        skillRanks.put("Insight",0);
        skillRanks.put("Intimidation",0);
        skillRanks.put("Investigation",0);
        skillRanks.put("Medicine",0);
        skillRanks.put("Nature",0);
        skillRanks.put("Perception",0);
        skillRanks.put("Performance",0);
        skillRanks.put("Persuasion",0);
        skillRanks.put("Religion",0);
        skillRanks.put("Sleight of Hand",0);
        skillRanks.put("Stealth",0);
        skillRanks.put("Survival",0);
    }
}
