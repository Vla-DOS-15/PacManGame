import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Enemy extends Component implements Runnable {
    private String enemyName;
    private int ghostRow;
    private int ghostColumn;
    private  int pacRow;
    private  int pacColumn;
    private boolean[][] obstacleMap;
    private int direction = 1;
    private int delay;
    private boolean running;
    private DefaultTableModel tableModel;
    private ImageIcon imageIcon;

    public Enemy(int ghostRow, int ghostColumn, boolean[][] obstacleMap, int delay, DefaultTableModel tableModel, ImageIcon imageIcon, int pacRow, int pacColumn, String enemyName) {
        this.ghostRow = ghostRow;
        this.ghostColumn = ghostColumn;
        this.obstacleMap = obstacleMap;
        this.delay = delay;
        this.tableModel = tableModel;
        this.imageIcon = imageIcon;
        this.pacRow = pacRow;
        this.pacColumn = pacColumn;
        this.enemyName = enemyName;
        this.running = true;
    }
    public void movePacman(int row, int col) {
        pacRow = row;
        pacColumn = col;
    }
    boolean next;

    @Override
    public void run() {
        while (running) {
            int newGhostRow = ghostRow;
            int newGhostColumn = ghostColumn;
            if ( next == true){
                tableModel.setValueAt("", ghostRow, ghostColumn);
            }
            if (tableModel.getValueAt(ghostRow, ghostColumn).equals(enemyName) && next == false) {
                tableModel.setValueAt("*", ghostRow, ghostColumn);
            }

            if (newGhostRow == pacRow && newGhostColumn == pacColumn) {
                System.out.println("Game over!");

            }
            if (direction == 1) {
                newGhostColumn = ghostColumn + 1;
            } else if (direction == 2) {
                newGhostColumn = ghostColumn - 1;
            } else if (direction == 3) {
                newGhostRow = ghostRow + 1;
            } else if (direction == 4) {
                newGhostRow = ghostRow - 1;
            }
            if (newGhostRow < 0 || newGhostRow >= 30 || newGhostColumn < 0 || newGhostColumn >= 28) {
                return;
            }

            if (!obstacleMap[newGhostRow][newGhostColumn]) {
                if (direction == 1) {
                    direction = 2;
                } else if (direction == 2) {
                    direction = 3;
                } else if (direction == 3) {
                    direction = 4;
                } else if (direction == 4) {
                    direction = 1;
                } else {
                    direction = 1;
                }
                newGhostRow = ghostRow;
                newGhostColumn = ghostColumn;
            }

            ghostRow = newGhostRow;
            ghostColumn = newGhostColumn;
            if (tableModel.getValueAt(ghostRow, ghostColumn).equals("")) {
                next = true;
            }
            if (tableModel.getValueAt(ghostRow, ghostColumn).equals("*")) {
                next = false;
            }
            tableModel.setValueAt(enemyName, ghostRow, ghostColumn);

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean Check(){
        if(ghostRow == pacRow && ghostColumn == pacColumn){
            return true;
        }
        return false;
    }
    public void stop() {
        running = false;
    }
    @Override
    public String toString(){
        return enemyName;
    }
}