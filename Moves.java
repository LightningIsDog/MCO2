import java.io.PrintStream;

/** This class represents a Move in a Pokémon game.
 *  It contains properties such as name, description, machine classification and type
 *  @author Justin Miguel Agustin L. Lotilla
 *  @author Maurienne Marie M. Mojica
 *  @version 5.0
 */

public class Moves {

    /**
     * Move attributes an count
     */
    private String Name;
    private String Desc;
    private String Machine;
    private String Type1;
    private String Type2;

    /**
     * tracks move count
     */
    public static int moveCount = 0;

    /**
     * Static array to hold all moves
     */
    public static Moves[] moveList = new Moves[100];

    /**
     * Constructor for the Moves class.
     * Initializes a move with its name, description, machine classification, and types.
     * @param Name move name
     * @param Desc move description
     * @param Machine move machine classification
     * @param Type1 move type 1
     * @param Type2 move type 2
     */
    public Moves(String Name, String Desc, String Machine, String Type1, String Type2) {
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
    public String getName() {
        return this.Name;
    }

    /**
     * Returns the description of the move.
     * @return the description of the move
     */
    public String getDesc() {
        return this.Desc;
    }

    /**
     * Returns the machine classification of the move.
     * @return the machine classification of the move
     */
    public String getMachine() {
        return this.Machine;
    }

    /**
     * Returns the type of the move.
     * The first type is always present, while the second type may be "0" if
     * @return the type of the move
     */
    public String getType1() {
        return this.Type1;
    }

    /**
     * Returns the second type of the move.
     * If the second type is "0", it indicates that the move does not have a second type.
     * @return the second type of the move, or "0" if it does not exist
     */
    public String getType2() {
        return this.Type2;
    }

    /**
     * Displays the moves from database
     * @param tab The {@code tab} parameter can be used to control display options or UI behavior.
     */
    public void displaymove(boolean tab) {
        System.out.println("\n ==================================================================\n");
        if (tab)
        {
            System.out.println("\tMove: " + getName().trim());
        }
        else {
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
     * If any of the parameters are null, it will not add the move and print an error message.
     * @param name the name of the move
     * @param desc the description of the move
     * @param machine the machine classification of the move
     * @param type1 the first type of the move
     * @param type2 the second type of the move
     */
    public static void addMovetoDataBase(String name, String desc, String machine, String type1, String type2) {
        {
            if(name==null||desc==null||machine==null||type1==null||type2==null) {
                System.out.println("null move cannot be added");
                return;
            }
            moveList[moveCount] = new Moves(name, desc, machine, type1, type2);
            moveCount++;
        }
    }

    /**
     * Retrieves a move by its name from the move database.
     * It searches through the moveList array and returns the first move that matches the given name
     * @param name move name
     * @return the Moves object if found, or null if not found
     */
    public static Moves getMoveByName(String name){
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