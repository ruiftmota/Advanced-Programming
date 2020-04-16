package tp.logic.state_machine.states;

import tp.logic.state_machine.GameStateAdapter;
import tp.logic.state_machine.StateInterface;
import tp.logic.enumerations.EnumCrewMembers;
import tp.logic.game_data.GameData;

public class ChooseWhereToMoveMember extends GameStateAdapter
{
    EnumCrewMembers member;

    public ChooseWhereToMoveMember(GameData game_data, EnumCrewMembers member)
    {
        super(game_data);
        this.member = member;
        return;
    }

    @Override
    public StateInterface selectRoom(int room_number)
    {
        GameData game_data = getGameData();
        StateInterface next_state = null;

        game_data.moveMemberToRoom(member, room_number);

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

    @Override
    public EnumCrewMembers getMovingCrewMember(){
        return member;
    }
}