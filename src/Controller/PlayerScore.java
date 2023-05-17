package Controller;

import java.io.Serializable;

public class PlayerScore implements Serializable,Comparable<PlayerScore>{
    private String name;
    private int score;

    public PlayerScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return name + " - " + score;
    }

    @Override
    public int compareTo(PlayerScore o) {
        if (this.getScore() > o.getScore())
            return -1;
        else if (this.getScore() == o.getScore())
            return 0;
        else
            return 1;
    }
}
