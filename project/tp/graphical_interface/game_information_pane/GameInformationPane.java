package tp.graphical_interface.game_information_pane;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;

import tp.logic.ObservableGame;

public class GameInformationPane extends BorderPane implements PropertyChangeListener
{
    private ObservableGame observable_game = null;

    private GeneralInformationPane general_information_pane = null;
    private RoomInformationPane room_information_pane = null;
    private ShipPane ship_pane = null;
    private TrackersPane trackers_pane = null;

    public GameInformationPane(ObservableGame observable_game)
    {
        super();

        this.observable_game = observable_game;
        this.observable_game.addPropertyChangeListener(this);

        createInformationPanes();

        configuration();

        propertyChange(null);
    }

    private void createInformationPanes()
    {
        general_information_pane = new GeneralInformationPane(observable_game);
        room_information_pane = new RoomInformationPane(observable_game);
        ship_pane = new ShipPane(observable_game);
        trackers_pane = new TrackersPane(observable_game);

        setCenter(ship_pane);
        setRight(room_information_pane);
        setLeft(general_information_pane);
        setBottom(trackers_pane);
    }

    private void configuration()
    {
        setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, BorderStroke.THICK)));
        setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        return;
    }
}