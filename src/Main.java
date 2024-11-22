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
    frame.getContentPane().setBackground(Color.WHITE);
    frame.setSize(500, 700);
    frame.setLayout(null);

    //title
    JPanel titlePanel = new JPanel();
    titlePanel.setBounds(0, 0, 500, 50);
    titlePanel.setBackground(new Color(255, 143, 160));
    JLabel title = new JLabel("2048");
    title.setHorizontalAlignment(JLabel.CENTER);
    title.setVerticalAlignment(JLabel.CENTER);
    title.setForeground(new Color(207, 45, 70));
    title.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 40));
    titlePanel.add(title);
    frame.add(titlePanel);

    JPanel actionPanel = new JPanel();
    actionPanel.setBounds(40, 90, 400, 450);
    actionPanel.setBackground(Color.WHITE);
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
        actionPanel.add(buttonList[i][j]);
      }
    }

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        buttonList[i][j].setBackground(new Color(255, 212, 223));
        buttonList[i][j].setText("0");
        buttonList[i][j].setFont(new Font("Arial", Font.BOLD, 30));
      }
    }

    //game over screen
    JPanel overPanel = getOverPanel();

    frame.setVisible(true);

    //ACTUAL PROGRAM CODE
    int startingPanel1 = (int) (Math.random() * 4);
    int startingPanel2 = (int) (Math.random() * 4);
    int startingPanel3 = (int) (Math.random() * 4);
    int startingPanel4 = (int) (Math.random() * 4);

    buttonList[startingPanel1][startingPanel2].setText("2");
    buttonList[startingPanel3][startingPanel4].setText("2");

    //changes color of tiles depending on num
    for (JButton[] jButtons : buttonList) {
      for (int j = 0; j < buttonList[0].length; j++) {
        if (jButtons[j].getText().trim().equals("0")) {
          jButtons[j].setBackground(new Color(255, 194, 216));
          jButtons[j].repaint();
        }

        if (jButtons[j].getText().trim().equals("2")) {
          jButtons[j].setBackground(new Color(255, 173, 203));
          jButtons[j].repaint();
        }
      }
    }

    //game loop with timer
    Timer timer = new Timer(100, e -> {
      if (UPCHECK || DOWNCHECK || LEFTCHECK || RIGHTCHECK) {
        if (UPCHECK) {
          checkUp(buttonList);
          UPCHECK = false;
        } else if (DOWNCHECK) {
          checkDown(buttonList);
          DOWNCHECK = false;
        } else if (LEFTCHECK) {
          checkLeft(buttonList);
          LEFTCHECK = false;
        } else if (RIGHTCHECK) {
          checkRight(buttonList);
          RIGHTCHECK = false;
        }
        initializeBoard(buttonList);
        updateTileColors(buttonList);
        if (isGameOver(buttonList)) {
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
          case "0": newColor = new Color(255, 194, 216); break;
          case "2": newColor = new Color(255, 173, 203); break;
          case "4": newColor = new Color(255, 153, 190); break;
          case "8": newColor = new Color(250, 132, 175); break;
          case "16": newColor = new Color(250, 110, 161); break;
          case "32": newColor = new Color(252, 88, 148); break;
          case "64": newColor = new Color(252, 63, 132); break;
          case "128": newColor = new Color(250, 45, 120); break;
          case "256": newColor = new Color(232, 21, 98); break;
          case "512": newColor = new Color(224, 9, 88); break;
          case "1024": newColor = new Color(201, 2, 75); break;
          case "2048": newColor = new Color(158, 2, 59); break;
          default: newColor = Color.GRAY;
        }
        button.setBackground(newColor);
      }
    }
  }

  //initializes board making sure initial two tiles don't overlap
  public static void initializeBoard(JButton[][] buttonList) {
    addRandomTile(buttonList);
    addRandomTile(buttonList);
  }

  //adds random tiles at start of game
  public static void addRandomTile(JButton[][] buttonList) {
    boolean isValidPlace = false;
    while (!isValidPlace) {
      int row = (int) (Math.random() * 4);
      int col = (int) (Math.random() * 4);
      if (buttonList[row][col].getText().equals("0")) {
        buttonList[row][col].setText("2");
        isValidPlace = true;
      }
    }
  }

  //checks if game over
  public static boolean isGameOver(JButton[][] buttonList) {
    for (int r = 0; r < 4; r++) {
      for (int c = 0; c < 4; c++) {
        if (buttonList[r][c].getText().equals("0")) return false;
        if (r > 0 && buttonList[r][c].getText().equals(buttonList[r - 1][c].getText())) return false;
        if (c > 0 && buttonList[r][c].getText().equals(buttonList[r][c - 1].getText())) return false;
      }
    }
    return true;
  }

  //game over screen
  public static void showGameOver(JFrame frame, JPanel overPanel) {
    frame.getContentPane().removeAll();
    frame.add(overPanel);
    frame.revalidate();
    frame.repaint();
  }

  private static JPanel getOverPanel() {
    JPanel overPanel = new JPanel();
    overPanel.setBounds(0, 0, 500, 700);
    overPanel.setBackground(Color.white);
    JLabel overLabel = new JLabel("GAME OVER");
    overLabel.setHorizontalAlignment(JLabel.CENTER);
    overLabel.setVerticalAlignment(JLabel.CENTER);
    overPanel.setLayout(null);
    overLabel.setBounds(90, 200, 300, 200);
    overLabel.setForeground(new Color(207, 45, 70));
    overLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 50));
    overPanel.add(overLabel);
    return overPanel;
  }

  //for when user presses up
  public static void checkUp(JButton[][] list) {
    boolean[][] addedAlready = new boolean[list.length][list[0].length];

    for (int i = 0; i < 4; i++) {
      for (int c = 0; c < list[0].length; c++) {
        for (int r = list.length - 1; r >= 0; r--) {
          if (r != 0) {
            int temp;
            int otherTemp;
            if (list[r][c].getText().isEmpty()) {
              temp = 0;
            } else {
              temp = Integer.parseInt(list[r][c].getText());
            }

            if (list[r - 1][c].getText().isEmpty()) {
              otherTemp = 0;
            } else {
              otherTemp = Integer.parseInt(list[r - 1][c].getText());
            }

            if (otherTemp == 0) {
              list[r - 1][c].setText(String.valueOf(temp));
              list[r][c].setText("0");
            }
            if (otherTemp == temp) {
              if (!addedAlready[r][c]) {
                list[r - 1][c].setText(String.valueOf(temp * 2));
                list[r][c].setText("0");
                addedAlready[r - 1][c] = true;
              }
            }
          }
        }
      }
    }
  }

  //for when user presses down
  public static void checkDown(JButton[][] list) {
    boolean[][] addedAlready = new boolean[list.length][list[0].length];

    for (int i = 0; i < 4; i++) {
      for (int c = 0; c < list[0].length; c++) {
        for (int r = 0; r < list.length; r++) {
          if (r != list.length - 1) {
            int temp;
            int otherTemp;
            if (list[r][c].getText().isEmpty()) {
              temp = 0;
            } else {
              temp = Integer.parseInt(list[r][c].getText());
            }
            if (list[r + 1][c].getText().isEmpty()) {
              otherTemp = 0;
            } else {
              otherTemp = Integer.parseInt(list[r + 1][c].getText());
            }

            if (otherTemp == 0) {
              list[r + 1][c].setText(String.valueOf(temp));
              list[r][c].setText("0");
            }
            if (otherTemp == temp) {
              if (!addedAlready[r][c]) {
                list[r + 1][c].setText(String.valueOf(temp * 2));
                list[r][c].setText("0");
                addedAlready[r + 1][c] = true;
              }
            }
          }
        }
      }
    }
  }

  //for when user presses left
  public static void checkLeft(JButton[][] list) {
    boolean[][] addedAlready = new boolean[list.length][list[0].length];

    for (int i = 0; i < 4; i++) {
      for (int r = 0; r < list.length; r++) {
        for (int c = list[0].length - 1; c >= 0; c--) {
          if (c != 0) {
            int temp;
            int otherTemp;
            if (list[r][c].getText().isEmpty()) {
              temp = 0;
            } else {
              temp = Integer.parseInt(list[r][c].getText());
            }
            if (list[r][c - 1].getText().isEmpty()) {
              otherTemp = 0;
            } else {
              otherTemp = Integer.parseInt(list[r][c - 1].getText());
            }

            if (otherTemp == 0) {
              list[r][c - 1].setText(String.valueOf(temp));
              list[r][c].setText("0");
            }
            if (otherTemp == temp) {
              if (!addedAlready[r][c]) {
                list[r][c - 1].setText(String.valueOf(temp * 2));
                list[r][c].setText("0");
                addedAlready[r][c - 1] = true;
              }
            }
          }
        }
      }
    }
  }

  //for when user presses right
  public static void checkRight(JButton[][] list) {
    boolean[][] addedAlready = new boolean[list.length][list[0].length];

    for (int i = 0; i < 4; i++) {
      for (int r = 0; r < list.length; r++) {
        for (int c = 0; c < list[0].length; c++) {
          if (c != list.length - 1) {
            int temp;
            int otherTemp;
            if (list[r][c].getText().isEmpty()) {
              temp = 0;
            } else {
              temp = Integer.parseInt(list[r][c].getText());
            }
            if (list[r][c + 1].getText().isEmpty()) {
              otherTemp = 0;
            } else {
              otherTemp = Integer.parseInt(list[r][c + 1].getText());
            }

            if (otherTemp == 0) {
              list[r][c + 1].setText(String.valueOf(temp));
              list[r][c].setText("0");
            }
            if (otherTemp == temp) {
              if (!addedAlready[r][c]) {
                list[r][c + 1].setText(String.valueOf(temp * 2));
                list[r][c].setText("0");
                addedAlready[r][c + 1] = true;
              }
            }
          }
        }
      }
    }
  }
}