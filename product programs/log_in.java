

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.Box;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * The start frame of software
 * @author Group28
 *
 */
public class log_in {
	JFrame f_login = new JFrame();
	static control c;
	static int log_indriverID;
	static int log_inrouteID;
	static int log_intrainID;
	
	Font wordFont = new Font("times Roman", Font.BOLD, 50);
	String s = "Welcome";
	int x_character = 0;
	Draw d1 = new Draw();
	Threadrun t1 = new Threadrun();
	
	JLabel l1=new JLabel("Please choose your identity");
	Font wordFont2 = new Font("times Roman", Font.BOLD, 12);
	JLabel blank1=new JLabel("                                ");
	JLabel blank2=new JLabel("                                 ");
	JLabel blank3=new JLabel("                                ");
	JLabel blank4=new JLabel("                                 ");
	JLabel blank5=new JLabel("                                 ");
	JButton b_ad=new JButton(" Administrator ");// Click here to enter the interface for admini
	JButton b_dr=new JButton("         Driver        ");//driver
	JButton b_st=new JButton("Station display");//station display
	JButton b_tra=new JButton("  Train display  ");//train display
	JPanel p1=new JPanel();
	Box box=Box.createVerticalBox();
	
	JFrame f_drlg = new JFrame();
	JPanel p2= new JPanel();
	JPanel p3= new JPanel();
	JLabel l2=new JLabel("Please input your driver ID:");
	TextField driID = new TextField(10);
	JButton b1=new JButton("Enter");
	JComboBox<String> ID;
	JFrame f_stlg = new JFrame();
	JPanel p4= new JPanel();
	JPanel p5= new JPanel();
	JLabel l3=new JLabel("Please input your route ID:");
	TextField routeID = new TextField(10);
	JButton b2=new JButton("Enter");
	JComboBox<String> sID;
	JFrame f_trlg = new JFrame();
	JPanel p6= new JPanel();
	JPanel p7= new JPanel();
	JLabel l4=new JLabel("Please input your trian ID:");
	TextField trID = new TextField(10);
	JButton b3=new JButton("Enter");
	JComboBox<String> tID;
	/**
	 * main method to running software
	 * @param args	input if have some running configuration
	 */
	public static void main(String[] args) {
		log_in gui = new log_in();
		c=new control();
		c.getstarttime();
		control.flag=false;
		gui.go();
		try {
			String lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (Exception e) {
		}

	}
	/**
	 * generate GUI
	 */
	@SuppressWarnings("deprecation")
	public void go()
	{
	/***********choose the identify----interface*************/
		ID = new JComboBox<>(new MyComboBox());
		sID = new JComboBox<>(new MyComboBox2());
		tID = new JComboBox<>(new MyComboBox3());
		f_login.setBounds(10, 10, 400, 500);
		f_login.add(BorderLayout.SOUTH, p1);
		p1.add(box,BorderLayout.CENTER);
		f_login.setTitle("Group 28");
		b_ad.addActionListener(new ActionListener() {//administrator entrance
			public void actionPerformed(ActionEvent e) {
				f_login.dispose();
				Ad_int ad_int=new Ad_int();
				ad_int.ad_int();
				t1.stop();
				
			}

		});
		b_dr.addActionListener(new ActionListener() {//driver entrance
			public void actionPerformed(ActionEvent e) {
				f_login.dispose();
				f_drlg.setVisible(true);
				t1.stop();
			}

		});
		b_tra.addActionListener(new ActionListener() {//train entrance
			public void actionPerformed(ActionEvent e) {
				f_login.dispose();
				f_trlg.setVisible(true);
				t1.stop();
			}

		});
		b_st.addActionListener(new ActionListener() {//station entrance
			public void actionPerformed(ActionEvent e) {
				f_login.dispose();
				f_stlg.setVisible(true);
				t1.stop();
			}

		});
		l1.setFont(wordFont2);
        box.add(l1);
        box.add(blank5);
		box.add(b_ad);
		box.add(blank1);
		box.add(b_dr);
		box.add(blank2);
		box.add(b_st);
		box.add(blank3);
		box.add(b_tra);
		box.add(blank4);
		
		f_login.add(d1);
		t1.start();
		f_login.setLocationRelativeTo(null);
		f_login.setVisible(true);
		f_login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		f_drlg.add(BorderLayout.NORTH,p2);
		f_drlg.add(BorderLayout.SOUTH,p3);
		p2.add(l2);
		p2.add(ID);
		p3.add(b1);
		b1.addActionListener(new ActionListener() {//choose which driver you want to see
			public void actionPerformed(ActionEvent e) {
				try{
					log_indriverID=Integer.parseInt(ID.getSelectedItem().toString());
					f_drlg.dispose();
				Dri_int dri_int=new Dri_int();
				dri_int.dri_int();
				}catch(Exception ee){
				}
			}

		});
		f_drlg.setBounds(10, 10, 300, 125);
		f_drlg.setLocationRelativeTo(null);
		f_drlg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	

		f_stlg.add(BorderLayout.NORTH,p4);
		f_stlg.add(BorderLayout.SOUTH,p5);
		p4.add(l3);
		p4.add(sID);
		//p2.add(driID);
		p5.add(b2);
		b2.addActionListener(new ActionListener() {//choose which station you want to see
			public void actionPerformed(ActionEvent e) {
				
				try{
					String s=sID.getSelectedItem().toString();
					f_stlg.dispose();
					log_inrouteID=Integer.valueOf(s).intValue();
					Station_int sta_int=new Station_int();
					sta_int.station_int();
				}catch(Exception ee){
				}
			}

		});
		f_stlg.setBounds(10, 10, 300, 125);
		f_stlg.setLocationRelativeTo(null);
		f_stlg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	

		f_trlg.add(BorderLayout.NORTH,p6);
		f_trlg.add(BorderLayout.SOUTH,p7);
		p6.add(l4);
		p6.add(tID);
		p7.add(b3);
		b3.addActionListener(new ActionListener() {//choose which train you want to see
			public void actionPerformed(ActionEvent e) {
				try{
					String s=tID.getSelectedItem().toString();
					f_trlg.dispose();
					log_intrainID=Integer.valueOf(s).intValue();
					Train_int sta_int=new Train_int();
					sta_int.train_int();
				}catch(Exception ee){

				}
			}

		});
		f_trlg.setBounds(10, 10, 300, 125);
		f_trlg.setLocationRelativeTo(null);
		f_trlg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	
    

	/**
	 * inner class to draw a gif
	 * @author Group28
	 *
	 */
	@SuppressWarnings("serial")
	public class Draw extends JPanel {
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setFont(wordFont);
			g2.setColor(Color.black);
			g2.drawString(s.substring(0, x_character), 95, 150);
		}}
		/**
		 * A thread to refrash frame
		 * @author xg
		 *
		 */
		public class Threadrun extends Thread {
			public void run() {

				while (true) {
					if (++x_character > s.length())
						x_character = 0;
					f_login.repaint();
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
					}
				}
			}
		}

/**
 * Inner class for driver combobox
 * @author Group28
 *
 */
@SuppressWarnings("serial")
class MyComboBox extends AbstractListModel<String> implements ComboBoxModel<String>
{
	String selecteditem=null;
	String[] driID=new String[driver.driverlist.size()];
	/**
	 * The structure to add driver to combobox
	 */
	public MyComboBox(){
		for(int i=0;i<driver.driverlist.size();i++){
			driID[i]=""+driver.driverlist.get(i).getid();
		}
		if(driID.length==0){
			driID=new String[1];
			driID[0]="";
		}
	}
	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	public String getElementAt(int index)
	{
		return driID[index];
	}
	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getSize()
	 */
	public int getSize(){
	    return driID.length;
	}
	/* (non-Javadoc)
	 * @see javax.swing.ComboBoxModel#setSelectedItem(java.lang.Object)
	 */
	public void setSelectedItem(Object item)
	{
		selecteditem=(String)item;
	}
	/* (non-Javadoc)
	 * @see javax.swing.ComboBoxModel#getSelectedItem()
	 */
	public Object getSelectedItem()
	{
		return selecteditem;
	}
}
	
/**
 * Inner class for route combobox
 * @author Group28
 *
 */
@SuppressWarnings("serial")
class MyComboBox2 extends AbstractListModel<String> implements ComboBoxModel<String>
{
	String selecteditem=null;
	String[] routeID=new String[route.routelist.size()];
	/**
	 * The structure to add route to combobox
	 */
	public MyComboBox2(){
		for(int i=0;i<route.routelist.size();i++){
			routeID[i]=String.valueOf(route.routelist.get(i).getid());
		}
		
		if(routeID.length==0){
			routeID=new String[1];
			routeID[0]="";
		}
	}
	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	public String getElementAt(int index)
	{
		return routeID[index];
	}
	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getSize()
	 */
	public int getSize(){
	    return routeID.length;
	}
	/* (non-Javadoc)
	 * @see javax.swing.ComboBoxModel#setSelectedItem(java.lang.Object)
	 */
	public void setSelectedItem(Object item)
	{
		selecteditem=(String)item;
	}
	/* (non-Javadoc)
	 * @see javax.swing.ComboBoxModel#getSelectedItem()
	 */
	public Object getSelectedItem()
	{
		return selecteditem;
	}
}
/**
 * Inner class for train combobox
 * @author Group28
 *
 */
@SuppressWarnings("serial")
class MyComboBox3 extends AbstractListModel<String> implements ComboBoxModel<String>
{
	String selecteditem=null;
	String[] trID=new String[train.trainlist.size()];
	/**
	 * The structure to add train to combobox
	 */
	public MyComboBox3(){
		for(int i=0;i<train.trainlist.size();i++){
			trID[i]=String.valueOf(train.trainlist.get(i).gettrainid());
		}
		
		if(trID.length==0){
			trID=new String[1];
			trID[0]="";
		}
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	public String getElementAt(int index)
	{
		return trID[index];
	}
	/* (non-Javadoc)
	 * @see javax.swing.ListModel#getSize()
	 */
	public int getSize(){
	    return trID.length;
	}
	/* (non-Javadoc)
	 * @see javax.swing.ComboBoxModel#setSelectedItem(java.lang.Object)
	 */
	public void setSelectedItem(Object item)
	{
		selecteditem=(String)item;
	}
	/* (non-Javadoc)
	 * @see javax.swing.ComboBoxModel#getSelectedItem()
	 */
	public Object getSelectedItem()
	{
		return selecteditem;
	}
}

}



