import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Scanner;

import javax.swing.*;

public class Main
{
	static boolean UPCHECK = false;
	static boolean DOWNCHECK = false;
	static boolean LEFTCHECK = false;
	static boolean RIGHTCHECK = false;
	
	//got help from https://stackoverflow.com/questions/616924/how-to-check-if-the-key-pressed-was-an-arrow-key-in-java-keylistener
		static class Keychecker extends KeyAdapter
		{

		    @Override
		    public void keyPressed(KeyEvent event)
		    {
		    	int key = event.getKeyCode();
		    	
		    	if(key == KeyEvent.VK_UP)
		    	{
		    		//System.out.println("UP");
			    	UPCHECK = true;
		    	}
		    	
		    	if(key == KeyEvent.VK_DOWN)
		    	{
			    	//System.out.println("DOWN");
		    		DOWNCHECK = true;
		    	}
		    	if(key == KeyEvent.VK_LEFT)
		    	{
		    		LEFTCHECK = true;
			    	//System.out.println("LEFT");
		    	}
		    	if(key == KeyEvent.VK_RIGHT)
			    	//System.out.println("RIGHT"); 	
		    		RIGHTCHECK = true;
		    }
		}
		
		
		
		
		
		
	public static void main(String[] args) throws Exception 
	{
		JFrame frame = new JFrame("2048");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setSize(500, 700);
		frame.setLayout(null); //need this for setBounds to work?
		//https://www.guru99.com/java-swing-gui.html#:~:text=like%20the%20Frame.-,What%20is%20GUI%20in%20Java%3F,can%20interact%20with%20an%20application.
		
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
		JButton button1 = new JButton();
		JButton button2 = new JButton();
		JButton button3 = new JButton();
		JButton button4 = new JButton();
		JButton button5 = new JButton();
		JButton button6 = new JButton();
		JButton button7 = new JButton();
		JButton button8 = new JButton();
		JButton button9 = new JButton();
		JButton button10 = new JButton();
		JButton button11 = new JButton();
		JButton button12 = new JButton();
		JButton button13 = new JButton();
		JButton button14 = new JButton();
		JButton button15 = new JButton();
		JButton button16 = new JButton();
		
		JButton[][] buttonList = {{button1, button2, button3, button4}, 
                {button5, button6, button7, button8}, 
                {button9, button10, button11, button12}, 
                {button13, button14, button15, button16}};

		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++) 
			{
				buttonList[i][j].setBackground(new Color(255, 212, 223));
				buttonList[i][j].setText("0");
				buttonList[i][j].setFont(new Font("Arial", Font.BOLD, 30));
			}
		}
		
		actionPanel.add(button1);
		actionPanel.add(button2);
		actionPanel.add(button3);
		actionPanel.add(button4);
		actionPanel.add(button5);
		actionPanel.add(button6);
		actionPanel.add(button7);
		actionPanel.add(button8);
		actionPanel.add(button9);
		actionPanel.add(button10);
		actionPanel.add(button11);
		actionPanel.add(button12);
		actionPanel.add(button13);
		actionPanel.add(button14);
		actionPanel.add(button15);
		actionPanel.add(button16);
		
		
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
		int startingPanel1 = (int)(Math.random() * 4);
		int startingPanel2 = (int)(Math.random() * 4);
		int startingPanel3 = (int)(Math.random() * 4);
		int startingPanel4 = (int)(Math.random() * 4);
		
		buttonList[startingPanel1][startingPanel2].setText("2");
		buttonList[startingPanel3][startingPanel4].setText("2");
		
		//changes color of tiles depending on num
		for(int i = 0; i < buttonList.length; i++)
		{
			for(int j = 0; j < buttonList[0].length; j++)
			{
				if(buttonList[i][j].getText().equals("0"))
					buttonList[i][j].setBackground(new Color(255, 194, 216));
				

				if(buttonList[i][j].getText().equals("2"))
					buttonList[i][j].setBackground(new Color(255, 173, 203));
			}
		}
			
		boolean isPlaying = true;
		
		while(isPlaying)
		{
			System.out.println(UPCHECK);
			if(UPCHECK == true || DOWNCHECK == true || LEFTCHECK == true || RIGHTCHECK == true)
			{
				
				System.out.println(UPCHECK);
				//button.getTExt();
				
				if(UPCHECK == true)
				{
					System.out.println("hi");
					checkUp(buttonList);
					UPCHECK = false;
				}
				if(DOWNCHECK == true)
				{
					//checkDown(buttonList);
					checkDown(buttonList);
					DOWNCHECK = false;
				}
				if(LEFTCHECK == true)
				{
					checkLeft(buttonList);
					LEFTCHECK = false;
				}
				if(RIGHTCHECK == true)
				{
					checkRight(buttonList);
					RIGHTCHECK = false;
				}
				
				
				//adds random 2 to empty spot
				boolean isValidPlace = false;
				while(!isValidPlace)
				{
					int counter = 0;
					for(int i = 0; i < buttonList.length; i++)
					{
						for(int j = 0; j < buttonList[0].length; j++)
						{
							if(buttonList[i][j].getText().equals("0"))
								counter++;
						}
					}
					if(counter == 0)
					{
						System.out.println("GAME OVER");
						isValidPlace = false;
						isPlaying = false;
					}
					
					int num1 = (int)(Math.random()*4);
					int num2 = (int)(Math.random()*4);
					if(buttonList[num1][num2].getText().equals("0"))
					{
						buttonList[num1][num2].setText("2");
						isValidPlace = true;
					}
				}
				
				for(int i = 0; i < buttonList.length; i++)
				{
					for(int j = 0; j < buttonList[0].length; j++)
					{
						if(buttonList[i][j].getText().equals("0"))
							buttonList[i][j].setBackground(new Color(255, 194, 216));
						

						if(buttonList[i][j].getText().equals("2"))
							buttonList[i][j].setBackground(new Color(255, 173, 203));

						if(buttonList[i][j].getText().equals("4"))
							buttonList[i][j].setBackground(new Color(255, 153, 190));

						if(buttonList[i][j].getText().equals("8"))
							buttonList[i][j].setBackground(new Color(250, 132, 175));

						if(buttonList[i][j].getText().equals("16"))
							buttonList[i][j].setBackground(new Color(250, 110, 161));

						if(buttonList[i][j].getText().equals("32"))
							buttonList[i][j].setBackground(new Color(252, 88, 148));

						if(buttonList[i][j].getText().equals("64"))
							buttonList[i][j].setBackground(new Color(252, 63, 132));
						
						if(buttonList[i][j].getText().equals("128"))
							buttonList[i][j].setBackground(new Color(250, 45, 120));

						if(buttonList[i][j].getText().equals("256"))
							buttonList[i][j].setBackground(new Color(232, 21, 98));
						
						if(buttonList[i][j].getText().equals("512"))
							buttonList[i][j].setBackground(new Color(224, 9, 88));

						if(buttonList[i][j].getText().equals("1024"))
							buttonList[i][j].setBackground(new Color(201, 2, 75));
						

						if(buttonList[i][j].getText().equals("2048"))
							buttonList[i][j].setBackground(new Color(158, 2, 59));
						

					}
				}
			}
			
			
			
			
			
			
			
		}
		
		
		
		
	}
	
	//function for when user presses up
	public static void checkUp(JButton[][] list)
	{
		boolean[][] addedAlready = new boolean[list.length][list[0].length];
		
		for(int i = 0; i < 4; i++)
		{
			for(int c = 0; c < list[0].length; c++)
			{
				for(int r = list.length-1; r >= 0; r--)
				{
					if(r != 0)
					{
						int temp;
						int otherTemp;
						if(list[r][c].getText().equals(""))
						{
							temp = 0;
						}
						else
						{
							temp = Integer.parseInt(list[r][c].getText());
						}
						
						if(list[r-1][c].getText().equals(""))
						{
							otherTemp = 0;
						}
						else
						{
							otherTemp = Integer.parseInt(list[r-1][c].getText());
						}
						
						if(otherTemp == 0)
						{
							list[r-1][c].setText(String.valueOf(temp));
							list[r][c].setText("0");
						}
						if(otherTemp == temp)
						{
							if(addedAlready[r][c] == false)
							{
								list[r-1][c].setText(String.valueOf(temp*2));
								list[r][c].setText("0");
								addedAlready[r-1][c] = true;
							}
						}
					}
				}
			}
		}
	}
	
	
	public static void checkDown(JButton[][] list)
	{
		boolean[][] addedAlready = new boolean[list.length][list[0].length];
		
		for(int i = 0; i < 4; i++)
		{
			for (int c = 0; c < list[0].length; c++)
			{
				for(int r = 0; r < list.length; r++)
				{
					if(r != list.length - 1)
					{
						int temp;
						int otherTemp;
						if(list[r][c].getText().equals(""))
						{
							temp = 0;
						}
						else
						{
							temp = Integer.parseInt(list[r][c].getText());
						}
						if(list[r+1][c].getText().equals(""))
						{
							otherTemp = 0;
						}
						else
						{
							otherTemp = Integer.parseInt(list[r+1][c].getText());
						}
						
						if(otherTemp == 0)
						{
							list[r+1][c].setText(String.valueOf(temp));
							list[r][c].setText("0");
						}
						if(otherTemp == temp)
						{
							if(addedAlready[r][c] == false)
							{
								list[r+1][c].setText(String.valueOf(temp*2));
								list[r][c].setText("0");
								addedAlready[r+1][c] = true;
							}
						}
					}
				}
			}
		}	
	}
	
	
	
	public static void checkLeft(JButton[][] list)
	{
		boolean[][] addedAlready = new boolean[list.length][list[0].length];
		
		for(int i = 0; i < 4; i++)
		{
			for(int r = 0; r < list.length; r++)
			{
				for(int c = list[0].length - 1; c >= 0; c--)
				{
					if(c != 0)
					{
						int temp;
						int otherTemp;
						if(list[r][c].getText().equals(""))
						{
							temp = 0;
						}
						else
						{
							temp = Integer.parseInt(list[r][c].getText());
						}
						if(list[r][c-1].getText().equals(""))
						{
							otherTemp = 0;
						}
						else
						{
							otherTemp = Integer.parseInt(list[r][c-1].getText());
						}
						
						if(otherTemp == 0)
						{
							list[r][c-1].setText(String.valueOf(temp));
							list[r][c].setText("0");
						}
						if(otherTemp == temp)
						{
							if(addedAlready[r][c] == false)
							{
								list[r][c-1].setText(String.valueOf(temp*2));
								list[r][c].setText("0");
								addedAlready[r][c-1] = true;
							}
						}
					}
				}
			}
		}
	}
	
	
	
	public static void checkRight(JButton[][] list)
	{
		boolean[][] addedAlready = new boolean[list.length][list[0].length];
		
		for(int i = 0; i < 4; i++)
		{
			for(int r = 0; r < list.length; r++)
			{
				for(int c = 0; c < list[0].length; c++)
				{
					if(c != list.length - 1)
					{
						int temp;
						int otherTemp;
						if(list[r][c].getText().equals(""))
						{
							temp = 0;
						}
						else
						{
							temp = Integer.parseInt(list[r][c].getText());
						}
						if(list[r][c+1].getText().equals(""))
						{
							otherTemp = 0;
						}
						else
						{
							otherTemp = Integer.parseInt(list[r][c+1].getText());
						}
						
						if(otherTemp == 0)
						{
							list[r][c+1].setText(String.valueOf(temp));
							list[r][c].setText("0");
						}
						if(otherTemp == temp)
						{
							if(addedAlready[r][c] == false)
							{
								list[r][c+1].setText(String.valueOf(temp*2));
								list[r][c].setText("0");
								addedAlready[r][c+1] = true;
							}
							
						}
					}
				}
			}
		}
	}	    

}

