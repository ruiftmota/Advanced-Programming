package tp.logic.game_data.crew_members;

import tp.logic.game_data.rooms.*;
import tp.logic.game_data.aliens.*;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;


public class Captain extends CrewMember  implements Serializable{

    public Captain() {
        super("Captain", 6, 1, 1);
    }

    @Override
    public int attack(ArrayList <Alien> aliens, int diceValue) {
        if(diceValue < 3)
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
        if( room.getIsSealed() == true) return 0;

        if(this.getMovement()>0){
            for( int i=0 ; i<this.getRoom().getMoveOnePosition().size() ; i++ ){
                if( this.getRoom().getMoveOnePosition().get(i) == room.getId()){
                    this.setRoom(room);
                    return 1;
                }
            }
        }

        if(this.getMovement()>1){
            for( int i=0 ; i<this.getRoom().getMoveTwoPositions().size() ; i++ ){
                if( this.getRoom().getMoveTwoPositions().get(i) == room.getId()){
                    this.setRoom(room);
                    return 1;
                }
            }
        }

        if(this.getMovement()>2){
            for( int i=0 ; i<this.getRoom().getMoveThreePositions().size() ; i++ ){
                if( this.getRoom().getMoveThreePositions().get(i) == room.getId()){
                    this.setRoom(room);
                    return 1;
                }
            }
        }
        return -1;
    }

    

}