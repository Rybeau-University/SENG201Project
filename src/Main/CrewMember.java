package Main;
import GUI.Alert;
import java.util.Random;
/**
 * This is the parent class for each of the different types of crew members within the game.<br>
 * Each of type of crew member extends this class.<br>
 * @author Ryan Beaumont
 */
public class CrewMember {
	/**
	 * The name of a crew member.
	 */
	private String memberName;
	/**
	 * The health level of a crew member.
	 */
	private int memberHealth = 100;
	/**
	 * The maximum health that the CrewMember can have. This depends on the CrewMember.
	 */
	private int memberMaxHealth = 100;
	/**
	 * The type of crew member.
	 */
	private String memberType;
	/**
	 * The hunger level of a crew member.
	 */
	private int memberHunger = 0;
	/**
	 * The energy of a crew member.
	 */
	private int memberEnergy = 100;
	/**
	 * Does the crew member have the space plague.
	 */
	private boolean hasPlague = false;
	/**
	 * The number of actions that the crew member can do.
	 */
	private int memberActions = 2;
	/**
	 * This is the amount that a crew members energy lower each day.
	 */
	private int dailyEnergyUse = 40;
	/**
	 * This is the amount that a crew members hunger lowers each day.
	 */
	private int dailyHungerUse = 40;
	/**
	 * This is the random number generator used when searchPlanet() is called.
	 */
	private Random randomNumberGenerator = new Random();
	
	/**
	 * The default builder for the class.
	 * @param name Name of the crew member given by player.
	 * @param type The type of crew member selected by the player.
	 */
	public CrewMember(String name, String type){
		memberName = name;
		memberType = type;
	}
	/**
	 * This is the constructor for the CrewMember types that have a different maximum health amount.<br>
	 * E.g. Tank.
	 * @param name Name of the crew member inputed by player.
	 * @param type The type of crew member selected by the player.
	 * @param maxHealth The maximum health level that the crew member can have.
	 */
	public CrewMember(String name, String type, int maxHealth) {
		memberName = name;
		memberType = type;
		memberHealth = maxHealth;
		memberMaxHealth = maxHealth;
	}
	/**
	 * This is the constructor for the CrewMember types that have a different dailyHungerUse amounts.<br>
	 * E.g. Marathon.
	 * @param name Name of the crew member inputed by player.
	 * @param hungerUse The dailyHungerUse of the crew member type.
	 * @param type The type of crew member selected by the player.
	 */
	public CrewMember(String name, int hungerUse ,String type) {
		memberName = name;
		memberType = type;
		dailyHungerUse = hungerUse;
	}
	/**
	 * This is the constructor for the CrewMember types that have a different dailyEnergyUse amounts.<br>
	 * E.g. To be named.
	 * @param energyUse The dailyEnergyUse of the crew member type.
	 * @param name Name of the crew member inputed by player.
	 * @param type The type of crew member selected by the player.
	 */
	public CrewMember(int energyUse, String name, String type) {
		memberName = name;
		memberType = type;
		dailyEnergyUse = energyUse;
	}
	/**
	 * @return The int variable memberHealth.
	 */
	public int getHealth() {
		return memberHealth;
	}
	/**
	 * Sets the memberHealth variable to a given amount.
	 * @param health The value to set memberHealth to.
	 */
	public void setHealth(int health) {
		memberHealth = health;
	}
	/**
	 * Sets the memberHunger variable to a given amount.
	 * @param hunger The value to set memberHunger to.
	 */
	public void setHunger(int hunger) {
		memberHunger = hunger;
	}
	/**
	 * This is the getter for memberHunger.
	 * @return The int memberHunger.
	 */
	public int getHunger() {
		return memberHunger;
	}
	/**
	 * Sets the memberEnergy variable to a given amount.
	 * @param energy The value to set memberEnergy to.
	 */
	public void setEnergy(int energy) {
		memberEnergy = energy;
	}
	/**
	 * Checks if the crew member has the plague.
	 * @return Returns the boolean value has plague.
	 */
	public boolean checkPlague() {
		return hasPlague;
	}
	/**
	 * Sets the value of the boolean variable hasPlague.
	 * @param plague Boolean value for setting hasPlague.
	 */
	public void setPlague(boolean plague) {
		hasPlague = plague;
	}
	/**
	 * @return The int variable memberActions.
	 */
	public int getActions() {
		return memberActions;
	}
	/**
	 * This method sets the variable memberActions to a given value.
	 * @param actions The number of actions for memberActions to be set to.
	 */
	public void setActions(int actions) {
		memberActions = actions;
	}
	/**
	 * @return The String variable memberType.
	 */
	public String getType() {
		return memberType;
	}
	/**
	 * Outputs a string that shows the status of a crew member.<br>
	 * Includes memberName, memberType, memberHealth, memberHunger, memberEnergy variables.<br>
	 */
	public String viewStatus() {
		return "Type: " + memberType + "\nHealth Level: " + memberHealth + "\nPlague Status: " + hasPlague + "\nHunger Level: " +
	memberHunger + "\nEnergy Level: " + memberEnergy + "\nActions: " + memberActions;
	}
	/**
	 * This method is for checking that the CrewMember has actions left to use.
	 * @return If memberActions is greater than 0.
	 */
	public boolean hasActions() {
		return memberActions > 0;
	}
	/**
	 * The getter for memberEnergy.
	 * @return The int memberEnergy
	 */
	public int getEnergy() {
		return memberEnergy;
	}
	/**
	 * This method increases the players hunger level by consuming an item of food.<br>
	 * This is an action so it decreases memberActions by one.
	 * @param food The food item selected by the player for the crew member to consume.
	 */
	public void feed(FoodItem food, Crew crew) {
		if(hasActions()) {
			memberHunger -= food.getFillUpAmount();
			if(memberHunger < 0) {
				memberHunger = 0;
			}
			memberActions -= 1;
			crew.removeFromFoodItems(food);
		}else {
			
		}
	}
	/**
	 * This method heals the crew member using a MedicalItem.<br>
	 * If this MedicalItem heals by some amount then the crew member is healed by this amount.<br>
	 * If this MedicalItem cures the plague then the crew member is cured of the plague.<br>
	 * This is an action so it decreases memberActions by one.
	 * @param item The MedicalItem to be used.
	 */
	public void heal(MedicalItem item, Crew crew) {
		memberHealth += item.getHealAmount();
		if(memberHealth > memberMaxHealth) {
			memberHealth = memberMaxHealth;
		}
		if(item.getCure()) {
			hasPlague = false;
		}
		memberActions -= 1;
		crew.removeFromMedicalItems(item);
	}
	/**
	 * This method puts the crew member to sleep, increasing their energy level (memberEnergy).<br>
	 * This is an action so it decreases memberActions by one.
	 */
	public void sleep() {
		memberEnergy += 40;
		if(memberEnergy > 100) {
			memberEnergy = 100;
		}
		memberActions -= 1;
	}
	/**
	 * This method repairs the ship.<br>
	 * This is an action so it decreases memberActions by one.
	 */
	public void repairShip(Ship ship) {
			int newShieldLevel = ship.getShieldLevel() + 30;
			if(newShieldLevel > 100) {
				newShieldLevel = 100;
			}
			ship.setShieldLevel(newShieldLevel);
			memberActions -= 1;
	}
	/**
	 * This is the searchPlanet method for CrewMember.<br>
	 * It uses Random to pick a random integer between 0-100<br>
	 * 0-35: Transporter Part if there is one on the planet still.<br>
	 * 36-56 Nothing is found.<br>
	 * 56-75: A random MedicalItem or FoodItem. foundItem() called.<br>
	 * 76-100: A random amount of money between 1-500 is found. 
	 */
	public String searchPlanet(Crew crew, Planet planet, GameEnvironment environment) {
		String alertMessage = "Whilst Searching the planet " + this.memberName + " found: ";
		int roll = randomNumberGenerator.nextInt(100);
		if(roll <= 35 && planet.getTransporterPartsAmount() > 0) {//need check planet parts
			crew.setPartsFound(crew.getPartsFound() + 1);
			planet.setTransporterParts(0);
			alertMessage += "1 Transporter Part";
			if(environment.checkGameOver()) {
				environment.gameOver();
			}
		}else if(roll <= 55){
			alertMessage += "Nothing";
		}else if(roll <= 75){
			alertMessage += foundItem(crew);
		}else {
			int moneyFound = randomNumberGenerator.nextInt(450) + 50;//50 added as the integer can be 450 is the biggest value as 50 will be added.
			crew.setMoney(crew.getMoney() + moneyFound);
			alertMessage += "$" + moneyFound;
		}
		memberActions -= 1;
		return alertMessage;
	}

/**
 * Helper function for searchPlanet.<br>
 * It decides what MedicalItem or FoodItem you have found<br>
 * This function was created due to the size of the switch statement to keep the code readable.	
 * @param crew The Crew so that the item can be added to Medical/Food items.
 * @return The message string for the alert box.
 */
	public String foundItem(Crew crew) {
		int itemFound = randomNumberGenerator.nextInt(8);
		String message = "";
		switch(itemFound) {
		case 0:
			crew.addToMedicalItems(new BasicMedicalKit());
			message += "A Basic Medical Kit";
			break;
		case 1:
			crew.addToMedicalItems(new AdvancedMedicalKit());
			message += "An Advanced Medical Kit";
			break;
		case 2:
			crew.addToMedicalItems(new PlagueCure());
			message += "A Plague Cure";
			break;
		case 3:
			crew.addToFoodItems(new Apple());
			message += "An Apple";
			break;
		case 4:
			crew.addToFoodItems(new Banana());
			message += "A Banana";
			break;
			
		case 5:
			crew.addToFoodItems(new HighCalorieBar());
			message += "A High Calorie Bar";
			break;
			
		case 6:
			crew.addToFoodItems(new SpaceSoup());
			message += "Space Soup";
			break;
			
		case 7:
			crew.addToFoodItems(new BeefSteak());
			message += "A Beef Steak";
			break;
			
		case 8:
			crew.addToFoodItems(new MRE());
			message += "An MRE";
			break;
		}
		return message;
	}
	
	/**
	 * The pilotShip method is used to go to a new planet.<br>
	 * It starts by defining the base alertMessage for traveling between planets.<br>
	 * It then calls the newPlanet method in the Planet for the game.<br>
	 * memberActions is the decreased, and the secondPilots actions are also decreased.<br>
	 * It then rolls the random chance of going into an asteroid field using randomNumberGenerator.<br>
	 * If it rolls 0-35 then asteroidField is called and the string it returns is concatenated to the alertMessage.<br>
	 * Then the alertMessage is returned to GameEnvironment.pilotShip();
	 * 
	 * @param secondPilot The second CrewMember Piloting the ship.
	 * @param planet The planet object for the current game.
	 * @param ship The Crew's ship.
	 * 
	 * @return An alertMessage String indicating the Crew is traveled to a new planet. If asteroidField was called
	 * it will indicate that this occurred and the damage that it cause to the ship.
	 */
	public String pilotShip(CrewMember secondPilot, Planet planet, Ship ship) {
		String alertMessage = "You have travelled to a new planet. There is 1 transporter part to collect here.";
		planet.newPlanet();
		memberActions -= 1;
		secondPilot.setActions(secondPilot.getActions() - 1);
		if(randomNumberGenerator.nextInt(100) <= 35) {
			alertMessage += ship.asteroidField();
		}
		return alertMessage;
	}
	

	/**
	 * This method is called at the start of each new day.<br>
	 * It resets the memberActions to 2.<br>
	 * It increases the memberHunger by dailyHungerUse.<br>
	 * It decreases the memberEnergy by dailyEnergyUse.<br>
	 * If either memberHunger or memberEnergy are 0 then memberHealth will be decreased.<br>
	 * It then checks if the crew members health is greater than 0, if it is not the crew member is removed from the crew as they have died.
	 */
	public void nextDay(Crew crew) {
		memberActions = 2;
		memberHunger += dailyHungerUse;
		memberEnergy -= dailyEnergyUse;
		if(memberHunger >= 100) {
			memberHunger = 100;
			memberHealth -= 30; //Decrease CrewMember health
		}if(memberEnergy <= 0) {
			memberEnergy = 0;
			memberHealth -= 30; //Decrease CrewMember health
		}if(hasPlague) {
			memberHealth -= 15;
		}
		if(memberHealth <= 0) {//Checking if the player still has health
			crew.removeCrewMember(this); //Removing the CrewMember from the crew.
			new Alert("Crew Member " + memberName + " has died!");
		}
		
	}
	

	
	/**
	 * Used by in Jlist to display the memberName in the list.
	 * @return memberName
	 */
	public String toString() {
		return memberName;
	}
}
