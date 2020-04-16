package tp.graphical_interface.state_panes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import tp.logic.ObservableGame;
import tp.logic.enumerations.EnumCrewMembers;
import tp.logic.game_data.crew_members.CrewMember;
import tp.logic.game_data.rooms.Room;
import tp.logic.state_machine.states.ChooseWhereToMoveMember;

public class ChooseWhereToMoveMemberPane extends HBox implements PropertyChangeListener{
    private ObservableGame observable_game = null;
    
    private ArrayList<Button> buttonList = new ArrayList<>();
    private VBox buttons_box = new VBox();
    private Label label = new Label();

    public ChooseWhereToMoveMemberPane(ObservableGame observable_game)
    {
        this.observable_game = observable_game;
        this.observable_game.addPropertyChangeListener(this);
        
        configuration();

        this.propertyChange(null);
    }

    private void configuration(){

        label.setText("Choose where to move member:");
        getChildren().addAll(label, buttons_box);

        
        for( int i=0 ; i<observable_game.getRooms().size() ; i++){
            buttonList.add(new Button(observable_game.getRooms().get(i).getName()));
            buttons_box.getChildren().add(buttonList.get(i));
            buttonList.get(i).setOnAction(new ChooseWhereToMoveMemberEvent(i+1));
            buttonList.get(i).setDisable(true);
        }
    }

    class ChooseWhereToMoveMemberEvent implements EventHandler<ActionEvent>{
        private int room_number = 0;

        ChooseWhereToMoveMemberEvent(int room_number){
            this.room_number = room_number;
        }

        

        @Override
        public void handle(ActionEvent e){
            observable_game.selectRoom(room_number);
        }
    }

    private void hideButtons(){

        Button room_button = null;
        Room button_room = null;
        EnumCrewMembers member_to_move_enum = null;
        CrewMember member_to_move = null;

        for(int i = 0; i < buttonList.size(); i++)
        {
            room_button = buttonList.get(i);
            button_room = observable_game.getRooms().get(i);
            member_to_move_enum = observable_game.getMovingCrewMember();
            member_to_move = convertEnumCrewMemberToObject(member_to_move_enum);

            if(member_to_move.getRoom().canMoveToAnotherRoom(button_room.getId(), member_to_move.getMovement()) == true)
            {
                room_button.setDisable(false);
            }



        }

        /* CrewMember member = null;
        for( int i=0 ; i<observable_game.getCrewMembers().size() ; i++){
            if(observable_game.getMovingCrewMember().getNumber() == observable_game.getCrewMembers().get(i).getId())
                member = observable_game.getCrewMembers().get(i);
        }
        for( int i=0 ; i<observable_game.getRooms().size() ; i++ ){
            buttonList.get(i).setDisable(true);
            if( member.getRoom().canMoveToAnotherRoom(i+1, member.getMovement()) == false){
                buttonList.get(i).setDisable(false);
                System.out.println(i+1 + "   " + member.getMovement());
            }
                
        } */
    }

    private CrewMember convertEnumCrewMemberToObject(EnumCrewMembers member)
    {
        for(int i = 0; i < observable_game.getCrewMembers().size(); i++)
        {
            if(observable_game.getCrewMembers().get(i).getId() == member.getNumber())
            {
                return observable_game.getCrewMembers().get(i);
            }
        }

        return null;
    }



    @Override
    public void propertyChange(PropertyChangeEvent evt){
        if(observable_game.getState() instanceof ChooseWhereToMoveMember == true){
            hideButtons();
            setVisible(true);
        }else{
            setVisible(false);
        }
    }
}