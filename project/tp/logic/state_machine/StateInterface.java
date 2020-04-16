package tp.logic.state_machine;

import tp.logic.enumerations.*;

public interface StateInterface
{
    StateInterface chooseRandom();
    StateInterface chooseManual();
    StateInterface chooseMember(EnumCrewMembers member);
    StateInterface setMemberPosition(int room_number);
    StateInterface setToken(int position);
    StateInterface nextRound();
    StateInterface chooseDetonate();
    StateInterface chooseRepairHull();
    StateInterface chooseHealPlayer();
    StateInterface chooseUpgrade(EnumUpgrades upgrade);
    StateInterface chooseMove();
    StateInterface chooseAttack();
    StateInterface manuallySetRoll(int roll);
    StateInterface chooseSetTrap();
    StateInterface selectTrap(EnumTraps trap);
    StateInterface chooseSealRoom();
    StateInterface selectRoom(int room_number);
    StateInterface chooseSacrificeRedShirt();
    StateInterface restartGame();

    EnumCrewMembers getMovingCrewMember();
}