package tp.logic.game_data.rooms;

public class Engineering extends Room{
    
    public Engineering(){
        super(9, "Engineering", true);
        

        setMoveOnePosition(3);
        setMoveOnePosition(12);

        setMoveTwoPositions(5);
        setMoveTwoPositions(10);
        
        setMoveThreePositions(4);
        setMoveThreePositions(7);
    }
    
}