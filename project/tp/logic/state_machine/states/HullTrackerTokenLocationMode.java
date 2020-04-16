package tp.logic.state_machine.states;

import tp.logic.state_machine.GameStateAdapter;
import tp.logic.state_machine.StateInterface;
import tp.logic.game_data.GameData;

public class HullTrackerTokenLocationMode extends GameStateAdapter
{
    public HullTrackerTokenLocationMode(GameData game_data)
    {
        super(game_data);
        return;
    }

    @Override
    public StateInterface chooseRandom()
    {
        return new HealthTrackerTokenLocationMode(getGameData());
    }

    @Override
    public StateInterface chooseManual()
    {
        return new PutTokenInHullTracker(getGameData());
    }

    @Override
    public StateInterface restartGame()
    {
        return new StartEnd(getGameData());
    }
}