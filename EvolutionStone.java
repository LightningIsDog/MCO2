public class EvolutionStone extends Items {
    private String[] compatiblePokemon;  // Pokemon that can evolve with this stone

    public EvolutionStone(String itemID, String itemName, String itemDesc,
                          String[] compatiblePokemon, double startBuyingPrice,
                          double sellingPrice) {
        super(itemID, itemName, "Evolution Stone", itemDesc,
                "Evolves Pokemon like " + String.join(", ", compatiblePokemon),
                true, startBuyingPrice, sellingPrice);
        this.compatiblePokemon = compatiblePokemon;
    }

    // Additional methods specific to evolution stones
    public boolean canEvolve(Pokemon pokemon) {
        for (String poke : compatiblePokemon) {
            if (pokemon.getName().equalsIgnoreCase(poke)) {
                return true;
            }
        }
        return false;
    }

    public void useForEvolution(Pokemon pokemon) {
        if (canEvolve(pokemon)) {
            System.out.println("Using " + getitemName() + " to evolve " + pokemon.getName());
            // Evolution logic would go here
        } else {
            System.out.println(pokemon.getName() + " cannot evolve with this stone!");
        }
    }
}