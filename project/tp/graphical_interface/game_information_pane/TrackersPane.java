package tp.graphical_interface.game_information_pane;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.beans.PropertyChangeEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import tp.logic.ObservableGame;

public class TrackersPane extends BorderPane implements PropertyChangeListener
{
    private ObservableGame observable_game = null;

    private VBox trackers_box = new VBox();

    private HBox journey_tracker_box = new HBox();
    private HBox hull_tracker_box = new HBox();
    private HBox health_tracker_box = new HBox();

    ArrayList<Button> journey_tracker_buttons = new ArrayList<Button>();
    ArrayList<Button> hull_tracker_buttons = new ArrayList<Button>();
    ArrayList<Button> health_tracker_buttons = new ArrayList<Button>();

    public TrackersPane(ObservableGame observable_game)
    {
        super();

        this.observable_game = observable_game;
        this.observable_game.addPropertyChangeListener(this);

        configuration();

        propertyChange(null);
    }

    private void configuration()
    {
        buildTrackers();
        trackers_box.getChildren().addAll(new HBox(new Label("Journey Tracker"), journey_tracker_box),
                                          new HBox(new Label("Hull Tracker"), hull_tracker_box), 
                                          new HBox(new Label("Health Tracker"), health_tracker_box));

        setCenter(trackers_box);

        setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, BorderStroke.THICK)));
        setVisible(true);
    }

    private void buildTrackers()
    {
        buildJourneyTracker();
        buildHullTracker();
        buildHealthTracker();
    }

    private void buildJourneyTracker()
    {
        int[] journey_tracker = observable_game.getJourneyTracker();

        journey_tracker_box.getChildren().clear();

        journey_tracker_buttons.add(new Button("S"));
        journey_tracker_box.getChildren().add(journey_tracker_buttons.get(0));
        journey_tracker_buttons.get(0).setDisable(true);

        for(int i = 1; i < 14; i++)
        {
            journey_tracker_buttons.add(new Button(new String("" + journey_tracker[i-1])));
            journey_tracker_box.getChildren().add(journey_tracker_buttons.get(i));
            journey_tracker_buttons.get(i).setDisable(true);
        }
    }

    private void buildHullTracker()
    {
        for(int i = 0; i < observable_game.getMaximumHull(); i++)
        {
            hull_tracker_buttons.add(new Button("" + (i + 1)));
            hull_tracker_buttons.get(i).setDisable(true);
            hull_tracker_box.getChildren().add(hull_tracker_buttons.get(i));
        }
    }

    private void buildHealthTracker()
    {
        for(int i = 0; i < observable_game.getMaximumHealth(); i++)
        {
            health_tracker_buttons.add(new Button("" + (i + 1)));
            health_tracker_buttons.get(i).setDisable(true);
            health_tracker_box.getChildren().add(health_tracker_buttons.get(i));
        }
    }

    private void updateTrackers()
    {
        updateJourneyTracker();
        updateHullTracker();
        updateHealthTracker();
    }

    private void updateJourneyTracker()
    {
        for(int i = 0; i < journey_tracker_buttons.size(); i++)
        {
            journey_tracker_buttons.get(i).setDisable(true);
            if(observable_game.getRoundNumber() == i - 1)
            {
                journey_tracker_buttons.get(i).setDisable(false);
            }
        }
    }

    private void updateHullTracker()
    {
        for(int i = 0; i < hull_tracker_buttons.size(); i++)
        {
            hull_tracker_buttons.get(i).setDisable(true);
            if(i + 1 == observable_game.getHullTracker())
            {
                hull_tracker_buttons.get(i).setDisable(false);
            }
        }
    }

    private void updateHealthTracker()
    {
        for(int i = 0; i < health_tracker_buttons.size(); i++)
        {
            health_tracker_buttons.get(i).setDisable(true);
            if(i + 1 == observable_game.getHealthTracker())
            {
                health_tracker_buttons.get(i).setDisable(false);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        updateTrackers();

        return;
    }
}