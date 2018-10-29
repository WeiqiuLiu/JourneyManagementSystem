import java.io.*;
import java.util.ArrayList;
/**
 * journey data class
 * @author Group28
 *
 */
@SuppressWarnings("serial")
public class journey implements Serializable{
	static ArrayList<journey> journeylist=new ArrayList<journey>();
	
	private driver Driver;
	private route Route;
	private ArrayList<time> timeList;
	private train Train;
	
	/**
	 * The structure to new journey
	 * @param d	driver used
	 * @param r	route used
	 * @param t	time table
	 * @param tr train used
	 */
	public journey(driver d,route r,ArrayList<time> t,train tr){
		this.Driver=d;
		this.Route=r;
		this.timeList=t;
		this.Train=tr;
	}
	
	/**
	 * The structure to new a journey
	 * @param r	route used
	 * @param t	time table
	 */
	public journey(route r,ArrayList<time> t){
		this.Route=r;
		this.timeList=t;
	}
	
	/**
	 * assign driver to journey
	 * @param d driver
	 */
	public void setdriver(driver d){
		this.Driver=d;
	}
	
	/**
	 * assign train to journey
	 * @param t train
	 */
	public void settrain(train t){
		this.Train=t;
	}
	
	/**
	 * save data to document using serialize
	 * @return whether save success
	 */
	public int save(){
		journeylist.add(this);
		try{
			FileOutputStream fos=new FileOutputStream("journey.out");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(journeylist);
			oos.close();
			fos.close();
			return 1;
		}catch(Exception e){
			journeylist.remove(this);
			return 0;
		}
	}
	/**
	 * check the available train now
	 * @return the available train list
	 */
	public ArrayList<train> checktrain(){
		ArrayList<train> tlist=train.trainlist;
		int size1=this.gettime().size();
		for(int ii=0;ii<train.trainlist.size();ii++){
			for(int j=0;j<journeylist.size();j++){
				if(train.trainlist.get(ii).gettrainid()==journeylist.get(j).gettrain().gettrainid()){
					for(int k=0;k<tlist.size();k++){
						if(tlist.get(k).gettrainid()==train.trainlist.get(ii).gettrainid()){
							tlist.remove(k);
						}
					}
				}
			}
		}
		for(int i=0;i<journeylist.size();i++){
			int size2=journeylist.get(i).gettime().size();
			if((comparetime(this.gettime().get(size1-1),journeylist.get(i).gettime().get(0))==0)||(comparetime(this.gettime().get(0),journeylist.get(i).gettime().get(size2-1))==1)){
				tlist.add(journeylist.get(i).gettrain());
			}
		}
		return tlist;
	}
	
	/**
	 * check the available driver now
	 * @return the available driver list
	 */
	public ArrayList<driver> checkdriver(){
		ArrayList<driver> dlist=driver.driverlist;
		int size1=this.gettime().size();
		for(int ii=0;ii<driver.driverlist.size();ii++){
			for(int j=0;j<journeylist.size();j++){
				if(driver.driverlist.get(ii).getid()==journeylist.get(j).getdriver().getid()){
					for(int k=0;k<dlist.size();k++){
						if(dlist.get(k).getid()==driver.driverlist.get(ii).getid()){
							dlist.remove(k);
						}
					}
				}
			}
		}
		for(int i=0;i<journeylist.size();i++){
			int size2=journeylist.get(i).gettime().size();
			if((comparetime(this.gettime().get(size1-1),journeylist.get(i).gettime().get(0))==0)||(comparetime(this.gettime().get(0),journeylist.get(i).gettime().get(size2-1))==1)){
				dlist.add(journeylist.get(i).getdriver());
			}
		}
		return dlist;
	}
	
	/**
	 * compare two time
	 * @param t1	time need to compare
	 * @param t2	time need to compare
	 * @return	t1>=t2 return 1, if not return 0
	 */
	public static int comparetime(time t1,time t2){
		if(t1.gethour()>t2.gethour()){
			return 1;
		}else if(t1.gethour()==t2.gethour()){
			if(t1.getminute()>=t2.getminute()){
				return 1;
			}else{
				return 0;
			}
		}else{
			return 0;
		}
	}
	
	/**
	 * read data from file when start software
	 * @return 1 for success,0 for failed
	 */
	@SuppressWarnings("unchecked")
	public static int read(){
		try{
			File file =new File("journey.out");
			if(!file.exists()){
				file.createNewFile();
			}else{
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream oin= new ObjectInputStream(fis);
				journeylist=(ArrayList<journey>) oin.readObject();
				oin.close();
				fis.close();
			}
			return 1;
		}catch(Exception e){
			return 0;
		}
	}
	
	/**
	 * get driver
	 * @return driver
	 */
	public driver getdriver(){
		return this.Driver;
	}
	
	/**
	 * get route
	 * @return route
	 */
	public route getroute(){
		return this.Route;
	}
	
	/**
	 * get time table reach station
	 * @return	time list of reach station
	 */
	public ArrayList<time> gettime(){
		return this.timeList;
	}
	
	/**
	 * get time table leave station
	 * @return	time list of leave station
	 */
	public ArrayList<time> gettime3(){
		ArrayList<time> ttt=new ArrayList<time>();
		for(int i=0;i<this.gettime().size();i++){
			int h=this.gettime().get(i).gethour();
			int m=this.gettime().get(i).getminute();
			if((m+3)>=60){
				h+=1;
				m-=60;
			}
			if(h>=24){
				h-=24;
			}
			ttt.add(new time(h,m+3));
		}
		return ttt;
	}
	
	/**
	 * get train
	 * @return train
	 */
	public train gettrain(){
		return this.Train;
	}

	/**
	 * save driverlist to document
	 */
	public static void savedriverlist() {
		try{
			FileOutputStream fos=new FileOutputStream("journey.out");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(journeylist);
			oos.close();
			fos.close();
		}catch(Exception e){
		}
		
	}
}
