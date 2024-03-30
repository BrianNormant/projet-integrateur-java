package gestionInformation;

public class Reservation {
	private Station conn_1, conn_2;
	private Rail rail;
	private String date;
	private ReservationPeriod period;
	private int id;
	private String company_id;
	
	
	public Reservation(int id, String company_id, String date, ReservationPeriod period, Rail rail) {
		this.id=id;
		this.company_id=company_id;
		this.date=date;
		this.period=period;
		this.rail=rail;
	}
	

	public enum ReservationPeriod {
		MORNING("morning"),
		EVENING("evening"),
		NIGHT("night");
		public String name;
		private ReservationPeriod(String name) {
			this.name = name;
		}
	}
	
}
