package tp.graphical_interface;

import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import tp.logic.ObservableGame;

public class BarraMenu extends MenuBar implements PropertyChangeListener
{
    private ObservableGame observable_game = null;

    MenuBar menuBar = new MenuBar();
    Menu itmSave = new Menu("Save");
    Menu itmLoad = new Menu("Load");
    Menu itmNew = new Menu("New");
    Menu itmExit = new Menu("Exit");



    public BarraMenu(ObservableGame observable_game)
    {
        this.observable_game = observable_game;
        this.observable_game.addPropertyChangeListener(this);


        menuBar.getMenus().add(itmNew);
        menuBar.getMenus().add(itmLoad);
        menuBar.getMenus().add(itmSave);
        menuBar.getMenus().add(itmExit);

        /* itmNew.setOnAction(e->observable_game.newGame()); */
        itmSave.setOnAction(e->observable_game.saveGame());
        itmLoad.setOnAction(e->observable_game.loadGame());
        /* itmExit.setOnAction(e->observable_game.exitGame()); */

        getChildren().addAll(menuBar);


        return;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) { return; }
}