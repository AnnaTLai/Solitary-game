// The "StdDeckClass" class.// Anna Lai
import java.awt.*;
import hsa.Console;
public class StdDeckClass extends DeckClass
{

    public StdDeckClass ()
    {
	setCenter (150, 600);

    }


    public StdDeckClass (int x, int y)  //useful the in the game!
    {
	super.setCenter (x, y);
    }


    private String[] suit = {"diamond", "club", "heart", "spade"};
    {
	for (int s = 0 ; s < 4 ; s++) // 0-3, 4 intotal
	{
	    for (int f = 0 ; f < 13 ; f++)
	    {
		CardClass oCard = new CardClass ();
		oCard.setSuit (suit [s]);
		oCard.setFaceValue (f);
		oCard.setShow (false);
		if (f == 0) // for the game
		{
		    oCard.setIsAce (true);
		}

		super.insert (oCard, 0);
		oCard = null;

	    }
	}
    } // StdDeckClass class
}
