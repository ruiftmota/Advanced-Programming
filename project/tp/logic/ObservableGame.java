package tp.logic;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import tp.logic.Game;
import tp.logic.LoadGame;
import tp.logic.Save;
import tp.logic.state_machine.StateInterface;
import tp.logic.enumerations.EnumCrewMembers;
import tp.logic.enumerations.EnumTraps;
import tp.logic.enumerations.EnumUpgrades;
import tp.logic.game_data.aliens.Alien;
import tp.logic.game_data.crew_members.CrewMember;
import tp.logic.game_data.rooms.Room;
import tp.logic.game_data.traps.Trap;

public class ObservableGame extends PropertyChangeSupport
{
    private static final long serialVersionUID = 1L;
    
    private Game game_logic = null;

    public ObservableGame(Game game_logic)
    {
        super(game_logic);
        this.game_logic = game_logic;
        return;
    }

    public StateInterface getState() { return game_logic.getState(); }

    public void loadGame()
    {
        LoadGame load_game = new LoadGame();

        this.game_logic = load_game.loadGame();

        return;
    }
    public void saveGame()
    {
        Save save_game = new Save();
        save_game.saveGameData(game_logic);

        return;
    }

    public ArrayList<Alien> getAliens() { return game_logic.getAliens(); }
    public ArrayList<CrewMember> getCrewMembers() { return game_logic.getCrewMembers(); }
    public ArrayList<Trap> getTraps() { return game_logic.getTraps(); }
    public ArrayList<Room> getRooms() { return game_logic.getRooms(); }
    public int getMemberSelectionCounter() { return game_logic.getMemberSelectionCounter(); }
    public int getMaximumHull() { return game_logic.getMaximumHull(); }
    public int getMaximumHealth() { return game_logic.getMaximumHealth(); }
    public int getNumberOfSealRoomToken(){ return game_logic.getNumberOfSealRoomToken(); }

    /* Ruben */
    public int[] getJourneyTracker() { return game_logic.getJourneyTracker(); }
    public int getRoundNumber() { return game_logic.getRoundNumber(); }
    public int getHullTracker() { return game_logic.getHullTrackerTocken(); }
    public int getHealthTracker() { return game_logic.getHealthTrackerToken(); }
    public int getInspirationPoints() { return game_logic.getInspirationPoints(); }
    public int getActionPoints() { return game_logic.getActionPoints(); }
    public int getAddToResultOfAttackDice() { return game_logic.getAddToResultOfAttackDice(); }
    public int getLastDiceValue() { return game_logic.getLastDiceValue(); }
    /*       */

    public EnumCrewMembers getMovingCrewMember(){ return game_logic.getMovingCrewMember(); }

    public void chooseRandom()
    {
        game_logic.chooseRandom();
        firePropertyChange(null, false, true);
        return;
    }
    public void chooseManual()
    {
        game_logic.chooseManual();
        firePropertyChange(null, false, true);
        return;
    }
    public void chooseMember(EnumCrewMembers member)
    {
        game_logic.chooseMember(member);
        firePropertyChange(null, false, true);
        return;
    }
    public void setMemberPosition(int room_number)
    {
        game_logic.setMemberPosition(room_number);
        firePropertyChange(null, false, true);
        return;
    }
    public void setToken(int position)
    {
        game_logic.setToken(position);
        firePropertyChange(null, false, true);
        return;
    }
    public void nextRound()
    {
        game_logic.nextRound();
        firePropertyChange(null, false, true);
        return;
    }
    public void chooseDetonate()
    {
        game_logic.chooseDetonate();
        firePropertyChange(null, false, true);
        return;
    }
    public void chooseRepairHull()
    {
        game_logic.chooseRepairHull();
        firePropertyChange(null, false, true);
        return;
    }
    public void chooseHealPlayer()
    {
        game_logic.chooseHealPlayer();
        firePropertyChange(null, false, true);
        return;
    }
    public void chooseUpgrade(EnumUpgrades upgrade)
    {
        game_logic.chooseUpgrade(upgrade);
        firePropertyChange(null, false, true);
        return;
    }
    public void chooseMove()
    {
        game_logic.chooseMove();
        firePropertyChange(null, false, true);
        return;
    }
    public void chooseAttack()
    {
        game_logic.chooseAttack();
        firePropertyChange(null, false, true);
        return;
    }
    public void manuallySetRoll(int roll)
    {
        game_logic.manuallySetRoll(roll);
        firePropertyChange(null, false, true);
        return;
    }
    public void chooseSetTrap()
    {
        game_logic.chooseSetTrap();
        firePropertyChange(null, false, true);
        return;
    }
    public void selectTrap(EnumTraps trap)
    {
        game_logic.selectTrap(trap);
        firePropertyChange(null, false, true);
        return;
    }
    public void chooseSealRoom()
    {
        game_logic.chooseSealRoom();
        firePropertyChange(null, false, true);
        return;
    }
    public void selectRoom(int room_number)
    {
        game_logic.selectRoom(room_number);
        firePropertyChange(null, false, true);
        return;
    }
    public void restartGame()
    {
        game_logic.restartGame();
        firePropertyChange(null, false, true);
        return;
    }
    public void chooseSacrificeRedShirt()
    {
        game_logic.chooseSacrificeRedShirt();
        firePropertyChange(null, false, true);
        return;
    }
}