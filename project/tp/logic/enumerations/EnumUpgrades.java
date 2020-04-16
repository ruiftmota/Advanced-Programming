package tp.logic.enumerations;

public enum EnumUpgrades
{
    ADD_ONE_TO_HEALTH(1, 1, "Add one to health"),
    REPAIR_ONE_HULL(2, 1, "Repair one hull point"),
    BUILD_ONE_ORGANIC_DETONATOR(3, 2, "Build Organic Detonator"),
    BUILD_ONE_PARTICLE_DISPERSER(4, 5, "Build Particle Disperser"),
    ADD_ONE_TO_MOVEMENT(5, 4, "Add one to total movement"),
    GAIN_ONE_SEALED_ROOM_TOKEN(6, 5, "Gain sealed room token"),
    GAIN_ONE_EXTRA_ATTACK_DIE(7, 6, "Gain one extra attack die"),
    ADD_ONE_TO_THE_RESULT_OF_ATTACK_DICE(8, 6, "Add one to result of attack dice");

    private int cost = 0;
    private int number = 0;
    private String name = null;

    EnumUpgrades(int number, int cost, String name)
    {
        this.cost = cost;
        this.number = number;
        this.name = name;
    }

    public String getName(){
        return new String(name);
    }

    public int getCost()
    {
        return this.cost;
    }
    public int getNumber()
    {
        return this.number;
    }
}