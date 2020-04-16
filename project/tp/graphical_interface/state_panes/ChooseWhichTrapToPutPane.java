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

import tp.logic.enumerations.EnumTraps;
import tp.logic.state_machine.states.ChooseWhichTrapToPut;
import tp.logic.ObservableGame;

public class ChooseWhichTrapToPutPane extends HBox implements PropertyChangeListener
{
    private ObservableGame observable_game = null;
    
    private ArrayList<Button> buttonList = new ArrayList<>();
    private VBox buttons_box = new VBox();
    private Label label = new Label();

    public ChooseWhichTrapToPutPane(ObservableGame observable_game)
    {
        this.observable_game = observable_game;
        this.observable_game.addPropertyChangeListener(this);
        
        configuration();

        this.propertyChange(null);
    }

    private void configuration(){

        label.setText("Choose which trap to put:");
        getChildren().addAll(label, buttons_box);

        
        for( int i=0 ; i<observable_game.getTraps().size() ; i++){
            if(observable_game.getTraps().get(i).getRoom() == null){
                buttonList.add(new Button(observable_game.getTraps().get(i).getName()));
                buttons_box.getChildren().add(buttonList.get(i));
                buttonList.get(i).setOnAction(new ChooseWhichTrapToPutEvent(i));
            }
     
        }
    }

    class ChooseWhichTrapToPutEvent implements EventHandler<ActionEvent>{
        private int pos = 0;

        ChooseWhichTrapToPutEvent(int pos){
            this.pos = pos;
        }

        private EnumTraps getEnumTrapPos(){
            for(EnumTraps enume : EnumTraps.values()){
                if(observable_game.getTraps().get(pos).getId() == enume.getNumber()){
                    return enume;
                }
            }
            return null;
        }

        @Override
        public void handle(ActionEvent e){
            observable_game.selectTrap(getEnumTrapPos());
        }
    }
    
    



    @Override
    public void propertyChange(PropertyChangeEvent evt){
        if(observable_game.getState() instanceof ChooseWhichTrapToPut == true){
            setVisible(true);
        }else{
            setVisible(false);
        }
    }
}