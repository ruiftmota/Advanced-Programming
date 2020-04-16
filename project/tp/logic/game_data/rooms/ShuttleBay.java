package tp.logic.game_data.rooms;

public class ShuttleBay extends Room{
    
    public ShuttleBay(){
        super(6, "Shuttle Bay", false);

        setMoveOnePosition(2);
        setMoveOnePosition(10);

        setMoveTwoPositions(7);
        setMoveTwoPositions(8);
        setMoveTwoPositions(5);
        setMoveTwoPositions(12);
        
        setMoveThreePositions(3);
        setMoveThreePositions(9);
        setMoveThreePositions(1);
        setMoveThreePositions(4);
        setMoveThreePositions(11);
    }
    
}