package tp.logic.state_machine.states;

import tp.logic.state_machine.GameStateAdapter;
import tp.logic.state_machine.StateInterface;
import tp.logic.enumerations.EnumNextRound;
import tp.logic.game_data.GameData;

public class ChooseAction extends GameStateAdapter
{
    public ChooseAction(GameData game_data)
    {
        super(game_data);
        return;
    }

    @Override
    public StateInterface nextRound()
    {
        GameData game_data = getGameData();
        StateInterface next_state = null;

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

    @Override
    public StateInterface chooseHealPlayer()
    {
        GameData game_data = getGameData();
        StateInterface next_state = null;

        game_data.healPlayer();

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

        return new ChooseAction(getGameData());
    }

    @Override
    public StateInterface chooseRepairHull()
    {
        GameData game_data = getGameData();
        StateInterface next_state = null;

        game_data.repairHull();

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

        return new ChooseAction(getGameData());
    }

    @Override
    public StateInterface chooseDetonate()
    {
        return new WhichTrapToDetonate(getGameData());
    }

    @Override
    public StateInterface chooseMove()
    {
        return new ChooseMemberToMove(getGameData());
    }

    @Override
    public StateInterface chooseAttack()
    {
        return new ChooseMemberWhoAttacks(getGameData());
    }

    @Override
    public StateInterface chooseSetTrap()
    {
        return new ChooseMemberWhoPutsTrap(getGameData());
    }

    @Override
    public StateInterface chooseSealRoom()
    {
        return new ChooseRoomToSeal(getGameData());
    }
    
    @Override
    public StateInterface chooseSacrificeRedShirt()
    {
        GameData game_data = getGameData();
        StateInterface next_state = null;

        game_data.sacrificeRedShirt();

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