package net.catstack.heroes.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Hero {
    private long id;

    @Size(min = 4, max = 14)
    private String name;

    @Min(value = 0)
    @Max(value = 30)
    private int level;

    private String ultimate;

    public Hero() {

    }

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

    public void setLevel(int level) {
        this.level = level;
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
