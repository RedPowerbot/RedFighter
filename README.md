RedFighter
/**
==========

RedFighter by RedDevil for RSBot

Tasks
-----------
1) Finish and attach antiban's
    - Find a better way to randomize the "TimedAntiban" class
    - Make thread work safer.
2) Work on the bank support in general
	- Get a working "BankingInstructions" class
	- Add banking support to Gui <--- Huge project
	- Remove unnecessary interfaces and abstract classes.
3) Incorporate break handling support to script and gui.
	- Make the timing more efficent.
	- Add a clock-based break handler
		- Create a 'radial-clock' in gui to select times. <--- Huge project.
		- Create a 'time-frame' object to help with time handling.
		- Create a effecient paint handling class to draw the clock on the game.
4) Clean up all the enums in com.data.enums.*
5) Add Image links to the Images class.
6) Give all "ScriptLoop"s their own "PaintListener.onRepaint" support.
	- Create the "ClientPainter" class to make for an easier control over paint certain
		objects in the game client.
	- (Idea) Creating a "ClientData" class to hold all the data, could clean up all the
		methods inside all the "ScriptLoop"s.
		~ This was a feature in previous ATTEMPTS at getting this script made.
7) Creating an editable JTree for handling properties automatically, rather than having 
	to hard code a gui for them.
	~ "TreeCellEditor" is probably the best approach.
8) General Gui clean up
	- (Add) Options for painting certain client objects.
	- (Add) Prayer altar selecting lists. (Like npc selection lists)
	- (Add Support) For rare drop list inside "MonsterWikiPanels"
	- (Test) Configurations more. {I'm really worried about this part of the gui. 
		I feel like it's a huge feature that hasn't had enough time spent on it}
	
	
**/	
	
	
	
/**
 * Punction
 * ----------------------------
 *  *)		Task
 *   - 		Subtask
 *  (**) 	Subtask title
 * 	{**} 	Personal quote
 *   ~ 		Suggestion to self
**/		