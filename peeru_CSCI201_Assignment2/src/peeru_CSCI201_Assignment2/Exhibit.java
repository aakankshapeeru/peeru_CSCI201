package peeru_CSCI201_Assignment2;

import com.google.gson.annotations.SerializedName;

public class Exhibit{
	private String name;
	@SerializedName(value = "exhibit", alternate = {"tour"})
	private String exhibit;
	private String localDate;
    @SerializedName(value = "museum", alternate = {"venue"})
	private String museum;
	private int agents;
	public Exhibit(String name,String exhibitOrTour, String localDate,String museumOrVenue, int agents) {
		super();
		this.name = name;
		this.exhibit=exhibitOrTour;
		this.localDate = localDate;
		this.museum=museumOrVenue;
		this.agents = agents;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExhibit() {
		return exhibit;
	}
	public void setExhibit(String exhibitOrTour) {
		this.exhibit = exhibitOrTour;
	}
	public String getLocalDate() {
		return localDate;
	}
	public void setLocalDate(String localDate) {
		this.localDate = localDate;
	}
	public String getMuseum() {
		return museum;
	}
	public void setMuseum(String museumOrVenue) {
		this.museum = museumOrVenue;
	}
	public int getAgents() {
		return agents;
	}
	public void setAgents(int agents) {
		this.agents = agents;
	}
	public boolean isNull() {
		if(this.name==null || this.exhibit==null ||this.localDate==null || this.museum==null ) {
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		String temp=name+" "+exhibit+" "+localDate+" "+museum+" "+agents;
		return temp;
	}
	
}
