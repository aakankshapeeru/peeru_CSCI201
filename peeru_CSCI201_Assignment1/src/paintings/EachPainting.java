package paintings;

public class EachPainting {
	private String name;
	private String exhibit;
	private String localDate;
	private String museum;
	EachPainting(String name, String exhibit, String localDate, String museum){
		this.name=name;
		this.exhibit=exhibit;
		this.localDate=localDate;
		this.museum=museum;
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
	public void setExhibit(String exhibit) {
		this.exhibit = exhibit;
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
	public void setMuseum(String museum) {
		this.museum = museum;
	}
	public boolean equals(EachPainting e) {
		int count=0;
		if(name.equalsIgnoreCase(e.getName())) {
			count++;
		}
		if(exhibit.equalsIgnoreCase(e.getExhibit())) {
			count++;
		}
		if(localDate.equalsIgnoreCase(e.getLocalDate())) {
			count++;
		}
		if(museum.equalsIgnoreCase(e.getMuseum())) {
			count++;
		}
		if(count==4) {
			return true;
		}
		return false;
		
	}
	@Override
	public String toString() {
		return name+", "+exhibit+", on "+localDate+", held at "+museum;
	}
	public boolean isNull() {
		if(this.name==null || this.exhibit==null ||this.localDate==null || this.museum==null ) {
			return true;
		}
		return false;
	}
	
}
