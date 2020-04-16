package tp.logic.state_machine.states;

import tp.logic.state_machine.GameStateAdapter;
import tp.logic.state_machine.StateInterface;
import tp.logic.enumerations.EnumCrewMembers;
import tp.logic.enumerations.EnumTraps;
import tp.logic.game_data.GameData;

public class ChooseWhichTrapToPut extends GameStateAdapter
{
    EnumCrewMembers member;

    public ChooseWhichTrapToPut(GameData game_data, EnumCrewMembers member)
    {
        super(game_data);
        this.member = member;
        return;
    }

    @Override
    public StateInterface selectTrap(EnumTraps trap)
    {
        GameData game_data = getGameData();

        game_data.putTrapInRoom(member, trap);
        StateInterface next_state = null;

        if(game_data.getActionPoints() <= 0)
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

        return new ChooseAction(game_data);
    }

    @Override
    public StateInterface restartGame()
    {
        return new StartEnd(getGameData());
    }
}