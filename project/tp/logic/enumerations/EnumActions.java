package tp.logic.enumerations;

public enum EnumActions
{
    CONTINUE("Continue"),
    HEAL_PLAYER("Heal player"),
    REPAIR_SHIP("Repair ship"),
    CHOOSE_DETONATE("Choose detonate"),
    CHOOSE_MOVE("Move"),
    CHOOSE_ATTACK("Attack"),
    CHOOSE_SET_TRAP("Mount trap"),
    CHOOSE_SEAL_ROOM("Seal Room"),
    CHOOSE_SACRIFICE_RED_SHIRT("Sacrifice red shirt");

    private String name = null;

    EnumActions(String name)
    {
        this.name = new String(name);
    }

    public String getName()
    {
        return new String(name);
    }

}