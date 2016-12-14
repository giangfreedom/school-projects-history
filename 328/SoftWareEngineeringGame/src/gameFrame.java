import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JScrollBar;
import javax.swing.JSeparator;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.BoxLayout;
import javax.swing.ListSelectionModel;

import java.awt.CardLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.Panel;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JList;


public class gameFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gameFrame frame = new gameFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public gameFrame() {
		Initialize gameinstant = new Initialize();
		
		// randomly select 1 of 3 player for human
		Random rand = new Random();
		int  n = rand.nextInt(3) + 1;
		if(n == 1){
			gameinstant.human = gameinstant.player1;
			gameinstant.AI1 = gameinstant.player2;
			gameinstant.AI2 = gameinstant.player3;
		}
		else if(n == 2){
			gameinstant.human = gameinstant.player2;
			gameinstant.AI1 = gameinstant.player2;
			gameinstant.AI2 = gameinstant.player3;
		}
		else{
			gameinstant.human = gameinstant.player3;
			gameinstant.AI1 = gameinstant.player2;
			gameinstant.AI2 = gameinstant.player3;
		}
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1219, 833);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 11, 1201, 482);
		contentPane.add(scrollPane);
		
		JLabel label = new ImageLabel(gameinstant);
		//JLabel label = new JLabel("New label");
		//label.setIcon(new ImageIcon(gameFrame.class.getResource("/image/CSULBMap3.png")));
		scrollPane.setViewportView(label);
		//label.getGraphics().drawString("natsu", 500, 500);
		
		JButton moveButton = new JButton("Move");
		moveButton.setBounds(10, 519, 91, 23);
		contentPane.add(moveButton);
		
		JButton btnNewButton_1 = new JButton("Quit");
		btnNewButton_1.setBounds(10, 564, 91, 23);
		contentPane.add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(485, 520, 716, 99);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("card");
		lblNewLabel.setBounds(142, 519, 200, 257);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 623, 79, 153);
		contentPane.add(scrollPane_1);
		
		//JList list = new JList();
		String[] saint = {"seiya","dragon"};
		JList list = new JList(gameinstant.human.GetLocation().getneighborString());
		scrollPane_1.setViewportView(list);

		list.setVisibleRowCount(4);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//list.setListData(gameinstant.human.GetLocation().getneighborString());
		
		moveButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						String selected = list.getSelectedValue().toString();
						//list.setListData(selected);
						//human turn 3 move
						for(int i = 0; i < gameinstant.human.Getmovevalue(); i++){
							// loop through campus 
							for(int j = 0; j < gameinstant.campus.length; j++){
								// if the selected item from the list = the room name
								// in campus then we found the next spot
								if(selected.equalsIgnoreCase(gameinstant.campus[j].getroom_name())){
									gameinstant.human.SetLocation(gameinstant.campus[j]);
								}
							}
							// get array of neighbor
							//int[] arrofNeighbor = human.GetLocation().GetNeighbor();
							// take the choice from human to see where to go next
							// subtract move value everyloop
							gameinstant.humanNewMovevalue--;
							gameinstant.human.SetMoveValue(gameinstant.humanNewMovevalue);
						}
						//reset the human move value
						gameinstant.human.SetMoveValue(3);
						gameinstant.humanNewMovevalue = 3;
						list.setListData(gameinstant.human.GetLocation().getneighborString());
					}
				}		
		);
	}
}
