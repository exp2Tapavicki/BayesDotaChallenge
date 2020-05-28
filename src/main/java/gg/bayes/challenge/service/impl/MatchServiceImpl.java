package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.constant.Const;
import gg.bayes.challenge.repository.*;
import gg.bayes.challenge.rest.model.*;
import gg.bayes.challenge.service.MatchService;
import gg.bayes.challenge.util.RegexUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;

@Slf4j
@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    HeroRepository heroRepository;
    @Autowired
    MatchRepository matchRepository;
    @Autowired
    HeroDamageRepository heroDamageRepository;
    @Autowired
    HeroItemsRepository heroItemsRepository;
    @Autowired
    HeroKillsRepository heroKillsRepository;
    @Autowired
    HeroSpellsRepository heroSpellsRepository;

    @Autowired
    public MatchServiceImpl() {
    }


//    [00:00:04.999] game state is now 2
//    [00:08:43.460] npc_dota_hero_pangolier casts ability pangolier_swashbuckle (lvl 1) on dota_unknown
//    actions
//          casts buys uses hits
//    [00:12:15.108] npc_dota_neutral_harpy_scout is killed by npc_dota_hero_pangolier
//    [00:16:11.717] npc_dota_neutral_forest_troll_high_priest is killed by npc_dota_hero_snapfire
//    [00:15:14.031] npc_dota_observer_wards is killed by npc_dota_observer_wards
//    [00:16:10.418] npc_dota_goodguys_siege is killed by npc_dota_hero_bloodseeker
//    [00:16:26.913] npc_dota_badguys_siege is killed by npc_dota_hero_abyssal_underlord
//    [00:16:15.116] npc_dota_goodguys_tower1_mid is killed by npc_dota_badguys_siege
//    [00:17:37.296] npc_dota_dark_troll_warlord_skeleton_warrior is killed by npc_dota_hero_snapfire
//    [00:17:37.330] npc_dota_neutral_dark_troll_warlord is killed by npc_dota_hero_snapfire
    @Override
    public Long ingestMatch(String payload) {
//        RegexUtil.
        String[] rows = payload.split("\n");
        Matcher timeMatcher;

//        save match and return id of match
        Match match = new Match();
        match.setName(UUID.randomUUID().toString());
        matchRepository.save(match);

        for (int i = 0; i < rows.length; i++) {
            String row = rows[i];
            if (row.contains(RegexUtil.NPC_DOTA_HERO)) {
                String heroText = row.split(Const.SPACE)[Const.INT_ONE];
                Hero hero;
                String heroName;
                if (heroText.startsWith(RegexUtil.NPC_DOTA_HERO)) {
                    heroName = heroText.replace(RegexUtil.NPC_DOTA_HERO, Const.EMPTY_STRING);
                    hero = heroRepository.get(heroName);
                    if (hero == null) {
                        hero = new Hero();
                        hero.setName(heroName);
                        heroRepository.save(hero);
                    }
                } else {
//                    if ( heroText.startsWith(RegexUtil.NPC_DOTA_NEUTRAL) ||
//                            heroText.startsWith(RegexUtil.NPC_DOTA_GOODGUYS) ||
//                            heroText.startsWith(RegexUtil.NPC_DOTA_BADGUYS) ||
//                            heroText.startsWith(RegexUtil.NPC_DOTA_OBSERVER)
//                    ) {
//                        continue;
//                        throw new NotImplementedException("Not implemented");
//                    }
                    continue;
                }

                String heroAction = row.split(Const.SPACE)[Const.INT_TWO];
                if (row.contains(RegexUtil.IS_KILLED_BY)) {
                    HeroKills heroKills = heroKillsRepository.get(hero.getId(), match.getId());
                    if (heroKills == null) {
                        heroKills = new HeroKills();
                        heroKills.setKills(Const.INT_ONE);
                        heroKills.setMatchId(match.getId());
                        heroKills.setHeroId(hero.getId());
                        heroKills.setHero(heroName);
                    } else {
                        heroKills.setKills(heroKills.getKills() + Const.INT_ONE);
                    }
                    heroKillsRepository.save(heroKills);
                } else if (heroAction.equals(Const.CASTS)) {
                    String castText = row.split(RegexUtil.CASTS_ABILITY)[1];
                    String spell = castText.split(Const.SPACE)[0];
                    String level = castText.split(Const.SPACE)[2].replace(Const.CLOSE_ROUND_BRACKET, Const.EMPTY_STRING);

                    HeroSpells heroSpells = heroSpellsRepository.get(hero.getId(), match.getId(), spell);
                    if (heroSpells == null) {
                        heroSpells = new HeroSpells();
                        heroSpells.setHeroId(hero.getId());
                        heroSpells.setMatchId(match.getId());
                        heroSpells.setSpell(spell);
                        heroSpells.setCasts(Const.INT_ONE);
                    } else {
                        heroSpells.setCasts(heroSpells.getCasts() + Const.INT_ONE);
                    }
                    heroSpellsRepository.save(heroSpells);
                } else if (heroAction.equals(RegexUtil.TOGGLES)){
                    String castText = row.split(RegexUtil.TOGGLES_ABILITY)[1];
                    String spell = castText.split(Const.SPACE)[0];
                    String level = castText.split(Const.SPACE)[2].replace(Const.CLOSE_ROUND_BRACKET, Const.EMPTY_STRING);

                    HeroSpells heroSpells = heroSpellsRepository.get(hero.getId(), match.getId(), spell);
                    if (heroSpells == null) {
                        heroSpells = new HeroSpells();
                        heroSpells.setHeroId(hero.getId());
                        heroSpells.setMatchId(match.getId());
                        heroSpells.setSpell(spell);
                        heroSpells.setCasts(Const.INT_ONE);
                    } else {
                        heroSpells.setCasts(heroSpells.getCasts() + Const.INT_ONE);
                    }
                    heroSpellsRepository.save(heroSpells);
                } else if (heroAction.equals(Const.HITS)) {
                    String hitText = row.split(RegexUtil.HITS)[1];
                    String spell = hitText.split(Const.SPACE)[2];
                    Integer damage = Integer.valueOf(hitText.split(Const.SPACE)[4]);

                    HeroSpells heroSpells = heroSpellsRepository.get(hero.getId(), match.getId(), spell);
                    if (heroSpells == null) {
                        heroSpells = new HeroSpells();
                        heroSpells.setHeroId(hero.getId());
                        heroSpells.setMatchId(match.getId());
                        heroSpells.setSpell(spell);
                        heroSpells.setCasts(Const.INT_ONE);
                    } else {
                        heroSpells.setCasts(heroSpells.getCasts() + Const.INT_ONE);
                    }

                    String damagedHeroName = hitText.split(Const.SPACE)[0].replace(RegexUtil.NPC_DOTA_HERO, Const.EMPTY_STRING);
                    Hero damagedHero = heroRepository.get(damagedHeroName);
                    if (damagedHero == null) {
                        damagedHero = new Hero();
                        damagedHero.setName(damagedHeroName);
                        heroRepository.save(damagedHero);
                    }

                    HeroDamage heroDamage = heroDamageRepository.get(hero.getId(), match.getId(), damagedHero.getId());
                    if (heroDamage == null) {
                        heroDamage = new HeroDamage();
                        heroDamage.setMatchId(match.getId());
                        heroDamage.setHeroId(hero.getId());
                        heroDamage.setTargetHeroId(damagedHero.getId());
                        heroDamage.setTarget(damagedHero.getName());
                        heroDamage.setDamageInstances(Const.INT_ONE);
                        heroDamage.setTotalDamage(damage);
                    } else {
                        heroDamage.setDamageInstances(heroDamage.getDamageInstances() + Const.INT_ONE);
                        heroDamage.setTotalDamage(heroDamage.getTotalDamage() + damage);
                    }
                    heroDamageRepository.save(heroDamage);
                } else if (heroAction.equals(Const.BUYS)) {
                    HeroItems heroItems = new HeroItems();
                    heroItems.setHeroId(hero.getId());
                    heroItems.setMatchId(match.getId());
                    heroItems.setItem(row.split(RegexUtil.BUY_ITEM_DELIMITER)[1]);
                    timeMatcher = RegexUtil.timePattern.matcher(row);
                    if (timeMatcher.find()) {
                        String time = timeMatcher.group(0).trim();
                        time = time.replace(Const.OPEN_SQUARE_BRACKET, Const.EMPTY_STRING);
                        time = time.replace(Const.CLOSE_SQUARE_BRACKET, Const.EMPTY_STRING);
                        LocalTime localTime = LocalTime.parse(time);
                        long timeStamp = localTime.toNanoOfDay() / Const.LONG_ONE_MILION;
                        heroItems.setTimestamp(timeStamp);
                    }
                    heroItemsRepository.save(heroItems);
                } else if (heroAction.equals(Const.USES)) {
                    continue;
//                    throw new NotImplementedException("Uses not implemented");
                } else {
                    continue;
//                    throw new NotImplementedException("Unknown, not implemented");
                }
            }
        }
        return match.getId();
    }

    @Override
    public List<HeroKills> getHeroKills(Long matchId) {
        return heroKillsRepository.getHeroKillsByMatch(matchId);
    }

    @Override
    public List<HeroItems> getHeroItems(Long matchId, String heroName) {
        return heroItemsRepository.getHeroItemsByMatchAndHero(matchId, heroName);
    }

    @Override
    public List<HeroSpells> getHeroSpells(Long matchId, String heroName) {
        return heroSpellsRepository.getHeroSpellsByMatchAndHero(matchId, heroName);
    }

    @Override
    public List<HeroDamage> getHeroDamage(Long matchId, String heroName) {
        return heroDamageRepository.getHeroDamageByMatchAndHero(matchId, heroName);
    }
}
