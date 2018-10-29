import java.io.*;
import java.util.ArrayList;
/**
 * Train class
 * @author Group28
 *
 */
@SuppressWarnings("serial")
public class train implements Serializable{
	static ArrayList<train> trainlist=new ArrayList<train>();
	
	private int trainid;
	private String mode;
	private String maintain;
	
	/**
	 * The structure to new a train
	 * @param a	train id
	 * @param mode	train model
	 * @param m	train information
	 */
	public train(int a,String mode,String m){
		this.trainid=a;
		this.mode=mode;
		this.maintain=m;
	}
	
	/**
	 * get train id
	 * @return	train id
	 */
	public int gettrainid(){
		return this.trainid;
	}
	
	/**
	 * get train model
	 * @return	train model
	 */
	public String getmode(){
		return this.mode;
	}
	
	/**
	 * set train information
	 * @param m	train information
	 */
	public void setmaintain(String m){
		this.maintain=m;
		train.savetrainlist();
	}
	
	/**
	 * get train information
	 * @return	train information
	 */
	public String getmaintain(){
		control c=new control();
		if(live_int.f==0){
		if(c.getlocation(this)!=0)this.maintain="running";
		else this.maintain="stop";
		}
		train.savetrainlist();
		return this.maintain;
	}
	
	/**
	 * save train list
	 * @return	whether save success
	 */
	public int save(){
		if(check(this)==false){
			return 0;
		}else{
			trainlist.add(this);
		}
		try{
			FileOutputStream fos=new FileOutputStream("train.out");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(trainlist);
			oos.close();
			fos.close();
			return 1;
		}catch(Exception e){
			trainlist.remove(this);
			return 0;
		}
	}
	
	/**
	 * save train list
	 */
	public static void savetrainlist(){
		try{
			FileOutputStream fos=new FileOutputStream("train.out");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(trainlist);
			oos.close();
			fos.close();
		}catch(Exception e){
		}
	}
	
	/**
	 * check whether train repeat
	 * @param t	train need to check
	 * @return	true or false
	 */
	private boolean check(train t){
		for(int i=0;i<trainlist.size();i++){
			if(trainlist.get(i).gettrainid()==t.gettrainid()){
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
			File file =new File("train.out");
			if(!file.exists()){
				file.createNewFile();
			}else{
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream oin= new ObjectInputStream(fis);
				trainlist=(ArrayList<train>) oin.readObject();
				oin.close();
				fis.close();
			}
			return 1;
		}catch(Exception e){
			return 0;
		}
	}
}
