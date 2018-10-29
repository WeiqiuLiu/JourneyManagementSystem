
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 * The driver management frame
 * @author Group28
 *
 */
@SuppressWarnings("serial")
public class Ad_driman extends JFrame {
	/**
	 * The structure to add the JButton, JTable... to frame
	 */
	public Ad_driman() {
		final Container container = getContentPane();
		final JButton driman_back=new JButton("Back");
		driman_back.addActionListener(new ActionListener() {//back button listener
			public void actionPerformed(ActionEvent e) {
				dispose();
		  Ad_int ad_int=new Ad_int();
		  ad_int.ad_int();
				
			}

		});
		Vector<String> tableColumnNames = new Vector<String>();//Column name
		tableColumnNames.add("ID");
		tableColumnNames.add("Name");
		tableColumnNames.add("Age");
		tableColumnNames.add("Gender");
		Vector<Vector<String>> tableValues = new Vector<Vector<String>>();
		for (int i = 0; i < driver.driverlist.size(); i++) {//Add exist data to table
			Vector<String> vector = new Vector<String>();
			vector.add("" + driver.driverlist.get(i).getid());                      
			vector.add("" + driver.driverlist.get(i).getname());
			vector.add("" + driver.driverlist.get(i).getage());
			vector.add("" + driver.driverlist.get(i).getgender());
			tableValues.add(vector);
		}
		final DefaultTableModel defaultTableModel = new DefaultTableModel(
				tableValues, tableColumnNames);                    
		final JTable table = new JTable(defaultTableModel);//setting of the table atrribute
		table.getTableHeader().setReorderingAllowed(false);  
		  table.getTableHeader().setResizingAllowed(false);  
		  table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		container.add(scrollPane, BorderLayout.CENTER);
		JPanel panel = new JPanel();
		container.add(panel, BorderLayout.SOUTH);
		JLabel labelA = new JLabel("ID: ");
		final JTextField textFieldA = new JTextField(10);
		JLabel labelB = new JLabel("Name: ");
		final JTextField textFieldB = new JTextField(10);
		JLabel labelC = new JLabel("Age: ");
		final JTextField textFieldC = new JTextField(5);
		JLabel labelD = new JLabel("Gender: ");
		final JTextField textFieldD = new JTextField(10);
		JButton buttonAdd = new JButton("Add");
		JButton buttonDel = new JButton("Delete");
		JButton buttonDeselected = new JButton("Deselect");
		panel.add(labelA);
		panel.add(textFieldA);
		panel.add(labelB);
		panel.add(textFieldB);
		panel.add(labelC);
		panel.add(textFieldC);
		panel.add(labelD);
		panel.add(textFieldD);
		panel.add(buttonAdd);
		panel.add(buttonDel);
		panel.add(buttonDeselected);
		panel.add(driman_back);
		buttonAdd.addActionListener(new ActionListener() {//Add button listener

			@Override
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table.getSelectedRows(); 
				Vector<String> rowData = new Vector<String>();
				try{
				driver d=new driver(Integer.parseInt(textFieldA.getText()),textFieldB.getText(),Integer.parseInt(textFieldC.getText()),Integer.parseInt(textFieldD.getText()));
				if(d.save()==1){
				rowData.add(""+d.getid());
				rowData.add(d.getname()); 
				rowData.add(d.getage()+""); 
				rowData.add(d.getgender());//get the data in text field and print to table
				if (selectedRows.length == 0||selectedRows.length == 1) {//clear text field after add button click
					defaultTableModel.addRow(rowData);
					textFieldA.setText(null);
					textFieldB.setText(null);
					textFieldC.setText(null);
					textFieldD.setText(null);
				}else {
					JOptionPane.showMessageDialog(container,
							"Your operation is forbidden", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
				}else{
					JOptionPane.showMessageDialog(null, "ID exists", "Error",JOptionPane.ERROR_MESSAGE);
				}
				}catch(Exception ee){
					JOptionPane.showMessageDialog(null, "Please enter int in ID, AGE and Gender", "Error",JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		
		buttonDel.addActionListener(new ActionListener() {//delete button listener

			@Override
			public void actionPerformed(ActionEvent e) {
			
				int[] selectedRows = table.getSelectedRows();
				JOptionPane.showMessageDialog(null, "The journey using this driver will also be deleted");												
				for (int i = 0; i < selectedRows.length; i++) {
					defaultTableModel.removeRow(selectedRows[i] - i);
					ArrayList<journey> jj=journey.journeylist;
					for(int j=0;j<jj.size();j++){
						if(jj.get(j).getdriver().getid()==driver.driverlist.get(selectedRows[i]).getid()){
							journey.journeylist.remove(jj.get(j));
						}
					}
					driver.driverlist.remove(selectedRows[i]);
					driver.savedriverlist();
					journey.savedriverlist();
				}
			}

		});
		buttonDeselected.addActionListener(new ActionListener() {//deselect button listener

			@Override
			public void actionPerformed(ActionEvent e) {
		
				table.clearSelection();
			}

		});

		scrollPane.addMouseListener(new MouseAdapter() {//scrollPane listener
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1
						&& e.getButton() == MouseEvent.BUTTON1) {
					table.clearSelection();
				}
			}
		});
		
		
		addWindowFocusListener(new WindowAdapter() {// make the text field focused every time the window is activated

			@Override
			public void windowGainedFocus(WindowEvent e) {
				textFieldA.requestFocus(); 
			}
			
		});


		
		
		
	}

	/**
	 * Some function defined like frame setvisible
	 */
	public void ad_driman() {
		// TODO Auto-generated method stub
		Ad_driman frame = new Ad_driman();
		frame.setTitle("Driver Information Management");
		frame.pack(); //Realize the components.
  // 	frame.setBounds(10, 10, 600, 500);
   	frame.setLocationRelativeTo(null);
//		textFieldA.requestFocus();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); //Display the window.
	}

}