package Main;
import GUI.Alert;
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
	 * @param plague Boolean value for settin hasPlague.
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
		return "Status of Crew Member " + memberName + ":\nType: " + memberType + "\nHealth Level: " + memberHealth + "\nHunger Level: " + memberHunger + "\nEnergy Level: " + memberEnergy;
	}
	/**
	 * This method is for checking that the CrewMember has actions left to use.
	 * @return If memberActions is greater than 0.
	 */
	public boolean hasActions() {
		return memberActions > 0;
	}
	/**
	 * If the CrewMember has actions left, this method increases the players hunger level by consuming an item of food.<br>
	 * This is an action so it decreases memberActions by one.
	 * @param food The food item selected by the player for the crew member to consume.
	 */
	public void feed(FoodItem food) {
		if(hasActions()) {
			int hunger = memberHunger;
			memberHunger = (hunger + food.getFillUpAmount()) % 100;
			memberActions -= 1;
		//Add the remove from crew food items.
		}else {
			sendAlert("No actions left for this crew member!");
		}
	}
	/**
	 * If the CrewMember has actions left, this method heals the crew member using a MedicalItem.<br>
	 * If this MedicalItem heals by some amount then the crew member is healed by this amount.<br>
	 * If this MedicalItem cures the plague then the crew member is cured of the plague.<br>
	 * This is an action so it decreases memberActions by one.
	 * @param item The MedicalItem to be used.
	 */
	public void heal(MedicalItem item) {
		if (hasActions()) {
			int health = memberHealth;
			memberHealth = (health + item.getHealAmount()) % memberMaxHealth;
			if(item.getCure()) {
				hasPlague = false;
			}
			memberActions -= 1;
			//add remove from medicalitems
		}else {
			sendAlert("No actions left for this crew member!");
		}
	}
	/**
	 * If the CrewMember has actions left, this method puts the crew member to sleep, increasing their energy level (memberEnergy).<br>
	 * This is an action so it decreases memberActions by one.
	 */
	public void sleep() {
		if(hasActions()) {
			int energy = memberEnergy;
			memberEnergy = (energy + 25) % 100;
			memberActions -= 1;
		}else {
			sendAlert("No actions left for this crew member!");
		}
	}
	/**
	 * If the CrewMember has actions left, this method repairs the ship.<br>
	 * This is an action so it decreases memberActions by one.
	 */
	public void repairShip(Ship ship) {
		if(hasActions()) {
			//Daniel implement first.
			memberActions -= 1;
		}else {
			sendAlert("No actions left for this crew member!");
		}
	}
	/**
	 * 
	 */
	public void searchPlanet() {
		if(hasActions()) {
			//to be done
		}else {
			sendAlert("No actions left for this crew member!");
		}
	}
	/**
	 * 
	 * @param secondPilot The second CrewMember Piloting the ship.
	 */
	public void pilotShip(CrewMember secondPilot) {
		if(hasActions() && secondPilot.hasActions()) {
			//implement.
			memberActions -= 1;
			secondPilot.setActions(secondPilot.getActions() - 1);
		}else {
			sendAlert("Not enough actoins!");
		}
	}
	/**
	 * This method is called at the start of each new day.<br>
	 * It decreases the crew members energy and hunger level.<br>
	 * If either memberHunger or memberEnergy are 0 then memberHealth will be decreased.<br>
	 * It then checks if the crew members health is greater than 0, if it is not the crew member is removed from the crew as they have died.
	 */
	public void nextDay() {
		memberHunger = (memberHunger + dailyHungerUse) % 100;//Decrease CrewMember Hunger Level
		memberEnergy = (memberEnergy - dailyEnergyUse) % 100;//Decrease CrewMember Energy Level
		if(memberHunger == 100) {
			memberHealth -= 30; //Decrease CrewMember health
		}else if(memberEnergy == 0) {
			memberHealth -= 30; //Decrease CrewMember health
		}
		if(memberHealth <= 0) {//Checking if the player still has health
			Crew crew = GameEnvironment.getGameCrew(); //Getting crew
			crew.removeCrewMember(this); //Removing the CrewMember from the crew.
		}
	}
	
	/**
	 * Sends an alert to the player with the give alert text.
	 * @param text The alert text to be displayed.
	 */
	public void sendAlert(String text) {
		Alert alert = new Alert(text);
	}
	
}
