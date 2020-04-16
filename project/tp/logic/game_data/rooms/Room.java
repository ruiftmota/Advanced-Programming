package tp.logic.game_data.rooms;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable{
    private int id;
    private String name;
    private boolean canBeSealed;
    private boolean isSealed;
    private ArrayList <Integer> moveOnePosition;
    private ArrayList <Integer> moveTwoPositions;
    private ArrayList <Integer> moveThreePositions;

    public Room(int id, String name, boolean canBeSealed){
        this.id = id;
        this.name = new String(name);
        this.canBeSealed = canBeSealed;
        this.isSealed = false;
        moveOnePosition = new ArrayList<>();
        moveTwoPositions = new ArrayList<>();
        moveThreePositions = new ArrayList<>();
    }

    public int getId(){ return id; }

    public String getName(){ return new String(name); }

    public boolean getCanBeSealed(){ return canBeSealed; }

    public boolean getIsSealed(){ return isSealed; }
    public void setIsSealed(){ isSealed = true; }



    public void setMoveOnePosition(int number){
        Integer aux = new Integer(number);
        moveOnePosition.add(aux);
    }
    public void setMoveTwoPositions(int number){
        Integer aux = new Integer(number);
        moveTwoPositions.add(aux);
    }
    public void setMoveThreePositions(int number){
        Integer aux = new Integer(number);
        moveThreePositions.add(aux);
    }

    public ArrayList<Integer> getAdjacentRooms() { return new ArrayList<Integer>(moveOnePosition); }
    public ArrayList<Integer> getMoveOnePosition(){ return new ArrayList<Integer>(moveOnePosition);}
    public ArrayList<Integer> getMoveTwoPositions(){ return new ArrayList<Integer>(moveTwoPositions);}
    public ArrayList<Integer> getMoveThreePositions(){ return new ArrayList<Integer>(moveThreePositions);}

    public boolean canMoveToAnotherRoom(int idRoom, int numberOfMovesPermited){

        boolean yes_no = false;

        if(numberOfMovesPermited >= 1 && numberOfMovesPermited <=3)
        {
            switch(numberOfMovesPermited)
            {
                case 3:
                    for( int i=0 ; i<moveThreePositions.size() ; i++){
                        if(moveThreePositions.get(i).intValue() == idRoom){
                            yes_no = true;
                        }
                    }
                    
                    for( int i=0 ; i<moveTwoPositions.size() ; i++){
                        if(moveTwoPositions.get(i).intValue() == idRoom){
                            yes_no = true;
                        }
                    }

                    for( int i=0 ; i<moveOnePosition.size() ; i++){
                        if(moveOnePosition.get(i).intValue() == idRoom){
                            yes_no = true;
                        }
                    }
                    break;

                case 2:
                    for( int i=0 ; i<moveTwoPositions.size() ; i++){
                            if(moveTwoPositions.get(i).intValue() == idRoom){
                                yes_no = true;
                            }
                        }

                    for( int i=0 ; i<moveOnePosition.size() ; i++){
                        if(moveOnePosition.get(i).intValue() == idRoom){
                            yes_no = true;
                        }
                    }
                    break;

                case 1:
                    for( int i=0 ; i<moveOnePosition.size() ; i++){
                        if(moveOnePosition.get(i).intValue() == idRoom){
                            yes_no = true;
                        }
                    }
                    break;
            }
        }
        else
        {
            yes_no = false;
        }

        return yes_no;


        /* if(numberOfMovesPermited < 1 || numberOfMovesPermited > 3) return false;

        if(numberOfMovesPermited > 2){
            for( int i=0 ; i<moveThreePositions.size() ; i++){
                if(moveThreePositions.get(i).intValue() == idRoom){
                    return true;
                }
                    
            }
        }
        if(numberOfMovesPermited > 1){
            for( int i=0 ; i<moveTwoPositions.size() ; i++){
                if(moveTwoPositions.get(i).intValue() == idRoom)
                    return true;
            }
        }
        if(numberOfMovesPermited > 0){
            for( int i=0 ; i<moveOnePosition.size() ; i++){
                if(moveOnePosition.get(i).intValue() == idRoom)
                    return true;
            }
        }
        return false; */
    }
}