package gestionInformation;

import java.util.Optional;

public class Reservation {
	private Station conn_1, conn_2;
	private Optional<Rail> rail;
	private String date;
	private String period;
	private int id;
	private String company_id;
	
	
	public Reservation(int id, String company_id, String date, String period, Optional<Rail> rail) {
		this.id=id;
		this.company_id=company_id;
		this.date=date;
		this.period=period;
		this.rail=rail;
		System.out.println(""+this.id+" "+this.company_id+" "+this.date+" "+this.period+" "+this.rail);
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
    
	}

