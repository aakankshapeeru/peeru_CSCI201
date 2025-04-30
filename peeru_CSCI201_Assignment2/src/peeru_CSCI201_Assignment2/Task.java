package peeru_CSCI201_Assignment2;

public class Task {

	private String tradeStart;
	private String artistName;
	private String nOfTickets;
	private String ticketPrice;
	
	public Task(String tradeStart, String artistName, String nOfTickets, String ticketPrice) {
		super();
		this.tradeStart = tradeStart;
		this.artistName = artistName;
		this.nOfTickets = nOfTickets;
		this.ticketPrice = ticketPrice;
	}
	public String getTradeStart() {
		return tradeStart;
	}
	public void setTradeStart(String tradeStart) {
		this.tradeStart = tradeStart;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getnOfTickets() {
		return nOfTickets;
	}
	public void setnOfTickets(String nOfTickets) {
		this.nOfTickets = nOfTickets;
	}
	public String getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(String ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public boolean isNull() {
		if(this.tradeStart==null || this.artistName==null || this.nOfTickets==null || this.ticketPrice==null) {
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		String temp=tradeStart+" "+artistName+" "+nOfTickets+" "+ticketPrice;
		return temp;
	}

}
