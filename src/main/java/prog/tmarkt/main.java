package prog.tmarkt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

import javax.swing.JTextField;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;

public class main {

	private JFrame frame;
	private JTextField txtPresName;
	private JTextField txtPresSurname;
	private JTextField txtPresTeam;
	JFormattedTextField txtPresDoB;
	JFormattedTextField ftxtTFormDate;
	JFormattedTextField txtManDoB;
	JFormattedTextField txtfPDoB;
	private JTextField txtManName;
	private JTextField txtManSurname;
	private JTextField txtManCTeam;
	private JTextField txtManPTeam;
	private JTextField txtManAmount;
	private JTextField txtTName;
	private JTextField txtTPres;
	private JTextField txtTCity;
	private JTextField txtTStadium;
	private JTextField txtTChampYears;
	private JTextField txtTCups;
	private JTextField txtTColors;
	private JTextField txtTLeague;
	private JTextField txtTLastMatch;
	private JTextField txtTScore;
	private JTextField txtPName;
	private JTextField txtPSurname;
	private JTextField txtPCurrentTeam;
	private JTextField txtPPrevTeams;
	private JTextField txtPRealTeam;
	private JTextField txtPAmount;
	JTextArea txtaPInjuries;
	ButtonGroup feeMButtons;
	ButtonGroup bgrentorbought;
	ButtonGroup bgleftrightboth;
	ButtonGroup bginhandornot;
	private JTextField txtPresFindName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public main(){
		initialize();
		
	}
	
	public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

	public void mongoDatabaseaddPres() throws Exception {
		
		DBObject president = new BasicDBObject()
		                            .append("name", txtPresName.getText())
		                            .append("surname", txtPresSurname.getText())
		                            .append("dateofbirth", txtPresDoB.getText())
									.append("team",txtPresTeam.getText());
		MongoClient mongoClient = new MongoClient();
		DB database = mongoClient.getDB("tmar");
		DBCollection collection = database.getCollection("presidents");
		collection.insert(president);
		mongoClient.close();
	}
	
	public void mongoDatabaseaddManager() throws Exception {
		
		DBObject manager = new BasicDBObject()
		                            .append("name", txtManName.getText())
		                            .append("surname", txtManSurname.getText())
		                            .append("dateofbirth", txtManDoB.getText())
									.append("currentteam",txtManCTeam.getText())
									.append("previousteam",txtManPTeam.getText())
									.append("tranferfee",getSelectedButtonText(feeMButtons))
									.append("amount",txtManAmount.getText());
		MongoClient mongoClient = new MongoClient();
		DB database = mongoClient.getDB("tmar");
		DBCollection collection = database.getCollection("managers");
		collection.insert(manager);
		mongoClient.close();
	}
	
	public void mongoDatabaseaddTeam() throws Exception {
		
		DBObject team = new BasicDBObject()
		                            .append("name", txtTName.getText())
		                            .append("president", txtTPres.getText())
		                            .append("city", txtTCity.getText())
									.append("stadium",txtTStadium.getText())
									.append("championshipyears",txtTChampYears.getText())
									.append("cups", txtTCups.getText())
									.append("colors",txtTColors.getText())
									.append("formationdate",ftxtTFormDate.getText())
									.append("league",txtTLeague.getText())
									.append("lastmatch",txtTLastMatch.getText())
									.append("score",txtTScore.getText());
		MongoClient mongoClient = new MongoClient();
		DB database = mongoClient.getDB("tmar");
		DBCollection collection = database.getCollection("teams");
		collection.insert(team);
		mongoClient.close();
	}
	
	public void mongoDatabaseaddPlayer() throws Exception {
		
		DBObject player = new BasicDBObject()
		                            .append("name", txtPName.getText())
		                            .append("surname", txtPSurname.getText())
		                            .append("dateofbirth", txtfPDoB.getText())
									.append("currentteam",txtPCurrentTeam.getText())
									.append("previousteam",txtPPrevTeams.getText())
									.append("status",getSelectedButtonText(bgrentorbought))
									.append("realteam",txtPRealTeam.getText())
									.append("dominantfoot",getSelectedButtonText(bgleftrightboth))
									.append("tranferfee",getSelectedButtonText(bginhandornot))
									.append("amount",txtPAmount.getText())
									.append("injuries",txtaPInjuries.getText());
		MongoClient mongoClient = new MongoClient();
		DB database = mongoClient.getDB("tmar");
		DBCollection collection = database.getCollection("players");
		collection.insert(player);
		mongoClient.close();
	}
	
	public void mongoDatabaseFindPres() throws Exception {
		
		MongoClient mongoClient = new MongoClient();
		DB database = mongoClient.getDB("tmar");
		DBCollection collection = database.getCollection("presidents");
		
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("name", txtPresFindName.getText());
		DBCursor cursor = collection.find(whereQuery);
		while(cursor.hasNext()) {
			
			DBObject president = cursor.next();
		    System.out.println(president);
		    
		    String name = (String) president.get("name");
		    String surname = (String) president.get("surname");
		    String dateofbirth = (String) president.get("dateofbirth");
		    String team = (String) president.get("team");
		    txtPresName.setEnabled(true);
		    txtPresName.setText(name);
		    txtPresSurname.setEnabled(true);
		    txtPresSurname.setText(surname);
		    txtPresDoB.setEnabled(true);
		    txtPresDoB.setText(dateofbirth);
		    txtPresTeam.setEnabled(true);
		    txtPresTeam.setText(team);

		}
		
		mongoClient.close();
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize(){
		frame = new JFrame();
		frame.setBounds(100, 100, 792, 535);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		bgrentorbought = new ButtonGroup();
		
		bgleftrightboth = new ButtonGroup();
		
		bginhandornot = new ButtonGroup();
		
		feeMButtons = new ButtonGroup();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 778, 498);
		frame.getContentPane().add(tabbedPane);
		
		JPanel pTeam = new JPanel();
		tabbedPane.addTab("Team", null, pTeam, null);
		pTeam.setLayout(null);
		
		JLabel lblName_1 = new JLabel("Name :");
		lblName_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblName_1.setBounds(257, 95, 67, 19);
		pTeam.add(lblName_1);
		
		JLabel lblColors = new JLabel("Colors :");
		lblColors.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblColors.setBounds(455, 132, 67, 19);
		pTeam.add(lblColors);
		
		JLabel lblCity = new JLabel("City :");
		lblCity.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblCity.setBounds(73, 173, 67, 19);
		pTeam.add(lblCity);
		
		JLabel lblStadium = new JLabel("Stadium :");
		lblStadium.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblStadium.setBounds(73, 221, 82, 19);
		pTeam.add(lblStadium);
		
		JLabel lblFormationDate = new JLabel("Formation Date :");
		lblFormationDate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblFormationDate.setBounds(455, 173, 129, 19);
		pTeam.add(lblFormationDate);
		
		JLabel lblPresident = new JLabel("President :");
		lblPresident.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPresident.setBounds(73, 132, 97, 19);
		pTeam.add(lblPresident);
		
		JLabel lblChampionshipYears = new JLabel("Championship Years : ");
		lblChampionshipYears.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblChampionshipYears.setBounds(74, 270, 171, 19);
		pTeam.add(lblChampionshipYears);
		
		JLabel lblLeague = new JLabel("League :");
		lblLeague.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblLeague.setBounds(455, 221, 67, 19);
		pTeam.add(lblLeague);
		
		JLabel lblCups = new JLabel("Cups :");
		lblCups.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblCups.setBounds(73, 329, 82, 19);
		pTeam.add(lblCups);
		
		JLabel lblLastMatch = new JLabel("Last Match :");
		lblLastMatch.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblLastMatch.setBounds(455, 272, 94, 19);
		pTeam.add(lblLastMatch);
		
		JLabel lblSrore = new JLabel("Score :");
		lblSrore.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblSrore.setBounds(455, 329, 67, 19);
		pTeam.add(lblSrore);
		
		JButton btnTSubmit = new JButton("Submit");
		btnTSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mongoDatabaseaddTeam();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				txtTChampYears.setText("");
				txtTCity.setText("");
				txtTColors.setText("");
				txtTCups.setText("");
				txtTLastMatch.setText("");
				txtTLeague.setText("");
				txtTName.setText("");
				txtTPres.setText("");
				txtTScore.setText("");
				txtTStadium.setText("");
				ftxtTFormDate.setText("");
			}
		});
		btnTSubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTSubmit.setBounds(323, 387, 117, 35);
		pTeam.add(btnTSubmit);
		
		txtTName = new JTextField();
		lblName_1.setLabelFor(txtTName);
		txtTName.setBounds(334, 97, 163, 19);
		pTeam.add(txtTName);
		txtTName.setColumns(10);
		
		txtTPres = new JTextField();
		lblPresident.setLabelFor(txtTPres);
		txtTPres.setColumns(10);
		txtTPres.setBounds(176, 134, 163, 19);
		pTeam.add(txtTPres);
		
		txtTCity = new JTextField();
		lblCity.setLabelFor(txtTCity);
		txtTCity.setColumns(10);
		txtTCity.setBounds(176, 175, 163, 19);
		pTeam.add(txtTCity);
		
		txtTStadium = new JTextField();
		lblStadium.setLabelFor(txtTStadium);
		txtTStadium.setColumns(10);
		txtTStadium.setBounds(176, 223, 163, 19);
		pTeam.add(txtTStadium);
		
		txtTChampYears = new JTextField();
		lblChampionshipYears.setLabelFor(txtTChampYears);
		txtTChampYears.setColumns(10);
		txtTChampYears.setBounds(253, 272, 163, 19);
		pTeam.add(txtTChampYears);
		
		txtTCups = new JTextField();
		lblCups.setLabelFor(txtTCups);
		txtTCups.setColumns(10);
		txtTCups.setBounds(176, 331, 163, 19);
		pTeam.add(txtTCups);
		
		txtTColors = new JTextField();
		lblColors.setLabelFor(txtTColors);
		txtTColors.setColumns(10);
		txtTColors.setBounds(533, 134, 163, 19);
		pTeam.add(txtTColors);
		
		txtTLeague = new JTextField();
		lblLeague.setLabelFor(txtTLeague);
		txtTLeague.setColumns(10);
		txtTLeague.setBounds(533, 223, 163, 19);
		pTeam.add(txtTLeague);
		
		txtTLastMatch = new JTextField();
		lblLastMatch.setLabelFor(txtTLastMatch);
		txtTLastMatch.setColumns(10);
		txtTLastMatch.setBounds(559, 274, 155, 19);
		pTeam.add(txtTLastMatch);
		
		txtTScore = new JTextField();
		lblSrore.setLabelFor(txtTScore);
		txtTScore.setColumns(10);
		txtTScore.setBounds(533, 331, 163, 19);
		pTeam.add(txtTScore);
		
		ftxtTFormDate = new JFormattedTextField(format);
		lblFormationDate.setLabelFor(ftxtTFormDate);
		ftxtTFormDate.setBounds(594, 175, 120, 19);
		pTeam.add(ftxtTFormDate);
		
		JPanel pPlayer = new JPanel();
		tabbedPane.addTab("Player", null, pPlayer, null);
		pPlayer.setLayout(null);
		
		JLabel lblPName = new JLabel("Name :");
		lblPName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPName.setBounds(29, 96, 57, 13);
		pPlayer.add(lblPName);
		
		JLabel lblPSurname = new JLabel("Surname :");
		lblPSurname.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPSurname.setBounds(29, 129, 90, 13);
		pPlayer.add(lblPSurname);
		
		JLabel lblPDateOfBirth = new JLabel("Date of Birth :");
		lblPDateOfBirth.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPDateOfBirth.setBounds(29, 171, 114, 13);
		pPlayer.add(lblPDateOfBirth);
		
		JLabel lblPCurrTeam = new JLabel("Current Team :");
		lblPCurrTeam.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPCurrTeam.setBounds(29, 205, 114, 13);
		pPlayer.add(lblPCurrTeam);
		
		JLabel lblPPrevTeams = new JLabel("Previous Teams :");
		lblPPrevTeams.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPPrevTeams.setBounds(29, 244, 136, 13);
		pPlayer.add(lblPPrevTeams);
		
		JLabel lblPStatus = new JLabel("Status :");
		lblPStatus.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPStatus.setBounds(408, 94, 77, 13);
		pPlayer.add(lblPStatus);
		
		JLabel lblPRealteam = new JLabel("Real Team :");
		lblPRealteam.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPRealteam.setBounds(408, 135, 90, 13);
		pPlayer.add(lblPRealteam);
		
		JLabel lblPDomFoot = new JLabel("Dominant Foot :");
		lblPDomFoot.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPDomFoot.setBounds(408, 169, 124, 13);
		pPlayer.add(lblPDomFoot);
		
		JLabel lblPTransFee = new JLabel("Transfer Fee :");
		lblPTransFee.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPTransFee.setBounds(408, 207, 120, 13);
		pPlayer.add(lblPTransFee);
		
		JLabel lblPInjuries = new JLabel("Injuries :");
		lblPInjuries.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPInjuries.setBounds(408, 288, 77, 13);
		pPlayer.add(lblPInjuries);
		
		txtPName = new JTextField();
		txtPName.setBounds(171, 95, 154, 19);
		pPlayer.add(txtPName);
		txtPName.setColumns(10);
		
		txtPSurname = new JTextField();
		txtPSurname.setColumns(10);
		txtPSurname.setBounds(171, 128, 154, 19);
		pPlayer.add(txtPSurname);
		
		txtPCurrentTeam = new JTextField();
		txtPCurrentTeam.setColumns(10);
		txtPCurrentTeam.setBounds(171, 204, 154, 19);
		pPlayer.add(txtPCurrentTeam);
		
		txtPPrevTeams = new JTextField();
		txtPPrevTeams.setColumns(10);
		txtPPrevTeams.setBounds(171, 243, 154, 19);
		pPlayer.add(txtPPrevTeams);
		
		txtPRealTeam = new JTextField();
		txtPRealTeam.setColumns(10);
		txtPRealTeam.setBounds(538, 128, 173, 19);
		pPlayer.add(txtPRealTeam);
		txtPRealTeam.setEnabled(false);
		
		JLabel lblAmount_1 = new JLabel("Amount (\u20AC) :");
		lblAmount_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblAmount_1.setBounds(408, 244, 99, 13);
		pPlayer.add(lblAmount_1);
		
		txtPAmount = new JTextField();
		txtPAmount.setColumns(10);
		txtPAmount.setBounds(538, 243, 173, 19);
		pPlayer.add(txtPAmount);
		txtPAmount.setEnabled(false);
		
		txtaPInjuries = new JTextArea();
		txtaPInjuries.setBounds(538, 290, 173, 103);
		pPlayer.add(txtaPInjuries);
		
		
		JRadioButton rdbtnBought = new JRadioButton("Bought");
		rdbtnBought.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnBought.setSelected(true);
		rdbtnBought.setBounds(538, 93, 63, 21);
		pPlayer.add(rdbtnBought);
		
		JRadioButton rdbtnRented = new JRadioButton("Rented");
		rdbtnRented.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnRented.setBounds(637, 94, 74, 21);
		pPlayer.add(rdbtnRented);
		bgrentorbought.add(rdbtnRented);
		bgrentorbought.add(rdbtnBought);
		
		rdbtnRented.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtPRealTeam.setEnabled(true);
				
			}
		});
		
		rdbtnBought.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtPRealTeam.setEnabled(false);
				txtPRealTeam.setText("");
			}
		});
		
		JRadioButton rdbtnLeft = new JRadioButton("Left");
		rdbtnLeft.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnLeft.setSelected(true);
		rdbtnLeft.setBounds(538, 169, 47, 21);
		pPlayer.add(rdbtnLeft);
		
		JRadioButton rdbtnRight = new JRadioButton("Right");
		rdbtnRight.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnRight.setBounds(587, 169, 63, 21);
		pPlayer.add(rdbtnRight);
		
		JRadioButton rdbtnBoth = new JRadioButton("Both");
		rdbtnBoth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnBoth.setBounds(652, 169, 63, 21);
		pPlayer.add(rdbtnBoth);
		bgleftrightboth.add(rdbtnLeft);
		bgleftrightboth.add(rdbtnRight);
		bgleftrightboth.add(rdbtnBoth);
		
		JRadioButton rdbtnInHand_1 = new JRadioButton("In Hand");
		rdbtnInHand_1.setSelected(true);
		rdbtnInHand_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnInHand_1.setBounds(538, 203, 74, 21);
		pPlayer.add(rdbtnInHand_1);
		
		JRadioButton rdbtnNotInHand_1 = new JRadioButton("Not In Hand");
		rdbtnNotInHand_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnNotInHand_1.setBounds(614, 203, 97, 21);
		pPlayer.add(rdbtnNotInHand_1);
		bginhandornot.add(rdbtnInHand_1);
		bginhandornot.add(rdbtnNotInHand_1);
		
		rdbtnNotInHand_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtPAmount.setEnabled(true);
				
			}
		});
		
		rdbtnInHand_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtPAmount.setEnabled(false);
				
			}
		});
		
		JPanel pManager = new JPanel();
		tabbedPane.addTab("Manager", null, pManager, null);
		pManager.setLayout(null);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblName.setBounds(229, 112, 75, 26);
		pManager.add(lblName);
		
		JLabel lblSurname = new JLabel("Surname :");
		lblSurname.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblSurname.setBounds(229, 148, 92, 26);
		pManager.add(lblSurname);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth :");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblDateOfBirth.setBounds(229, 184, 114, 26);
		pManager.add(lblDateOfBirth);
		
		JLabel lblCurrentTeam = new JLabel("Current Team :");
		lblCurrentTeam.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblCurrentTeam.setBounds(229, 220, 114, 26);
		pManager.add(lblCurrentTeam);
		
		JLabel lblPreviousTeams = new JLabel("Previous Teams : ");
		lblPreviousTeams.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPreviousTeams.setBounds(229, 256, 135, 26);
		pManager.add(lblPreviousTeams);
		
		JLabel lblTransferFee = new JLabel("Transfer Fee : ");
		lblTransferFee.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblTransferFee.setBounds(229, 292, 135, 26);
		pManager.add(lblTransferFee);
		
		JRadioButton rdbtnInHand = new JRadioButton("In Hand");
		rdbtnInHand.setSelected(true);
		rdbtnInHand.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnInHand.setBounds(408, 297, 75, 21);
		pManager.add(rdbtnInHand);
		
		JRadioButton rdbtnNotInHand = new JRadioButton("Not In Hand");
		rdbtnNotInHand.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnNotInHand.setBounds(498, 296, 105, 21);
		pManager.add(rdbtnNotInHand);
		feeMButtons.add(rdbtnInHand);
		feeMButtons.add(rdbtnNotInHand);
		
		
		rdbtnInHand.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				txtManAmount.setText("");
				txtManAmount.setEnabled(false);
				
			}
		});
		rdbtnNotInHand.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				txtManAmount.setEnabled(true);
			}
		});
		
		JLabel lblAmount = new JLabel("Amount (\u20AC) : ");
		lblAmount.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblAmount.setBounds(229, 328, 135, 26);
		pManager.add(lblAmount);
		
		JButton btnManSubmit = new JButton("Submit");
		btnManSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mongoDatabaseaddManager();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				txtManName.setText("");
				txtManSurname.setText("");
				txtManDoB.setText("");
				txtManCTeam.setText("");
				txtManPTeam.setText("");
				rdbtnInHand.setSelected(true);
				txtManAmount.setText("");
			}
		});
		
		JButton btnPSubmit = new JButton("Submit");
		btnPSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mongoDatabaseaddPlayer();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				txtPAmount.setText("");
				txtPCurrentTeam.setText("");
				txtfPDoB.setText("");
				txtPName.setText("");
				txtPPrevTeams.setText("");
				txtPRealTeam.setText("");
				txtPSurname.setText("");
				txtaPInjuries.setText("");
				rdbtnBought.setSelected(true);
				rdbtnLeft.setSelected(true);
				rdbtnInHand_1.setSelected(true);
			}
		});
		btnPSubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPSubmit.setBounds(323, 387, 117, 35);
		pPlayer.add(btnPSubmit);
		
		txtfPDoB = new JFormattedTextField(format);
		lblPDateOfBirth.setLabelFor(txtfPDoB);
		txtfPDoB.setBounds(171, 170, 154, 19);
		pPlayer.add(txtfPDoB);
		btnManSubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnManSubmit.setBounds(323, 387, 117, 35);
		pManager.add(btnManSubmit);
		
		txtManName = new JTextField();
		txtManName.setColumns(10);
		txtManName.setBounds(408, 118, 195, 19);
		pManager.add(txtManName);
		
		txtManSurname = new JTextField();
		txtManSurname.setColumns(10);
		txtManSurname.setBounds(408, 154, 195, 19);
		pManager.add(txtManSurname);
		
		txtManDoB = new JFormattedTextField(format);
		txtManDoB.setBounds(408, 190, 195, 19);
		pManager.add(txtManDoB);
		
		txtManCTeam = new JTextField();
		txtManCTeam.setColumns(10);
		txtManCTeam.setBounds(408, 226, 195, 19);
		pManager.add(txtManCTeam);
		
		txtManPTeam = new JTextField();
		txtManPTeam.setColumns(10);
		txtManPTeam.setBounds(408, 262, 195, 19);
		pManager.add(txtManPTeam);
		
		txtManAmount = new JTextField();
		txtManAmount.setColumns(10);
		txtManAmount.setBounds(408, 334, 195, 19);
		pManager.add(txtManAmount);
		txtManAmount.setEnabled(false);
		
		JPanel pPres = new JPanel();
		tabbedPane.addTab("President", null, pPres, null);
		pPres.setLayout(null);
		
		JLabel lblPresName = new JLabel("Name :");
		lblPresName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPresName.setBounds(224, 157, 66, 35);
		pPres.add(lblPresName);
		
		JLabel lblPresSurname = new JLabel("Surname :");
		lblPresSurname.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPresSurname.setBounds(224, 202, 88, 35);
		pPres.add(lblPresSurname);
		
		JLabel lblPresDateOfBirth = new JLabel("Date of Birth :");
		lblPresDateOfBirth.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPresDateOfBirth.setBounds(224, 247, 117, 35);
		pPres.add(lblPresDateOfBirth);
		
		JLabel lblPresTeam = new JLabel("Team :");
		lblPresTeam.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPresTeam.setBounds(224, 292, 117, 35);
		pPres.add(lblPresTeam);
		
		txtPresName = new JTextField();
		lblPresName.setLabelFor(txtPresName);
		txtPresName.setBounds(360, 167, 195, 19);
		pPres.add(txtPresName);
		txtPresName.setColumns(10);
		
		txtPresSurname = new JTextField();
		lblPresSurname.setLabelFor(txtPresSurname);
		txtPresSurname.setColumns(10);
		txtPresSurname.setBounds(360, 212, 195, 19);
		pPres.add(txtPresSurname);
		
		txtPresTeam = new JTextField();
		lblPresTeam.setLabelFor(txtPresTeam);
		txtPresTeam.setColumns(10);
		txtPresTeam.setBounds(360, 302, 195, 19);
		pPres.add(txtPresTeam);
		
		JButton btnPresSubmit = new JButton("Submit");
		btnPresSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mongoDatabaseaddPres();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				txtPresName.setText("");
				txtPresSurname.setText("");
				txtPresDoB.setText("");
				txtPresTeam.setText("");
			}
		});
		btnPresSubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPresSubmit.setBounds(323, 387, 117, 35);
		pPres.add(btnPresSubmit);
		
		txtPresDoB = new JFormattedTextField(format);
		lblPresDateOfBirth.setLabelFor(txtPresDoB);
		txtPresDoB.setBounds(360, 257, 195, 19);
		pPres.add(txtPresDoB);
		
		JLabel lblPresFindName = new JLabel("Name :");
		lblPresFindName.setEnabled(false);
		lblPresFindName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPresFindName.setBounds(243, 42, 66, 35);
		pPres.add(lblPresFindName);
		
		txtPresFindName = new JTextField();
		txtPresFindName.setEnabled(false);
		txtPresFindName.setColumns(10);
		txtPresFindName.setBounds(328, 52, 195, 19);
		pPres.add(txtPresFindName);
		
		JButton btnPresUpdate = new JButton("Update");
		btnPresUpdate.setEnabled(false);
		btnPresUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPresUpdate.setBounds(523, 387, 117, 35);
		pPres.add(btnPresUpdate);
		
		JButton btnPresFind = new JButton("Find");
		btnPresFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mongoDatabaseFindPres();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPresFind.setEnabled(false);
		btnPresFind.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnPresFind.setBounds(533, 51, 66, 19);
		pPres.add(btnPresFind);
		
		JCheckBox chckbxPresFind = new JCheckBox("Find");
		chckbxPresFind.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxPresFind.setBounds(45, 28, 60, 21);
		pPres.add(chckbxPresFind);
			
		chckbxPresFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(chckbxPresFind.isSelected()) {
					btnPresSubmit.setEnabled(false);
					lblPresDateOfBirth.setEnabled(false);
					lblPresName.setEnabled(false);
					lblPresSurname.setEnabled(false);
					lblPresTeam.setEnabled(false);
					txtPresDoB.setEnabled(false);
					txtPresDoB.setText("");
					txtPresName.setEnabled(false);
					txtPresName.setText("");
					txtPresSurname.setEnabled(false);
					txtPresSurname.setText("");
					txtPresTeam.setEnabled(false);
					txtPresTeam.setText("");
					txtPresFindName.setEnabled(true);
					lblPresFindName.setEnabled(true);
					btnPresUpdate.setEnabled(true);
					btnPresFind.setEnabled(true);
				}else {
					btnPresSubmit.setEnabled(true);
					lblPresDateOfBirth.setEnabled(true);
					lblPresName.setEnabled(true);
					lblPresSurname.setEnabled(true);
					lblPresTeam.setEnabled(true);
					txtPresDoB.setEnabled(true);
					txtPresDoB.setText("");
					txtPresName.setEnabled(true);
					txtPresName.setText("");
					txtPresSurname.setEnabled(true);
					txtPresSurname.setText("");
					txtPresTeam.setEnabled(true);
					txtPresTeam.setText("");
					txtPresFindName.setEnabled(false);
					txtPresFindName.setText("");
					lblPresFindName.setEnabled(false);
					btnPresUpdate.setEnabled(false);
					btnPresFind.setEnabled(false);
				}
			}
		});
	
		
	}
}
