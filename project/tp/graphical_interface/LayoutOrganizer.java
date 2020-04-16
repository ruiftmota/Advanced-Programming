package tp.graphical_interface;

import javafx.scene.layout.Pane;

import tp.logic.ObservableGame;
import tp.graphical_interface.RootPane;

public class LayoutOrganizer
{
    private final Pane root;

    public LayoutOrganizer(ObservableGame observable_game)
    {
        root = new RootPane(observable_game);
        return;
    }

    public Pane getRoot() { return root; }
}