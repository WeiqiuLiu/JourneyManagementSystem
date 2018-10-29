

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
 * The route management frame
 * @author Group28
 *
 */
@SuppressWarnings("serial")
public class Ad_routeman extends JFrame {
  
	/**
	 * The structure to add Jbutton, Jtable... to frame
	 */
	public Ad_routeman() {
		
		 
		final Container container = getContentPane();
		final JButton jouman_back=new JButton("Back");
		jouman_back.addActionListener(new ActionListener() {//back to administrator entrance frame button listener
			public void actionPerformed(ActionEvent e) {
				dispose();
		  Ad_int ad_int=new Ad_int();
		  ad_int.ad_int();
				
			}

		});
		Vector<String> tableColumnNames = new Vector<String>();//Column name
		tableColumnNames.add("routeID");
		tableColumnNames.add("Station 0");
		tableColumnNames.add("Station 1");
		tableColumnNames.add("Station 2");
		tableColumnNames.add("Station 3");
		tableColumnNames.add("Station 4");
		
		Vector<Vector<String>> tableValues = new Vector<Vector<String>>();
		for (int i = 0; i < route.routelist.size(); i++) {//Add exist data to table
			Vector<String> vector = new Vector<String>();
			vector.add(""+route.routelist.get(i).getid());
			for(int j=0;j<route.routelist.get(i).getstation().size();j++){
				vector.add(route.routelist.get(i).getstation().get(j).getname());
			}
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
		JLabel labelA = new JLabel("rID: ");
		final JTextField textFieldA = new JTextField(5);
		JLabel labelB = new JLabel("ST0: ");
		final JTextField textFieldB = new JTextField(10);
		JLabel labelC = new JLabel("ST1: ");
		final JTextField textFieldC = new JTextField(5);
		JLabel labelD = new JLabel("ST2: ");
		final JTextField textFieldD = new JTextField(5);
		JLabel labelE = new JLabel("ST3: ");
		final JTextField textFieldE = new JTextField(5);
		JLabel labelF = new JLabel("ST4: ");
		final JTextField textFieldF = new JTextField(5);
		JButton buttonAdd = new JButton("Add");
		JButton buttonDel = new JButton("Delecte");
		JButton buttonDeselected = new JButton("Deselect");
		panel.add(labelA);
		panel.add(textFieldA);
		panel.add(labelB);
		panel.add(textFieldB);
		textFieldB.setText("centralstation");
		panel.add(labelC);
		panel.add(textFieldC);
		panel.add(labelD);
		panel.add(textFieldD);
		panel.add(labelE);
		panel.add(textFieldE);
		panel.add(labelF);
		panel.add(textFieldF);
		panel.add(buttonAdd);
		panel.add(buttonDel);
		panel.add(buttonDeselected);
		panel.add(jouman_back);
		buttonAdd.addActionListener(new ActionListener() {//add button listener

			@Override
			public void actionPerformed(ActionEvent e) {
				table.getSelectedRows();
				Vector<String> rowData = new Vector<String>();
				ArrayList<station> slist=new ArrayList<station>();
				String a="",b = "",c="",d="",ee="";
				if(!(a=textFieldB.getText()).equals("")){
					slist.add(new station(a));
					if(!(b=textFieldC.getText()).equals("")){
						slist.add(new station(b));
						if(!(c=textFieldD.getText()).equals("")){
							slist.add(new station(c));
							if(!(d=textFieldE.getText()).equals("")){
								slist.add(new station(d));
								if(!(ee=textFieldF.getText()).equals("")){
									slist.add(new station(ee));
								}
							}
						}
					}
				}
				try{
				route r=new route(Integer.parseInt(textFieldA.getText()),slist);
				if(r.save()==1){
				rowData.add(""+r.getid());
				rowData.add(a); 
				rowData.add(b); 
				rowData.add(c); 
				rowData.add(d); 
				rowData.add(ee); 
				defaultTableModel.addRow(rowData);
				//textFieldA.setText(null);
				textFieldB.setText("centralstation");
				textFieldC.setText(null);
				textFieldD.setText(null);
				textFieldE.setText(null);
				textFieldF.setText(null);
				}else{
					JOptionPane.showMessageDialog(null, "Wrong", "Error",JOptionPane.ERROR_MESSAGE);
				}
				}catch(Exception eee){
					JOptionPane.showMessageDialog(null, "Enter ID in type int", "Error",JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		buttonDel.addActionListener(new ActionListener() {//delete button listener

			@Override
			public void actionPerformed(ActionEvent e) {
			
				int[] selectedRows = table.getSelectedRows();
				JOptionPane.showMessageDialog(null, "The journey using this route will also be deleted");													
				for (int i = 0; i < selectedRows.length; i++) {
					defaultTableModel.removeRow(selectedRows[i]-i );
					ArrayList<journey> jj=journey.journeylist;
					for(int j=0;j<jj.size();j++){
						if(jj.get(j).getroute().getid()==route.routelist.get(selectedRows[i]).getid()){
							journey.journeylist.remove(jj.get(j));
						}
					}
					route.routelist.remove(selectedRows[i]);
					route.saveroutelist();
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
		scrollPane.addMouseListener(new MouseAdapter() {//scrolpane listener
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
				textFieldB.requestFocus(); 
			}
		});
	}

	/**
	 * Some function defined like frame setvisible
	 */
	public void ad_jouman() {
		Ad_routeman frame = new Ad_routeman();
		frame.setTitle("Route Information Management");
		frame.pack();
   	frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); 
	}
}