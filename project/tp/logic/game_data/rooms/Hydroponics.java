package tp.logic.game_data.rooms;

public class Hydroponics extends Room{
    
    public Hydroponics(){
        super(12, "Hydroponics", true);


        setMoveOnePosition(9);
        setMoveOnePosition(10);

        setMoveTwoPositions(3);
        setMoveTwoPositions(5);
        setMoveTwoPositions(6);
        
        setMoveThreePositions(1);
        setMoveThreePositions(8);
        setMoveThreePositions(2);
    }
    
}