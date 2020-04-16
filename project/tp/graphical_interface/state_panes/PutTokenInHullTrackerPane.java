package tp.graphical_interface.state_panes;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import java.beans.PropertyChangeEvent;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import tp.logic.ObservableGame;
import tp.logic.state_machine.states.PutTokenInHullTracker;

public class PutTokenInHullTrackerPane extends HBox implements PropertyChangeListener
{
    private ObservableGame observable_game = null;

    private ArrayList<Button> hull_tracker_value_buttons = new ArrayList<Button>();
    private Label label = new Label();
    private HBox buttons_box = new HBox();

    public PutTokenInHullTrackerPane(ObservableGame observable_game)
    {
        this.observable_game = observable_game;
        this.observable_game.addPropertyChangeListener(this);

        configuration();

        propertyChange(null);
    }

    private void configuration()
    {
        getChildren().addAll(label, buttons_box);

        label.setText("Select your Hull Tracker Token position:");

        for(int i = 0; i < observable_game.getMaximumHull(); i++)
        {
            hull_tracker_value_buttons.add(new Button(new String("" + (i + 1))));
            buttons_box.getChildren().add(hull_tracker_value_buttons.get(i));
            hull_tracker_value_buttons.get(i).setOnAction(new TokenPositionSelectedEvent(i + 1));
        }
    }

    class TokenPositionSelectedEvent implements EventHandler<ActionEvent>
    {
        private int token_position = 0;

        TokenPositionSelectedEvent(int token_position)
        {
            this.token_position = token_position;
        }

        @Override
        public void handle(ActionEvent e)
        {
            observable_game.setToken(token_position);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if(observable_game.getState() instanceof PutTokenInHullTracker == true)
        {
            setVisible(true);
        }
        else
        {
            setVisible(false);
        }

        return;
    }
}