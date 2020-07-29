package testSuite;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import common.BaseClass;
import common.ListenersClass;
import common.Util;

@Listeners(ListenersClass.class)
public class StringReverse  extends BaseClass {
	public static StringBuilder sb = new StringBuilder();;

	@Test
	public void usingReverse()
	{
		Util.logInfoExtent("This is String Reverse \"Using Reverse\" method");
		String s = "Geeks For Geeks";
		StringBuilder sb = new StringBuilder();
		sb.append(s);
		System.out.println(sb.reverse());
	}
	
	@Test
	public void usingCharArray()
	{
		Util.logInfoExtent("This is String Reverse \"Using Character Array\" method");
		String s = "Geeks For Geeks";
		char[] chars = s.toCharArray();
		for(int i=chars.length-1;i>=0;i--)
			System.out.print(chars[i]);
		System.out.println();
	}
	
	@Test
	public void usingArrayList()
	{
		Util.logInfoExtent("This is String Reverse \"Using Array List\" method");
		String s = "Geeks For Geeks";
		char[] chars = s.toCharArray();
		List<Character> charArray = new ArrayList<Character>();
		
		for(char char1: chars)
			charArray.add(char1);
		
		Collections.reverse(charArray);
		ListIterator<Character> li = charArray.listIterator();
		while(li.hasNext())
			System.out.print(li.next());
		System.out.println();
	}
	
	@Test
	public void recursiveTest()
	{
		Util.logInfoExtent("This is String Reverse \"Using Recursive\" method");
		String s = "Geeks For Geeks";
		System.out.println(usingRecursive(s));
	}
	
	public StringBuilder usingRecursive(String s)
	{
		if(!s.isEmpty())
		{
			sb.append(s.charAt(s.length()-1));
			usingRecursive(s.substring(0,s.length()-1));
		}
		return sb;
	}
}
