package tp.graphical_interface.state_panes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

import tp.logic.ObservableGame;
import tp.logic.enumerations.EnumCrewMembers;
import tp.logic.game_data.crew_members.CrewMember;
import tp.logic.state_machine.states.SelectMember;

public class SelectMemberPane extends HBox implements PropertyChangeListener
{
    private ObservableGame observable_game = null;

    private ArrayList<Button> member_buttons = new ArrayList<Button>();
    private Label label = new Label();
    private VBox buttons_box = new VBox();

    public SelectMemberPane(ObservableGame observable_game)
    {
        this.observable_game = observable_game;
        this.observable_game.addPropertyChangeListener(this);
        
        configuration();

        propertyChange(null);
    }
    
    private void configuration()
    {
        getChildren().addAll(label, buttons_box);

        int i = 0;
        for(EnumCrewMembers member : EnumCrewMembers.values())
        {
            member_buttons.add(new Button(member.getName()));
            buttons_box.getChildren().add(member_buttons.get(i));
            member_buttons.get(i).setId(new String("" + member.getNumber()));
            member_buttons.get(i).setOnAction(new MemberSelectedEvent(Integer.parseInt(member_buttons.get(i).getId())));
            i++;
        }
    }

    private void showLabel()
    {
        StringBuilder string_to_show = new StringBuilder("Select the member number ");
        string_to_show.append(observable_game.getMemberSelectionCounter() + 1);
        label.setText(string_to_show.toString());
    }

    private void hideButtons()
    {
        for(int i = 0; i < member_buttons.size(); i++)
        {
            if(memberAlreadySelected(Integer.parseInt(member_buttons.get(i).getId())) == true)
            {
                member_buttons.get(i).setDisable(true);
            }
        }
    }

    private boolean memberAlreadySelected(int id)
    {
        ArrayList<CrewMember> crew_members = observable_game.getCrewMembers();

        for(int i = 0; i < crew_members.size(); i++)
        {
            if(id == crew_members.get(i).getId())
            {
                return true;
            } 
        }

        return false;
    }

    class MemberSelectedEvent implements EventHandler<ActionEvent>
    {
        int member_id = 0;

        MemberSelectedEvent(int member_id)
        {
            this.member_id = member_id;
        }

        private EnumCrewMembers getEnumFromId(int id)
        {
            for(EnumCrewMembers member : EnumCrewMembers.values())
            {
                if(id == member.getNumber())
                {
                    return member;
                }
            }
            
            return null;
        }

        @Override
        public void handle(ActionEvent e)
        {
            observable_game.chooseMember(getEnumFromId(member_id));
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if(observable_game.getState() instanceof SelectMember == true)
        {
            showLabel();
            hideButtons();
            setVisible(true);
        }
        else
        {
            setVisible(false);
        }
    }
}