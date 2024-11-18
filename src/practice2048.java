import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class practice2048 
{
    public static void main(String[] args)
    {
            
        int[][] list = new int[4][4];
        int rand1 = (int)(Math.random() * 4);
        int rand2 = (int)(Math.random() * 4);
        int rand3 = (int)(Math.random() * 4);
        int rand4 = (int)(Math.random() * 4);
        
        list[rand1][rand2] = 2;
        list[rand3][rand4] = 2;
        
        boolean isPlaying = true;
        while(isPlaying)
        {
            for(int i = 0; i < list.length; i++)
            {
                for(int j = 0; j < list[0].length; j++)
                {
                    System.out.print(list[i][j]);
                }
                System.out.println();
            }
                
            Scanner sc = new Scanner(System.in);
            int answer;
            
            System.out.print("Enter: ");
            answer = sc.nextInt();
            
            if(answer == 0)
                checkUp(list);
            if(answer == 1)
                checkDown(list);
            if(answer == 2)
                checkLeft(list);
            if(answer == 3)
                checkRight(list);
            
            System.out.println();
            for(int i = 0; i < list.length; i++)
            {
                for(int j = 0; j < list[0].length; j++)
                {
                    System.out.print(list[i][j]);
                }
                System.out.println();
            }
            
            
            //adds random 2
            boolean isValidPlace = false;
            while(isValidPlace == false)
            {
                int counter = 0;
                for(int i = 0; i < list.length; i++)
                {
                    for(int j = 0; j < list[0].length; j++)
                    {
                        if(list[i][j] == 0)
                            counter++;    
                    }
                }
                if(counter == 0)
                {
                    System.out.println("Game Over");
                    isValidPlace = true;
                }
                int num = (int)(Math.random()*4);
                int num2 = (int)(Math.random()*4);
                if(list[num][num2] == 0)
                {
                    list[num][num2] = 2;
                    isValidPlace = true;
                }
                
                String hi;
                System.out.print("keep playing? ");
                hi = sc.next();
                if(hi.equals("Y"))
                    isPlaying = true;
                if(hi.equals("N"))
                    isPlaying = false;
            }
            
            
            
            
            for(int i = 0; i < list.length; i++)
            {
                for(int j = 0; j < list[0].length; j++)
                {
                    System.out.print(list[i][j]);
                }
                System.out.println();
            }
            
            System.out.println();
            
            
        }
        
        
        
    }
    public static void checkUp(int[][] bruh)
    {
        boolean[][] addedAlready = new boolean[bruh.length][bruh[0].length];
        
        for(int i = 0; i < 4; i++)
        {
            for(int c = 0; c < bruh[0].length; c++)
            {
                for(int r = bruh.length-1; r >= 0; r--)
                {
                    if(r != 0)
                    {
                        int temp = bruh[r][c];
                        if(bruh[r-1][c] == 0)
                        {
                            bruh[r-1][c] = temp;
                            bruh[r][c] = 0;
                        }
                        if(bruh[r-1][c] == bruh[r][c])
                        {
                            if(addedAlready[r][c] == false)
                            {
                                bruh[r-1][c] = temp*2;
                                bruh[r][c] = 0;
                                addedAlready[r-1][c] = true;
                            }
                        }
                    }
                }
            }
        }
    }
    public static void checkDown(int[][] bruh)
    {
        boolean[][] addedAlready = new boolean[bruh.length][bruh[0].length];
        
        for(int i = 0; i < 4; i++)
        {
            for(int c = 0; c < bruh[0].length; c++)
            {
                for(int r = 0; r < bruh.length; r++)
                {
                    if(r != bruh.length - 1)
                    {
                        int temp = bruh[r][c];
                        if(bruh[r+1][c] == 0)
                        {
                            bruh[r+1][c] = temp;
                            bruh[r][c] = 0;
                        }
                        if(bruh[r+1][c] == bruh[r][c])
                        {
                            if(addedAlready[r][c] == false)
                            {
                                bruh[r+1][c] = temp*2;
                                bruh[r][c] = 0;
                                addedAlready[r+1][c] = true;
                            }
                        }
                    }
                }
            }
        }
    }
    
    
    
    

    public static void checkLeft(int[][] bruh)
    {
        boolean[][] addedAlready = new boolean[bruh.length][bruh[0].length];
        
        for(int i = 0; i < 4; i++)
        {
            for(int r = 0; r < bruh.length; r++)
            {
                for(int c = bruh[0].length - 1; c >= 0; c--)
                {
                    if(c != 0)
                    {
                        int temp = bruh[r][c];
                        if(bruh[r][c-1] == 0)
                        {
                            bruh[r][c-1] = temp;
                            bruh[r][c] = 0;
                        }
                        if(bruh[r][c-1] == bruh[r][c])
                        {
                            if(addedAlready[r][c] == false)
                            {
                                bruh[r][c-1] = temp*2;
                                bruh[r][c] = 0;
                                addedAlready[r][c-1] = true;
                            }
                        }
                    }
                }
            }
        }
        
    }
    
    
    
    
    public static void checkRight(int[][] bruh)
    {
        boolean[][] addedAlready = new boolean[bruh.length][bruh[0].length];
                                                            
        for(int i = 0; i < 4; i++)
        {
            for(int r = 0; r < bruh.length; r++)
            {
                for(int c = 0; c < bruh[0].length; c++)
                {
                    if(c != bruh[0].length - 1)
                    {
                        int temp = bruh[r][c];
                        if(bruh[r][c+1] == 0)
                        {
                            bruh[r][c+1] = temp;
                            bruh[r][c] = 0;
                        }
                        if(bruh[r][c+1] == bruh[r][c])
                        {
                            if(addedAlready[r][c] == false)
                            {
                                bruh[r][c+1] = temp*2;
                                bruh[r][c] = 0;
                                addedAlready[r][c+1] = true;
                            }
                        }
                            
                    }
                }
            }
        }
    }

}