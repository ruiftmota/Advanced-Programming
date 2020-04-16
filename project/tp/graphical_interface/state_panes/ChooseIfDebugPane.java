package tp.graphical_interface.state_panes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import tp.logic.ObservableGame;
import tp.logic.state_machine.states.ChooseIfDebug;

public class ChooseIfDebugPane extends HBox implements PropertyChangeListener
{
    private ObservableGame observable_game = null;
    
    private Button debug = null;
    private Button dontDebug = null;
    private HBox buttons_box = new HBox();
    private Label label = null;

    public ChooseIfDebugPane(ObservableGame observable_game)
    {
        this.observable_game = observable_game;
        this.observable_game.addPropertyChangeListener(this);
        
        configuration();

        this.propertyChange(null);
    }

    private void configuration()
    {
        
    
        label = new Label("Do you want to debug?");
        getChildren().addAll(label, buttons_box);

        debug = new Button("Debug");
        dontDebug = new Button("Do not debug");

        buttons_box.getChildren().addAll(debug, dontDebug);

        debug.setOnAction(e -> {
            observable_game.chooseManual();
        });
        dontDebug.setOnAction(e -> {
            observable_game.chooseRandom();
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if(observable_game.getState() instanceof ChooseIfDebug == true)
        {
            setVisible(true);
        }
        else
        {
            setVisible(false);
        }
    }
}