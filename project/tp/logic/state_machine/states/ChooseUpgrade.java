package tp.logic.state_machine.states;

import tp.logic.state_machine.GameStateAdapter;
import tp.logic.state_machine.StateInterface;
import tp.logic.enumerations.EnumUpgrades;
import tp.logic.game_data.GameData;

public class ChooseUpgrade extends GameStateAdapter
{
    public ChooseUpgrade(GameData game_data)
    {
        super(game_data);
        return;
    }

    @Override
    public StateInterface nextRound()
    {
        return new ChooseAction(getGameData());
    }

    @Override
    public StateInterface chooseUpgrade(EnumUpgrades upgrade)
    {
        GameData game_data = getGameData();
        StateInterface next_state = null;

        game_data.upgrade(upgrade);

        if(game_data.getInspirationPoints() <= 0)
        {
            switch(game_data.nextRound())
            {
                case END_GAME:
                    next_state = new StartEnd(game_data);
                    break;

                case CHOOSE_ACTION:
                    next_state = new ChooseAction(game_data);
                    break;

                case CHOOSE_UPGRADE:
                    next_state = new ChooseUpgrade(game_data);
                    break;
            }

            return next_state;
        }

        return new ChooseUpgrade(game_data);
    }

    @Override
    public StateInterface restartGame()
    {
        return new StartEnd(getGameData());
    }
}