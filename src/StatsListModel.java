import javax.swing.*;
import java.util.List;

public class StatsListModel extends AbstractListModel<PlayerScore> {
    List<PlayerScore> statistics;

    public StatsListModel(List<PlayerScore> statistics) {
        this.statistics = statistics;
    }
    public void addPlayerScore(PlayerScore playerScore) {
        statistics.add(playerScore);
        fireContentsChanged(this, 0, statistics.size() - 1);
    }
    public void updateList() {
        fireContentsChanged(this, 0, statistics.size() - 1);
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
