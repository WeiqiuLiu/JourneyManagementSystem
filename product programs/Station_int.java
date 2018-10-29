

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 * Station entrance frame
 * @author Group28
 *
 */
public class Station_int {
	
JFrame sta_int=new JFrame();
Table_Model model = new Table_Model();
JTable status = new JTable(model);
JLabel sta_l1=new JLabel("The timetable of the route is as follow: ");
Font wordFont = new Font("times Roman", Font.BOLD, 20);
Font wordFont2 = new Font("times Roman", Font.BOLD, 13);
JPanel sta_p1=new JPanel();
JPanel sta_p2=new JPanel();
JButton back=new JButton("     Back     ");
/**
 * add the JButton, JTable... to frame 
 */
public void station_int()
{   
	sta_l1.setFont(wordFont);
	sta_int.add(sta_p1, BorderLayout.NORTH);
	sta_int.add(sta_p2, BorderLayout.SOUTH);	
	sta_p1.add(sta_l1);
	sta_p2.add(back);

	back.addActionListener(new ActionListener() {//back button listener
		public void actionPerformed(ActionEvent e) {
			sta_int.dispose();
			log_in lg_int=new log_in();
			lg_int.go();
			
		}

	});
	
	status.setEnabled(false);
	JScrollPane s_pan = new JScrollPane();
	s_pan.setViewportView(status);
	sta_int.add(s_pan);
	ArrayList<time> tl=new ArrayList<time>();
	for(int j=0;j<journey.journeylist.size();j++){
    	if(log_in.log_inrouteID==journey.journeylist.get(j).getroute().getid()){
    		int ii;
    		for(ii=0;ii<journey.journeylist.get(j).gettime().size();ii++){
    			tl.add(journey.journeylist.get(j).gettime().get(ii));
    		}
    		for(;ii<10;ii++){
    			tl.add(new time(0,0));
    		}
    		model.addRow(tl.get(0).showtime(),tl.get(1).showtime(), tl.get(2).showtime(),tl.get(3).showtime(),tl.get(4).showtime(),tl.get(5).showtime(),tl.get(6).showtime(),tl.get(7).showtime(),tl.get(8).showtime(),tl.get(9).showtime());
    	}
    }
	
	sta_int.setBounds(10, 10, 900, 200);
	sta_int.setLocationRelativeTo(null);
	sta_int.setVisible(true);
	sta_int.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
}
/**
 * Inner class for table model configuration
 * @author Group
 *
 */
@SuppressWarnings({"rawtypes","unchecked"}) 
class Table_Model extends AbstractTableModel {
    private static final long serialVersionUID = -3094977414157589758L;

	private Vector content = null;

    private String[] title_name =new String[10];

    /**
     * The structure to add title name to table
     */
    public Table_Model() {
    	for(int i=0;i<10;i++){
        	title_name[i]="";
        }
        for(int j=0;j<route.routelist.size();j++){
        	if(log_in.log_inrouteID==route.routelist.get(j).getid()){
        		title_name=new String[2*(route.routelist.get(j).getstation().size())-1];
        		try{
        		for(int k=0;k<route.routelist.get(j).getstation().size();k++){
        			if(k==(route.routelist.get(j).getstation().size()-1)){
        				this.title_name[k]=route.routelist.get(j).getstation().get(k).getname();
        			}else{
        				this.title_name[k]=route.routelist.get(j).getstation().get(k).getname();
            			this.title_name[title_name.length-k-1]=route.routelist.get(j).getstation().get(k).getname();
        			}
        		}
        		}catch(Exception ee){
        		}
        	}
        }
        if(title_name[0]==""){
        	
        }
    	content = new Vector();
    }

    /**
     * The stucture for more count
     * @param count number of vector
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
        v.add(5,null);
        v.add(6,null);
        v.add(7,null);
        v.add(8,null);
        v.add(9,null);
        content.add(row, v);
    }

  
    /**
     * add data to one row
     * @param CS	central station
     * @param A		station one
     * @param B		station one
     * @param C		station one
     * @param D		station one
     * @param D2	station one
     * @param C2	station one
     * @param B2	station one
     * @param A2	station one
     * @param CS2	central station
     */
    public void addRow(String CS,String A,String B,String C,String D,String D2,String C2,String B2,String A2,String CS2) {
        Vector v = new Vector(5);
        v.add(0, CS);
        v.add(1, A); 
        v.add(2, B);
        v.add(3, C);
        v.add(4,D);
        v.add(5,D2);
        v.add(6,C2);
        v.add(7,B2);
        v.add(8,A2);
        v.add(9,CS2);

        content.add(v);
    }

    /**
     * remove one row
     * @param row row number
     */
    public void removeRow(int row) {
        content.remove(row);
    }

    /* (non-Javadoc)
     * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
     */
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(rowIndex == 2) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object, int, int)
     */
    public void setValueAt(Object value, int row, int col) {
        ((Vector) content.get(row)).remove(col);
        ((Vector) content.get(row)).add(col, value);
        this.fireTableCellUpdated(row, col);
    }

    /* (non-Javadoc)
     * @see javax.swing.table.AbstractTableModel#getColumnName(int)
     */
    public String getColumnName(int col) {
        return title_name[col];
    }

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