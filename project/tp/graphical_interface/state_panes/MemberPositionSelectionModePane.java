package tp.graphical_interface.state_panes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import tp.logic.ObservableGame;

import tp.logic.state_machine.states.MemberPositionSelectionMode;

public class MemberPositionSelectionModePane extends HBox implements PropertyChangeListener
{
    private ObservableGame observable_game = null;

    private Button manuallyButton;
    private Button randomButton;

    private Label optionsInformation;

    public MemberPositionSelectionModePane(ObservableGame observable_game)
    {
        this.observable_game = observable_game;
        this.observable_game.addPropertyChangeListener(this);

        configuration();

        this.propertyChange(null);
    }

    private void configuration(){

        optionsInformation = new Label("Choose how to position members:\n");
        getChildren().add(optionsInformation);

        manuallyButton = new Button("Manually");
        randomButton = new Button("Random");
        
        getChildren().addAll( manuallyButton, randomButton);
        
        manuallyButton.setOnAction(e-> {
            observable_game.chooseManual();
        });

        randomButton.setOnAction(e->{
            observable_game.chooseRandom();
        });
        
        


    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        if(observable_game.getState() instanceof MemberPositionSelectionMode == true)
            setVisible(true);
        else 
            setVisible(false);
    }
}