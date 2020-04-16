package tp.logic.game_data.crew_members;

import tp.logic.game_data.rooms.*;
import tp.logic.game_data.aliens.*;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;


public class TransporterChief extends CrewMember implements Serializable{
    
    public TransporterChief(){
        super("Transporter Chief", 8, 13, 1);
    }

    @Override
    public int attack(ArrayList <Alien> aliens, int diceValue) {
        if(diceValue < 5)
            return 0;

        for(int i=0 ; i<aliens.size() ; i++){
            if( aliens.get(i).getRoom() == this.getRoom()){
                aliens.remove(i);
                return 1;
            }
        }
        return -1;
    }

    
    @Override
    public int move(Room room) {
        this.setRoom(room);
        return 1;
    }

}