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
    private long money;

    private Pokemon[] pokemonTeam; // Set to 6 max
    private Pokemon[] pokemonPC; // Set to 10 max
    private static int lineupCount; // Counter; 6 max
    private static int storageCount; // Counter; 10 max

    private Items[] bag; // Set to 50 max
    private Items[] uniqueItems; // To track which items are already in the bag
    private int uniqueCount; // Set to 10 max; unique means that Trainers can only have 10 kinds of items max
    private int itemCount; // Track number of items in bag; 50 max

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

        this.bag = new Items[50]; // Maximum of 50 items
        this.uniqueItems = new Items[10];
        this.uniqueCount = 0;
        this.itemCount = 0;

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
    public long getMoney() {
        return money;
    }

    /** This method returns a Trainer object's Pokemon lineup
     *
     * @return pokemonLineup: a Trainer object's Pokemon lineup
     */
    public Pokemon[] getPokemonLineup() {
        return pokemonTeam;
    }

    /** This method returns a Trainer object's Pokemon storage
     *
     * @return pokemonCount: a Trainer object's Pokemon storage
     */
    public Pokemon[] getPokemonStorage() {
        return pokemonPC;
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
    public void setMoney(long money) {
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


    public void saveToFile() {
        // Implement saving trainer data to file
        // This should update the trainer's Pokémon lineup and storage
    }
}
