package Main;
import java.util.Random;

/**
 * This class extends CrewMember.<br>
 * It is the class for the Scavenger type CrewMember.
 * @author Ryan Beaumont
 *
 */
public class Scavenger extends CrewMember{
	/**
	 * This is the constructor for the Scavenger Class.<br>
	 * This passes the name selected by the player and the String "Scavenger" which is the type to the parent class CrewMember.
	 * @param name Name inputed by the player.
	 */
	public Scavenger(String name) {
		super(name,"Scavenger");
	}
	/**
	 * This method overrides CrewMember.searchPlanet().<br>
	 * It increases the chances of finding transporter parts when searching the planet and will always find something<br>
	 * 0-55: Transporter Part if there is one on the planet still.<br>
	 * 56-75: A random MedicalItem or FoodItem. foundItem() called.<br>
	 * 76-100: A random amount of money between 1-500 is found.
	 */
	public String searchPlanet(Crew crew, Planet planet, GameEnvironment environment) {
		Random randomNumberGenerator = new Random();
		String alertMessage = "Whilst Searching the planet " + super.toString() + " found: ";
		int roll = randomNumberGenerator.nextInt(100);
		if(roll <= 55 && planet.getTransporterPartsAmount() > 0) {
			crew.setPartsFound(crew.getPartsFound() + 1);
			planet.setTransporterParts(0);
			alertMessage += "1 Transporter Part";
			if(environment.checkGameOver()) {
				environment.gameOver();
			}
		}else if(roll <= 75) {
			alertMessage += super.foundItem(crew);
		}else {
			int moneyFound = randomNumberGenerator.nextInt(450) + 50;//50 added as the integer can be 450 is the biggest value as 50 will be added.
			crew.setMoney(crew.getMoney() + moneyFound);
			alertMessage += "$" + moneyFound;
		}
		super.setActions(super.getActions() - 1);
		return alertMessage;
	}
}
