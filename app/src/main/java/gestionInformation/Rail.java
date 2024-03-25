package gestionInformation;

import composanteGraphique.Point;

public class Rail {
	private final Station con1, con2;

	public Rail(int con1, int con2) {
		this.con1 = Station.createOrGetStation(con1);
		this.con2 = Station.createOrGetStation(con2);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o instanceof Rail rail) {
			return this.con1 == rail.con1 && this.con2 == rail.con2 ||
				   this.con1 == rail.con2 && this.con2 == rail.con1;
		}
		return false;
	}

	public Point getPoint1() {
		return con1.getPoint();
	}

	public Point getPoint2() {
		return con2.getPoint();
	}
}
