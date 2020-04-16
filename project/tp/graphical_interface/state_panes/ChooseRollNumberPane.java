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
import tp.logic.state_machine.states.ChooseRollNumber;


public class ChooseRollNumberPane extends HBox implements PropertyChangeListener
{
    private ObservableGame observable_game = null;

    private ArrayList<Button> health_tracker_value_buttons = new ArrayList<Button>();
    private Label label = new Label();
    private HBox buttons_box = new HBox();

    public ChooseRollNumberPane(ObservableGame observable_game)
    {
        this.observable_game = observable_game;
        this.observable_game.addPropertyChangeListener(this);

        configuration();

        propertyChange(null);
    }

    private void configuration()
    {
        getChildren().addAll(label, buttons_box);

        label.setText("Select the value of the dice for debug:");

        for(int i = 0; i < 6; i++)
        {
            health_tracker_value_buttons.add(new Button(new String("" + (i + 1))));
            buttons_box.getChildren().add(health_tracker_value_buttons.get(i));
            health_tracker_value_buttons.get(i).setOnAction(new ChooseRollNumberEvent(i + 1));
        }
    }

    class ChooseRollNumberEvent implements EventHandler<ActionEvent>
    {
        private int roll_number = 0;

        ChooseRollNumberEvent(int roll_number)
        {
            this.roll_number = roll_number;
        }

        @Override
        public void handle(ActionEvent e)
        {
            observable_game.manuallySetRoll(roll_number);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if(observable_game.getState() instanceof ChooseRollNumber == true)
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