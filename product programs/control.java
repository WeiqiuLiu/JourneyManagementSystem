import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;
/**
 * Summary of some useful method
 * @author Group28
 *
 */
public class control {
	static station central;
	static time starttime;
	static boolean flag;
	public control()  {
		route.read();
		station.read();
		train.read();
		journey.read();
		driver.read();
		central=new station("centralstation");
		if(station.stationlist.size()==0){
			central.save();
		}
		
	}
	
	
	/**
	 * get the start time when software start
	 */
	@SuppressWarnings("deprecation")
	public void getstarttime(){
		Date date=new Date();
		starttime=new time(date.getHours(),date.getMinutes(),(int)((date.getTime())/1000));
	}
	/**
	 * set the start time manually
	 * @param hh hour
	 * @param mm minite
	 */
	public void setstarttime(int hh,int mm){
		starttime=new time(hh,mm,(int)(System.currentTimeMillis()/1000));
	}
	/**
	 * get now time through calculate the start time
	 * @param starttime start time
	 * @return now time
	 */
	public time getnowtime(time starttime){
		
		Date date=new Date();
		time nowtime;
		int distance=(int)((date.getTime())/1000)-starttime.gettd();
		if(flag==true){//jiasu
			int h=starttime.gethour();
			int m=starttime.getminute();
			int mm=distance%60;
			int hh=distance/60;
			if((m+mm)>=60){
				hh+=1;
				mm-=60;
			}
			if((h+hh)>=24){
				hh-=24;
			}
			nowtime=new time(h+hh,mm+m);
		}else{
			int h=starttime.gethour();
			int m=starttime.getminute();
			int mm=distance/60;
			int hh=distance/60/60;
			if((m+mm)>=60){
				hh+=1;
				mm-=60;
			}
			if((h+hh)>=24){
				hh-=24;
			}
			nowtime=new time(h+hh,mm+m);
		}
		return nowtime;
	}
	/**
	 * change input String tt to object time
	 * @param tt String need to change(must in type "HH:mm")
	 * @return object time representative input string tt
	 */
	public static time changestring(String tt){
		time t=new time(Integer.parseInt(tt.substring(0, 2)),Integer.parseInt(tt.substring(3,5)));
		return t;
	}
	/**
	 * judge input t whether obey the type ("HH:mm")
	 * @param t a time object
	 * @return true or false
	 */
	public static boolean istime(String t){
		String pattern="(([0-1][0-9])|2[0-3]):[0-5][0-9]";
		Pattern p = Pattern.compile(pattern);
		return p.matcher(t).matches();
	}
	/**
	 * judge input a whether a number
	 * @param a string need to be judged
	 * @return true or false
	 */
	public boolean isint(String a){
		try{
			Integer.parseInt(a);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
	/**
	 * complement the time accurance flag
	 */
	public void setflag(){
		if(flag==true){
			flag=false;
		}else{
			flag=true;
		}
	}
	/**
	 * get the train location now
	 * @param t train want to get its location
	 * @return a location flag in type int
	 */
	public int getlocation(train t){
		int returnnumber=0;
		time tt=this.getnowtime(starttime);
		for(int i=0;i<journey.journeylist.size();i++){
			int size=journey.journeylist.get(i).gettime().size();
			if(t.gettrainid()==journey.journeylist.get(i).gettrain().gettrainid()){
				if((journey.comparetime(tt,journey.journeylist.get(i).gettime().get(size-1))==0)&&(journey.comparetime(tt,journey.journeylist.get(i).gettime().get(0))==1)){
					ArrayList<time> timeleave=journey.journeylist.get(i).gettime3();
					ArrayList<time> timeenter=journey.journeylist.get(i).gettime();
					for(int j=0;j<journey.journeylist.get(i).gettime().size()-1;j++){
						if((journey.comparetime(timeleave.get(j),tt)==0)&&(journey.comparetime(timeenter.get(j+1),tt)==1)){
							returnnumber=2*j+1;
						}else if((journey.comparetime(timeenter.get(j),tt)==0)&&(journey.comparetime(timeleave.get(j),tt)==1)){
							returnnumber=2*j;
							if(j==0){
								returnnumber=1;
							}
						}
						if(returnnumber>(((journey.journeylist.get(i).gettime().size()+1)/2-1)*2)){
							returnnumber=((journey.journeylist.get(i).gettime().size()+1)/2-1)*2*2-returnnumber;
						}
					}
				}
			}
		}
		
		return returnnumber;
	}
	/**
	 * get next station in a running train
	 * @param t a train want to get its next station
	 * @return a station flag in tyoe int
	 */
	public int getnextstation(train t){
		int returnnumber=0;
		time tt=this.getnowtime(starttime);
		for(int i=0;i<journey.journeylist.size();i++){
			int size=journey.journeylist.get(i).gettime().size();
		flag:if(t.gettrainid()==journey.journeylist.get(i).gettrain().gettrainid()){
				if((journey.comparetime(tt,journey.journeylist.get(i).gettime().get(size-1))==0)&&(journey.comparetime(tt,journey.journeylist.get(i).gettime().get(0))==1)){
					ArrayList<time> timeleave=journey.journeylist.get(i).gettime3();
					ArrayList<time> timeenter=journey.journeylist.get(i).gettime();
					for(int j=0;j<journey.journeylist.get(i).gettime().size()-1;j++){
						if((journey.comparetime(timeleave.get(j),tt)==0)&&(journey.comparetime(timeenter.get(j+1),tt)==1)){
							returnnumber=2*j+1;
						}else if((journey.comparetime(timeenter.get(j),tt)==0)&&(journey.comparetime(timeleave.get(j),tt)==1)){
							returnnumber=2*j;
							if(j==0){
								returnnumber=1;
							}
						}
						
					}
				}
				if(returnnumber==0)break flag;
				if(returnnumber>(((journey.journeylist.get(i).gettime().size()+1)/2-1)*2)){
					returnnumber=((journey.journeylist.get(i).gettime().size()+1)/2-1)*2*2-returnnumber;
					if(returnnumber!=0){
						if((returnnumber+1)%2==0){
							returnnumber--;
						}else returnnumber-=2;
					}
				}else{
					if(returnnumber!=((journey.journeylist.get(i).gettime().size()+1)/2-1)*2){
						if((returnnumber+1)%2==0)returnnumber++;
						else returnnumber+=2;
					}else returnnumber=(((journey.journeylist.get(i).gettime().size()+1)/2-1)*2)-2;
				}
			}
		}
		return returnnumber/2;
	}
	/**
	 * get next reaching station time
	 * @param t the train want to get the time
	 * @return a time flag in tyoe int
	 */
	public int getnexttime(train t){
		int returnnumber=0;
		time tt=this.getnowtime(starttime);
		for(int i=0;i<journey.journeylist.size();i++){
			int size=journey.journeylist.get(i).gettime().size();
			if(t.gettrainid()==journey.journeylist.get(i).gettrain().gettrainid()){
				if((journey.comparetime(tt,journey.journeylist.get(i).gettime().get(size-1))==0)&&(journey.comparetime(tt,journey.journeylist.get(i).gettime().get(0))==1)){
					ArrayList<time> timeleave=journey.journeylist.get(i).gettime3();
					ArrayList<time> timeenter=journey.journeylist.get(i).gettime();
					for(int j=0;j<journey.journeylist.get(i).gettime().size()-1;j++){
						if((journey.comparetime(timeleave.get(j),tt)==0)&&(journey.comparetime(timeenter.get(j+1),tt)==1)){
							returnnumber=2*j+1;
						}else if((journey.comparetime(timeenter.get(j),tt)==0)&&(journey.comparetime(timeleave.get(j),tt)==1)){
							returnnumber=2*j;
							if(j==0){
								returnnumber=1;
							}
						}
					}
				}
				if(returnnumber!=((journey.journeylist.get(i).gettime().size()+1)/2-1)*2*2){
					if(returnnumber%2==0)returnnumber=returnnumber/2;
					else returnnumber=(returnnumber+1)/2;
				}else returnnumber=((journey.journeylist.get(i).gettime().size()+1)/2-1)*2;
			}
		}
		
		return returnnumber;
	}
	/**
	 * check the input timelist whether equal to a exist one
	 * @param tt a timelist need to check
	 * @return true or false
	 */
 	public static boolean checktt(ArrayList<time> tt){
 		for(int i=0;i<tt.size()-1;i++){
 			time t1=tt.get(i);
 			time t2=tt.get(i+1);
 			if(journey.comparetime(t1, t2)==1){
 				return false;
 			}
 		}
		return true;
	}
	
}
