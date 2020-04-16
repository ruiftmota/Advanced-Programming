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
import tp.logic.game_data.traps.Trap;
import tp.logic.game_data.aliens.Alien;

public class RoomInformationPane extends BorderPane implements PropertyChangeListener
{
    private ObservableGame observable_game = null;

    private VBox room_information_box = new VBox();
    private Label information_label = new Label();

    public RoomInformationPane(ObservableGame observable_game)
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
        room_information_box.getChildren().add(information_label);
        setCenter(room_information_box);

        setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, BorderStroke.THICK)));
        setStyle("-fx-margin: 10;");
        setVisible(true);
    }

    private void buildInformationLabel()
    {
        StringBuilder string = new StringBuilder();

        ArrayList<Room> rooms = observable_game.getRooms();
        ArrayList<CrewMember> crew_members = observable_game.getCrewMembers();
        ArrayList<Trap> traps = observable_game.getTraps();
        ArrayList<Alien> aliens = observable_game.getAliens();

        int i = 0, j = 0;

        string.append("ROOMS:\n\n");
        for(i = 0; i < rooms.size(); i++)
        {
            string.append(rooms.get(i).getId()); 
            string.append(". "); string.append(rooms.get(i).getName());
            if(rooms.get(i).getIsSealed() == true)
            {
                string.append(" [IS SEALED] ");
            }
            string.append(": ");
            for(j = 0; j < crew_members.size(); j++)
            {
                if(crew_members.get(j).getRoom() == rooms.get(i))
                {
                    string.append(crew_members.get(j).getName().toUpperCase()); string.append(", ");
                }
            }

            for(j = 0; j < traps.size(); j++)
            {
                if(traps.get(j).getRoom() == rooms.get(i))
                {
                    string.append(traps.get(j).getName()); string.append(", ");
                }
            }
            for(j = 0; j < aliens.size(); j++)
            {
                if(aliens.get(j).getRoom() == rooms.get(i))
                {
                    string.append("Alien "); string.append(aliens.get(j).getId()); string.append(", ");
                }
            }
            string.append("\n\n");
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