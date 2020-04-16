package tp.logic.state_machine.states;

import tp.logic.state_machine.GameStateAdapter;
import tp.logic.state_machine.StateInterface;
import tp.logic.game_data.GameData;

public class StartEnd extends GameStateAdapter
{
    public StartEnd(GameData game_data)
    {
        super(game_data);
        return;
    }

    @Override
    public StateInterface restartGame()
    {
        return new MemberSelectionMode(getGameData());
    }
}