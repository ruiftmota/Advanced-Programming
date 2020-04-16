package tp.logic.game_data.rooms;

public class Holodeck extends Room{
    
    public Holodeck(){
        super(11, "Holodeck", true);

        setMoveOnePosition(4);
        setMoveOnePosition(7);

        setMoveTwoPositions(8);
        setMoveTwoPositions(2);
        
        setMoveThreePositions(1);
        setMoveThreePositions(5);
        setMoveThreePositions(10);
        setMoveThreePositions(6);
    }
    
}