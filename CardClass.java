// The "CardClass" class.
//Anna Lai
// 12/12/2016

import java.awt.*;
import javax.swing.*;
import java.net.URL;
import hsa.Console;


public class CardClass extends ShapeClass
{
    // the variables belonge to this class only
    private int value;
    private ShapeClass cardSuit;    // note: i don't really need it
    private String Size;
    private String Suit;
    private boolean isShowing;
    private Color bColour;
    private int column;
    private int row;
    private boolean isAce;
    private boolean isInside;


    // constructor //
    public CardClass ()
    {
	super ();
	setFaceValue (1);
	Size = "small";
	Suit = "heart";
	isShowing = true;
	setColor (Color.black);
	bColour = Color.green;
	setHeight (100);
	setWidth (getHeight () * 7 / 10);
	column = -1;
	row = -1; // it is not present yet
	isAce = false;
	isInside = false;

    }


    // Communicators
    public void setIsAce (boolean ace)
    {
	isAce = ace;
    }


    public void setColumn (int number)
    {
	if (number <= 3 && number >= 0)
	{
	    column = number;
	}
    }


    public void setRow (int number)
    {
	if (number <= 3 && number >= 0)
	{
	    row = number;
	}
    }


    public void setFaceValue (int number)
    {
	if (number <= 12 && number >= 0)
	{
	    value = number;
	}
    }


    public void rollFaceValue ()
    {
	value = (int) Math.floor (Math.random () * 13); // 0 to 12

    }


    public void setColor (Color cNewColor)  // override
    {

    }


    public void setIsinside (boolean b)  // for the draw only
    {
	isInside = b;
    }


    public int getFaceValue ()
    {
	return value;
    }


    public String getSuit ()  // override
    {
	return Suit;
    }


    public Color getbColour ()  // override
    {
	Color colo;
	colo = bColour;
	return colo;
    }


    // for the game
    public int getColumn ()
    {
	return column;

    }


    public int getRow ()  //game
    {
	return row;
    }


    public boolean getIsAce ()  // game
    {
	return isAce;
    }


    public void setWidth (int iNewWidth)  // override
    {
    }


    public void setHeight (int iNewHeight)  // override
    {
    }


    public void setShow (boolean show)
    {
	isShowing = show;
    }


    public boolean getShow ()
    {
	return isShowing;
    }


    // Set size
    public void setSize (String size)
    {
	// %size := Rand.Int (1, 4) % the size  of the card has to follow big small or

	Size = size;
	if (Size.equals ("small"))
	{
	    setHeight (60);
	}


	else if (Size.equals ("medium"))
	{
	    setHeight (80);
	}


	else if (Size.equals ("large"))
	{
	    setHeight (100);
	}


	else if (Size.equals ("extra large")) // extra large
	{
	    setHeight (120);
	}

	setWidth (getHeight () * 7 / 10);
    }


    //%%%%%%%% the procudure of set suit!
    public void setSuit (String suit)
    {
	Suit = suit;
	if (suit.equals ("diamond"))
	{
	    DiamondClass sSuit = new DiamondClass ();
	    cardSuit = sSuit;
	    super.setColor (Color.red);  //% you dont have the choice to pick the colours
	    setColor (Color.red);  //% a firm line to ensure the colour changes
	}
	else if (suit.equals ("club"))
	{
	    ClubClass sSuit = new ClubClass ();
	    cardSuit = sSuit;
	    super.setColor (Color.black);  //% you dont have the choice to pick the colours
	    setColor (Color.black);  //% a firm line to ensure the colour changes
	}
	else if (suit.equals ("heart"))
	{
	    HeartClass sSuit = new HeartClass ();
	    cardSuit = sSuit;
	    super.setColor (Color.red);  //% you dont have the choice to pick the colours
	    setColor (Color.red);  //% a firm line to ensure the colour changes
	}
	else if (suit.equals ("spade"))
	{
	    SpadeClass sSuit = new SpadeClass ();
	    cardSuit = sSuit;
	    super.setColor (Color.black);  //% you dont have the choice to pick the colours
	    setColor (Color.black);  //% a firm line to ensure the colour changes
	}

	cardSuit.setHeight (getHeight () / 4);
    }



    // Method draw right here!!
    public void draw (Console c)
    {

	int leftdownX = getCenterX () - getWidth () / 2;
	int leftdownY = getCenterY () - getHeight () / 2;
	if (isShowing == true)
	{
	    c.setColor (Color.white);
	    c.fillRoundRect (leftdownX, leftdownY, getWidth (), getHeight (), 15, 15);
	    c.setColor (super.getColor ());
	    c.drawRoundRect (leftdownX, leftdownY, getWidth (), getHeight (), 15, 15);

	    //% the suit
	    setSuit (Suit);
	    cardSuit.setColor (getColor ());
	    cardSuit.setCenter (getCenterX (), getCenterY ());
	    cardSuit.draw (c);

	    //the face value at the lest corner
	    String faceValue[] = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	    int fontsize = getHeight () / 5;
	    Font f1 = new Font ("SanSerif", Font.PLAIN, fontsize); //no change at all time !!
	    c.setFont (f1);
	    c.drawString (faceValue [value], (getCenterX () - (getWidth () / 7) * 3), (getCenterY () - getHeight () / 3));
	} // end if
	else // the back side
	{
	    c.setColor (bColour);
	    c.fillRoundRect (leftdownX, leftdownY, getWidth (), getHeight (), 15, 15);

	}
    }


    public void draw (Graphics g)
    {

	int leftdownX = getCenterX () - getWidth () / 2;
	int leftdownY = getCenterY () - getHeight () / 2;
	if (isShowing == true)
	{

	    g.setColor (Color.white);
	    g.fillRoundRect (leftdownX, leftdownY, getWidth (), getHeight (), 15, 15);

	    g.setColor (super.getColor ());

	    g.drawRoundRect (leftdownX, leftdownY, getWidth (), getHeight (), 15, 15);


	    //% the suit
	    setSuit (Suit);
	    cardSuit.setColor (getColor ());
	    cardSuit.setCenter (getCenterX (), getCenterY ());
	    cardSuit.draw (g);

	    //the face value at the lest corner
	    String faceValue[] = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
	    int fontsize = getHeight () / 5;
	    Font f1 = new Font ("SanSerif", Font.PLAIN, fontsize); //no change at all time !!
	    g.setFont (f1);
	    g.drawString (faceValue [value], (getCenterX () - (getWidth () / 7) * 3), (getCenterY () - getHeight () / 3));

	    // the selectied card
	    if (isInside == true) // draw the outer layer in green so that players konw the card  is selected
	    {
		g.setColor (Color.green);
		g.drawRoundRect (leftdownX - 1, leftdownY - 1, getWidth () + 2, getHeight () + 2, 15, 15);
	    }


	} // end if
	else // the back side
	{
	    g.setColor (bColour);
	    g.fillRoundRect (leftdownX, leftdownY, getWidth (), getHeight (), 15, 15);

	    g.setColor (Color.blue);
	    g.drawRoundRect (leftdownX, leftdownY, getWidth (), getHeight (), 15, 15);
	}
    }


    // %%%%%%%%%%%%% check is inside
    public boolean isPointInside (int px, int py)
    {
	if ((px >= getCenterX () - getWidth ()/2) && (px <= getCenterX () + getWidth ()/2)
		&& (py >= getCenterY () - getHeight ()/2) && (py <= getCenterY () + getHeight ()/2))
	{
	    return true;
	}
	return false;

    }



    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Draw method

} // CardClass class
