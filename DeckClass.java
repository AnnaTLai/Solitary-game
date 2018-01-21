// The "DeckClass" class.
//Anna Lai
// 12/15/2016
import java.awt.*;
import java.util.*;
import hsa.Console;


public class DeckClass extends ShapeClass
{
    //CardClass is imported

    //The declarations and globals
    // static int MaxCardNum = 52; // the max card no is constant`
    private int rSuit;
    private boolean isFind;
    protected Vector cardsArray;


    // constructors
    public DeckClass ()
    {
	super.setCenter (150, 600);
	cardsArray = new Vector (1, 1);
    }


    public DeckClass (int x, int y)  //useful the in the game!
    {
	super.setCenter (x, y);
	cardsArray = new Vector (1, 1);
    }


    // Procedures

    public void insert (CardClass c, int pos)  // the position??
    {
	if (getCardNums () >= pos || pos < 0)
	{
	    cardsArray.insertElementAt (c, pos);
	}
	else
	{
	    cardsArray.addElement (c);
	}
    }


    public void replace (int pos, CardClass c)  // the position??
    {
	cardsArray.setElementAt (c, pos);
	//System.out.println ("replaced");// test line

    }


    public CardClass remove (int pos)
    {

	if (getCardNums () >= pos || pos >= 0)
	{
	    CardClass temp; // of the card itself is null (empty card from deck one)
	    temp = (CardClass) cardsArray.elementAt (pos);
	    
	    if (temp == null)
	    {
		return null;
	    }
	    else
	    {
		cardsArray.removeElementAt (pos);
		return (CardClass) temp;
	    }
	}
	else
	{
	    //System.out.println ("There is no card in position" + pos);
	    return null; // null stilll occupies a position
	}
    }

    public int getCardNums ()  //
    {
	return cardsArray.size (); // the number of cards
    }


    public CardClass getCard (int number)
    {
	CardClass temp;
	temp = (CardClass) cardsArray.elementAt (number);

	return (CardClass) temp;
    }



    public void shuffle ()  // the normal shuffle
    {
	if (getCardNums () != 0)  // card array not empty
	{
	    for (int i = 0 ; i < getCardNums () + 1 ; i++) // 0-52, 52 cards just shuffled! 53 times
	    {
		int randInt;
		int size;
		size = getCardNums ();
		randInt = (int) Math.floor (Math.random () * size); // zero to max num of cards
		CardClass c = remove (randInt);
		insert (c, 0); // starts at 0!!
	    }

	}

    }



    public void shuffle (String d)  // for d2 new shuffle
    {
	if (getCardNums () >= 16)  // card array not bigger than  16
	{

	    for (int i = 0 ; i < (getCardNums () - 1) / 2 ; i++) // remove 16 cards from the old deck, and give the card coordinates
	    {

		int randInt, randInt2;
		int size; // how many cards are there
		size = getCardNums ();
		randInt = (int) Math.floor (Math.random () * size); // zero to max num of cards
		randInt2 = (int) Math.floor (Math.random () * (size - 1)); // zero to max num of cards
		CardClass cardA = getCard (randInt);
		CardClass cardB = getCard (randInt2);

		// For the game and ace aquare, swap!
		int col, row, x, y;
		col = cardA.getColumn ();
		row = cardA.getRow ();
		x = cardA.getCenterX ();
		y = cardA.getCenterY ();

		cardA.setColumn (cardB.getColumn ());
		cardA.setRow (cardB.getRow ());
		cardA.setCenter (cardB.getCenterX (), cardB.getCenterY ());

		cardB.setColumn (col);
		cardB.setRow (row);
		cardB.setCenter (x, y);

		cardsArray.set (randInt, cardA);
		cardsArray.set (randInt2, cardB);



	    }

	}

    }


    public int isCardInside (int x, int y)  // retrun the card that is inside the deck
    {
	CardClass temp;
	for (int i = 0 ; i < getCardNums () ; i++)
	{
	    temp = (CardClass) cardsArray.elementAt (i);
	    temp.setIsinside (false);
	}

	for (int i = 0 ; i < getCardNums () ; i++)
	{
	    //System.out.println ("Found card" +i);

	    temp = (CardClass) cardsArray.elementAt (i);
	    //System.out.println ("test card run in array" + i); // test statement

	    if (temp.isPointInside (x, y) == true)
	    {
		//System.out.println ("Found card" +i);
		temp.setIsinside (true); // for drawing the card
		return i;
	    }
	    temp = null; // to be sure
	}
	return -1; // not inside
    }


    public boolean sameRow (CardClass A, CardClass B)
    {
	if (A.getRow () == B.getRow ())
	{
	    return true;
	}


	else
	{
	    return false;
	}
    }


    public boolean sameColumn (CardClass A, CardClass B)
    {
	if (A.getColumn () == B.getColumn ())
	{
	    return true;
	}


	else
	{
	    return false;
	}
    }



    // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    public void draw (Console c)  // no need
    {

    }


    public void erase (Console c)  // no need
    {
    }


    public void draw (Graphics g)
    {
	if (getCardNums () != 0)
	{

	    for (int i = 0 ; i < getCardNums () ; i++)
	    {
		CardClass card;
		card = (CardClass) cardsArray.get (i); // draw the first one !!
		//card.setShow (true); // test line
		card.setCenter (getCenterX (), getCenterY () + 5 * i);
		card.draw (g);
	    }
	}


	else
	{
	    g.setColor (getColor ());
	    g.drawRoundRect (getCenterX () - getHeight () / 2, getCenterY () - getHeight () / 2, getCenterX () +
		    getHeight () / 2, getCenterY () + getHeight () / 2, 15, 15);
	}
    }


    public void draw (Graphics g, String b)  // used by d2 overload method, imput any text inside
    {
	if (getCardNums () != 0)
	{

	    for (int i = 0 ; i < getCardNums () ; i++)
	    {
		CardClass card;
		card = (CardClass) cardsArray.get (i); // draw the first one !!
		//card.setShow (true); // test line
		//card.setCenter (getCenterX (), getCenterY ()+ 5*i);
		card.draw (g);
	    }
	}


	else
	{
	    g.setColor (getColor ());
	    g.drawRoundRect (getCenterX () - getHeight () / 2, getCenterY () - getHeight () / 2, getCenterX () +
		    getHeight () / 2, getCenterY () + getHeight () / 2, 15, 15);
	}
    }


    public void erase (Graphics g)
    {
	if (getCardNums () != 0)
	{
	    CardClass card;
	    card = (CardClass) cardsArray.get (1); // draw the first one !!
	    card.setShow (false);
	    card.setCenter (getCenterX (), getCenterY ());
	    card.erase (g);

	}
    }


    /// The Methods



} // DeckClass class


