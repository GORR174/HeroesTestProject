package net.catstack.heroes;

import net.catstack.heroes.jdbc.SQLiteDBConnector;
import net.catstack.heroes.model.Hero;
import net.catstack.heroes.repository.HeroRepository;
import net.catstack.heroes.repository.impl.HeroRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        try (SQLiteDBConnector connector = new SQLiteDBConnector("heroes.db")) {
            HeroRepository heroRepository = new HeroRepositoryImpl(connector.getConnection());

            heroRepository.addHero(new Hero("Test", 7, "Testing"));
            heroRepository.addHero(new Hero("Iron Man", 15, "Flying"));

            heroRepository.getHeroes().forEach(System.out::println);
        }
    }
}
