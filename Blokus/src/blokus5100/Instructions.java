package blokus5100;

import blokus5100.MainMenu;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

import javax.swing.*;


	public class Instructions extends JFrame{

		
		static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		private int height = 800;
		private int width = 690;
		private String currentLine;
		private String file = "instructions.txt";
		private BufferedReader br;
		private JLabel title;
		private JTextArea windowTF;
		private JScrollPane scroll; 
		private JButton backButton;
		private JFrame fm;
		public Instructions() throws IOException 
		{
			
			this.setLocation((screenWidth -690)/2,(screenHeight-800)/2);
			fm = this;
			setTitle("Instructions");
			setSize(width, height);
			Container pane = getContentPane();
			pane.setLayout(null);
			
			// add "Instructions" title
			title = new JLabel("Instructions", SwingConstants.CENTER);
			title.setFont(new Font("Arial", Font.PLAIN, 36));
			title.setSize(650, 50);
			title.setLocation(0, 10);
			pane.add(title);
			
			// add "instruction content file"
			br = new BufferedReader(new FileReader(file));
			windowTF = new JTextArea(50, 50);
			windowTF.setEditable(false);
			windowTF.setSize(630, 750);
			windowTF.setLocation(10, 70);
			scroll = new JScrollPane(windowTF);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scroll.setLocation(10, 70);
			scroll.setSize(660, 600);
			currentLine = br.readLine();
			while(currentLine != null)
			{
				windowTF.append("   "+currentLine + "\n");
				currentLine = br.readLine();
			}		
			pane.add(scroll);
			
			// add "back" button
			backButton = new JButton("Back");
			backButton.addActionListener( new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					fm.dispose();
					MainMenu mm = new MainMenu();
					mm.createAndShowGUI();
					
				}
			});
			pane.add(backButton);
			backButton.setBounds(300, 680, 80,35);
			br.close();
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setVisible(true);
		}
		
		
	}



