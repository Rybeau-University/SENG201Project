package GUI;

import javax.swing.JFrame;
import javax.swing.JList;
import Main.Crew;
import Main.CrewMember;
import Main.GameEnvironment;
import javax.swing.AbstractListModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * This is the main GUI window for running the game itself.<br>
 * All aspects of the game are displayed here and player interaction happens with this GUI.
 * @author Ryan Beaumont
 *
 */
public class GameWindow {
	/**
	 * This is the GameEnvironment class that is controlling the game.
	 */
	private GameEnvironment environment;
	/**
	 * This is the Crew for the game.
	 */
	private Crew crew;
	/**
	 * The JFrame containing all the GUI elements
	 */
	private JFrame gameScreen;
	/**
	 * This is the JPanel that contains the CrewMember related elements.
	 */
	private JPanel crewPanel;
	/**
	 * This is the JTextPane within crewPanel that displays the status of the CrewMember selected in listOfCrew.
	 */
	private JTextPane statusPane;
	/**
	 * THis is the JList that the player uses to select a CrewMember to use for an action.
	 */
	private JList<CrewMember> listOfCrew;
	/**
	 * This is the topInfoPanel that contains information about the day, ship shield level, crew money, transporter parts found
	 * and transporter parts to find.
	 */
	private JPanel topInfoPanel;
	/**
 	* This is the constructor for GameWindow
 	* It assigns the variables environment and crew with incomingEnvironment and incomingCrew.
 	* @param incomingEnvironment This is the GameEnvironment class currently controlling the game.
 	* @param incomingCrew This is the Crew for the current game instance.
 	*/
	public GameWindow(GameEnvironment incomingEnvironment, Crew incomingCrew) {
		crew = incomingCrew;
		environment = incomingEnvironment;
		initialize();
		gameScreen.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.<br>
	 * Builds all the GUI elements.<br>
	 * Called when an instance of GameWindow is created.
	 */
	private void initialize() {
		gameScreen = new JFrame();
		gameScreen.setTitle("Space Explorer");
		gameScreen.setBounds(100, 100, 700, 400);
		gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameScreen.setLocationRelativeTo(null);
		gameScreen.getContentPane().setLayout(null);
		
		crewPanel = new JPanel();
		crewPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		crewPanel.setBounds(12, 50, 163, 299);
		gameScreen.getContentPane().add(crewPanel);
		crewPanel.setLayout(null);
		buildCrewPanel();
		
		topInfoPanel = new JPanel();
		topInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		topInfoPanel.setBounds(12, 12, 660, 34);
		gameScreen.getContentPane().add(topInfoPanel);
		topInfoPanel.setLayout(null);
		buildTopInfoPanel();
		
		JPanel bottomActivitiesPanel = new JPanel();
		bottomActivitiesPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		bottomActivitiesPanel.setBounds(187, 315, 485, 34);
		gameScreen.getContentPane().add(bottomActivitiesPanel);
		bottomActivitiesPanel.setLayout(null);
		
		JButton btnNextDay = new JButton("Next Day");
		btnNextDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextDay();
			}
		});
		btnNextDay.setBounds(304, 3, 115, 26);
		bottomActivitiesPanel.add(btnNextDay);
		
		JButton btnVisitOutpost = new JButton("Visit Outpost");
		btnVisitOutpost.setBounds(177, 3, 115, 26);
		bottomActivitiesPanel.add(btnVisitOutpost);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(50, 3, 115, 26);
		bottomActivitiesPanel.add(btnNewButton);
		
		JPanel actionsPanel = new JPanel();
		actionsPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		actionsPanel.setBounds(187, 50, 485, 258);
		gameScreen.getContentPane().add(actionsPanel);
		actionsPanel.setLayout(null);
		
		JButton btnSearchPlanet = new JButton("Search Planet");
		btnSearchPlanet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrewMember member = listOfCrew.getSelectedValue();
				environment.searchPlanet(member);;
				refreshTopInfoPane();
				updateCrewMember();
			}
		});
		btnSearchPlanet.setBounds(347, 172, 115, 26);
		actionsPanel.add(btnSearchPlanet);
		
		JButton btnEatFood = new JButton("Eat Food");
		btnEatFood.setBounds(347, 20, 115, 26);
		actionsPanel.add(btnEatFood);
		
		JButton btnSleep = new JButton("Sleep");
		btnSleep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrewMember member = listOfCrew.getSelectedValue();
				environment.sleep(member);
				updateCrewMember();
			}
		});
		btnSleep.setBounds(347, 96, 115, 26);
		actionsPanel.add(btnSleep);
		
		JButton btnRepairShip = new JButton("Repair Ship");
		btnRepairShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrewMember member = listOfCrew.getSelectedValue();
				environment.repairShip(member);
				refreshTopInfoPane();
				updateCrewMember();
			}
		});
		btnRepairShip.setBounds(347, 134, 115, 26);
		actionsPanel.add(btnRepairShip);
		
		JButton btnPilotShip = new JButton("Pilot Ship");
		btnPilotShip.setBounds(347, 209, 115, 26);
		actionsPanel.add(btnPilotShip);
		
		JButton btnHeal = new JButton("Heal");
		btnHeal.setBounds(347, 58, 115, 26);
		actionsPanel.add(btnHeal);
	}
	/**
	 * This method creates the GUI elements with the crewPane JPanel.<br>
	 * This seperate method is used as the elements are recreated when crewPane needs to be refreshed.
	 */
	private void buildCrewPanel() {
		listOfCrew = new JList();
		listOfCrew.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				updateCrewMember();
			}
		});
		listOfCrew.setBackground(UIManager.getColor("Button.background"));
		listOfCrew.setBorder(new LineBorder(new Color(0, 0, 0)));
		listOfCrew.setBounds(12, 40, 139, 110);
		crewPanel.add(listOfCrew);
		listOfCrew.setModel(new AbstractListModel<CrewMember>() {
			CrewMember[] values = crew.getCrewList().toArray(new CrewMember[crew.getCrewList().size()]);
			public int getSize() {
				return values.length;
			}
			public CrewMember getElementAt(int index) {
				return values[index];
			}
		});
		JLabel lblCrewName = new JLabel(crew.getCrewName());
		lblCrewName.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCrewName.setBounds(12, 12, 139, 16);
		crewPanel.add(lblCrewName);

		statusPane = new JTextPane();
		statusPane.setEditable(false);
		statusPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		statusPane.setBackground(UIManager.getColor("Button.background"));
		statusPane.setBounds(12, 195, 139, 92);
		crewPanel.add(statusPane);
		listOfCrew.setSelectedIndex(0);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Dialog", Font.BOLD, 14));
		lblStatus.setBounds(12, 167, 55, 16);
		crewPanel.add(lblStatus);
	}
	/**
	 * This method creates the GUI elements with the topInfoPane JPanel.<br>
	 * This separate method is used as the elements are recreated when topInfoPane needs to be refreshed.
	 */
	private void buildTopInfoPanel() {
		JLabel lblDay = new JLabel("Day: " + environment.getCurrentDay());
		lblDay.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDay.setBounds(12, 12, 78, 16);
		topInfoPanel.add(lblDay);
		
		JLabel lblShipShieldLevel = new JLabel("Ship Shield Level: " + crew.getCrewShip().getShieldLevel() + "%");
		lblShipShieldLevel.setFont(new Font("Dialog", Font.BOLD, 15));
		lblShipShieldLevel.setBounds(122, 12, 176, 16);
		topInfoPanel.add(lblShipShieldLevel);
		
		JLabel lblTransporterParts = new JLabel("Transporter Parts: " + crew.getPartsFound() + "/" + environment.getPartsToCollect());
		lblTransporterParts.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTransporterParts.setBounds(495, 12, 153, 16);
		topInfoPanel.add(lblTransporterParts);
		
		JLabel lblMoney = new JLabel("Money: $" + crew.getMoney());
		lblMoney.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMoney.setBounds(353, 12, 111, 16);
		topInfoPanel.add(lblMoney);
	}
	
	/**
	 * This method is called by the GameEnvironment class to "refresh" GUI elements after a change to 
	 * their value has occurred.<br>
	 * It calls the methods refreshCrewPane() and refreshInfoPane() to refresh each of the JPanels that contain changing information.
	 */
	public void refresh() {
		refreshCrewPane();
		refreshTopInfoPane();
	}
	/**
	 * This closes the GameWindow Instance.
	 */
	public void closeWindow() {
		gameScreen.dispose();
	}
	/**
	 * This method refreshes all GUI elements within the crewPane JPanel.
	 * It removes all elements within the crewPane, then calls the buildCrewPane() method to recreate them.<br>
	 * After this it is revalidated and the repainted to display all the new elements.
	 */
	private void refreshCrewPane() {
		crewPanel.removeAll();
		buildCrewPanel();
		crewPanel.revalidate();
		crewPanel.repaint();
	}
	/**
	 * This method refreshes all GUI elements within the topInfoPane JPanel.
	 * It removes all elements within the topInfoPane, then calls the buildTopInfoPane() method to recreate them.<br>
	 * After this it is revalidated and the repainted to display all the new elements.
	 */
	private void refreshTopInfoPane() {
		topInfoPanel.removeAll();
		buildTopInfoPanel();
		topInfoPanel.revalidate();
		topInfoPanel.repaint();
	}
	/**
	 * This method is used to call the nextDay method within GameEnvironment passing the GameWindow instance to the method.
	 */
	private void nextDay() {
		environment.nextDay(this);
	}
	/**
	 * This method refreshes the statusPane JTextPane text to display the status of the currently selected CrewMember in the 
	 * listOfCrew JList.
	 */
	private void updateCrewMember() {
		CrewMember member = listOfCrew.getSelectedValue();
		statusPane.setText(member.viewStatus());
	}
}