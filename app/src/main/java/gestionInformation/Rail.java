package gestionInformation;

import composanteGraphique.Point;
import java.util.HashMap;

public class Rail {
	
	private static final HashMap<Integer, Rail> railMap = new HashMap<>();

	private final Station con1, con2;
	private final int id;

	public static Rail createRail(int con1, int con2, int id) {
		if (railMap.containsKey(id)) {
			return railMap.get(id);
		} else {
			return new Rail(con1, con2, id);
		}
	}

	public static Rail getRailIfExists(int id) {
		if (railMap.containsKey(id)) {
			return railMap.get(id);
		} else return null;
	}

	private Rail(int con1, int con2, int id) {
		this.con1 = Station.createOrGetStation(con1);
		this.con2 = Station.createOrGetStation(con2);
		this.id = 0;

		railMap.put(id, this);
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
