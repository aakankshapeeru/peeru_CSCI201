package paintings;

import java.util.List;

import java.util.Comparator;

import com.google.gson.annotations.SerializedName;

public class AllPaintings {

	@SerializedName("data")

	private List<EachPainting> data;

	public List<EachPainting> getData() {
		return data;
	}

	public void setData(List<EachPainting> data) {
		this.data = data;
	}

	public void displayAll() {
		for (EachPainting painting : data) {
			System.out.println(painting);
			System.out.println();
		}
	}

	public EachPainting getDatabyIndex(int n) {
		return data.get(n);
	}

	public boolean searchExhibit(String exhibit) {

		for (EachPainting painting : data) {
			if (painting.getExhibit().equalsIgnoreCase(exhibit)) {
				System.out.println(painting);
				System.out.println();
				return true;
			}
		}
		return false;

	}

	public boolean museumExists(String museum) {
		for (EachPainting painting : data) {
			if (painting.getMuseum().equalsIgnoreCase(museum)) {
				return true;
			}
		}
		return false;
	}

	public void allExhibits(String museum) {

		for (EachPainting painting : data) {
			if (painting.getMuseum().equalsIgnoreCase(museum)) {
				System.out.println(painting.getExhibit() + " found on the " + painting.getMuseum());
				System.out.println();
			}
		}

	}

	public boolean exhibitExists(EachPainting e) {

		for (EachPainting temp : data) {
			if (temp.equals(e)) {
				return true;
			}
		}
		return false;

	}

	public boolean addExhibit(String name, String exhibit, String localDate, String museum) {

		EachPainting newExhibit = new EachPainting(name, exhibit, localDate, museum);
		if (exhibitExists(newExhibit)) {
			System.out.println("This exhibit already exists in the collection!");
			return false;
		}
		data.add(newExhibit);
		return true;
	}

	public void displayNames() {
		int i = 1;
		for (EachPainting painting : data) {
			System.out.println((i++) + ") " + painting.getName());
		}
	}

	public int numberOfExhibits() {
		return data.size();
	}

	public boolean empty() {
		return data.size()==0;
	}
	public String removeExhibit(int index) {
		
		if (!exhibitExists(data.get(index - 1))) {
			System.out.println("The exhibit doesn't exists in our collection!");
			return "";
		}
		String name = (data.get(index - 1)).getName();
		data.remove(index - 1);
		return name;
	}

	public void sortAtoZ() {
		data.sort(Comparator.comparing(painting -> painting.getName().toLowerCase()));
	}

	public void sortZtoA() {
		data.sort(Comparator.comparing((EachPainting p) -> p.getName().toLowerCase()).reversed());


	}
	public boolean validData() {
		
		for(EachPainting e:data) {
			if(e==null) {
				return false;
			}
			
			if(e.isNull()) {
				return false;
			}

		}
		return true;
	}

}
