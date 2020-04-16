package tp.logic.game_data.rooms;

public class SickBay extends Room{
    
    public SickBay(){
        super(2, "Sick Bay", false);


        setMoveOnePosition(7);
        setMoveOnePosition(8);
        setMoveOnePosition(6);

        setMoveTwoPositions(11);
        setMoveTwoPositions(4);
        setMoveTwoPositions(1);
        setMoveTwoPositions(5);
        
        setMoveThreePositions(3);
        setMoveThreePositions(10);
    }

}