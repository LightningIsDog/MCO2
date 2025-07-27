import java.util.*;
/** This class represents a Pokémon in the game, encapsulating its properties and behaviors.It includes attributes such as Pokédex number, name, types, base level, 
    evolution details, stats (HP, Attack, Defense, Speed), moves set, and held items.    
    The class provides methods to display Pokémon details and etc.
 *  @author Justin Miguel Agustin L. Lotilla
 *  @author Maurienne Marie M. Mojica
 *  @version 5.0
 */

public class Pokemon 
{
    private int PokedexNo;
    private String Name;
    private String Type1;
    private String Type2;
    private int BaseLevel;
    private int From;
    private int To;
    private int EvoLevel;
    private int HP;
    private int Atk;
    private int Def;
    private int Spd;
    private Moves[] moves = new Moves[4];
    private Items heldItem;
    private int pItems = 0;
    private int pMoves = 0;
    
        // ✅ Default (no-arg) constructor
    public Pokemon() {
        this.PokedexNo = 0;
        this.Name = "";
        this.Type1 = "";
        this.Type2 = "0"; // Use "0" to indicate no second type
        this.BaseLevel = 1; // Default starting level
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
     * Array to store the types of Pokémon.
     */
    public  static final String[] TYPES = { "Normal", "Fire", "Water", "Electric", "Grass", "Ice", "Fighting", "Poison", "Ground", "Flying", "Psychic", "Bug", "Rock", "Ghost", "Dragon", "Dark", "Steel", "Fairy" };

    /**
        Returns the Dex No. of the Pokemon.
        @return the item ID as an int
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

      public int getPMoves() {
        return pMoves;
    }

    public Moves[] getMoves() {
        return moves;
    }

    public void setPokedexNo(int pokedexNo) 
    {
        this.PokedexNo = pokedexNo;
    }

   
    public void setName(String name) 
    {
        this.Name = name;
    }

    public void setType1(String type1) 
    {
        this.Type1 = type1;
    }

    public void setType2(String type2) 
    {
        this.Type2 = type2;
    }

    public void setFrom(int from) 
    {
        this.From = from;
    }

    public void setTo(int to) 
    {
        this.To = to;
    }

    public void setEvoLevel(int evoLevel) 
    {
        this.EvoLevel = evoLevel;
    }
   
    public void setHP(int hp) 
    {
        this.HP = hp;
    }

    public void setAtk(int atk) 
    {                               
        this.Atk = atk;
    }

    public void setDef(int def) 
    {
        this.Def = def;
    }

    public void setSpd(int spd) 
    {                       
        this.Spd = spd;
    }

    public void setMoves(Moves[] moves) {
    this.moves = moves;
}

public void setPMoves(int pMoves) {
    this.pMoves = pMoves;
}

 /**
        Teaches a move to the Pokémon if it doesn't already know it and if it can learn it.
        @param moveName the name of the move to teach
        @param show     whether to display a message when the move is learned
    */
    public void teachMove(String moveName,boolean show)
    {
        if (pMoves >= 4) 
        {
            System.out.println(Name + " already knows 4 moves.");
            return;
        }
        Moves move = Moves.getMoveByName(moveName);
        if (move == null) 
        {
            System.out.println("Move '" + moveName + "' not found in database.");
            return;
        }
        if (!move.getType1().equalsIgnoreCase(getType1())&&!move.getType1().equalsIgnoreCase(getType2())&&!move.getType1().equalsIgnoreCase("Normal")){
            System.out.println(Name + " cannot learn " + move.getName() + " because it is not " + move.getType1() + " Type ");
            return;
        }
        for (int i = 0; i < pMoves; i++) 
        {
            if (moves[i].getName().equalsIgnoreCase(moveName)) 
            {
                System.out.println(Name + " already knows " + moveName);
                return;
            }
        }
        moves[pMoves++] = move;
        if(show == true)
        {
            System.out.println(Name + " learned " + moveName + "!");
        }
    }

     /**
        Forgets a move by its name if the Pokémon knows it and if it is not a Hidden Machine (HM).
        @param moveName the name of the move to forget
        @return true if the move was successfully forgotten, false otherwise
    */
    public boolean forgetMove(String moveName) 
    {
        for (int i = 0; i < pMoves; i++) 
        {
            if (moves[i] != null && moves[i].getName().equalsIgnoreCase(moveName)) 
            {
                if(moves[i].getMachine().equalsIgnoreCase("HM"))
                {
                    System.out.println(moveName + " cannot be forgottem! ");
                    return false;
                }
                for (int j = i; j < pMoves - 1; j++) 
                {
                    moves[j] = moves[j + 1];
                }
                moves[pMoves - 1] = null; // Clear last slot
                pMoves--;
                System.out.println(Name + " forgot " + moveName + "!");
                return true;
            }
        }
        System.out.println(Name + " doesn't know " + moveName + "!");
        return false;
    }

    /**
       Displays the details of the Pokémon in a formatted manner.
       It includes the Pokédex number, name, type(s), base level, stats (HP, Attack, Defense, Speed),
       evolution details, held item, and moves known by the Pokémon. 
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
        Gives a held item to the Pokémon if it is not already holding one.
        @param itemName the name of the item to give
    */
    public void giveHeldItem(String itemName) 
    {
        if (heldItem != null) 
        {
            System.out.println(Name + " is already holding " + heldItem.getitemName());
            return;
        }
        Items item = Items.getItemByName(itemName);
        heldItem = item;
        if (item == null)
        {
            System.out.println(itemName + " does not exist");
            return;
        }
        System.out.println(Name + " is now holding " + item.getitemName());
    }

    /**
        Removes the held item from the Pokémon if it is holding one.
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
        Uses the held item if it is not null and if it is not a held item.
        If the item is a held item, it will not be removed after use.
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
        Makes the Pokémon cry, which is a sound it makes.
        This method prints a message indicating that the Pokémon is crying.
    */
     public String cry() {
        // You might want to remove spaces from the name for the actual "sound" part
        // For example, "Mister Mime" might cry "MISTERMIME!!!"
        String crySoundName = this.Name.replace(" ", "");

        // Construct and return the string
        return this.Name + " cries " + crySoundName.toUpperCase() + "!!!";
    }
}

