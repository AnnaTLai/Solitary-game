// The "ShapeClass" class.
// Anna Lai
// 12/9/2016

import java.awt.*;
import hsa.Console;

public abstract class ShapeClass
{
    // global variables for this class
    // encapsulated data
    private int iWidth;
    private int iHeight;
    private int iCentreX;
    private int iCentreY;
    private Color cColor;
    private boolean filled;


    // contructors

    public ShapeClass ()
    {
	iWidth = 80;
	iHeight = 100;
	iCentreX = 100;
	iCentreY = 100;
	cColor = Color.red;
	filled = true;
    }


    public ShapeClass (int iNewWidth, int iNewHeight, int iNewCentreX, int iNewCentreY, Color cNewColor)
    {
	iWidth = iNewWidth;
	iHeight = iNewHeight;
	iCentreX = iNewCentreX;
	iCentreY = iNewCentreY;
	cColor = cNewColor;
    }


    // communicator methods
    public void setWidth (int iNewWidth)
    {
	iWidth = iNewWidth;
    }


    public void setHeight (int iNewHeight)
    {
	iHeight = iNewHeight;
    }


    public void setCenter (int iNewCentreX, int iNewCentreY)
    {
	iCentreX = iNewCentreX;
	iCentreY = iNewCentreY;
    }


    public void setColor (Color cNewColor)
    {

	cColor = cNewColor;
    }


    public int getWidth ()
    {
	return iWidth;
    }


    public int getHeight ()
    {
	return iHeight;
    }


    public int getCenterX ()
    {
	return iCentreX;
    }


    public int getCenterY ()
    {
	return iCentreY;
    }


    public Color getColor ()
    {
	return cColor;
    }


    public void setIsFilled (boolean newfilled)
    {
	filled = newfilled;
    }


    public boolean getIsFilled ()
    {
	return filled;
    }


    // Delay
    public void delay (int iDelayTime)
    {
	long lFinalTime = System.currentTimeMillis () + iDelayTime;
	do
	{
	}
	while (lFinalTime >= System.currentTimeMillis ());

    }


    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% draw and erase

    public abstract void draw (Console c);
    public abstract void draw (Graphics g);

    public void erase (Console c)
    {
	Color cOldColor = getColor ();
	setColor (Color.white);
	draw (c);
	setColor (cOldColor);
    }



    public void erase (Graphics g)
    {
	Color cOldColor = getColor ();
	setColor (Color.white);
	draw (g);
	setColor (cOldColor);
    }


    // main method
} // ShapeClass class
