package tp.logic.state_machine;

import tp.logic.state_machine.StateInterface;
import tp.logic.game_data.GameData;
import tp.logic.enumerations.*;

public class GameStateAdapter implements StateInterface
{
    GameData game_data = null;

    public GameStateAdapter(GameData game_data)
    {
        this.game_data = game_data;
        return;
    }

    public GameData getGameData() { return game_data; }

    @Override
    public StateInterface chooseRandom() { return this; }
    @Override
    public StateInterface chooseManual() { return this; }
    @Override
    public StateInterface chooseMember(EnumCrewMembers member) { return this; }
    @Override
    public StateInterface setMemberPosition(int room_number) { return this; }
    @Override
    public StateInterface setToken(int position) { return this; }
    @Override
    public StateInterface nextRound() { return this; }
    @Override
    public StateInterface chooseDetonate() { return this; }
    @Override
    public StateInterface chooseRepairHull() { return this; }
    @Override
    public StateInterface chooseHealPlayer() { return this; }
    @Override
    public StateInterface chooseUpgrade(EnumUpgrades upgrade) { return this; }
    @Override
    public StateInterface chooseMove() { return this; }
    @Override
    public StateInterface chooseAttack() { return this; }
    @Override
    public StateInterface manuallySetRoll(int roll) { return this; }
    @Override
    public StateInterface chooseSetTrap() { return this; }
    @Override
    public StateInterface selectTrap(EnumTraps trap) { return this; }
    @Override
    public StateInterface chooseSealRoom() { return this; }
    @Override
    public StateInterface selectRoom(int room_number) { return this; }
    @Override
    public StateInterface chooseSacrificeRedShirt() { return this; }
    @Override
    public StateInterface restartGame() { return this; }

    @Override
    public EnumCrewMembers getMovingCrewMember() { return null; }
}