import java.io.*;
import java.util.ArrayList;
/**
 * Route data class
 * @author Group28
 *
 */
@SuppressWarnings("serial")
public class route implements Serializable{
	public static ArrayList<route> routelist=new ArrayList<route>();
	private ArrayList<station> stationList=new ArrayList<station>();
	private int id;
	/**
	 * The structure to new a route
	 * @param id	route id
	 * @param station	station list
	 */
	public route(int id,ArrayList<station> station){
		this.stationList=station;
		this.id=id;
	}
	
	/**
	 * get route id
	 * @return route id
	 */
	public int getid(){
		return this.id;
	}
	
	/**
	 * get station
	 * @return	station list
	 */
	public ArrayList<station> getstation(){
		return this.stationList;
	}
	
	/**
	 * save route list
	 * @return	whether save success
	 */
	public int save(){
		if(check(this)==false){
			return 0;
		}else{
			routelist.add(this);
		}
		try{
			FileOutputStream fos=new FileOutputStream("route.out");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(routelist);
			oos.close();
			fos.close();
			return 1;
		}catch(Exception e){
			routelist.remove(this);
			return 0;
		}
	}
	
	/**
	 * check whether route legal
	 * @param t	a route
	 * @return	true or false
	 */
	private boolean check(route t){
		if(!t.getstation().get(0).getname().equals("centralstation")){
			return false;
		}
		ArrayList<station> s=t.getstation();
		for(int i=0;i<s.size();i++){
			for(int j=0;j<s.size();j++){
				if((i!=j)&&(s.get(i).getname().equals(t.getstation().get(j).getname()))){
					return false;
				}
			}
		}
		if(t.getstation().size()==1){
			return false;
		}
		for(int k=0;k<routelist.size();k++){
			if(t.id==routelist.get(k).getid()){
				return false;
			}
		}
		int sum0=0;
		for(int ii=0;ii<t.getstation().size();ii++){
			for(int jj=0;jj<station.stationlist.size();jj++){
				if(t.getstation().get(ii).getname().equals(station.stationlist.get(jj).getname())){
					sum0+=1;
				}
			}
		}
		if(sum0!=t.getstation().size()){
			System.out.println(getstation().size());
			return false;
		}
		for(int i=0;i<routelist.size();i++){
			int sum=0;
			for(int j=0;(t.getstation().size()==routelist.get(i).getstation().size())&&(j<routelist.get(i).getstation().size());j++){
				if(routelist.get(i).getstation().get(j).getname().equals(t.getstation().get(j).getname())){
					sum+=1;
				}
			}
			if(sum==routelist.get(i).getstation().size()){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * save route list
	 */
	public static void saveroutelist(){
		try{
			FileOutputStream fos=new FileOutputStream("route.out");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(routelist);
			oos.close();
			fos.close();
		}catch(Exception e){
		}
	}
	
	/**
	 * read data from file when start software
	 * @return 1 for success,0 for failed
	 */
	@SuppressWarnings("unchecked")
	public static int read(){
		try{
			File file =new File("route.out");
			if(!file.exists()){
				file.createNewFile();
			}else{
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream oin= new ObjectInputStream(fis);
				routelist=(ArrayList<route>) oin.readObject();
				oin.close();
				fis.close();
			}
			return 1;
		}catch(Exception e){
			return 0;
		}
	}


}
