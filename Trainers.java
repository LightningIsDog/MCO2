import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException; // Make sure this is imported
import java.text.SimpleDateFormat; // <--- YOU NEED THIS IMPORT
import java.util.Calendar; // <--- YOU NEED THIS IMPORT for java.util.Calendar

/** This class represents a collection of trainers in the game.
 *  @author Justin Miguel Agustin L. Lotilla
 *  @author Maurienne Marie M. Mojica
 *  @version 5.0
 */

public class Trainers {
    private static int trainerCount = 0;

    private int trainerNumber; // Indexing; no delete trainer method in specs implies that this is fixed already

    private String ID; // Made String to handle entries that start with 0s
    private String name;
    private Date birth;
    private String sex;
    private String home;
    private String description;
    private double money;

    private Pokemon[] pokemonTeam; // Set to 6 max
    private Pokemon[] pokemonPC; // Set to 10 max
    private static int lineupCount; // Counter; 6 max
    private static int storageCount; // Counter; 10 max

    private Items[] bag; // Set to 50 max
    private Items[] uniqueItems; // To track which items are already in the bag
    private int uniqueCount; // Set to 10 max; unique means that Trainers can only have 10 kinds of items max
    private int itemCount; // Track number of items in bag; 50 max
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd-yyyy");

    /** This method initializes a Trainer object
     *
     * @param ID: user's input to ID
     * @param name: user's input to name
     * @param sex: user's input to sex
     * @param home: user's input to home
     * @param description: user's input to description
     */
    public Trainers(String ID, String name, int month, int day, int year, String sex, String home, String description) {
        this.ID = ID;
        this.name = name;
        this.birth = new Date(month, day, year);
        this.sex = sex;
        this.home = home;
        this.description = description;
        this.money = 1000000;

        // Default attributes; updated through other methods
        this.pokemonTeam = new Pokemon[6]; // Maximum of 6 Pokemon
        this.pokemonPC = new Pokemon[10]; // Maximum of 10 Pokemon
        this.lineupCount = 0;
        this.storageCount = 0;

        this.bag = new Items[99]; // Maximum of 50 items
        this.uniqueItems = new Items[10];
        this.uniqueCount = 0;
        this.itemCount = 0;

        this.trainerNumber = trainerCount++;
    }

    public Trainers(String ID, String name, String birthDateString, String sex, String home, String description,
                    double money, List<String> itemNamesFromBag) {
        this.ID = ID;
        this.name = name;
        this.sex = sex;
        this.home = home;
        this.description = description; // This holds the 'occupation' from the file
        this.money = money;

        // Parse birthDateString into your Date object
        try {
            java.util.Date parsedUtilDate = DATE_FORMAT.parse(birthDateString);
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.setTime(parsedUtilDate);
            int month = cal.get(java.util.Calendar.MONTH) + 1; // Calendar.MONTH is 0-indexed
            int day = cal.get(java.util.Calendar.DAY_OF_MONTH);
            int year = cal.get(java.util.Calendar.YEAR);
            this.birth = new Date(month, day, year); // Assuming Date(month, day, year) constructor

        } catch (ParseException e) {
            System.err.println("Error parsing birth date string: '" + birthDateString + "' for trainer " + name + ". Setting default date.");
            this.birth = new Date(1, 1, 2000); // Fallback if parsing fails
        }

        // Initialize Pokemon arrays (not loaded from trainers.txt in this format)
        this.pokemonTeam = new Pokemon[6];
        this.pokemonPC = new Pokemon[10];
        // FIX: lineupCount and storageCount should be instance variables, not static.
        this.lineupCount = 0; // Fix here as well
        this.storageCount = 0; // Fix here as well

        // Initialize item arrays
        this.bag = new Items[99];
        this.uniqueItems = new Items[10];
        this.uniqueCount = 0;
        this.itemCount = 0;

        if (itemNamesFromBag != null && !itemNamesFromBag.isEmpty()) {
    Set<String> tempUniqueNames = new HashSet<>();
    for (String itemName : itemNamesFromBag) {
        if (this.itemCount < 99) {
            Items loadedItem = Items.getItemByName(itemName);

            if (loadedItem != null) {
                Items clonedItem = Items.cloneItem(loadedItem); // ✅ use cloneItem method

                this.bag[this.itemCount] = clonedItem;
                this.itemCount++;

                if (tempUniqueNames.add(itemName)) {
                    if (this.uniqueCount < 10) {
                        this.uniqueItems[this.uniqueCount] = clonedItem;
                        this.uniqueCount++;
                    }
                }
            } else {
                System.err.println("Warning: Item '" + itemName + "' not found when loading trainer " + name + " (ID: " + ID + ").");
            }
        }
    }
}

        this.trainerNumber = trainerCount++;
    }
    /** This method returns the Trainer class's trainerCount
     *
     * @return trainerCount: the Trainer class's trainerCount
     */
    public static int getTrainerCount() {
        return trainerCount;
    }

    /** This method returns a Trainer object's trainerNumber
     *
     * @return trainerNumber: a Trainer object's trainerNumber
     */
    public int getTrainerNumber() {
        return trainerNumber;
    }

    /** This method returns a Trainer object's ID
     *
     * @return ID: a Trainer object's ID
     */
    public String getID() {
        return ID;
    }

    /** This method returns a Trainer object's name
     *
     * @return name: a Trainer object's name
     */
    public String getName() {
        return name;
    }

    /** This method returns a Trainer object's birth year
     *
     * @return birth: a Trainer object's birth year
     */
    public Date getBirth() {
        return birth;
    }

    /** This method returns a Trainer object's sex
     *
     * @return sex: a Trainer object's sex
     */
    public String getSex() {
        return sex;
    }

    /** This method returns a Trainer object's home town
     *
     * @return home: a Trainer object's home town
     */
    public String getHome() {
        return home;
    }

    /** This method returns a Trainer object's description
     *
     * @return description: a Trainer object's description
     */
    public String getDescription() {
        return description;
    }

    /** This method returns a Trainer object's money
     *
     * @return money: a Trainer object's money
     */
    public double getMoney() {
        return money;
    }

    /** This method returns a Trainer object's Pokemon lineup
     *
     * @return pokemonLineup: a Trainer object's Pokemon lineup
     */
    public Pokemon[] getPokemonLineup() {
        return pokemonTeam;
    }

    public Pokemon getPokemonFromLineup(int i) {
        return pokemonTeam[i];
    }

    /** This method returns a Trainer object's Pokemon storage
     *
     * @return pokemonCount: a Trainer object's Pokemon storage
     */
    public Pokemon[] getPokemonStorage() {
        return pokemonPC;
    }

    public Pokemon getPokemonFromStorage(int i) {
        return pokemonPC[i];
    }

    /** This method returns a Trainer object's lineupCount
     *
     * @return pokemonCount: a Trainer object's lineupCount
     */
    public static int getLineupCount() {
        return lineupCount;
    }

    /** This method returns a Trainer object's storageCount
     *
     * @return pokemonCount: a Trainer object's storageCount
     */
    public static int getStorageCount() {
        return storageCount;
    }

    /** This method returns a Trainer object's bag
     *
     * @return pokemonCount: a Trainer object's bag
     */
    public Items[] getBag() {
        return bag;
    }

    /** This method returns a Trainer object's uniqueItems
     *
     * @return pokemonCount: a Trainer object's uniqueItems
     */
    public Items[] getUniqueItems() {
        return uniqueItems;
    }

    /** This method returns a Trainer object's uniqueCount
     *
     * @return pokemonCount: a Trainer object's uniqueCount
     */
    public int getUniqueCount() {
        return uniqueCount;
    }

    /** This method returns a Trainer object's itemCount
     *
     * @return pokemonCount: a Trainer object's itemCount
     */
    public int getItemCount() {
        return itemCount;
    }

    // Setters
    /** This method sets the Trainer's money.
     *
     * @param money: the amount of money to set
     */
    public void setMoney(double money) {
        this.money = money;
    }

    /** This method sets the Trainer's Pokemon lineup.
     *
     * @param pokemonLineup: the array of Pokemon to set as the lineup (max 6)
     */
    public void setPokemonLineup(Pokemon[] pokemonLineup) {
        this.pokemonTeam = pokemonLineup;
    }

    /** This method sets the Trainer's Pokemon storage.
     *
     * @param pokemonStorage: the array of Pokemon to set as the storage (max 10)
     */
    public void setPokemonStorage(Pokemon[] pokemonStorage) {
        this.pokemonPC = pokemonStorage;
    }

    /** This method sets the count of Pokemon in the lineup.
     *
     * @param lineupCount: the number of Pokemon in the lineup (max 6)
     */
    public void setLineupCount(int lineupCount) {
        this.lineupCount = lineupCount;
    }

    /** This method sets the count of Pokemon in storage.
     *
     * @param storageCount: the number of Pokemon in storage (max 10)
     */
    public void setStorageCount(int storageCount) {
        this.storageCount = storageCount;
    }

    /** This method sets the Trainer's item bag.
     *
     * @param bag: the array of items to set in the bag (max 50)
     */
    public void setBag(Items[] bag) {
        this.bag = bag;
    }

    /** This method sets the Trainer's list of unique items.
     *
     * @param uniqueItems: the array of unique items (max 10 types)
     */
    public void setUniqueItems(Items[] uniqueItems) {
        this.uniqueItems = uniqueItems;
    }

    /** This method sets the count of unique items.
     *
     * @param uniqueCount: the number of unique item types in the bag (max 10)
     */
    public void setUniqueCount(int uniqueCount) {
        this.uniqueCount = uniqueCount;
    }

    /** This method sets the total item count in the bag.
     *
     * @param itemCount: the total number of items in the bag (max 50)
     */
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public void display(){
        System.out.println("Trainer Card");
        System.out.println("ID No.      : " + ID);
        System.out.println("Name        : " + name);
        System.out.println("Birthdate   : " + birth );
        System.out.println("Sex         : " + sex);
        System.out.println("Hometown    : " + home);
        System.out.println("Description : " + description);
        System.out.println("Money       : " + money);

        System.out.println("\nPokemon in Lineup:");
        if (pokemonTeam != null && lineupCount > 0) {
            for (int i = 0; i < lineupCount; i++) {
                if (pokemonTeam[i] != null) {
                    System.out.print("  - ");
                    System.out.print(pokemonTeam[i].getName());
                }
            }
        } else {
            System.out.println("  None");
        }

        System.out.println("Pokemon in Storage:");
        if (pokemonPC != null && storageCount > 0) {
            for (int i = 0; i < storageCount; i++) {
                if (pokemonPC[i] != null) {
                    System.out.print("  - ");
                    System.out.print(pokemonPC[i].getName());
                }
            }
        } else {
            System.out.println("  None");
        }

        System.out.println("----------------------------");

    }
    public boolean canAddToTeam() {
        return lineupCount < 6;
    }

    public boolean canAddToPC() {
        return storageCount < 10;
    }

    public String addPokemon(Pokemon pokemon) {
        if (canAddToTeam()) {
            pokemonTeam[lineupCount++] = pokemon;
            return pokemon.getName() + " was added to your team!";
        } else if (canAddToPC()) {
            pokemonPC[storageCount++] = pokemon;
            return pokemon.getName() + " was sent to your PC (team was full)!";
        }
        return "Both your team and PC are full!";
    }
    // In Trainers.java
    public String addPokemonToLineup(Pokemon pokemon) {
        if (lineupCount >= 6) {
            return "Error: Your team is already full (6/6).";
        }

        pokemonTeam[lineupCount] = pokemon;
        lineupCount++;
        return "Successfully added " + pokemon.getName() + " to your team!";
    }

    public String addPokemonToStorage(Pokemon pokemon) {
        if (storageCount >= 10) {
            return "Error: Your PC storage is full (10/10).";
        }

        pokemonPC[storageCount] = pokemon;
        storageCount++;
        return "Successfully added " + pokemon.getName() + " to your PC storage!";
    }

    public String switchPokemon(int lineupIndex, int storageIndex) {
        // Validate indices
        if (lineupIndex == -1 && storageIndex == -1) {
            return "Error: No Pokémon selected!";
        }

        // Moving from lineup to storage
        if (lineupIndex >= 0 && storageIndex == -1) {
            if (lineupIndex >= lineupCount) {
                return "Error: Invalid lineup index!";
            }
            if (storageCount >= pokemonPC.length) {
                return "Error: Storage is full!";
            }

            // Move to storage
            Pokemon pokemon = pokemonTeam[lineupIndex];
            pokemonPC[storageCount++] = pokemon;

            // Shift lineup to fill gap
            for (int i = lineupIndex; i < lineupCount - 1; i++) {
                pokemonTeam[i] = pokemonTeam[i + 1];
            }
            pokemonTeam[--lineupCount] = null;

            return pokemon.getName() + " moved to storage!";
        }
        // Moving from storage to lineup
        else if (storageIndex >= 0 && lineupIndex == -1) {
            if (storageIndex >= storageCount) {
                return "Error: Invalid storage index!";
            }
            if (lineupCount >= pokemonTeam.length) {
                return "Error: Lineup is full!";
            }

            // Move to lineup
            Pokemon pokemon = pokemonPC[storageIndex];
            pokemonTeam[lineupCount++] = pokemon;

            // Shift storage to fill gap
            for (int i = storageIndex; i < storageCount - 1; i++) {
                pokemonPC[i] = pokemonPC[i + 1];
            }
            pokemonPC[--storageCount] = null;

            return pokemon.getName() + " added to your team!";
        }
        // Direct swap between lineup and storage
        else if (lineupIndex >= 0 && storageIndex >= 0) {
            if (lineupIndex >= lineupCount || storageIndex >= storageCount) {
                return "Error: Invalid indices!";
            }

            // Perform swap
            Pokemon temp = pokemonTeam[lineupIndex];
            pokemonTeam[lineupIndex] = pokemonPC[storageIndex];
            pokemonPC[storageIndex] = temp;

            return "Swapped " + temp.getName() + " and " + pokemonTeam[lineupIndex].getName();
        }

        return "Error: Invalid operation!";
    }
    public boolean canReleaseFromLineup() {
        return lineupCount > 1; // Must keep at least 1 Pokémon in team
    }

    public boolean canReleaseFromStorage() {
        return true; // Can always release from storage
    }
    public Pokemon releasePokemon(boolean fromLineup, int index) {
        if (fromLineup) {
            if (index < 0 || index >= lineupCount || lineupCount <= 1) {
                return null; // Must keep at least 1 Pokémon in team
            }

            Pokemon released = pokemonTeam[index];
            System.arraycopy(pokemonTeam, index + 1, pokemonTeam, index, lineupCount - index - 1);
            pokemonTeam[--lineupCount] = null;
            return released;
        } else {
            if (index < 0 || index >= storageCount) {
                return null;
            }

            Pokemon released = pokemonPC[index];
            System.arraycopy(pokemonPC, index + 1, pokemonPC, index, storageCount - index - 1);
            pokemonPC[--storageCount] = null;
            return released;
        }
    }


    /**
     * Releases a Pokémon from storage
     * @param index Index of Pokémon in storage to release
     * @return The released Pokémon (or null if invalid index)
     */
    public Pokemon releaseFromStorage(int index) {
        if (index < 0 || index >= storageCount) {
            return null;
        }

        Pokemon released = pokemonPC[index];

        // Shift remaining Pokémon to fill the gap
        for (int i = index; i < storageCount - 1; i++) {
            pokemonPC[i] = pokemonPC[i + 1];
        }
        pokemonPC[--storageCount] = null;

        return released;
    }

    public void saveToFile() {
        // Implement saving trainer data to file
        // This should update the trainer's Pokémon lineup and storage
    }

    public boolean isItemUnique(Items item) {
        for (int i = 0; i < uniqueCount; i++) {
            if (uniqueItems[i] != null && uniqueItems[i].getitemName().equals(item.getitemName())) {
                return false; // Already exists
            }
        }
        return true; // New unique item
    }

    public String addItemToBag(Items item) {
    if (itemCount >= 99) {
        return "You cannot add more items. Bag is full (99/99).";
    }

    boolean isUnique = isItemUnique(item);
    if (isUnique && uniqueCount >= 10) {
        return "You cannot add more unique item types. Limit is 10.";
    }

    // ✅ Use centralized clone method
    Items copy = Items.cloneItem(item);

    bag[itemCount] = copy;
    itemCount++;

    if (isUnique) {
        uniqueItems[uniqueCount] = copy;
        uniqueCount++;
    }

    return copy.getitemName() + " was successfully added to your bag.";
}

    /**
     * Converts the current Trainer object into a string format suitable for saving to a file.
     * Format: ID-Name-Birthday(MM-dd-yyyy)-Sex-Home-Description(Occupation)-Money-Item1,Item2,Item3,...
     * Items part will be omitted if no items are present.
     * @return A string representation of the Trainer.
     */
    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ID).append("-");
        sb.append(name).append("-");
        // Use your Date object's method to get the birthday string or format it
        // Assuming your Date class has a meaningful toString() for "MM-dd-yyyy"
        sb.append(birth.toFileString()).append("-"); // Assuming you add a toFileString to your Date class
        sb.append(sex).append("-");
        sb.append(home).append("-");
        sb.append(description).append("-"); // This field holds the 'occupation' from file
        sb.append(String.format("%.2f", money)); // Money is the last fixed field

        // Append items if any, separated by commas, preceded by a hyphen
        // We need to iterate through your 'bag' array to get item names
        List<String> currentBagItemNames = new ArrayList<>();
        for (int i = 0; i < itemCount; i++) { // Iterate up to itemCount, not bag.length
            if (bag[i] != null) {
                currentBagItemNames.add(bag[i].getitemName()); // Assuming Items has getitemName()
            }
        }

        if (!currentBagItemNames.isEmpty()) { // <--- FIXED: Now using currentBagItemNames
            sb.append("-"); // Add the hyphen separator for the items section
            sb.append(String.join(",", currentBagItemNames)); // <--- FIXED: Now using currentBagItemNames
        }
        return sb.toString();
    }

    /**
     * Parses a line from trainers.txt into a Trainers object.
     * Expected Format: ID-Name-Birthday(MM-dd-yyyy)-Sex-Home-Description(Occupation)-Money[-Item1,Item2,...]
     * @param line The line string from the file.
     * @return A Trainers object, or null if parsing fails.
     */
    public static Trainers fromFileString(String line) {
        String[] parts = line.split("-", 8); // Limit to 8 to keep the item string as one part

        if (parts.length < 7) {
            System.err.println("Malformed trainer line (too few parts): " + line);
            return null;
        }

        try {
            String trainerID = parts[0];
            String name = parts[1];
            String birthDateString = parts[2]; // Keep as String for the constructor
            String sex = parts[3];
            String home = parts[4];
            String description = parts[5]; // This corresponds to 'Occupation' in your file
            double money = Double.parseDouble(parts[6]);

            List<String> items = new ArrayList<>();
            if (parts.length == 8 && !parts[7].trim().isEmpty()) {
                String[] itemNames = parts[7].split(",");
                for (String itemName : itemNames) {
                    items.add(itemName.trim());
                }
            }
            // THIS LINE WILL NOW CALL THE NEW OVERLOADED CONSTRUCTOR
            return new Trainers(trainerID, name, birthDateString, sex, home, description, money, items);

        } catch (NumberFormatException e) {
            System.err.println("Error parsing money or other number for trainer: " + line + ". Error: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Unexpected error parsing trainer line: " + line + ". Error: " + e.getMessage());
            return null;
        }
    }

       public void removeItemFromBag(Items itemToRemove) {
    if (itemToRemove == null) return;

    for (int i = 0; i < itemCount; i++) {
        if (bag[i] != null && bag[i].getitemID().equals(itemToRemove.getitemID())) {
            // Shift items left
            for (int j = i; j < itemCount - 1; j++) {
                bag[j] = bag[j + 1];
            }
            bag[itemCount - 1] = null;
            itemCount--;

            // Check if this item still exists in the bag
            boolean stillExists = false;
            for (int j = 0; j < itemCount; j++) {
                if (bag[j] != null && bag[j].getitemID().equals(itemToRemove.getitemID())) {
                    stillExists = true;
                    break;
                }
            }

            // If not found elsewhere, remove from uniqueItems
            if (!stillExists) {
                for (int j = 0; j < uniqueCount; j++) {
                    if (uniqueItems[j] != null && uniqueItems[j].getitemID().equals(itemToRemove.getitemID())) {
                        // Shift unique items left
                        for (int k = j; k < uniqueCount - 1; k++) {
                            uniqueItems[k] = uniqueItems[k + 1];
                        }
                        uniqueItems[uniqueCount - 1] = null;
                        uniqueCount--;
                        break;
                    }
                }
            }

            break; // only remove 1 instance
        }
    }
}

public boolean hasPokemon(Pokemon p) {
    // Check lineup
    for (Pokemon teamMember : pokemonTeam) {
        if (teamMember != null && teamMember.getPokedexNo() == p.getPokedexNo()) {
            return true;
        }
    }

    // Check storage
    for (Pokemon stored : pokemonPC) {
        if (stored != null && stored.getPokedexNo() == p.getPokedexNo()) {
            return true;
        }
    }

    return false;
}
}