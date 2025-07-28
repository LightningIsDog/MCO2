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
        new Items("01", "HP Up", "Vitamin", "A nutritious drink for Pokemon", "+10 HP EVs", false, 10000, 5000);
        new Items("02", "Protein", "Vitamin", "A nutritious drink for Pokemon", "+10 Attack EVs", false, 10000, 5000);
        new Items("03", "Iron", "Vitamin", "A nutritious drink for Pokemon", "+10 Defense EVs", false, 10000, 5000);
        new Items("04", "Carbos", "Vitamin", "A nutritious drink for Pokemon", "+10 Speed EVs", false, 10000, 5000);
        new Items("05", "Rare Candy", "Leveling Item", "A candy that is packed with energy", "Increases level by 1", false, 4800, 2400);
        new Items("06", "Health Feather", "Feather", "A feather that slightly increases HP", "+1 HP EV", false, 300, 150);
        new Items("07", "Muscle Feather", "Feather", "A feather that slightly increases Attack", "+1 Attack EV", false, 300, 150);
        new Items("08", "Resist Feather", "Feather", "A feather that slightly increases Defense", "+1 Defense EV", false, 300, 150);
        new Items("09", "Swift Feather", "Feather", "A feather that slightly increases Speed", "+1 Speed EV", false, 300, 150);
        new Items("10", "Zinc", "Vitamin", "A nutritious drink for Pokemon", "+10 Special Defense EVs", false, 10000, 5000);

        // Assign UNIQUE IDs to evolution stones to prevent conflicts with 01-10
        new Items("11", "Fire Stone", "Evolution Stone", "A stone that radiates heat", "Evolves Pokemon like Vulpix, Growlithe, Eevee", true, 3000, 1500);
        new Items("12", "Water Stone", "Evolution Stone", "A stone with a blue watery appearance", "Evolves Pokemon like Poliwhirl, Shellder, Eevee", true, 3000, 1500);
        new Items("13", "Thunder Stone", "Evolution Stone", "A stone that sparkles with electricity", "Evolves Pokemon like Pikachu, Eevee", true, 3000, 1500);
        new Items("14", "Leaf Stone", "Evolution Stone", "A stone with a leaf pattern", "Evolves Pokemon like Gloom, Weepinbell, Exeggcute", true, 3000, 1500);
        new Items("15", "Moon Stone", "Evolution Stone", "A stone that glows faintly in the moonlight", "Evolves Pokemon like Nidorina, Clefairy, Jigglypuff, etc.", true, 3000, 1500);
        new Items("16", "Sun Stone", "Evolution Stone", "A stone that glows like the sun", "Evolves Pokemon like Gloom, Sunkern, Cottonee, etc.", true, 3000, 1500);
        new Items("17", "Shiny Stone", "Evolution Stone", "A stone that sparkles brightly", "Evolves Pokemon like Togetic, Roselia, Minccino, etc.", true, 3000, 1500);
        new Items("18", "Dusk Stone", "Evolution Stone", "A dark stone that is ominous in appearance", "Evolves Pokemon like Murkrow, Misdreavus, Doublade, etc.", true, 3000, 1500);
        new Items("19", "Dawn Stone", "Evolution Stone", "A stone that sparkles like the morning sky", "Evolves male Kirlia into Gallade, female Snorunt into Froslass", true, 3000, 1500);
        new Items("20", "Ice Stone", "Evolution Stone", "A stone that is cold to the touch", "Evolves Pokemon like Alolan Vulpix, Galarian Darumaka, Eevee", true, 3000, 1500);



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

        // Call the private constructor and explicitly pass 'false' for shouldRegister
        return new Items(
            original.itemID,
            original.itemName,
            original.itemCategory,
            original.itemDesc,
            original.itemEffects,
            original.forEvo,
            original.startBuyingPrice,
            original.sellingPrice,
            false // DO NOT REGISTER this cloned item into the master itemList
        );
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
}