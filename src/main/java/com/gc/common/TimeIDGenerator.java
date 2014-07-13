package com.gc.common;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.AbstractUUIDGenerator;

public class TimeIDGenerator extends AbstractUUIDGenerator {
	
	private static final Log log=LogFactory.getLog(TimeIDGenerator.class);

	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {

		// TODO 自动生成方法存根
		return new String(new StringBuffer()
			//.append(getIP()).append(sep)
			//.append(getJVM()).append(sep)
			//.append(getHiTime()).append(sep)
			//.append(getLoTime()).append(sep)
//			.append(getTime()).append(sep)
			.append(getTimeString())
//			.append(getIPString())
			.append(getCountString())
			.toString());
	}
	
	protected String getCountString(){
		String count=String.valueOf(getCount());
		for(int i=count.length();i<5;i++){
			count="0"+count;
		}
		return count;
	}
	
	protected long getTime(){
		return System.currentTimeMillis();
	}
	
	protected String getTimeString(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
		return sdf.format(Calendar.getInstance().getTime());
	}
	
	private static int flowCount=0;
	
	private static String getFlowCount(){
		synchronized(TimeIDGenerator.class) {
			if (flowCount<0) flowCount=0;
			String count= String.valueOf(flowCount++);
			for(int i=count.length();i<4;i++){
				count="0"+count;
			}
			return count;
		}
	}
	
	private static void resetFlowCount(){
		flowCount=-1;
	}
	
	public static void main(String[] args) {
		TimeIDGenerator idg = new TimeIDGenerator();
		for(int i=0;i<100;i++){
			System.out.println(idg.generate(null,null));
		}
	}
}