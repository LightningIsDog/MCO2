
/** This class represents a Vitamin item in the game.
 * Vitamins are used to boost a specific stat of a Pokemon by 10 EVs.
 @author Justin Miguel Agustin L. Lotilla
 @author Maurienne Marie M. Mojica
 @version 5.0
 */

public class Vitamin extends Items {

    /**
     * The stat that this vitamin affects (e.g., "HP", "Attack", "Defense", etc.
     */
    private String statAffected;  
    
    /**
     * Constructor for the Vitamin class.
     * @param itemID item ID
     * @param itemName item name
     * @param itemDesc item description
     * @param statAffected stats affected by item
     * @param startBuyingPrice item buying price
     * @param sellingPrice item selling price
     */
    public Vitamin(String itemID, String itemName, String itemDesc, String statAffected,
                   double startBuyingPrice, double sellingPrice) {
        super(itemID, itemName, "Vitamin", itemDesc, "+10 " + statAffected + " EVs",
                false, startBuyingPrice, sellingPrice);
        this.statAffected = statAffected;
    }

    /**
     * Returns the stat that this vitamin affects.
     * @return statAffected
     */
    public String getStatAffected() {
        return statAffected;
    }

    /**
     * Applies the vitamin effect to a given Pokemon.
     * This method would typically modify the Pokemon's EVs based on the vitamin's effect.
     * @param pokemon pokemon that will apply the item
     */
    public void applyVitamin(Pokemon pokemon) {
        System.out.println("Applying " + getitemName() + " to " + pokemon.getName());
    }
}