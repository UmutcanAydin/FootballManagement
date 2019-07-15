package prog.tmarkt;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import javax.swing.JButton;
import java.awt.Font;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Players extends JDialog {
	private JTable table;
	private JTextField txtTShowPlayersName;
	private DefaultTableModel model;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Players dialog = new Players();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					dialog.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void mongoDatabaseFindPlayers() throws Exception {
		MongoClient mongoClient = new MongoClient();
		DB database = mongoClient.getDB("tmar");
		DBCollection collection = database.getCollection("players");
		
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("currentteam", txtTShowPlayersName.getText());
		BasicDBObject projectQuery = new BasicDBObject();
		projectQuery.put("_id", 0);
		DBCursor cursor = collection.find(whereQuery,projectQuery);
		while(cursor.hasNext()) {
			
			model.addRow(new Object[]{cursor.next().toString()});
		   
		}	   
	}

	/**
	 * Create the dialog.
	 */
	public Players() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setAlwaysOnTop(true);
		setBounds(100, 100, 764, 526);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 81, 750, 408);
		getContentPane().add(scrollPane);
		
		table = new JTable(model = new DefaultTableModel(new Object[][]{},new Object[]{"Players"}));
		table.setRowSelectionAllowed(false);
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
				
		txtTShowPlayersName = new JTextField();
		txtTShowPlayersName.setBounds(247, 32, 223, 19);
		getContentPane().add(txtTShowPlayersName);
		txtTShowPlayersName.setColumns(10);
		
		JButton btnTShowPlayerFind = new JButton("Find");
		btnTShowPlayerFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel tm = (DefaultTableModel) table.getModel();
					tm.setRowCount(0);
					mongoDatabaseFindPlayers();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnTShowPlayerFind.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTShowPlayerFind.setBounds(496, 32, 85, 21);
		getContentPane().add(btnTShowPlayerFind);
		
		JLabel lblNewLabel = new JLabel("Name of the Team :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(75, 33, 150, 13);
		getContentPane().add(lblNewLabel);

	}
}
