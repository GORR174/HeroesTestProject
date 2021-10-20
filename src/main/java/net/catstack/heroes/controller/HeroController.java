package net.catstack.heroes.controller;

import net.catstack.heroes.model.Hero;
import net.catstack.heroes.model.Pudge;
import net.catstack.heroes.model.errors.ValidationError;
import net.catstack.heroes.service.HeroService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class HeroController {

    private final HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping("/hero")
    public Hero getHero() {
        return new Pudge(10);
    }

    @GetMapping("/hero/{id}")
    public Hero getHero(@PathVariable long id) {
        return heroService.getHeroById(id);
    }

    @PostMapping("/hero")
    public Hero addHero(@RequestBody @Valid Hero hero) {
        return heroService.saveHero(hero);
    }

    @GetMapping("/heroes")
    public List<Hero> getHeroes() {
        return heroService.getHeroes();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationError handleValidationExceptions(MethodArgumentNotValidException ex) {
        return new ValidationError(ex);
    }
}
