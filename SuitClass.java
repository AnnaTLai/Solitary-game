// The "SuitClass" class.
//Anna Lai
// 12/9/2016

import java.awt.*;
import hsa.Console;

public abstract class SuitClass extends ShapeClass
{

    //%%%%%%%%%%%%%%%  the procedures

    public SuitClass ()
    {
	super ();
	setHeight (getHeight ());

    }


    public void setWidth (int ipWidth)
    {
	super.setWidth (ipWidth);
	super.setHeight (getWidth () * 5 / 4);
    }


    public void setHeight (int ipHeight)
    {
	super.setHeight (ipHeight);
	super.setWidth (getHeight () * 4 / 5);
    }



} // SuitClass class
