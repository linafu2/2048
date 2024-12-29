import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class Main {
  static boolean UPCHECK = false;
  static boolean DOWNCHECK = false;
  static boolean LEFTCHECK = false;
  static boolean RIGHTCHECK = false;

  static class Keychecker extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent event) {
      int key = event.getKeyCode();

      if (key == KeyEvent.VK_UP) {
        UPCHECK = true;
      }
      if (key == KeyEvent.VK_DOWN) {
        DOWNCHECK = true;
      }
      if (key == KeyEvent.VK_LEFT) {
        LEFTCHECK = true;
      }
      if (key == KeyEvent.VK_RIGHT)
        RIGHTCHECK = true;
    }
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("2048");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setBackground(new Color(255, 240, 230));
    frame.setSize(500, 625);
    frame.setLayout(null);

    //title
    JPanel titlePanel = new JPanel();
    titlePanel.setBounds(0, 0, 500, 50);
    titlePanel.setBackground(new Color(0xFFBEAE));
    JLabel title = new JLabel("2048");
    title.setHorizontalAlignment(JLabel.CENTER);
    title.setVerticalAlignment(JLabel.CENTER);
    title.setForeground(Color.black);
    title.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 40));
    titlePanel.add(title);
    frame.add(titlePanel);

    JPanel actionPanel = new JPanel();
    actionPanel.setBounds(40, 90, 400, 450);
    actionPanel.setBackground(new Color(255, 240, 230));
    actionPanel.setLayout(new GridLayout(4, 4, 5, 5));
    actionPanel.setFocusable(true);
    actionPanel.requestFocusInWindow();
    actionPanel.addKeyListener(new Keychecker());

    frame.add(actionPanel);

    //making the "buttons"
    JButton[][] buttonList = new JButton[4][4];

    for (int i = 0; i < buttonList.length; i++) {
      for (int j = 0; j < buttonList[i].length; j++) {
        buttonList[i][j] = new JButton();
        buttonList[i][j].setOpaque(true);
        buttonList[i][j].setBorderPainted(false);
        actionPanel.add(buttonList[i][j]);
      }
    }

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        buttonList[i][j].setBackground(new Color(0xFFD9CC));
        buttonList[i][j].setText("0");
        buttonList[i][j].setFont(new Font("Arial", Font.BOLD, 30));
      }
    }

    //game over screen
    JPanel overPanel = getOverPanel();

    frame.setVisible(true);

    // initialize two random tiles to start with value of 2
    int startingPanel1 = (int) (Math.random() * 4);
    int startingPanel2 = (int) (Math.random() * 4);
    int startingPanel3 = (int) (Math.random() * 4);
    int startingPanel4 = (int) (Math.random() * 4);

    buttonList[startingPanel1][startingPanel2].setText("2");
    buttonList[startingPanel3][startingPanel4].setText("2");

    //color initial tiles
    for (JButton[] jButtons : buttonList) {
      for (int j = 0; j < buttonList[0].length; j++) {
        if (jButtons[j].getText().trim().equals("0")) {
          jButtons[j].setBackground(new Color(0xFFD9CC));
          jButtons[j].setOpaque(true);
          jButtons[j].setBorderPainted(false);
        }

        if (jButtons[j].getText().trim().equals("2")) {
          jButtons[j].setBackground(new Color(0xFFD0C2));
          jButtons[j].setOpaque(true);
          jButtons[j].setBorderPainted(false);
        }
      }
    }

    //game loop with timer
    Timer timer = new Timer(100, e -> {
      //each time an up/down/left/right key is pressed
      if (UPCHECK || DOWNCHECK || LEFTCHECK || RIGHTCHECK) {
        if (UPCHECK) {
          checkUp(buttonList);
          UPCHECK = false; //reset
        } else if (DOWNCHECK) {
          checkDown(buttonList);
          DOWNCHECK = false;
        } else if (LEFTCHECK) {
          checkLeft(buttonList);
          LEFTCHECK = false;
        } else { // right key
          checkRight(buttonList);
          RIGHTCHECK = false;
        }
        addRandomTile(buttonList); //add tile
        updateTileColors(buttonList); //update colors
        if (isGameOver(buttonList)) { //check game over
          showGameOver(frame, overPanel);
          ((Timer) e.getSource()).stop();
        }
      }
    });
    timer.start();
  }

  //updates tile colors
  public static void updateTileColors(JButton[][] buttonList) {
    for (JButton[] row : buttonList) {
      for (JButton button : row) {
        String text = button.getText().trim();
        Color newColor;
        switch (text) {
          case "0": newColor = new Color(0xFFD9CC); break;
          case "2": newColor = new Color(0xFFD0C2); break;
          case "4": newColor = new Color(0xFFC7B8); break;
          case "8": newColor = new Color(0xFFBEAE); break;
          case "16": newColor = new Color(0xFFB4A4); break;
          case "32": newColor = new Color(0xFFAB9A); break;
          case "64": newColor = new Color(0xFFA08F); break;
          case "128": newColor = new Color(0xF68B82); break;
          case "256": newColor = new Color(0xEF7977); break;
          case "512": newColor = new Color(0xE8696D); break;
          case "1024": newColor = new Color(0xDF5460); break;
          case "2048": newColor = new Color(0xCF2D46); break;
          default: newColor = Color.GRAY;
        }
        button.setBackground(newColor);
        button.setOpaque(true);
        button.setBorderPainted(false);
      }
    }
  }

  //adds a random 2 (90%) or 4 (10%) tile
  public static void addRandomTile(JButton[][] buttonList) {
    boolean isValidPlace = false;
    while (!isValidPlace) {
      int row = (int) (Math.random() * 4);
      int col = (int) (Math.random() * 4);
      if (buttonList[row][col].getText().trim().equals("0")) {
        int value = Math.random() < 0.9 ? 2 : 4;
        buttonList[row][col].setText(String.valueOf(value));
        isValidPlace = true;
      }
    }
  }

  //checks if game over
  public static boolean isGameOver(JButton[][] buttonList) {
    for (int r = 0; r < 4; r++) {
      for (int c = 0; c < 4; c++) {
        // not over if there are 0 tiles
        if (buttonList[r][c].getText().equals("0")) return false;
        // not over if tiles next to each other can be combined
        if (r < 3 && buttonList[r][c].getText().trim().equals(buttonList[r + 1][c].getText().trim())) return false;
        if (c < 3 && buttonList[r][c].getText().trim().equals(buttonList[r][c + 1].getText().trim())) return false;
      }
    }
    return true;
  }

  //game over screen
  public static void showGameOver(JFrame frame, JPanel overPanel) {
    frame.getContentPane().removeAll();
    frame.add(overPanel);
  }

  private static JPanel getOverPanel() {
    JPanel overPanel = new JPanel();
    overPanel.setBounds(0, 0, 500, 625);
    overPanel.setBackground(new Color(255, 240, 230));
    JLabel overLabel = new JLabel("GAME OVER");
    overLabel.setHorizontalAlignment(JLabel.CENTER);
    overLabel.setVerticalAlignment(JLabel.CENTER);
    overPanel.setLayout(null);
    overLabel.setBounds(90, 200, 300, 200);
    overLabel.setForeground(new Color(0xCF2D46));
    overLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 40));
    overPanel.add(overLabel);
    return overPanel;
  }

  //for when user presses up
  public static void checkUp(JButton[][] list) {
    boolean[][] addedAlready = new boolean[list.length][list[0].length];

    //reset addedAlready array after each move
    for (int i = 0; i < list.length; i++) {
      for (int j = 0; j < list[i].length; j++) {
        addedAlready[i][j] = false;
      }
    }

    //up move logic for each column
    for (int c = 0; c < list[0].length; c++) {
      for (int r = 1; r < list.length; r++) { //start from 1 bc nothing to merge with in row 0
        if (!list[r][c].getText().trim().equals("0")) {
          int temp = Integer.parseInt(list[r][c].getText().trim());
          int checkRow = r - 1;
          while (checkRow >= 0 && list[checkRow][c].getText().trim().equals("0")) {
            checkRow--;
          }

          //if next cell is same value and hasn't merged yet
          if (checkRow >= 0 && list[checkRow][c].getText().trim().equals(String.valueOf(temp))
                  && !addedAlready[checkRow][c]) {
            list[checkRow][c].setText(String.valueOf(temp * 2));
            list[r][c].setText("0");
            addedAlready[checkRow][c] = true;
          } else if (checkRow + 1 != r) {
            list[checkRow + 1][c].setText(String.valueOf(temp));
            list[r][c].setText("0");
          }
        }
      }
    }
  }

  //for when user presses down
  public static void checkDown(JButton[][] list) {
    boolean[][] addedAlready = new boolean[list.length][list[0].length];

    //reset addedAlready array after each move
    for (int i = 0; i < list.length; i++) {
      for (int j = 0; j < list[i].length; j++) {
        addedAlready[i][j] = false;
      }
    }

    //down move logic for each column
    for (int c = 0; c < list[0].length; c++) {
      for (int r = list.length - 2; r >= 0; r--) { //start from second last row
        if (!list[r][c].getText().trim().equals("0")) {
          int temp = Integer.parseInt(list[r][c].getText().trim());
          int checkRow = r + 1;
          while (checkRow < list.length && list[checkRow][c].getText().trim().equals("0")) {
            checkRow++;
          }

          //if next cell is same value and hasn't merged yet
          if (checkRow < list.length
                  && list[checkRow][c].getText().trim().equals(String.valueOf(temp))
                  && !addedAlready[checkRow][c]) {
            list[checkRow][c].setText(String.valueOf(temp * 2));
            list[r][c].setText("0");
            addedAlready[checkRow][c] = true;
          } else if (checkRow - 1 != r) {
            list[checkRow - 1][c].setText(String.valueOf(temp));
            list[r][c].setText("0");
          }
        }
      }
    }
  }

  //for when user presses left
  public static void checkLeft(JButton[][] list) {
    boolean[][] addedAlready = new boolean[list.length][list[0].length];

    //reset addedAlready array after each move
    for (int i = 0; i < list.length; i++) {
      for (int j = 0; j < list[i].length; j++) {
        addedAlready[i][j] = false;
      }
    }

    //left move logic for each row
    for (int r = 0; r < list.length; r++) {
      for (int c = 1; c < list[0].length; c++) { //start from second column
        if (!list[r][c].getText().trim().equals("0")) {
          int temp = Integer.parseInt(list[r][c].getText().trim());
          int checkCol = c - 1;
          while (checkCol >= 0 && list[r][checkCol].getText().trim().equals("0")) {
            checkCol--;
          }

          //if next cell is same value and hasn't merged yet
          if (checkCol >= 0 && list[r][checkCol].getText().trim().equals(String.valueOf(temp))
                  && !addedAlready[r][checkCol]) {
            list[r][checkCol].setText(String.valueOf(temp * 2));
            list[r][c].setText("0");
            addedAlready[r][checkCol] = true;
          } else if (checkCol + 1 != c) {
            list[r][checkCol + 1].setText(String.valueOf(temp));
            list[r][c].setText("0");
          }
        }
      }
    }
  }

  //for when user presses right
  public static void checkRight(JButton[][] list) {
    boolean[][] addedAlready = new boolean[list.length][list[0].length];

    //reset addedAlready array after each move
    for (int i = 0; i < list.length; i++) {
      for (int j = 0; j < list[i].length; j++) {
        addedAlready[i][j] = false;
      }
    }

    //right move logic for each row
    for (int r = 0; r < list.length; r++) {
      for (int c = list[0].length - 2; c >= 0; c--) { //start from second-last column
        if (!list[r][c].getText().trim().equals("0")) {
          int temp = Integer.parseInt(list[r][c].getText().trim());
          int checkCol = c + 1;
          while (checkCol < list[0].length && list[r][checkCol].getText().trim().equals("0")) {
            checkCol++;
          }

          //if next cell is same value and hasn't merged yet
          if (checkCol < list[0].length
                  && list[r][checkCol].getText().trim().equals(String.valueOf(temp))
                  && !addedAlready[r][checkCol]) {
            list[r][checkCol].setText(String.valueOf(temp * 2));
            list[r][c].setText("0");
            addedAlready[r][checkCol] = true;
          } else if (checkCol - 1 != c) {
            list[r][checkCol - 1].setText(String.valueOf(temp));
            list[r][c].setText("0");
          }
        }
      }
    }
  }
}