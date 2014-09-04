package com.gc.sys.common;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.AbstractUUIDGenerator;

public class TimeIDGeneratorLong extends AbstractUUIDGenerator {
	
	private static final Log log=LogFactory.getLog(TimeIDGeneratorLong.class);

	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {

		// TODO 自动生成方法存根
		return new Long(new StringBuffer()
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(Calendar.getInstance().getTime());
	}
	
	private static int flowCount=0;
	
	private static String getFlowCount(){
		synchronized(TimeIDGeneratorLong.class) {
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
	
	public static void main(String args[]) {
		TimeIDGeneratorLong idg = new TimeIDGeneratorLong();
		for(int i=0;i<54;i++){
			//log.info(idg.generate(null,null));
			System.out.println(idg.generate(null,null));
		}
//		log.info(idg.getIP());
//		try {
//			log.info( InetAddress.getLocalHost().getAddress());
//			log.info("---------------");
//			byte[] b= InetAddress.getLocalHost().getAddress();
//			for(int i=0;i<b.length;i++){
//				log.info(b[i] << 8);
//				log.info(Byte.MIN_VALUE);
//				log.info(b[i]);
//				log.info((b[i] << 8 ) - Byte.MIN_VALUE + (int) b[i]);
//				log.info("---------------");
//			}
//		}
//		catch (UnknownHostException e) {
//			// TODO 自动生成 catch 块
//			e.printStackTrace();
//		}
//		log.info(IPUtils.getFirstNoLoopbackAddress());
//		String str=IPUtils.getFirstNoLoopbackAddress();
//		str="255.255.255.255";
//		log.info(Base64Util.base64Encoder(str));
//		log.info(EncryptUtil.getEncodedPassword(str));
//		str="255.255.255.254";
//		log.info(EncryptUtil.getEncodedPassword(str));
//		log.info(Base64Util.base64Decoder("MTAuMjEwLjI5LjM3"));
//		log.info(Base64Util.base64Encoder("192.168.111.120"));
//		log.info(""+System.currentTimeMillis());
//		try {
//			byte[] bt=InetAddress.getLocalHost().getAddress();
//			for(int i=0;i<bt.length;i++){
//				log.info(""+bt[i]);
//				log.info(""+(255-bt[i]-Byte.MIN_VALUE));
//			}
//		}
//		catch (UnknownHostException e) {
//			log.error(IDGenerator.class,e);
//		}
	}
	

}