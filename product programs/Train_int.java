
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
/**
 * Train entrance frame
 * @author Group28
 *
 */
public class Train_int {
	
JFrame tra_int=new JFrame();
Table_Model model = new Table_Model(20);
JTable status = new JTable(model);
JLabel tra_l1=new JLabel("Train "+log_in.log_inrouteID);
Font wordFont = new Font("times Roman", Font.BOLD, 20);
Font wordFont2 = new Font("times Roman", Font.BOLD, 13);
JPanel tra_p1=new JPanel();
JPanel tra_p2=new JPanel();
JButton back=new JButton("     Back     ");

/**
 * add the JButton, JTable... to frame 
 */
public void train_int()
{   
	tra_l1.setFont(wordFont);
	tra_int.add(tra_p1, BorderLayout.NORTH);
	tra_int.add(tra_p2, BorderLayout.SOUTH);	
	tra_p1.add(tra_l1);
	tra_p2.add(back);

	back.addActionListener(new ActionListener() {//back button listener
		public void actionPerformed(ActionEvent e) {
		tra_int.setVisible(false);
		log_in lg_int=new log_in();
		lg_int.go();
		}
	});
	
	status.setEnabled(false);
	JScrollPane s_pan = new JScrollPane();
	s_pan.setViewportView(status);
	tra_int.add(s_pan);
	control c=new control();
	train t=null;
	for(int i=0;i<train.trainlist.size();i++){
		if(train.trainlist.get(i).gettrainid()==log_in.log_intrainID){
			t=train.trainlist.get(i);
			int h =c.getnextstation(t);
			for(int ii=0;ii<journey.journeylist.size();ii++){
				if(journey.journeylist.get(ii).gettrain().gettrainid()==t.gettrainid()){
					model.addRow(journey.journeylist.get(ii).getroute().getstation().get(h).getname(),journey.journeylist.get(ii).gettime().get(c.getnexttime(t)).showtime());
				}
			}
		}
	}
	
	tra_int.setBounds(10, 10, 600, 200);
	tra_int.setLocationRelativeTo(null);
	tra_int.setVisible(true);
	tra_int.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
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

    private String[] title_name = { "The next station", "time" };

    /**
     * Structure
     */
    public Table_Model() {
        content = new Vector();
    }
    /**
     * Structure
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
       
        content.add(row, v);
    }

  
    /**
     * add data to table
     * @param NS
     * @param time
     */
    public void addRow(String NS,String time) {
        Vector v = new Vector(5);
        v.add(0, NS);
        v.add(1, time);
        content.add(v);
    }

    /**
     * remove row
     * @param row	row number
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