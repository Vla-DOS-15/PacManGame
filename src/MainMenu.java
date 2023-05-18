import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        // Створення кнопки "Почати гру"
        JButton startGameButton = new JButton("Почати гру");
        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                PacmanMap pacmanMap = new PacmanMap();
                pacmanMap.setVisible(true);
            }
        });
// Створення кнопки "Результати"
        JButton resultsButton = new JButton("Результати");
        resultsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                ResultTable resultTable = new ResultTable();

            }
        });
        // Створення кнопки "Вийти"
        JButton exitButton = new JButton("Вийти");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

// Групування кнопок у панелі за допомогою GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 5, 5);
        panel.add(startGameButton, constraints);
        constraints.gridy = 1;
        panel.add(resultsButton, constraints); // Додавання нової кнопки
        constraints.gridy = 2;
        panel.add(exitButton, constraints);

// Зміна розмірів контейнера
        panel.setPreferredSize(new Dimension(200, 200)); // Збільшення контейнера для нової кнопки

// Додавання контейнера до вікна з допомогою менеджера BorderLayout
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);

// Налаштування вікна
        setTitle("Меню");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(new Dimension(300, 250)); // Виділення більшого розміру для нової кнопки
    }
    // Метод для зчитування даних з файлу

}
