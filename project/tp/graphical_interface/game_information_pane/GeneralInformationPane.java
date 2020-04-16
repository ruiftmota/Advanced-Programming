package tp.graphical_interface.game_information_pane;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;

import java.util.ArrayList;

import tp.logic.ObservableGame;
import tp.logic.game_data.rooms.Room;
import tp.logic.game_data.crew_members.CrewMember;
import tp.logic.game_data.traps.*;
import tp.logic.game_data.aliens.Alien;

public class GeneralInformationPane extends BorderPane implements PropertyChangeListener
{
    private ObservableGame observable_game = null;

    private VBox general_information_box = new VBox();
    private Label information_label = new Label();

    public GeneralInformationPane(ObservableGame observable_game)
    {
        super();

        this.observable_game = observable_game;
        this.observable_game.addPropertyChangeListener(this);

        configuration();

        propertyChange(null);
    }

    private void configuration()
    {
        buildInformationLabel();
        general_information_box.getChildren().add(information_label);
        setCenter(general_information_box);

        setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, BorderStroke.THICK)));
        setStyle("-fx-margin: 10;");
        setVisible(true);
    }

    private void buildInformationLabel()
    {
        StringBuilder string = new StringBuilder();

        ArrayList<Room> rooms = observable_game.getRooms();
        ArrayList<Trap> traps = observable_game.getTraps();

        int i = 0, j = 0;

        string.append("Organic Detonators mounted: ");
        for(j = 0, i = 0; i < traps.size(); i++)
        {
            if(traps.get(i).getRoom() == null && traps.get(i) instanceof OrganicDetonator)
            {
                j++;
            }
        }
        string.append(j); string.append("\n\n");

        string.append("Particle Dispersers mounted: ");
        for(j = 0, i = 0; i < traps.size(); i++)
        {
            if(traps.get(i).getRoom() == null && traps.get(i) instanceof ParticleDisperser)
            {
                j++;
            }
        }
        string.append(j); string.append("\n\n");

        string.append("Inspiration Points: "); string.append(observable_game.getInspirationPoints()); string.append("\n");
        string.append("Action Points: "); string.append(observable_game.getActionPoints()); string.append("\n");
        string.append("Seal Room Tokens: "); string.append(observable_game.getNumberOfSealRoomToken()); string.append("\n");
        string.append("Add one to the result of the attack dice: "); string.append(observable_game.getAddToResultOfAttackDice()); string.append("\n\n");
        string.append("LAST DICE VALUE: "); string.append(observable_game.getLastDiceValue()); string.append("\n");

        for( int a=0 ; a<observable_game.getCrewMembers().size() ; a++){
            string.append(observable_game.getCrewMembers().get(a).getName() +
                 ": Attacks " + observable_game.getCrewMembers().get(a).getNumberOfAttacks() +
                 "   Movement " + observable_game.getCrewMembers().get(a).getMovement() + "\n");
        }
            
        information_label.setText(string.toString());


    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        buildInformationLabel();

        return;
    }
}