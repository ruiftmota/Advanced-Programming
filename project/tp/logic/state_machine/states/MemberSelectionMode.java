package tp.logic.state_machine.states;

import tp.logic.state_machine.GameStateAdapter;
import tp.logic.state_machine.StateInterface;
import tp.logic.game_data.GameData;

public class MemberSelectionMode extends GameStateAdapter
{
    public MemberSelectionMode(GameData game_data)
    {
        super(game_data);
        return;
    }

    @Override
    public StateInterface chooseRandom()
    {
        GameData game_data = getGameData();

        game_data.selectMembersRandomly();

        return new MemberPositionSelectionMode(game_data);
    }

    @Override
    public StateInterface chooseManual()
    {        
        return new SelectMember(getGameData());
    }

    @Override
    public StateInterface restartGame()
    {
        return new StartEnd(getGameData());
    }
}