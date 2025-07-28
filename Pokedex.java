import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/** This class is a collection of all classes (Pokemon, Move, and Item) as well as all of the methods needed to forming a functional Pokedex Menu
 *  @author Justin Miguel Agustin L. Lotilla
 *  @author Maurienne Marie M. Mojica
 *  @version 5.0
 */
public class Pokedex {

     /**
     * Private constructor to prevent instantiation of this utility class.
     */

    private Pokedex() { 
        // Prevent instantiation
    }
    /**
     Array to store Pokémon objects, moves, available items, evolution items, and trainers.
     */
    public static Pokemon[]pokemon = new Pokemon[300]; 
    private static Moves[]moves = new Moves[300];
    private static Items[]availItems = new Items[300];
    private static Items[]evolutionItems = new Items[300];
    private static Trainers[]trainers = new Trainers[300];
    /**
     * Counter for the number of Pokémon added to the Pokedex.
     */
    public static int pokemonCount = 0;
    private static int moveCount = 0;
    private static int availItemsCount = 10;
    private static int evolutionItemsCount = 10;
    private static Scanner in = new Scanner(System.in);

    /**
        This method validates numeric inputs with range (used for main menus and submenus)
        @param prompt: the message displayed to the user
        @param min: the minimum allowed integer (inclusive)
        @param max: the maximum allowed integer (inclusive)
        @param mainMenu: true if the input is part of the main menu
        @param add: true if the input is used for add pokemon/move
        @return: the validated integer input from the user
    */
    private static int getValidatedIntInput(String prompt, int min, int max, boolean mainMenu,boolean add) 
    {
        int value = -1;
        boolean valid = false;

        while (!valid) 
        {
            System.out.print(prompt);
            if (!in.hasNextInt()) 
            {
                if (mainMenu) 
                {
                    System.out.println("                            Invalid Input, Try Again");
                } 
                else if(add)
                {
                        System.out.println("   Invalid Input, Try Again");
                }
                else 
                {
                    System.out.println("      Invalid Input, Try Again");
                }
                in.next(); 
            }

            else 
            {
                value = in.nextInt();
                if (value < min || value > max) 
                {
                    if (mainMenu) 
                    {
                        System.out.println("                            Invalid Input, Try Again");
                    } 
                    else 
                    {
                        System.out.println("      Invalid Input, Try Again");
                    }
                } 
                else 
                {
                    valid = true;
                }
            }
        }
        return value;
    }

    /**
        This method displays the list of types
    */
    public static void displayTypes()
    {
        System.out.println("\n       ╔══════════════════════════════════════╗");
        System.out.printf ("       ║ %-10s ║ %-10s ║ %-10s ║\n", "Bug", "Fighting", "Psychic");
        System.out.printf ("       ║ %-10s ║ %-10s ║ %-10s ║\n", "Fairy", "Grass", "Ice");
        System.out.printf ("       ║ %-10s ║ %-10s ║ %-10s ║\n", "Ghost", "Poison", "Flying");
        System.out.printf ("       ║ %-10s ║ %-10s ║ %-10s ║\n", "Normal", "Dragon", "Rock");
        System.out.printf ("       ║ %-10s ║ %-10s ║ %-10s ║\n", "Steel", "Fire", "Water");
        System.out.printf ("       ║ %-10s ║ %-10s ║ %-10s ║\n", "Dark", "Ground", "Electric");
        System.out.println("       ╚══════════════════════════════════════╝\n");
    }

    /**
        This method prompts the user to input details for a new Pokémon, including Pokedex number,
        name, types, stats, and evolution information. It performs input validation to ensure
        no duplicate entries and valid types are added. Once validated, it stores the Pokémon in
        the array and appends the data to the file "pokedex.txt".
        @return: void
    */
    private static void addPokemon() // orig
    {
        int PokedexNo;
        String Name;
        String Type1;
        String Type2;
        System.out.println("\n\n ====================================================");
        System.out.println(" ------------------- ADD POKEMON --------------------  ");
        System.out.println(" ==================================================== ");
        int exists=0;
        do
        {
            exists = 0;
            PokedexNo = getValidatedIntInput("\n   Enter Pokedex number     ||    ", 1, 2000, false,true);
            in.nextLine();
            for(int i = 0; i < pokemonCount; i++)
            {
                if(PokedexNo == pokemon[i].getPokedexNo())
                {
                    exists = 1;
                }
            }
            if(exists == 1)
            {
                System.out.printf("   Pokedex Number Already Exists\n ");
            }
        }while (exists==1);
    
        do
        {
            exists = 0;
            System.out.print("\n   Enter Pokemon Name       ||    ");
            Name = in.nextLine();
            for(int i = 0; i < pokemonCount; i++)
            {
                if(pokemon[i].getName().toUpperCase().equalsIgnoreCase(Name.toUpperCase()))
                {
                    exists = 1;
                }
            }
            if(exists == 1)
            {
                System.out.printf("   Pokemon Already Exists\n ");
            }
            if (Name.isBlank())
            {
                System.out.println("   Name Cannot be Blank\n");
            }

        }while (exists==1 || Name.isBlank());
        
        displayTypes();
        boolean Validtype = false;

        do{
            System.out.print("   Enter Type 1             ||    ");
            Type1 = in.nextLine();
            for (String type : Pokemon.TYPES) 
            {
                if (type.equalsIgnoreCase(Type1)) 
                {
                    Validtype=true;

                }

            }
            if(Validtype==false)
            {
                System.out.println("   Invalid Type\n");
            }
        } while (Validtype==false);

        do 
        {
            Validtype = false;
            System.out.print("   Enter Type 2 (0 if none) ||    ");
            Type2 = in.nextLine();
            for (String type : Pokemon.TYPES) 
            {
                if (type.equalsIgnoreCase(Type2)||Type2.equalsIgnoreCase("0")) 
                {
                    Validtype=true;

                }

            }
            if(Validtype==false)
            {
                System.out.println("   Invalid Type\n");
            }
            if(Type1.equalsIgnoreCase(Type2))
            {
                System.out.println("   Duplicate Type\n");
                Validtype=false;
            }
        } while (Validtype==false);

        int BaseLevel = getValidatedIntInput("\n   Base Level               ||    ", 1, 1, false,true);
        in.nextLine();

        int From = getValidatedIntInput("\n   Evolves From (0 if none) ||    ", 0, 2000, false,true);
        in.nextLine();

        int To = getValidatedIntInput("\n   Evolves To   (0 if none) ||    ", 0, 2000, false,true);
        in.nextLine();

        int EvoLevel = getValidatedIntInput("\n   Evolution Level          ||    ", 2, 100, false,true);
        in.nextLine();

        int HP = getValidatedIntInput("\n   Enter HP                 ||    ", 1, 2000, false,true);
        in.nextLine();

        int Atk = getValidatedIntInput("\n   Enter Attack             ||    ", 1, 2000, false,true);
        in.nextLine();

        int Def = getValidatedIntInput("\n   Enter Defense            ||    ", 1, 2000, false,true);
        in.nextLine();

        int Spd = getValidatedIntInput("\n   Enter Speed              ||    ", 1, 2000, false,true);
        System.out.println("\n ==================================================== ");
        pokemon[pokemonCount] = new Pokemon (PokedexNo,Name,Type1,Type2,BaseLevel,From,To,EvoLevel,HP,Atk,Def,Spd);
        pokemon[pokemonCount].teachMove("Tackle",false);
        pokemon[pokemonCount].teachMove("Defend",false);
        pokemonCount++;
        System.out.println("\n  Pokemon Added Successfully!");
        String data = ("\n" + PokedexNo + " " +  Name.replace(" ", "_") + " " + Type1 + " " + Type2 + " "+ BaseLevel +" " + From + " " +To + " " + EvoLevel + " " + HP + " " + Atk + " " + Def +  " " + Spd );
            try 
            {
                FileWriter writer = new FileWriter("pokedex.txt",true);
                writer.append(data);
                writer.close();
            }
            catch (IOException e) 
            {
                e.printStackTrace();
            }
    }

    private static void viewPokemon()
    {
        System.out.println("\n ================================================================================================");
        System.out.println("\n --------------------------------------- VIEW ALL POKEMON --------------------------------------- ");
        if(pokemonCount == 0)
        {
            System.out.println(" There are no pokemon");
        }

        else
        {
            for(int i = 0; i < pokemonCount;i++)
                {
                    pokemon[i].displaypokemon();
                }
        }
    }

    /**
        This method allows the user to search for Pokémon in the array using
        one of three criteria: Pokedex Number, Name, or Type. If no Pokémon
        are found or available, it notifies the user. The search is case-insensitive
        and loops until the user chooses to return to the main menu.
        @return: void
    */
    private static void searchPokemon()
    {
        int choice = 0;
        boolean Validtype =false;
        if (pokemonCount == 0) 
        {
            System.out.println("  There are no Pokemon Available");
        } 
        
        else 
        {
            do
            {
                System.out.println("\n     ╔══════════════════════════════════════════════════════╗");
                System.out.println("      ================== ENHANCED POKEDEX =================="); 
                System.out.println("     ╠══════════════════════════════════════════════════════╣");
                System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "");
                System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "  [1] Pokedex Number");
                System.out.printf("     ║ %-15s ║ %-34s ║\n", "  Search", "  [2] Pokemon Name");
                System.out.printf("     ║ %-15s ║ %-34s ║\n", "  Categories", "  [3] Pokemon Type");
                System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "  [4] Pokemon Management Menu");
                System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "");
                System.out.println("     ╚══════════════════════════════════════════════════════╝");
                choice = getValidatedIntInput("\n      Enter: ", 1, 4, false,false);
                
                switch (choice) 
                {
                    case 1:
                        System.out.println("\n ================== SEARCH POKEDEX NUMBER ==================\n");
                        int SearchID = getValidatedIntInput("\n   Enter Pokedex Number : ", 1, 2000, false,true);
                        boolean found = false;

                        for (Pokemon pokemon : pokemon) 
                        {
                            if (pokemon == null) 
                            {
                                continue;
                            }
                            if (pokemon.getPokedexNo() == SearchID) 
                            {
                                pokemon.displaypokemon();
                                found = true;
                            }
                        }

                        if (found == false) 
                        {
                            System.out.println("   Pokemon not found");
                            System.out.println("\n ============================================================");
                        }
                        enterContinue(true);
                        break;

                    case 2:
                        System.out.println("\n =================== SEARCH POKEMON NAME ===================\n");
                        in.nextLine(); // consume leftover newline
                        System.out.print("   Enter Pokemon Name: ");
                        String searchword = in.nextLine().toLowerCase();
                        found = false;

                        for (Pokemon pokemon : pokemon) 
                        {
                            if (pokemon == null) 
                            {
                                continue;
                            }
                            if (pokemon.getName().toLowerCase().contains(searchword))
                            {
                                pokemon.displaypokemon();
                                found = true;
                            }
                        }
                        if (found == false) 
                        {
                            System.out.println("   Pokemon not found");
                            System.out.println("\n ============================================================");
                        }
                        enterContinue(false);
                        break;

                    case 3:
                        System.out.println("\n =================== SEARCH POKEMON TYPE ===================\n");
                        in.nextLine();
                        displayTypes();
                        do 
                        {
                            Validtype=false;
                            System.out.print("   Enter Pokemon Type: ");
                            searchword = in.nextLine();

                            for (String type : Pokemon.TYPES) 
                            {
                                if (type.equalsIgnoreCase(searchword)) 
                                {
                                    Validtype=true;
                                }
                            }
                            if(Validtype==false)
                            {
                                System.out.println("   Invalid Type\n");
                            }
                        }while (Validtype==false);
                        found = false;

                        for (Pokemon pokemon : pokemon) 
                        {
                            if (pokemon == null) 
                            {
                                continue;
                            }
                            if (pokemon.getType1().toLowerCase().contains(searchword)||
                                pokemon.getType2().toLowerCase().contains(searchword))
                            {
                            pokemon.displaypokemon();
                            found = true;
                            }
                        }

                        if (found == false) 
                        {
                            System.out.println("   Pokemon not found");
                            System.out.println("\n ============================================================");
                        }
                        enterContinue(false);
                        break;
                }
            }while(choice != 4);
        }
    }

    /**
        This method allows the user to add a new move by entering its name, description,
        category (TM or HM), and type(s). It performs validation for duplicate names,
        blank inputs, and invalid or duplicate types. The move is then saved in the
        `Moves` database and appended to the "moves.txt" file.
        @return void
    */
    private static void addMove()
    {
        int exists=0;
        boolean isNumber=false;
        boolean isMachine=true;
        boolean Validtype=false;
        String Name;
        String Desc;
        String Machine;
        String Type1;
        String Type2;
        System.out.println("\n\n ====================================================");
        System.out.println(" --------------------- ADD MOVE ---------------------  ");
        System.out.println(" ==================================================== ");
        in.nextLine();

        do 
        {
            exists=0;
            isNumber=false;
            System.out.print("   Enter Move Name                 ||    ");
            Name = in.nextLine();

            for(int i = 0; i < Moves.moveCount; i++)
            {
                if(Moves.moveList[i].getName().toUpperCase().contains(Name.toUpperCase()))
                {
                    exists = 1;
                }
            }

            if(exists == 1 && !Name.isBlank())
            {
                System.out.println("   Move Already Exists\n");
            }

            if (Name == null || Name.isBlank())
            {
                System.out.println("   Name cannot be blank\n");
                isNumber=true;
            }

            for (char c : Name.toCharArray()) 
            {
                if (Character.isDigit(c)) 
                {
                    System.out.println("   Name cannot contain numbers\n");
                    isNumber=true;
                    break;
                }
            }
        }while (exists==1 || isNumber==true);

        do 
        {
            System.out.print("   Enter Move Description          ||    ");
            Desc = in.nextLine();
            if (Desc == null || Desc.isBlank())
            {
                System.out.println("   Description cannot be blank\n");
            }
        } while(Desc.isBlank());

        do 
        {
            isMachine=false;
            System.out.print("   Enter Move Category (TM/HM)     ||    ");
            Machine = in.nextLine();
            if (Machine == null || Machine.isBlank()){
                System.out.println("   This field cannot be blank");
            }
            if (Machine.equalsIgnoreCase("TM")||Machine.equalsIgnoreCase("HM")){
                isMachine=true;
            }
            else
            {
                System.out.println("   Enter TM or HM\n");
            }
        }while(Machine.isBlank()||isMachine==false);

        displayTypes();
        do 
        {
            Validtype=false;
            System.out.print("   Enter Move Type 1               ||    ");
            Type1 = in.nextLine();
            for (String type : Pokemon.TYPES) 
            {
                if (type.equalsIgnoreCase(Type1))
                {
                    Validtype=true;

                }

            }
            if(Validtype==false)
            {
                System.out.println("   Invalid Type\n");
            }
        } while (Validtype==false);
        do 
        {
            Validtype=false;
            System.out.print("   Enter Move Type 2 (0 if none)    ||    ");
            Type2 = in.nextLine();
            for (String type : Pokemon.TYPES) 
            {
                if (type.equalsIgnoreCase(Type2)||Type2.equalsIgnoreCase("0")) 
                {
                    Validtype=true;

                }

            }
            if(Validtype==false)
            {
                System.out.println("   Invalid Type\n");
            }
            if(Type1.equalsIgnoreCase(Type2))
            {
                System.out.println("   Duplicate Type\n");
                Validtype=false;
            }
        } while (Validtype==false);
        System.out.println(" ==================================================== ");
        Moves.addMovetoDataBase(Name, Desc, Machine, Type1, Type2);
        System.out.println("\n  Move Added Successfully!");
        String data = ("\n" + Name + "-" + Desc + "-" + Machine + "-"+ Type1 +"-" + Type2 + "-");

        try 
        {
            FileWriter writer = new FileWriter("moves.txt",true);
            writer.append(data);
            writer.close();
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

     /**
        This method displays all moves currently stored in the move list.
        If no moves are added, it displays a message indicating the list is empty.
        Uses a different format for the first move (e.g., without a top separator).
        @return void
    */
    private static void viewMove()
    {
        System.out.println("\n ==================================================================");
        System.out.println("\n  ------------------------ VIEW ALL MOVES ------------------------ ");
        if(Moves.moveCount == 0)
        {
            System.out.println(" No Moves Added");
        }
        else
        {
            for(int i = 0; i < Moves.moveCount;i++)
                {
                    if(i == 0)
                    {
                        Moves.moveList[i].displaymove(false);
                    }
                    else
                    {
                        Moves.moveList[i].displaymove(true);
                    }
                }
        }
    }

     /**
        This method allows the user to search for moves based on different criteria:
        move name, move type, or move classification (TM/HM). It handles case-insensitive
        matching and continues to prompt the user until they choose to return to the
        Move Management Menu.
        @return void
    */
    private static void searchMove()
    {
        boolean Validtype=false;
        int choice = 0;
        if (Moves.moveCount == 0)
        {
            System.out.println("  There are no Moves Available");
        } 
        else 
        {
            do
            {
                System.out.println("\n     ╔══════════════════════════════════════════════════════╗");
                System.out.println("      ================== ENHANCED POKEDEX =================="); 
                System.out.println("     ╠══════════════════════════════════════════════════════╣");
                System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "");
                System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "  [1] Move Name");
                System.out.printf("     ║ %-15s ║ %-34s ║\n", "  Search", "  [2] Move Type");
                System.out.printf("     ║ %-15s ║ %-34s ║\n", "  Categories", "  [3] Move Classification");
                System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "  [4] Move Management Menu");
                System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "");
                System.out.println("     ╚══════════════════════════════════════════════════════╝");
                choice = getValidatedIntInput("\n      Enter: ", 1, 4, false,false);

                switch (choice) 
                {
                    case 1:
                        System.out.println("\n =================== SEARCH MOVE NAME ===================\n");
                        in.nextLine();
                        System.out.print("   Enter name: ");
                        String searchword = in.nextLine().toLowerCase();
                        boolean found = false;

                        for (Moves moves : Moves.moveList)
                        {
                            if (moves == null) 
                            {
                                continue;
                            }
                            if (moves.getName().toLowerCase().contains(searchword)) 
                            {
                                moves.displaymove(false);
                                found = true;
                            }
                        }
                        if (found == false) 
                        {
                            System.out.println("   Move not found");
                            System.out.println("\n ============================================================");
                        }
                        enterContinue(false);
                        break;

                    case 2:
                        System.out.println("\n =================== SEARCH MOVE TYPE ===================\n");
                        in.nextLine();
                        displayTypes();
                        
                        do 
                        {
                            Validtype=false;
                            System.out.print("   Enter Type: ");
                            searchword = in.nextLine();
                            for (String type : Pokemon.TYPES) 
                            {
                                if (type.equalsIgnoreCase(searchword)) 
                                {
                                    Validtype=true;

                                }
                            }
                            if(Validtype==false)
                            {
                                System.out.println("   Invalid Type\n");
                            }
                        } while (Validtype==false);
                            found = false;

                        for (Moves moves : Moves.moveList)
                        {
                            if (moves == null) 
                            {
                                continue;
                            }
                            if (moves.getType1().toLowerCase().contains(searchword)||
                                    moves.getType2().toLowerCase().contains(searchword))
                            {
                                moves.displaymove(false);
                                found = true;
                            }
                        }
                        if (found == false) 
                        {
                            System.out.println("   Move not found");
                            System.out.println("\n ============================================================");
                        }
                        enterContinue(false);
                        break;

                    case 3:
                        System.out.println("\n =================== SEARCH MOVE CLASSIFICATION ===================\n");
                        in.nextLine();
                        System.out.print("   Enter Classification: ");
                        searchword = in.nextLine().toLowerCase();
                        found = false;

                        for (Moves moves : Moves.moveList)
                        {
                            if (moves == null) 
                            {
                                continue;
                            }
                            if (moves.getMachine().toLowerCase().contains(searchword)) 
                            {
                                found = true;
                                moves.displaymove(false);
                            }
                        }
                        if (found == false) 
                        {
                            System.out.println("   Move not found");
                            System.out.println("\n ============================================================");
                        }
                        enterContinue(false);
                        break;
                }
            }while(choice != 4);
        }
    }

    /**
        This method is used to exit from a selected menu
        @param afterInt: boolean indicating whether to wait for an Enter key press after an integer input
    */
    public static void enterContinue(boolean afterInt)
    {
        System.out.print("  Press Enter to Continue ");
        if (afterInt) 
        {
            in.nextLine(); 
        }
        in.nextLine(); 
    }

    /**
        This method manages a menu for viewing items in the Pokedex.
        Users can choose to view all available items or just evolution stones.
        The method loops until the user selects the option to return to the
        Item Management Menu.
    */
    public static void manageViewItem()
    {
        int choice = 0;
        do
        {
            System.out.println("\n     ╔══════════════════════════════════════════════════════╗");
            System.out.println("      ================= ENHANCED POKEDEX ==================="); 
            System.out.println("     ╠══════════════════════════════════════════════════════╣");
            System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "");
            System.out.printf("     ║ %-15s ║ %-34s ║\n", "  View", "  [1] View Available Items");
            System.out.printf("     ║ %-15s ║ %-34s ║\n", "  Categories", "  [2] View Evolution Stones");
            System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "  [3] Item Management Menu");
            System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "");
            System.out.println("     ╚══════════════════════════════════════════════════════╝");
            choice = getValidatedIntInput("\n      Enter: ", 1, 3, false,false);
            
                switch(choice)
                {
                    case 1: 
                        System.out.println("\n ================================================================================================");
                        System.out.println("\n ----------------------------------- VIEW ALL AVAILABLE ITEMS -----------------------------------");
                        for (int i = 0; i < availItemsCount; i++) 
                        {
                            if (availItems[i] != null) 
                            {
                                //availItems[i].displayItems();

                            }
                        }
                        enterContinue(true);
                        break;

                    case 2: 
                        System.out.println("\n ================================================================================================");
                        System.out.println("\n ----------------------------------- VIEW ALL EVOLUTION STONES ----------------------------------");
                        for (int j = 0; j < evolutionItemsCount; j++) 
                        {
                            if (evolutionItems[j] != null) 
                            {                                   
                                //evolutionItems[j].displayItems();
                            }
                        }
                        enterContinue(true);
                        break;
                }
                
        }while(choice != 3);
    }

     /**
        This method searches through the provided array of items and displays
        all items whose names contain the given search keyword (case-insensitive).
        If no matching items are found, it displays an appropriate message.
        @param itemsArray  the array of items to search through
        @param searchName  the keyword to search for in item names
    */
    public static void searchItemsByName(Items[] itemsArray, String searchName) 
    {
        boolean found = false;
        for (Items item : itemsArray) 
        {
            if (item == null) 
            {
                continue;
            }

            if (item.getitemName().toLowerCase().contains(searchName)) 
            {
               // item.displayItems(); 
                found = true;
            }
        }
        if (found == false) 
        {
            System.out.println("   Item not found");
            System.out.println("\n ================================================================================================");
        } 
    }

     /**
        This method searches through the provided array of items and displays
        all items whose category contains the given search keyword (case-insensitive).
        If no matching items are found, it displays an appropriate message.
        @param itemsArray the array of items to search through
        @param searchCategory the keyword to search for in item categories
    */
    public static void searchItemsByCategory(Items[] itemsArray, String searchCategory) 
    {
        boolean found = false;
        for (Items item : itemsArray) 
        {
            if (item == null) 
            {
                continue;
            }
            if (item.getitemCategory().toLowerCase().contains(searchCategory)) 
            {
                //item.displayItems(); // print each matched item
                found = true;
            }
        }
        if (found == false) 
        {
            System.out.println("   Item not found");
            System.out.println("\n ================================================================================================");
        } 
    }

    /**
        This method searches through the given array of items and displays
        all items whose description contains the specified keyword (case-insensitive).
        If no matches are found, it notifies the user.
        @param itemsArray  the array of items to search
        @param searchDesc  the keyword to search for in the item descriptions
    */
    public static void searchItemsByDescription(Items[] itemsArray, String searchDesc) 
    {
        boolean found = false;
        for (Items item : itemsArray) 
        {
            if (item == null) 
            {
                continue;
            }
            if (item.getitemDesc().toLowerCase().contains(searchDesc)) 
            {
                //item.displayItems(); // print each matched item
                found = true;
            }
        }
         if (found == false) 
        {
            System.out.println("   Item not found");
            System.out.println("\n ================================================================================================");
        } 
    }

    /**
        This method searches through the given array of items and displays
        all items whose effects field contains the specified keyword (case-insensitive).
        If no matching items are found, it displays an appropriate message.
        @param itemsArray the array of items to search
        @param searchEffect the keyword to search for in the item effects
     */
    public static void searchItemsByEffect(Items[] itemsArray, String searchEffect) 
    {
        boolean found = false;
        for (Items item : itemsArray) 
        {
            if (item == null) 
            {
                continue;
            }
            if (item.getitemEffects().toLowerCase().contains(searchEffect)) 
            { 
                //item.displayItems(); // print each matched item
                found = true;
            }
        }
        if (found == false) 
        {
            System.out.println("   Item not found");
            System.out.println("\n ================================================================================================");
        } 
    }

    /* 
     /**
        This method searches for items based on either buying or selling price.
        It supports searching within a price range (for evolution items) or exact match.
        If no matching items are found, it prints an appropriate message.
        @param itemsArray   the array of items to search
        @param searchPrice  the price value to search for
        @param option       the type of search: 5 = buying price, 6 = selling price
        @param Evo          true if the item is an evolution item (uses range search)
     
    public static void searchItemsByPrice(Items[] itemsArray, double searchPrice,int option, boolean Evo) 
    {
        boolean found = false;
        for (Items item : itemsArray) 
        {
            if (item == null) 
            {
                continue;
            }

            if(option == 5)
            {
                if(Evo == true)
                {
                    if (item.getstartBuyingPrice() <= searchPrice && item.getendBuyingPrice() >= searchPrice) 
                    {
                        item.displayItems(); // print each matched item
                        found = true;
                    }
                } 
        
                else 
                {
                   if(item.getstartBuyingPrice() == searchPrice){
                     item.displayItems(); // print each matched item
                     found = true;
                   } 
                }
            }
            
            else if(option ==6)
            {
                if (item.getsellingPrice() == searchPrice) 
                {
                     item.displayItems(); // print each matched item
                    found = true;
                }
            }
        }
       if (found == false) 
        {
            System.out.println("   Item not found");
            System.out.println("\n ================================================================================================");
        } 
    } */

    /**
        This method prompts the user for a valid double input with a specified prompt.
        It continues to ask until a valid double is entered, handling non-numeric inputs gracefully.
        @param in     the Scanner object for input
        @param prompt the message to display when asking for input
        @return       the valid double input from the user
     */
    public static double getValidDouble(Scanner in, String prompt) 
    {
        double value = 0;
        boolean valid = false;

        while (!valid) 
        {
            System.out.print(prompt);
            if (in.hasNextDouble()) 
            {
                value = in.nextDouble();
                valid = true;
            } 
            else 
            {
                System.out.println("   Input should be numeric\n");
                in.next(); 
            }
        }
        return value;
    }

    /**
        This method displays a search menu that allows the user to search items
        by name, category, description, effect, or price (buying/selling).
        It loops until the user selects the option to return to the previous menu.
        @param itemsArray the array of items to search through
        @param Evo true if searching evolution items (uses price range)
     */
    public static void searchItemCategory(Items[] itemsArray, boolean Evo)
    {
        int choice = 0;
        do
        {
            System.out.println("\n     ╔══════════════════════════════════════════════════════╗");
            System.out.println("      ================= ENHANCED POKEDEX ==================="); 
            System.out.println("     ╠══════════════════════════════════════════════════════╣");
            System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "");
            System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "  [1] Item Name");
            System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "  [2] Item Category");
            System.out.printf("     ║ %-15s ║ %-34s ║\n", "  Search", "  [3] Item Description");
            System.out.printf("     ║ %-15s ║ %-34s ║\n", "  Categories", "  [4] Item Effect");
            System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "  [5] Buying Price");
            System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "  [6] Selling Price");
            System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "  [7] Search-Item Menu");
            System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "");
            System.out.println("     ╚══════════════════════════════════════════════════════╝");
            choice  = getValidatedIntInput("\n      Enter: ", 1, 7, false,false);
           
            switch(choice)
            {
                case 1:
                System.out.println("\n ====================================== SEARCH ITEM NAME ========================================\n");
                in.nextLine(); // consume leftover newline
                System.out.print("   Enter Item Name:");
                String searchName = in.nextLine().toLowerCase();
                searchItemsByName(itemsArray, searchName);
                enterContinue(false);
                break;

                case 2:
                System.out.println("\n ==================================== SEARCH ITEM CATEGORY ======================================\n");
                in.nextLine(); // consume leftover newline
                System.out.print("   Enter Item Category:");
                String searchCategory = in.nextLine().toLowerCase();
                searchItemsByCategory(itemsArray,searchCategory);
                enterContinue(false);
                break;

                case 3: 
                System.out.println("\n =================================== SEARCH ITEM DESCRIPTION ====================================\n");
                in.nextLine();
                System.out.print("   Enter Item Description: ");
                String searchDesc = in.nextLine().toLowerCase();
                searchItemsByDescription(itemsArray,searchDesc);
                enterContinue(false);
                break;

                case 4:
                System.out.println("\n ===================================== SEARCH ITEM EFFECT =======================================\n");
                in.nextLine();
                System.out.print("   Enter Item Effect:");
                String searchEffect = in.nextLine().toLowerCase();
                searchItemsByEffect(itemsArray,searchEffect);
                enterContinue(false);
                break;

                case 5:
                System.out.println("\n ===================================== SEARCH BUYING PRICE ======================================\n");
                double searchBuyingPrice = getValidDouble(in, "   Enter Buying Price: ");
               // searchItemsByPrice(itemsArray,searchBuyingPrice,choice,Evo);
                enterContinue(true);
                break;

                case 6: 
                System.out.println("\n ===================================== SEARCH SELLING PRICE =====================================\n");
                double searchSellingPrice = getValidDouble(in, "   Enter Selling Price: ");
                //searchItemsByPrice(itemsArray,searchSellingPrice,choice,Evo);
                enterContinue(true);
                break;
            }
        }while(choice != 7);
    }

    /**
        This method displays the item search menu that lets the user choose
        between searching Available Items or Evolution Stones. It calls the
        appropriate search handler based on the user’s selection.
     */
    public static void manageSearchItem()
    {
        int choice = 0;
        do
        {
            System.out.println("\n     ╔══════════════════════════════════════════════════════╗");
            System.out.println("      ================= ENHANCED POKEDEX ==================="); 
            System.out.println("     ╠══════════════════════════════════════════════════════╣");
            System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "");
            System.out.printf("     ║ %-15s ║ %-34s ║\n", "  Search-Item", "  [1] Available Items");
            System.out.printf("     ║ %-15s ║ %-34s ║\n", "  Menu", "  [2] Evolution Stones");
            System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "  [3] Item Management Menu");
            System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "");
            System.out.println("     ╚══════════════════════════════════════════════════════╝");
            choice = getValidatedIntInput("\n      Enter: ", 1, 3, false,false);

            switch(choice)
            {
                case 1:
                searchItemCategory(availItems,false);
                break;

                case 2: 
                searchItemCategory(evolutionItems,true);
                break;
            }
        }while(choice != 3);
    }

     public static ArrayList<Pokemon> getAvailablePokemon() {
        ArrayList<Pokemon> availablePokemon = new ArrayList<>();
        for (Pokemon pokemon : pokemon)
        {
            availablePokemon.add(pokemon);
        }
        return availablePokemon;
    }
    
    /**
        The main method initializes the Pokedex application, loads data from files,
        and provides a menu for users to interact with the Pokedex features.
        It allows users to manage Pokemon, moves, items, and search functionalities.
        @param args command line arguments (not used)
        @throws FileNotFoundException if the specified files are not found
    */
    public static void main(String[] args) throws FileNotFoundException
    {
        int choice = 0, manage=0;
        Scanner input = new Scanner(new File("pokedex.txt"));
        Scanner input2 = new Scanner(new File("moves.txt"));
        input2.useDelimiter("-");
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

   while (input2.hasNext()) 
        {
            String Name = input2.next().trim();
            String Desc = input2.next().trim();
            String Machine = input2.next().trim();
            String Type1 = input2.next().trim();
            String Type2 = input2.next().trim();
            Moves.addMovetoDataBase(Name, Desc, Machine, Type1, Type2);
        }
            //Moves.addMovetoDataBase(null,null,null,null,null); for testing
        while(input.hasNext()) 
        {
            int PokedexNo = input.nextInt();
            String Name = input.next().replace("_"," ");
            String Type1 = input.next();
            String Type2 = input.next();
            int BaseLevel = input.nextInt();
            int From = input.nextInt();
            int To = input.nextInt();
            int EvoLevel = input.nextInt();
            int HP = input.nextInt();
            int Atk = input.nextInt();
            int Def = input.nextInt();
            int Spd = input.nextInt();
            pokemon[pokemonCount] = new Pokemon (PokedexNo,Name,Type1,Type2,BaseLevel,From,To,EvoLevel,HP,Atk,Def,Spd);
            pokemon[pokemonCount].teachMove("Tackle",false);
            pokemon[pokemonCount].teachMove("Defend",false);
           
            /* for testing item and move methods
            pokemon[pokemonCount].teachMove("Megahorn");
            pokemon[pokemonCount].teachMove("Surf");
            pokemon[pokemonCount].forgetMove("Surf");
            pokemon[pokemonCount].forgetMove("Megahorn");
            pokemon[pokemonCount].giveHeldItem("Magazine");
            pokemon[pokemonCount].giveHeldItem("HP Up");
            pokemon[pokemonCount].giveHeldItem("Carbos");
            pokemon[pokemonCount].removeHeldItem();*/ 
    

            pokemonCount++;
        }

        do
        {
           new DexGui("Pokemon");
            System.out.println("\n\n\n       ███████╗███╗   ██╗██╗  ██╗ █████╗ ███╗   ██╗ ██████╗███████╗██████╗     ██████╗  ██████╗ ██╗  ██╗███████╗██████╗ ███████╗██╗  ██╗");
            System.out.println("        ██╔════╝████╗  ██║██║  ██║██╔══██╗████╗  ██║██╔════╝██╔════╝██╔══██╗    ██╔══██╗██╔═══██╗██║ ██╔╝██╔════╝██╔══██╗██╔════╝╚██╗██╔╝");
            System.out.println("        █████╗  ██╔██╗ ██║███████║███████║██╔██╗ ██║██║     █████╗  ██║  ██║    ██████╔╝██║   ██║█████╔╝ █████╗  ██║  ██║█████╗   ╚███╔╝ ");
            System.out.println("        ██╔══╝  ██║╚██╗██║██╔══██║██╔══██║██║╚██╗██║██║     ██╔══╝  ██║  ██║    ██╔═══╝ ██║   ██║██╔═██╗ ██╔══╝  ██║  ██║██╔══╝   ██╔██╗ ");
            System.out.println("        ███████╗██║ ╚████║██║  ██║██║  ██║██║ ╚████║╚██████╗███████╗██████╔╝    ██║     ╚██████╔╝██║  ██╗███████╗██████╔╝███████╗██╔╝ ██╗");
            System.out.println("        ╚══════╝╚═╝  ╚═══╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝ ╚═════╝╚══════╝╚═════╝     ╚═╝      ╚═════╝ ╚═╝  ╚═╝╚══════╝╚═════╝ ╚══════╝╚═╝  ╚═╝\n\n");
            System.out.println("                            ╔══════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("                            ║                                                                                      ║");
            System.out.println("                            ║            ╔════════════════════════════════════════════════════════════╗            ║");
            System.out.println("                            ║            ║                                                            ║            ║");
            System.out.println("                            ║            ║                                                            ║            ║");
            System.out.println("                            ║            ║              WELCOME TO THE ENHANCED POKEDEX               ║            ║");
            System.out.println("                            ║   == .     ║                       ============                         ║  . . .     ║");
            System.out.println("                            ║      _     ║                                                            ║     _      ║");
            System.out.println("                            ║     / \\    ║               Catch, Train, Battle, Repeat!                ║    / \\     ║");
            System.out.println("                            ║    | O |   ║                                                            ║   | O |    ║");
            System.out.println("                            ║     \\_/    ║                                                            ║    \\_/     ║");
            System.out.println("                            ║            ║                       [1] POKEMON                          ║            ║");
            System.out.println("                            ║            ║                       [2] MOVES                            ║            ║");
            System.out.println("                            ║            ║                       [3] ITEMS                            ║            ║");
            System.out.println("                            ║            ║                       [4] TRAINERS                         ║            ║");
            System.out.println("                            ║    :::     ║                       [5] EXIT                             ║     :::    ║");
            System.out.println("                            ║    :::     ║                                                            ║     :::    ║");
            System.out.println("                            ║            ║                                                            ║            ║");
            System.out.println("                            ║            ╚════════════════════════════════════════════════════════════╝            ║");
            System.out.println("                            ║                P O K E D E X                                                         ║");
            System.out.println("                            ╚══════════════════════════════════════════════════════════════════════════════════════╝");
            System.out.print("");
            choice = getValidatedIntInput("\n                            Enter Number to Manage: ", 1, 5, true,false);

            switch (choice)
            {
                // Pokemon Management Menu
                case 1:
                    do 
                    {
                        System.out.println("\n     ╔══════════════════════════════════════════════════════╗");
                        System.out.println("      ================== ENHANCED POKEDEX =================="); 
                        System.out.println("     ╠══════════════════════════════════════════════════════╣");
                        System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "");
                        System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "  [1] Add Pokemon");
                        System.out.printf("     ║ %-15s ║ %-34s ║\n", "  Pokemon", "  [2] View Pokemon");
                        System.out.printf("     ║ %-15s ║ %-34s ║\n", "  Management", "  [3] Search Pokemon");
                        System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "  [4] Main Menu");
                        System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "");
                        System.out.println("     ╚══════════════════════════════════════════════════════╝");
                        manage = getValidatedIntInput("\n      Enter: ", 1, 4, false,false);

                        switch (manage) 
                        {
                            case 1:
                                addPokemon();
                                enterContinue(true);
                                break;
                            case 2:
                                viewPokemon();
                                enterContinue(true);
                                break;
                            case 3:
                                searchPokemon();
                                break;
                        }
                    }while (manage!=4);
                    break;

                // Moves Management Menu
                case 2:
                    do 
                    {
                        System.out.println("\n     ╔══════════════════════════════════════════════════════╗");
                        System.out.println("      ================== ENHANCED POKEDEX =================="); 
                        System.out.println("     ╠══════════════════════════════════════════════════════╣");
                        System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "");
                        System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "  [1] Add Move");
                        System.out.printf("     ║ %-15s ║ %-34s ║\n", "  Move", "  [2] View Move");
                        System.out.printf("     ║ %-15s ║ %-34s ║\n", "  Management", "  [3] Search Move");
                        System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "  [4] Main Menu");
                        System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "");
                        System.out.println("     ╚══════════════════════════════════════════════════════╝");
                        manage = getValidatedIntInput("\n      Enter: ", 1, 4, false,false);

                        switch (manage) 
                        {
                            case 1:
                                addMove();
                                enterContinue(false);
                                break;
                            case 2:
                                viewMove();
                                enterContinue(true);
                                break;
                            case 3:
                                searchMove();
                                break;
                        }
                    }while (manage != 4);
                    break;
                
                    // Item Management Menu
                    case 3:
                    do
                    {
                        System.out.println("\n     ╔══════════════════════════════════════════════════════╗");
                        System.out.println("      ================= ENHANCED POKEDEX ==================="); 
                        System.out.println("     ╠══════════════════════════════════════════════════════╣");
                        System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "");
                        System.out.printf("     ║ %-15s ║ %-34s ║\n", "  Item", "  [1] View Items");
                        System.out.printf("     ║ %-15s ║ %-34s ║\n", "  Management", "  [2] Search Items");
                        System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "  [3] Main Menu");
                        System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "");
                        System.out.println("     ╚══════════════════════════════════════════════════════╝");
                        manage =getValidatedIntInput("\n      Enter: ", 1, 3, false,false);

                        if(manage < 1 || manage > 3)
                        {
                            System.out.println("      Invalid Input, Try Again");
                        }

                        switch(manage)
                        {
                            case 1:
                            manageViewItem();
                            break;

                            case 2: 
                            manageSearchItem();
                            break;
                        }
                    }while(manage != 3);
                    break;

                    case 4:
                    do
                    {
                        System.out.println("\n     ╔══════════════════════════════════════════════════════╗");
                        System.out.println("      ================= ENHANCED POKEDEX ==================="); 
                        System.out.println("     ╠══════════════════════════════════════════════════════╣");
                        System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "");
                        System.out.printf("     ║ %-15s ║ %-34s ║\n", "  Trainer", "  Coming Soon");
                        System.out.printf("     ║ %-15s ║ %-34s ║\n", "  Management", "  [1] Main Menu");
                        System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "");
                        System.out.printf("     ║ %-15s ║ %-34s ║\n", "", "");
                        System.out.println("     ╚══════════════════════════════════════════════════════╝");
                        manage= getValidatedIntInput("\n      Enter: ", 1, 1, false,false);

                    }while(manage != 1);
                    break;
            }
        }while(choice != 5);
    }
}