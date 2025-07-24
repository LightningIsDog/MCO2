/** This class represents an item in the game, encapsulating its properties and behaviors.
  It includes attributes such as item ID, name, category, description, effects, buying and  selling prices,
  and flags for whether the item is for sale or used in evolution.
 *  @author Justin Miguel Agustin L. Lotilla
 *  @author Maurienne Marie M. Mojica
 *  @version 5.0
 */
public class Items {

    private String itemID;
    private String itemName;
    private String itemCategory;
    private String itemDesc;
    private String itemEffects;
    private double startBuyingPrice;
    private double endBuyingPrice;
    private double sellingPrice;
    private boolean toSold;
    private boolean forEvo;
    /**
     * Array to store Items objects.
     */
    public static Items[] itemList = new Items[100];
    private static Items[] availItems = new Items[10];
    private static Items[] evolutionItems = new Items[10];
    private static int itemCount = 0;

     /**
        Constructs a new Item object with the specified attributes.
        Initializes item information including ID, name, category, description,
        effects, availability for sale, evolution flag, buying price range, and selling price.
        Automatically registers the item in the item list.
        @param itemID            the unique identifier for the item
        @param itemName          the name of the item
        @param itemCategory      the category/type of the item
        @param itemDesc          a description of the item
        @param itemEffects       the effect or bonus provided by the item
        @param toSold            true if the item is available for sale
        @param forEvo            true if the item is used for evolution
        @param startBuyingPrice  the minimum price if range-based, or the fixed price
        @param endBuyingPrice    the maximum price for buying (same as start if not a range)
        @param sellingPrice      the selling price of the item
    */
    public Items(String itemID,String itemName,String itemCategory, String itemDesc, String itemEffects,boolean toSold,boolean forEvo,
                 double startBuyingPrice,double endBuyingPrice, double sellingPrice)
    {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemDesc = itemDesc;
        this.itemEffects = itemEffects;
        this.toSold = toSold;
        this.forEvo = forEvo;
        this.startBuyingPrice = startBuyingPrice;
        this.endBuyingPrice = endBuyingPrice;
        this.sellingPrice = sellingPrice;
        registerItem(this);
    }

    public static void initializeItems() {
    // Allocate arrays
    availItems = new Items[10];
    evolutionItems = new Items[10];
    itemList = new Items[20];
    itemCount = 0;

    // Fill availItems
    availItems[0] = new Items("01", "HP Up", "Vitamin", "A nutritious drink for Pokemon", "+10 HP EVs", true, false, 10000, 10000, 5000);
    availItems[1] = new Items("02", "Protein", "Vitamin", "A nutritious drink for Pokemon", "+10 Attack EVs", true, false, 10000, 10000, 5000);
    availItems[2] = new Items("03", "Iron", "Vitamin", "A nutritious drink for Pokemon", "+10 Defense EVs", true, false, 10000, 10000, 5000);
    availItems[3] = new Items("04", "Carbos", "Vitamin", "A nutritious drink for Pokemon", "+10 Speed EVs", true, false, 10000, 10000, 5000);
    availItems[4] = new Items("05", "Rare Candy", "Leveling Item", "A candy that is packed with energy", "Increases level by 1", false, false, 0, 0, 2400);
    availItems[5] = new Items("06", "Health Feather", "Feather", "A feather that slightly increases HP", "+1 HP EV", true, false, 300, 300, 150);
    availItems[6] = new Items("07", "Muscle Feather", "Feather", "A feather that slightly increases Attack", "+1 Attack EV", true, false, 300, 300, 150);
    availItems[7] = new Items("08", "Resist Feather", "Feather", "A feather that slightly increases Defense", "+1 Defense EV", true, false, 300, 300, 150);
    availItems[8] = new Items("09", "Swift Feather", "Feather", "A feather that slightly increases Speed", "+1 Speed EV", true, false, 300, 300, 150);
    availItems[9] = new Items("10", "Zinc", "Vitamin", "A nutritious drink for Pokemon", "+10 Special Defense EVs", true, false, 10000, 10000, 5000);

    // Fill evolutionItems
    evolutionItems[0] = new Items("01", "Fire Stone", "Evolution Stone", "A stone that radiates heat", "Evolves Pokemon like Vulpix, Growlithe, Eevee", true, true, 3000, 5000, 1500);
    evolutionItems[1] = new Items("02", "Water Stone", "Evolution Stone", "A stone with a blue watery appearance", "Evolves Pokemon like Poliwhirl, Shellder, Eevee", true, true, 3000, 5000, 1500);
    evolutionItems[2] = new Items("03", "Thunder Stone", "Evolution Stone", "A stone that sparkles with electricity", "Evolves Pokemon like Pikachu, Eevee", true, true, 3000, 5000, 1500);
    evolutionItems[3] = new Items("04", "Leaf Stone", "Evolution Stone", "A stone with a leaf pattern", "Evolves Pokemon like Gloom, Weepinbell, Exeggcute", true, true, 3000, 5000, 1500);
    evolutionItems[4] = new Items("05", "Moon Stone", "Evolution Stone", "A stone that glows faintly in the moonlight", "Evolves Pokemon like Nidorina, Clefairy, Jigglypuff, etc.", false, true, 0, 0, 1500);
    evolutionItems[5] = new Items("06", "Sun Stone", "Evolution Stone", "A stone that glows like the sun", "Evolves Pokemon like Gloom, Sunkern, Cottonee, etc.", true, true, 3000, 5000, 1500);
    evolutionItems[6] = new Items("07", "Shiny Stone", "Evolution Stone", "A stone that sparkles brightly", "Evolves Pokemon like Togetic, Roselia, Minccino, etc.", true, true, 3000, 5000, 1500);
    evolutionItems[7] = new Items("08", "Dusk Stone", "Evolution Stone", "A dark stone that is ominous in appearance", "Evolves Pokemon like Murkrow, Misdreavus, Doublade, etc.", true, true, 3000, 5000, 1500);
    evolutionItems[8] = new Items("09", "Dawn Stone", "Evolution Stone", "A stone that sparkles like the morning sky", "Evolves male Kirlia into Gallade, female Snorunt into Froslass", true, true, 3000, 5000, 1500);
    evolutionItems[9] = new Items("10", "Ice Stone", "Evolution Stone", "A stone that is cold to the touch", "Evolves Pokemon like Alolan Vulpix, Galarian Darumaka, Eevee", true, true, 3000, 5000, 1500);

    // Merge all into itemList
    for (int i = 0; i < 10; i++) {
        itemList[itemCount++] = availItems[i];
        itemList[itemCount++] = evolutionItems[i];
    }
}

    /**
        Returns the ID of the item.
        @return the item ID as a String
    */
    public String getitemID()
    {
        return itemID;
    }

     /**
        Returns the Name of the item.
        @return the item name as a String
    */
    public String getitemName()
    {
        return itemName;
    }

    /**
        Returns the Category of the item.
        @return the item category as a String
    */
    public String getitemCategory()
    {
        return itemCategory;
    }

    /**
        Returns the Description of the item.
        @return the item description as a String
    */
    public String getitemDesc()
    {
        return itemDesc;
    }

    /**
        Returns the Effects of the item.
        @return the item effects as a String
    */
    public String getitemEffects()
    {
        return itemEffects;
    }

    /**
       Returns whether the item is marked to be sold in shops.
       @return true if the item can be sold; false otherwise
    */
    public boolean getToSold()
    {
        return toSold;
    }

    /**
       Returns whether the item is marked for evolution use.
       @return true if the item is associated with evolution stones; false otherwise
    */
    public boolean getForEvo()
    {
        return forEvo;
    }

    /**
    Returns the starting buying price of the item.
    @return the minimum or fixed buying price as a double
    */
    public double getstartBuyingPrice()
    {
        return startBuyingPrice;
    }

     /**
    Returns the end buying price of the item.
    @return the maximum or fixed buying price as a double
    */
    public double getendBuyingPrice()
    {
        return endBuyingPrice;
    }

     /**
    Returns the selling price of the item.
    @return the selling price as a double
    */
    public double getsellingPrice()
    {
        return sellingPrice;
    }

    /**
    This method displays the details of the item in a formatted manner.
    It includes the item ID, name, category, description, effects, buying price
    */
    public void displayItems()
    {
        System.out.println("\n ================================================================================================");
        System.out.printf("\n\tItem ID %s: %s",getitemID(), getitemName());
        System.out.printf("\n\tCategory: " + getitemCategory());
        System.out.printf("\n\tDescription: " + getitemDesc());
        System.out.printf("\n\tEffects: " + getitemEffects());

        if (getToSold() == true)
        {
            if(getForEvo() == false)
            {
                System.out.printf("\n\tBuying Price: P %.2f", getstartBuyingPrice());
            }
            else
            {
                System.out.printf("\n\tBuying Price: P %.2f - P %.2f", getstartBuyingPrice(), getendBuyingPrice());
            }
        }
        else 
        {
            System.out.printf("\n\tBuying Price: Not Sold");
        }
        System.out.printf("\n\tSelling Price: P %.2f", getsellingPrice());
        System.out.println("\n\n ================================================================================================");
    }

    /**
    Searches for an item in the item list by its name.
    @param name the name of the item to search for
    @return the matching Items object if found, otherwise null
    */
    public static Items getItemByName(String name) 
    {
        for (Items items : itemList) 
        {  
            if (items != null && items.getitemName().equalsIgnoreCase(name.strip())) 
            {
                return items;
            }
        }
        return null;
    }

    /**
    Registers a new item into the item list if there is available space.
    If the item list is full, it prints an error message.
    @param item the item to be registered
    @return void
    */
    private static void registerItem(Items item) 
    {
        if(item==null){
            System.out.println("null item cannot be added");
            return;
        }
        if (itemCount < itemList.length) 
        {
            itemList[itemCount] = item; 
            itemCount++;
        } 
        else 
        {
            System.err.println("⚠ Item database full! Cannot register: " + item.getitemName());
        }
    }
}
