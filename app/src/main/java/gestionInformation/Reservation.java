package gestionInformation;

import composanteGraphique.Rail;

public class Reservation {
	private Rail rail;
	private String date;
	private String period;
	private int id;
	private String company_id;
	
	
	public Reservation(int id, String company_id, String date, String period, Rail rail) {
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
	
	public boolean checkInEnum(String value) {
		  try {
            ReservationPeriod.valueOf(value.toUpperCase()); // Convert input to uppercase for case-insensitive check
            return true; // Input is a valid enum constant
        } catch (IllegalArgumentException e) {
            return false; // Input is not a valid enum constant
        }
	}
	@Override
	public String toString() {
		return "Reservation [rail=" + rail + ", date=" + date + ", period=" + period + ", id=" + id + ", company_id="
				+ company_id + "]";
	}
	public Rail getRail() {
		return rail;
	}
	public void setRail(Rail rail) {
		this.rail = rail;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	
}
