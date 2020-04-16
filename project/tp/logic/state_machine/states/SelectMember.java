package tp.logic.state_machine.states;

import tp.logic.state_machine.GameStateAdapter;
import tp.logic.state_machine.StateInterface;
import tp.logic.enumerations.EnumCrewMembers;
import tp.logic.game_data.GameData;

public class SelectMember extends GameStateAdapter
{
    public SelectMember(GameData game_data)
    {
        super(game_data);
        return;
    }

    @Override
    public StateInterface chooseMember(EnumCrewMembers member)
    {
        GameData game_data = getGameData();

        if(game_data.selectMemberManually(member) < 1)
        {
            return new SelectMember(game_data);
        }
        else
        {
            return new MemberPositionSelectionMode(getGameData());
        }
    }

    @Override
    public StateInterface restartGame()
    {
        return new StartEnd(getGameData());
    }
}