package highScores;
/**
 * Shujie Shen/ Dream Team
 */
import systemAdministrator.SystemAdministratorMain;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ScoreManagement extends JPanel implements ListSelectionListener
{
	
	static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	JLabel listTitle;
	JButton deleteButton, clearAllButton, backButton;
	DefaultListModel listModel;
	JList list;
	int [] selectedIndices;
	protected static JFrame frame;
	public ScoreManagement()
	{
		this.setLayout(null);
		
		listTitle = new JLabel("Hight Scores");
		listTitle.setFont(new Font("Arial", Font.PLAIN, 24));
		listModel = new DefaultListModel();
		listModel.addElement("player 1     100");
		listModel.addElement("player 2     85");// add the player object here.
		listModel.addElement("player 3     60");
		listModel.addElement("player 4     60");
		listModel.addElement("player 5     45");// add the player object here.
		listModel.addElement("player 6");
		listModel.addElement("player 7");
		listModel.addElement("player 8");// add the player object here.
		listModel.addElement("player 9");
		listModel.addElement("player 10");
		
		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setSelectedIndices(selectedIndices = new int[] {1,3});
		list.addListSelectionListener(this);
		list.setVisibleRowCount(10);
		JPanel listPane = new JPanel();
		
		deleteButton = new JButton("Delete");
		
		clearAllButton = new JButton("Clear All");
		
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
				SystemAdministratorMain SAmain = new SystemAdministratorMain();
				SAmain.ShowSystemAdministratorGUI();
			}
		});
		
		
		add(listTitle);
		listTitle.setBounds(150,40,250,30);
		add(listPane);
		listPane.setBounds(100, 100, 250, 320);
		
		
		add(backButton);
		backButton.setBounds(50, 470, 80, 30);
		add(deleteButton);
		deleteButton.setBounds(150, 470, 100, 30);
		deleteButton.setForeground(Color.red);
		add(clearAllButton);
		clearAllButton.setBounds(270, 470, 120, 30);
		clearAllButton.setForeground(Color.red);
		
	}
	
	public static void showScoreManagementGUI()
	{
		frame = new JFrame("MANAGE USER ACCOUNT");
		frame.setSize(440, 600); //window size
		frame.setLocation((width -440)/2,(height-600)/2); //window location
		frame.setResizable(false); // fix window size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// close window, program close
		
		frame.setLayout(null);
		JComponent SMmain = new ScoreManagement();
		frame.add(SMmain);
		
		frame.setVisible(true);//show window
		frame.setContentPane(SMmain);
	}
	
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
