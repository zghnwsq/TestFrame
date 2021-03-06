package com.TestFrame;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.openqa.selenium.By;
//import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class WebEle {
	
	private WebDriver dr;
	private Log log;
//	private WebElement ele;
		
	public WebEle(WebDriver dr, Log log) {
		this.dr = dr;
		this.log = log;	
	}

	public WebElement get(String loc) throws WebDriverException{
		//分解定位表达式
		String method = loc.trim().substring(0, loc.trim().indexOf("="));
		String locator = loc.trim().substring(loc.trim().indexOf("=")+1, loc.trim().length());
		//反射,根据method调用By类的方法,返回By对象
		Class<By> cls = By.class;
		Method md;
		By by;
		try {
			md = cls.getDeclaredMethod(method, String.class);
			by = (By)md.invoke(null, locator); //(By)md.invoke(md, method)??  By的方法是静态的
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block		
			log.write("SEVERE", "No Such Method Of By Class");
			e.printStackTrace();
			return null;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			log.write("SEVERE", "Raise Security Exception");
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			log.write("SEVERE", "Raise Illegal Access Exception");
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			log.write("SEVERE", "Raise Illegal Argument Exception");
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			log.write("SEVERE", "Raise InvocationTargetException");
			e.printStackTrace();
			return null;
		}
		return dr.findElement(by);
	}
		
	
	public WebElement get(String method, String locator) throws WebDriverException{
		//反射,根据method调用By类的方法,返回By对象
		Class<By> cls = By.class;
		Method md;
		By by;
		try {
			md = cls.getDeclaredMethod(method, String.class);
			by = (By)md.invoke(null, locator); //(By)md.invoke(md, method)??  By的方法是静态的
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block		
			log.write("SEVERE", "No Such Method Of By Class");
			e.printStackTrace();
			return null;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			log.write("SEVERE", "Raise Security Exception");
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			log.write("SEVERE", "Raise Illegal Access Exception");
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			log.write("SEVERE", "Raise Illegal Argument Exception");
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			log.write("SEVERE", "Raise InvocationTargetException");
			e.printStackTrace();
			return null;
		}
		//返回定位的元素
//		try {
//			ele = dr.findElement(by);
//		} catch(NoSuchElementException e) {
//			log.write("SEVERE", "No Such Element Of : " +method+" = "+locator);
//			e.printStackTrace();
//			return null;
//		}
//		return ele;
		return dr.findElement(by);
	}
		
}
