package tp.graphical_interface;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;

import tp.logic.ObservableGame;
import tp.graphical_interface.state_panes.*;
import tp.logic.state_machine.states.*;
import tp.logic.state_machine.StateInterface;

public class InteractionPane extends BorderPane implements PropertyChangeListener
{
    private ObservableGame observable_game = null;

    private ChooseActionPane choose_action_pane = null;
    private ChooseIfDebugPane choose_if_debug_pane = null;
    private ChooseMemberToMovePane choose_member_to_move_pane = null;
    private ChooseMemberWhoAttacksPane choose_member_who_attacks_pane = null;
    private ChooseMemberWhoPutsTrapPane choose_member_who_puts_trap_pane = null;
    private ChooseRollNumberPane choose_roll_number_pane = null;
    private ChooseRoomToSealPane choose_room_to_seal_pane = null;
    private ChooseUpgradePane choose_upgrade_pane = null;
    private ChooseWhereToMoveMemberPane choose_where_to_move_member_pane = null;
    private ChooseWhichTrapToPutPane choose_which_trap_to_put_pane = null;
    private HealthTrackerTokenLocationModePane health_tracker_token_location_mode_pane = null;
    private HullTrackerTokenLocationModePane hull_tracker_token_location_mode_pane = null;
    private MemberPositionSelectionModePane member_position_selection_mode_pane = null;
    private MemberSelectionModePane member_selection_mode_pane = null;
    private PutTokenInHealthTrackerPane put_token_in_health_tracker_pane = null;
    private PutTokenInHullTrackerPane put_token_in_hull_tracker_pane = null;
    private SelectMemberPane select_member_pane = null;
    private SelectMemberPositionPane select_member_position_pane = null;
    private WhichTrapToDetonatePane which_trap_to_detonate_pane = null;

    public InteractionPane(ObservableGame observable_game)
    {
        super();

        this.observable_game = observable_game;
        this.observable_game.addPropertyChangeListener(this);

        createStatePanes();

        configuration();

        this.propertyChange(null);
    }

    private void createStatePanes()
    {
        choose_action_pane = new ChooseActionPane(this.observable_game);
        choose_if_debug_pane = new ChooseIfDebugPane(this.observable_game);
        choose_member_to_move_pane = new ChooseMemberToMovePane(this.observable_game);
        choose_member_who_attacks_pane = new ChooseMemberWhoAttacksPane(this.observable_game);
        choose_member_who_puts_trap_pane = new ChooseMemberWhoPutsTrapPane(this.observable_game);
        choose_roll_number_pane = new ChooseRollNumberPane(this.observable_game);
        choose_room_to_seal_pane = new ChooseRoomToSealPane(this.observable_game);
        choose_upgrade_pane = new ChooseUpgradePane(this.observable_game);
        choose_where_to_move_member_pane = new ChooseWhereToMoveMemberPane(this.observable_game);
        choose_which_trap_to_put_pane = new ChooseWhichTrapToPutPane(this.observable_game);
        health_tracker_token_location_mode_pane = new HealthTrackerTokenLocationModePane(this.observable_game);
        hull_tracker_token_location_mode_pane = new HullTrackerTokenLocationModePane(this.observable_game);
        member_position_selection_mode_pane = new MemberPositionSelectionModePane(this.observable_game);
        member_selection_mode_pane = new MemberSelectionModePane(this.observable_game);
        put_token_in_health_tracker_pane = new PutTokenInHealthTrackerPane(this.observable_game);
        put_token_in_hull_tracker_pane = new PutTokenInHullTrackerPane(this.observable_game);
        select_member_pane = new SelectMemberPane(this.observable_game);
        select_member_position_pane = new SelectMemberPositionPane(this.observable_game);
        which_trap_to_detonate_pane = new WhichTrapToDetonatePane(this.observable_game);
    }

    private void configuration()
    {
        setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, BorderStroke.THICK)));
        setStyle("-fx-padding: 10;");
        setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        StateInterface actual_state = observable_game.getState();

        if(actual_state instanceof MemberSelectionMode) { setCenter(member_selection_mode_pane); }
        else if(actual_state instanceof SelectMember) { setCenter(select_member_pane); }
        else if(actual_state instanceof MemberPositionSelectionMode) { setCenter(member_position_selection_mode_pane); }
        else if(actual_state instanceof HullTrackerTokenLocationMode) { setCenter(hull_tracker_token_location_mode_pane); }
        else if(actual_state instanceof PutTokenInHullTracker) { setCenter(put_token_in_hull_tracker_pane); }
        else if(actual_state instanceof HealthTrackerTokenLocationMode) { setCenter(health_tracker_token_location_mode_pane); }
        else if(actual_state instanceof PutTokenInHealthTracker) { setCenter(put_token_in_health_tracker_pane); }
        else if(actual_state instanceof ChooseAction) { setCenter(choose_action_pane); }
        else if(actual_state instanceof ChooseIfDebug) { setCenter(choose_if_debug_pane); }
        else if(actual_state instanceof ChooseMemberToMove) { setCenter(choose_member_to_move_pane); }
        else if(actual_state instanceof ChooseWhereToMoveMember) { setCenter(choose_where_to_move_member_pane); }
        else if(actual_state instanceof ChooseMemberWhoAttacks) { setCenter(choose_member_who_attacks_pane); }
        else if(actual_state instanceof ChooseMemberWhoPutsTrap) { setCenter(choose_member_who_puts_trap_pane); }
        else if(actual_state instanceof ChooseRollNumber) { setCenter(choose_roll_number_pane); }
        else if(actual_state instanceof ChooseRoomToSeal) { setCenter(choose_room_to_seal_pane); }
        else if(actual_state instanceof ChooseUpgrade) { setCenter(choose_upgrade_pane); }
        else if(actual_state instanceof SelectMemberPosition) { setCenter(select_member_position_pane); }
        else if(actual_state instanceof ChooseWhichTrapToPut) { setCenter(choose_which_trap_to_put_pane); }
        else if(actual_state instanceof WhichTrapToDetonate) { setCenter(which_trap_to_detonate_pane); }

        return;
    }
}