package tp.text_interface;

import java.util.Scanner;

import tp.logic.Game;
import tp.logic.state_machine.states.*;
import tp.logic.state_machine.StateInterface;
import tp.logic.enumerations.*;

public class TextInterface
{
    private Game game_logic = null;
    private boolean exit_game = false;
    private Scanner scanf = new Scanner(System.in);

    public TextInterface()
    {
        this.game_logic = new Game();
        return;
    }

    private void clearScreen()
    {  
        for (int i = 0; i < 50; ++i) System.out.println();
        
        System.out.println("DESTINATION EARTH\n");
    } 

    private int robustInput()
    {
        int input = -1;
        String buffer = scanf.next();
        scanf.useDelimiter("\r\n");

        for(int i = 0; i < buffer.length(); i++)
        {
            if(buffer.charAt(i) < '0' || buffer.charAt(i) > '9')
            {
                return -1;
            }
        }
        input = Integer.parseInt(buffer);

        return input;
    }

    public void runGame()
    {
        StateInterface actual_state = null;

        clearScreen();
        UI_chooseToLoadGame();
        clearScreen();

        while(exit_game == false)
        {
            actual_state = game_logic.getState();

            if(actual_state instanceof StartEnd){ UI_startEnd(); }
            else if(actual_state instanceof MemberSelectionMode) { UI_memberSelectionMode(); }
            else if(actual_state instanceof SelectMember) { UI_selectMember(); }
            else if(actual_state instanceof MemberPositionSelectionMode) { UI_memberPositionSelectionMode(); }
            else if(actual_state instanceof HullTrackerTokenLocationMode) { UI_hullTrackerTokenLocationMode(); }
            else if(actual_state instanceof PutTokenInHullTracker) { UI_putTokenInHullTracker(); }
            else if(actual_state instanceof HealthTrackerTokenLocationMode) { UI_healthTrackerTokenLocationMode(); }
            else if(actual_state instanceof PutTokenInHealthTracker) { UI_putTokenInHealthTracker(); }
            else if(actual_state instanceof ChooseAction) { UI_chooseAction(); }
            else if(actual_state instanceof ChooseIfDebug) { UI_chooseIfDebug(); }
            else if(actual_state instanceof ChooseMemberToMove) { UI_chooseMemberToMove(); }
            else if(actual_state instanceof ChooseWhereToMoveMember) { UI_chooseWhereToMoveMember(); }
            else if(actual_state instanceof ChooseMemberWhoAttacks) { UI_chooseMemberWhoAttacks(); }
            else if(actual_state instanceof ChooseMemberWhoPutsTrap) { UI_chooseMemberWhoPutsTrap(); }
            else if(actual_state instanceof ChooseRollNumber) { UI_chooseRollNumber(); }
            else if(actual_state instanceof ChooseRoomToSeal) { UI_chooseRoomToSeal(); }
            else if(actual_state instanceof ChooseUpgrade) { UI_chooseUpgrade(); }
            else if(actual_state instanceof SelectMemberPosition) { UI_selectMemberPosition(); }
            else if(actual_state instanceof ChooseWhichTrapToPut) { UI_chooseWhichTrapToPut(); }
            else if(actual_state instanceof WhichTrapToDetonate) { UI_whichTrapToDetonate(); }

            clearScreen();
            System.out.println("--------------------------------\n" + game_logic + "------------------------\n");
        }
        scanf.close();
        return;
    }

    private void UI_chooseToLoadGame()
    {
        int option = 0;

        System.out.println("Do you want to load the game?\n");
        System.out.println("1. Yes\n" + "0. No\n");
        System.out.print("Enter a value between 0 and 1: ");

        do
        {
            option = robustInput();
            if(option < 0 || option > 1)
            {
                System.out.print("Enter a value between 0 and 1: ");
            }
        } while(option < 0 || option > 1);

        switch(option)
        {
            case 0:
                break;

            case 1:
                game_logic.loadGame();
                break;
        }

        return;
    }

    private void UI_startEnd()
    {
        int option = 0;

        System.out.println("Do you want to save the game?\n");
        System.out.println("1. Yes\n" + "0. No\n");
        System.out.print("Enter a value between 0 and 1: ");

        do
        {
            option = robustInput();
            if(option < 0 || option > 1)
            {
                System.out.print("Enter a value between 0 and 1: ");
            }
        } while(option < 0 || option > 1);

        switch(option)
        {
            case 0:
                break;

            case 1:
                game_logic.saveGame();
                break;
        }

        exit_game = true;

        return;
    }

    private void UI_memberSelectionMode()
    {
        int option = 0;

        System.out.println("How do you want to choose your members?\n");
        System.out.println("1. Randomly\n" + "2. Manually\n\n" + "0. Exit Game\n");
        System.out.print("Enter a value between 0 and 2: ");
        do
        {
            option = robustInput();
            if(option < 0 || option > 2)
            {
                System.out.print("Enter a value between 0 and 2: ");
            }
        } while(option < 0 || option > 2);

        switch(option)
        {
            case 0:
                game_logic.restartGame();
                break;

            case 1:
                game_logic.chooseRandom();
                break;

            case 2:
                game_logic.chooseManual();
                break;
        }

        return;
    }

    private void UI_memberPositionSelectionMode()
    {
        int option = 0;

        System.out.println("How do you want to position your members?\n");
        System.out.println("1. Randomly\n" + "2. Manually\n\n" + "0. Exit Game\n");
        System.out.print("Enter a value between 0 and 2: ");

        do
        {
            option = robustInput();
            if(option < 0 || option > 2)
            {
                System.out.print("Enter a value between 0 and 2: ");
            }
        } while(option < 0 || option > 2);

        switch(option)
        {
            case 0:
                game_logic.restartGame();
                break;

            case 1:
                game_logic.chooseRandom();
                break;

            case 2:
                game_logic.chooseManual();
                break;
        }

        return;
    }
    
    private void UI_selectMember()
    {
        int option = 0;

        System.out.println("Select the member:\n");
        System.out.println("1. Doctor\n" + "2. Comm's Officer\n" + "3. Red Shirt\n" +
                           "4. Science Officer\n" + "5. Engineer\n" + "6. Captain\n" +
                           "7. Commander\n" + "8. Transporter Chief\n" + "9. Moral Officer\n" +
                           "10. Security Officer\n" + "11. Navigation Officer\n" + "12. Shuttle Pilot\n\n" +
                           "0. Exit Game\n");
        System.out.print("\nEnter a value between 0 and 12: ");
        do
        {
            option = robustInput();
            if(option < 0 || option > 12)
            {
                System.out.print("Enter a value between 0 and 12: ");
            }
        } while(option < 0 || option > 12);

        switch(option)
        {
            case 0:
                game_logic.restartGame();
                break;

            default:
                for(EnumCrewMembers member : EnumCrewMembers.values())
                {
                    if(member.getNumber() == option)
                    {
                        game_logic.chooseMember(member);
                        break;
                    }
                }
                break;
                
        }

        return;
    }

    private void UI_hullTrackerTokenLocationMode()
    {
        int option = 0;

        System.out.println("How do you want to put the Hull Tracker Token?:\n");
        System.out.println("1. Randomly\n" + "2. Manually\n\n" + "0. Exit Game\n");
        System.out.print("Enter a value between 0 and 2: ");
        do
        {
            option = robustInput();
            if(option < 0 || option > 2)
            {
                System.out.print("Enter a value between 0 and 2: ");
            }
        } while(option < 0 || option > 2);

        switch(option)
        {
            case 0:
                game_logic.restartGame();
                break;

            case 1:
                game_logic.chooseRandom();
                break;

            case 2:
                game_logic.chooseManual();
                break;
        }

        return;
    }

    private void UI_putTokenInHullTracker()
    {
        int option = 0;

        System.out.println("Type the position of the Hull Tracker (1 to 10) or exit the game(0): ");
        do
        {
            option = robustInput();
            if(option < 0 || option > 10)
            {
                System.out.print("Enter a value between 0 and 10: ");
            }
        } while(option < 0 || option > 10);

        if(option == 0)
        {
            game_logic.restartGame();
        }
        else
        {
            game_logic.setToken(option);
        }

        return;
    }

    private void UI_healthTrackerTokenLocationMode()
    {
        int option = 0;

        System.out.println("How do you want to put the Health Tracker Token?:\n");
        System.out.println("1. Randomly\n" + "2. Manually\n\n" + "0. Exit Game\n");
        System.out.print("Enter a value between 0 and 2: ");
        do
        {
            option = robustInput();
            if(option < 0 || option > 2)
            {
                System.out.print("Enter a value between 0 and 2: ");
            }
        } while(option < 0 || option > 2);

        switch(option)
        {
            case 0:
                game_logic.restartGame();
                break;

            case 1:
                game_logic.chooseRandom();
                break;

            case 2:
                game_logic.chooseManual();
                break;
        }

        return;
    }

    private void UI_putTokenInHealthTracker()
    {
        int option = 0;

        System.out.println("Type the position of the Health Tracker (1 to 10) or exit the game(0): ");
        do
        {
            option = robustInput();
            if(option < 0 || option > 10)
            {
                System.out.print("Enter a value between 0 and 10: ");
            }
        } while(option < 0 || option > 10);

        if(option == 0)
        {
            game_logic.restartGame();
        }
        else
        {
            game_logic.setToken(option);
        }

        return;
    }

    private void UI_chooseAction()
    {
        int option = 0;

        System.out.println("Choose the action you want to execute:\n");
        System.out.println("1. Continue\n" + "2. Move\n" + "3. Attack\n" + 
                           "4. Heal One Health\n" + "5. Fix One Hull\n" +
                           "6. Set Trap\n" + "7. Detonate Particle Disperser\n" +
                           "8. Seal Room\n" + "9. Sacrifice Red Shirt\n\n" + "0. Exit Game\n");
        System.out.print("\nEnter a value between 0 and 9: ");

        do
        {
            option = robustInput();
            if(option < 0 || option > 9)
            {
                System.out.print("Enter a value between 0 and 9: ");
            }
        } while(option < 0 || option > 9);

        switch(option)
        {
            case 0:
                game_logic.restartGame();
                break;

            case 1:
                game_logic.nextRound();
                break;

            case 2:
                game_logic.chooseMove();
                break;

            case 3:
                game_logic.chooseAttack();
                break;

            case 4:
                game_logic.chooseHealPlayer();
                break;

            case 5:
                game_logic.chooseRepairHull();
                break;

            case 6:
                game_logic.chooseSetTrap();
                break;

            case 7:
                game_logic.chooseDetonate();
                break;

            case 8:
                game_logic.chooseSealRoom();
                break;
            case 9:
                game_logic.chooseSacrificeRedShirt();
                break;    
        }

        return;
    }

    private void UI_chooseIfDebug()
    {
        int option = 0;

        System.out.println("How do you want to roll the dice?\n");
        System.out.println("1. Randomly\n" + "2. Manually\n\n" + "0. Exit Game\n");
        System.out.print("Enter a value between 0 and 2: ");
        do
        {
            option = robustInput();
            if(option < 0 || option > 3)
            {
                System.out.print("Enter a value between 0 and 2: ");
            }
        } while(option < 0 || option > 2);

        switch(option)
        {
            case 0:
                game_logic.restartGame();
                break;

            case 1:
                game_logic.chooseRandom();
                break;

            case 2:
                game_logic.chooseManual();
                break;
        }

        return;
    }

    private void UI_chooseMemberToMove()
    {
        int option = 0;

        System.out.println("Which member do you want to move?:\n");
        System.out.println("1. Doctor\n" + "2. Comm's Officer\n" + "3. Red Shirt\n" +
                           "4. Science Officer\n" + "5. Engineer\n" + "6. Captain\n" +
                           "7. Commander\n" + "8. Transporter Chief\n" + "9. Moral Officer\n" +
                           "10. Security Officer\n" + "11. Navigation Officer\n" + "12. Shuttle Pilot\n\n" +
                           "0. Exit Game\n");
        System.out.print("\nEnter a value between 0 and 12: ");
        do
        {
            option = robustInput();
            if(option < 0 || option > 12)
            {
                System.out.print("Enter a value between 0 and 12: ");
            }
        } while(option < 0 || option > 12);

        switch(option)
        {
            case 0:
                game_logic.restartGame();
                break;

            default:
                for(EnumCrewMembers member : EnumCrewMembers.values())
                {
                    if(member.getNumber() == option)
                    {
                        game_logic.chooseMember(member);
                        break;
                    }
                }
                break;
        }

        return;
    }

    private void UI_chooseWhereToMoveMember()
    {
        int option = 0;

        System.out.println("Where do you want to move the member?:\n");
        System.out.println("1. Bridge\n" + "2. Sick Bay\n" + "3. Brig\n" +
                           "4. Crew Quarters\n" + "5. Conference Room\n" + "6. Shuttle Bay\n" +
                           "7. Weapon's Bay\n" + "8. Mess Hall\n" + "9. Engineering\n" +
                           "10. Astrometrics\n" + "11. Holodeck\n" + "12. Hydroponics\n\n" +
                           "0. Exit Game\n");
        System.out.print("\nEnter a value between 0 and 12: ");
        do
        {
            option = robustInput();
            if(option < 0 || option > 12)
            {
                System.out.print("Enter a value between 0 and 12: ");
            }
        } while(option < 0 || option > 12);

        if(option == 0)
        {
            game_logic.restartGame();
        }
        else
        {
            game_logic.selectRoom(option);
        }

        return;
    }

    private void UI_chooseMemberWhoAttacks()
    {
        int option = 0;

        System.out.println("Which member do you want to attack an alien?:\n");
        System.out.println("1. Doctor\n" + "2. Comm's Officer\n" + "3. Red Shirt\n" +
                           "4. Science Officer\n" + "5. Engineer\n" + "6. Captain\n" +
                           "7. Commander\n" + "8. Transporter Chief\n" + "9. Moral Officer\n" +
                           "10. Security Officer\n" + "11. Navigation Officer\n" + "12. Shuttle Pilot\n\n" +
                           "0. Exit Game\n");
        System.out.print("\nEnter a value between 0 and 12: ");
        do
        {
            option = robustInput();
            if(option < 0 || option > 12)
            {
                System.out.print("Enter a value between 0 and 12: ");
            }
        } while(option < 0 || option > 12);

        switch(option)
        {
            case 0:
                game_logic.restartGame();
                break;

            default:
                for(EnumCrewMembers member : EnumCrewMembers.values())
                {
                    if(member.getNumber() == option)
                    {
                        game_logic.chooseMember(member);
                        break;
                    }
                }
                break;
        }

        return;
    }

    private void UI_chooseMemberWhoPutsTrap()
    {
        int option = 0;

        System.out.println("Which member do you want to put a trap?:\n");
        System.out.println("1. Doctor\n" + "2. Comm's Officer\n" + "3. Red Shirt\n" +
                           "4. Science Officer\n" + "5. Engineer\n" + "6. Captain\n" +
                           "7. Commander\n" + "8. Transporter Chief\n" + "9. Moral Officer\n" +
                           "10. Security Officer\n" + "11. Navigation Officer\n" + "12. Shuttle Pilot\n\n" +
                           "0. Exit Game\n");
        System.out.print("\nEnter a value between 0 and 12: ");
        do
        {
            option = robustInput();
            if(option < 0 || option > 12)
            {
                System.out.print("Enter a value between 0 and 12: ");
            }
        } while(option < 0 || option > 12);

        System.out.println("aqui");

        switch(option)
        {
            case 0:
                game_logic.restartGame();
                break;

            default:
                for(EnumCrewMembers member : EnumCrewMembers.values())
                {
                    if(member.getNumber() == option)
                    {
                        game_logic.chooseMember(member);
                        break;
                    }
                    System.out.print("hey\n");
                }
                break;
        }

        return;
    }

    private void UI_chooseRollNumber()
    {
        int option = 0;

        System.out.println("Type the roll number(1 - 6) or exit the game(0): ");
        do
        {
            option = robustInput();
            if(option < 0 || option > 6)
            {
                System.out.print("Enter a value between 0 and 6: ");
            }
        } while(option < 0 || option > 6);

        if(option == 0)
        {
            game_logic.restartGame();
        }
        else
        {
            game_logic.manuallySetRoll(option);
        }

        return;
    }

    private void UI_chooseRoomToSeal()
    {
        int option = 0;

        System.out.println("Which room do you want to seal?:\n");
        System.out.println("1. Bridge\n" + "2. Sick Bay\n" + "3. Brig\n" +
                           "4. Crew Quarters\n" + "5. Conference Room\n" + "6. Shuttle Bay\n" +
                           "7. Weapon's Bay\n" + "8. Mess Hall\n" + "9. Engineering\n" +
                           "10. Astrometrics\n" + "11. Holodeck\n" + "12. Hydroponics\n\n" +
                           "0. Exit Game\n");
        System.out.print("\nEnter a value between 0 and 12: ");
        do
        {
            option = robustInput();
            if(option < 0 || option > 12)
            {
                System.out.print("Enter a value between 0 and 12: ");
            }
        } while(option < 0 || option > 12);

        if(option == 0)
        {
            game_logic.restartGame();
        }
        else
        {
            game_logic.selectRoom(option);
        }

        return;
    }

    private void UI_chooseUpgrade()
    {
        int option = 0;

        System.out.println("What upgrade do you want to make?:\n");
        System.out.println("1. Add one to health\n" + "2. Repair one hull\n" + "3. Build one organic detonator\n" +
                           "4. Build one particle detonator\n" + "5. Add one to movement\n" + "6. Gain one sealed room token\n" +
                           "7. Gain one extra attack die\n" + "8. Add one to the result of attack dice\n" + "9. Continue\n" + "0. Exit Game\n");
        System.out.print("\nEnter a value between 0 and 8: ");
        do
        {
            option = robustInput();
            if(option < 0 || option > 9)
            {
                System.out.print("Enter a value between 0 and 9: ");
            }
        } while(option < 0 || option > 9);

        switch(option)
        {
            case 0:
                game_logic.restartGame();
                break;

            case 9:
                game_logic.nextRound();
                break;

            default:
                for(EnumUpgrades upgrade : EnumUpgrades.values())
                {
                    if(upgrade.getNumber() == option)
                    {
                        game_logic.chooseUpgrade(upgrade);
                        break;
                    }
                }
                break;
        }

        return;
    }

    private void UI_selectMemberPosition()
    {
        int option = 0;

        System.out.println("Where do you want to position the member?:\n");
        System.out.println("1. Bridge\n" + "2. Sick Bay\n" + "3. Brig\n" +
                           "4. Crew Quarters\n" + "5. Conference Room\n" + "6. Shuttle Bay\n" +
                           "7. Weapon's Bay\n" + "8. Mess Hall\n" + "9. Engineering\n" +
                           "10. Astrometrics\n" + "11. Holodeck\n" + "12. Hydroponics\n\n" +
                           "0. Exit Game\n");
        System.out.print("\nEnter a value between 0 and 12: ");
        do
        {
            option = robustInput();
            if(option < 0 || option > 12)
            {
                System.out.print("Enter a value between 0 and 12: ");
            }
        } while(option < 0 || option > 12);

        switch(option)
        {
            case 0:
                game_logic.restartGame();
                break;

            default:
                game_logic.selectRoom(option);
                break;
        }

        return;
    }

    private void UI_chooseWhichTrapToPut()
    {
        int option = 0;

        System.out.println("Which trap do you want to put?:\n");
        System.out.println("1. Particle Disperser\n" + "2. Organic Detonator\n\n" +
                           "0. Exit Game\n");
        System.out.print("\nEnter a value between 0 and 2: ");
        do
        {
            option = robustInput();
            if(option < 0 || option > 2)
            {
                System.out.print("Enter a value between 0 and 2: ");
            }
        } while(option < 0 || option > 2);

        switch(option)
        {
            case 0:
                game_logic.restartGame();
                break;

            default:
                for(EnumTraps trap : EnumTraps.values())
                {
                    if(trap.getNumber() == option)
                    {
                        game_logic.selectTrap(trap);
                        break;
                    }
                }
                break;
        }

        return;
    }

    private void UI_whichTrapToDetonate()
    {
        int option = 0;

        System.out.println("In which room is the particle disperser you want to detonate?:\n");
        System.out.println("1. Bridge\n" + "2. Sick Bay\n" + "3. Brig\n" +
                           "4. Crew Quarters\n" + "5. Conference Room\n" + "6. Shuttle Bay\n" +
                           "7. Weapon's Bay\n" + "8. Mess Hall\n" + "9. Engineering\n" +
                           "10. Astrometrics\n" + "11. Holodeck\n" + "12. Hydroponics\n\n" +
                           "0. Exit Game\n");
        System.out.print("\nEnter a value between 0 and 12: ");
        do
        {
            option = robustInput();
            if(option < 0 || option > 12)
            {
                System.out.print("Enter a value between 0 and 12: ");
            }
        } while(option < 0 || option > 12);

        if(option == 0)
        {
            game_logic.restartGame();
        }
        else
        {
            game_logic.selectRoom(option);
        }

        return;
    }
}