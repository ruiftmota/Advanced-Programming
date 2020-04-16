package tp.graphical_interface.state_panes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import tp.logic.ObservableGame;
import tp.logic.state_machine.states.HullTrackerTokenLocationMode;

public class HullTrackerTokenLocationModePane extends HBox implements PropertyChangeListener
{
    private ObservableGame observable_game = null;

    private Button defaultly = new Button("Default");
    private Button manually = new Button("Manual");
    private HBox buttons_box = new HBox();
    private Label label = new Label("How do you want to position the Hull Tracker?");

    public HullTrackerTokenLocationModePane(ObservableGame observable_game)
    {
        this.observable_game = observable_game;
        this.observable_game.addPropertyChangeListener(this);
        
        configuration();

        this.propertyChange(null);
    }

    private void configuration()
    {
        getChildren().addAll(label, buttons_box);

        buttons_box.getChildren().addAll(defaultly, manually);

        defaultly.setOnAction(e -> {
            observable_game.chooseRandom();
        });
        manually.setOnAction
        (
            e -> { observable_game.chooseManual(); }
        );
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if(observable_game.getState() instanceof HullTrackerTokenLocationMode == true)
        {
            setVisible(true);
        }
        else
        {
            setVisible(false);
        }
    }
}