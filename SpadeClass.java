// The "SpadeClass" class.
//Anna Lai
// 12/9/2016

import hsa.Console;
import java.awt.*;

class SpadeClass extends SuitClass
{
    public SpadeClass ()
    {
	super ();
	super.setColor (Color.black);
    }



    public void draw (Console c)
    {
	int iPointsX[] = new int [5];
	int iPointsY[] = new int [5];

	iPointsX [0] = getCenterX () - getWidth () / 2;
	iPointsY [0] = getCenterY ();
	iPointsX [1] = getCenterX () + getWidth () / 2;
	iPointsY [1] = getCenterY ();
	iPointsX [2] = getCenterX ();
	iPointsY [2] = getCenterY () - getHeight () / 2;
	iPointsX [3] = getCenterX () - getWidth () / 2;
	iPointsY [3] = getCenterY () - getHeight () / 4;
	iPointsX [4] = getCenterX ();
	iPointsY [4] = getCenterY () - getHeight () / 4;

	int triPointsX[] = new int [3];
	int triPointsY[] = new int [3];

	triPointsX [0] = getCenterX ();
	triPointsY [0] = getCenterY ();
	triPointsX [1] = getCenterX () - getWidth () / 8;
	triPointsY [1] = getCenterY () + getHeight () / 2;
	triPointsX [2] = getCenterX () + getWidth () / 8;
	triPointsY [2] = getCenterY () + getHeight () / 2;

	c.setColor (getColor ());
	c.fillArc (iPointsX [3], iPointsY [3], getWidth () / 2, getHeight () / 2, 180, 180);
	c.fillArc (iPointsX [4], iPointsY [4], getWidth () / 2, getHeight () / 2, 180, 180);
	c.fillPolygon (iPointsX, iPointsY, 3);
	c.fillPolygon (triPointsX, triPointsY, 3);
    }


    public void draw (Graphics g)
    {
	int iPointsX[] = new int [5];
	int iPointsY[] = new int [5];

	iPointsX [0] = getCenterX () - getWidth () / 2;
	iPointsY [0] = getCenterY ();
	iPointsX [1] = getCenterX () + getWidth () / 2;
	iPointsY [1] = getCenterY ();
	iPointsX [2] = getCenterX ();
	iPointsY [2] = getCenterY () - getHeight () / 2;
	iPointsX [3] = getCenterX () - getWidth () / 2;
	iPointsY [3] = getCenterY () - getHeight () / 4;
	iPointsX [4] = getCenterX ();
	iPointsY [4] = getCenterY () - getHeight () / 4;

	int triPointsX[] = new int [3];
	int triPointsY[] = new int [3];

	triPointsX [0] = getCenterX ();
	triPointsY [0] = getCenterY ();
	triPointsX [1] = getCenterX () - getWidth () / 8;
	triPointsY [1] = getCenterY () + getHeight () / 2;
	triPointsX [2] = getCenterX () + getWidth () / 8;
	triPointsY [2] = getCenterY () + getHeight () / 2;

	g.setColor (getColor ());
	g.fillArc (iPointsX [3], iPointsY [3], getWidth () / 2, getHeight () / 2, 180, 180);
	g.fillArc (iPointsX [4], iPointsY [4], getWidth () / 2, getHeight () / 2, 180, 180);
	g.fillPolygon (iPointsX, iPointsY, 3);
	g.fillPolygon (triPointsX, triPointsY, 3);
    }
} // SpadeClass class
