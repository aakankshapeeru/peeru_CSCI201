package peeru_CSCI201_Assignment2;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Schedule {
	
	@SerializedName("data")
	private List<Task> data;
	private int balance=0;
	
	public Schedule(List<Task> data) {
		this.data=data;
	}
	
	public List<Task> getData() {
		return data;
	}

	public void setData(List<Task> data) {
		this.data = data;
	}
	
	public int getBalance() {
		return this.balance;
	}
	
	public Task get(int index) {
		return data.get(index);
	}
	
	public void setBalance(int balance) {
		this.balance=balance;
	}
	
	public int size() {
		return data.size();
	}

	public boolean validData(List<List<String>> records) {
		for(Task t:data) {
			if(t==null) {
				return false;
			}
			if(t.isNull()) {
				return false;
			}
		}
		for(List<String> row:records) {
			if(row.isEmpty()|| row.size()!=4) {
				return false;
			}
			
			String start=row.get(0).strip();
			String tickets=row.get(2).strip();
			String price=row.get(3).strip();
			
			try {
				int num=Integer.parseInt(start);
				int ticket=Integer.parseInt(tickets);
				int cost=Integer.parseInt(price);
			}
			catch(NumberFormatException e) {
				return false;
			}
			
		}
		return true;
	}

	@Override
	public String toString() {
		return "Schedule [data=" + data + ", balance=" + balance + "]";
	}
	
}

