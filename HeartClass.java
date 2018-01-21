// The "HeartClass" class.
import hsa.Console;
import java.awt.*;

class HeartClass extends SuitClass
{
    public HeartClass ()
    {
	super ();
	super.setColor (Color.red);
    }


    public void setColor (Color c)
    {
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
	iPointsY [2] = getCenterY () + getHeight () / 2;
	iPointsX [3] = getCenterX () - getWidth () / 2;
	iPointsY [3] = getCenterY () - getHeight () / 4;
	iPointsX [4] = getCenterX ();
	iPointsY [4] = getCenterY () - getHeight () / 4;

	c.setColor (getColor ());

	c.fillArc (iPointsX [3], iPointsY [3], getWidth () / 2, getHeight () / 2, 0, 180);
	c.fillArc (iPointsX [4], iPointsY [4], getWidth () / 2, getHeight () / 2, 0, 180);
	c.fillPolygon (iPointsX, iPointsY, 3);


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
	iPointsY [2] = getCenterY () + getHeight () / 2;
	iPointsX [3] = getCenterX () - getWidth () / 2;
	iPointsY [3] = getCenterY () - getHeight () / 4;
	iPointsX [4] = getCenterX ();
	iPointsY [4] = getCenterY () - getHeight () / 4;

	g.setColor (getColor ());

	g.fillArc (iPointsX [3], iPointsY [3], getWidth () / 2, getHeight () / 2, 0, 180);
	g.fillArc (iPointsX [4], iPointsY [4], getWidth () / 2, getHeight () / 2, 0, 180);
	g.fillPolygon (iPointsX, iPointsY, 3);


    }
}

