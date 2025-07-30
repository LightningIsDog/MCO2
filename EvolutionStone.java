
/** This class represents an Evolution Stone item in a Pokemon game.
 * It extends the Items class and includes properties and methods specific to evolution stones.
 @author Justin Miguel Agustin L. Lotilla
 @author Maurienne Marie M. Mojica
 @version 5.0
 */

public class EvolutionStone extends Items {

    /**
     * The list of Pokemon that can evolve using this stone.
     * This is an array of strings, where each string is the name of a compatible Pokemon
     */
    private String[] compatiblePokemon;  

    /**
     * Constructor for the EvolutionStone class.
     * Initializes the item with its ID, name, description, compatible Pokemon,
     * starting buying price, and selling price.
     *
     * @param itemID The unique identifier for the item.
     * @param itemName The name of the item.
     * @param itemDesc A description of the item.
     * @param compatiblePokemon An array of Pokemon names that can evolve using this stone.
     * @param startBuyingPrice The initial buying price of the item.
     * @param sellingPrice The selling price of the item.
     */
    public EvolutionStone(String itemID, String itemName, String itemDesc, String[] compatiblePokemon, 
                            double startBuyingPrice,double sellingPrice) {
        super(itemID, itemName, "Evolution Stone", itemDesc,
                "Evolves Pokemon like " + String.join(", ", compatiblePokemon),
                true, startBuyingPrice, sellingPrice);
                this.compatiblePokemon = compatiblePokemon;
    }

    /**
     * Checks if a given Pokemon can evolve using this evolution stone.
     *
     * @param pokemon The Pokemon to check for compatibility.
     * @return true if the Pokemon can evolve with this stone, false otherwise.
     */
    public boolean canEvolve(Pokemon pokemon) {
        for (String poke : compatiblePokemon) 
        {
            if (pokemon.getName().equalsIgnoreCase(poke)) 
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Uses the evolution stone on a Pokemon to attempt evolution.
     * If the Pokemon is compatible, it will print a message indicating the evolution.
     * @param pokemon the {@code Pokemon} object to attempt evolution on
     */
    public void useForEvolution(Pokemon pokemon) {
        if (canEvolve(pokemon)) {
            System.out.println("Using " + getitemName() + " to evolve " + pokemon.getName());
        } 
        else {
            System.out.println(pokemon.getName() + " cannot evolve with this stone!");
        }
    }
}