package tp.logic.state_machine.states;

import tp.logic.state_machine.GameStateAdapter;
import tp.logic.state_machine.StateInterface;
import tp.logic.game_data.GameData;

public class SelectMemberPosition extends GameStateAdapter
{
    public SelectMemberPosition(GameData game_data)
    {
        super(game_data);
        return;
    }

    @Override
    public StateInterface selectRoom(int room_number)
    {
        GameData game_data = getGameData();

        if(game_data.selectMembersPositionManually(room_number) == true)
        {
            return new SelectMemberPosition(game_data);
        }
        else
        {
            return new HullTrackerTokenLocationMode(game_data);
        }
    }

    @Override
    public StateInterface restartGame()
    {
        return new StartEnd(getGameData());
    }
}