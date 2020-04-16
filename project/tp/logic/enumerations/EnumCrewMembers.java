package tp.logic.enumerations;

public enum EnumCrewMembers
{
    DOCTOR(1, "Doctor"),
    COMMS_OFFICER(2, "Comms Officer"),
    RED_SHIRT(3, "Red Shirt"),
    SCIENCE_OFFICER(4, "Science Officer"),
    ENGINEER(5, "Engineer"),
    CAPTAIN(6, "Captain"),
    COMMANDER(7, "Commander"),
    TRANSPORTER_CHIEF(8, "Transporter Chief"),
    MORAL_OFFICER(9, "Moral Officer"),
    SECURITY_OFFICER(10, "Security Officer"),
    NAVIGATION_OFFICER(11, "Navigation Officer"),
    SHUTTLE_PILOT(12, "Shuttle Pilot");

    private int number = 0;
    private String name = null;

    EnumCrewMembers(int number, String name)
    {
        this.number = number;
        this.name = new String(name);
    }

    public int getNumber()
    {
        return this.number;
    }

    public String getName()
    {
        return new String(name);
    }

}