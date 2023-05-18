import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ResultTable {
    JFrame f;
    JTable j;
    String[][] data;

    public ResultTable() {
        f = new JFrame();
        f.setTitle("Таблиця результатів");

        // Зчитування даних з файлу
        data = readDataFromFile("./src/data/results.txt");

        String[] columnNames = {"Нікнейм", "Очки", "Дата"};

        j = new JTable(data, columnNames);
        j.setBounds(30, 40, 200, 300);

        JScrollPane sp = new JScrollPane(j);
        f.add(sp);
        f.setSize(500, 200);
        f.setVisible(true);
    }

    private String[][] readDataFromFile(String filename) {
        ArrayList<String[]> dataList = new ArrayList<>();
        try {
            File file = new File(filename);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(" ");
                dataList.add(data);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[][] dataArray = new String[dataList.size()][3];
        for (int i = 0; i < dataList.size(); i++) {
            dataArray[i] = dataList.get(i);
        }
        return dataArray;
    }
}