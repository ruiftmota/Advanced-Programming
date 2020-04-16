package tp.logic.game_data.rooms;

public class Bridge extends Room{

    public Bridge(){
        super(1, "Bridge", false);

        
        setMoveOnePosition(5);
        setMoveOnePosition(8);
    
        setMoveTwoPositions(3);
        setMoveTwoPositions(10);
        setMoveTwoPositions(4);
        setMoveTwoPositions(2);

        setMoveThreePositions(7);
        setMoveThreePositions(11);
        setMoveThreePositions(9);
        setMoveThreePositions(12);
        setMoveThreePositions(6);
    }
    
}