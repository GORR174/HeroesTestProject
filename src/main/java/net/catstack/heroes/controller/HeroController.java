package net.catstack.heroes.controller;

import net.catstack.heroes.model.Hero;
import net.catstack.heroes.model.Pudge;
import net.catstack.heroes.repository.HeroRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HeroController {

    private final HeroRepository heroRepository;

    public HeroController(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @GetMapping("/hero")
    public Hero getHero() {
        return new Pudge(10);
    }

    @PostMapping("/hero")
    public Hero addHero(@RequestBody Hero hero) {
        heroRepository.addHero(hero);
        return new Hero(100, hero.getName(), hero.getLevel(), hero.getUltimate());
    }

    @GetMapping("/heroes")
    public List<Hero> getHeroes() {
        return heroRepository.getHeroes();
    }
}
