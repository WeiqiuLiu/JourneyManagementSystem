import java.io.*;
import java.util.ArrayList;
/**
 * station data class
 * @author Group28
 *
 */
@SuppressWarnings("serial")
public class station implements Serializable{
	static ArrayList<station> stationlist=new ArrayList<station>();
	private String name;
	/**
	 * The stucture to new a station
	 * @param name station name
	 */
	public station(String name){
		this.name=name;
	}
	
	/**
	 * get name
	 * @return	station name
	 */
	public String getname(){
		return this.name;
	}
	
	/**
	 * save station list
	 * @return	whether save success
	 */
	public int save(){
		if(check(this)==false){
			return 0;
		}else{
			stationlist.add(this);
		}
		try{
			FileOutputStream fos=new FileOutputStream("station.out");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(stationlist);
			oos.close();
			fos.close();
			return 1;
		}catch(Exception e){
			stationlist.remove(this);
			return 0;
		}
	}
	
	/**
	 * save station list
	 */
	public static void savestationlist(){
		try{
			FileOutputStream fos=new FileOutputStream("station.out");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(stationlist);
			oos.close();
			fos.close();
		}catch(Exception e){
		}
	}
	
	/**
	 * check station whether repeat
	 * @param t	station need to check
	 * @return	ture or false
	 */
	private boolean check(station t){
		for(int i=0;i<stationlist.size();i++){
			if(stationlist.get(i).getname().equals(this.getname())){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * read data from file when start software
	 * @return 1 for success,0 for failed
	 */
	@SuppressWarnings("unchecked")
	public static int read(){
		try{
			File file =new File("station.out");
			if(!file.exists()){
				file.createNewFile();
			}else{
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream oin= new ObjectInputStream(fis);
				stationlist=(ArrayList<station>) oin.readObject();
				oin.close();
				fis.close();
			}
			return 1;
		}catch(Exception e){
			return 0;
		}
	}
}
