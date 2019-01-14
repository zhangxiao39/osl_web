package com.osl.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.channels.FileChannel;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.osl.common.web.RedisUtils;

/**
 * 共通方法的定义
 */
public class Util {
	
	/**
	 * 空检查
	 */
	public static boolean isEmpty(Object value) {
		if (value == null) {
			return true;
		} else {
			if (value instanceof String) {
				String strTmp = (String) value;
				if (strTmp.trim().length() == 0) {
					return true;
				} else {
					return false;
				}
			} else if (value instanceof Collection) {
				Collection<?> datalist = (Collection<?>) value;
				if (datalist.size() == 0) {
					return true;
				} else {
					return false;
				}
			} else if (value instanceof Map) {
				Map<?, ?> datalist = (Map<?, ?>) value;
				if (datalist.size() == 0) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}

	public static boolean isEmptyList(Collection<?> list) {
		if (list == null) {
			return true;
		} else if (list.size() <= 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isEmptyMap(Map<?, ?> list) {
		if (list == null) {
			return true;
		} else if (list.size() <= 0) {
			return true;
		} else {
			return false;
		}
	}
	
    /**
     * 正则表达式：判断是否数字
     * @param str
     * @return
     */
    public static boolean isNumer(String str){ 
        Pattern pattern = Pattern.compile("[0-9]*"); 
        return pattern.matcher(str).matches();    
     }
    
    /**
     * 正则表达式：判断是否为正小数
     * @param str
     * @return
     */
    public static boolean isPositiveDecimal(String orginal){  
        return isMatch("\\+{0,1}[0]\\.[1-9]*|\\+{0,1}[1-9]\\d*\\.\\d*", orginal);  
    }  
    private static boolean isMatch(String regex, String orginal){  
        if (orginal == null || orginal.trim().equals("")) {  
            return false;  
        }  
        Pattern pattern = Pattern.compile(regex);  
        Matcher isNum = pattern.matcher(orginal);  
        return isNum.matches();  
    }  
    /**
     * 正则表达式：判断是否数字和字符串
     * @param str
     * @return
     */
    public static boolean isNumeAndStr(String str){ 
        return str.matches("[0-9a-zA-Z]*");
     }
	private static final String REG_RXPRE_RESV[] = { "\\.", "\\^", "\\$", "\\[", "\\]", "\\*", "\\+", "\\?", "\\|", "\\(", "\\)" };

	/**
	 * 正则表达式ReplaceAll
	 * 
	 * @param value
	 *            要替换的字符
	 * @param origStr
	 *            替换的原字符串
	 * @param replStr
	 *            替换的字符串
	 * @return
	 */
	public static String replaceAll(String value, String origStr, String replStr) {
		if ((value == null) || (origStr == null)) {
			return value;
		} else {
			if (replStr == null) {
				replStr = UtilConst.EMPTY_STRING;
			}
			for (String tmp : REG_RXPRE_RESV) {
				origStr = origStr.replaceAll(tmp, "\\" + tmp);
			}
			return value.replaceAll(origStr, replStr);
		}
	}

	/**
	 * 系统路径取得
	 */
	public static String getRuntimePath() {
		String strTmp = Util.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		int pos = strTmp.indexOf("classes");
		if (pos >= 0) {
			return strTmp.substring(0, pos + 8);
		} else {
			return UtilConst.EMPTY_STRING;
		}
	}

	/**
	 * project路径取得
	 */
	public static String getProjectPath() {
		return System.getProperty("user.dir");
	}

	/**
	 * 换行文字
	 * 
	 * @return
	 */
	public static String getLineSep() {
		return System.getProperty("line.separator");
	}

	/**
	 * 
	 * @param totalRecNum
	 * @param perPageNum
	 * @return 总页数
	 */
	public static int getTotalPage(int totalRecNum, int perPageNum) {
		if ((totalRecNum <= 0) || (perPageNum <= 0)) {
			return 0;
		} else {
			int result = totalRecNum / perPageNum;
			if ((result * perPageNum) == totalRecNum) {
				return result;
			} else {
				return result + 1;
			}
		}
	}

	/**
	 * 指定长度
	 * 
	 * @param size
	 * @return Random
	 */
	public static String getRandomStr(int size) {
		if (size <= 0) {
			return UtilConst.EMPTY_STRING;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			int pos = (int) (Math.random() * 62);
			if (pos < 0) {
				pos = 0;
			} else if (pos >= 62) {
				pos = 61;
			}
			sb.append(UtilConv.STR_62.charAt(pos));
		}
		return sb.toString();
	}

	/**
	 * 获取byte数
	 * 
	 * @param value
	 * @return
	 */
	public static int getByteNum(String value) {
		if (value == null) {
			return 0;
		}
		return value.getBytes().length;
	}

	/**
	 * 替换：[+][/]　→　[_][-]
	 * 
	 * @param sessId
	 * @return
	 */
	public static String convSessionId(String sessId) {
		if (sessId == null) {
			return UtilConst.EMPTY_STRING;
		}
		String result = sessId.replaceAll("\\+", "_");
		return result.replaceAll("/", "-");
	}

	/**
	 * 替换：[_][-]　→　[+][/]
	 * 
	 * @param sessId
	 * @return
	 */
	public static String convSessionIdRev(String sessId) {
		if (sessId == null) {
			return UtilConst.EMPTY_STRING;
		}
		String result = sessId.replaceAll("_", "+");
		return result.replaceAll("-", "/");
	}

	static final String CERT_BASE_LIST = "0123456789ABCDEFGHIJKLMNOPQRSTUWXYZabcdefghijklmnopqrstuwxyz_-";

	public static String getOcxCertifyPassword(int pwdLen) {
		StringBuffer sbCertifyPassword = new StringBuffer();
		int listLen = CERT_BASE_LIST.length();
		for (int iNum = 0; iNum < pwdLen; iNum++) {
			Random rand = new Random();
			int iPos = (int) (listLen * rand.nextDouble());
			if (iPos < 0) {
				iPos = 0;
			}
			if (iPos >= listLen) {
				iPos = listLen - 1;
			}
			sbCertifyPassword.append(CERT_BASE_LIST.charAt(iPos));
		}
		return sbCertifyPassword.toString();
	}

	/**
	 * @param param
	 * @param value
	 *            [|]
	 * @return
	 */
	public static List<String> splitString(String value, String param) {
		List<String> result = new ArrayList<String>();
		if (value != null) {
			String list[] = value.split("\\" + param);
			for (int i = 0; i < list.length; i++) {
				result.add(list[i]);
			}
		}
		return result;
	}

	/**
	 * @param param
	 * @param value
	 *            [|]
	 * @return
	 */
	public static List<String> splitFullString(String value, String param) {
		List<String> result = new ArrayList<String>();
		if (isEmpty(value)) {
			return result;
		} else if (isEmpty(param)) {
			result.add(value);
			return result;
		} else {
			while (true) {
				int iPos = value.indexOf(param);
				if (iPos < 0) {
					result.add(value);
					break;
				} else {
					result.add(value.substring(0, iPos));
					value = value.substring(iPos + param.length());
				}
			}
			return result;
		}
	}

	/**
	 * CharArray
	 *
	 * @param array
	 * @param beginIndex
	 * @param endIndex
	 */
	public static char[] getCharArray(char[] array, int beginIndex, int endIndex) {
		if (array == null) {
			return null;
		}
		char[] tmp = new char[endIndex - beginIndex];
		int count = 0;
		for (int i = beginIndex; i < endIndex; i++) {
			if (i < array.length) {
				tmp[i - beginIndex] = array[i];
				count++;
			} else {
				break;
			}
		}
		char[] ret = new char[count];
		for (int i = 0; i < count; i++) {
			ret[i] = tmp[i];
		}
		return ret;
	}

	/**
	 * 当前时间 Timestamp
	 * 
	 * @return
	 */
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 当前日期
	 * 
	 * @return
	 */
	public static java.sql.Date getCurrentDate() {
		Date date = new Date();
		return new java.sql.Date(date.getTime());
	}

	public static java.sql.Date timestampToDate(Timestamp p) {
		if (p == null) {
			return null;
		}
		return new java.sql.Date(p.getTime());
	}

	/**
	 * 保存文件
	 * 
	 * @param is
	 *            输入流
	 * @param filepath
	 *            输出文件地址
	 * @throws Exception
	 */
	public static void saveFile(BufferedInputStream is, String filepath) throws Exception {
		int bytesRead = 0;
		byte[] buffer = new byte[1024];
		FileOutputStream fos = new FileOutputStream(filepath);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
			bos.write(buffer, 0, bytesRead);
		}
		bos.close();
		fos.close();
	}

	public static List<String> findDates(Date dBegin, Date dEnd) {
		List<String> lDate = new ArrayList<String>();
		lDate.add(UtilConv.date2Str(dBegin, UtilConv.DATE_YYYY_MM_DD_CHN));
		Calendar calBegin = Calendar.getInstance();
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(dEnd);
		while (dEnd.after(calBegin.getTime())) {
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(UtilConv.date2Str(calBegin.getTime(), UtilConv.DATE_YYYY_MM_DD_CHN));
		}
		return lDate;
	}

	public static List<String> findMonths(Date dBegin, Date dEnd) {
		List<String> lDate = new ArrayList<String>();
		lDate.add(UtilConv.date2Str(dBegin, UtilConv.YEAR_MONTH_));
		Calendar calBegin = Calendar.getInstance();
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(dEnd);
		while (dEnd.after(calBegin.getTime())) {
			calBegin.add(Calendar.MONTH, 1);
			lDate.add(UtilConv.date2Str(calBegin.getTime(), UtilConv.YEAR_MONTH_));
		}
		return lDate;
	}

	/**
	 * 判断当前的request请求是否为ajax请求
	 * 
	 * @param request
	 * @return true表示是ajax
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		if (header != null && "XMLHttpRequest".equals(header)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除文件夹中的文件（递归删除）
	 * 
	 * @param path
	 */
	public static void clearFolder(String path) {
		File file = new File(path);
		if (file.isDirectory()) {
			String[] list = file.list();
			for (String str : list) {
				clearFolder(path + "/" + str);
			}
		} else {
			file.delete();
		}
	}

	/**
	 * 删除文件夹中的文件（不递归删除）
	 * 
	 * @param path
	 */
	public static void clearFolderL1(String path) {
		File file = new File(path);
		if (file.isDirectory()) {
			String[] list = file.list();
			for (String str : list) {
				file = new File(path + "/" + str);
				if (!file.isDirectory()) {
					file.delete();
				}
			}
		}
	}

	/**
	 * 执行可执行文件
	 * 
	 * @param command
	 * @throws Exception
	 */
	public static void execute(String path) {
		Process proc;
		try {
			proc = Runtime.getRuntime().exec(path);
			proc.getOutputStream().close();
			StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), "Error");
			StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(), "Output");
			errorGobbler.start();
			outputGobbler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static class StreamGobbler extends Thread {
		private InputStream is;
		private String type;

		public StreamGobbler(InputStream is, String type) {
			this.is = is;
			this.type = type;
		}

		@Override
		public void run() {
			try {
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String line = null;
				while ((line = br.readLine()) != null) {
					if (type.equals("Error")) {
						System.err.println(line);
					} else {
						System.out.println(line);
					}
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	/**
	 * 获取扩展名
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileExt(String filePath) {
		String fileExt = UtilConst.EMPTY_STRING;
		if (filePath == null) {
			return fileExt;
		}
		int iPos = filePath.lastIndexOf(".");
		if (iPos >= 0) {
			fileExt = filePath.substring(iPos + 1);
		}
		return fileExt;
	}

	/**
	 * 拼接字符串
	 * 
	 * @param obj1
	 *            字符串1（可以是Object类型，会自动转换）
	 * @param obj2
	 *            字符串1（可以是Object类型，会自动转换）
	 * @param combineSymbol
	 *            拼接标识字符串
	 * @return
	 * @return String
	 * @version 1.0
	 */
	public static String combineStr(String combineSymbol, Object... objs) {
		StringBuffer sb = new StringBuffer();
		for (Object obj : objs) {
			sb.append(UtilConv.objToStr(obj));
			sb.append(UtilConv.objToStr(combineSymbol));
		}
		return sb.substring(0, sb.length() - combineSymbol.length());
	}

	/**
	 * 文件拷贝
	 * 
	 * @param oldFilename
	 * @param newFilename
	 * @param bNotOverWrite
	 */
	public synchronized static void copyFile(String oldFilename, String newFilename, boolean bNotOverWrite) {
		FileChannel ifc = null;
		FileChannel ofc = null;
		try {
			File fileNew = new File(newFilename);
			if ((bNotOverWrite && fileNew.exists())) {
				// 文件已存在、不覆盖模式的情况下，不处理
				return;
			}
			File file = new File(oldFilename);
			if (!file.exists()) {
				// 文件不存在
				return;
			}
			@SuppressWarnings("resource")
			FileInputStream fis = new FileInputStream(file);
			ifc = fis.getChannel();
			@SuppressWarnings("resource")
			FileOutputStream fos = new FileOutputStream(fileNew);
			ofc = fos.getChannel();
			// 数据传输
			ifc.transferTo(0, ifc.size(), ofc);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ifc != null) {
				try {
					ifc.close();
				} catch (IOException e) {
				}
			}
			if (ofc != null) {
				try {
					ofc.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static String createSetGetCode(Class<?> clsFrom, Class<?> clsTo) {
		StringBuffer sb = new StringBuffer();
		Field[] declaredFields = clsTo.getDeclaredFields();
		String toName = "toData";
		String fromName = "fromData";
		for (Field field : declaredFields) {
			String fieldConvStr = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			sb.append(toName).append(".set").append(fieldConvStr).append("(").append(fromName).append(".get").append(fieldConvStr).append("()").append(")").append(";").append(Util.getLineSep());
		}
		return sb.toString();
	}
	
	/**
	 * 去除头和尾的字符串
	 * @param value 传入的字符串
	 * @param replaceFirst 去除第一个字符串
	 * @param subStringLast 去除的最后一个字符串
	 * @return
	 */
	public static String removeFirstAndLastString(String value,String replaceFirst,String subStringLast){
		if(!Util.isEmpty(value)){
			String newValue = value.substring(0,value.lastIndexOf(subStringLast));
			newValue = newValue.replaceFirst(replaceFirst, "");
			return newValue;
		}else{
			return null;
		}
	}

    /**
     * 检查文件是否存在
     * @param value
     * @return
     */
    public static boolean isNotValidFile(String value) {
		if (Util.isEmpty(value)) {
			return false;
		}

		File file = new File(value);
		if (!file.exists() || !file.isFile()) {
			return true;
		} else {
			return false;
		}
	}

    /**
	 * 根据当前时间创建时间点
	 * @param qzzm 前置字母
	 * @return
	 */
	public static String getTimeDot(String qzzm){
		StringBuffer timeDot = new StringBuffer();
		String nowStr = UtilDateTime.getNowStr(UtilDateTime.DATE_TIME_FULL_PAT_14);
		
		if(!Util.isEmpty(qzzm)){
			timeDot.append(qzzm);
			timeDot.append("_");
		}
		timeDot.append(nowStr);
		return timeDot.toString();
	}
	
	/**
	 * 利用数据库自己查询序号模式，生成各个数据库表的id
	 * id格式 → (表标识 + 用户id[6位，0补齐] + 时间[年+月形式] + 序号[当前表中，当前用户，当前年月的序号])
	 * id格式样例【纳品详情表为例】：纳品明细ID → NPD+000001+201812+00000001
	 * id中的表标识：表的拼音首字母+(总表【C】/详情表【D】)
	 * 
	 * @author zhangzy
	 * 
	 * @param tableKey
	 * @param businessId
	 * @param time
	 * @param number
	 * @return
	 */
	public static String generateTableIdByDB(String tableKey , long businessId , long number) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		StringBuffer str = new StringBuffer();
		str.append(tableKey);
		str.append(fillingId(businessId , 6));
		str.append(sdf.format(new Date()));
		str.append(fillingId(number , 8));
		return str.toString();
	}
	
	/**
	 * 利用redis模式，生成各个数据库表的id
	 * id格式 → (表标识 + 用户id[6位，0补齐] + 时间[年+月形式] + 序号[当前表中，当前用户，当前年月的序号])
	 * id格式样例【纳品详情表为例】：纳品明细ID → NPD+000001+201812+00000001
	 * id中的表标识：表的拼音首字母+(总表【C】/详情表【D】)
	 * 
	 * @author zhangzy
	 * 
	 * @param tableKey
	 * @param businessId
	 * @param time
	 * @param number
	 * @return
	 */
	public static String generateTableIdByRedis(RedisUtils redisUtils ,String tableKey , long businessId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		StringBuffer str = new StringBuffer();
		StringBuffer redisKey = new StringBuffer();
		str.append(tableKey);
		str.append(fillingId(businessId , 6));
		str.append(sdf.format(new Date()));
		String key = redisKey.append(tableKey).append(fillingId(businessId , 6)).toString();
		long number = redisUtils.incr(key , 1);
		str.append(fillingId(number , 8));
		/*Jedis jedis = null;
		try {
			jedis = RedisUtil.getJedis();
	        long number = jedis.incr(str);
	        str.append(fillingId(number , 8));
		}catch(Exception e) {
			jedis.decr(str);
			str.append(fillingId(1 , 8));
		}*/
		return str.toString();
	}
	
	/**
	 * 用于补齐字符串
	 * @author zhangzy
	 * 
	 * @param object
	 * @param digit
	 * @return
	 */
	public static String fillingId(long object , int digit) {
		String str=String.format("%0"+digit+"d", object);
		return str;
	}
	
	public static void main(String[] args) {
		// String filePath =
		// "G:/Software/eclipse-LN/workspace/DigitalCampus/src/resourceToChg.bat";
		// // 转换为properties文件
		// Util.execute(filePath);
		//System.out.println(createSetGetCode(EmployInfoEntity.class, CleanResultEntity.class));
		// System.out.println(Util.combineStr("_", "aa", "bb", "cc"));
	}
}
