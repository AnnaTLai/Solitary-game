// The "UseDeckClass" class.
import java.awt.*;
import hsa.Console;

public class UseDeckClass
{

    public static void main (String[] args)
    {
	Console c = new Console ();
	StdDeckClass d1 = new StdDeckClass();
	StdDeckClass d2 = new StdDeckClass ();

	d1.shuffle ();
	d2.shuffle ();

	for (int i = 0 ; i < 52 ; i++) //0-51, 52 all in total
	{
	
	d1.draw(c);
	d2.setCenter (d2.getCenterX(), d2.getCenterY()-200);
	d2.draw (c);
	
	
	
	}




	// Place your program here.  'c' is the output console
    } // main method
} // UseDeckClass class
