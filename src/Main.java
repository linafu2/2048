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
        if (jButtons[j].getText().equals("0"))
          jButtons[j].setBackground(new Color(255, 194, 216));


        if (jButtons[j].getText().equals("2"))
          jButtons[j].setBackground(new Color(255, 173, 203));
      }
    }

    boolean isPlaying = true;

    while (isPlaying) {
      System.out.println(UPCHECK);
      if (UPCHECK || DOWNCHECK || LEFTCHECK || RIGHTCHECK) {
        System.out.println(UPCHECK);
        if (UPCHECK) {
          System.out.println("hi");
          checkUp(buttonList);
          UPCHECK = false;
        }
        if (DOWNCHECK) {
          checkDown(buttonList);
          DOWNCHECK = false;
        }
        if (LEFTCHECK) {
          checkLeft(buttonList);
          LEFTCHECK = false;
        }
        if (RIGHTCHECK) {
          checkRight(buttonList);
          RIGHTCHECK = false;
        }

        //adds random 2 to empty spot
        boolean isValidPlace = false;
        while (!isValidPlace) {
          int counter = 0;
          for (JButton[] jButtons : buttonList) {
            for (int j = 0; j < buttonList[0].length; j++) {
              if (jButtons[j].getText().equals("0"))
                counter++;
            }
          }
          if (counter == 0) {
            System.out.println("GAME OVER");
            isPlaying = false;
          }

          int num1 = (int) (Math.random() * 4);
          int num2 = (int) (Math.random() * 4);
          if (buttonList[num1][num2].getText().equals("0")) {
            buttonList[num1][num2].setText("2");
            isValidPlace = true;
          }
        }

        for (JButton[] jButtons : buttonList) {
          for (int j = 0; j < buttonList[0].length; j++) {
            if (jButtons[j].getText().equals("0"))
              jButtons[j].setBackground(new Color(255, 194, 216));

            if (jButtons[j].getText().equals("2"))
              jButtons[j].setBackground(new Color(255, 173, 203));

            if (jButtons[j].getText().equals("4"))
              jButtons[j].setBackground(new Color(255, 153, 190));

            if (jButtons[j].getText().equals("8"))
              jButtons[j].setBackground(new Color(250, 132, 175));

            if (jButtons[j].getText().equals("16"))
              jButtons[j].setBackground(new Color(250, 110, 161));

            if (jButtons[j].getText().equals("32"))
              jButtons[j].setBackground(new Color(252, 88, 148));

            if (jButtons[j].getText().equals("64"))
              jButtons[j].setBackground(new Color(252, 63, 132));

            if (jButtons[j].getText().equals("128"))
              jButtons[j].setBackground(new Color(250, 45, 120));

            if (jButtons[j].getText().equals("256"))
              jButtons[j].setBackground(new Color(232, 21, 98));

            if (jButtons[j].getText().equals("512"))
              jButtons[j].setBackground(new Color(224, 9, 88));

            if (jButtons[j].getText().equals("1024"))
              jButtons[j].setBackground(new Color(201, 2, 75));

            if (jButtons[j].getText().equals("2048"))
              jButtons[j].setBackground(new Color(158, 2, 59));
          }
        }
      }
    }
  }

  //function for when user presses up
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

  //function for when user presses down
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

  //function for when user presses left
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

  //function for when user presses right
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