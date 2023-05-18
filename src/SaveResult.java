import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SaveResult extends JFrame implements ActionListener {

    private JButton submitButton;
    private JTextField nameField;
    private int point;

    public SaveResult(int point) {
        this.point = point;
        JLabel nameLabel = new JLabel("Нік:");
        nameField = new JTextField(20);
        submitButton = new JButton("Зберегти");

        this.setLayout(new FlowLayout());
        this.add(nameLabel);
        this.add(nameField);
        this.add(submitButton);

        submitButton.addActionListener(this);

        this.setTitle("Збереження результатів");
        this.setSize(400, 80);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Зберегти") {
            String fileName = "./src/data/results.txt";
            String name = nameField.getText();
            LocalDate date = LocalDate.now();
            WriteFile(fileName, name, point, date);
            this.dispose();
        }
    }

    public static void WriteFile(String fileName, String name, int point, LocalDate date) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            // Записуємо у файл
            bufferedWriter.write("\n"+name + " " + point + " " + date.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}