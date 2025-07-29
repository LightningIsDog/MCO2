public class LevelingItem extends Items {
    private int levelIncrease;  // Typically +1 level

    public LevelingItem(String itemID, String itemName, String itemDesc,
                        double startBuyingPrice, double sellingPrice) {
        super(itemID, itemName, "Leveling Item", itemDesc, "Increases level by 1",
                false, startBuyingPrice, sellingPrice);
        this.levelIncrease = 1;
    }

    // Additional methods specific to leveling items
    public void useOnPokemon(Pokemon pokemon) {
        System.out.println("Using " + getitemName() + " on " + pokemon.getName());
       // pokemon.levelUp();  // Assuming Pokemon class has a levelUp method
    }
}