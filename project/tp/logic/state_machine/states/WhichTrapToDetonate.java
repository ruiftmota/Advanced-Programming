package tp.logic.state_machine.states;

import tp.logic.state_machine.GameStateAdapter;
import tp.logic.state_machine.StateInterface;
import tp.logic.game_data.GameData;

public class WhichTrapToDetonate extends GameStateAdapter
{
    public WhichTrapToDetonate(GameData game_data)
    {
        super(game_data);
        return;
    }

    @Override
    public StateInterface selectRoom(int room_number)
    {
        GameData game_data = getGameData();

        game_data.detonateParticleDisperser(room_number);

        return new ChooseAction(game_data);
    }

    @Override
    public StateInterface restartGame()
    {
        return new StartEnd(getGameData());
    }
}