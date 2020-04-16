package tp.logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import tp.logic.Game;

public class LoadGame
{

    public Game loadGame(){
        String fileName = "SavedData.bin";
        Game game_logic = null;
        try{
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
            game_logic = (Game) is.readObject();
            is.close();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException x){
            x.printStackTrace();
        }
        return game_logic;
    }
}