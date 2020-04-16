package tp.graphical_interface.state_panes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import tp.logic.ObservableGame;
import tp.logic.enumerations.EnumUpgrades;
import tp.logic.state_machine.states.ChooseUpgrade;

public class ChooseUpgradePane extends HBox implements PropertyChangeListener
{
    private ObservableGame observable_game = null;
    
    private ArrayList<Button> buttonList = new ArrayList<>();
    private VBox buttons_box = new VBox();
    private Label label = new Label();

    public ChooseUpgradePane(ObservableGame observable_game)
    {
        this.observable_game = observable_game;
        this.observable_game.addPropertyChangeListener(this);
        
        configuration();

        this.propertyChange(null);
    }

    private void configuration()
    {
        label.setText("Choose Upgrade:");
        getChildren().addAll(label, buttons_box);
        int i = 0;
        for(EnumUpgrades upgrade : EnumUpgrades.values()){
            buttonList.add(new Button(upgrade.getName()));
            buttons_box.getChildren().add(buttonList.get(i));
            buttonList.get(i).setOnAction(new ChooseUpgradeEvent(i+1));
            i++;
        }
    }

    class ChooseUpgradeEvent implements EventHandler<ActionEvent>{
        
        private int option = 0;

        ChooseUpgradeEvent(int option){
            this.option = option;
        }


        @Override
        public void handle(ActionEvent e)
        {
            switch(option)
            {
                case 0:
                    observable_game.restartGame();
                    break;
    
                case 9:
                    observable_game.nextRound();
                    break;
    
                default:
                    for(EnumUpgrades upgrade : EnumUpgrades.values())
                    {
                        if(upgrade.getNumber() == option)
                        {
                            observable_game.chooseUpgrade(upgrade);
                            break;
                        }
                    }
                    break;
            }

        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if(observable_game.getState() instanceof ChooseUpgrade == true)
        {
            setVisible(true);
        }
        else
        {
            setVisible(false);
        }
    }

}