
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
 * The journey assignment frame
 * @author Group28
 *
 */
@SuppressWarnings("serial")
public class Ad_ass extends JFrame {

	/**
	 * The structure to add Jbutton, Jtable... to frame
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Ad_ass() {
		
		 final Container container = getContentPane();
		final JButton jouman_back=new JButton("Back");
		jouman_back.addActionListener(new ActionListener() {//back button listener
			public void actionPerformed(ActionEvent e) {
				dispose();
		  Ad_int ad_int=new Ad_int();
		  ad_int.ad_int();	
			}
		});

		Vector<String> tableColumnNames = new Vector<String>();//Column name
		tableColumnNames.add("journey");
		tableColumnNames.add("route");
		tableColumnNames.add("time");
		tableColumnNames.add("driverID");
		tableColumnNames.add("trainID");
		
		
		Vector<Vector<String>> tableValues = new Vector<Vector<String>>();//Add exist data to table
		for (int i = 0; i < journey.journeylist.size(); i++) {
			Vector<String> vector = new Vector<String>();
			vector.add(""+i);
			vector.add("RouteID "+journey.journeylist.get(i).getroute().getid()+"    Destination "+journey.journeylist.get(i).getroute().getstation().get(journey.journeylist.get(i).getroute().getstation().size()-1).getname());                      //INSERT THE VALUSE  PRINT
			vector.add(journey.journeylist.get(i).gettime().get(0).gethour()+":"+journey.journeylist.get(i).gettime().get(0).getminute()+"-" + journey.journeylist.get(i).gettime().get(journey.journeylist.get(i).gettime().size()-1).gethour()+":"+journey.journeylist.get(i).gettime().get(journey.journeylist.get(i).gettime().size()-1).getminute());                               //vector<----vector row row 
			vector.add(journey.journeylist.get(i).getdriver().getname());
			vector.add(""+journey.journeylist.get(i).gettrain().gettrainid());
			
			tableValues.add(vector);
		}
		final DefaultTableModel defaultTableModel = new DefaultTableModel(
				tableValues, tableColumnNames);                    
		final JTable table = new JTable(defaultTableModel);//setting of the table atrribute
		  table.getTableHeader().setReorderingAllowed(false);  
		  table.getTableHeader().setResizingAllowed(false);  
		  table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		  String[] rID = new String[route.routelist.size()] ;
		  for(int i=0;i<route.routelist.size();i++){
			  rID[i]=""+i;
		  }
	        
		  
	        final JComboBox rcom = new JComboBox(rID);
	        
	        table.getColumnModel();
	        
	       
		JScrollPane scrollPane = new JScrollPane();   //panel
		scrollPane.setViewportView(table);
		container.add(scrollPane, BorderLayout.CENTER);
		JPanel panel = new JPanel();
		container.add(panel, BorderLayout.SOUTH);
		final JTextField textFieldA = new JTextField(10);
		JLabel labelC = new JLabel("routeID: ");
		JLabel labelD = new JLabel("Available Train: ");
		
		JButton buttonAdd = new JButton("Set time");
		JButton buttonDel = new JButton("Delecte");
		JButton buttonSet = new JButton("Save");
		JButton buttonDeselected = new JButton("Deselect");
		panel.add(labelC);
		panel.add(rcom);
		
		panel.add(buttonAdd);
		panel.add(textFieldA);
		textFieldA.setEditable(false);
		panel.add(buttonDel);
		panel.add(buttonDeselected);
		panel.add(jouman_back);
		
		buttonAdd.addActionListener(new ActionListener() {//Add journey button listen

			@Override
			public void actionPerformed(ActionEvent e) {
				buttonAdd.setEnabled(false);
				route r=route.routelist.get(Integer.parseInt(rcom.getSelectedItem().toString()));
				ArrayList<station> temp=new ArrayList<station>();
				for(int jj=0;jj<r.getstation().size();jj++){
					temp.add(r.getstation().get(jj));
				}
				for(int j=0;j<(r.getstation().size()-1);j++){
					temp.add(r.getstation().get(r.getstation().size()-2-j));
				}
				ArrayList<time> tt=new ArrayList<time>();
				for(int i=0;i<temp.size();i++){
					String a="";
					a=JOptionPane.showInputDialog("Enter the reaching time in type (hh:mm) for "+temp.get(i).getname());//Set time
					if(a==null){
						i--;
						JOptionPane.showMessageDialog(null, "Wrong", "Error",JOptionPane.ERROR_MESSAGE);
					}else{
						if(control.istime(a)){
							time t=control.changestring(a);
							tt.add(t);
						}else{
							i--;
							JOptionPane.showMessageDialog(null, "Wrong", "Error",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				if(control.checktt(tt)){
				textFieldA.setText(tt.get(0).gethour()+":"+tt.get(0).getminute()+"-"+tt.get(tt.size()-1).gethour()+":"+tt.get(tt.size()-1).getminute());
				journey j=new journey(r,tt);
				String[] dID=new String[j.checkdriver().size()];//return the available driver
		        String[] tID=new String[j.checktrain().size()];//return the avaliable train
				for(int i=0;i<j.checkdriver().size();i++){
					  dID[i]=j.checkdriver().get(i).getid()+"";
				  }
				
				if(dID.length==0){
					dID=new String[1];
					dID[0]="";
				}
				
				for(int i=0;i<j.checktrain().size();i++){
					  tID[i]=j.checktrain().get(i).gettrainid()+"";
				  }
				
				if(tID.length==0){
					tID=new String[1];
					tID[0]="";
				}
				final JComboBox com = new JComboBox(dID);
		        final JComboBox ttcom = new JComboBox(tID);

		        panel.remove(jouman_back);
		        
		        //panel.add(labelB);
		        panel.add(com);
		        panel.add(labelD);
		        panel.add(ttcom);
		        panel.add(buttonSet);
		        if((dID[0]=="")||(tID[0]=="")){
					buttonSet.setEnabled(false);
				}else{
					buttonSet.setEnabled(true);
				}
		        panel.add(jouman_back);
		        panel.repaint();
		        setVisible(true);
				
				buttonSet.addActionListener(new ActionListener() {//Save setting button listener
					public void actionPerformed(ActionEvent e) {
						driver d = null;
						for(int i=0;i<driver.driverlist.size();i++){
							if(Integer.parseInt(com.getSelectedItem().toString())==driver.driverlist.get(i).getid()){
								d=driver.driverlist.get(i);
							}
						}
						j.setdriver(d);
						train t = null;
						for(int jj=0;jj<train.trainlist.size();jj++){
							if(Integer.parseInt(ttcom.getSelectedItem().toString())==train.trainlist.get(jj).gettrainid()){
								t=train.trainlist.get(jj);
							}
						}
						j.settrain(t);
						if(j.save()==1){//Judge the new journey whether repeat
							Vector<String> rowData = new Vector<String>();
							rowData.add(""+journey.journeylist.size());
							rowData.add("RouteID "+j.getroute().getid()+"    Destination "+j.getroute().getstation().get(j.getroute().getstation().size()-1).getname()); 
							rowData.add(j.gettime().get(0).gethour()+":"+j.gettime().get(0).getminute()+"-"+j.gettime().get(j.gettime().size()-1).gethour()+":"+j.gettime().get(j.gettime().size()-1).getminute()); 
							rowData.add(""+j.getdriver().getid()); 
							rowData.add(""+j.gettrain().gettrainid()); 
							defaultTableModel.addRow(rowData);
					        panel.remove(com);
					        panel.remove(labelD);
					        panel.remove(ttcom);
					        panel.remove(buttonSet);
					        panel.add(buttonDel);
							panel.add(buttonDeselected);
							panel.add(jouman_back);
							buttonAdd.setEnabled(true);
					        panel.repaint();
					        setVisible(true);
						}else{
							JOptionPane.showMessageDialog(null, "journey save false", "Error",JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				}else{
					JOptionPane.showMessageDialog(null, "time input wrong", "Error",JOptionPane.ERROR_MESSAGE);
					buttonAdd.setEnabled(true);
				}
			}

		});
	
		
		

		buttonDel.addActionListener(new ActionListener() {//Delet journey button listener

			@Override
			public void actionPerformed(ActionEvent e) {
			
				int[] selectedRows = table.getSelectedRows(); // table
																
				for (int i = 0; i < selectedRows.length; i++) {   //REMOVE MORE ROWS
					defaultTableModel.removeRow(selectedRows[i]-i ); //2,3,4-->2,2,2 because if we delect 2, 3 will become 2,so -1
					journey.journeylist.remove(selectedRows[i]);
					journey.savedriverlist();
				}
			}

		});
		buttonDeselected.addActionListener(new ActionListener() {//Deselect element in table

			@Override
			public void actionPerformed(ActionEvent e) {
		
				table.clearSelection();
			}

		});
		/************************************************************************/
		scrollPane.addMouseListener(new MouseAdapter() {//scrollpane listener
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1
						&& e.getButton() == MouseEvent.BUTTON1) {
					table.clearSelection();
				}
			}
		});
	}

	
	/**
	 * Some function defined like frame setvisible
	 */
	public void ad_ass() {
		Ad_ass frame = new Ad_ass();
		frame.setTitle("Journey Assignment");
		frame.pack(); //Adjust
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000,500); 
		frame.setLocationRelativeTo(null); 
		frame.setVisible(true); //Display the window.
	}
}