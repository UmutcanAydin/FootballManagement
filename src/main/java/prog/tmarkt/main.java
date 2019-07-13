package prog.tmarkt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.naming.ldap.Rdn;
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
import javax.swing.text.TabExpander;

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
	private JFormattedTextField txtPresDoB;
	private JFormattedTextField ftxtTFormDate;
	private JFormattedTextField txtManDoB;
	private JFormattedTextField txtfPDoB;
	private JRadioButton rdbtnInHand;
	private JRadioButton rdbtnNotInHand;
	private JRadioButton rdbtnInHand_1;
	private JRadioButton rdbtnNotInHand_1;
	private JRadioButton rdbtnBought;
	private JRadioButton rdbtnRented;
	private JRadioButton rdbtnLeft;
	private JRadioButton rdbtnRight;
	private JRadioButton rdbtnBoth;
	private JTabbedPane tabbedPane;
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
	private JTextField txtManFindName;
	private JTextField txtPFind;
	private JTextField txtTFind;
	private JTextArea txtaPInjuries;
	private ButtonGroup feeMButtons;
	private ButtonGroup bgrentorbought;
	private ButtonGroup bgleftrightboth;
	private ButtonGroup bginhandornot;
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

	public void mongoDatabaseadd() throws Exception {
		if(tabbedPane.getSelectedIndex() == 3) {
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
		else if(tabbedPane.getSelectedIndex() == 2) {
			DBObject manager = new BasicDBObject()
                    .append("name", txtManName.getText())
                    .append("surname", txtManSurname.getText())
                    .append("dateofbirth", txtManDoB.getText())
					.append("currentteam",txtManCTeam.getText())
					.append("previousteam",txtManPTeam.getText())
					.append("transferfee",getSelectedButtonText(feeMButtons))
					.append("amount",txtManAmount.getText());
			MongoClient mongoClient = new MongoClient();
			DB database = mongoClient.getDB("tmar");
			DBCollection collection = database.getCollection("managers");
			collection.insert(manager);
			mongoClient.close();
		}
		else if(tabbedPane.getSelectedIndex() == 1) {
			DBObject player = new BasicDBObject()
                    .append("name", txtPName.getText())
                    .append("surname", txtPSurname.getText())
                    .append("dateofbirth", txtfPDoB.getText())
					.append("currentteam",txtPCurrentTeam.getText())
					.append("previousteam",txtPPrevTeams.getText())
					.append("status",getSelectedButtonText(bgrentorbought))
					.append("realteam",txtPRealTeam.getText())
					.append("dominantfoot",getSelectedButtonText(bgleftrightboth))
					.append("transferfee",getSelectedButtonText(bginhandornot))
					.append("amount",txtPAmount.getText())
					.append("injuries",txtaPInjuries.getText());
			MongoClient mongoClient = new MongoClient();
			DB database = mongoClient.getDB("tmar");
			DBCollection collection = database.getCollection("players");
			collection.insert(player);
			mongoClient.close();
		}
		
		else if(tabbedPane.getSelectedIndex() == 0) {
		
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
	
	}
		
	Object objectId;
	public void mongoDatabaseFind() throws Exception {
		if(tabbedPane.getSelectedIndex() == 3) {
			MongoClient mongoClient = new MongoClient();
			DB database = mongoClient.getDB("tmar");
			DBCollection collection = database.getCollection("presidents");
			
			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("name", txtPresFindName.getText());
			DBCursor cursor = collection.find(whereQuery);
			while(cursor.hasNext()) {
				
				DBObject president = cursor.next();
			    
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
			    if(!txtPresName.getText().equals("")) {
			    	objectId = (Object) president.get("_id");
			    }

			}

			mongoClient.close();
			
		}else if(tabbedPane.getSelectedIndex() == 2) {
			MongoClient mongoClient = new MongoClient();
			DB database = mongoClient.getDB("tmar");
			DBCollection collection = database.getCollection("managers");
			
			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("name", txtManFindName.getText());
			DBCursor cursor = collection.find(whereQuery);
			while(cursor.hasNext()) {
				
				DBObject manager = cursor.next();
			    
			    String name = (String) manager.get("name");
			    String surname = (String) manager.get("surname");
			    String dateofbirth = (String) manager.get("dateofbirth");
			    String currentteam = (String) manager.get("currentteam");
			    String previousteam = (String) manager.get("previousteam");
			    String transferfee = (String) manager.get("transferfee");
			    String amount = (String) manager.get("amount");
			    rdbtnInHand.setEnabled(true);
			    rdbtnNotInHand.setEnabled(true);
			    if(transferfee.equals("In Hand")) {
			    	rdbtnInHand.setSelected(true);
			    }else if(transferfee.equals("Not In Hand")){
			    	rdbtnNotInHand.setSelected(true);
			    }
			    txtManName.setEnabled(true);
			    txtManName.setText(name);
			    txtManSurname.setEnabled(true);
			    txtManSurname.setText(surname);
			    txtManDoB.setEnabled(true);
			    txtManDoB.setText(dateofbirth);
			    txtManCTeam.setEnabled(true);
			    txtManCTeam.setText(currentteam);
			    txtManPTeam.setEnabled(true);
			    txtManPTeam.setText(previousteam);
			    txtManAmount.setEnabled(true);
			    txtManAmount.setText(amount);
			    if(!txtManName.getText().equals("")) {
			    	objectId = (Object) manager.get("_id");
			    }

			}

			mongoClient.close();
			
		}else if(tabbedPane.getSelectedIndex() == 1) {
			MongoClient mongoClient = new MongoClient();
			DB database = mongoClient.getDB("tmar");
			DBCollection collection = database.getCollection("players");
			
			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("name", txtPFind.getText());
			DBCursor cursor = collection.find(whereQuery);
			while(cursor.hasNext()) {
				
				DBObject player = cursor.next();
			    
			    String name = (String) player.get("name");
			    String surname = (String) player.get("surname");
			    String dateofbirth = (String) player.get("dateofbirth");
			    String currentteam = (String) player.get("currentteam");
			    String previousteam = (String) player.get("previousteam");
			    String status = (String) player.get("status");
			    String realteam = (String) player.get("realteam");
			    String dominantfoot = (String) player.get("dominantfoot");
			    String injuries = (String) player.get("injuries");
			    String transferfee = (String) player.get("transferfee");
			    String amount = (String) player.get("amount");			    
			    rdbtnInHand_1.setEnabled(true);
			    rdbtnNotInHand_1.setEnabled(true);
			    if(transferfee.equals("In Hand")) {
			    	rdbtnInHand_1.setSelected(true);
			    }else if(transferfee.equals("Not In Hand")){
			    	rdbtnNotInHand_1.setSelected(true);
			    }
			    rdbtnLeft.setEnabled(true);
			    rdbtnRight.setEnabled(true);
			    rdbtnBoth.setEnabled(true);
			    if(dominantfoot.equals("Left")) {
			    	rdbtnLeft.setSelected(true);
			    }else if(dominantfoot.equals("Right")){
			    	rdbtnRight.setSelected(true);
			    }else if(dominantfoot.equals("Both")) {
			    	rdbtnBoth.setSelected(true);
			    }
			    rdbtnBought.setEnabled(true);
			    rdbtnRented.setEnabled(true);
			    if(status.equals("Bought")) {
			    	rdbtnBought.setSelected(true);
			    }else if(status.equals("Rented")){
			    	rdbtnRented.setSelected(true);
			    }
			    txtPName.setEnabled(true);
			    txtPName.setText(name);
			    txtPSurname.setEnabled(true);
			    txtPSurname.setText(surname);
			    txtfPDoB.setEnabled(true);
			    txtfPDoB.setText(dateofbirth);
			    txtPCurrentTeam.setEnabled(true);
			    txtPCurrentTeam.setText(currentteam);
			    txtPPrevTeams.setEnabled(true);
			    txtPPrevTeams.setText(previousteam);
			    txtPRealTeam.setEnabled(true);
			    txtPRealTeam.setText(realteam);
			    txtPAmount.setEnabled(true);
			    txtPAmount.setText(amount);
			    txtaPInjuries.setEnabled(true);
			    txtaPInjuries.setText(injuries);
			    if(!txtPName.getText().equals("")) {
			    	objectId = (Object) player.get("_id");
			    }

			}

			mongoClient.close();
			
		}
		
		else if(tabbedPane.getSelectedIndex() == 0) {
			MongoClient mongoClient = new MongoClient();
			DB database = mongoClient.getDB("tmar");
			DBCollection collection = database.getCollection("teams");
			
			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("name", txtTFind.getText());
			DBCursor cursor = collection.find(whereQuery);
			while(cursor.hasNext()) {
				
				DBObject team = cursor.next();
			    
			    String name = (String) team.get("name");
			    String president = (String) team.get("president");
			    String city = (String) team.get("city");
			    String stadium = (String) team.get("stadium");
			    String championshipyears = (String) team.get("championshipyears");
			    String cups = (String) team.get("cups");
			    String colors = (String) team.get("colors");
			    String formationdate = (String) team.get("formationdate");
			    String league = (String) team.get("league");
			    String lastmatch = (String) team.get("lastmatch");
			    String score = (String) team.get("score");
			 
			    txtTName.setEnabled(true);
			    txtTName.setText(name);
			    txtTPres.setEnabled(true);
			    txtTPres.setText(president);
			    txtTCity.setEnabled(true);
			    txtTCity.setText(city);
			    txtTStadium.setEnabled(true);
			    txtTStadium.setText(stadium);
			    txtTChampYears.setEnabled(true);
			    txtTChampYears.setText(championshipyears);
			    txtTCups.setEnabled(true);
			    txtTCups.setText(cups);
			    txtTColors.setEnabled(true);
			    txtTColors.setText(colors);
			    ftxtTFormDate.setEnabled(true);
			    ftxtTFormDate.setText(formationdate);
			    txtTLeague.setEnabled(true);
			    txtTLeague.setText(league);
			    txtTLastMatch.setEnabled(true);
			    txtTLastMatch.setText(lastmatch);
			    txtTScore.setEnabled(true);
			    txtTScore.setText(score);
			    
			    if(!txtTName.getText().equals("")) {
			    	objectId = (Object) team.get("_id");
			    }

			}

			mongoClient.close();
			
		}
	}
	
	public void mongoDatabaseUpdate() throws Exception {
		if(tabbedPane.getSelectedIndex() == 3) {
			MongoClient mongoClient = new MongoClient();
			DB database = mongoClient.getDB("tmar");
			DBCollection collection = database.getCollection("presidents");
			
			BasicDBObject updateFields = new BasicDBObject();
			updateFields.append("name", txtPresName.getText());
			updateFields.append("surname", txtPresSurname.getText());
			updateFields.append("dateofbirth", txtPresDoB.getText());
			updateFields.append("team", txtPresTeam.getText());
			BasicDBObject setQuery = new BasicDBObject();
			setQuery.append("$set", updateFields);
			BasicDBObject searchQuery = new BasicDBObject().append("_id", objectId);
			collection.update(searchQuery, setQuery);
			mongoClient.close();
		}
		
		else if(tabbedPane.getSelectedIndex() == 2) {
			MongoClient mongoClient = new MongoClient();
			DB database = mongoClient.getDB("tmar");
			DBCollection collection = database.getCollection("managers");
			
			BasicDBObject updateFields = new BasicDBObject();
			updateFields.append("name", txtManName.getText());
			updateFields.append("surname", txtManSurname.getText());
			updateFields.append("dateofbirth", txtManDoB.getText());
			updateFields.append("currentteam", txtManCTeam.getText());
			updateFields.append("previousteam", txtManPTeam.getText());
			updateFields.append("transferfee", getSelectedButtonText(feeMButtons));
			updateFields.append("amount", txtManAmount.getText());
			BasicDBObject setQuery = new BasicDBObject();
			setQuery.append("$set", updateFields);
			BasicDBObject searchQuery = new BasicDBObject().append("_id", objectId);
			collection.update(searchQuery, setQuery);
			
			mongoClient.close();
		}
		else if(tabbedPane.getSelectedIndex() == 1) {
			MongoClient mongoClient = new MongoClient();
			DB database = mongoClient.getDB("tmar");
			DBCollection collection = database.getCollection("players");
			
			BasicDBObject updateFields = new BasicDBObject();
			updateFields.append("name", txtPName.getText());
			updateFields.append("surname", txtPSurname.getText());
			updateFields.append("dateofbirth", txtfPDoB.getText());
			updateFields.append("currentteam", txtPCurrentTeam.getText());
			updateFields.append("previousteam", txtPPrevTeams.getText());
			updateFields.append("status", getSelectedButtonText(bgrentorbought));
			updateFields.append("realteam", txtPRealTeam.getText());
			updateFields.append("dominantfoot", getSelectedButtonText(bgleftrightboth));
			updateFields.append("transferfee", getSelectedButtonText(bginhandornot));
			updateFields.append("amount", txtPAmount.getText());
			updateFields.append("injuries", txtaPInjuries.getText());
			BasicDBObject setQuery = new BasicDBObject();
			setQuery.append("$set", updateFields);
			BasicDBObject searchQuery = new BasicDBObject().append("_id", objectId);
			collection.update(searchQuery, setQuery);
			
			mongoClient.close();
			
		}
		else if(tabbedPane.getSelectedIndex() == 0) {
			MongoClient mongoClient = new MongoClient();
			DB database = mongoClient.getDB("tmar");
			DBCollection collection = database.getCollection("teams");
			
			BasicDBObject updateFields = new BasicDBObject();
			updateFields.append("name", txtTName.getText());
			updateFields.append("president", txtTPres.getText());
			updateFields.append("city", txtTCity.getText());
			updateFields.append("stadium", txtTStadium.getText());
			updateFields.append("championshipyears", txtTChampYears.getText());
			updateFields.append("cups", txtTCups.getText());
			updateFields.append("colors", txtTColors.getText());
			updateFields.append("formationdate", ftxtTFormDate.getText());
			updateFields.append("league", txtTLeague.getText());
			updateFields.append("lastmatch", txtTLastMatch.getText());
			updateFields.append("score", txtTScore.getText());
			
			BasicDBObject setQuery = new BasicDBObject();
			setQuery.append("$set", updateFields);
			BasicDBObject searchQuery = new BasicDBObject().append("_id", objectId);
			collection.update(searchQuery, setQuery);
			mongoClient.close();
		}
			
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
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
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
		lblCity.setBounds(52, 173, 67, 19);
		pTeam.add(lblCity);
		
		JLabel lblStadium = new JLabel("Stadium :");
		lblStadium.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblStadium.setBounds(52, 221, 82, 19);
		pTeam.add(lblStadium);
		
		JLabel lblFormationDate = new JLabel("Formation Date :");
		lblFormationDate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblFormationDate.setBounds(455, 173, 129, 19);
		pTeam.add(lblFormationDate);
		
		JLabel lblPresident = new JLabel("President :");
		lblPresident.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPresident.setBounds(52, 132, 97, 19);
		pTeam.add(lblPresident);
		
		JLabel lblChampionshipYears = new JLabel("Championship Years : ");
		lblChampionshipYears.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblChampionshipYears.setBounds(53, 270, 171, 19);
		pTeam.add(lblChampionshipYears);
		
		JLabel lblLeague = new JLabel("League :");
		lblLeague.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblLeague.setBounds(455, 221, 67, 19);
		pTeam.add(lblLeague);
		
		JLabel lblCups = new JLabel("Cups :");
		lblCups.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblCups.setBounds(52, 329, 82, 19);
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
					mongoDatabaseadd();
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
		txtTPres.setBounds(155, 134, 163, 19);
		pTeam.add(txtTPres);
		
		txtTCity = new JTextField();
		lblCity.setLabelFor(txtTCity);
		txtTCity.setColumns(10);
		txtTCity.setBounds(155, 175, 163, 19);
		pTeam.add(txtTCity);
		
		txtTStadium = new JTextField();
		lblStadium.setLabelFor(txtTStadium);
		txtTStadium.setColumns(10);
		txtTStadium.setBounds(155, 223, 163, 19);
		pTeam.add(txtTStadium);
		
		txtTChampYears = new JTextField();
		lblChampionshipYears.setLabelFor(txtTChampYears);
		txtTChampYears.setColumns(10);
		txtTChampYears.setBounds(232, 272, 163, 19);
		pTeam.add(txtTChampYears);
		
		txtTCups = new JTextField();
		lblCups.setLabelFor(txtTCups);
		txtTCups.setColumns(10);
		txtTCups.setBounds(155, 331, 163, 19);
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
		
		JLabel lblTFind = new JLabel("Name :");
		lblTFind.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblTFind.setEnabled(false);
		lblTFind.setBounds(250, 31, 66, 35);
		pTeam.add(lblTFind);
		
		JButton btnTFind = new JButton("Find");
		btnTFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mongoDatabaseFind();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnTFind.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnTFind.setEnabled(false);
		btnTFind.setBounds(540, 40, 66, 19);
		pTeam.add(btnTFind);
		
		JButton btnTeamUpdate = new JButton("Update");
		btnTeamUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mongoDatabaseUpdate();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnTeamUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTeamUpdate.setEnabled(false);
		btnTeamUpdate.setBounds(523, 387, 117, 35);
		pTeam.add(btnTeamUpdate);
		
		JCheckBox chckboxTFind = new JCheckBox("Find");
		chckboxTFind.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckboxTFind.setBounds(52, 17, 60, 21);
		pTeam.add(chckboxTFind);		
		chckboxTFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(chckboxTFind.isSelected()) {
					txtTName.setEnabled(false);
					txtTName.setText("");
					txtTChampYears.setEnabled(false);
					txtTChampYears.setText("");
					txtTCity.setEnabled(false);
					txtTCity.setText("");
					txtTColors.setEnabled(false);
					txtTColors.setText("");
					txtTCups.setEnabled(false);
					txtTCups.setText("");
					txtTLastMatch.setEnabled(false);
					txtTLastMatch.setText("");
					txtTLeague.setEnabled(false);
					txtTLeague.setText("");
					txtTPres.setEnabled(false);
					txtTPres.setText("");
					txtTScore.setEnabled(false);
					txtTScore.setText("");
					txtTStadium.setEnabled(false);
					txtTStadium.setText("");
					ftxtTFormDate.setEnabled(false);
					ftxtTFormDate.setText("");
					lblName_1.setEnabled(false);
					lblChampionshipYears.setEnabled(false);
					lblCity.setEnabled(false);
					lblColors.setEnabled(false);
					lblCups.setEnabled(false);
					lblLastMatch.setEnabled(false);
					lblLeague.setEnabled(false);
					lblPresident.setEnabled(false);
					lblSrore.setEnabled(false);
					lblStadium.setEnabled(false);
					lblFormationDate.setEnabled(false);
					lblTFind.setEnabled(true);
					txtTFind.setEnabled(true);
					btnTFind.setEnabled(true);
					btnTSubmit.setEnabled(false);
					btnTeamUpdate.setEnabled(true);
				}else {
					txtTName.setEnabled(true);
					txtTName.setText("");
					txtTChampYears.setEnabled(true);
					txtTChampYears.setText("");
					txtTCity.setEnabled(true);
					txtTCity.setText("");
					txtTColors.setEnabled(true);
					txtTColors.setText("");
					txtTCups.setEnabled(true);
					txtTCups.setText("");
					txtTLastMatch.setEnabled(true);
					txtTLastMatch.setText("");
					txtTLeague.setEnabled(true);
					txtTLeague.setText("");
					txtTPres.setEnabled(true);
					txtTPres.setText("");
					txtTScore.setEnabled(true);
					txtTScore.setText("");
					txtTStadium.setEnabled(true);
					txtTStadium.setText("");
					ftxtTFormDate.setEnabled(true);
					ftxtTFormDate.setText("");
					lblName_1.setEnabled(true);
					lblChampionshipYears.setEnabled(true);
					lblCity.setEnabled(true);
					lblColors.setEnabled(true);
					lblCups.setEnabled(true);
					lblLastMatch.setEnabled(true);
					lblLeague.setEnabled(true);
					lblPresident.setEnabled(true);
					lblSrore.setEnabled(true);
					lblStadium.setEnabled(true);
					lblFormationDate.setEnabled(true);
					lblTFind.setEnabled(false);
					txtTFind.setEnabled(false);
					txtTFind.setText("");
					btnTFind.setEnabled(false);
					btnTSubmit.setEnabled(true);
					btnTeamUpdate.setEnabled(false);
				}
			}
		});
		
		
		txtTFind = new JTextField();
		txtTFind.setEnabled(false);
		txtTFind.setColumns(10);
		txtTFind.setBounds(335, 41, 195, 19);
		pTeam.add(txtTFind);
		
		JPanel pPlayer = new JPanel();
		tabbedPane.addTab("Player", null, pPlayer, null);
		pPlayer.setLayout(null);
		
		JLabel lblPName = new JLabel("Name :");
		lblPName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPName.setBounds(40, 75, 57, 13);
		pPlayer.add(lblPName);
		
		JLabel lblPSurname = new JLabel("Surname :");
		lblPSurname.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPSurname.setBounds(40, 108, 90, 13);
		pPlayer.add(lblPSurname);
		
		JLabel lblPDateOfBirth = new JLabel("Date of Birth :");
		lblPDateOfBirth.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPDateOfBirth.setBounds(40, 150, 114, 13);
		pPlayer.add(lblPDateOfBirth);
		
		JLabel lblPCurrTeam = new JLabel("Current Team :");
		lblPCurrTeam.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPCurrTeam.setBounds(40, 184, 114, 13);
		pPlayer.add(lblPCurrTeam);
		
		JLabel lblPPrevTeams = new JLabel("Previous Teams :");
		lblPPrevTeams.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPPrevTeams.setBounds(40, 223, 136, 13);
		pPlayer.add(lblPPrevTeams);
		
		JLabel lblPStatus = new JLabel("Status :");
		lblPStatus.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPStatus.setBounds(419, 73, 77, 13);
		pPlayer.add(lblPStatus);
		
		JLabel lblPRealteam = new JLabel("Real Team :");
		lblPRealteam.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPRealteam.setBounds(419, 114, 90, 13);
		pPlayer.add(lblPRealteam);
		
		JLabel lblPDomFoot = new JLabel("Dominant Foot :");
		lblPDomFoot.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPDomFoot.setBounds(419, 148, 124, 13);
		pPlayer.add(lblPDomFoot);
		
		JLabel lblPTransFee = new JLabel("Transfer Fee :");
		lblPTransFee.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPTransFee.setBounds(419, 186, 120, 13);
		pPlayer.add(lblPTransFee);
		
		JLabel lblPInjuries = new JLabel("Injuries :");
		lblPInjuries.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPInjuries.setBounds(40, 252, 77, 13);
		pPlayer.add(lblPInjuries);
		
		txtPName = new JTextField();
		txtPName.setBounds(182, 74, 154, 19);
		pPlayer.add(txtPName);
		txtPName.setColumns(10);
		
		txtPSurname = new JTextField();
		txtPSurname.setColumns(10);
		txtPSurname.setBounds(182, 107, 154, 19);
		pPlayer.add(txtPSurname);
		
		txtPCurrentTeam = new JTextField();
		txtPCurrentTeam.setColumns(10);
		txtPCurrentTeam.setBounds(182, 183, 154, 19);
		pPlayer.add(txtPCurrentTeam);
		
		txtPPrevTeams = new JTextField();
		txtPPrevTeams.setColumns(10);
		txtPPrevTeams.setBounds(182, 222, 154, 19);
		pPlayer.add(txtPPrevTeams);
		
		txtPRealTeam = new JTextField();
		txtPRealTeam.setColumns(10);
		txtPRealTeam.setBounds(549, 107, 173, 19);
		pPlayer.add(txtPRealTeam);
		txtPRealTeam.setEnabled(false);
		
		JLabel lblAmount_1 = new JLabel("Amount (\u20AC) :");
		lblAmount_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblAmount_1.setBounds(419, 223, 99, 13);
		pPlayer.add(lblAmount_1);
		
		txtPAmount = new JTextField();
		txtPAmount.setColumns(10);
		txtPAmount.setBounds(549, 222, 173, 19);
		pPlayer.add(txtPAmount);
		txtPAmount.setEnabled(false);
		
		txtaPInjuries = new JTextArea();
		txtaPInjuries.setLineWrap(true);
		txtaPInjuries.setBounds(170, 254, 173, 103);
		pPlayer.add(txtaPInjuries);
		
		
		rdbtnBought = new JRadioButton("Bought");
		rdbtnBought.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnBought.setSelected(true);
		rdbtnBought.setBounds(549, 72, 63, 21);
		pPlayer.add(rdbtnBought);
		
		rdbtnRented = new JRadioButton("Rented");
		rdbtnRented.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnRented.setBounds(648, 73, 74, 21);
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
		
		rdbtnLeft = new JRadioButton("Left");
		rdbtnLeft.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnLeft.setSelected(true);
		rdbtnLeft.setBounds(549, 148, 47, 21);
		pPlayer.add(rdbtnLeft);
		
		rdbtnRight = new JRadioButton("Right");
		rdbtnRight.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnRight.setBounds(598, 148, 63, 21);
		pPlayer.add(rdbtnRight);
		
		rdbtnBoth = new JRadioButton("Both");
		rdbtnBoth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnBoth.setBounds(663, 148, 63, 21);
		pPlayer.add(rdbtnBoth);
		bgleftrightboth.add(rdbtnLeft);
		bgleftrightboth.add(rdbtnRight);
		bgleftrightboth.add(rdbtnBoth);
		
		rdbtnInHand_1 = new JRadioButton("In Hand");
		rdbtnInHand_1.setSelected(true);
		rdbtnInHand_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnInHand_1.setBounds(549, 182, 74, 21);
		pPlayer.add(rdbtnInHand_1);
		
		rdbtnNotInHand_1 = new JRadioButton("Not In Hand");
		rdbtnNotInHand_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnNotInHand_1.setBounds(625, 182, 97, 21);
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
		
		JLabel lblManName = new JLabel("Name :");
		lblManName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblManName.setBounds(229, 112, 75, 26);
		pManager.add(lblManName);
		
		JLabel lblManSurname = new JLabel("Surname :");
		lblManSurname.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblManSurname.setBounds(229, 148, 92, 26);
		pManager.add(lblManSurname);
		
		JLabel lblManDoB = new JLabel("Date of Birth :");
		lblManDoB.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblManDoB.setBounds(229, 184, 114, 26);
		pManager.add(lblManDoB);
		
		JLabel lblManCTeam = new JLabel("Current Team :");
		lblManCTeam.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblManCTeam.setBounds(229, 220, 114, 26);
		pManager.add(lblManCTeam);
		
		JLabel lblManPTeams = new JLabel("Previous Teams : ");
		lblManPTeams.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblManPTeams.setBounds(229, 256, 135, 26);
		pManager.add(lblManPTeams);
		
		JLabel lblManTransferFee = new JLabel("Transfer Fee : ");
		lblManTransferFee.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblManTransferFee.setBounds(229, 292, 135, 26);
		pManager.add(lblManTransferFee);
		
		rdbtnInHand = new JRadioButton("In Hand");
		rdbtnInHand.setSelected(true);
		rdbtnInHand.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnInHand.setBounds(408, 297, 75, 21);
		pManager.add(rdbtnInHand);
		
		rdbtnNotInHand = new JRadioButton("Not In Hand");
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
		
		JLabel lblManAmount = new JLabel("Amount (\u20AC) : ");
		lblManAmount.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblManAmount.setBounds(229, 328, 135, 26);
		pManager.add(lblManAmount);
		
		JButton btnManSubmit = new JButton("Submit");
		btnManSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mongoDatabaseadd();
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
					mongoDatabaseadd();
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
		txtfPDoB.setBounds(182, 149, 154, 19);
		pPlayer.add(txtfPDoB);
		
		JLabel lblPFind = new JLabel("Name :");
		lblPFind.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblPFind.setEnabled(false);
		lblPFind.setBounds(225, 20, 66, 35);
		pPlayer.add(lblPFind);
		
		txtPFind = new JTextField();
		txtPFind.setEnabled(false);
		txtPFind.setColumns(10);
		txtPFind.setBounds(310, 30, 195, 19);
		pPlayer.add(txtPFind);
		
		JButton btnPFind = new JButton("Find");
		btnPFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mongoDatabaseFind();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPFind.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnPFind.setEnabled(false);
		btnPFind.setBounds(515, 29, 66, 19);
		pPlayer.add(btnPFind);
		
		JButton btnPUpdate = new JButton("Update");
		btnPUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mongoDatabaseUpdate();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPUpdate.setEnabled(false);
		btnPUpdate.setBounds(523, 387, 117, 35);
		pPlayer.add(btnPUpdate);
		btnManSubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnManSubmit.setBounds(323, 387, 117, 35);
		pManager.add(btnManSubmit);
		
		JCheckBox chckboxPFind = new JCheckBox("Find");
		chckboxPFind.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckboxPFind.setBounds(27, 6, 60, 21);
		pPlayer.add(chckboxPFind);
		chckboxPFind.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(chckboxPFind.isSelected()) {
					txtPName.setEnabled(false);
					txtPName.setText("");
					txtPSurname.setEnabled(false);
					txtPSurname.setText("");
					txtfPDoB.setEnabled(false);
					txtfPDoB.setText("");
					txtPCurrentTeam.setEnabled(false);
					txtPCurrentTeam.setText("");
					txtPPrevTeams.setEnabled(false);
					txtPPrevTeams.setText("");
					txtaPInjuries.setEnabled(false);
					txtaPInjuries.setText("");
					rdbtnBought.setEnabled(false);
					rdbtnRented.setEnabled(false);
					txtPRealTeam.setEnabled(false);
					txtPRealTeam.setText("");
					rdbtnLeft.setEnabled(false);
					rdbtnRight.setEnabled(false);
					rdbtnBoth.setEnabled(false);
					rdbtnInHand_1.setEnabled(false);
					rdbtnNotInHand_1.setEnabled(false);
					txtPAmount.setEnabled(false);
					lblPName.setEnabled(false);
					lblPSurname.setEnabled(false);
					lblPDateOfBirth.setEnabled(false);
					lblPCurrTeam.setEnabled(false);
					lblPPrevTeams.setEnabled(false);
					lblPInjuries.setEnabled(false);
					lblPStatus.setEnabled(false);
					lblPRealteam.setEnabled(false);
					lblPDomFoot.setEnabled(false);
					lblPTransFee.setEnabled(false);
					lblAmount_1.setEnabled(false);
					btnPSubmit.setEnabled(false);
					lblPFind.setEnabled(true);
					txtPFind.setEnabled(true);
					btnPFind.setEnabled(true);
					btnPUpdate.setEnabled(true);
				}else {
					txtPName.setEnabled(true);
					txtPName.setText("");
					txtPSurname.setEnabled(true);
					txtPSurname.setText("");
					txtfPDoB.setEnabled(true);
					txtfPDoB.setText("");
					txtPCurrentTeam.setEnabled(true);
					txtPCurrentTeam.setText("");
					txtPPrevTeams.setEnabled(true);
					txtPPrevTeams.setText("");
					txtaPInjuries.setEnabled(true);
					txtaPInjuries.setText("");
					rdbtnBought.setEnabled(true);
					rdbtnRented.setEnabled(true);
					txtPRealTeam.setEnabled(true);
					txtPRealTeam.setText("");
					rdbtnLeft.setEnabled(true);
					rdbtnRight.setEnabled(true);
					rdbtnBoth.setEnabled(true);
					rdbtnInHand_1.setEnabled(true);
					rdbtnNotInHand_1.setEnabled(true);
					txtPAmount.setEnabled(true);
					lblPName.setEnabled(true);
					lblPSurname.setEnabled(true);
					lblPDateOfBirth.setEnabled(true);
					lblPCurrTeam.setEnabled(true);
					lblPPrevTeams.setEnabled(true);
					lblPInjuries.setEnabled(true);
					lblPStatus.setEnabled(true);
					lblPRealteam.setEnabled(true);
					lblPDomFoot.setEnabled(true);
					lblPTransFee.setEnabled(true);
					lblAmount_1.setEnabled(true);
					btnPSubmit.setEnabled(true);
					lblPFind.setEnabled(false);
					txtPFind.setEnabled(false);
					txtPFind.setText("");
					btnPFind.setEnabled(false);
					btnPUpdate.setEnabled(false);
				}
				
			}
		});
		
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
		
		JLabel lblManFind = new JLabel("Name :");
		lblManFind.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblManFind.setEnabled(false);
		lblManFind.setBounds(243, 42, 66, 35);
		pManager.add(lblManFind);
		

		JButton btnManFind = new JButton("Find");
		btnManFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mongoDatabaseFind();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnManFind.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnManFind.setEnabled(false);
		btnManFind.setBounds(533, 51, 66, 19);
		pManager.add(btnManFind);
		
		JButton btnManUpdate = new JButton("Update");
		btnManUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mongoDatabaseUpdate();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnManUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnManUpdate.setEnabled(false);
		btnManUpdate.setBounds(523, 387, 117, 35);
		pManager.add(btnManUpdate);
		
		JCheckBox chckboxManFind = new JCheckBox("Find");
		chckboxManFind.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckboxManFind.setBounds(45, 28, 60, 21);
		pManager.add(chckboxManFind);
		chckboxManFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(chckboxManFind.isSelected()) {
					txtManName.setEnabled(false);
					txtManName.setText("");
					txtManSurname.setEnabled(false);
					txtManSurname.setText("");
					txtManCTeam.setEnabled(false);
					txtManCTeam.setText("");
					txtManPTeam.setEnabled(false);
					txtManPTeam.setText("");
					txtManDoB.setEnabled(false);
					txtManDoB.setText("");
					rdbtnInHand.setEnabled(false);
					rdbtnNotInHand.setEnabled(false);
					rdbtnInHand.setSelected(true);
					txtManAmount.setEnabled(false);
					txtManAmount.setText("");
					txtManFindName.setEnabled(true);
					lblManFind.setEnabled(true);
					btnManFind.setEnabled(true);
					btnManSubmit.setEnabled(false);
					btnManUpdate.setEnabled(true);
					lblManName.setEnabled(false);
					lblManSurname.setEnabled(false);
					lblManCTeam.setEnabled(false);
					lblManPTeams.setEnabled(false);
					lblManTransferFee.setEnabled(false);
					lblManDoB.setEnabled(false);
					lblManAmount.setEnabled(false);
				}else {
					txtManName.setEnabled(true);					
					txtManSurname.setEnabled(true);					
					txtManCTeam.setEnabled(true);					
					txtManPTeam.setEnabled(true);					
					txtManDoB.setEnabled(true);					
					rdbtnInHand.setEnabled(true);
					rdbtnNotInHand.setEnabled(true);
					rdbtnInHand.setSelected(true);
					txtManAmount.setEnabled(false);
					txtManFindName.setEnabled(false);
					txtManFindName.setText("");
					lblManFind.setEnabled(false);
					btnManFind.setEnabled(false);
					btnManSubmit.setEnabled(true);
					btnManUpdate.setEnabled(false);
					lblManName.setEnabled(true);
					lblManSurname.setEnabled(true);
					lblManCTeam.setEnabled(true);
					lblManPTeams.setEnabled(true);
					lblManTransferFee.setEnabled(true);
					lblManDoB.setEnabled(true);
					lblManAmount.setEnabled(true);
				}
			}
		});
		
		
		txtManFindName = new JTextField();
		txtManFindName.setEnabled(false);
		txtManFindName.setColumns(10);
		txtManFindName.setBounds(328, 52, 195, 19);
		pManager.add(txtManFindName);
		
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
					mongoDatabaseadd();
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
		btnPresUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mongoDatabaseUpdate();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPresUpdate.setEnabled(false);
		btnPresUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPresUpdate.setBounds(523, 387, 117, 35);
		pPres.add(btnPresUpdate);
		
		JButton btnPresFind = new JButton("Find");
		btnPresFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mongoDatabaseFind();
					
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
