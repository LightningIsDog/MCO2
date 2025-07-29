public class Feather extends Items {
    private String statAffected;  // The stat this feather boosts
    private int evBoost;          // Typically +1 EV

    public Feather(String itemID, String itemName, String itemDesc, String statAffected,
                   double startBuyingPrice, double sellingPrice) {
        super(itemID, itemName, "Feather", itemDesc, "+1 " + statAffected + " EV",
                false, startBuyingPrice, sellingPrice);
        this.statAffected = statAffected;
        this.evBoost = 1;
    }

    // Additional methods specific to feathers
    public void applyFeather(Pokemon pokemon) {
        System.out.println("Applying " + getitemName() + " to " + pokemon.getName());
        // Would actually modify the Pokemon's EV stats here
    }
}