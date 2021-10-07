package net.catstack.heroes.model;

public class Hero {
    private long id;
    private String name;
    private int level;
    private String ultimate;

    public Hero(String name, int level, String ultimate) {
        this.name = name;
        this.level = level;
        this.ultimate = ultimate;
    }

    public Hero(long id, String name, int level, String ultimate) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.ultimate = ultimate;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public String getUltimate() {
        return ultimate;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", ultimate='" + ultimate + '\'' +
                '}';
    }
}
