package tp.logic.state_machine.states;

import tp.logic.state_machine.GameStateAdapter;
import tp.logic.state_machine.StateInterface;
import tp.logic.game_data.GameData;

public class HealthTrackerTokenLocationMode extends GameStateAdapter
{
    public HealthTrackerTokenLocationMode(GameData game_data)
    {
        super(game_data);
        return;
    }

    public StateInterface chooseRandom()
    {
        return new ChooseAction(getGameData());
    }

    public StateInterface chooseManual()
    {
        return new PutTokenInHealthTracker(getGameData());
    }

    @Override
    public StateInterface restartGame()
    {
        return new StartEnd(getGameData());
    }
}