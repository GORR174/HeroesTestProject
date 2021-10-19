package net.catstack.heroes.repository;

import net.catstack.heroes.model.Hero;

import java.util.List;

public interface HeroRepositoryJdbc {
    void addHero(Hero hero);

    Hero getHeroById(long id);

    List<Hero> getHeroes();
}
