

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 * Driver entrance frame
 * @author Group28
 *
 */
public class Dri_int {
	
JFrame dri_int=new JFrame();
Table_Model model = new Table_Model(20);
JTable status = new JTable(model);
train tt=null;
JLabel dri_l1=new JLabel("Your assignment is as follow: ");
JLabel dri_l2=new JLabel("You can click on the first button to report error and apply for remote controlling");
Font wordFont = new Font("times Roman", Font.BOLD, 20);
Font wordFont2 = new Font("times Roman", Font.BOLD, 13);
JPanel dri_p1=new JPanel();
JPanel dri_p2=new JPanel();
JPanel dri_p3=new JPanel();
JButton sent=new JButton("Reporting error");
JButton back2=new JButton("     Back      ");

JFrame dri_warn=new JFrame();
JButton warn_b1=new JButton("Back");
JPanel warn_p1=new JPanel();
JPanel warn_p2=new JPanel();
/**
 * add the JButton, JTable... to frame 
 */
public void dri_int()
{   
	dri_l1.setFont(wordFont);
	dri_l2.setFont(wordFont2);
	dri_int.add(dri_p1, BorderLayout.NORTH);
	dri_int.add(dri_p3,BorderLayout.CENTER);
	dri_int.add(dri_p2, BorderLayout.SOUTH);	
	dri_p1.add(dri_l1);
	dri_p3.add(dri_l2);
	dri_p2.add(sent);
	dri_p2.add(back2);

		back2.addActionListener(new ActionListener() {//back to start frame button actionlistener
			public void actionPerformed(ActionEvent e) {
			dri_int.dispose();
	      log_in lg_int=new log_in();
	       lg_int.go();
			}
		});
	
	status.setEnabled(false);
	JScrollPane s_pan = new JScrollPane();
	s_pan.setViewportView(status);
	dri_int.add(s_pan);
	driver d=null;
	for(int i=0;i<driver.driverlist.size();i++){
		if(log_in.log_indriverID==driver.driverlist.get(i).getid()){
			d=driver.driverlist.get(i);
		}
	}
	int trainnumber=-1;
	
	int routenumber = -1;
	String timet="";
	for(int j=0;j<journey.journeylist.size();j++){
		if(journey.journeylist.get(j).getdriver().getid()==d.getid()){
			trainnumber=journey.journeylist.get(j).gettrain().gettrainid();
			routenumber=journey.journeylist.get(j).getroute().getid();
			tt=journey.journeylist.get(j).gettrain();
			ArrayList<time> ttt=journey.journeylist.get(j).gettime();
			timet=ttt.get(0).gethour()+":"+ttt.get(0).getminute()+"-"+ttt.get(ttt.size()-1).gethour()+":"+ttt.get(ttt.size()-1).getminute();
		}
	}
	if(!timet.equals("")){
	sent.addActionListener(new ActionListener() {//sent unusual to administrator
		public void actionPerformed(ActionEvent e) {
			tt.setmaintain("Abort");
		}
	});
	model.addRow(d.getid(),d.getname(), routenumber,trainnumber,timet);
	dri_int.setBounds(10, 10, 700, 200);
	dri_int.setLocationRelativeTo(null);
	dri_int.setVisible(true);
	dri_int.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
}else{
	JOptionPane.showMessageDialog(null, "NO mission", "Error",JOptionPane.ERROR_MESSAGE);
	dri_int.dispose();
    log_in lg_int=new log_in();
     lg_int.go();
	}
}
/**
 * Inner class to set the table configuration
 * @author Group28
 *
 */
@SuppressWarnings({"rawtypes","unchecked"})
class Table_Model extends AbstractTableModel {
    private static final long serialVersionUID = -3094977414157589758L;

	private Vector content = null;

    private String[] title_name = { "ID", "Name", "routeNum","TrainNum","time" };

    /**
     * The structure to new a vector
     */
    public Table_Model() {
        content = new Vector();
    }
    
    /**
     * The structure to new a vector
     * @param count the number of vector
     */
    public Table_Model(int count) {
        content = new Vector(count);
    }

    /** 
     * add one space row
     * @param row row number
     */
	public void addRow(int row) {
        Vector v = new Vector(3);
        v.add(0, null);
        v.add(1, null);
        v.add(2, null);
        v.add(3,null);
        v.add(4,null);
        content.add(row, v);
    }

  
    /**
     * add information
     * @param ID driver ID
     * @param name driver name
     * @param rNum route number
     * @param tNum train number
     * @param time working time
     */
    public void addRow(int ID,String name, int rNum,int tNum,String time) {
        Vector v = new Vector(5);
        v.add(0, ID);
        v.add(1, name);
        v.add(2, rNum); 
        v.add(3, tNum);
        v.add(4,time);

        content.add(v);
    }

    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    public int getColumnCount() {
        return title_name.length;
    }

    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getRowCount()
     */
    public int getRowCount() {
        return content.size();
    }

    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    public Object getValueAt(int row, int col) {
        return ((Vector) content.get(row)).get(col);
    }

    /* (non-Javadoc)
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     */
    public Class getColumnClass(int col) {
        return getValueAt(0, col).getClass();
    }


}
}