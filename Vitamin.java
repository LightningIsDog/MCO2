public class Vitamin extends Items {
    private String statAffected;  // The stat this vitamin boosts (HP, Attack, etc.)

    public Vitamin(String itemID, String itemName, String itemDesc, String statAffected,
                   double startBuyingPrice, double sellingPrice) {
        super(itemID, itemName, "Vitamin", itemDesc, "+10 " + statAffected + " EVs",
                false, startBuyingPrice, sellingPrice);
        this.statAffected = statAffected;
    }

    // Additional methods specific to vitamins
    public String getStatAffected() {
        return statAffected;
    }

    public void applyVitamin(Pokemon pokemon) {
        // Logic to apply the vitamin effect to a Pokemon
        System.out.println("Applying " + getitemName() + " to " + pokemon.getName());
        // Would actually modify the Pokemon's EV stats here
    }
}
