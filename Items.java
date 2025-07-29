/** This class represents an item in the game, encapsulating its properties and behaviors.
 It includes attributes such as item ID, name, category, description, effects, buying and selling prices,
 and flags for whether the item is for sale or used in evolution.
 @author Justin Miguel Agustin L. Lotilla
 @author Maurienne Marie M. Mojica
 @version 5.0
 */
public class Items {

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
    // Removing availItems and evolutionItems static arrays from here
    // as they are primarily for UI filtering, which can be done directly from itemList
    // or through new methods if absolutely necessary.
    // Keeping only itemList as the source of truth for all defined items.
    public static int itemCount = 0; // Tracks the number of items in itemList

    /**
     * Constructs a new Item object with the specified attributes.
     * Initializes item information including ID, name, category, description,
     * effects, availability for sale, evolution flag, buying price range, and selling price.
     * Automatically registers the item in the item list.
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
        // This public constructor will always register the item in the master list
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

    public static void initializeItems() {
        itemList = new Items[20];
        itemCount = 0;

        // Create ALL items with UNIQUE IDs. Their constructors will automatically add them to itemList.
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

        // Assign UNIQUE IDs to evolution stones to prevent conflicts with 01-10
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
        clone.setQuantity(original.getQuantity()); // Copy quantity
        return clone;
    }
    public int getQuantity() {
        return quantity;
    }

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

    /*
    // Uncomment and complete if needed.
    public void displayItems() {
        System.out.println("\n ================================================================================================");
        System.out.printf("\n\tItem ID %s: %s", getitemID(), getitemName());
        System.out.printf("\n\tCategory: " + getitemCategory());
        System.out.printf("\n\tDescription: " + getitemDesc());
        System.out.printf("\n\tEffects: " + getitemEffects());

        // Assuming getToSold() and getendBuyingPrice() would be implemented elsewhere if this is used
        // if (getToSold() == true) {
        //     if(getForEvo() == false) {
        //         System.out.printf("\n\tBuying Price: P %.2f", getstartBuyingPrice());
        //     } else {
        //         System.out.printf("\n\tBuying Price: P %.2f - P %.2f", getstartBuyingPrice(), getendBuyingPrice());
        //     }
        // } else {
        //     System.out.printf("\n\tBuying Price: Not Sold");
        // }
        System.out.printf("\n\tSelling Price: P %.2f", getsellingPrice());
        System.out.println("\n\n ================================================================================================");
    }
    */

    /**
     * Searches for an item in the item list by its name.
     * @param name the name of the item to search for
     * @return the matching Items object if found, otherwise null
     */
    public static Items getItemByName(String name) {
        // Search the master itemList
        for (int i = 0; i < itemCount; i++) { // Iterate only up to itemCount
            Items items = itemList[i];
            if (items != null && items.getitemName().equalsIgnoreCase(name.strip())) {
                return items;
            }
        }
        return null;
    }

    // Removed getAvailItems(), getEvolutionItems(), getAvailItemCount(), getEvolutionItemCount()
    // because if your UI code expects to iterate over Items.itemList and filter it,
    // these explicit filtered arrays are not strictly necessary as static members of Items.
    // If your UI relies on these being pre-filtered and readily available as static arrays,
    // we would need to re-introduce the logic to populate them *after* initializeItems
    // ensures all items are in itemList.

    /**
     * Registers a new item into the item list if there is available space.
     * If the item list is full, it prints an error message.
     * This method is private because only the initial creation of master item definitions
     * should add to itemList.
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
    public String toCsvString() {
        return this.itemName + "," + this.quantity;
    }
}
