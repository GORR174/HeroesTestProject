package net.catstack.heroes.repository.impl;

import net.catstack.heroes.model.Hero;
import net.catstack.heroes.repository.HeroRepositoryJdbc;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HeroRepositoryJdbcImpl implements HeroRepositoryJdbc {

    private static final String CREATE_TABLE_QUERY =
            "CREATE TABLE IF NOT EXISTS heroes (" +
                    "id INTEGER PRIMARY KEY auto_increment," +
                    "name VARCHAR(50) NOT NULL," +
                    "level INTEGER NOT NULL ," +
                    "ultimate VARCHAR(50) NOT NULL" +
                    ");";

    private static final String INSERT_HERO_QUERY = "INSERT INTO heroes (name, level, ultimate) VALUES (?, ?, ?);";

    private static final String GET_HEROES_QUERY = "SELECT * FROM heroes";

    private static final String GET_HERO_BY_ID_QUERY = "SELECT * FROM heroes WHERE id=?";

    private Connection connection;

    public HeroRepositoryJdbcImpl(Connection connection) {
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
    public Hero getHeroById(long id) {
        try (PreparedStatement statement = connection.prepareStatement(GET_HERO_BY_ID_QUERY)) {
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            return getHeroFromResultSet(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();

            return null;
        }
    }

    @Override
    public List<Hero> getHeroes() {
        List<Hero> resultList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet results = statement.executeQuery(GET_HEROES_QUERY);

            while (results.next()) {
                resultList.add(getHeroFromResultSet(results));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return resultList;
    }

    private Hero getHeroFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        int level = resultSet.getInt("level");
        String ultimate = resultSet.getString("ultimate");

        return new Hero(id, name, level, ultimate);
    }
}
