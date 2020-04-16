package tp.logic.game_data.rooms;

public class ConferenceRoom extends Room{
    
    public ConferenceRoom(){
        super(5, "Conference Room", false);
        

        setMoveOnePosition(1);
        setMoveOnePosition(3);
        setMoveOnePosition(10);
        setMoveOnePosition(8);
        
        setMoveTwoPositions(9);
        setMoveTwoPositions(12);
        setMoveTwoPositions(4);
        setMoveTwoPositions(2);
        setMoveTwoPositions(6);
        
        setMoveThreePositions(11);
        setMoveThreePositions(7);
        
    }
    
}