package tp;

import tp.text_interface.TextInterface;

public class TextInterfaceMain
{
    public static void main(String[] args)
    {
        TextInterface text_interface = new TextInterface();
        text_interface.runGame();

        System.out.println("Program successfully terminated.");

        return;
    }
}
