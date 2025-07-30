
/** This class represents a Leveling Item in a game.
 *  It extends the Items class and is used to increase a Pokémon's level.
 *  @author Justin Miguel Agustin L. Lotilla
 *  @author Maurienne Marie M. Mojica
 *  @version 5.0
 */

public class LevelingItem extends Items {

    /**
     * The amount by which the level of a Pokémon is increased when this item is used.
     * This is typically set to 1 for standard leveling items.
     */
    private int levelIncrease;  

    /**
     * Constructor for LevelingItem.
     * Initializes the item with its ID, name, description, and pricing.
     * @param itemID item's ID
     * @param itemName item's name
     * @param itemDesc item's description
     * @param startBuyingPrice item's buying price
     * @param sellingPrice item's selling price
     */
    public LevelingItem(String itemID, String itemName, String itemDesc,
                        double startBuyingPrice, double sellingPrice) {
        super(itemID, itemName, "Leveling Item", itemDesc, "Increases level by 1",
                false, startBuyingPrice, sellingPrice);
        this.levelIncrease = 1;
    }

    /**
     * Method to get the level increase value.
     * @param pokemon the {@code Pokemon} object on which the item is used
     */
    public void useOnPokemon(Pokemon pokemon) {
        System.out.println("Using " + getitemName() + " on " + pokemon.getName());
    }
}