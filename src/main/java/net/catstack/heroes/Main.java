package net.catstack.heroes;

import net.catstack.heroes.jdbc.SQLiteDBConnector;
import net.catstack.heroes.model.Hero;
import net.catstack.heroes.model.Pudge;
import net.catstack.heroes.repository.HeroRepository;
import net.catstack.heroes.repository.impl.HeroRepositoryImpl;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try (SQLiteDBConnector connector = new SQLiteDBConnector("heroes.db")) {
            HeroRepository heroRepository = new HeroRepositoryImpl(connector.getConnection());

//            heroRepository.addHero(new Hero("Test", 7, "Testing"));
//            heroRepository.addHero(new Hero("Iron Man", 15, "Flying"));

            heroRepository.getHeroes().forEach(System.out::println);
        }

        String serializationFile = "Pudge.bin";

        serializePudge(new Pudge(12), serializationFile);
        deserializePudge(serializationFile);
    }

    private static void serializePudge(Pudge pudge, String filePath) {
        try (
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))
        ) {
            oos.writeObject(pudge);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deserializePudge(String filePath) {
        try (
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))
        ) {
            Pudge pudge = (Pudge) ois.readObject();

            System.out.println(pudge);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
