package tp.graphical_interface.state_panes;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.beans.PropertyChangeEvent;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

import tp.logic.state_machine.states.SelectMemberPosition;
import tp.logic.ObservableGame;

public class SelectMemberPositionPane extends HBox implements PropertyChangeListener
{
    private ObservableGame observable_game = null;

    private ArrayList<Button> buttonList = new ArrayList<>();
    private Label label = new Label();
    private VBox buttonGroupList = new VBox();

    public SelectMemberPositionPane(ObservableGame observable_game)
    {
        this.observable_game = observable_game;
        this.observable_game.addPropertyChangeListener(this);
        
        configuration();
        
        propertyChange(null);
    }


    private  void configuration(){
        getChildren().addAll(label, buttonGroupList);
        
        for(int i=0 ; i<observable_game.getRooms().size() ; i++){
            buttonList.add(new Button(observable_game.getRooms().get(i).getName()));
            buttonGroupList.getChildren().add(buttonList.get(i));
            buttonList.get(i).setOnAction(new SelectMemberPositionEvent(i+1));
        }

    }


    class SelectMemberPositionEvent implements EventHandler<ActionEvent>{
        int roomNumber = 0;

        SelectMemberPositionEvent(int roomNumber){
            this.roomNumber = roomNumber;
        }

        @Override
        public void handle(ActionEvent e)
        {
            observable_game.selectRoom(roomNumber);
        }
    }


    private void showLabel() {
        StringBuilder string_to_show = new StringBuilder("Select the room where to put member ");
        string_to_show.append(observable_game.getMemberSelectionCounter() + 1);
        label.setText(string_to_show.toString());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){

        if(observable_game.getState() instanceof SelectMemberPosition == true){
            showLabel();
            setVisible(true);
        }else 
            setVisible(false);
    }
}