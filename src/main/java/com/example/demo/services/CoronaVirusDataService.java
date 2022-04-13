package com.example.demo.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.models.LocationStats;

@Service
public class CoronaVirusDataService {

	private static final String VIRUS_DATA_URI = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

	List<LocationStats> lateststats = new ArrayList<>();
	
	public List<LocationStats> getLateststats() {
		return lateststats;
	}
	public void setLateststats(List<LocationStats> lateststats) {
		this.lateststats = lateststats;
	}
	
	@PostConstruct
	@Scheduled(cron = "* * 4 * * *")
	public void fetchDataFromUri() throws IOException, InterruptedException {
		
		List<LocationStats> currentStats = new ArrayList<>();
		
		HttpClient client  = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(VIRUS_DATA_URI)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		StringReader reader = new StringReader(response.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
		for (CSVRecord record : records) {
		    
			LocationStats locationStats = new LocationStats();
			
			String state = record.get("Province/State");
		    locationStats.setState(record.get("Province/State"));
		    locationStats.setCountry(record.get("Country/Region"));
		    
		    int latestCases= Integer.parseInt(record.get(record.size()- 1));
		    int previousCases = Integer.parseInt(record.get(record.size()- 2));
		    
		    locationStats.setLatestCases(latestCases);	    
		    locationStats.setChangeInCases(latestCases - previousCases);
		    
		    currentStats.add(locationStats);
		}
		this.lateststats=currentStats;
	}

}
