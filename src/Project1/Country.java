package Project1;
/**
 *  Stores the name, capital, population, gdp, covid cases, covid deaths, and area of each country as well
 *  as calculating the GDPPC, CFR, Death Rates, Case Rates, and Population Density for each country.
 */
public class Country {
	
	private String name;
	private String capital;
	private String population;
	private String gdp;
	private String covidCases;
	private String covidDeaths;
	private String area;
	
	public Country(String name, String capital, String population, String gdp, String covidCases, String covidDeaths, String area) {
		
		this.name = name;
		this.capital = capital;
		this.population = population;
		this.gdp = gdp;
		this.covidCases = covidCases;
		this.covidDeaths = covidDeaths;
		this.area = area;
				
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setCapital(String capital) {
		this.capital = capital;
	}
	
	public String getCapital() {
		return this.capital;
	}
	
	public void setPopulation(String population) {
		this.population = population;
	}
	
	public String getPopulation() {
		return this.population;
	}
	
	public void setGDP(String gdp) {
		this.gdp = gdp;
	}
	
	public String getGDP() {
		return this.gdp;
	}
	
	public void setCovidCases(String covidCases) {
		this.covidCases = covidCases;
	}
	
	public String getCovidCases() {
		return this.covidCases;
		
	}
	
	public void setCovidDeaths(String covidDeaths) {
		this.covidDeaths = covidDeaths;
	}
	
	public String getCovidDeaths() {
		return this.covidDeaths;
	}
	
	public void setArea(String area) {
		this.area = area;
	}
	
	public String getArea() {
		return this.area;
	}
	
	public double getGDPPC() {
		double GDPPC = Double.parseDouble(getGDP())/ Double.parseDouble(getPopulation());
		
		return GDPPC;
	}
	
	public double getCFR() {
		double CFR = Double.parseDouble(getCovidDeaths())/ Double.parseDouble(getCovidCases());;
		
		return CFR;
	}
	
	public double getCaseRate() {
		double caseRate = (100000.0) *Double.parseDouble(getCovidCases())/ Double.parseDouble(getPopulation());
		
		return caseRate;
	}
	
	public double getDeathRate() {
		double deathRate = (100000.0) * Double.parseDouble(getCovidDeaths())/ Double.parseDouble(getPopulation());
		
		return deathRate;
	}
	
	public double getPopDensity() {
		double popDensity = Double.parseDouble(getPopulation())/ Double.parseDouble(getArea());
		
		return popDensity;
	}
	
	public String getCountryData() {
		String countryData;
		
		countryData = getName() + "\t "+ getCapital() + "\t" + String.format("%10.3f", getGDPPC()) + "\t" + String.format("%10.6f", getCFR()) + "\t" + String.format("%10.3f", getCaseRate()) + "\t" +  String.format("%10.3f", getDeathRate()) + "\t" +  String.format("%10.3f", getPopDensity());
		
		return countryData;
	}
}
