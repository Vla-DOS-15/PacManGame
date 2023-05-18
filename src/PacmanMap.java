import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class PacmanMap extends JFrame {
    private static final int CELL_SIZE = 25;
    private static final int ROWS = 31;
    private static final int COLUMNS = 28;
    private JTable mapTable;
    private DefaultTableModel tableModel;
    private int pacmanRow = (ROWS / 2) + 2;
    private int pacmanColumn = (COLUMNS / 2) +4;
    private int ghostRow = (ROWS / 2) + 2;
    private int ghostColumn = (COLUMNS / 2) - 5;
    final boolean W=false; // Wall.
    final boolean F=true; // Crossroads with food
    final boolean E=false; // Empty crossroads
    private boolean obstacleMap[][] = {
            {W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W},
            {W,F,F,F,F,F,F,F,F,F,F,F,F,W,W,F,F,F,F,F,F,F,F,F,F,F,F,W},
            {W,F,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,F,W},
            {W,F,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,F,W},
            {W,F,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,F,W},
            {W,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,W},
            {W,F,W,W,W,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,W,W,W,F,W},
            {W,F,W,W,W,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,W,W,W,F,W},
            {W,F,F,F,F,F,F,W,W,F,F,F,F,W,W,F,F,F,F,W,W,F,F,F,F,F,F,W},
            {W,W,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,W,W},
            {E,E,E,E,E,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,E,E,E,E,E},
            {E,E,E,E,E,W,F,W,W,F,F,F,F,F,F,F,F,F,F,W,W,F,W,E,E,E,E,E},
            {E,E,E,E,E,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,E,E,E,E,E},
            {W,W,W,W,W,W,F,W,W,F,W,E,E,E,E,E,E,W,F,W,W,F,W,W,W,W,W,W},
            {F,F,F,F,F,F,F,F,F,F,W,E,E,E,E,E,E,W,F,F,F,F,F,F,F,F,F,F},
            {W,W,W,W,W,W,F,W,W,F,W,E,E,E,E,E,E,W,F,W,W,F,W,W,W,W,W,W},
            {E,E,E,E,E,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,E,E,E,E,E},
            {E,E,E,E,E,W,F,W,W,F,F,F,F,F,F,F,F,F,F,W,W,F,W,E,E,E,E,E},
            {E,E,E,E,E,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,E,E,E,E,E},
            {W,W,W,W,W,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,W,W,W,W,W},
            {W,F,F,F,F,F,F,F,F,F,F,F,F,W,W,F,F,F,F,F,F,F,F,F,F,F,F,W},
            {W,F,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,F,W},
            {W,F,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,F,W},
            {W,F,F,F,W,W,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,W,W,F,F,F,W},
            {W,W,W,F,W,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,W,F,W,W,W},
            {W,W,W,F,W,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,W,F,W,W,W},
            {W,F,F,F,F,F,F,W,W,F,F,F,F,W,W,F,F,F,F,W,W,F,F,F,F,F,F,W},
            {W,F,W,W,W,W,W,W,W,W,W,W,F,W,W,F,W,W,W,W,W,W,W,W,W,W,F,W},
            {W,F,W,W,W,W,W,W,W,W,W,W,F,W,W,F,W,W,W,W,W,W,W,W,W,W,F,W},
            {W,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,W},
            {W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W}
    };

    private int counter = 1;
    private int countPoint = 0;
    private JLabel counterLabel;
    private JLabel livesLabel; // Додаємо JLabel для відображення кількості життів

    Enemy enemyBlueRight;
    Enemy enemyRedLeft;
    Enemy enemyOrangeRight;
    public PacmanMap()  {
        initUI();
        ImageIcon imageIconRedLeft = new ImageIcon(getClass().getResource("img/" + "RedLeft.png"));
        ImageIcon imageIconBlueRight = new ImageIcon(getClass().getResource("img/" + "BlueRight.png"));
            enemyBlueRight = new Enemy(ghostRow - 6, ghostColumn, obstacleMap, 500, tableModel, imageIconBlueRight, pacmanRow, pacmanColumn, "enemyBlue");
        Thread enemyThreadBlueRight = new Thread(enemyBlueRight);
        enemyThreadBlueRight.start();

        enemyRedLeft = new Enemy(ghostRow+3, ghostColumn, obstacleMap, 500, tableModel, imageIconRedLeft, pacmanRow, pacmanColumn, "enemyRed");
        Thread enemyThreadenemyRedLeft = new Thread(enemyRedLeft);
        enemyThreadenemyRedLeft.start();

        ImageIcon imageIconOrangeRight = new ImageIcon(getClass().getResource("img/" + "OrangeRight.png"));
        enemyOrangeRight = new Enemy(ghostRow-7, ghostColumn + 7, obstacleMap, 500, tableModel, imageIconOrangeRight, pacmanRow, pacmanColumn, "enemyOrange");
        Thread enemyThreadOrangeRight = new Thread(enemyOrangeRight);
        enemyThreadOrangeRight.start();
    }

    private void initUI(){
        tableModel = new DefaultTableModel(ROWS, COLUMNS);
        ImageIcon photo = new ImageIcon(getClass().getResource("img/" +"PacmanRight.png"));
        Image image = photo.getImage();

        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                if (!obstacleMap[row][column]) {
                    tableModel.setValueAt("X", row, column);
                }
                else if(obstacleMap[row][column]) {
                    tableModel.setValueAt("*", row, column);
                    countPoint++;
                }
            }
        }

        tableModel.setValueAt(image, pacmanRow, pacmanColumn);
        mapTable = new JTable(tableModel);
        mapTable.setGridColor(Color.black);
        mapTable.setRowHeight(CELL_SIZE);
        for (int column = 0; column < COLUMNS; column++) {
            mapTable.getColumnModel().getColumn(column).setPreferredWidth(CELL_SIZE);
        }
        mapTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public void setValue(Object value) {

                if (value instanceof Image) {
                    ImageIcon icon = new ImageIcon((Image)value);
                    setIcon(icon);
                    setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                    setVerticalAlignment(DefaultTableCellRenderer.CENTER);
                    setBackground(new Color(13,1,35));
                    setText(null);
                }
                else if (value instanceof String && value.equals("X")) {
                    setBackground(new Color(80,111,214));
                    setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                    setVerticalAlignment(DefaultTableCellRenderer.CENTER);
                    setText("X");
                    setIcon(null);
                }
                else if (value instanceof String && ((String) value).contains("enemy")) {
                    String nameEnemy = "";
                    ImageIcon imageIcon;
                    if((value).equals("enemyOrange")){
                        imageIcon = new ImageIcon(getClass().getResource("img/" + "OrangeRight.png"));
                        setIcon(imageIcon);
                        nameEnemy = "enemyOrange";
                    }
                    else if((value).equals("enemyRed")){
                        imageIcon = new ImageIcon(getClass().getResource("img/" + "RedLeft.png"));
                        setIcon(imageIcon);
                        nameEnemy = "enemyRed";

                    }
                    else if((value).equals("enemyBlue")){
                    imageIcon = new ImageIcon(getClass().getResource("img/" + "BlueRight.png"));
                    setIcon(imageIcon);
                    nameEnemy = "enemyRed";

                    }
                    setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                    setVerticalAlignment(DefaultTableCellRenderer.CENTER);
                    setBackground(new Color(13,1,35));
                    setText(nameEnemy);
                }
                else if(value instanceof String && value.equals("")){
                    setText("");
                    setIcon(null);
                    setBackground(new Color(13,1,35));
                }
                else if(value instanceof String && value.equals("*")){
                    setText("");
                    ImageIcon photoNormalPoint = new ImageIcon(getClass().getResource("img/" +"NormalPoint.png"));
                    setIcon(photoNormalPoint);
                    setBackground(new Color(13,1,35));
                }
                else{
                    setText("");
                    setIcon(null);
                    setBackground(new Color(13,1,35));
                }
            }
        });

        mapTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent event) {
                movePacman(event);
            }
        });
// Create a JPanel with a FlowLayout set to center
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JScrollPane scrollPane = new JScrollPane(mapTable);
        scrollPane.setPreferredSize(new Dimension(CELL_SIZE * COLUMNS, CELL_SIZE * ROWS));

// Create and add the counter label to the top panel
        counterLabel = new JLabel("Очки: " + counter);
        topPanel.add(counterLabel);

// Create and add the lives label to the top panel
        livesLabel = new JLabel("Життя: " + lives);
        topPanel.add(livesLabel);

// Add the top panel to the NORTH position of the main JFrame
        add(topPanel, BorderLayout.NORTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(scrollPane);
        this.pack();
        this.setSize(800, 865);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    int lives=3;
    private Thread movementThread;
    private int lastKeyCode = -1;

    private void movePacman(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
            lastKeyCode = keyCode;

            if (movementThread != null && movementThread.isAlive()) {
                // Якщо потік вже запущено, не виконувати новий рух
                return;
            }
            movementThread = new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(500);  // Затримка 0.5 секунди
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ImageIcon photo = null; //= new ImageIcon(getClass().getResource("img/" +"PacmanRight.png"));
                    int newPacmanRow = pacmanRow;
                    int newPacmanColumn = pacmanColumn;

                    switch (lastKeyCode) {
                        case KeyEvent.VK_UP:
                            newPacmanRow = pacmanRow - 1;
                            photo = new ImageIcon(getClass().getResource("img/" +"PacmanUp.png"));

                            break;
                        case KeyEvent.VK_DOWN:
                            newPacmanRow = pacmanRow + 1;
                            photo = new ImageIcon(getClass().getResource("img/" +"PacmanDown.png"));

                            break;
                        case KeyEvent.VK_LEFT:
                            newPacmanColumn = pacmanColumn - 1;
                            photo = new ImageIcon(getClass().getResource("img/" +"PacmanLeft.png"));

                            break;
                        case KeyEvent.VK_RIGHT:
                            newPacmanColumn = pacmanColumn + 1;
                            photo = new ImageIcon(getClass().getResource("img/" +"PacmanRight.png"));
                            break;
                        default:
                            return;
                    }

                    if (newPacmanRow < 0 || newPacmanRow >= ROWS || newPacmanColumn < 0 || newPacmanColumn >= COLUMNS) {
                        return;
                    }

                    if (!obstacleMap[newPacmanRow][newPacmanColumn]) {
                        return;
                    }

                    // Increment the counter if Pacman moves onto a cell with a "." marker
                    if (tableModel.getValueAt(newPacmanRow, newPacmanColumn).equals("*")) {
                        counter++;
                        counterLabel.setText("Очки: " + counter);
                    }


                    tableModel.setValueAt("", pacmanRow, pacmanColumn);
                    pacmanRow = newPacmanRow;
                    pacmanColumn = newPacmanColumn;

                    Image image = photo.getImage();

                    tableModel.setValueAt(image, pacmanRow, pacmanColumn);

                    enemyBlueRight.movePacman(newPacmanRow, newPacmanColumn);
                    enemyOrangeRight.movePacman(newPacmanRow, newPacmanColumn);
                    enemyRedLeft.movePacman(newPacmanRow, newPacmanColumn);

                    if (enemyBlueRight.Check() || enemyOrangeRight.Check() || enemyRedLeft.Check()) {
                        lives--;
                        livesLabel.setText("Життя: " + lives);
                        if (lives == 0) {
                            JOptionPane.showMessageDialog(this, "Кінець гри!" + "\nВаш рахунок: " + counter, "Гра завершена", JOptionPane.PLAIN_MESSAGE);
                            enemyRedLeft.stop();
                            enemyOrangeRight.stop();
                            enemyBlueRight.stop();
                            new SaveResult(counter);
                        }
                    }

                    if (countPoint == counter) {
                        JOptionPane.showMessageDialog(this, "Ви виграли!" + "\nВаш рахунок: " + counter, "Гра завершена", JOptionPane.PLAIN_MESSAGE);
                        enemyRedLeft.stop();
                        enemyOrangeRight.stop();
                        enemyBlueRight.stop();
                    }
                }
            });

            movementThread.start();
        }
    }
}