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
