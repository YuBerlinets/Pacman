import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Statistics {
    private Map<String, Integer> personStats;
    private final String path = "resources/stats.txt";

    Statistics() {
        personStats = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line = null;
            int index = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] person;
                person = line.split("\\s+");
                personStats.put(person[0], Integer.parseInt(person[1]));
                index++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JPanel getStatistics() {
        JPanel pplStats = new JPanel();
        int index = 1;
        for (String item : personStats.keySet()) {
            JLabel personScore = new JLabel(index++ + ". Name: " + item + " - " + personStats.get(item));
            personScore.setFont(new Font("sarif", Font.PLAIN, 26));
            personScore.setForeground(Color.WHITE);
            pplStats.add(personScore);
        }
        pplStats.setLayout(new BoxLayout(pplStats, BoxLayout.Y_AXIS));
        pplStats.setBackground(Color.BLACK);
        return pplStats;
    }
}
