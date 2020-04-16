package tp.logic.game_data.rooms;

public class Astrometrics extends Room{
    
    public Astrometrics(){
        super(10, "Astrometrics", false);
        

        setMoveOnePosition(12);
        setMoveOnePosition(5);
        setMoveOnePosition(6);
    
        setMoveTwoPositions(9);
        setMoveTwoPositions(3);
        setMoveTwoPositions(1);
        setMoveTwoPositions(8);
        setMoveTwoPositions(2);

        setMoveThreePositions(4);
        setMoveThreePositions(7);
    }
    
}