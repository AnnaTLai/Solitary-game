// The "Main" class.
//Anna Lai
// This is the main applet of my game

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import sun.audio.*;
import java.io.FileReader;
import java.io.BufferedReader;

public class Main extends Applet implements ActionListener, MouseListener, MouseMotionListener
{
    // Place instance variables here
    Graphics g;   // declares a graphics canvas for drawing
    BorderLayout lm = new BorderLayout ();
    AudioClip gong;
    StdDeckClass d1; // my main deck
    DeckClass d2; // The 16 card game interface

    //Declare my buttons Start, instruction page
    //Display time in s and points(button) gain
    Frame frame = new Frame ("Instruction Page");
    Button buttonStart = new Button ("Start");
    Button buttonInstructions = new Button ("Instructions");
    //Button buttonShuffle = new Button ("Shuffle"); // the shuffle does not match the goal of the game
    Label labelTime = new Label ("Time left in Seconds:");
    TextField textFieldTime = new TextField (10);
    Label labelPoints = new Label ("Points gain:"); //each switch worths 10 points! (since at the end of the game, not every time you can get the cards fully eliminated)
    TextField textFieldPoints = new TextField (15);

    // The check boxes Muisc on/off
    CheckboxGroup musicGroup = new CheckboxGroup (); // for music
    Checkbox musicGroupOn = new Checkbox ("Music on", musicGroup, true);
    Checkbox musicGroupOff = new Checkbox ("Music Off", musicGroup, false);

    Choice choiceTime = new Choice (); // for time limit, can only choose once per game


    // Variables
    private boolean isTimed = false; //Is the time limit set in this game?
    private boolean musicOn = true;
    private int time;
    private int points;
    private long start, end, diff;


    //*******************************  For mouse pressed
    // global variable for comparison. card A
    CardClass cA;
    private int cardApos = -1; // use in remove
    private int cardAcolumn = -1;
    private int cardArow = -1;
    private int cardAx = -1;
    private int cardAy = -1;
    private String cardASuit = "None"; // save the location of card A for comparison
    private boolean CardAisAce = false;
    //**********************************

    public void init ()
    {
	//bg music start in default
	(new GameMusic ()).start ();
	// new decks in Place!!
	d1 = new StdDeckClass (750, 80);
	d2 = new DeckClass (500, 80);

	setSize (800, 600);
	setBackground (Color.orange); // color bg is very important!
	Panel pDraw = new Panel ();
	add ("Center", pDraw);
	setLayout (lm);
	g = pDraw.getGraphics ();

	Panel pdeNorth = new Panel (); // upper pannel!
	GridBagLayout gbl = new GridBagLayout ();
	pdeNorth.setLayout (gbl);
	GridBagConstraints gbc = new GridBagConstraints ();
	gbc.weightx = 2;
	gbc.weighty = 1;

	gbc.gridx = 0;
	gbc.gridy = 0;
	gbl.setConstraints (buttonStart, gbc);
	pdeNorth.add (buttonStart);

	gbc.gridx = 1;
	gbc.gridy = 0;
	gbl.setConstraints (buttonInstructions, gbc);
	pdeNorth.add (buttonInstructions);

	gbc.gridx = 2;
	gbc.gridy = 0;
	choiceTime.add ("1 min");
	choiceTime.add ("2 mins");
	choiceTime.add ("3 mins");
	pdeNorth.add (choiceTime);

	gbc.gridx = 3;
	gbc.gridy = 0;
	gbl.setConstraints (musicGroupOn, gbc);
	pdeNorth.add (musicGroupOn);
	gbc.gridx = 4;
	gbc.gridy = 0;
	gbl.setConstraints (musicGroupOff, gbc);
	pdeNorth.add (musicGroupOff);
	add ("North", pdeNorth);

	Panel pdeSouth = new Panel (); // lower pannel!!
	GridBagLayout gbl2 = new GridBagLayout ();
	pdeSouth.setLayout (gbl2);
	GridBagConstraints gbc2 = new GridBagConstraints ();
	gbc2.weightx = 2;
	gbc2.weighty = 1;
	gbc2.gridx = 0;
	gbc2.gridy = 0;
	gbl2.setConstraints (labelTime, gbc2);
	pdeSouth.add (labelTime);
	gbc2.gridx = 1;
	gbc2.gridy = 0;
	gbl2.setConstraints (textFieldTime, gbc2);
	pdeSouth.add (textFieldTime);

	gbc2.gridx = 2;
	gbc2.gridy = 0;
	gbl2.setConstraints (labelPoints, gbc2);
	pdeSouth.add (labelPoints);
	gbc2.gridx = 3;
	gbc2.gridy = 0;
	gbl2.setConstraints (textFieldPoints, gbc2);
	pdeSouth.add (textFieldPoints);

	// gbc2.gridx = 4;
	// gbc2.gridy = 0;
	// gbl2.setConstraints (buttonShuffle, gbc2);
	// pdeSouth.add (buttonShuffle); // the shuffle does not match the goal of the game
	add ("South", pdeSouth);

	//listeners
	buttonStart.addActionListener (this);
	buttonInstructions.addActionListener (this);
	// buttonShuffle.addActionListener (this);// the shuffle does not match the goal of the game
	addMouseListener (this);
	addMouseMotionListener (this);

	// Place the body of the initialization method here
    } // init method


    public void paint (Graphics g)
    {
	d1.draw (g);

	if (d2.getCardNums () > 0)
	{
	    // d2.setCenter (500, 80);// test lines
	    d2.draw (g, "Ace Square");
	}

	// Place the body of the drawing method here
    } // paint method


    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% The action listener group

    public boolean action (Event e, Object o)
    {
	handleAction (e.target);
	return true;
    }


    public void actionPerformed (ActionEvent e)
    {
	handleAction (e.getSource ());
    }


    public void handleAction (Object objSource)  // the buttons are clicked!
    {
	if (objSource instanceof Button)
	{
	    if (objSource == buttonStart)
	    {
		textFieldPoints.setText ("New Game");
		// the game operates under this button
		isTimed = false;
		points = 0;

		//Time calculation part
		time = 0;

		// Add the deck in Place!! xxd
		d1 = new StdDeckClass (750, 80);
		d1.shuffle ();
		d2 = new DeckClass ();


		for (int row = 3 ; row >= 0 ; row--)  // remove 16 cards from the old deck, and give the card coordinates
		{

		    for (int col = 3 ; col >= 0 ; col--)
		    {
			// remove
			CardClass card = d1.remove (0);
			card.setColumn (col);
			card.setRow (row);
			card.setCenter (550 - 110 * col, 450 - 110 * row); // Frist in last out
			card.setShow (true);

			//need a overloading method for deck class here !!

			d2.insert (card, 0);

		    }
		}


		repaint ();

	    }
	    if (objSource == buttonInstructions)
	    {
		frame.setSize (400, 400); // just in case
		frame.setBackground (Color.yellow);
		frame.addWindowListener (new WindowAdapter ()
		{

		    public void windowClosing (WindowEvent e)
		    {
			//System.exit (0);
			frame.setVisible (false);
		    }
		}
		);

		// Label text = new Label ("Hello");
		// text.setText ("Hello Player, Welcome to Anna's Ace Square!");
		// text.setFont (new Font ("Serif", Font.BOLD, 34));
		// text.setLocation (0, 0);
		// frame.add (text);

		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% read from file
		//Name of the file
		String fileName = "instructions.txt";
		try
		{

		    //Create object of FileReader
		    FileReader inputFile = new FileReader (fileName);

		    //Instantiate the BufferedReader Class
		    BufferedReader bufferReader = new BufferedReader (inputFile);

		    //Variable to hold the one line data
		    String line;
		    TextArea textArea = new TextArea (0, 24);
		    //ScrollPane scrollPane = new JScrollPane(textArea);
		    textArea.setEditable (false);


		    // Read file line by line and print on the console
		    while ((line = bufferReader.readLine ()) != null)
		    {
			// line.setFont (new Font ("Serif", Font.BOLD, 16)); // not for string!?
			textArea.append (line + "\n");
		    }
		    //Close the buffer reader
		    bufferReader.close ();
		    frame.add (textArea);
		}
		catch (Exception e)
		{
		    System.out.println ("Error while reading file line by line:" + e.getMessage ());
		}






		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%





		frame.pack ();
		frame.setVisible (true);
	    }
	    // the shuffle does not match the main goal of the game
	    // if (objSource == buttonShuffle)
	    // {
	    //     d2.shuffle ("Ace Square");
	    //     textFieldPoints.setText ("Shuffled!");
	    //     repaint ();
	    // }
	}


	if (objSource instanceof Choice) // Time
	{

	    if (isTimed == false)
	    {
		if (choiceTime.getSelectedIndex () == 0)
		{
		    time = 60; // 1 min
		}
		else if (choiceTime.getSelectedIndex () == 1)
		{
		    time = 120; // 2 min
		}
		else if (choiceTime.getSelectedIndex () == 2)
		{
		    time = 180; // 3 min
		}
		isTimed = true; // cannot set the time again during the game!
		start = System.currentTimeMillis ();
		(new GameTimer ()).start ();
		System.out.println ("Time started!"); // test line

	    }
	} // time

	//*****************************************8
	if (objSource instanceof Checkbox)
	{
	    if (musicGroupOn.getState () == true)
	    {
		if (musicOn == false) // prevent dual sound track
		{
		    musicOn = true;
		    (new GameMusic ()).start ();
		    System.out.println ("Play music!" + musicOn);
		}
	    }
	    if (musicGroupOff.getState () == true)
	    {
		musicOn = false;
		// System.out.println ("Stop music checkbox right!" + musicOn);
	    }
	}



    }




    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% The mouse group
    public void mouseClicked (MouseEvent e)
    {
    }


    public void mouseEntered (MouseEvent e)
    {

    }


    public void mouseExited (MouseEvent e)
    {
    }


    public void mousePressed (MouseEvent e)
    {
	if (time - diff / 1000 > 0) // if there are time left then play
	{
	    int pos = d2.isCardInside (e.getX (), e.getY ());

	    CardClass cB;
	    int cardBrow = -1;
	    int cardBcolumn = -1;
	    String cardBSuit = "None";
	    boolean CardBisAce = false;

	    if (pos <= -1) // check, no card inside
	    {
		textFieldPoints.setText ("No Card Selected!!");
	    }
	    else if (pos > -1) // check if cany card is inside
	    {
		//System.out.println ("card inside");

		if (cardArow == -1 && cardAcolumn == -1) // first card to compare
		{ // only get method will be used here for the cards
		    System.out.println ("first card selected"); // test line

		    cA = d2.getCard (pos);
		    cardAcolumn = cA.getColumn ();
		    cardArow = cA.getRow ();
		    cardASuit = cA.getSuit ();
		    CardAisAce = cA.getIsAce ();
		    cardApos = pos;
		    cardAx = cA.getCenterX ();
		    cardAy = cA.getCenterY ();
		}
		else // where the comparison begins and the points adding!!
		{
		    cB = d2.getCard (pos);
		    cardBcolumn = cB.getColumn ();
		    cardBrow = cB.getRow ();
		    cardBSuit = cB.getSuit (); // info of the second card
		    CardBisAce = cB.getIsAce ();

		    // comparison made here


		    if (cardAcolumn == cardBcolumn && cardArow == cardBrow) // if player selec the same card
		    {
			textFieldPoints.setText ("Same Card!");
		    }

		    else if (cardAcolumn == cardBcolumn || cardArow == cardBrow) // only if the cards are in the same dimension
		    {
			if (cardASuit == cardBSuit && CardAisAce == false && CardBisAce == false)
			{

			    if (d1.getCardNums () > 0) // if there are more cards in deck one
			    {
				// the cards have the same suits and they are not Ace
				points = points + 10;

				// remove the card from the deck, and is replced by two new one
				// replace card A

				// System.out.println ("Match all");
				CardClass newCard = d1.remove (0); // get refill from d1
				newCard.setRow (cardArow);
				newCard.setColumn (cardAcolumn);
				newCard.setCenter (cardAx, cardAy);
				newCard.setShow (true);
				d2.replace (cardApos, newCard); // the position in deck does not matter, only the rows and column matters

				newCard = null; // reduce the declaration of variables.

				// replace card B

				newCard = d1.remove (0); // get refill from d1
				newCard.setRow (cardBrow);
				newCard.setColumn (cardBcolumn);
				newCard.setCenter (cB.getCenterX (), cB.getCenterY ());
				newCard.setShow (true);
				d2.replace (pos, newCard); // the position in deck does not matter, only the rows and column matters

				// if there is no card left in deck one, then deck two will be replaced by null cards.
				// delet the null cards
				// d2.removeNull ();


				System.out.println ("replaced card B " + pos + ", card A " + cardApos);
			    }
			    else
			    {
				CardClass NoUse = d2.remove (cardApos);
				NoUse = null;
				if (cardApos > pos)
				{
				    NoUse = d2.remove (pos);
				}
				else
				{
				    NoUse = d2.remove (pos - 1);
				}
				NoUse = null;



				System.out.println ("No more cards in deck one!");

			    }


			}

			textFieldPoints.setText (String.valueOf (points)); // show your current mark(s)
			if (d2.getCardNums () == 4)
			    // when there are only four cards left, they can be only the Aces
			    // The winning statement
			    {


				textFieldPoints.setText ("You won! points:" + String.valueOf (points));
			    }
		    }

		    // clear this comparison and be ready for the next comparison!
		    // even if they do not match, renew the check value
		    cardAcolumn = -1;
		    cardArow = -1;

		}


	    }
	} // if time

	else
	{
	    textFieldTime.setText ("Time's up!");

	}

	repaint ();
    }


    public void mouseReleased (MouseEvent e)
    {
	//OKtoMove = false;
    }


    public void mouseDragged (MouseEvent e)
    {
	// if (OKtoMove == true)
	// {
	//     d1.setCenter (e.getX (), e.getY ());
	//     d1.draw (g);
	//     repaint ();
	// }

    }


    public void mouseMoved (MouseEvent e)
    {
    }


    class GameTimer extends Thread // don't want the timer to slow the game down
    {

	public void run ()
	{
	    if (isTimed == true)
	    {
		try
		{
		    end = System.currentTimeMillis ();
		    diff = end - start;
		    //  System.out.println ("Time passed is : " + diff / 1000);

		    while (time - diff / 1000 > 0)
		    {
			textFieldTime.setText (String.valueOf (time - diff / 1000) + "s");
			Thread.sleep (1000);
			end = System.currentTimeMillis ();
			diff = end - start;
			//System.out.println ("Time passed is : " + diff / 1000);

		    }

		    textFieldTime.setText ("Time's up!");


		}
		catch (Exception c)
		{
		    System.out.println ("Got an exception!"); // statement need to compelet the logic
		}
	    }
	} // run
    } // inner timer class


    class GameMusic extends Thread
    {
	public void run ()
	{
	    gong = getAudioClip (getDocumentBase (), "bgmusic.wav");

	    while (musicOn == true) // music on ~ and play
	    {
		// play music
		//System.out.println ("Play music!");
		// gong.play ();
		gong.loop (); // another option
	    }
	    if (musicOn == false)
	    {
		System.out.println ("Stop music!");
		gong.stop ();
	    }
	} // run
    } // inner class 2
} // Main class
