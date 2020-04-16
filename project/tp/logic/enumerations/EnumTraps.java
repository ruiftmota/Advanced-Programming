package tp.logic.enumerations;

public enum EnumTraps
{
    PARTICLE_DISPERSER(1),
    ORGANIC_DETONATOR(2);

    private int number = 0;

    EnumTraps(int number)
    {
        this.number = number;
    }

    public int getNumber()
    {
        return this.number;
    }
}