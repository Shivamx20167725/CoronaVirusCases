package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.models.LocationStats;
import com.example.demo.services.CoronaVirusDataService;

@Controller
public class HomeController {

	@Autowired
	private CoronaVirusDataService coronaVirusDataService;
	
	@GetMapping("/home")
	public String home(Model model) {
		
		List<LocationStats> allstats = coronaVirusDataService.getLateststats();
		
		int totalRepotedCases = allstats.stream().mapToInt(stat -> stat.getLatestCases()).sum();
		int newCases = allstats.stream().mapToInt(stat -> stat.getChangeInCases()).sum();
		model.addAttribute("test",coronaVirusDataService.getLateststats());
		model.addAttribute("totalRepotedCases",totalRepotedCases);
		model.addAttribute("newCases",newCases);
		
		return "home";
	}
}
