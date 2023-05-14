import javax.swing.*;

public class Test {

    public static void main(String[] args) {

        JFrame frame = new JFrame("List Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create a DefaultListModel to hold the data for the JList
        DefaultListModel<String> listModel = new DefaultListModel<>();

        // create a JList with the DefaultListModel
        JList<String> list = new JList<>(listModel);
        list.setBounds(10, 10, 150, 200);

        // create a JTextField for entering new information
        JTextField textField = new JTextField();
        textField.setBounds(170, 10, 120, 20);

        // create a JButton for adding new information to the JList
        JButton addButton = new JButton("Add");
        addButton.setBounds(170, 40, 80, 20);
        addButton.addActionListener(e -> {
            // get the text from the JTextField
            String newInfo = textField.getText();
            // add the new information to the DefaultListModel
            listModel.addElement(newInfo);
            // set the JList's model to the updated DefaultListModel
            list.setModel(listModel);
            // clear the JTextField
            textField.setText("");
        });

        // add the JList, JTextField, and JButton to the JFrame
        frame.add(list);
        frame.add(textField);
        frame.add(addButton);

        frame.setSize(300, 250);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
