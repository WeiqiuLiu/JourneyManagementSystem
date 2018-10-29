import java.io.Serializable;

/**
 * Time class
 * @author Group28
 *
 */
@SuppressWarnings("serial")
public class time implements Serializable{
	private int hour;
	private int minute;
	private int td;
	
	/**
	 * The structure to new a time
	 * @param h	hour
	 * @param m	minute
	 * @param td	the time distance between now to 1970-01-01
	 */
	public time(int h,int m,int td){
		this.hour=h;
		this.minute=m;
		this.td=td;//s
	}
	
	/**
	 * The structure to new a time 
	 * @param h	hour	
	 * @param m	minute
	 */
	public time(int h,int m){
		this.hour=h;
		this.minute=m;
	}
	
	/**
	 * get hour
	 * @return	hour
	 */
	public int gethour(){
		return this.hour;
	}
	
	/**
	 * get time distance
	 * @return	time distance
	 */
	public int gettd(){
		return this.td;
	}
	
	/**
	 * get minute
	 * @return	minute
	 */
	public int getminute(){
		return this.minute;
	}
	
	/**
	 * display the time in type "HH:mm"
	 * @return	String representative time
	 */
	public String showtime(){
		return this.gethour()+":"+this.getminute();
	}
}