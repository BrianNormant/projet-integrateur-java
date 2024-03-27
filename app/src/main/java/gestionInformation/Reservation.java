package gestionInformation;

public class Reservation {
	public Station origin, destination;
	public String date;
	public ReservationPeriod period;

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
