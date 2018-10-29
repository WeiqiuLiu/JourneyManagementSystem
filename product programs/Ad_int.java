

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * The administrator entrance frame
 * @author Group 28
 *
 */
public class Ad_int {
	JFrame ad_int=new JFrame();
	Font wordFont3 = new Font("times Roman", Font.BOLD, 20);
	Font wordFont2 = new Font("times Roman", Font.BOLD, 12);
	JLabel ad_l1=new JLabel("Please choose the option you want:");
	JLabel ad_blank1=new JLabel("                                 ");
	JLabel ad_blank2=new JLabel("                                  ");
	JLabel ad_blanks=new JLabel("                                  ");
	JLabel ad_blank3=new JLabel("                                    ");
	JLabel ad_blank4=new JLabel("                                      ");
	JLabel ad_blank5=new JLabel("                                       ");
	JLabel ad_blank7=new JLabel("                                       ");
	JLabel ad_blank8=new JLabel("                                       ");
	JButton ad_b1=new JButton("                 Manage drivers' information               ");
	JButton ad_b2=new JButton("                 Manage trains' information                 ");
	JButton ad_b3=new JButton("                 Manage routes' information                ");
	JButton ad_b4=new JButton("                             Assignment                             ");
	JButton ad_b6=new JButton("                   Check trains' live status                    ");
	JButton ad_station=new JButton("                 Manage stations' information             ");
	JButton ad_back=new JButton("Back");
	JPanel p4=new JPanel();
	JPanel ad_p5=new JPanel();
	JPanel ad_p6=new JPanel(); 
	Box box2=Box.createVerticalBox();
	/**
	 * The structure to add the JButton, JTable... to frame
	 */
	public void ad_int()
	{
	ad_int.setBounds(10, 10, 600, 400);
	ad_l1.setFont(wordFont3);
	ad_b1.setFont(wordFont2);
	ad_b2.setFont(wordFont2);
	ad_b3.setFont(wordFont2);
	ad_b4.setFont(wordFont2);
	ad_b6.setFont(wordFont2);
	ad_back.setFont(wordFont2);
	box2.add(ad_blank1);
	box2.add(ad_b1);
	ad_b1.addActionListener(new ActionListener() {//driver management button listener
		public void actionPerformed(ActionEvent e) {
			ad_int.dispose();
			Ad_driman ad_driman=new Ad_driman();
			ad_driman.ad_driman();
			
		}

	});
	box2.add(ad_blank2);
	box2.add(ad_b2);
	ad_b2.addActionListener(new ActionListener() {//train management button listener
		public void actionPerformed(ActionEvent e) {
			ad_int.dispose();
			Ad_trman ad_trman=new Ad_trman();
			ad_trman.ad_trman();
			
		}

	});
	
	box2.add(ad_blanks);
	box2.add(ad_station);
	ad_station.addActionListener(new ActionListener() {//station management button listener
		public void actionPerformed(ActionEvent e) {
			ad_int.dispose();
			Ad_stationman a=new Ad_stationman();
			a.ad_stationman();
		}
	});
	
	box2.add(ad_blank3);
	box2.add(ad_b3);
	ad_b3.addActionListener(new ActionListener() {//route management button listener
		public void actionPerformed(ActionEvent e) {
			ad_int.dispose();
			Ad_routeman ad_jouman=new Ad_routeman();
			ad_jouman.ad_jouman();
			
		}

	});
	
	box2.add(ad_blank4);
	box2.add(ad_b4);
	ad_b4.addActionListener(new ActionListener() {//journey assignment button listener
		public void actionPerformed(ActionEvent e) {
			ad_int.dispose();
			Ad_ass ad_ass=new Ad_ass();
			ad_ass.ad_ass();
			
		}

	});
	box2.add(ad_blank5);
	box2.add(ad_b6);
	ad_b6.addActionListener(new ActionListener() {//Check trains' live status button listener
		public void actionPerformed(ActionEvent e) {
			ad_int.dispose();
			live_int live_int2=new live_int();
			live_int2.live_int2();
		}

	});
	ad_p6.add(ad_back);
	ad_back.addActionListener(new ActionListener() {//back to the start frame
		public void actionPerformed(ActionEvent e) {
		ad_int.dispose();
        log_in lg_int=new log_in();
        lg_int.go();
		}

	});
	ad_int.add(BorderLayout.CENTER, p4);
	ad_int.add(BorderLayout.NORTH,ad_p5);
	ad_int.add(BorderLayout.SOUTH,ad_p6);
	
	p4.add(box2,BorderLayout.CENTER);
	ad_p5.add(ad_l1);
	ad_int.setLocationRelativeTo(null);
	ad_int.setVisible(true);
	ad_int.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
}
}
