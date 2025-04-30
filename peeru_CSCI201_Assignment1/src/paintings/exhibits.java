package paintings;

public class exhibits {
	String name;
	String exhibit;
	String localDate;
	String museum;
	exhibits(String name, String exhibit, String localDate,String museum){
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
	
}
class exhibitGroup{
	
}
