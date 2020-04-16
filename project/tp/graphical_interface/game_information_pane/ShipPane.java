package tp.graphical_interface.game_information_pane;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;

import tp.logic.ObservableGame;

public class ShipPane extends BorderPane implements PropertyChangeListener
{
    ObservableGame observable_game = null;

    public ShipPane(ObservableGame observable_game)
    {
        super();

        this.observable_game = observable_game;
        this.observable_game.addPropertyChangeListener(this);

        configuration();

        propertyChange(null);
    }

    private void configuration()
    {
        setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, BorderStroke.THICK)));
        setStyle("-fx-margin: 10;");
        setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        return;
    }
}