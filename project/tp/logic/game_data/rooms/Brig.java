package tp.logic.game_data.rooms;

public class Brig extends Room{
    
    public Brig(){
        super(3, "Brig", true);

        
        setMoveOnePosition(5);
        setMoveOnePosition(9);

        setMoveTwoPositions(1);
        setMoveTwoPositions(10);
        setMoveTwoPositions(12);
        setMoveTwoPositions(8);

        setMoveThreePositions(4);
        setMoveThreePositions(2);
        setMoveThreePositions(6);
    }
    
}