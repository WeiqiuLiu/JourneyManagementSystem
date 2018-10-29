



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
 * The station management frame
 * @author Group 28
 *
 */
@SuppressWarnings("serial")
public class Ad_stationman extends JFrame {
  
	/**
	 * The structure to add the JButton, JTable... to frame
	 */
	public Ad_stationman() {
		final Container container = getContentPane();
		final JButton driman_back=new JButton("Back");
		driman_back.addActionListener(new ActionListener() {////back to administrator entrance frame button listener
			public void actionPerformed(ActionEvent e) {
				dispose();
		  Ad_int ad_int=new Ad_int();
		  ad_int.ad_int();
				
			}

		});
		Vector<String> tableColumnNames = new Vector<String>();//Column name
		tableColumnNames.add("ID");
		tableColumnNames.add("Name");

		Vector<Vector<String>> tableValues = new Vector<Vector<String>>();
		for (int i = 0; i < station.stationlist.size(); i++) {//Add exist data to table
			Vector<String> vector = new Vector<String>();
			vector.add("" + i);
			vector.add("" + station.stationlist.get(i).getname());
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
		JLabel labelA = new JLabel("Name: ");
		final JTextField textFieldA = new JTextField(10);

		JButton buttonAdd = new JButton("Add");
		JButton buttonDel = new JButton("Delete");
		JButton buttonDeselected = new JButton("Deselect");
		panel.add(labelA);
		panel.add(textFieldA);
		panel.add(buttonAdd);
		panel.add(buttonDel);
		panel.add(buttonDeselected);
		panel.add(driman_back);
		buttonAdd.addActionListener(new ActionListener() {//add button listener

			@Override
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table.getSelectedRows();
				Vector<String> rowData = new Vector<String>();
				station d=new station(textFieldA.getText());
				if(d.save()==1){
				rowData.add(""+(station.stationlist.size()-1));
				rowData.add(d.getname());
				if (selectedRows.length == 0||selectedRows.length == 1) {
					defaultTableModel.addRow(rowData);
					textFieldA.setText(null);

				} else {
					JOptionPane.showMessageDialog(container,
							"Your operation is forbidden", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
				}else{
					JOptionPane.showMessageDialog(null, "station exists", "Error",JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		
		buttonDel.addActionListener(new ActionListener() {//delete button listener

			@Override
			public void actionPerformed(ActionEvent e) {
			
				int[] selectedRows = table.getSelectedRows();
															
		flag:	{for (int i = 0; i < selectedRows.length; i++) {
					if(selectedRows[i]==0){
						JOptionPane.showMessageDialog(null, "centrol station can't be remove", "Error",JOptionPane.ERROR_MESSAGE);
						break flag;
					}
					JOptionPane.showMessageDialog(null, "The route using this station will also be deleted");		
					ArrayList<route> kk=route.routelist;
					
					for(int j=0;j<kk.size();j++){
						int size=kk.get(j).getstation().size();
						for(int k=0;k<size;k++){
						if(kk.get(j).getstation().get(k).getname().equals(station.stationlist.get(selectedRows[i]).getname())){
							route.routelist.remove(kk.get(j));
						}
						}
					}
					defaultTableModel.removeRow(selectedRows[i] - i);
					station.stationlist.remove(selectedRows[i]);
					station.savestationlist();
					route.saveroutelist();
				}}
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
	public void ad_stationman() {
		Ad_stationman frame = new Ad_stationman();
		frame.setTitle("Station Information Management");
		frame.pack(); 
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}