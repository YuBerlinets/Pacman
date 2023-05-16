import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Statistics {

    private JList<PlayerScore> pplStats;
    private List<PlayerScore> people;
    private final String path = "resources/stats.txt";
    private StatsListModel statsListModel;

    Statistics() {
        people = new ArrayList<>();

        readDataFromFile();
        statsListModel = new StatsListModel(people);
        pplStats = new JList<>(statsListModel);
        pplStats.setBackground(Color.BLACK);
        pplStats.setForeground(Color.WHITE);
        pplStats.setFont(new Font("sarif", Font.PLAIN, 30));
    }

    public JList<PlayerScore> getStats(){
        return pplStats;
    }

    public void addPersonScore(PlayerScore stats){
        people.add(stats);
        saveDataToFile();
    }
    public void readDataFromFile(){
        File file = new File(path);
        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path))) {
                people = (List<PlayerScore>) inputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Failed to load data from file");
            }
        }
    }


    private void saveDataToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            outputStream.writeObject(people);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

