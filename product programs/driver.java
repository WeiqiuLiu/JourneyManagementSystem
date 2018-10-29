import java.io.*;
import java.util.ArrayList;
/**
 * Driver data class
 * @author Group28
 *
 */
@SuppressWarnings("serial")
public class driver implements Serializable{
	static ArrayList<driver> driverlist=new ArrayList<driver>();
	private String name;
	private int age;
	private int gender;
	private int id;
	
	/**
	 * The structure to new a driver
	 * @param id	driver id
	 * @param name	driver name
	 * @param age	driver age
	 * @param gender	driver gender
	 */
	public driver(int id,String name,int age,int gender){
		this.name=name;
		this.id=id;
		this.age=age;
		this.gender=gender;
	}
	/**
	 * get driver id
	 * @return driver id
	 */
	public int getid(){
		return this.id;
	}
	/**
	 * get driver name
	 * @return	driver name
 	 */
	public String getname(){
		return this.name;
	}
	
	/**
	 * get driver age
	 * @return	driver age
	 */
	public int getage(){
		return this.age;
	}
	
	/**
	 * get driver gender
	 * @return driver gender
	 */
	public String getgender(){
		if(this.gender==1){
			return "male";
		}
		else if(this.gender==0){
			return "female";
		}else{
			return "Unknown";
		}
	}
	
	/**
	 * save data to document using serialize
	 * @return whether save success
	 */
	public int save(){
		if(check(this)==false){
			return 0;
		}else{
			driverlist.add(this);
		}
		try{
			FileOutputStream fos=new FileOutputStream("driver.out");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(driverlist);
			oos.close();
			fos.close();
			return 1;
		}catch(Exception e){
			driverlist.remove(this);
			return 0;
		}
	}
	
	/**
	 * save driverlist to document
	 */
	public static void savedriverlist(){
		try{
			FileOutputStream fos=new FileOutputStream("driver.out");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(driverlist);
			oos.close();
			fos.close();
		}catch(Exception e){
		}
	}
	
	/**
	 * check driver whether repeat
	 * @param t	a driver
	 * @return true or false
	 */
	private boolean check(driver t){
		for(int i=0;i<driverlist.size();i++){
			if(driverlist.get(i).getid()==t.getid()){
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
			File file =new File("driver.out");
			if(!file.exists()){
				file.createNewFile();
			}else{
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream oin= new ObjectInputStream(fis);
				driverlist=(ArrayList<driver>) oin.readObject();
				oin.close();
				fis.close();
			}
			return 1;
		}catch(Exception e){
			return 0;
		}
	}
}
