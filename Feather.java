
/** This class represents a Feather item in the game.
 * Feathers are used to boost a specific stat of a Pokemon by 1 EV.
 @author Justin Miguel Agustin L. Lotilla
 @author Maurienne Marie M. Mojica
 @version 5.0
 */

public class Feather extends Items {

    /**
     * The stat this feather boosts (HP, Attack, etc.)
     * This is typically a stat like HP, Attack, Defense, etc.
     */
    private String statAffected;  
    private int evBoost;         

    /**
     * Constructor for the Feather class.
     * Initializes the Feather item with its ID, name, description, affected stat,
     * @param itemID the items ID
     * @param itemName the item's name
     * @param itemDesc item's description
     * @param statAffected stat affected by item
     * @param startBuyingPrice item buying price
     * @param sellingPrice item selling price
     */
    public Feather(String itemID, String itemName, String itemDesc, String statAffected,
                   double startBuyingPrice, double sellingPrice) {
        super(itemID, itemName, "Feather", itemDesc, "+1 " + statAffected + " EV",
                false, startBuyingPrice, sellingPrice);
        this.statAffected = statAffected;
        this.evBoost = 1;
    }

    /**
     * Returns the stat affected by this feather.
     * @param pokemon the {@code Pokemon} whose stat will be affected by the feather
     */
    public void applyFeather(Pokemon pokemon) {
        System.out.println("Applying " + getitemName() + " to " + pokemon.getName());
    }
}