package tp.logic;

import java.io.Serializable;
import java.util.ArrayList;

import tp.logic.state_machine.StateInterface;
import tp.logic.state_machine.states.MemberSelectionMode;
import tp.logic.enumerations.EnumCrewMembers;
import tp.logic.enumerations.EnumTraps;
import tp.logic.enumerations.EnumUpgrades;
import tp.logic.game_data.GameData;
import tp.logic.game_data.aliens.Alien;
import tp.logic.game_data.crew_members.CrewMember;
import tp.logic.game_data.rooms.Room;
import tp.logic.game_data.traps.Trap;

public class Game implements Serializable
{
    StateInterface actual_state = null;
    GameData game_data = null;
    
    public Game(){
        
        game_data = new GameData();

        actual_state = new MemberSelectionMode(game_data);

        return;
    }

    /* ----------------------------------------------------------------------------------------------------- */

    private void setState(StateInterface new_state)
    {
        actual_state = new_state;
        return;
    }
    public StateInterface getState() { return actual_state; }

    public int getNumberOfAliens(){ return game_data.getNumberOfAliens(); }
    public int getAlienRoom(int pos){ return game_data.getAlienRoom(pos); }
    public int getAlienId(int pos){ return game_data.getAlienId(pos); }
    public ArrayList<Alien> getAliens() { return game_data.getAliens(); }

    public int getNumberOfCrewMembers(){ return game_data.getNumberOfCrewMembers(); }
    public String getCrewMemberName(int pos){ return game_data.getCrewMemberName(pos); }
    public int getCrewMemberMovement(int pos){ return game_data.getCrewMemberMovement(pos); }
    public int getCrewMemberNumberOfAttacks(int pos){ return game_data.getCrewMemberNumberOfAttacks(pos); }
    public int getCrewMemberId(int pos){ return game_data.getCrewMemberId(pos); }
    public int getCrewMemberRoom(int pos){ return game_data.getCrewMemberRoom(pos); }
    public ArrayList<CrewMember> getCrewMembers() { return game_data.getCrewMembers(); }

    public int getNumberOfTraps(){ return game_data.getNumberOfTraps(); }
    public int getTrapId(int pos){ return game_data.getTrapId(pos); }
    public int getTrapRoom(int pos){ return game_data.getTrapRoom(pos); }
    public ArrayList<Trap> getTraps() { return game_data.getTraps(); }

    public ArrayList<Room> getRooms() { return game_data.getRooms(); }
    
    public int getHullTrackerTocken(){ return game_data.getHullTrackerTocken(); }
    public int getHealthTrackerToken(){ return game_data.getHealthTrackerToken(); }
    public int getRoundNumber(){ return game_data.getRoundNumber(); }
    public int getActionPoints(){ return game_data.getActionPoints(); }
    public int getMaximumActionPoints() { return game_data.getMaximumActionPoints(); }
    public int getInspirationPoints(){ return game_data.getInspirationPoints(); }
    public int getAddToResultOfAttackDice() { return game_data.getAddToResultOfAttackDice(); }
    public int getNumberOfSealRoomToken(){ return game_data.getNumberOfSealRoomToken(); }
    public int getMemberSelectionCounter() { return game_data.getMemberSelectionCounter(); }
    public int getMaximumHull() { return game_data.getMaximumHull(); }
    public int getMaximumHealth() { return game_data.getMaximumHealth(); }

    /* Ruben */
    public int[] getJourneyTracker() { return game_data.getJourneyTracker(); }
    public int getLastDiceValue() { return game_data.getLastDiceValue(); }
    /*       */

    public EnumCrewMembers getMovingCrewMember(){ 
        return actual_state.getMovingCrewMember();
    }

    /* ------------------------------------------------------------------------------------------------------ */

    public void chooseRandom()
    {
        setState(actual_state.chooseRandom());
        return;
    }
    public void chooseManual()
    {
        setState(actual_state.chooseManual());
        return;
    }
    public void chooseMember(EnumCrewMembers member)
    {
        setState(actual_state.chooseMember(member));
        return;
    }
    public void setMemberPosition(int room_number)
    {
        setState(actual_state.setMemberPosition(room_number));
        return;
    }
    public void setToken(int position)
    {
        setState(actual_state.setToken(position));
        return;
    }
    public void nextRound()
    {
        setState(actual_state.nextRound());
        return;
    }
    public void chooseDetonate()
    {
        setState(actual_state.chooseDetonate());
        return;
    }
    public void chooseRepairHull()
    {
        setState(actual_state.chooseRepairHull());
        return;
    }
    public void chooseHealPlayer()
    {
        setState(actual_state.chooseHealPlayer());
        return;
    }
    public void chooseUpgrade(EnumUpgrades upgrade)
    {
        setState(actual_state.chooseUpgrade(upgrade));
        return;
    }
    public void chooseMove()
    {
        setState(actual_state.chooseMove());
        return;
    }
    public void chooseAttack()
    {
        setState(actual_state.chooseAttack());
        return;
    }
    public void manuallySetRoll(int roll)
    {
        setState(actual_state.manuallySetRoll(roll));
        return;
    }
    public void chooseSetTrap()
    {
        setState(actual_state.chooseSetTrap());
        return;
    }

    public void selectTrap(EnumTraps trap)
    {
        setState(actual_state.selectTrap(trap));
        return;
    }
    
    public void chooseSealRoom()
    {
        setState(actual_state.chooseSealRoom());
        return;
    }
    public void selectRoom(int room_number)
    {
        setState(actual_state.selectRoom(room_number));
        return;
    }

    public void restartGame()
    {
        setState(actual_state.restartGame());
        return;
    }

    public void chooseSacrificeRedShirt()
    {
        setState(actual_state.chooseSacrificeRedShirt());
        return;
    }

    @Override
    public String toString()
    {
        return game_data.toString();
    }
}