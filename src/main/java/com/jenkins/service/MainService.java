package com.jenkins.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class MainService 
{

	
	List<Map<String,Object>> list=new ArrayList<>();
	
	public String saveTheRecord(Map<String,Object> map)
	{
		list.add(map);
		System.out.println("Printing the Records ......");
		System.out.println(list);
		return "Records Saved";
	}
	
	
	public List<Map<String,Object>> getTheData()
	{
		return list;
	}
	
}
