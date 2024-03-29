package Main;

/**
 * This class defines a Planet.
 * @author Daniel Porter
 * 
 */

public class Planet {
	/**
	 * The amount of transporter parts on the planet.
	 * Can only have 1 or 0.
	 */
	private int planetTransporterParts;
	/**
	 * Every planet has an Outpost.
	 */
	private Outpost planetOutpost;
	/**
	 * This is the constructor for the Planet Class. <br>
	 * Sets an Outpost for the planet. <br>
	 * Sets the amount of transporter parts on the planet to 1.
	 */
	public Planet() {
		planetOutpost = new Outpost();
		planetTransporterParts = 1;
	}
	/**
	 * Sets planetTransporterParts to 1. <br>
	 * (There will always be 1 transporter part on a new planet.)
	 */
	public void newPlanet() {
		planetTransporterParts = 1;
	}
	/**
	 * @return
	 * the amount of transporter parts on the planet.
	 */
	public int getTransporterPartsAmount() {
		return planetTransporterParts;
	}
	
	public void setTransporterParts(int newAmount) {
		planetTransporterParts = newAmount;
	}
	
	public Outpost getOutpost() {
		return planetOutpost;
	}

}
