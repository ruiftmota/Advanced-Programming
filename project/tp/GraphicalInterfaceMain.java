package tp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import tp.logic.Game;
import tp.logic.ObservableGame;
import tp.graphical_interface.LayoutOrganizer;

public class GraphicalInterfaceMain extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        Game game_logic = new Game();
        ObservableGame observable_game = new ObservableGame(game_logic);

        LayoutOrganizer layout = new LayoutOrganizer(observable_game);

        Scene scene = new Scene(layout.getRoot());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Programação Avançada - Destination Earth");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}