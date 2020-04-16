package tp.logic.game_data.rooms;

public class WeaponsBay extends Room{
    
    public WeaponsBay(){
        super(7, "Weapons Bay", false);

        setMoveOnePosition(2);
        setMoveOnePosition(11);

        setMoveTwoPositions(6);
        setMoveTwoPositions(8);
        setMoveTwoPositions(4);
        
        setMoveThreePositions(10);
        setMoveThreePositions(5);
        setMoveThreePositions(1);
    }
    
}