package peeru_CSCI201_Assignment2;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Locale;
import com.google.gson.annotations.SerializedName;

public class ExhibitList {
	@SerializedName("data")
	
	private List<Exhibit> data;
	
	
	public List<Exhibit> getData() {
		return data;
	}

	public void setData(List<Exhibit> data) {
		this.data = data;
	}
	public boolean validData() {
		for(Exhibit e:data) {
			if(e==null) {
				return false;
			}
			if(e.isNull()) {
				return false;
			}
		
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.US)
					.withResolverStyle(ResolverStyle.STRICT);
			DateValidator validator = new Utility(dateFormatter);
			if(!validator.isValid(e.getLocalDate())) {
				return false;
				
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "ExhibitList [data=" + data + "]";
	}
	public Exhibit exhibitFromName(String name) {
		for(Exhibit e:data) {
			if(e.getName().equals(name)) {
				return e;
			}
		}
		return null;
	}

	
}
