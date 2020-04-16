package tp.logic.game_data.rooms;

public class CrewQuarters extends Room{
    
    public CrewQuarters(){
        super(4, "Crew Quarters", true);
    

        setMoveOnePosition(8);
        setMoveOnePosition(11);

        setMoveTwoPositions(7);
        setMoveTwoPositions(2);
        setMoveTwoPositions(1);
        setMoveTwoPositions(5);
        
        setMoveThreePositions(3);
        setMoveThreePositions(10);
        setMoveThreePositions(6);
    
    }
    


}