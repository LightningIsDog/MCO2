/** This class represents an item in the game, encapsulating its properties and behaviors.
 It includes attributes such as item ID, name, category, description, effects, buying and selling prices,
 and flags for whether the item is for sale or used in evolution.
 @author Justin Miguel Agustin L. Lotilla
 @author Maurienne Marie M. Mojica
 @version 5.0
 */

public class Items {

    /**
     * Attributes of the Items class.
     */
    private String itemID;
    private String itemName;
    private String itemCategory;
    private String itemDesc;
    private String itemEffects;
    private double startBuyingPrice;
    private boolean forEvo;
    private double sellingPrice;
    private int quantity;

    /**
     * Array to store Items objects. This is the master list of item definitions.
     */
    public static Items[] itemList = new Items[100]; // Max capacity

    /**
     * Tracks the number of items in itemList
     */
    public static int itemCount = 0; 

    /**
     * Constructs a new Item object with the specified attributes.
     * Initializes item information including ID, name, category, description,
     * effects, availability for sale, evolution flag, buying price range, and selling price.
     * Automatically registers the item in the item or master list.
     * @param itemID             the unique identifier for the item
     * @param itemName           the name of the item
     * @param itemCategory       the category/type of the item
     * @param itemDesc           a description of the item
     * @param itemEffects        the effect or bonus provided by the item
     * @param startBuyingPrice   the minimum price if range-based, or the fixed price
     * @param forEvo             whether the item is used for evolution
     * @param sellingPrice       the selling price of the item
     */
    public Items(String itemID, String itemName, String itemCategory, String itemDesc, String itemEffects, boolean forEvo,
                 double startBuyingPrice, double sellingPrice) {
        this(itemID, itemName, itemCategory, itemDesc, itemEffects, forEvo, startBuyingPrice, sellingPrice, true);
        this.quantity = 0;
    }

    /**
     * Private constructor for internal use, allowing control over item registration.
     * This is used by cloneItem to prevent re-registering cloned items into the master list.
     * @param itemID             the unique identifier for the item
     * @param itemName           the name of the item
     * @param itemCategory       the category/type of the item
     * @param itemDesc           a description of the item
     * @param itemEffects        the effect or bonus provided by the item
     * @param forEvo             whether the item is used for evolution
     * @param startBuyingPrice   the minimum price if range-based, or the fixed price
     * @param sellingPrice       the selling price of the item
     * @param shouldRegister     if true, the item will be added to the static itemList
     */
    private Items(String itemID, String itemName, String itemCategory, String itemDesc, String itemEffects, boolean forEvo,
                  double startBuyingPrice, double sellingPrice, boolean shouldRegister) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemDesc = itemDesc;
        this.itemEffects = itemEffects;
        this.forEvo = forEvo;
        this.startBuyingPrice = startBuyingPrice;
        this.sellingPrice = sellingPrice;

        if (shouldRegister) {
            registerItem(this);
        }
    }

    /**
     * Initializes the static itemList with predefined items.
     * This method creates instances of various item types (Vitamins, Feathers, LevelingItems, EvolutionStones)
     * and registers them in the static itemList array.
     */
    public static void initializeItems() {
        itemList = new Items[20];
        itemCount = 0;

        new Vitamin("01", "HP Up", "A nutritious drink for Pokemon", "HP", 10000, 5000);
        new Vitamin("02", "Protein", "A nutritious drink for Pokemon", "Attack", 10000, 5000);
        new Vitamin("03", "Iron", "A nutritious drink for Pokemon", "Defense", 10000, 5000);
        new Vitamin("04", "Carbos", "A nutritious drink for Pokemon", "Speed", 10000, 5000);
        new LevelingItem("05", "Rare Candy", "A candy that is packed with energy", 4800, 2400);
        new Feather("06", "Health Feather", "A feather that slightly increases HP", "HP", 300, 150);
        new Feather("07", "Muscle Feather", "A feather that slightly increases Attack", "Attack", 300, 150);
        new Feather("08", "Resist Feather", "A feather that slightly increases Defense", "Defense", 300, 150);
        new Feather("09", "Swift Feather", "A feather that slightly increases Speed", "Speed", 300, 150);
        new Vitamin("10", "Zinc", "A nutritious drink for Pokemon", "Special Defense", 10000, 5000);
        new EvolutionStone("11", "Fire Stone", "A stone that radiates heat",
                new String[]{"Vulpix", "Growlithe", "Eevee"}, 3000, 1500);
        new EvolutionStone("12", "Water Stone", "A stone with a blue watery appearance",
                new String[]{"Poliwhirl", "Shellder", "Eevee"}, 3000, 1500);
        new EvolutionStone("13", "Thunder Stone", "A stone that sparkles with electricity",
                new String[]{"Pikachu", "Eevee"}, 3000, 1500);

        new EvolutionStone("14", "Leaf Stone", "A stone with a leaf pattern",
                new String[]{"Gloom", "Weepinbell", "Exeggcute"}, 3000, 1500);

        new EvolutionStone("15", "Moon Stone", "A stone that glows faintly in the moonlight",
                new String[]{"Nidorina", "Nidorino", "Clefairy", "Jigglypuff"}, 3000, 1500);

        new EvolutionStone("16", "Sun Stone", "A stone that glows like the sun",
                new String[]{"Gloom", "Sunkern", "Cottonee"}, 3000, 1500);

        new EvolutionStone("17", "Shiny Stone", "A stone that sparkles brightly",
                new String[]{"Togetic", "Roselia", "Minccino"}, 3000, 1500);

        new EvolutionStone("18", "Dusk Stone", "A dark stone that is ominous in appearance",
                new String[]{"Murkrow", "Misdreavus", "Doublade"}, 3000, 1500);

        new EvolutionStone("19", "Dawn Stone", "A stone that sparkles like the morning sky",
                new String[]{"Kirlia (Male)", "Snorunt (Female)"}, 3000, 1500);

        new EvolutionStone("20", "Ice Stone", "A stone that is cold to the touch",
                new String[]{"Alolan Vulpix", "Galarian Darumaka", "Eevee"}, 3000, 1500);
        System.out.println("Items initialized. Total items in master list: " + itemCount);
    }

    /**
     * Creates a deep copy of the given Items object.
     * This method uses a private constructor to ensure that the cloned item is NOT
     * re-registered into the static itemList.
     * @param original The original Items object to clone.
     * @return A new Items object with the same properties.
     */
    public static Items cloneItem(Items original) {
        if (original == null) return null;
        Items clone = new Items(
                original.itemID,
                original.itemName,
                original.itemCategory,
                original.itemDesc,
                original.itemEffects,
                original.forEvo,
                original.startBuyingPrice,
                original.sellingPrice,
                false // Do not register in master list
        );
        clone.setQuantity(original.getQuantity()); 
        return clone;
    }

    /**
     * Returns the quantity of the item.
     * @return the quantity of the item as an integer
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the item.
     * @param quantity quantity of an item
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the ID of the item.
     * @return the item ID as a String
     */
    public String getitemID() {
        return itemID;
    }

    /**
     * Returns the Name of the item.
     * @return the item name as a String
     */
    public String getitemName() {
        return itemName;
    }

    /**
     * Returns the Category of the item.
     * @return the item category as a String
     */
    public String getitemCategory() {
        return itemCategory;
    }

    /**
     * Returns the Description of the item.
     * @return the item description as a String
     */
    public String getitemDesc() {
        return itemDesc;
    }

    /**
     * Returns the Effects of the item.
     * @return the item effects as a String
     */
    public String getitemEffects() {
        return itemEffects;
    }

    /**
     * Returns whether the item is a part of evolution stones
     * @return true if the item is used for evolution, false otherwise
     */
    public boolean getForEvo() {
        return forEvo;
    }

    /**
     * Returns the starting buying price of the item.
     * @return the minimum or fixed buying price as a double
     */
    public double getstartBuyingPrice() {
        return startBuyingPrice;
    }

    /**
     * Returns the selling price of the item.
     * @return the selling price as a double
     */
    public double getsellingPrice() {
        return sellingPrice;
    }

    /**
     * Searches for an item in the item list by its name.
     * @param name the name of the item to search for
     * @return the matching Items object if found, otherwise null
     */
    public static Items getItemByName(String name) {
        for (int i = 0; i < itemCount; i++) { 
            Items items = itemList[i];
            if (items != null && items.getitemName().equalsIgnoreCase(name.strip())) {
                return items;
            }
        }
        return null;
    }

    /**
     * Registers an item into the item list
     * @param item the item to be registered
     */
    private static void registerItem(Items item) {
        if (item == null) {
            System.out.println("null item cannot be added");
            return;
        }
        // Check for duplicates by ID before adding to prevent accidental re-registration
        for (int i = 0; i < itemCount; i++) {
            if (itemList[i] != null && itemList[i].getitemID().equals(item.getitemID())) {
                // Item with this ID already exists, do not re-register
                System.out.println("Warning: Item with ID " + item.getitemID() + " (" + item.getitemName() + ") already registered. Skipping re-registration.");
                return; // Prevent adding duplicate ID
            }
        }
        if (itemCount < itemList.length) {
            itemList[itemCount] = item;
            itemCount++;
        } else {
            System.err.println("⚠ Item database full! Cannot register: " + item.getitemName());
        }
    }

    /**
     * Converts this item object into a comma-separated string
     * @return the formatted string representation of the item's data
     */
    public String toCsvString() {
        return this.itemName + "," + this.quantity;
    }
}