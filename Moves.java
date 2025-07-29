//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.PrintStream;

public class Moves {
    private String Name;
    private String Desc;
    private String Machine;
    private String Type1;
    private String Type2;
    public static int moveCount = 0;
    public static Moves[] moveList = new Moves[100];

    public Moves(String Name, String Desc, String Machine, String Type1, String Type2) {
        this.Name = Name;
        this.Desc = Desc;
        this.Machine = Machine;
        this.Type1 = Type1;
        this.Type2 = Type2;
    }

    public String getName() {
        return this.Name;
    }

    public String getDesc() {
        return this.Desc;
    }

    public String getMachine() {
        return this.Machine;
    }

    public String getType1() {
        return this.Type1;
    }

    public String getType2() {
        return this.Type2;
    }

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

    public static Moves getMoveByName(String name)
    {
        for (Moves move : moveList)
        {
            if (move != null && move.getName().equalsIgnoreCase(name.strip()))
            {                return move;
            }
        }

        return null;
    }
}
