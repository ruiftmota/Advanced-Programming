package tp.logic.game_data.aliens;

import java.io.Serializable;

import tp.logic.game_data.rooms.*;

public class Alien implements Serializable
{
    public static int contador = 0;

    private Room room = null;
    private int room_number = 0;
    private int id = 0;

    public Alien()
    {
        Alien.contador++;
        this.id = Alien.contador;
    }

    public Alien(Room room)
    {
        Alien.contador++;
        this.id = Alien.contador;
        this.room = room;
    }

    public Room getRoom() { return room; }
    public void setRoom(Room room){ this.room = room; }

    public int getRoomNumber() { return room_number; }
    public void setRoomNumber(int room_number){ this.room_number = room_number; }

    public int getId() { return id; }

    public void alienAttack(){
        
    }
}