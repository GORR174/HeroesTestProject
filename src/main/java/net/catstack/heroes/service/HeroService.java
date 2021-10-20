package net.catstack.heroes.service;

import net.catstack.heroes.model.Hero;
import net.catstack.heroes.repository.HeroRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroService {
    private final HeroRepository heroRepository;
    private final KafkaTemplate<String, Hero> kafkaTemplate;

    public HeroService(HeroRepository heroRepository, KafkaTemplate<String, Hero> kafkaTemplate) {
        this.heroRepository = heroRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Hero getHeroById(long id) {
        return heroRepository.findById(id).orElse(null);
    }

    public Hero saveHero(Hero hero) {
        Hero savedHero = heroRepository.save(hero);

        kafkaTemplate.send("kafka-hero-consumer", "new-hero", savedHero);

        return savedHero;
    }

    public List<Hero> getHeroes() {
        return heroRepository.findAll();
    }
}
