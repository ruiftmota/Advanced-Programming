package tp.logic.game_data.traps;

import java.util.ArrayList;

import tp.logic.enumerations.*;
import tp.logic.game_data.crew_members.CrewMember;
import tp.logic.game_data.rooms.*;

public class Trap{
    private EnumTraps trap_enum;
    private int id;
    private String name;
    private Room room = null;
    private int room_number = 0;

    public Trap(String name, int id){
        this.name = new String(name);
        this.id = id;
    };

    public String getName(){ return new String(name); }

    public int getId(){ return id; }

    public Room getRoom(){ return room;}
    public void setRoom(Room room) {this.room = room;}

    public int getRoomNumber() { return room_number; }
    public void setRoomNumber(int room_number){ this.room_number = room_number; }

    //public abstract void activateTrap(ArrayList<Alien> aliens, ArrayList<CrewMember> crew_members, int room_number);

}