

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * The train location display
 * @author Group28
 *
 */
public class live_int implements Runnable {
	JFrame f_llg=new JFrame();
	JPanel llg_p1=new JPanel();
	JPanel llg_p2=new JPanel();
	Thread t1 = new Thread(this);
	int h;
	int xx=0,yy=0;
	control c;
	JLabel llg_l1=new JLabel("Please choose the train number");
	TextField llg_trID = new TextField(10);
	JButton llg_b3=new JButton("Enter");
	String trID[];
	train t;
	int num;
	JFrame f_lint=new JFrame();
	JPanel lint_p1=new JPanel();
	JButton lint_b1=new JButton("Stop the train");
	JButton lint_b2=new JButton("          Back          ");
	JButton lint_time=new JButton("Acceleration time");
	MyCanvas line=new MyCanvas();
	JLabel labelB;
	static int f=0;
	/*
	 * add the JButton, JTable... to frame
	 */
	public void live_int2()
	{
		trID=new String[train.trainlist.size()];
		for(int i=0;i<train.trainlist.size();i++){
			  trID[i]=train.trainlist.get(i).gettrainid()+"";
		  }
		JComboBox<String> tID = new JComboBox<>(new MyComboBox());
	
		c =new control();
		t1.start();
		labelB = new JLabel(c.getnowtime(control.starttime).showtime());
		f_llg.add(BorderLayout.NORTH,llg_p1);
		f_llg.add(BorderLayout.SOUTH,llg_p2);
		f_llg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		llg_p1.add(llg_l1);
		llg_p1.add(tID);
		llg_p2.add(llg_b3);
		llg_b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//ensure choose button listener
				for(int i=0;i<train.trainlist.size();i++){
					try{
						if(Integer.parseInt(tID.getSelectedItem().toString())==train.trainlist.get(i).gettrainid()){
							t=train.trainlist.get(i);
							f_llg.setVisible(false);
							f_lint.setVisible(true);
							xx=1;
						}
					}catch(Exception ee){
						
					}
				}
			}

		});
		f_llg.setBounds(10, 10, 300, 125);
		f_llg.setLocationRelativeTo(null);
		f_llg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		f_llg.setVisible(true);
		f_lint.add(line);//line is the picture
		f_lint.add(BorderLayout.SOUTH,lint_p1);
		lint_p1.add(lint_time);
		lint_p1.add(lint_b1);
		lint_p1.add(lint_b2); 
		lint_time.addActionListener(new ActionListener() {//change time flag button listener
			public void actionPerformed(ActionEvent e) {
				String butName = e.getActionCommand();
				if ("        Real time        ".equals(butName)) {
					lint_time.setText("Acceleration time");
					control.flag=false;
				} else {
					control.flag=true;
				  lint_time.setText("        Real time        ");
				}

			}

		});
		lint_b1.addActionListener(new ActionListener() {//stop or start train listener
			public void actionPerformed(ActionEvent e) {
				String butName = e.getActionCommand();
				if ("Stop the train".equals(butName)) {
					t.setmaintain("remotely stop");
					yy=1;
					f=1;
					lint_b1.setText("Start the train");
				} else {
					t.setmaintain("running");
					f=0;
				  lint_b1.setText("Stop the train");
					yy=0;
				}
			}
		});
		lint_b2.addActionListener(new ActionListener() {//back to start frame
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				f_llg.dispose();
				f_lint.dispose();
				Ad_int ad_int=new Ad_int();
				t1.stop();
				ad_int.ad_int();
			}
		});
		f_lint.setBounds(10, 10, 600, 300);
		f_lint.setLocationRelativeTo(null);
		f_lint.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		while (true) {
			if (Thread.currentThread() == t1) {// 当现场为蓝色
				try{
					if(yy==0){
						f_lint.remove(line);  
					}
					lint_p1.remove(labelB);
					if(!t.getmode().equals("stop")){
						h =c.getlocation(t);
					}
				}catch(Exception ee){
					h=0;
				}
				
				labelB = new JLabel(c.getnowtime(control.starttime).showtime());
				if(yy==0){
					f_lint.add(line);  
				}
				lint_p1.add(labelB);
				if(xx==1){
					f_lint.setVisible(true);
				}
			}
			try {
				Thread.sleep(1000); // 休息500毫秒。
			} catch (InterruptedException e) {
			}
		}
	}
	/**
	 * Inner class to set the combobox configuration
	 * @author Group28
	 *
	 */
	@SuppressWarnings("serial")
	class MyComboBox extends AbstractListModel<String> implements ComboBoxModel<String>
	{
		String selecteditem=null;
		String[] trID=new String[train.trainlist.size()];
		
		/**
		 * The structure to new a combobox
		 */
		public MyComboBox(){
			for(int i=0;i<train.trainlist.size();i++){
				  trID[i]=train.trainlist.get(i).gettrainid()+"";
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
	
	/**
	 * Inner class to set Canvas configuration
	 * @author Group28
	 *
	 */
	@SuppressWarnings("serial")
	class MyCanvas extends Canvas { // Canvas容器，用来存放线条 没用了
		
		/**
		 * paint method
		 */
		public void paint(Graphics g) {

			int j=journey.journeylist.size();
			for(int i=0;i<journey.journeylist.size();i++){
				if(journey.journeylist.get(i).gettrain().gettrainid()==t.gettrainid()){
					j=journey.journeylist.get(i).getroute().getstation().size();
				}
			}
			
			int interval=(this.getWidth()/(j+1));
			for(int i=1;i<=j;i++)
			{   
				g.setColor(Color.black);
				g.fillOval(interval*i,100, 15, 15);
			 }
			for(int i=1;i<j;i++)
			{
				g.setColor(Color.black);
				g.fillOval(interval*i+interval/2,102, 10, 10);
			}
			    g.drawLine(interval,107,interval*j,107);
			if(h%2!=0)// it is on the station
			{
				int h2=(h+1)/2;
				g.setColor(Color.green);
				g.fillOval(interval*h2+interval/2,102, 10, 10);
			}
			if(h%2==0)//it is between two station
			{   
				int h2=h/2+1;
				g.setColor(Color.red);
				g.fillOval(interval*h2,100, 15, 15);
			}
			}
	}
	

}
