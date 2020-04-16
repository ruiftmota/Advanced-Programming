package tp.logic.game_data;

import java.io.Serializable;
import java.util.ArrayList;
import tp.logic.game_data.crew_members.*;
import tp.logic.enumerations.*;
import tp.logic.game_data.aliens.*;
import tp.logic.game_data.rooms.*;
import tp.logic.game_data.traps.*;


public class GameData implements Serializable{

    private ArrayList<Alien> aliens = new ArrayList<Alien>();
    private ArrayList<Room> rooms = new ArrayList<Room>();
    private ArrayList<CrewMember> crew_members = new ArrayList<CrewMember>();
    private ArrayList<Trap> traps = new ArrayList<Trap>();
    
    private int hull_tracker_token = 8, maximum_hull = 12;
    private int health_tracker_token = 8, maximum_health = 12;
    private int round_number = 0;
    private int[] journey_tracker = {2, 3, 4, -5, 0, 4, 5, -6, 0, 6, -7, 0, 8};
    private int action_points = 5, maximum_action_points = 5;
    private int inspiration_points = 0;
    private int seal_room_tokens = 0;
    private int add_to_result_of_attack_dice = 0;
    private int last_value_of_dice = 0;
    private boolean win = true;

    private int member_selection_counter = 0;

    public GameData()
    {
        createRooms();
        spawnAliens(journey_tracker[0]);
    }

    public GameData getGameData() { return this; }

    public int getNumberOfAliens(){ return aliens.size(); }
    public int getAlienRoom(int pos){ 
        System.out.println("Size: " + aliens.size());
        for(int i = 0; i < aliens.size(); i++)
        {
            System.out.println("Alien ID: " + aliens.get(i).getId());
            System.out.println("      Room: " + aliens.get(i).getRoom().getName());
        }
        return aliens.get(pos).getRoom().getId(); }
    public int getAlienId(int pos){ return aliens.get(pos).getId(); }
    public ArrayList<Alien> getAliens() { return new ArrayList<Alien>(aliens); }

    public int getNumberOfCrewMembers(){ return crew_members.size(); }
    public String getCrewMemberName(int pos){ return crew_members.get(pos).getName(); }
    public int getCrewMemberMovement(int pos){ return crew_members.get(pos).getMovement(); }
    public int getCrewMemberNumberOfAttacks(int pos){ return crew_members.get(pos).getNumberOfAttacks(); }
    public int getCrewMemberId(int pos){ return crew_members.get(pos).getId(); }
    public int getCrewMemberRoom(int pos){ return crew_members.get(pos).getRoom().getId(); }
    public ArrayList<CrewMember> getCrewMembers() { return new ArrayList<CrewMember>(crew_members); }

    public int getNumberOfTraps(){ return traps.size(); }
    public int getTrapId(int pos){ return traps.get(pos).getId(); }
    public int getTrapRoom(int pos){
        if(traps.get(pos).getRoom() == null)
            return 0;
        return traps.get(pos).getRoom().getId(); 
    }
    public ArrayList<Trap> getTraps() { return new ArrayList<Trap>(traps); }

    public ArrayList<Room> getRooms() { return new ArrayList<Room>(rooms); }

    public int getHullTrackerTocken(){ return hull_tracker_token; }
    public int getHealthTrackerToken(){ return health_tracker_token; }
    public int getRoundNumber(){ return round_number; }
    public int getActionPoints(){ return action_points; }
    public int getMaximumActionPoints() { return maximum_action_points; }
    public int getInspirationPoints(){ return inspiration_points; }
    public int getAddToResultOfAttackDice() { return  add_to_result_of_attack_dice; }
    public int getNumberOfSealRoomToken(){ return seal_room_tokens; }
    public int getMemberSelectionCounter() { return member_selection_counter; }
    public int getMaximumHull() { return maximum_hull; }
    public int getMaximumHealth() { return maximum_health; }

    /* Ruben */
    public int[] getJourneyTracker() { return journey_tracker; }
    public int getLastDiceValue() { return last_value_of_dice; }
    /*       */

    private void createRooms()
    {
        rooms.add(new Bridge());
        rooms.add(new SickBay());
        rooms.add(new Brig());
        rooms.add(new CrewQuarters());
        rooms.add(new ConferenceRoom());
        rooms.add(new ShuttleBay());
        rooms.add(new WeaponsBay());
        rooms.add(new MessHall());
        rooms.add(new Engineering());
        rooms.add(new Astrometrics());
        rooms.add(new Holodeck());
        rooms.add(new Hydroponics());
    }
    private void spawnAliens(int number)
    {
        int spawning_room_index = 0;

        for(int i = 0; i < number; i++)
        {
            if(aliens.size() < 15)
            {
                spawning_room_index = (int)(Math.random() * (rooms.size() - 1)) + 0;
                aliens.add(new Alien(rooms.get(spawning_room_index)));
            }
        }

        return;
    }
    private boolean validCrewMember(EnumCrewMembers member)
    {
        for(int i = 0; i < crew_members.size(); i++)
        {
            if(crew_members.get(i).getId() == member.getNumber())
            {
                return true;
            }
        }

        return false;
    }
    private CrewMember convertEnumCrewMemberToObject(EnumCrewMembers member)
    {
        for(int i = 0; i < crew_members.size(); i++)
        {
            if(crew_members.get(i).getId() == member.getNumber())
            {
                return crew_members.get(i);
            }
        }

        return null;
    }
    /*private Room seeIfAliensInAdjacentRoom(Room room)
    {
        int number_of_adjacent_rooms = room.getAdjacentRooms().size();

        for(int i = 0; i < number_of_adjacent_rooms; i++)
        {
            if(seeIfAliensInRoom(rooms.get(room.getAdjacentRooms.get(i).intValue())) == true)
            {
                return rooms.get(room.getAdjacentRooms.get(i).intValue());
            }
        }
    }*/
    private boolean seeIfParticleDisperserInRoom(Room room)
    {
        for(int i = 0; i < traps.size(); i++)
        {
            if((traps.get(i) instanceof ParticleDisperser) && traps.get(i).getRoom() == room)
            {
                return true;
            }
        }

        return false;
    }
    
    private void upgradeAddOneToHealth()
    {
        int health_increment = 1;
        EnumUpgrades upgrade = EnumUpgrades.ADD_ONE_TO_HEALTH;

        if(inspiration_points >= upgrade.getCost())
        {
            for(int i = 0; i < crew_members.size(); i++)
            {
                if(crew_members.get(i) instanceof Doctor)
                {
                    health_increment = 2;
                    break;
                }
            }

            health_tracker_token = health_tracker_token + health_increment;
            if(health_tracker_token > maximum_health)
            {
                health_tracker_token = maximum_health;
            }

            inspiration_points = inspiration_points - upgrade.getCost();
        }

        return;
    }
    private void upgradeRepairOneHull()
    {
        int hull_increment = 1;
        EnumUpgrades upgrade = EnumUpgrades.REPAIR_ONE_HULL;

        if(inspiration_points >= upgrade.getCost())
        {
            for(int i = 0; i < crew_members.size(); i++)
            {
                if(crew_members.get(i) instanceof Engineer)
                {
                    hull_increment = 2;
                    break;
                }
            }

            hull_tracker_token = hull_tracker_token + hull_increment;
            if(hull_tracker_token > maximum_hull)
            {
                hull_tracker_token = maximum_hull;
            }

            inspiration_points = inspiration_points - upgrade.getCost();
        }

        return;
    }
    private void upgradeBuildOneOrganicDetonator()
    {
        EnumUpgrades upgrade = EnumUpgrades.BUILD_ONE_ORGANIC_DETONATOR;
        int total = 0;

        if(inspiration_points >= upgrade.getCost())
        {
            for(int i = 0; i < traps.size(); i++)
            {
                if(traps.get(i) instanceof OrganicDetonator)
                {
                    total++;
                }
            }

            if(total < 4)
            {
                traps.add(new OrganicDetonator());

                inspiration_points = inspiration_points - upgrade.getCost();
            }
        }

        return;
    }
    private void upgradeBuildOneParticleDisperser()
    {
        EnumUpgrades upgrade = EnumUpgrades.BUILD_ONE_PARTICLE_DISPERSER;
        int total = 0;

        if(inspiration_points >= upgrade.getCost())
        {
            for(int i = 0; i < traps.size(); i++)
            {
                if(traps.get(i) instanceof ParticleDisperser)
                {
                    total++;
                }
            }

            if(total < 2)
            {
                traps.add(new ParticleDisperser());

                inspiration_points = inspiration_points - upgrade.getCost();
            }
        }
    }
    private void upgradeAddOneToMovement()
    {
        CrewMember crew_member = null;
        EnumUpgrades upgrade = EnumUpgrades.ADD_ONE_TO_MOVEMENT;

        if(inspiration_points >= upgrade.getCost())
        {
            for(int i = 0; i < crew_members.size(); i++)
            {
                crew_member = crew_members.get(i);
                crew_member.setMovement(crew_member.getMovement() + 1);
            }

            inspiration_points = inspiration_points - upgrade.getCost();
        }

        return;
    }
    private void upgradeGainOneSealedRoomToken()
    {
        EnumUpgrades upgrade = EnumUpgrades.GAIN_ONE_SEALED_ROOM_TOKEN;

        if(inspiration_points >= upgrade.getCost())
        {
            seal_room_tokens++;

            inspiration_points = inspiration_points - upgrade.getCost();
        }

        return;
    }
    private void upgradeGainOneExtraAttackDie()
    {
        CrewMember crew_member = null;
        EnumUpgrades upgrade = EnumUpgrades.GAIN_ONE_EXTRA_ATTACK_DIE;

        if(inspiration_points >= upgrade.getCost())
        {
            for(int i = 0; i < crew_members.size(); i++)
            {
                crew_member = crew_members.get(i);
                crew_member.setNumberOfAttacks(crew_member.getNumberOfAttacks() + 1);
            }

            inspiration_points = inspiration_points - upgrade.getCost();
        }

        return;
    }
    private void upgradeAddOneToTheResultOfAttackDice()
    {
        EnumUpgrades upgrade = EnumUpgrades.ADD_ONE_TO_THE_RESULT_OF_ATTACK_DICE;

        if(inspiration_points >= upgrade.getCost())
        {
            add_to_result_of_attack_dice++;

            inspiration_points = inspiration_points - upgrade.getCost();
        }

        return;
    }

    private boolean alienPhase()
    {
        movementOfAliens();
        detonationOfAliens();
        destructionOfHull();
        if(hull_tracker_token <= 0)
        {
            return false;
        }
        attackMembers();
        if(health_tracker_token <= 0)
        {
            return false;
        }

        return true;
    }
    private void movementOfAliens()
    {
        ArrayList<Integer> adjacent_rooms = null;
        boolean someone_in_adjacent_room = false;
        Room room_to_go = null;
        int index_of_room_to_go = 0;
        int i = 0, j = 0, k = 0;

        for(i = 0; i < aliens.size(); i++)
        {
            adjacent_rooms = aliens.get(i).getRoom().getAdjacentRooms();
            someone_in_adjacent_room = false;
            for(j = 0; j < crew_members.size(); j++)
            {
                for(k = 0; k < adjacent_rooms.size(); k++)
                {
                    if(rooms.get(adjacent_rooms.get(k)-1) == crew_members.get(j).getRoom())
                    {
                        someone_in_adjacent_room = true;
                        break;
                    }
                }
                if(someone_in_adjacent_room == true)
                {
                    break;
                }
            }
            if(someone_in_adjacent_room == true)
            {
                room_to_go = crew_members.get(j).getRoom();
            }
            else
            {
                index_of_room_to_go = adjacent_rooms.get((int)(Math.random() * (adjacent_rooms.size() - 1)) + 0);
                room_to_go = rooms.get(index_of_room_to_go - 1);
            }
            aliens.get(i).setRoom(room_to_go);
        }
    }
    private void detonationOfAliens()
    {
        for(int i = 0; i < aliens.size(); i++)
        {
            for(int j = 0; j < traps.size(); j++)
            {
                if(aliens.get(i).getRoom() == traps.get(j).getRoom() && traps.get(j) instanceof OrganicDetonator)
                {
                    aliens.remove(i);
                    i--;
                    traps.remove(j);
                    j--;
                    break;
                }
            }
        }
    }
    private void destructionOfHull()
    {
        boolean no_crew_members_in_room = true;

        for(int i = 0; i < aliens.size(); i++)
        {
            no_crew_members_in_room = true;
            for(int j = 0; j < crew_members.size(); j++)
            {
                if(aliens.get(i).getRoom() == crew_members.get(j).getRoom())
                {
                    no_crew_members_in_room = false;
                    break;
                }
            }
            if(no_crew_members_in_room == true)
            {
                hull_tracker_token--;
            }
        }
    }
    private void attackMembers()
    {
        int dice_roll_number = 0;

        for(int i = 0; i < aliens.size(); i++)
        {
            for(int j = 0; j < crew_members.size(); j++)
            {
                if(aliens.get(i).getRoom() == crew_members.get(j).getRoom())
                {
                    dice_roll_number = last_value_of_dice = (int)(Math.random() * 6) + 1;
                    if(dice_roll_number >= 5 && crew_members.get(j) instanceof CommsOfficer == false)
                    {
                        health_tracker_token--;
                    }
                    else if(dice_roll_number >= 5 && crew_members.get(j) instanceof CommsOfficer == true)
                    {
                        dice_roll_number = last_value_of_dice = (int)(Math.random() * 6) + 1;
                        if(dice_roll_number != 1 && dice_roll_number != 2)
                        {
                            health_tracker_token--;
                        }
                    }
                }
            }
        }
    }

    public void selectMembersRandomly()
    {
        int member_to_select = 0;
        boolean validated = true;

        for(int i = 0; i < 2; i++)
        {
            do
            {
                validated = true;
                member_to_select = (int)(Math.random() * 12) + 1;
                for(EnumCrewMembers member : EnumCrewMembers.values())
                {
                    if(member.getNumber() == member_to_select)
                    {
                        if(selectMemberManually(member) == -1)
                        {
                            validated = false;
                        }
                        break;
                    }
                }
            }
            while(validated == false);
        }

        return;
    }
    public int selectMemberManually(EnumCrewMembers member)
    {
        for(int i = 0; i < crew_members.size(); i++)
        {
            if(crew_members.get(i).getId() == member.getNumber())
            {
                return -1;
            }
        }
        switch(member.getNumber())
        {
            case 1:
                crew_members.add(new Doctor());
                break;

            case 2:
                crew_members.add(new CommsOfficer());
                break;

            case 3:
                crew_members.add(new RedShirt());
                break;

            case 4:
                crew_members.add(new ScienceOfficer());
                break;

            case 5:
                crew_members.add(new Engineer());
                break;

            case 6:
                crew_members.add(new Captain());
                break;

            case 7:
                crew_members.add(new Commander());
                action_points = 6;
                maximum_action_points = 6;
                break;

            case 8:
                crew_members.add(new TransporterChief());
                break;

            case 9:
                crew_members.add(new MoralOfficer());
                inspiration_points = 5;
                break;

            case 10:
                crew_members.add(new SecurityOfficer());
                break;

            case 11:
                crew_members.add(new NavigationOfficer());
                break;

            case 12:
                crew_members.add(new ShuttlePilot());
                health_tracker_token = health_tracker_token + 4;
                break;
        }

        member_selection_counter++;
        if(member_selection_counter == 2)
        {
            member_selection_counter = 0;
            return 1;
        }
        else
        {
            return 0;
        }
    }
    
    public void selectMembersPositionRandomly()
    {
        int room_to_position_index = 0;

        for(int i = 0; i < crew_members.size(); i++)
        {
            room_to_position_index = (int)(Math.random() * (rooms.size())) + 1;
            selectMembersPositionManually(room_to_position_index);
        }

        return;
    }
    public boolean selectMembersPositionManually(int room_number)
    {
        room_number--;

        Room room_to_position = rooms.get(room_number);

        crew_members.get(member_selection_counter).setRoom(room_to_position);

        member_selection_counter++;
        if(member_selection_counter == 2)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void putTokenInHullTracker(int position)
    {
        hull_tracker_token = position;
        return;
    }
    public void putTokenInHealthTracker(int position)
    {
        health_tracker_token = position;
        return;
    }

    public void healPlayer()
    {
        for(int i = 0; i < crew_members.size(); i++)
        {
            if(crew_members.get(i) instanceof Doctor)
            {
                if(health_tracker_token < maximum_health && action_points > 0)
                {
                    health_tracker_token++;
                    action_points--;
                }
            }
        }

        return;
    }
    public void repairHull()
    {
        for(int i = 0; i < crew_members.size(); i++)
        {
            if(crew_members.get(i) instanceof Engineer)
            {
                if(hull_tracker_token < maximum_hull && action_points > 0)
                {
                    hull_tracker_token++;
                    action_points--;
                }
            }
        }

        return;
    }
    
    public void moveMemberToRoom(EnumCrewMembers member, int room_number)
    {
        CrewMember crew_member = null;
        Room actual_room = null;
        boolean can = false;
        
        if(validCrewMember(member) == true && action_points > 0)
        {
            crew_member = convertEnumCrewMemberToObject(member);
            int result = crew_member.move(rooms.get(room_number-1));
            if(result != 0){
                action_points--;
            }
        }

        return;
    }

    public void putTrapInRoom(EnumCrewMembers member, EnumTraps trap)
    {
        CrewMember crew_member = null;

        if(validCrewMember(member) == false || action_points <= 0)
        {
            return;
        }
        else
        {
            for(int i = 0; i < traps.size(); i++)
            {
                if(traps.get(i).getRoom() == null)
                {
                    if(traps.get(i).getId() == trap.getNumber())
                    {
                        crew_member = convertEnumCrewMemberToObject(member);
                        traps.get(i).setRoom(crew_member.getRoom());
                        action_points--;
                        return;
                    }
                }
            }
        }

        return;
    }

    public void sealRoom(int room_number)
    {
        room_number--;

        if(seal_room_tokens > 0 && rooms.get(room_number).getCanBeSealed() == true && action_points > 0 && emptyRoom(rooms.get(room_number)) == true)
        {
            rooms.get(room_number).setIsSealed();

            action_points--;
            seal_room_tokens--;
        }

        return;

    }
    private boolean emptyRoom(Room room)
    {
        for(int i = 0; i < crew_members.size(); i++)
        {
            if(crew_members.get(i).getRoom() == room)
            {
                return false;
            }
        }

        for(int i = 0; i < aliens.size(); i++)
        {
            if(aliens.get(i).getRoom() == room)
            {
                return false;
            }
        }

        return true;
    }

    public void attack(EnumCrewMembers member)
    {
        CrewMember crew_member = null;
        int dice_roll_number = 0, minimum_to_attack = 5;
        int result = 0;

        if(validCrewMember(member) == true && action_points > 0)
        {
            crew_member = convertEnumCrewMemberToObject(member);
            for(int i = 0; i < crew_member.getNumberOfAttacks(); i++)
            {
                dice_roll_number = last_value_of_dice = (int)(Math.random() * 6) + 1 + add_to_result_of_attack_dice;
                result = crew_member.attack(aliens, dice_roll_number + add_to_result_of_attack_dice);
                if(result == 1)
                {
                    inspiration_points++;
                }
            }
            action_points--;
        }

        return;
    }
    public void attack(EnumCrewMembers member, int dice_roll_number)
    {
        CrewMember crew_member = null;
        int minimum_to_attack = 5;
        int result = 0; 

        if(validCrewMember(member) == true && action_points > 0)
        {
            crew_member = convertEnumCrewMemberToObject(member);
            for(int i = 0; i < crew_member.getNumberOfAttacks(); i++)
            {
                result = crew_member.attack(aliens, dice_roll_number + add_to_result_of_attack_dice);
                if(result == 1){
                    action_points--;
                    inspiration_points++;
                }
            }
            action_points--;
        }
    }

    public void sacrificeRedShirt()
    {
        for(int i = 0; i < crew_members.size(); i++)
        {
            if(crew_members.get(i) instanceof RedShirt)
            {
                health_tracker_token = health_tracker_token + 5;
                if(health_tracker_token > maximum_health)
                {
                    health_tracker_token = maximum_health;
                }
            }
        }

        return;
    }

    public void detonateParticleDisperser(int room_number)
    {
        room_number--;
        Room detonating_room = rooms.get(room_number);

        if(seeIfParticleDisperserInRoom(detonating_room) == true && action_points > 0)
        {
            for(int i = 0; i < crew_members.size(); i++)
            {
                if(crew_members.get(i).getRoom() == detonating_room)
                {
                    crew_members.remove(i);
                    i--;
                    health_tracker_token = 0;
                }
            }
            for(int i = 0; i < aliens.size(); i++)
            {
                if(aliens.get(i).getRoom() == detonating_room)
                {
                    aliens.remove(i);
                    i--;
                }
            }

            action_points--;
        }
    }

    public void upgrade(EnumUpgrades upgrade)
    {
        switch(upgrade)
        {
            case ADD_ONE_TO_HEALTH:
                upgradeAddOneToHealth();
                break;

            case REPAIR_ONE_HULL:
                upgradeRepairOneHull();
                break;

            case BUILD_ONE_ORGANIC_DETONATOR:
                upgradeBuildOneOrganicDetonator();
                break;
                
            case BUILD_ONE_PARTICLE_DISPERSER:
                upgradeBuildOneParticleDisperser();
                break;

            case ADD_ONE_TO_MOVEMENT:
                upgradeAddOneToMovement();
                break;

            case GAIN_ONE_SEALED_ROOM_TOKEN:
                upgradeGainOneSealedRoomToken();
                break;

            case GAIN_ONE_EXTRA_ATTACK_DIE:
                upgradeGainOneExtraAttackDie();
                break;

            case ADD_ONE_TO_THE_RESULT_OF_ATTACK_DICE:
                upgradeAddOneToTheResultOfAttackDice();
                break;
        }

        return;
    }

    public EnumNextRound nextRound()
    {
        EnumNextRound next_round;

        if(health_tracker_token <= 0 || hull_tracker_token <= 0)
        {
            win = false;
            next_round = EnumNextRound.END_GAME;
        }
        else if(round_number == journey_tracker.length - 1)
        {
            next_round = EnumNextRound.END_GAME;
        }
        else if(journey_tracker[round_number + 1] == 0)
        {
            if(alienPhase() == false)
            {
                win = false;
                next_round = EnumNextRound.END_GAME;
            }
            else
            {
                aliens.clear();
                next_round = EnumNextRound.CHOOSE_UPGRADE;
            }
        }
        else
        {
            if(alienPhase() == false)
            {
                win = false;
                next_round = EnumNextRound.END_GAME;
            }
            else
            {
                next_round = EnumNextRound.CHOOSE_ACTION;
                spawnAliens(journey_tracker[round_number + 1]);
            }
        }

        round_number++;
        action_points = maximum_action_points;
        return next_round;
    }

    @Override
    public String toString()
    {
        StringBuilder string = new StringBuilder();
        int i = 0, j = 0;

        string.append("Selected Crew Members: ");
        for(i = 0; i < crew_members.size(); i++)
        {
            string.append(crew_members.get(i).getName()); string.append(", ");
        }
        string.append("\n\n");

        string.append("Organic Detonators mounted: ");
        for(j = 0, i = 0; i < traps.size(); i++)
        {
            if(traps.get(i).getRoom() == null && traps.get(i) instanceof OrganicDetonator)
            {
                j++;
            }
        }
        string.append(j); string.append("\n");

        string.append("Particle Dispersers mounted: ");
        for(j = 0, i = 0; i < traps.size(); i++)
        {
            if(traps.get(i).getRoom() == null && traps.get(i) instanceof ParticleDisperser)
            {
                j++;
            }
        }
        string.append(j); string.append("\n\n");

        string.append("Health Tracker: "); string.append(health_tracker_token); string.append("\n");
        string.append("Hull Tracker: "); string.append(hull_tracker_token); string.append("\n");
        string.append("Inspiration Points: "); string.append(inspiration_points); string.append("\n");
        string.append("Action Points: "); string.append(action_points); string.append("\n");
        string.append("Seal Room Tokens: "); string.append(seal_room_tokens); string.append("\n");
        string.append("Add one to the result of the attack dice: "); string.append(add_to_result_of_attack_dice); string.append("\n\n");

        string.append("Round number: "); string.append(round_number); string.append("\n");
        string.append("Journey Tracker: "); 
        string.append("S, ");
        for(i = 0; i < journey_tracker.length; i++)
        {
            string.append(journey_tracker[i]); string.append(", ");
        }
        string.append("E"); string.append("\n\n");

        string.append("Rooms:\n");
        for(i = 0; i < rooms.size(); i++)
        {
            string.append(rooms.get(i).getId()); string.append(". "); string.append(rooms.get(i).getName()); string.append(": ");
            for(j = 0; j < crew_members.size(); j++)
            {
                if(crew_members.get(j).getRoom() == rooms.get(i))
                {
                    string.append(crew_members.get(j).getName()); string.append(", ");
                }
            }
            for(j = 0; j < traps.size(); j++)
            {
                if(traps.get(j).getRoom() == rooms.get(i))
                {
                    string.append(traps.get(j).getName()); string.append(", ");
                }
            }
            for(j = 0; j < aliens.size(); j++)
            {
                if(aliens.get(j).getRoom() == rooms.get(i))
                {
                    string.append("Alien "); string.append(aliens.get(j).getId()); string.append(", ");
                }
            }
            string.append("\n\n");
        }

        if(win == false)
        {
            string.append("YOU LOST. GAME OVER\n");
        }
        else if(win == true && round_number == journey_tracker.length)
        {
            string.append("YOU WON. GAME OVER\n");
        }

        return string.toString();
    }
}