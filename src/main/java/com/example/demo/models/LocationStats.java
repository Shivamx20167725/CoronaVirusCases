package com.example.demo.models;

public class LocationStats {

	private String state;
	private String country;
	private Integer latestCases;
	private Integer changeInCases;


	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Integer getLatestCases() {
		return latestCases;
	}
	public void setLatestCases(Integer latestCases) {
		this.latestCases = latestCases;
	}
	public Integer getChangeInCases() {
		return changeInCases;
	}
	public void setChangeInCases(Integer changeInCases) {
		this.changeInCases = changeInCases;
	}
	@Override
	public String toString() {
		return "LocationStats [state=" + state + ", country=" + country + ", latestCases=" + latestCases + "]";
	}



}
