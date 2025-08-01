import java.io.Serializable;
import java.util.*;

/** This class represents a Pokémon in the game.
 *  It includes attributes such as Pokedex number, name, types, base level,
 *  @author Justin Miguel Agustin L. Lotilla
 *  @author Maurienne Marie M. Mojica
 *  @version 5.0
 */

public class Pokemon implements Serializable{

    /**
     * Serial version UID for serialization compatibility.
     * This is used to ensure that a loaded class corresponds
     */
    private static final long serialVersionUID = 1L;

    /**
     * Pokemon dex number
     */
    private int PokedexNo;

    /**
     * Pokemon name
     */
    private String Name;

    /**
     * Pokemon primary type
     */
    private String Type1;

    /**
     * Pokemon secondary type
     */
    private String Type2;

    /**
     * Pokemon base level
     */
    private int BaseLevel;

    /**
     * Pokemon evolution from
     */
    private int From;

    /**
     * Pokemon evolution to
     */
    private int To;

    /**
     * Pokemon evolution level
     */
    private int EvoLevel;

    /**
     * Pokemon HP stat
     */
    private int HP;

    /**
     * Pokemon attack stat
     */
    private int Atk;

    /**
     * Pokemon defense stat
     */
    private int Def;

    /**
     * Pokemon Speed stat
     */
    private int Spd;

    /**
     * Array to hold the Pokémon's moves
     */
    private Moves[] moves = new Moves[4];

    /**
     * Pokemon's held item
     */
    private Items heldItem;

    /**
     * Pokemon's item count
     */
    private int pItems = 0;

    /**
     * Pokemon's move count
     */
    private int pMoves = 0;

   /**
    * Default constructor for the Pokémon class.
    * Initializes the Pokémon with default values.
    */
    public Pokemon() {
        this.PokedexNo = 0;
        this.Name = "";
        this.Type1 = "";
        this.Type2 = "0"; 
        this.BaseLevel = 1; 
        this.From = 0;
        this.To = 0;
        this.EvoLevel = 0;
        this.HP = 0;
        this.Atk = 0;
        this.Def = 0;
        this.Spd = 0;
    }

    /**
     Constructs a new Pokemon object with the specified attributes.
     @param PokedexNo  the unique Pokédex number of the Pokémon
     @param Name       the name of the Pokémon
     @param Type1      the primary type of the Pokémon
     @param Type2      the secondary type of the Pokémon (or "0" if none)
     @param BaseLevel  the level at which this Pokémon is normally found
     @param From       the Pokédex number it evolves from (0 if none)
     @param To         the Pokédex number it evolves to (0 if none)
     @param EvoLevel   the level required to evolve (if applicable)
     @param HP         the hit points (health) of the Pokémon
     @param Atk        the attack stat of the Pokémon
     @param Def        the defense stat of the Pokémon
     @param Spd        the speed stat of the Pokémon
     */
    public Pokemon (int PokedexNo, String Name, String Type1,String Type2,int BaseLevel,int From, int To, int EvoLevel,int HP, int Atk,int Def, int Spd)
    {
        this.PokedexNo = PokedexNo;
        this.Name = Name;
        this.Type1 = Type1;
        this.Type2 = Type2;
        this.BaseLevel = BaseLevel;
        this.From = From;
        this.To = To;
        this.EvoLevel = EvoLevel;
        this.HP = HP;
        this.Atk = Atk;
        this.Def = Def;
        this.Spd = Spd;
    }

    /**
     * Array of all possible Pokémon types.
     */
    public  static final String[] TYPES = { "Normal", "Fire", "Water", "Electric", "Grass", "Ice", "Fighting", "Poison", 
    "Ground", "Flying", "Psychic", "Bug", "Rock", "Ghost", "Dragon", "Dark", "Steel", "Fairy" };

    /**
     * Returns the Pokédex number of the Pokémon.
     * @return the Pokédex number as an int
     */
    public int getPokedexNo()
    {
        return PokedexNo;
    }

    /**
     Returns the name of the Pokemon.
     @return the Pokemon's name as a String
     */
    public String getName()
    {
        return Name;
    }

    /**
     Returns the Pokemon's primary type.
     @return the primary type of the Pokemon as a String
     */
    public String getType1()
    {
        return Type1;
    }

    /**
     Returns the Pokemon's secondary type.
     @return the secondary type of the Pokemon as a String, or "0" if none
     */
    public String getType2()
    {
        return Type2;
    }

    /**
     Returns the base level of the Pokemon.
     @return the base level of the Pokemon as an int
     */
    public int getBaseLevel()
    {
        return BaseLevel;
    }

    /**
     Returns the Pokédex number that this Pokémon evolves from.
     @return the Pokédex number it evolves from, or 0 if it does not evolve from another Pokémon
     */
    public int getFrom()
    {
        return From;
    }

    /**
     Returns the Pokédex number that this Pokémon evolves to.
     @return the Pokédex number it evolves to or 0 if it does not evolve into another Pokémon
     */
    public int getTo()
    {
        return To;
    }

    /**
     Returns the level at which this Pokémon evolves.
     @return the level required for evolution
     */
    public int getEvoLevel()
    {
        return EvoLevel;
    }

    /**
     Returns the Pokémon's (HP).
     @return the hit points (health) of the Pokémon as an int
     */
    public int getHP()
    {
        return HP;
    }

    /**
     Returns the Pokémon's Attack.
     @return the attack stat of the Pokémon as an int
     */
    public int getAtk()
    {
        return Atk;
    }

    /**
     Returns the Pokemon's defend stat.
     @return the defend stat of the Pokémon as an int
     */
    public int getDef()
    {
        return Def;
    }

    /**
     Returns the Pokémon's Speed.
     @return the speed stat of the Pokémon as an int
     */
    public int getSpd()
    {
        return Spd;
    }

    /**
     Returns the item that the Pokémon is currently holding.
     @return the held item as an Items object, or null if not holding any item
     */
    public Items getHeldItem()
    {
        return heldItem;
    }

    /**
     * Returns the number of moves the Pokémon currently knows.
     * @return the number of moves as an int
     */
    public int getPMoves() {
        return pMoves;
    }

    /**
     * Returns the array of moves that the Pokémon currently knows.
     * @return an array of Moves objects representing the Pokémon's moves
     */
    public Moves[] getMoves() {
        return moves;
    }

    /**
     * Sets the Pokédex number of the Pokémon.
     * @param pokedexNo pokedex number of the pokemon
     */
    public void setPokedexNo(int pokedexNo)
    {
        this.PokedexNo = pokedexNo;
    }

    /**
     * Sets the name of the Pokémon.
     * @param name pokemon name
     */
    public void setName(String name)
    {
        this.Name = name;
    }

    /**
     * Sets the primary type of the Pokémon.
     * @param type1 primary type of a pokemon
     */
    public void setType1(String type1)
    {
        this.Type1 = type1;
    }

    /**
     * Sets the secondary type of the Pokémon.
     * @param type2 secondary type of pokemon
     */
    public void setType2(String type2)
    {
        this.Type2 = type2;
    }

    /**
     * Sets the base level of the Pokémon.
     * @param from evolution from of a pokemon 
     */
    public void setFrom(int from)
    {
        this.From = from;
    }

    /**
     * Sets the Pokédex number that this Pokémon evolves to.
     * @param to evolution to of a pokemon
     */
    public void setTo(int to)
    {
        this.To = to;
    }

    /**
     * Sets the level at which this Pokémon evolves.
     * @param evoLevel evolution level of a pokemon
     */
    public void setEvoLevel(int evoLevel)
    {
        this.EvoLevel = evoLevel;
    }

    /**
     * Sets the hit points (HP) of the Pokémon.
     * @param hp hp stats of a pokemon
     */
    public void setHP(int hp)
    {
        this.HP = hp;
    }

    /**
     * Sets the attack stat of the Pokémon.
     * @param atk attack stat of a pokemon
     */
    public void setAtk(int atk)
    {
        this.Atk = atk;
    }

    /**
     * Sets the defense stat of the Pokémon.
     * @param def defense stat of a pokemon
     */
    public void setDef(int def)
    {
        this.Def = def;
    }

    /**
     * Sets the speed stat of the Pokémon.
     * @param spd speed stat of a pokemon
     */
    public void setSpd(int spd)
    {
        this.Spd = spd;
    }

    /**
     * Stores the move sets of the Pokémon.
     * @param moves move set of a pokemon
     */
    public void setMoves(Moves[] moves) {
        this.moves = moves;
    }

    /**
     * Sets the moves that the pokemon wants to learn
     * @param pMoves pokemon moves
     */
    public void setPMoves(int pMoves) {
        this.pMoves = pMoves;
    }

   /**
    * Teaches a move to the Pokémon.
    * @param moveName move name to be taught
    * @param overwrite {@code true} to allow replacing an existing move if the Pokémon already knows 4 moves;
    *                  {@code false} to prevent overwriting
    * @return {@code true} if the move was successfully taught; {@code false} otherwise
    */
    public boolean teachMove(String moveName, boolean overwrite) {
        Moves move = Moves.getMoveByName(moveName);
        if (move == null) return false;

        // Check if already knows the move
        for (int i = 0; i < pMoves; i++) {
            if (moves[i].getName().equalsIgnoreCase(moveName)) {
                return false;
            }
        }

        // If has empty move slots
        if (pMoves < moves.length) {
            moves[pMoves++] = move;
            return true;
        }

        // If no slots and overwrite enabled
        if (overwrite && pMoves > 0) {
            moves[pMoves-1] = move; // Replace last move
            return true;
        }
        return false;
    }

    /**
     * Unlearns a move from the Pokémon
     * @param moveName Name of move to remove
     * @return true if move was successfully removed
     */
    public boolean unlearnMove(String moveName) {
        // Must keep at least 1 move
        if (pMoves <= 1) return false; 
        for (int i = 0; i < pMoves; i++) {
            if (moves[i].getName().equalsIgnoreCase(moveName)) {
                System.arraycopy(moves, i+1, moves, i, pMoves-i-1);
                moves[--pMoves] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the names of the moves that the Pokémon currently knows.
     * @return an array of move names as Strings
     */
    public String[] getKnownMoves() {
        String[] knownMoves = new String[pMoves];
        for (int i = 0; i < pMoves; i++) {
            knownMoves[i] = moves[i].getName();
        }
        return knownMoves;
    }

     /**
      * Displays the details of the Pokémon in a formatted manner.
      * It includes the Pokédex number, name, type(s), base level, stats (HP, Attack, Defense, Speed),
      * evolution details, held item, and moves known by the Pokémon.
      */
    public void displaypokemon()
    {
        System.out.println("\n ================================================================================================");
        System.out.printf("\n\tPokemon #%04d: %s\n", getPokedexNo(), getName());

        switch (Type1.toLowerCase())
        {
            case "normal":
                System.out.println("\t(ᵔᴥᵔ)");
                System.out.println("\t( •.•)");
                System.out.println("\t(\")_(\")");
                break;

            case "fire":
                System.out.println("\t(´⊙ω⊙`)");
                System.out.println("\t) ^.^ (");
                System.out.println("\t/(___)\\");
                break;

            case "water":
                System.out.println("\t(◕‿◕)");
                System.out.println("\t~('.')~");
                System.out.println("\t (\"~~\")");
                break;

            case "grass":
                System.out.println("\t(✿◠‿◠)");
                System.out.println("\t~`'´~");
                System.out.println("\t (\"--\")");
                break;

            case "electric":
                System.out.println("\t(◕‿◕)✧");
                System.out.println("\t(•̀ᴗ•́)");
                System.out.println("\t /╲/\\╱");
                break;

            case "ice":
                System.out.println("\t(◕ᴗ◕)");
                System.out.println("\t(⋆.⋆)");
                System.out.println("\t /   \\");
                break;

            case "fighting":
                System.out.println("\t(ง •̀_•́)ง");
                System.out.println("\t(ó‿ò✿)");
                System.out.println("\t/(___)\\");
                break;

            case "poison":
                System.out.println("\t(◕‿◕)☠");
                System.out.println("\t(•́︿•̀)");
                System.out.println("\t╰[___]╯");
                break;

            case "ground":
                System.out.println("\t(◕ᴥ◕ʋ)");
                System.out.println("\t(•ᴗ•๑)");
                System.out.println("\t[_____]");
                break;

            case "flying":
                System.out.println("\t(◕‿◕✿)");
                System.out.println("\t~('.')~");
                System.out.println("\t  \\\\_/");
                break;

            case "psychic":
                System.out.println("\t(◕‿◕)☆");
                System.out.println("\t(◑‿◐)");
                System.out.println("\t /___\\");
                break;

            case "bug":
                System.out.println("\t(✿◠‿◠)");
                System.out.println("\t╭(•́⍛•̀)╮");
                System.out.println("\t (\\\"~~\\\")");
                break;

            case "rock":
                System.out.println("\t(◕‿◕)ᓄ");
                System.out.println("\t(•̀ᴗ•́)✧");
                System.out.println("\t[_____]");
                break;

            case "ghost":
                System.out.println("\t(◕‿◕)☁");
                System.out.println("\t(×‿×)");
                System.out.println("\t(\"~~~\")");
                break;

            case "dragon":
                System.out.println("\t(◕‿◕)☄");
                System.out.println("\t(•̀ᴗ•́)✧");
                System.out.println("\t<___>");
                break;

            case "dark":
                System.out.println("\t(｀へ´)");
                System.out.println("\t(•̀_•́)");
                System.out.println("\t[_____]");
                break;

            case "steel":
                System.out.println("\t(◕‿◕)✧");
                System.out.println("\t(•̀ᴗ•́)≡");
                System.out.println("\t[_____]");
                break;

            case "fairy":
                System.out.println("\t(◕‿◕✿)");
                System.out.println("\t(✿◠‿◠)");
                System.out.println("\t(\"~~\")");
                break;
        }

        cry();

        System.out.printf("\n\t%s: %s", "Type", Type1);
        if (!Type2.contains("0"))
        {
            System.out.printf(" / %s", Type2);
        }
        System.out.printf("\n\tBase Level: " + getBaseLevel());
        System.out.printf("\n\tHP:%6s", "");
        for (int j = 0; j < getHP() / 2; j++)
        {
            System.out.printf("█");
        }
        System.out.println(" " + getHP());
        System.out.printf("\tAttack:%2s", "");
        for (int j = 0; j < getAtk() / 2; j++)
        {
            System.out.printf("█");
        }
        System.out.println(" " + getAtk());
        System.out.printf("\tDefense:%1s", "");
        for (int j = 0; j < getDef() / 2; j++)
        {
            System.out.printf("█");
        }
        System.out.println(" " + getDef());
        System.out.printf("\tSpeed:%3s", "");
        for (int j = 0; j < getSpd() / 2; j++)
        {
            System.out.printf("█");
        }
        System.out.println(" " + getSpd());
        if (getFrom() != 0 && getFrom() <= Pokedex.pokemonCount)
        {
            System.out.println("\tEvolves From " + Pokedex.pokemon[getFrom() - 1].getName() + " At Level " + Pokedex.pokemon[getFrom() - 1].getEvoLevel());
        }
        else if (getFrom() != 0)
        {
            System.out.println("\tEvolves From Unknown At Unknown Level");
        }
        if (getTo() != 0 && getTo() <= Pokedex.pokemonCount)
        {
            System.out.println("\tEvolves To " + Pokedex.pokemon[getTo() - 1].getName() + " At Level " + getEvoLevel());
        }
        else if (getTo() != 0)
        {
            System.out.println("\tEvolves To Unknown At Level " + getEvoLevel());
        }
        if (heldItem == null)
        {
            System.out.println("\n\tHeld Item: None");
        }
        else
        {
            System.out.println("\tHeld Item: " + heldItem.getitemName());
        }
        System.out.println("\n\t" + Name + "'s Moves:");
        if (pMoves == 0)
        {
            System.out.println("  (No moves known)");
            return;
        }
        for (int i = 0; i < pMoves; i++)
        {
            Moves m = moves[i];
            System.out.println("\t  - " + m.getName() + ": " + m.getDesc() +
                    " [" + m.getType1() + (m.getType2().contains("0") ? "" : "/" + m.getType2()) +
                    "] " + (m.getMachine().isEmpty() ? "" : "(Machine: " + m.getMachine() + ")"));
        }
        System.out.println("\n ================================================================================================");
    }

    /**
     * Gives a held item to the Pokémon. If already holding one, it is replaced.
     * @param itemName the name of the item to give
     * @return message indicating the result
     */

    public String giveHeldItem(String itemName) {
        Items item = Items.getItemByName(itemName);
        if (item == null) {
            return itemName + " does not exist.";
        }
        String message;

        if (heldItem != null) {
            message = Name + " replaced " + heldItem.getitemName() + " with " + item.getitemName() + ".";
        } else {
            message = Name + " is now holding " + item.getitemName() + ".";
        }
        heldItem = item;
        return message;
    }

    /**
     * Removes a held item to the Pokémon
     */
    public void removeHeldItem()
    {
        if (heldItem == null)
        {
            System.out.println(Name + " isn't holding any item.");
            return;
        }
        System.out.println(Name + " dropped " + heldItem.getitemName());
        heldItem = null;
    }

    /**
     * Uses a held Item
     */
    public void useHeldItem()
    {
        if (heldItem == null)
        {
            System.out.println(Name + " isn't holding any item to use.");
            return;
        }
        System.out.println(Name + " used " + heldItem.getitemName() + "!");
        System.out.println("Effect: " + heldItem.getitemEffects());
        if (!heldItem.getitemCategory().equalsIgnoreCase("held")) {
            heldItem = null;
        }
    }

    /**
     * This method return the Pokemon's cry
     * @return Pokemon's Cry
     */
    public String cry() {
        String crySoundName = this.Name.replace(" ", "");
        return this.Name + " cries " + crySoundName.toUpperCase() + "!!!";
    }

    /**
     * This method increments Pokemon's base stats and level
     */
    public void levelUp() {
        BaseLevel++;
        HP += 2;
        Atk += 1;
        Def += 1;
        Spd += 1;

        System.out.println(Name + " grew to level " + BaseLevel + "!");
        System.out.println(cry());

        if (canEvolve()) {
            System.out.println(Name + " is trying to evolve!");
            evolve();
        }
    }

    /**
     * Checks if the Pokémon meets the conditions to evolve.
     * @return true if evolution conditions are met
     */
    public boolean canEvolve() {
        // Check level-based evolution
        if (To != 0 && BaseLevel >= EvoLevel && EvoLevel != 0) {
            return true;
        }

        // Check stone-based evolution (if holding an evolution stone)
        if (heldItem != null && heldItem.getForEvo()) {
            EvolutionStone stone = (EvolutionStone) heldItem;
            return stone.canEvolve(this);
        }

        // Could add other evolution conditions here (friendship, trade, etc.)
        return false;
    }

    /**
     * Evolves the Pokémon into its next form if conditions are met.
     * @return true if evolution was successful
     */
    public boolean evolve() {
        // Level-based evolution
        if (To != 0 && BaseLevel >= EvoLevel) {
            Pokemon evolution = Pokedex.pokemon[To - 1]; // Assuming Pokédex numbers are 1-based
            evolveInto(evolution);
            return true;
        }

        // Stone-based evolution
        if (heldItem != null && heldItem.getForEvo()) {
            EvolutionStone stone = (EvolutionStone) heldItem;
            if (stone.canEvolve(this)) {
                Pokemon evolution = findStoneEvolution();
                if (evolution != null) {
                    stone.useForEvolution(this);
                    evolveInto(evolution);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Helper method to handle the actual evolution transformation.
     * @param evolution The Pokémon to evolve into
     */
    private void evolveInto(Pokemon evolution) {
        System.out.println("What? " + Name + " is evolving!");

        // Save current moves
        Moves[] currentMoves = moves;
        int currentMoveCount = pMoves;

        // Transform into the evolution
        this.PokedexNo = evolution.PokedexNo;
        this.Name = evolution.Name;
        this.Type1 = evolution.Type1;
        this.Type2 = evolution.Type2;
        this.From = evolution.From;
        this.To = evolution.To;
        this.EvoLevel = evolution.EvoLevel;

        // Stat increases
        this.HP += evolution.HP / 2;
        this.Atk += evolution.Atk / 2;
        this.Def += evolution.Def / 2;
        this.Spd += evolution.Spd / 2;

        // Keep moves (but limit to 4)
        this.moves = new Moves[4];
        this.pMoves = Math.min(currentMoveCount, 4);
        System.arraycopy(currentMoves, 0, this.moves, 0, pMoves);
        System.out.println("Congratulations! Your " + Name + " evolved!");
        System.out.println(cry());
    }

    /**
     * Finds the appropriate evolution for stone-based evolutions.
     * @return The Pokémon to evolve into, or null if no evolution found
     */
    private Pokemon findStoneEvolution() {
        if (heldItem == null || !heldItem.getForEvo()) {
            return null;
        }

        if (To != 0) {
            return Pokedex.pokemon[To - 1];
        }

        // Special cases for stone evolutions that might not be in the normal chain
        // (e.g., Eevee's multiple evolutions)
        String stoneName = heldItem.getitemName();
        return null;
    }

    /**
     * Displays evolution information for the Pokémon.
     */
    public void displayEvolutionInfo() {
        if (From != 0) {
            Pokemon preEvolution = Pokedex.pokemon[From - 1];
            System.out.println(Name + " evolves from " + preEvolution.getName() +
                    " at level " + preEvolution.getEvoLevel());
        }

        if (To != 0) {
            Pokemon evolution = Pokedex.pokemon[To - 1];
            System.out.println(Name + " evolves into " + evolution.getName() +
                    " at level " + EvoLevel);
        }

        if (heldItem != null && heldItem.getForEvo()) {
            System.out.println(Name + " can also evolve using a " + heldItem.getitemName());
        }
    }

    /**
     * This method handles compatibility check for which pokemon, a pokemon can evolve with
     * by the use of evolution stone
     * @param stoneName evolution stone name
     * @return false if there is no evolution
     */
    public boolean canEvolveWith(String stoneName) {
        // Check if this Pokémon has any stone-based evolutions
        if (To == 0) return false; // No evolution at all

        // Get the evolution data from Pokedex
        Pokemon evolution = Pokedex.pokemon[To - 1];

        switch (Name.toLowerCase()) {
            case "eevee":
                switch (stoneName.toLowerCase()) {
                    case "water stone": return true;
                    case "fire stone": return true;
                    case "thunder stone": return true;
                    case "leaf stone": return true;
                    case "ice stone": return true;
                    case "sun stone": return true;
                    case "moon stone": return true;
                    case "shiny stone": return true;
                    case "dusk stone": return true;
                    case "dawn stone": return true;
                }
                break;

            case "vulpix":
                return stoneName.equalsIgnoreCase("Fire Stone");

            case "growlithe":
                return stoneName.equalsIgnoreCase("Fire Stone");

            case "poliwhirl":
                return stoneName.equalsIgnoreCase("Water Stone");

            case "shellder":
                return stoneName.equalsIgnoreCase("Water Stone");

            case "pikachu":
                return stoneName.equalsIgnoreCase("Thunder Stone");

            case "gloom":
                return stoneName.equalsIgnoreCase("Leaf Stone") ||
                        stoneName.equalsIgnoreCase("Sun Stone");

            case "weepinbell":
                return stoneName.equalsIgnoreCase("Leaf Stone");

            case "exeggcute":
                return stoneName.equalsIgnoreCase("Leaf Stone");

            case "nidorina":
            case "nidorino":
                return stoneName.equalsIgnoreCase("Moon Stone");

            case "clefairy":
            case "jigglypuff":
                return stoneName.equalsIgnoreCase("Moon Stone");

            case "sunkern":
            case "cottonee":
                return stoneName.equalsIgnoreCase("Sun Stone");

            case "togetic":
            case "roselia":
            case "minccino":
                return stoneName.equalsIgnoreCase("Shiny Stone");

            case "murkrow":
            case "misdreavus":
            case "doublade":
                return stoneName.equalsIgnoreCase("Dusk Stone");

            case "kirlia": 
                return stoneName.equalsIgnoreCase("Dawn Stone");

            case "snorunt":
                return stoneName.equalsIgnoreCase("Dawn Stone");

            case "alolan vulpix":
            case "galarian darumaka":
                return stoneName.equalsIgnoreCase("Ice Stone");
        }
        return false;
    }

    /**
     * Evolves the Pokémon using a specific stone.
     * @param stoneName The name of the evolution stone to use
     * @return The evolved Pokémon, or null if evolution failed
     */
    public Pokemon evolveWithStone(String stoneName) {
        if (!canEvolveWith(stoneName)) {
            return null;
        }
        // Find the appropriate evolution
        Pokemon evolution = null;

        switch (Name.toLowerCase()) {
            case "eevee":
                switch (stoneName.toLowerCase()) {
                    case "water stone":
                        evolution = Pokedex.getPokemonByName("Vaporeon");
                        break;
                    case "fire stone":
                        evolution = Pokedex.getPokemonByName("Flareon");
                        break;
                    case "thunder stone":
                        evolution = Pokedex.getPokemonByName("Jolteon");
                        break;
                    case "leaf stone":
                        evolution = Pokedex.getPokemonByName("Leafeon");
                        break;
                    case "ice stone":
                        evolution = Pokedex.getPokemonByName("Glaceon");
                        break;
                    case "sun stone":
                        evolution = Pokedex.getPokemonByName("Espeon");
                        break;
                    case "moon stone":
                        evolution = Pokedex.getPokemonByName("Umbreon");
                        break;
                }
                break;

            case "vulpix":
                evolution = Pokedex.getPokemonByName("Ninetales");
                break;

            case "growlithe":
                evolution = Pokedex.getPokemonByName("Arcanine");
                break;

            case "poliwhirl":
                evolution = Pokedex.getPokemonByName("Poliwrath");
                break;

            case "shellder":
                evolution = Pokedex.getPokemonByName("Cloyster");
                break;

            case "pikachu":
                evolution = Pokedex.getPokemonByName("Raichu");
                break;

            case "gloom":
                if (stoneName.equalsIgnoreCase("Leaf Stone")) {
                    evolution = Pokedex.getPokemonByName("Vileplume");
                } else {
                    evolution = Pokedex.getPokemonByName("Bellossom");
                }
                break;

            case "weepinbell":
                evolution = Pokedex.getPokemonByName("Victreebel");
                break;

            case "exeggcute":
                evolution = Pokedex.getPokemonByName("Exeggutor");
                break;

            case "nidorina":
                evolution = Pokedex.getPokemonByName("Nidoqueen");
                break;

            case "nidorino":
                evolution = Pokedex.getPokemonByName("Nidoking");
                break;

            case "clefairy":
                evolution = Pokedex.getPokemonByName("Clefable");
                break;

            case "jigglypuff":
                evolution = Pokedex.getPokemonByName("Wigglytuff");
                break;
        }

        if (evolution != null) {
            // Create a copy of the evolution with our current level and stats
            Pokemon evolved = new Pokemon(
                    evolution.getPokedexNo(),
                    evolution.getName(),
                    evolution.getType1(),
                    evolution.getType2(),
                    this.BaseLevel, 
                    this.PokedexNo, 
                    evolution.getTo(),
                    evolution.getEvoLevel(),
                    this.HP + (evolution.getHP() / 2), 
                    this.Atk + (evolution.getAtk() / 2),
                    this.Def + (evolution.getDef() / 2),
                    this.Spd + (evolution.getSpd() / 2)
            );

            System.arraycopy(this.moves, 0, evolved.moves, 0, this.pMoves);
            evolved.pMoves = this.pMoves;
            return evolved;
        }
        return null;
    }

    /**
     * This method gets the pokemon name of a pokemon it evolves into
     * @return Evolved Pokemon name
     */
    public String getEvolutionName() {
        // Check for level-based evolution first
        if (To != 0 && Pokedex.pokemon != null && To <= Pokedex.pokemonCount) {
            return Pokedex.pokemon[To - 1].getName();
        }

        // Check for stone-based evolutions if holding an evolution stone
        if (heldItem != null && heldItem.getForEvo()) {
            String stoneName = heldItem.getitemName();
            switch (Name.toLowerCase()) {
                case "eevee":
                    switch (stoneName.toLowerCase()) {
                        case "water stone": return "Vaporeon";
                        case "fire stone": return "Flareon";
                        case "thunder stone": return "Jolteon";
                        case "leaf stone": return "Leafeon";
                        case "ice stone": return "Glaceon";
                    }
                    break;
                case "vulpix": return "Ninetales";
                case "growlithe": return "Arcanine";
                case "poliwhirl": return "Poliwrath";
                case "shellder": return "Cloyster";
                case "pikachu": return "Raichu";
                case "gloom":
                    return stoneName.equalsIgnoreCase("Leaf Stone") ? "Vileplume" : "Bellossom";
                case "weepinbell": return "Victreebel";
                case "exeggcute": return "Exeggutor";
                case "nidorina": return "Nidoqueen";
                case "nidorino": return "Nidoking";
                case "clefairy": return "Clefable";
                case "jigglypuff": return "Wigglytuff";
            }
        }
        return "None"; 
    }

    /**
     * Reconstructs a Pokemon object from a line of saved data.
     * @param line the {@code String} containing serialized Pokémon data
     * @return the reconstructed Pokemon object, or null if parsing fails
     */
    public static Pokemon fromFileString(String line) {
        try {
            String[] parts = line.split(",");
            if (parts.length < 8) {
                throw new IllegalArgumentException("Invalid Pokémon data string");
            }

            // Parse basic info
            String name = parts[0];
            int baseLevel = Integer.parseInt(parts[1]);
            String type1 = parts[2];
            String type2 = parts[3].equals("0") ? "0" : parts[3]; // Handle null/empty type2
            int hp = Integer.parseInt(parts[4]);
            int atk = Integer.parseInt(parts[5]);
            int def = Integer.parseInt(parts[6]);
            int spd = Integer.parseInt(parts[7]);

            // Find original Pokémon from Pokedex to get evolution info
            Pokemon original = Pokedex.getPokemonByName(name);
            if (original == null) {
                throw new IllegalArgumentException("Pokémon not found in Pokedex: " + name);
            }

            // Create new Pokémon instance
            Pokemon pokemon = new Pokemon(
                    original.getPokedexNo(),
                    name,
                    type1,
                    type2,
                    baseLevel,
                    original.getFrom(),
                    original.getTo(),
                    original.getEvoLevel(),
                    hp,
                    atk,
                    def,
                    spd
            );

            // Load moves if they exist in the data (parts[8] contains moves)
            if (parts.length > 8 && !parts[8].isEmpty()) {
                String[] moveNames = parts[8].split(";");
                for (String moveName : moveNames) {
                    pokemon.teachMove(moveName, false);
                }
            }
            return pokemon;
        } catch (Exception e) {
            System.err.println("Error parsing Pokémon data: " + e.getMessage());
            return null;
        }
    }

    /**
     * Converts this Pokemon object into a comma-separated string
     * for saving to a file.
     * @return the formatted string representation of the Pokémon's data
     */
    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Name).append(",");
        sb.append(BaseLevel).append(",");
        sb.append(Type1).append(",");
        sb.append(Type2 == null ? "0" : Type2).append(",");
        sb.append(HP).append(",");
        sb.append(Atk).append(",");
        sb.append(Def).append(",");
        sb.append(Spd).append(",");

        if (pMoves > 0) {
            for (int i = 0; i < pMoves; i++) {
                if (moves[i] != null) {
                    sb.append(moves[i].getName());
                    if (i < pMoves - 1) sb.append(";");
                }
            }
        }
        return sb.toString();
    }

    /**
     * Converts this Pokémon object into a CSV string representation.
     * This includes all basic stats and the names of its moves.
     * @return the CSV string representation of the Pokémon
     */
    public String toCsvString() {
        String movesString = "";
            if (pMoves > 0) {
                movesString = String.join(",",
                        Arrays.stream(moves, 0, pMoves)
                                .filter(Objects::nonNull)
                                .map(Moves::getName)
                                .toArray(String[]::new)
                );
            }

        return String.join(",",
                String.valueOf(this.PokedexNo),
                this.Name,
                this.Type1,
                this.Type2,
                String.valueOf(this.BaseLevel),
                String.valueOf(this.From),
                String.valueOf(this.To),
                String.valueOf(this.EvoLevel),
                String.valueOf(this.HP),
                String.valueOf(this.Atk),
                String.valueOf(this.Def),
                String.valueOf(this.Spd),
                movesString // Always include moves field (empty if no moves)
        );
    }

    /**
     * Creates a Pokémon object from a CSV string.
     * The CSV string should contain the following fields in order:
     * @param csv the CSV-formatted {@code String} containing Pokémon data
     * @return a new Pokémon object with the parsed data
     * @throws IllegalArgumentException if the CSV format is invalid
     */
    public static Pokemon fromCsvString(String csv) {
        String[] parts = csv.split(",");
        if (parts.length < 12) {
            throw new IllegalArgumentException("Invalid CSV format. Expected 12 fields, got: " + parts.length);
        }

        // Create the Pokemon with basic stats
        Pokemon pokemon = new Pokemon(
                Integer.parseInt(parts[0]),  // pokedexNo
                parts[1],                   // name
                parts[2],                   // type1
                parts[3],                   // type2
                Integer.parseInt(parts[4]), // baseLevel
                Integer.parseInt(parts[5]), // from
                Integer.parseInt(parts[6]), // to
                Integer.parseInt(parts[7]), // evoLevel
                Integer.parseInt(parts[8]), // HP
                Integer.parseInt(parts[9]), // Atk
                Integer.parseInt(parts[10]), // Def
                Integer.parseInt(parts[11])  // Spd
        );

        // Parse and add moves if they exist
        if (parts.length > 12 && !parts[12].isEmpty()) {
            String[] moveNames = parts[12].split(",");
            for (String moveName : moveNames) {
                pokemon.teachMove(moveName.trim(), false);
            }
        }
        return pokemon;
    }
}