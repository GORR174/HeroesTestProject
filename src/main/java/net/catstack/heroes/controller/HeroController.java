package net.catstack.heroes.controller;

import net.catstack.heroes.model.Hero;
import net.catstack.heroes.model.Pudge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("api")
public class HeroController {

    @GetMapping("/hero")
    public Hero getHero() {
        return new Pudge(10);
    }
}
