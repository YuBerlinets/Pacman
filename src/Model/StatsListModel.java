package Model;

import Controller.PlayerScore;

import javax.swing.*;
import java.util.List;

public class StatsListModel extends AbstractListModel<PlayerScore> {
    List<PlayerScore> statistics;

    public StatsListModel(List<PlayerScore> statistics) {
        this.statistics = statistics;
    }
    @Override
    public int getSize() {
        return statistics.size();
    }

    @Override
    public PlayerScore getElementAt(int index) {
        return statistics.get(index);
    }
}
