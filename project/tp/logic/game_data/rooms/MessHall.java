package tp.logic.game_data.rooms;

public class MessHall extends Room{
    
    public MessHall(){
        super(8, "Mess Hall", false);

        setMoveOnePosition(1);
        setMoveOnePosition(5);
        setMoveOnePosition(4);
        setMoveOnePosition(2);

        setMoveTwoPositions(3);
        setMoveTwoPositions(10);
        setMoveTwoPositions(11);
        setMoveTwoPositions(7);
        setMoveTwoPositions(6);
        
        setMoveThreePositions(9);
        setMoveThreePositions(12);
    }
    
}