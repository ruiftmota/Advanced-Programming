package tp.logic.state_machine.states;

import tp.logic.state_machine.GameStateAdapter;
import tp.logic.state_machine.StateInterface;
import tp.logic.enumerations.EnumCrewMembers;
import tp.logic.game_data.GameData;

public class ChooseMemberWhoPutsTrap extends GameStateAdapter
{
    public ChooseMemberWhoPutsTrap(GameData game_data)
    {
        super(game_data);
        return;
    }

    @Override
    public StateInterface chooseMember(EnumCrewMembers member)
    {
        return new ChooseWhichTrapToPut(getGameData(), member);
    }

    @Override
    public StateInterface restartGame()
    {
        return new StartEnd(getGameData());
    }
}