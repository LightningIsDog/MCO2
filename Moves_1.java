/** This class represents a move in the game, encapsulating its properties and behaviors.
 * It includes attributes such as move name, description, machine classification (TM/HM), and types (primary and secondary).
 * The class provides methods to display move details, add moves to the database, and retrieve moves by name.
 *  @author Justin Miguel Agustin L. Lotilla
 *  @author Maurienne Marie M. Mojica
 *  @version 5.0
 */
public class Moves 
{
    private String Name;
    private String Desc;
    private String Machine;
    private String Type1;
    private String Type2;
    /**
     * Array to store Moves objects.
     */
    public static int moveCount = 0;
    /**
     * Array to store Moves objects.
     */
    public static Moves[] moveList = new Moves[100];

    /**
     * Constructs a new Moves object with the specified attributes.
     * Initializes move information including name, description, machine classification, and types.
     * Automatically registers the move in the move list.
     * @param Name      the name of the move
     * @param Desc      a description of the move
     * @param Machine   the classification of the move (e.g., TM, HM)
     * @param Type1     the primary type of the move
     * @param Type2     the secondary type of the move (if any)
     */
    public Moves (String Name, String Desc, String Machine, String Type1, String Type2)
    {
        this.Name = Name;
        this.Desc = Desc;
        this.Machine = Machine;
        this.Type1 = Type1;
        this.Type2 = Type2;
    }

    /**
     * Returns the name of the move.
     * @return the name of the move
     */
    public String getName() 
    {
        return Name;
    }

    /**
     * Returns the description of the move.
     * @return the description of the move
     */
    public String getDesc() 
    {
        return Desc;
    }

    /**
     * Returns the classification of the move (e.g., TM, HM).
     * @return the classification of the move
     */
    public String getMachine() 
    {
        return Machine;
    }

    /**
     * Returns the primary type of the move.
     * @return the primary type of the move
     */
    public String getType1() 
    {
        return Type1;
    }

    /**
     * Returns the secondary type of the move (if any).
     * @return the secondary type of the move, or "0" if there is no secondary type
     */
    public String getType2() 
    {
        return Type2;
    }

    /**
     * Displays the move details in a formatted manner.
     * If 'tab' is true, it displays the move name with a tab indentation.
     * Otherwise, it displays the move name without indentation.
     * @param tab boolean indicating whether to use tab indentation for the move name
     */
    public void displaymove(boolean tab)
    {
        System.out.println("\n ==================================================================\n");
        if (tab) 
        {
            System.out.println("\tMove: " + getName().trim());
        } 
        else 
        {
            System.out.println("\tMove: " + getName().trim());
        }
        System.out.println("\t" + getDesc());
        System.out.println("\tClassification: " + getMachine());
        if (!getType2().contains("0")) 
        {
        System.out.println("\tType: " + getType1() + "/" + getType2());
        } 
        else 
        {
            System.out.println("\tType: " + getType1());
        }
        System.out.println("\n ==================================================================");
    }

    /**
     * Adds a new move to the move database.
     * This method creates a new Moves object with the specified attributes and adds it to the moveList.
     * It increments the moveCount to keep track of the number of moves in the database.
     * @param name   the name of the move
     * @param desc   a description of the move
     * @param machine the classification of the move (e.g., TM, HM)
     * @param type1  the primary type of the move
     * @param type2  the secondary type of the move (if any)
     */
    public static void addMovetoDataBase(String name, String desc, String machine, String type1, String type2) 
    {
        if(name==null||desc==null||machine==null||type1==null||type2==null){
            System.out.println("null move cannot be added");
            return;
        }
        moveList[moveCount] = new Moves(name, desc, machine, type1, type2);
        moveCount++;
    }

    /**
     * Retrieves a move by its name from the moveList.
     * This method searches through the moveList for a move with the specified name (case-insensitive).
     * If found, it returns the corresponding Moves object; otherwise, it returns null.
     * @param name the name of the move to search for
     * @return the Moves object if found, or null if not found
     */
    public static Moves getMoveByName(String name) 
    {
        for (Moves move : moveList) 
        {  
            if (move != null && move.getName().equalsIgnoreCase(name.strip())) 
            {
                return move;
            }
        }
        return null;
    }
}
