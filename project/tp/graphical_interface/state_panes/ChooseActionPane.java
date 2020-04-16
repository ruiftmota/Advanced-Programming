package tp.graphical_interface.state_panes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import tp.logic.ObservableGame;
import tp.logic.enumerations.EnumActions;
import tp.logic.state_machine.states.ChooseAction;

public class ChooseActionPane extends HBox implements PropertyChangeListener
{
    private ObservableGame observable_game = null;
    
    private ArrayList<Button> buttonList = new ArrayList<>();
    private VBox buttons_box = new VBox();
    private Label label = new Label();

    public ChooseActionPane(ObservableGame observable_game)
    {
        this.observable_game = observable_game;
        this.observable_game.addPropertyChangeListener(this);
        
        configuration();

        this.propertyChange(null);
    }

    private void configuration()
    {
        label.setText("Choose Action:");
        getChildren().addAll(label, buttons_box);
        int i = 0;
        for(EnumActions action : EnumActions.values()){
            buttonList.add(new Button(action.getName()));
            buttons_box.getChildren().add(buttonList.get(i));
            buttonList.get(i).setOnAction(new SelectActionEvent(i+1));
            i++;
        }
    }

    class SelectActionEvent implements EventHandler<ActionEvent>{
        
        private int action = 0;

        SelectActionEvent(int action){
            this.action = action;
        }


        @Override
        public void handle(ActionEvent e)
        {
            if(action == 1) observable_game.nextRound(); 
            if(action == 2) observable_game.chooseHealPlayer();
            if(action == 3) observable_game.chooseRepairHull();
            if(action == 4) observable_game.chooseDetonate();
            if(action == 5) observable_game.chooseMove();
            if(action == 6) observable_game.chooseAttack();
            if(action == 7) observable_game.chooseSetTrap();
            if(action == 8) observable_game.chooseSealRoom();
            if(action == 9) observable_game.chooseSacrificeRedShirt();

        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if(observable_game.getState() instanceof ChooseAction == true)
        {
            hideButtons();
            setVisible(true);
        }
        else
        {
            setVisible(false);
        }
    }

    private void hideButtons(){
        buttonList.get(1).setDisable(true);
        for( int i=0 ; i<observable_game.getCrewMembers().size() ; i++){
            if(observable_game.getCrewMembers().get(i).getId() == 1){
                buttonList.get(1).setDisable(false);
                break;
            }
        }

        buttonList.get(2).setDisable(true);
        for( int i=0 ; i<observable_game.getCrewMembers().size() ; i++){
            if(observable_game.getCrewMembers().get(i).getId() == 5){
                buttonList.get(2).setDisable(false);
                break;
            }
        }
        
        buttonList.get(3).setDisable(true);
        for( int i=0 ; i<observable_game.getTraps().size() ; i++){
            if(observable_game.getTraps().get(i).getRoom() != null){
                buttonList.get(3).setDisable(false);
                break;
            }
        }

        buttonList.get(6).setDisable(true);
        for( int i=0 ; i<observable_game.getTraps().size() ; i++){
            if(observable_game.getTraps().get(i).getRoom() == null){
                buttonList.get(6).setDisable(false);
                break;
            }
        }


        if(observable_game.getNumberOfSealRoomToken() == 0)
            buttonList.get(7).setDisable(true);
        else
            buttonList.get(7).setDisable(false);

            buttonList.get(8).setDisable(true);
        for( int i=0 ; i<observable_game.getCrewMembers().size() ; i++){
            if(observable_game.getCrewMembers().get(i).getId() == 3){
                buttonList.get(8).setDisable(false);
                break;
            }
        }

        
        
    }



}