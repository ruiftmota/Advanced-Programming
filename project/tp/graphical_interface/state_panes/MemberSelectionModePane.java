package tp.graphical_interface.state_panes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import tp.logic.ObservableGame;
import tp.logic.state_machine.states.MemberSelectionMode;

public class MemberSelectionModePane extends HBox implements PropertyChangeListener
{
    private ObservableGame observable_game = null;
    
    private Button random = null;
    private Button manually = null;
    private HBox buttons_box = new HBox();
    private Label label = null;

    public MemberSelectionModePane(ObservableGame observable_game)
    {
        this.observable_game = observable_game;
        this.observable_game.addPropertyChangeListener(this);
        
        configuration();

        this.propertyChange(null);
    }

    private void configuration()
    {
        
    
        label = new Label("How do you want to choose your members?");
        getChildren().addAll(label, buttons_box);

        random = new Button("Random");
        manually = new Button("Manually");

        buttons_box.getChildren().addAll(manually, random);

        random.setOnAction(e -> {
            observable_game.chooseRandom();
        });
        manually.setOnAction(e -> {
            observable_game.chooseManual();
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if(observable_game.getState() instanceof MemberSelectionMode == true)
        {
            setVisible(true);
        }
        else
        {
            setVisible(false);
        }
    }
}