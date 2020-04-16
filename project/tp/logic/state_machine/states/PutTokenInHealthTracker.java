package tp.logic.state_machine.states;

import tp.logic.state_machine.GameStateAdapter;
import tp.logic.state_machine.StateInterface;
import tp.logic.game_data.GameData;

public class PutTokenInHealthTracker extends GameStateAdapter
{
    public PutTokenInHealthTracker(GameData game_data)
    {
        super(game_data);
        return;
    }

    public StateInterface setToken(int position)
    {
        GameData game_data = getGameData();

        game_data.putTokenInHealthTracker(position);

        return new ChooseAction(game_data);
    }

    @Override
    public StateInterface restartGame()
    {
        return new StartEnd(getGameData());
    }
}