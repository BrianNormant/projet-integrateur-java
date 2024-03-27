package gestionInformation;

public class Train {

	private String id;
	private String railId;
	private String pos;
	
	public Train(String id, String railId, String pos) {
		this.id = id;
		this.railId = railId;
		this.pos = pos;
	}

	public String getId() {
		return id;
	}

	public String getRailId() {
		return railId;
	}

	public String getPos() {
		return pos;
	}
}
