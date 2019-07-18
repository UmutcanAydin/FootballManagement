package prog.tmarkt;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.MouseEvent;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class MultiData extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String namevalue;
	private String surnamevalue;
	private String dobvalue;
	private String teamvalue;
	static main mn = new main();
	DefaultTableModel model = new DefaultTableModel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MultiData dialog = new MultiData(mn.getTxtPresName().getText());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setResizable(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mongoDatabaseFindPresidents(String txtPres) throws Exception {
		MongoClient mongoClient = new MongoClient();
		DB database = mongoClient.getDB("tmar");
		DBCollection collection = database.getCollection("presidents");
		
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("name", txtPres);
		BasicDBObject projectQuery = new BasicDBObject();
		projectQuery.put("_id", 0);
		DBCursor cursor = collection.find(whereQuery,projectQuery);
		while(cursor.hasNext()) {
			
			DBObject president = cursor.next();
		    
		    String name = (String) president.get("name");
		    String surname = (String) president.get("surname");
		    String dateofbirth = (String) president.get("dateofbirth");
		    String team = (String) president.get("team");
		    
			model.addRow(new Object[]{name,surname,dateofbirth,team});
		   
		}	   
	}
	
	/**
	 * Create the dialog.
	 * @param txtPresNameString 
	 * @throws Exception 
	 */
	public MultiData(String txtPresNameString) throws Exception {
		setResizable(false);
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 763, 512);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 749, 474);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JTable table = new JTable(){
            //Implement table cell tool tips.           
            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);

                try {
                    tip = getValueAt(rowIndex, colIndex).toString();
                } catch (RuntimeException e1) {
                	
                }

                return tip;
            }
        };
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(model = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Surname", "DateofBirth", "Team"
			}
		));
		table.getColumnModel().getColumn(0).setResizable(false);
		table.setFillsViewportHeight(true);
		table.getTableHeader().setReorderingAllowed(false);
		
		mongoDatabaseFindPresidents(txtPresNameString);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 750, 426);
		contentPanel.add(scrollPane);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectedRow()>-1) {
				int column = 0;
				int row = table.getSelectedRow();
				namevalue = table.getModel().getValueAt(row, column).toString();
				column = 1;
				surnamevalue = table.getModel().getValueAt(row, column).toString();
				column = 2;
				dobvalue = table.getModel().getValueAt(row, column).toString();
				column = 3;
				teamvalue = table.getModel().getValueAt(row, column).toString();
				dispose();
				}
				
				
				
			}
		});
		btnSelect.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSelect.setBounds(612, 436, 103, 28);
		contentPanel.add(btnSelect);
		
	}

	public String getNamevalue() {
		return namevalue;
	}

	public void setNamevalue(String namevalue) {
		this.namevalue = namevalue;
	}

	public String getSurnamevalue() {
		return surnamevalue;
	}

	public void setSurnamevalue(String surnamevalue) {
		this.surnamevalue = surnamevalue;
	}

	public String getDobvalue() {
		return dobvalue;
	}

	public void setDobvalue(String dobvalue) {
		this.dobvalue = dobvalue;
	}

	public String getTeamvalue() {
		return teamvalue;
	}

	public void setTeamvalue(String teamvalue) {
		this.teamvalue = teamvalue;
	}



}
