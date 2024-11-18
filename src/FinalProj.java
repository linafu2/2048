import javax.swing.*;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.imageio.ImageIO;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.border.BevelBorder;

public class FinalProj {
	public static void main(String[] args) throws MalformedURLException, IOException {
		JFrame window = new JFrame("RITARIA");
		Color skyBlue = new Color(208, 246, 247);
		BufferedImage image;
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//window.getContentPane().setBackground(skyBlue);
    
		JLabel message = new JLabel("Hello Graphical World!", SwingConstants.CENTER);
		message.setFont(new Font("TimesRoman", Font.BOLD + Font.ITALIC, 30));
		message.setForeground(Color.RED);
			
		image = ImageIO.read(new URL(
                "https://t4.ftcdn.net/jpg/00/41/32/79/360_F_41327998_X12XX5Oir5RZ48qE6SVDGpOmX5HtwM9J.jpg"));
		window.setIconImage(image);
		//window.add(new JLabel(new ImageIcon(image)));
		
		window.add(message);
		window.setSize(500, 300);
		window.setVisible(true);	
		

		
		//for making border
		/*
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(200, 200);
		
		// create the status bar panel and shove it down the bottom of the frame
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		frame.add(statusPanel, BorderLayout.SOUTH);
		statusPanel.setPreferredSize(new Dimension(frame.getWidth(), 16));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		JLabel statusLabel = new JLabel("status");
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		statusPanel.add(statusLabel);
		
		frame.setVisible(true);
		*/
		
		
		// Create a panel to hold the "Start" button:
        /*
		JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton start = new JButton("Start");
        start.setToolTipText("Click to use library");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("I AM PRESSED");
            }
        });
        
        submitPanel.add(start);
        */
	}
}
