package com.jenkins.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jenkins.service.MainService;

@RestController
public class MainController 
{

	@Autowired
	MainService mainService;
	
	@PostMapping()
	public String saveData(@RequestBody Map<String,Object> map)
	{
		mainService.saveTheRecord(map);
		return "Save the Records";
	}
	
	@GetMapping
	public List<Map<String,Object>> getTheData()
	{
		return mainService.getTheData();
	}
}
