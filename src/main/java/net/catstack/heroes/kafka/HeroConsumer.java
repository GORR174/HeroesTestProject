package net.catstack.heroes.kafka;

import net.catstack.heroes.model.Hero;
import net.catstack.heroes.service.HeroService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class HeroConsumer {

    private final HeroService heroService;

    public HeroConsumer(HeroService heroService) {
        this.heroService = heroService;
    }

    @KafkaListener(topics = "kafka-hero-producer")
    public void heroListener(ConsumerRecord<String, Hero> heroRecord) {
        System.out.println("Consume: [" + heroRecord.key() + "] " + heroRecord.value());

        heroService.saveHero(heroRecord.value());
    }
}
