package tp.graphical_interface.state_panes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javafx.scene.layout.HBox;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import tp.logic.ObservableGame;
import tp.logic.state_machine.states.ChooseRoomToSeal;

public class ChooseRoomToSealPane extends HBox implements PropertyChangeListener
{
    private ObservableGame observable_game = null;
    
    private ArrayList<Button> buttonList = new ArrayList<>();
    private HBox buttons_box = new HBox();
    private Label label = new Label();

    public ChooseRoomToSealPane(ObservableGame observable_game)
    {
        this.observable_game = observable_game;
        this.observable_game.addPropertyChangeListener(this);
        
        configuration();

        this.propertyChange(null);
    }

    private void configuration(){

        label.setText("Choose Room to seal:");
        getChildren().addAll(label, buttons_box);

        for( int i=0 ; i<observable_game.getRooms().size() ; i++ ){
                buttonList.add(new Button(observable_game.getRooms().get(i).getName()));
                buttons_box.getChildren().add(buttonList.get(i));
                buttonList.get(i).setOnAction(new ChooseRoomToSealEvent(i+1));
        }
    }

    class ChooseRoomToSealEvent implements EventHandler<ActionEvent>
    {
        private int room_number = 0;

        ChooseRoomToSealEvent(int room_number)
        {
            this.room_number = room_number;
        }

        @Override
        public void handle(ActionEvent e)
        {
            observable_game.selectRoom(room_number);
        }
    }


    private void hideButtons(){
        for(int i = 0; i < buttonList.size(); i++){
            if(observable_game.getRooms().get(i).getCanBeSealed() == true || observable_game.getRooms().get(i).getIsSealed() == true){
                buttonList.get(i).setDisable(true);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        if(observable_game.getState() instanceof ChooseRoomToSeal == true){
            hideButtons();
            setVisible(true);
        }else{
            setVisible(false);
        }
    }
}