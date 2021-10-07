package net.catstack.heroes.repository.impl;

import net.catstack.heroes.model.Hero;
import net.catstack.heroes.repository.HeroRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HeroRepositoryImpl implements HeroRepository {

    private Connection connection;

    public HeroRepositoryImpl(Connection connection) {
        this.connection = connection;

        createHeroTable();
    }

    private void createHeroTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS heroes (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name VARCHAR NOT NULL," +
                    "level INTEGER NOT NULL," +
                    "ultimate VARCHAR NOT NULL" +
                    ");");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void addHero(Hero hero) {
        String request = "INSERT INTO heroes (name, level, ultimate) VALUES (?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setString(1, hero.getName());
            statement.setLong(2, hero.getLevel());
            statement.setString(3, hero.getUltimate());

            statement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public List<Hero> getHeroes() {
        ArrayList<Hero> resultList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet results = statement.executeQuery("SELECT * FROM heroes");

            while (results.next()) {
                long id = results.getLong(1);
                String name = results.getString(2);
                int level = results.getInt(3);
                String ultimate = results.getString(4);

                resultList.add(new Hero(id, name, level, ultimate));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return resultList;
    }
}
