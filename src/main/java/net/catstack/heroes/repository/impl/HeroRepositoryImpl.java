package net.catstack.heroes.repository.impl;

import net.catstack.heroes.model.Hero;
import net.catstack.heroes.repository.HeroRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HeroRepositoryImpl implements HeroRepository {

    private static final String CREATE_TABLE_QUERY =
            "CREATE TABLE IF NOT EXISTS heroes (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name VARCHAR NOT NULL," +
                    "level INTEGER NOT NULL," +
                    "ultimate VARCHAR NOT NULL" +
                    ");";

    private static final String INSERT_HERO_QUERY = "INSERT INTO heroes (name, level, ultimate) VALUES (?, ?, ?);";

    private static final String GET_HEROES_QUERY = "SELECT * FROM heroes";

    private Connection connection;

    public HeroRepositoryImpl(Connection connection) {
        this.connection = connection;

        createHeroTable();
    }

    private void createHeroTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE_QUERY);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void addHero(Hero hero) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_HERO_QUERY)) {
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
        List<Hero> resultList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet results = statement.executeQuery(GET_HEROES_QUERY);

            while (results.next()) {
                long id = results.getLong("id");
                String name = results.getString("name");
                int level = results.getInt("level");
                String ultimate = results.getString("ultimate");

                resultList.add(new Hero(id, name, level, ultimate));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return resultList;
    }
}
