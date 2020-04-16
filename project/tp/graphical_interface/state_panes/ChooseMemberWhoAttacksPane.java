package tp.graphical_interface.state_panes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import tp.logic.enumerations.EnumCrewMembers;
import tp.logic.state_machine.states.ChooseMemberWhoAttacks;
import tp.logic.ObservableGame;

public class ChooseMemberWhoAttacksPane extends HBox implements PropertyChangeListener
{
    private ObservableGame observable_game = null;
    
    private ArrayList<Button> buttonList = new ArrayList<>();
    private VBox buttons_box = new VBox();
    private Label label = new Label();

    public ChooseMemberWhoAttacksPane(ObservableGame observable_game)
    {
        this.observable_game = observable_game;
        this.observable_game.addPropertyChangeListener(this);
        
        configuration();

        this.propertyChange(null);
    }

    private void configuration(){

        label.setText("Choose member who attacks:");
        getChildren().addAll(label, buttons_box);

        int i = 0;
        for(EnumCrewMembers member : EnumCrewMembers.values()){
            buttonList.add(new Button(member.getName()));
            buttons_box.getChildren().add(buttonList.get(i));
            buttonList.get(i).setOnAction(new ChooseMemberWhoAttacksEvent(i+1));
            i++;
        }
    }

    class ChooseMemberWhoAttacksEvent implements EventHandler<ActionEvent>{
        private int id = 0;

        ChooseMemberWhoAttacksEvent(int id){
            this.id = id;
        }

        private EnumCrewMembers getEnumFromId(int id){
            for(EnumCrewMembers member : EnumCrewMembers.values()){
                if(id == member.getNumber()){
                    return member;
                }
            }
            return null;
        }

        @Override
        public void handle(ActionEvent e){
            observable_game.chooseMember(getEnumFromId(id));
        }
    }

    private void hideButtons(){
        for( int  i=0 ; i<buttonList.size() ; i++){
            buttonList.get(i).setDisable(true);
            for( int j=0 ; j<observable_game.getCrewMembers().size() ; j++){
                if(observable_game.getCrewMembers().get(j).getId() == i+1){
                    buttonList.get(i).setDisable(false);
                }
            }
            
        }
    }



    @Override
    public void propertyChange(PropertyChangeEvent evt){
        if(observable_game.getState() instanceof ChooseMemberWhoAttacks == true){
            hideButtons();
            setVisible(true);
        }else{
            setVisible(false);
        }
    }
}