package com.gc.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

@SuppressWarnings({"rawtypes","unchecked"})
public class FileUtil {

    public FileUtil() {

    }

    // 取得虚拟目录对应的磁盘路径
    // Web站点主目录的位置为<%=request.getRealPath("/")%>
    // JSP网页所在的目录位置<%=request.getRealPath("./")%>
    // JSP网页所在目录上一层目录的位置<%=request.getRealPath("../")%>

    /**
     * 创建一个新的文件 param file文件类型 return 是否创建成功的布尔型变量
     */
    public static final boolean createNewFile(File file) {
	boolean result = false;
	if (file.exists()) {
	    file.delete();
	}
	try {
	    result = file.createNewFile();
	} catch (IOException ioe) {
	    ioe.printStackTrace();
	}
	return result;
    }

    /**
     * 通过文件名称创建一个新的文件
     * 
     * @param filename要创建的文件名称
     * @return 是否创建成功的布尔型变量
     */
    public static final boolean createNewFile(String filename) {
	File file = new File(filename.trim());
	boolean result = false;
	if (file.exists()) {
	    file.delete();
	}
	try {
	    result = file.createNewFile();
	} catch (IOException ioe) {
	    ioe.printStackTrace();
	}
	return result;
    }

    /**
     * 判断某个文件是否存在
     * 
     * @param fileName
     *            文件名称
     * @return 是否存在的布尔型变量
     */
    public static final boolean isFileExists(String fileName) {
	File file = new File(fileName.trim());
	return file.exists();
    }

    public static final boolean isFileExists(File file) {
	return file.exists();
    }

    /**
     * 创建一个新目录
     * 
     * @file 要创建的文件目录
     * @return 是否创建成功
     */
    public static final boolean createFolder(File file) {
	boolean result = false;
	try {
	    if (file.exists()) {
		file.delete();
	    }
	    result = file.mkdir();
	    ;

	} catch (Exception ex) {
	    result = false;
	    ex.printStackTrace();
	}
	return result;
    }

    /**
     * 创建一个新目录
     * 
     * @folderName 要创建的文件目录名
     * @filePath 创建文件的路径
     * @return 是否创建成功
     */
    public static final boolean createFolder(String folderName) {
	boolean result = false;
	File file = new File(folderName.trim());
	try {
	    if (file.exists()) {
		file.delete();
	    }
	    result = file.mkdir();
	    ;

	} catch (Exception ex) {
	    result = false;
	    ex.printStackTrace();
	}
	return result;
    }

    /**
     * 读取文件内容
     * 
     * @param file
     *            文件信息
     * @return 文件内容的集合列表，文件每一行是列表的一个值
     */
   
	
	public List readFileToList(File file) {
	BufferedReader breader;
	List list;
	String line;
	list = new ArrayList();
	try {
	    breader = new BufferedReader(new FileReader(file));
	    while ((line = breader.readLine()) != null)
		list.add(line);
	    breader.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return list;
    }

    /**
     * 复制文件内容，从一源文件复制到目标文件
     * 
     * @param sourcename
     *            源文件
     * @param targetname
     *            目标文件
     * @throws Exception
     */
    public void copyFile(String sourcename, String targetname) throws Exception {
	BufferedReader breader;
	BufferedWriter bwriter;
	try {
	    breader = new BufferedReader(new FileReader(sourcename.trim()));
	    bwriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetname.trim())));
	    while (breader.ready())
		bwriter.write(breader.read());
	    breader.close();
	    bwriter.close();
	} catch (Exception e) {
	    throw e;
	}
    }

    /**
     * 在文件中添加内容
     * 
     * @param 要添加的字符串
     * @param filename
     *            目标文件名
     * @throws Exception
     */
    public void appendToFile(String str, String filename) throws Exception {
	FileOutputStream stream;
	OutputStreamWriter writer;
	try {
	    stream = new FileOutputStream(filename.trim(), true);
	    writer = new OutputStreamWriter(stream);
	    writer.write(str);
	    writer.close();
	    stream.close();
	} catch (Exception e) {
	    throw e;
	}
    }

    /**
     * 输出目录中的所有文件名
     * 
     * @param filePath
     *            目录名 return 文件名数组
     */
    public static final String[] readPathFile(String filePath) {
	File file = new File(filePath.trim());
	File[] tempFile = file.listFiles();
	String[] filestr = new String[tempFile.length];
	for (int i = 0; i < tempFile.length; i++) {
	    if (tempFile[i].isFile()) {
		filestr[i] = tempFile[i].getName();
	    }
	}
	return filestr;
    }

    /**
     * 输出目录中某种文件扩展名的文件
     * 
     * @param filePath
     *            目录路径
     * @param extension
     *            文件扩展名
     * @return 文件名数组
     */
    public static final String[] readExtFile(String filePath, String extension) {
	File file = new File(filePath.trim());
	File[] tempFile = file.listFiles();
	String[] filestr = new String[tempFile.length];
	int ie = 0;
	for (int i = 0; i < tempFile.length; i++) {
	    if (tempFile[i].isFile()) {
		String filename = tempFile[i].getName();
		ie = filename.lastIndexOf('.');
		if (filename.substring(ie, filename.length()).equals(extension.trim())) {
		    filestr[i] = filename;
		}
	    }
	}
	return filestr;
    }

    /**
     * 输出目录中的所有文件夹名称
     * 
     * @param filePath
     *            文件夹路径
     * @return 文件夹名称数组
     */
    public static final String[] readFolderByFile(String filePath) {
	File file = new File(filePath.trim());
	File[] tempFile = file.listFiles();
	String[] foldertr = new String[tempFile.length];
	;
	for (int i = 0; i < tempFile.length; i++) {
	    if (tempFile[i].isDirectory()) {
		foldertr[i] = tempFile[i].getName();
	    }
	}
	return foldertr;
    }

    /**
     * 检查文件中是否为一个空
     * 
     * @param filePath
     * @param fileName
     * @return 为空返回true
     * @throws IOException
     */
    public static final boolean fileIsNull(File file) throws IOException {
	boolean result = false;
	FileReader fr = new FileReader(file);
	if (fr.read() == -1) {
	    result = true;

	} else {
	    result = false;
	}
	fr.close();
	return result;
    }

    /**
     * 
     * @param filePath
     * @param fileName
     * @return
     * @throws IOException
     */
    public static final String readAllFile(String filePath, String fileName) throws IOException {
	FileReader fr = new FileReader(filePath.trim() + fileName.trim());
	String fileContent = "";
	int count = fr.read();
	while (count != -1) {
	    fileContent = fileContent + (char) count;
	    count = fr.read();
	    if (count == 13) {
		fr.skip(1);
	    }
	}
	fr.close();
	return fileContent;
    }

    /**
     * 一行一行的读取文件中的数据
     * 
     * @param filePath
     *            文件路径
     * @param fileName
     *            文件名称
     * @return 文件内容的集合。
     * @throws IOException
     */
    public ArrayList readLineFile(String filePath, String fileName) throws IOException {
	FileReader fr = new FileReader(filePath.trim() + fileName.trim());
	BufferedReader br = new BufferedReader(fr);
	ArrayList list = new ArrayList();
	String line = br.readLine();
	while (line != null) {
	    line = br.readLine();
	    list.add(line);
	}
	br.close();
	fr.close();
	return list;
    }

    /**
     * 将字符串数组写入到文件
     * 
     * @param filePath
     *            (文件路径)
     * @param fileName
     *            (文件名)
     * @param args
     *            [] 要写入的字符串数组
     * @throws IOException
     */
    public void writeFile(String filePath, String fileName, String[] args) throws IOException {
	FileWriter fw = new FileWriter(filePath.trim() + fileName.trim());
	PrintWriter out = new PrintWriter(fw);
	for (int i = 0; i < args.length; i++) {
	    out.write(args[i]);
	    out.println();
	    out.flush();
	}
	fw.close();
	out.close();
    }

    /**
     * 将一字符串写入到文件
     * 
     * @param filePath
     *            (文件路径)
     * @param fileName
     *            (文件名)
     * @param args
     *            要写入的字符串
     * @throws IOException
     */
    public void writeFile(String filePath, String fileName, String args) throws IOException {
	FileWriter fw = new FileWriter(filePath.trim() + fileName.trim());
	fw.write(args);
	fw.close();
    }

    /***************************************************************************
     * 取得一个文件名称的扩展名
     * 
     * @param FileName
     *            取得扩展名的文件名称 return 文件的扩展名
     */
    public static final String getFileExt(String fileName) {
	int i = fileName.lastIndexOf(".");
	String fileExt = "";
	if (i != -1) {
	    fileExt = fileName.substring(i + 1, fileName.trim().length());
	}
	return fileExt;
    }

    /***************************************************************************
     * 取得一个文件名称的路径
     * 
     * @param FileName
     *            文件的路径
     */
    public static final String getFilePath(String fileName) {
	int i = fileName.lastIndexOf("/");
	String path = "";
	if (i != -1) {
	    path = fileName.substring(0, i + 1);
	}
	return path;
    }

    /***************************************************************************
     * 根据文件的相对路径文件名称取得，文件的名称
     * 
     * @param FileName
     *            文件名称 return相对路径文件名称
     */
    public static final String getDocFileName(String fileName) {
	int i = fileName.lastIndexOf("/");
	String file = "";
	if (i != -1) {
	    file = fileName.substring(i + 1, fileName.trim().length());
	}
	return file;
    }

    /***************************************************************************
     * 取得一个文件名称的路径，反斜杠
     * 
     * @param FileName
     *            文件的路径
     */
    public static final String getFilePath2(String fileName) {
	int i = fileName.lastIndexOf("\\");
	String path = "";
	if (i != -1) {
	    path = fileName.substring(0, i + 1);
	}
	return path;
    }

    /***************************************************************************
     * 根据文件的相对路径文件名称取得，文件的名称，反斜杠
     * 
     * @param FileName
     *            文件名称 return相对路径文件名称
     */
    public static final String getDocFileName2(String fileName) {
	int i = fileName.lastIndexOf("\\");
	String file = "";
	if (i != -1) {
	    file = fileName.substring(i + 1, fileName.trim().length());
	}
	return file;
    }

    /***************************************************************************
     * 取得一个新的临时文件名称，该名称不带路径
     * 
     * @param FileExt
     *            文件的扩展名 文件的扩展名
     */
    public static final String getFileName(String fileExt) {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	Date currentTime = new Date();
	String fileName = formatter.format(currentTime);
	int i = (int) (Math.random() * 1000000000);
	String str = i + "";
	i = str.length() - 4;
	if (i < 0) {
	    i = 0;
	}
	;
	String sub = str.substring(i, str.length());
	fileName = fileName + sub;
	if (fileExt.length() > 0) {
	    fileName = fileName + "." + fileExt;
	}
	return fileName;
    }

    // 得到字符串中的子串的个数
    /*
     * public static int getSubtringCount(String source, String sub) { if
     * (source == null || source.length() == 0) { return 0; } int count = 0; int
     * index = source.indexOf(sub); while (index >= 0) { count++; index =
     * source.indexOf(sub, index + 1); } return count; }
     */

    // 通过FILE获取文件扩展名
    public static final String getExtension(File f) {
	return (f != null) ? getExtension(f.getName()) : "";
    }

    // 通过文件名获取此文件的扩展名
    public static final String getExtension(String filename) {
	return getExtension(filename, "");
    }

    public static final String getExtension(String filename, String defExt) {
	if ((filename != null) && (filename.length() > 0)) {
	    int i = filename.lastIndexOf('.');

	    if ((i > -1) && (i < (filename.length() - 1))) {
		return filename.substring(i + 1);
	    }
	}
	return defExt;
    }

    // 去除文件扩展名，返回文件名
    public static final String trimExtension(String filename) {
	if ((filename != null) && (filename.length() > 0)) {
	    int i = filename.lastIndexOf('.');
	    if ((i > -1) && (i < (filename.length()))) {
		return filename.substring(0, i);
	    }
	}
	return filename;
    }

    /**
     * 删除文件
     * 
     * @param filePath
     */
    public static void deleteFile(String filePath) {
	if (filePath != null && filePath.length() > 0)
	    try {
		String classPath = ClassPath.getClassPath();
		filePath = classPath.substring(0, classPath.indexOf("WEB-INF")) + filePath;
		File file = new File(filePath);
		boolean f = file.delete();
		System.out.println("ff==" + f);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
    }

    /**
     * 重命名文件名，在原文件名后加上 _yyyyMMddHHmmss
     * 
     * @param args
     * @throws Exception
     */
    public static String getTimeFileName(String fileName) {
	SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
	String name = "";
	if (fileName.indexOf(".") > -1) {
	    name = fileName.substring(0, fileName.lastIndexOf(".")) + "_" + sd.format(Calendar.getInstance().getTime()) + fileName.substring(fileName.indexOf("."));
	} else {
	    name = fileName + "_" + sd.format(Calendar.getInstance().getTime());
	}
	return name;
    }

    /**
     * 取得yyyyMM的目录名
     * 
     * @return
     */
    public static String getTimeFileFolder() {
	SimpleDateFormat ym = new SimpleDateFormat("yyyyMM");
	return ym.format(Calendar.getInstance().getTime());
    }

    
	/**
	 *得到以时间字符串命名的文件名
	 */
	public static String getTimeString(String fileName){
		String time = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(System.currentTimeMillis());
		String saveFile = time+fileName.substring(fileName.lastIndexOf("."));
		return saveFile;
	}
	
	/**
	 * 2014-5-12,gongchang
	 * 功能： 保存文件
	 * 逻辑描述： 
	 * 参数：无
	 * 返回结果：无
	 */
	public static void saveFile(File file,String saveDir,String fileName) throws Exception{
		File savefile = new File(new File(saveDir),fileName);
        if(!savefile.getParentFile().exists()) {
               savefile.getParentFile().mkdirs();
        }
        try {
               FileUtils.moveFile(file, savefile);
        } catch (IOException e) {
               e.printStackTrace();
        }
	}
    
	/**
	 * 2013-8-1 下午11:16:41  钟道侨
	 * 功能说明：根据路劲删除图片
	 * @param fileUrl
	 */
	public static void deleteFileByURL2(String fileUrl){
		File file=new File(ServletActionContext.getServletContext().getRealPath("/")+fileUrl);
		if(file.exists()){
			file.delete();
		}	
	}
	
    public static void main(String[] args) throws Exception {
//	FileUtil fu = new FileUtil();
	// File file = new File("D:/金财工程/ddd.doc");
	// String str[] = fu.readFolderByFile("D:/金财工程");
	// System.out.println(fu.fileIsNull(file));
	/*
	 * for(int i=0;i<str.length;i++) { System.out.println(str[i]); }
	 */
//	String path = "G:/eclipse/workspace/ITIL-qy/pages/itil/upload/doc/20071126183847_1.zip";
//	fu.deleteFile(path);
    }

	public static String getCurrentTimeString() {
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(System.currentTimeMillis());
	}

}
