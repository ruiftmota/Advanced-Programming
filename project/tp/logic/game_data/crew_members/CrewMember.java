package tp.logic.game_data.crew_members;

import tp.logic.game_data.rooms.*;
import tp.logic.game_data.aliens.*;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class CrewMember implements Serializable {
    private String name;
    private int movement;
    private int numberOfAttacks;
    private int id;
    private Room room;
    private int room_number = 0;

    public CrewMember(String name, int id, int movement, int numberOfAttacks){
        this.name = new String(name);
        this.movement = movement;
        this.numberOfAttacks = numberOfAttacks;
        this.id = id;
    };

    public String getName(){ return new String(name); }

    public int getId(){ return id; }

    public int getMovement(){ return movement; }
    public void setMovement(int movement)
    {
        this.movement = movement;
        if(movement > 3)
        {
            movement = 3;
        } 
    }

    public int getNumberOfAttacks(){ return numberOfAttacks; }
    public void setNumberOfAttacks(int numberOfAttacks)
    {
        this.numberOfAttacks = numberOfAttacks;
        if(numberOfAttacks > 3)
        {
            numberOfAttacks = 3;
        }
    }

    public Room getRoom(){ return room;}
    public void setRoom(Room room) { this.room = room; }

    public int getRoomNumber() { return room_number; }
    public void setRoomNumber(int room_number){ this.room_number = room_number; }

    public abstract int move(Room room);
    public abstract int attack(ArrayList <Alien> aliens, int diceValue);
}