package gg.bayes.challenge.util;

import java.util.regex.Pattern;

public class RegexUtil {

//    delimiters
    public static final String BUY_ITEM_DELIMITER = " buys item ";
    public static final String NPC_DOTA_HERO = "npc_dota_hero_";
    public static final String IS_KILLED_BY = " is killed by ";
    public static final String CASTS_ABILITY = " casts ability ";
    public static final String TOGGLES_ABILITY = " toggles ability ";
    public static final String HITS = " hits ";
    public static final String NPC_DOTA_NEUTRAL = "npc_dota_neutral_";
    public static final String NPC_DOTA_GOODGUYS =  "npc_dota_goodguys_";
    public static final String NPC_DOTA_BADGUYS =  "npc_dota_badguys_";
    public static final String NPC_DOTA_OBSERVER =  "npc_dota_observer_";
    public static final String TOGGLES = "toggles";


//    patterns
    public static final Pattern timePatternExclude = Pattern.compile("(?<=^\\[(\\d{2}):(\\d{2}):(\\d{2})\\.(\\d{3})\\]\\s).*");
    public static final Pattern timePattern = Pattern.compile("^\\[(\\d{2}):(\\d{2}):(\\d{2})\\.(\\d{3})\\]\\s");

}
