package net.catstack.heroes.model;

import java.io.*;

public class Pudge extends Hero implements Externalizable {
    public Pudge() {
        super("Pudge", 1, "Dismember");
    }

    public Pudge(int level) {
        super("Pudge", level, "Dismember");
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeInt(getLevel());
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        int level = objectInput.readInt();

        setLevel(level);
    }
}
