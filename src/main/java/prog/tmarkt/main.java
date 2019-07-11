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
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

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
	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize(){
		frame = new JFrame();
		frame.setBounds(100, 100, 741, 479);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		JTabbedPane tabbedPane1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane1.setBounds(0, 0, 727, 442);
		frame.getContentPane().add(tabbedPane1);
		
		JPanel tabAdd = new JPanel();
		tabbedPane1.addTab("Add", null, tabAdd, null);
		tabAdd.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 722, 415);
		tabAdd.add(tabbedPane);
		
		JPanel pTeam = new JPanel();
		tabbedPane.addTab("Team", null, pTeam, null);
		pTeam.setLayout(null);
		
		JLabel lblName_1 = new JLabel("Name :");
		lblName_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblName_1.setBounds(217, 10, 67, 19);
		pTeam.add(lblName_1);
		
		JLabel lblColors = new JLabel("Colors :");
		lblColors.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblColors.setBounds(415, 47, 67, 19);
		pTeam.add(lblColors);
		
		JLabel lblCity = new JLabel("City :");
		lblCity.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblCity.setBounds(33, 88, 67, 19);
		pTeam.add(lblCity);
		
		JLabel lblStadium = new JLabel("Stadium :");
		lblStadium.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblStadium.setBounds(33, 136, 82, 19);
		pTeam.add(lblStadium);
		
		JLabel lblFormationDate = new JLabel("Formation Date :");
		lblFormationDate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblFormationDate.setBounds(415, 88, 129, 19);
		pTeam.add(lblFormationDate);
		
		JLabel lblPresident = new JLabel("President :");
		lblPresident.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPresident.setBounds(33, 47, 97, 19);
		pTeam.add(lblPresident);
		
		JLabel lblChampionshipYears = new JLabel("Championship Years : ");
		lblChampionshipYears.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblChampionshipYears.setBounds(34, 185, 171, 19);
		pTeam.add(lblChampionshipYears);
		
		JLabel lblLeague = new JLabel("League :");
		lblLeague.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblLeague.setBounds(415, 136, 67, 19);
		pTeam.add(lblLeague);
		
		JLabel lblCups = new JLabel("Cups :");
		lblCups.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblCups.setBounds(33, 244, 82, 19);
		pTeam.add(lblCups);
		
		JLabel lblLastMatch = new JLabel("Last Match :");
		lblLastMatch.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblLastMatch.setBounds(415, 187, 94, 19);
		pTeam.add(lblLastMatch);
		
		JLabel lblSrore = new JLabel("Score :");
		lblSrore.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblSrore.setBounds(415, 244, 67, 19);
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
		btnTSubmit.setBounds(283, 322, 117, 35);
		pTeam.add(btnTSubmit);
		
		txtTName = new JTextField();
		lblName_1.setLabelFor(txtTName);
		txtTName.setBounds(294, 12, 163, 19);
		pTeam.add(txtTName);
		txtTName.setColumns(10);
		
		txtTPres = new JTextField();
		lblPresident.setLabelFor(txtTPres);
		txtTPres.setColumns(10);
		txtTPres.setBounds(136, 49, 163, 19);
		pTeam.add(txtTPres);
		
		txtTCity = new JTextField();
		lblCity.setLabelFor(txtTCity);
		txtTCity.setColumns(10);
		txtTCity.setBounds(136, 90, 163, 19);
		pTeam.add(txtTCity);
		
		txtTStadium = new JTextField();
		lblStadium.setLabelFor(txtTStadium);
		txtTStadium.setColumns(10);
		txtTStadium.setBounds(136, 138, 163, 19);
		pTeam.add(txtTStadium);
		
		txtTChampYears = new JTextField();
		lblChampionshipYears.setLabelFor(txtTChampYears);
		txtTChampYears.setColumns(10);
		txtTChampYears.setBounds(213, 187, 163, 19);
		pTeam.add(txtTChampYears);
		
		txtTCups = new JTextField();
		lblCups.setLabelFor(txtTCups);
		txtTCups.setColumns(10);
		txtTCups.setBounds(136, 246, 163, 19);
		pTeam.add(txtTCups);
		
		txtTColors = new JTextField();
		lblColors.setLabelFor(txtTColors);
		txtTColors.setColumns(10);
		txtTColors.setBounds(493, 49, 163, 19);
		pTeam.add(txtTColors);
		
		txtTLeague = new JTextField();
		lblLeague.setLabelFor(txtTLeague);
		txtTLeague.setColumns(10);
		txtTLeague.setBounds(493, 138, 163, 19);
		pTeam.add(txtTLeague);
		
		txtTLastMatch = new JTextField();
		lblLastMatch.setLabelFor(txtTLastMatch);
		txtTLastMatch.setColumns(10);
		txtTLastMatch.setBounds(519, 189, 155, 19);
		pTeam.add(txtTLastMatch);
		
		txtTScore = new JTextField();
		lblSrore.setLabelFor(txtTScore);
		txtTScore.setColumns(10);
		txtTScore.setBounds(493, 246, 163, 19);
		pTeam.add(txtTScore);
		
		ftxtTFormDate = new JFormattedTextField(format);
		lblFormationDate.setLabelFor(ftxtTFormDate);
		ftxtTFormDate.setBounds(554, 90, 120, 19);
		pTeam.add(ftxtTFormDate);
		
		JPanel pPlayer = new JPanel();
		tabbedPane.addTab("Player", null, pPlayer, null);
		pPlayer.setLayout(null);
		
		JLabel lblPName = new JLabel("Name :");
		lblPName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPName.setBounds(25, 12, 57, 13);
		pPlayer.add(lblPName);
		
		JLabel lblPSurname = new JLabel("Surname :");
		lblPSurname.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPSurname.setBounds(25, 45, 90, 13);
		pPlayer.add(lblPSurname);
		
		JLabel lblPDateOfBirth = new JLabel("Date of Birth :");
		lblPDateOfBirth.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPDateOfBirth.setBounds(25, 87, 114, 13);
		pPlayer.add(lblPDateOfBirth);
		
		JLabel lblPCurrTeam = new JLabel("Current Team :");
		lblPCurrTeam.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPCurrTeam.setBounds(25, 121, 114, 13);
		pPlayer.add(lblPCurrTeam);
		
		JLabel lblPPrevTeams = new JLabel("Previous Teams :");
		lblPPrevTeams.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPPrevTeams.setBounds(25, 160, 136, 13);
		pPlayer.add(lblPPrevTeams);
		
		JLabel lblPStatus = new JLabel("Status :");
		lblPStatus.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPStatus.setBounds(404, 10, 77, 13);
		pPlayer.add(lblPStatus);
		
		JLabel lblPRealteam = new JLabel("Real Team :");
		lblPRealteam.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPRealteam.setBounds(404, 51, 90, 13);
		pPlayer.add(lblPRealteam);
		
		JLabel lblPDomFoot = new JLabel("Dominant Foot :");
		lblPDomFoot.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPDomFoot.setBounds(404, 85, 124, 13);
		pPlayer.add(lblPDomFoot);
		
		JLabel lblPTransFee = new JLabel("Transfer Fee :");
		lblPTransFee.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPTransFee.setBounds(404, 123, 120, 13);
		pPlayer.add(lblPTransFee);
		
		JLabel lblPInjuries = new JLabel("Injuries :");
		lblPInjuries.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPInjuries.setBounds(404, 204, 77, 13);
		pPlayer.add(lblPInjuries);
		
		txtPName = new JTextField();
		txtPName.setBounds(167, 11, 154, 19);
		pPlayer.add(txtPName);
		txtPName.setColumns(10);
		
		txtPSurname = new JTextField();
		txtPSurname.setColumns(10);
		txtPSurname.setBounds(167, 44, 154, 19);
		pPlayer.add(txtPSurname);
		
		txtPCurrentTeam = new JTextField();
		txtPCurrentTeam.setColumns(10);
		txtPCurrentTeam.setBounds(167, 120, 154, 19);
		pPlayer.add(txtPCurrentTeam);
		
		txtPPrevTeams = new JTextField();
		txtPPrevTeams.setColumns(10);
		txtPPrevTeams.setBounds(167, 159, 154, 19);
		pPlayer.add(txtPPrevTeams);
		
		txtPRealTeam = new JTextField();
		txtPRealTeam.setColumns(10);
		txtPRealTeam.setBounds(534, 44, 173, 19);
		pPlayer.add(txtPRealTeam);
		txtPRealTeam.setEnabled(false);
		
		JLabel lblAmount_1 = new JLabel("Amount (\u20AC) :");
		lblAmount_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblAmount_1.setBounds(404, 160, 99, 13);
		pPlayer.add(lblAmount_1);
		
		txtPAmount = new JTextField();
		txtPAmount.setColumns(10);
		txtPAmount.setBounds(534, 159, 173, 19);
		pPlayer.add(txtPAmount);
		txtPAmount.setEnabled(false);
		
		txtaPInjuries = new JTextArea();
		txtaPInjuries.setBounds(534, 206, 173, 103);
		pPlayer.add(txtaPInjuries);
		
		
		JRadioButton rdbtnBought = new JRadioButton("Bought");
		rdbtnBought.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnBought.setSelected(true);
		rdbtnBought.setBounds(534, 9, 63, 21);
		pPlayer.add(rdbtnBought);
		
		JRadioButton rdbtnRented = new JRadioButton("Rented");
		rdbtnRented.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnRented.setBounds(633, 10, 74, 21);
		pPlayer.add(rdbtnRented);
		
		bgrentorbought = new ButtonGroup();
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
		rdbtnLeft.setBounds(534, 85, 47, 21);
		pPlayer.add(rdbtnLeft);
		
		JRadioButton rdbtnRight = new JRadioButton("Right");
		rdbtnRight.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnRight.setBounds(583, 85, 63, 21);
		pPlayer.add(rdbtnRight);
		
		JRadioButton rdbtnBoth = new JRadioButton("Both");
		rdbtnBoth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnBoth.setBounds(648, 85, 63, 21);
		pPlayer.add(rdbtnBoth);
		
		bgleftrightboth = new ButtonGroup();
		bgleftrightboth.add(rdbtnLeft);
		bgleftrightboth.add(rdbtnRight);
		bgleftrightboth.add(rdbtnBoth);
				
		JRadioButton rdbtnInHand_1 = new JRadioButton("In Hand");
		rdbtnInHand_1.setSelected(true);
		rdbtnInHand_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnInHand_1.setBounds(534, 119, 74, 21);
		pPlayer.add(rdbtnInHand_1);
		
		JRadioButton rdbtnNotInHand_1 = new JRadioButton("Not In Hand");
		rdbtnNotInHand_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnNotInHand_1.setBounds(610, 119, 97, 21);
		pPlayer.add(rdbtnNotInHand_1);
		
		bginhandornot = new ButtonGroup();
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
		lblName.setBounds(189, 41, 75, 26);
		pManager.add(lblName);
		
		JLabel lblSurname = new JLabel("Surname :");
		lblSurname.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblSurname.setBounds(189, 77, 92, 26);
		pManager.add(lblSurname);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth :");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblDateOfBirth.setBounds(189, 113, 114, 26);
		pManager.add(lblDateOfBirth);
		
		JLabel lblCurrentTeam = new JLabel("Current Team :");
		lblCurrentTeam.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblCurrentTeam.setBounds(189, 149, 114, 26);
		pManager.add(lblCurrentTeam);
		
		JLabel lblPreviousTeams = new JLabel("Previous Teams : ");
		lblPreviousTeams.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPreviousTeams.setBounds(189, 185, 135, 26);
		pManager.add(lblPreviousTeams);
		
		JLabel lblTransferFee = new JLabel("Transfer Fee : ");
		lblTransferFee.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblTransferFee.setBounds(189, 221, 135, 26);
		pManager.add(lblTransferFee);
		
		JRadioButton rdbtnInHand = new JRadioButton("In Hand");
		rdbtnInHand.setSelected(true);
		rdbtnInHand.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnInHand.setBounds(368, 226, 75, 21);
		pManager.add(rdbtnInHand);
		
		JRadioButton rdbtnNotInHand = new JRadioButton("Not In Hand");
		rdbtnNotInHand.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnNotInHand.setBounds(458, 225, 105, 21);
		pManager.add(rdbtnNotInHand);
		
		feeMButtons = new ButtonGroup();
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
		lblAmount.setBounds(189, 257, 135, 26);
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
		btnPSubmit.setBounds(312, 313, 117, 35);
		pPlayer.add(btnPSubmit);
		
		txtfPDoB = new JFormattedTextField(format);
		lblPDateOfBirth.setLabelFor(txtfPDoB);
		txtfPDoB.setBounds(167, 86, 154, 19);
		pPlayer.add(txtfPDoB);
		btnManSubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnManSubmit.setBounds(283, 315, 117, 35);
		pManager.add(btnManSubmit);
		
		txtManName = new JTextField();
		txtManName.setColumns(10);
		txtManName.setBounds(368, 47, 195, 19);
		pManager.add(txtManName);
		
		txtManSurname = new JTextField();
		txtManSurname.setColumns(10);
		txtManSurname.setBounds(368, 83, 195, 19);
		pManager.add(txtManSurname);
		
		txtManDoB = new JFormattedTextField(format);
		txtManDoB.setBounds(368, 119, 195, 19);
		pManager.add(txtManDoB);
		
		txtManCTeam = new JTextField();
		txtManCTeam.setColumns(10);
		txtManCTeam.setBounds(368, 155, 195, 19);
		pManager.add(txtManCTeam);
		
		txtManPTeam = new JTextField();
		txtManPTeam.setColumns(10);
		txtManPTeam.setBounds(368, 191, 195, 19);
		pManager.add(txtManPTeam);
		
		txtManAmount = new JTextField();
		txtManAmount.setColumns(10);
		txtManAmount.setBounds(368, 263, 195, 19);
		pManager.add(txtManAmount);
		txtManAmount.setEnabled(false);
		
		JPanel pPres = new JPanel();
		tabbedPane.addTab("President", null, pPres, null);
		pPres.setLayout(null);
		
		JLabel lblPresName = new JLabel("Name :");
		lblPresName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPresName.setBounds(193, 66, 66, 35);
		pPres.add(lblPresName);
		
		JLabel lblPresSurname = new JLabel("Surname :");
		lblPresSurname.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPresSurname.setBounds(193, 111, 88, 35);
		pPres.add(lblPresSurname);
		
		JLabel lblPresDateOfBirth = new JLabel("Date of Birth :");
		lblPresDateOfBirth.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPresDateOfBirth.setBounds(193, 156, 117, 35);
		pPres.add(lblPresDateOfBirth);
		
		JLabel lblPresTeam = new JLabel("Team :");
		lblPresTeam.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPresTeam.setBounds(193, 201, 117, 35);
		pPres.add(lblPresTeam);
		
		txtPresName = new JTextField();
		lblPresName.setLabelFor(txtPresName);
		txtPresName.setBounds(329, 76, 195, 19);
		pPres.add(txtPresName);
		txtPresName.setColumns(10);
		
		txtPresSurname = new JTextField();
		lblPresSurname.setLabelFor(txtPresSurname);
		txtPresSurname.setColumns(10);
		txtPresSurname.setBounds(329, 121, 195, 19);
		pPres.add(txtPresSurname);
		
		txtPresTeam = new JTextField();
		lblPresTeam.setLabelFor(txtPresTeam);
		txtPresTeam.setColumns(10);
		txtPresTeam.setBounds(329, 211, 195, 19);
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
		btnPresSubmit.setBounds(279, 296, 117, 35);
		pPres.add(btnPresSubmit);
		
		txtPresDoB = new JFormattedTextField(format);
		lblPresDateOfBirth.setLabelFor(txtPresDoB);
		txtPresDoB.setBounds(329, 166, 195, 19);
		pPres.add(txtPresDoB);
		
		
		JPanel tabEdit = new JPanel();
		tabbedPane1.addTab("Edit", null, tabEdit, null);
		tabEdit.setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(0, 0, 722, 415);
		tabEdit.add(tabbedPane_1);
		
		JPanel pETeam = new JPanel();
		tabbedPane_1.addTab("Team", null, pETeam, null);
		
		JPanel pEPlayer = new JPanel();
		tabbedPane_1.addTab("Player", null, pEPlayer, null);
		
		JPanel pEManager = new JPanel();
		tabbedPane_1.addTab("Manager", null, pEManager, null);
		
		JPanel pEPres = new JPanel();
		tabbedPane_1.addTab("President", null, pEPres, null);
	}
}
