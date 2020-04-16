package tp.logic.state_machine.states;

import tp.logic.state_machine.GameStateAdapter;
import tp.logic.state_machine.StateInterface;
import tp.logic.game_data.GameData;

public class PutTokenInHullTracker extends GameStateAdapter
{
    public PutTokenInHullTracker(GameData game_data)
    {
        super(game_data);
        return;
    }

    @Override
    public StateInterface setToken(int position)
    {
        GameData game_data = getGameData();

        game_data.putTokenInHullTracker(position);

        return new HealthTrackerTokenLocationMode(game_data);
    }

    @Override
    public StateInterface restartGame()
    {
        return new StartEnd(getGameData());
    }
}