package com.TestFrame;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Run {
	
	public String initTime;
	public String endTime;
	public String result;

	public static List<String[]> runTestSuit(String excel, String sheetName) throws SecurityException, IOException{
		SimpleDateFormat ft = new SimpleDateFormat("yyyy_MM_dd_HHmmss"); //文件名时间戳格式
		SimpleDateFormat ft2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	//log时间戳格式	
		Date suitBegTime = new Date(); //用例集开始时间
		Log log = new Log(ft.format(suitBegTime), "INFO"); //初始化日志
		Keyword k = new Keyword(log); //初始化关键字
		TestSuit ts = new TestSuit(excel, sheetName); //读取用例集
		Param p = new Param(); //全局参数
		List<String[]> toExcel= new ArrayList<String[]>(); //用于写入excel中的信息
		List<String[]> suit = ts.getTestSuit(); //获取用例集信息
		//		 suit.get(0)[3]="";
		List<List<String>> cases = ts.getTestCaseColletion(); //获取用例集的步骤集合
		for(String[] c : suit){
			c[4] = ft2.format(new Date()); //写入用例开始执行时间
			boolean result = true;
			log.write("INFO", "---------------CASE START: "+c[1]+"---------------");
			for(int i = Integer.valueOf(c[2]); i<=Integer.valueOf(c[3]) ;i++ ){
				String action = cases.get(Integer.valueOf(i)).get(2); //action
				//如果p1单元格为空,则填空字符串
				String p1="";
				if(cases.get(Integer.valueOf(i)).size() > 3){
					p1 = cases.get(Integer.valueOf(i)).get(3); //p1
					p1 = p.getParam(p1);
				}
				//如果p2单元格为空,则填空字符串
				String p2 = "";
				if(cases.get(Integer.valueOf(i)).size() > 4){
					p2 = cases.get(Integer.valueOf(i)).get(4); //p2
					p2 = p.getParam(p2);
				}
				//如果p3单元格为空,则填空字符串
				String p3 = "";
				if(cases.get(Integer.valueOf(i)).size() > 5){
					p3 = cases.get(Integer.valueOf(i)).get(5); //p3
					p3 = p.getParam(p3);
				}
				String params[] = {p1, p2, p3};
				boolean r = k.keyword(action, params, p);
				result = r && result;
				//这里可将keyword.res写入excel对应行
				//发送请求才写入响应
				if(action.indexOf("post")!=-1) {
					//判断结果并写入字符串
					if(r) {
						toExcel.add(new String[]{"PASS",Keyword.res});
					}else {
						toExcel.add(new String[]{"FAIL",Keyword.res});
					}
				}else {
					if(r) {
						toExcel.add(new String[]{"PASS",""});
					}else {
						toExcel.add(new String[]{"FAIL",""});
					}
				}
				//System.out.println("this action:"+result);
			}
			c[5] = ft2.format(new Date()); //写入用例结束时间
			if(result){
				c[6] = "PASS";
			}else{
				c[6] = "FAIL";
			}
			log.write("INFO", "---------------CASE END: "+c[1]+" "+c[6]+"---------------");
		}
		Excel.writeExcel(toExcel, excel, sheetName);
		return suit; 
	}

}
