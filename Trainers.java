import java.io.*;
import java.util.*;
import java.text.ParseException; // Make sure this is imported
import java.text.SimpleDateFormat; // <--- YOU NEED THIS IMPORT


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
    private java.util.Date birth;
    private String sex;
    private String home;
    private String description;
    private double money;

    private Pokemon[] pokemonTeam; // Set to 6 max
    private Pokemon[] pokemonPC; // Set to 10 max
    private int lineupCount; // Counter; 6 max
    private int storageCount; // Counter; 10 max

    private Items[] bag; // Set to 50 max
    private Items[] uniqueItems; // To track which items are already in the bag
    private int uniqueCount; // Set to 10 max; unique means that Trainers can only have 10 kinds of items max
    private int itemCount; // Track number of items in bag; 50 max
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

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
        this.birth = new java.util.Date(month, day, year);
        this.sex = sex;
        this.home = home;
        this.description = description;
        this.money = 1000000;

        // Default attributes; updated through other methods
        this.pokemonTeam = new Pokemon[6]; // Maximum of 6 Pokemon
        this.pokemonPC = new Pokemon[10]; // Maximum of 10 Pokemon
        this.lineupCount = 0;
        this.storageCount = 0;

        this.bag = new Items[50]; // Maximum of 50 items
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
            this.birth = new java.util.Date(month, day, year); // Assuming Date(month, day, year) constructor

        } catch (ParseException e) {
            System.err.println("Error parsing birth date string: '" + birthDateString + "' for trainer " + name + ". Setting default date.");
            this.birth = new java.util.Date(1, 1, 2000); // Fallback if parsing fails
        }

        // Initialize Pokemon arrays (not loaded from trainers.txt in this format)
        this.pokemonTeam = new Pokemon[6];
        this.pokemonPC = new Pokemon[10];
        // FIX: lineupCount and storageCount should be instance variables, not static.
        this.lineupCount = 0; // Fix here as well
        this.storageCount = 0; // Fix here as well

        // Initialize item arrays
        this.bag = new Items[50];
        this.uniqueItems = new Items[10];
        this.uniqueCount = 0;
        this.itemCount = 0;

        if (itemNamesFromBag != null && !itemNamesFromBag.isEmpty()) {
            Set<String> tempUniqueNames = new HashSet<>();
            for (String itemName : itemNamesFromBag) {
                if (this.itemCount < 50) {
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

    public Trainers() {
        // Initialize default values
        this.ID = "00000";
        this.name = "Unknown";
        this.birth = new java.util.Date(1, 1, 2000); // Your custom Date class
        this.sex = "Unknown";
        this.home = "Unknown";
        this.description = "No description";
        this.money = 0;
        this.pokemonTeam = new Pokemon[6];
        this.pokemonPC = new Pokemon[10];
        this.bag = new Items[50];
        this.uniqueItems = new Items[10];
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
    public java.util.Date getBirth() {
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
    public int getLineupCount() {
        return lineupCount;
    }

    /** This method returns a Trainer object's storageCount
     *
     * @return pokemonCount: a Trainer object's storageCount
     */
    public int getStorageCount() {
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
    public void setID(String ID){
        this.ID = ID;
    }
    public void setName(String name){
        this.name = name;
    }

    public void setSex(String sex){
        this.sex = sex;
    }

    public void setHome(String home){
        this.home = home;
    }

    public void setDescription(String description){
        this.description = description;
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


    public void saveToFile() {
        // Read all existing trainers first
        List<String> allTrainersData = new ArrayList<>();
        boolean trainerExists = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("trainers.txt"))) {
            StringBuilder currentTrainer = new StringBuilder();
            boolean isCurrentTrainer = false;

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("[TRAINER_START]")) {
                    currentTrainer = new StringBuilder();
                    isCurrentTrainer = true;
                }

                if (isCurrentTrainer) {
                    currentTrainer.append(line).append("\n");
                    if (line.startsWith("ID=") && line.substring(3).equals(this.ID)) {
                        trainerExists = true;
                        // Skip saving this old version - we'll replace it
                        while (!(line = reader.readLine()).equals("[TRAINER_END]")) {}
                        currentTrainer = new StringBuilder();
                        isCurrentTrainer = false;
                    }

                    if (line.equals("[TRAINER_END]")) {
                        if (!currentTrainer.toString().contains("ID=" + this.ID)) {
                            allTrainersData.add(currentTrainer.toString());
                        }
                        isCurrentTrainer = false;
                    }
                } else {
                    allTrainersData.add(line + "\n");
                }
            }
        } catch (IOException e) {
            // File may not exist yet - that's okay
        }

        // Add current trainer's data
        allTrainersData.add(this.toFileString());

        // Write everything back
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("trainers.txt"))) {
            for (String data : allTrainersData) {
                writer.write(data);
            }
        } catch (IOException e) {
            System.err.println("Failed to save trainers: " + e.getMessage());
        }
    }
    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[TRAINER_START]\n");

        // Core trainer info
        sb.append("ID=").append(this.ID).append("\n");
        sb.append("Name=").append(this.name).append("\n");

        // FIXED: Proper date formatting for custom Date class
        sb.append("Birth=")
                .append(String.format("%02d/%02d/%04d",
                        this.birth.getMonth(),
                        this.birth.getDay(),
                        this.birth.getYear()))
                .append("\n");

        sb.append("Sex=").append(this.sex).append("\n");
        sb.append("Home=").append(this.home).append("\n");
        sb.append("Description=").append(this.description).append("\n");
        sb.append(String.format("Money=%.2f\n", this.money));

        // Pokémon Team (Lineup)
        sb.append("[POKEMON_TEAM]\n");
        for (int i = 0; i < this.lineupCount; i++) {
            if (this.pokemonTeam[i] != null) {
                sb.append(this.pokemonTeam[i].toCsvString()).append("\n");
            }
        }

        // Pokémon Storage (PC)
        sb.append("[POKEMON_STORAGE]\n");
        for (int i = 0; i < this.storageCount; i++) {
            if (this.pokemonPC[i] != null) {
                sb.append(this.pokemonPC[i].toCsvString()).append("\n");
            }
        }

        // Items in Bag
        sb.append("[ITEMS]\n");
        for (int i = 0; i < this.itemCount; i++) {
            if (this.bag[i] != null) {
                sb.append(this.bag[i].getitemName())
                        .append(",")
                        .append(this.bag[i].getQuantity())
                        .append("\n");
            }
        }

        // Unique Items
        sb.append("[UNIQUE_ITEMS]\n");
        for (int i = 0; i < this.uniqueCount; i++) {
            if (this.uniqueItems[i] != null) {
                sb.append(this.uniqueItems[i].getitemName()).append("\n");
            }
        }

        sb.append("[TRAINER_END]\n");
        return sb.toString();
    }

    // Helper method to write Pokémon data
    private void writePokemon(PrintWriter writer, Pokemon p) {
        writer.println("POKEMON_START");
        writer.println(p.getPokedexNo());
        writer.println(p.getName());
        writer.println(p.getType1());
        writer.println(p.getType2());
        writer.println(p.getBaseLevel());
        // Add other Pokémon attributes as needed
        writer.println("MOVES_START");
        for (Moves move : p.getMoves()) {
            if (move != null) writer.println(move.getName());
        }
        writer.println("POKEMON_END");
    }
    public static Trainers loadFromFile(String trainerID) {
        try (BufferedReader reader = new BufferedReader(new FileReader("trainers.txt"))) {
            Trainers currentTrainer = null;
            String section = null;

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // Start of a new trainer block
                if (line.equals("[TRAINER_START]")) {
                    currentTrainer = new Trainers(); // Reset for new trainer
                    section = null;
                    continue;
                }

                // End of a trainer block: Check if ID matches
                if (line.equals("[TRAINER_END]")) {
                    if (currentTrainer != null && currentTrainer.getID().equals(trainerID)) {
                        return currentTrainer; // Return ONLY the correct trainer
                    }
                    currentTrainer = null; // Discard non-matching trainers
                    continue;
                }

                // Section headers (e.g., [POKEMON_TEAM])
                if (line.startsWith("[")) {
                    section = line.replace("[", "").replace("]", "");
                    continue;
                }

                // Only process if we're inside a trainer block
                if (currentTrainer != null) {
                    if (section == null) {
                        // Parse trainer attributes
                        if (line.startsWith("ID=")) {
                            currentTrainer.ID = line.substring(3).trim();
                        } else if (line.startsWith("Name=")) {
                            currentTrainer.name = line.substring(5).trim();
                        } else if (line.startsWith("Birth=")) {
                            try {
                                java.util.Date parsedDate = DATE_FORMAT.parse(line.substring(6).trim());
                                Calendar cal = Calendar.getInstance();
                                cal.setTime(parsedDate);
                                currentTrainer.birth = new java.util.Date(
                                        cal.get(Calendar.MONTH) + 1,  // Your Date class expects 1-12
                                        cal.get(Calendar.DAY_OF_MONTH),
                                        cal.get(Calendar.YEAR)
                                );
                            } catch (ParseException e) {
                                currentTrainer.birth = new java.util.Date(1, 1, 2000); // Default date
                            }
                        } else if (line.startsWith("Sex=")) {
                            currentTrainer.sex = line.substring(4).trim();
                        } else if (line.startsWith("Home=")) {
                            currentTrainer.home = line.substring(5).trim();
                        } else if (line.startsWith("Description=")) {
                            currentTrainer.description = line.substring(12).trim();
                        } else if (line.startsWith("Money=")) {
                            currentTrainer.money = Double.parseDouble(line.substring(6).trim());
                        }
                    } else {
                        // Parse section-specific data
                        switch (section) {
                            case "POKEMON_TEAM":
                                Pokemon teamPoke = Pokemon.fromCsvString(line);
                                if (teamPoke != null) {
                                    currentTrainer.addPokemonToLineup(teamPoke);
                                }
                                break;

                            case "POKEMON_STORAGE":
                                Pokemon storagePoke = Pokemon.fromCsvString(line);
                                if (storagePoke != null) {
                                    currentTrainer.addPokemonToStorage(storagePoke);
                                }
                                break;

                            case "ITEMS":
                                String[] itemData = line.split(",");
                                if (itemData.length >= 2) {
                                    Items item = Items.getItemByName(itemData[0]);
                                    if (item != null) {
                                        item.setQuantity(Integer.parseInt(itemData[1]));
                                        currentTrainer.addItemToBag(item);
                                    }
                                }
                                break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading trainer file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing numeric value: " + e.getMessage());
        }

        return null; // Trainer not found
    }
    private static Map<String, Trainers> trainerCache = new HashMap<>();

    // Call this method once at startup
    public static void loadAllTrainers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("trainers.txt"))) {
            Trainers currentTrainer = null;
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.equals("[TRAINER_START]")) {
                    currentTrainer = new Trainers();
                } else if (line.equals("[TRAINER_END]")) {
                    if (currentTrainer != null) {
                        trainerCache.put(currentTrainer.getID(), currentTrainer);
                        currentTrainer = null;
                    }
                } else if (currentTrainer != null) {
                    // Parse trainer data (same as before)
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading trainers: " + e.getMessage());
        }
    }

    // Now fetch any trainer in O(1) time
    public static Trainers getTrainerById(String trainerID) {
        return trainerCache.get(trainerID.trim());
    }
    private static Pokemon readPokemon(BufferedReader reader) throws IOException {
        if (!"POKEMON_START".equals(reader.readLine())) throw new IOException("Invalid Pokémon format");

        Pokemon p = new Pokemon(
                Integer.parseInt(reader.readLine()), // pokedexNo
                reader.readLine(), // name
                reader.readLine(), // type1
                reader.readLine(), // type2
                Integer.parseInt(reader.readLine()), // baseLevel
                Integer.parseInt(reader.readLine()),
                Integer.parseInt(reader.readLine()),
                Integer.parseInt(reader.readLine()),
                Integer.parseInt(reader.readLine()),
                Integer.parseInt(reader.readLine()),
                Integer.parseInt(reader.readLine()),
                Integer.parseInt(reader.readLine())
        );

        // Read moves
        if ("MOVES_START".equals(reader.readLine())) {
            String moveName;
            while (!(moveName = reader.readLine()).equals("POKEMON_END")) {
                p.teachMove(moveName, false);
            }
        }

        return p;
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
        if (itemCount >= 50) {
            return "You cannot add more items. Bag is full (50/50).";
        }

        // Check if item already exists in the bag
        for (int i = 0; i < 50; i++) {
            if (bag[i] != null && bag[i].getitemName().equals(item.getitemName())) {
                bag[i].setQuantity(bag[i].getQuantity() + 1);
                itemCount++; // ✅ increase total quantity
                return item.getitemName() + " quantity increased to " + bag[i].getQuantity() + ".";
            }
        }

        // Item is not in the bag — it is a new unique item
        if (uniqueCount >= 10) {
            return "You cannot add more unique item types. Limit is 10.";
        }

        Items copy = Items.cloneItem(item);
        copy.setQuantity(1);

        // Add to bag
        for (int i = 0; i < 50; i++) {
            if (bag[i] == null) {
                bag[i] = copy;
                break;
            }
        }
            itemCount++;       // ✅ total quantity increases
            uniqueItems[uniqueCount] = copy;
            uniqueCount++;

            return copy.getitemName() + " was successfully added to your bag.";
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
    public String useItem(Items item, Pokemon pokemon) {
        if (item == null || pokemon == null) {
            return "Error: Invalid item or Pokémon selection.";
        }

        // Check if item exists in bag (by ID)
        boolean itemFound = false;
        for (int i = 0; i < itemCount; i++) {
            if (bag[i] != null && bag[i].getitemID().equals(item.getitemID())) {
                itemFound = true;
                break;
            }
        }
        if (!itemFound) {
            return "Error: You don't have this item in your bag.";
        }

        // Handle different item categories
        String itemCategory = item.getitemCategory();
        String itemName = item.getitemName();
        String result;

        if (itemCategory.equals("Evolution Stone")) {
            // Evolution stone logic
            if (!pokemon.canEvolveWith(itemName)) {
                return pokemon.getName() + " cannot evolve with " + itemName + "!";
            }

            // Perform evolution
            Pokemon evolvedForm = pokemon.evolveWithStone(itemName);
            if (evolvedForm == null) {
                return "Evolution failed for " + pokemon.getName() + "!";
            }

            // Replace the Pokémon in team/storage
            replacePokemon(pokemon, evolvedForm);

            // Remove one instance of the item
            removeItemFromBag(item);

            return pokemon.getName() + " evolved into " + evolvedForm.getName() + "!";

        } else if (itemName.contains("Vitamin") || itemName.contains("Feather")) {
            // Vitamin/Feather logic - stat boosting items
            String stat = item.getitemEffects(); // Should return "HP", "Attack", etc.
            int boostAmount = itemName.contains("Vitamin") ? 10 : 1; // Vitamins give +10, feathers +1

            switch (stat) {
                case "HP":
                    pokemon.setHP(pokemon.getHP() + boostAmount);
                    break;
                case "Attack":
                    pokemon.setAtk(pokemon.getAtk() + boostAmount);
                    break;
                case "Defense":
                    pokemon.setDef(pokemon.getDef() + boostAmount);
                    break;
                case "Speed":
                    pokemon.setSpd(pokemon.getSpd() + boostAmount);
                    break;
                default:
                    return "Error: Unknown stat to boost!";
            }

            // Remove one instance of the item
            removeItemFromBag(item);

            return pokemon.getName() + "'s " + stat + " increased by " + boostAmount + "!";

        } else if (itemName.equals("Rare Candy")) {
            // Rare Candy logic - level up
            if (pokemon.getBaseLevel() >= 100) {
                return pokemon.getName() + " is already at maximum level!";
            }

            pokemon.levelUp();
            removeItemFromBag(item);
            return pokemon.getName() + " grew to level " + pokemon.getBaseLevel() + "!";

        } else {
            // Generic item usage
            removeItemFromBag(item);
            return "Used " + itemName + " on " + pokemon.getName() + ".";
        }
    }

    /**
     * Helper method to replace a Pokémon with its evolved form in team/storage
     * @param original The original Pokémon
     * @param evolved The evolved Pokémon
     */
    private void replacePokemon(Pokemon original, Pokemon evolved) {
        // Check team first
        for (int i = 0; i < lineupCount; i++) {
            if (pokemonTeam[i] != null && pokemonTeam[i].equals(original)) {
                pokemonTeam[i] = evolved;
                return;
            }
        }

        // Check storage if not found in team
        for (int i = 0; i < storageCount; i++) {
            if (pokemonPC[i] != null && pokemonPC[i].equals(original)) {
                pokemonPC[i] = evolved;
                return;
            }
        }
    }
    public boolean hasItem(Items item) {
        for (int i = 0; i < itemCount; i++) {
            if (bag[i] != null && bag[i].getitemName().equals(item.getitemName())) {
                return true;
            }
        }
        return false;
    }
}