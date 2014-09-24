package com.gc.util;
import javax.servlet.http.HttpServletRequest;

public class ClassPath {
	
	public String getClassAbsolutePath(String className) {

		if (!className.startsWith("/")) {
			className = "/" + className;
		}
		className = className.replace('.', '/');
		// className = className + ".class";

		java.net.URL classUrl = this.getClass().getResource(className);

		if (classUrl != null) {
			return classUrl.getFile();
		}
		else {
			return null;
		}
	}
	
	/**
	 * 取得.../WEB-INF/classes路径
	 * @return
	 * since 2007-7-3 上午10:23:45
	 */
	public static String getClassPath(){
		String cp="";
		try{
			if(ClassPath.class.getClassLoader().getResource("").getPath()!=null&&ClassPath.class.getClassLoader().getResource("").getPath().indexOf("/WEB-INF/classes")>-1){
				cp=ClassPath.class.getClassLoader().getResource("").getPath();
			}
			else if(ClassPath.class.getClassLoader().getResource("/").getPath()!=null&&ClassPath.class.getClassLoader().getResource("/").getPath().indexOf("/WEB-INF/classes")>-1){
				cp=ClassPath.class.getClassLoader().getResource("/").getPath();
			}
			else if(ClassPath.class.getResource("/").getPath()!=null&&ClassPath.class.getResource("/").getPath().indexOf("/WEB-INF/classes")>-1){
				cp=ClassPath.class.getResource("/").getPath();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return cp;
	}
	
	/**
	 * 取得.../pages路径
	 * @return
	 * since 2007-7-3 上午10:23:45
	 */
	public static String getPagePath(){
		if(getClassPath().indexOf("/WEB-INF")>0){
			return getClassPath().substring(0,getClassPath().indexOf("/WEB-INF"));
		}
		else{
			return getClassPath();
		}
	}
	
	public static String getPath(String url,HttpServletRequest request){
        return url.replaceAll("http://" + request.getServerName() + ":" + request.getServerPort(), "");
    }
    
    public static String getRootPath(HttpServletRequest request){
        String classPath = ClassPath.getClassPath();
        return  classPath.substring(0,classPath.indexOf("WEB-INF"));
    }
	
	public static void main(String[] args){
	    System.out.println(ClassPath.getClassPath());
	}

}
