package shu.cloud.main;

import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import shu.cloud.model.TaskFileData;
import shu.cloud.model.TaskFileDataDAO;
import shu.cloud.tools.OperateExcel;

public class GeoTimer{
	private Timer timer=null;
	public GeoTimer() {
		super();
		// TODO Auto-generated constructor stub
		
	}

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GeoTimer geo=new GeoTimer();
		geo.Reminder(0,5);

	}
	 public void Reminder(int delay,int seconds){  
		  timer = new Timer();  
	      timer.schedule(new MainHandle(), delay,seconds*1000);  
	}  

}
