package tp.graphical_interface;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import tp.logic.ObservableGame;
import tp.graphical_interface.game_information_pane.GameInformationPane;

public class RootPane extends VBox implements PropertyChangeListener
{
    private ObservableGame observable_game = null;

    private BarraMenu barra_menu = null;

    private HBox menu_and_main_panes_box = new HBox();
    private InteractionPane interaction_pane = null;
    private GameInformationPane game_information_pane = null;

    public RootPane(ObservableGame observable_game)
    {
        super();

        this.observable_game = observable_game;
        this.observable_game.addPropertyChangeListener(this);

        this.game_information_pane = new GameInformationPane(this.observable_game);
        this.interaction_pane = new InteractionPane(this.observable_game);
        this.barra_menu = new BarraMenu(this.observable_game);

        configuration();
    }

    private void configuration()
    {
        menu_and_main_panes_box.getChildren().addAll(game_information_pane, interaction_pane);

        getChildren().addAll(barra_menu, menu_and_main_panes_box);

        /* setTop(barra_menu);
        setCenter(menu_and_main_panes_box); */
        setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, BorderStroke.THICK)));
        setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) { return; }
}