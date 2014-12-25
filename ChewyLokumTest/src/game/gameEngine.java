package game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.Timer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * gameEngine is a class that opens game,
 * handles players actions and contains the graphics 
 * of the game.
 * 
 *
 */

public class gameEngine extends JFrame implements MouseListener{


	
	private static final long serialVersionUID = 1L;
	private int score;
	private int swapsLeft;
	private int specialSwapsLeft;
	private long time;
	private Level level;
	private Lokum sLokum1;
	private Lokum sLokum2;
	private Board board;	
	private JLabel scoreLabel;
	private JLabel swapsLeftLabel;
	private JLabel requiredScoreLabel;
	private JButton startButton;
	private JComboBox<Integer> levelChooser; 
	private JFrame frame;  
	private JPanel gamePanel; 
	private JPanel startMenu; 
	private JPanel lokumPanel; 
	private JCheckBox specialSwapSelecter;
	private JLabel specialSwapCountLabel;
	private JLabel timeLabel;
	private Timer timer; 
	private Timer gameTimer;
	//Images for representing lokums
	private ImageIcon red = new ImageIcon("Lokums/redone.png");
	private ImageIcon redVStriped = new ImageIcon("Lokums/redVStriped.png");
	private ImageIcon redHStriped = new ImageIcon("Lokums/redHStriped.png");
	private ImageIcon redWrapped = new ImageIcon("Lokums/redWrapped.png");

	private ImageIcon blue = new ImageIcon("Lokums/blueone.png");
	private ImageIcon blueVStriped = new ImageIcon("Lokums/blueVStriped.png");
	private ImageIcon blueHStriped = new ImageIcon("Lokums/blueHStriped.png");
	private ImageIcon blueWrapped = new ImageIcon("Lokums/blueWrapped.png");

	private ImageIcon yellow = new ImageIcon("Lokums/yellowone.png");
	private ImageIcon yellowVStriped = new ImageIcon("Lokums/yellowVStriped.png");
	private ImageIcon yellowHStriped = new ImageIcon("Lokums/yellowHStriped.png");
	private ImageIcon yellowWrapped = new ImageIcon("Lokums/yellowWrapped.png");

	private ImageIcon green = new ImageIcon("Lokums/greenone.png");
	private ImageIcon greenVStriped = new ImageIcon("Lokums/greenVStriped.png");
	private ImageIcon greenHStriped = new ImageIcon("Lokums/greenHStriped.png");
	private ImageIcon greenWrapped = new ImageIcon("Lokums/greenWrapped.png");

	private ImageIcon BOMB = new ImageIcon("Lokums/BOMB.png");

	/**
	 * @param sLokum1   the first selected lokum. 
	 * @param sLokum2 	the second selected lokum.
	 * @param swapsLeft the amount of swaps that is left after player's moves.
	 */

	public gameEngine() {

		frame = new JFrame("ChewyLokumLegend");
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.addMouseListener(this);

		timer = new Timer(100,new AnimationEventListener());
		gameTimer = new Timer(1000,new timeEventListener());

		
		board = new Board(6,4);

		level= new Level(1,20,5,100000,50);
		

		lokumPanel = new JPanel();
		lokumPanel.addMouseListener(this);
		lokumPanel.setLocation((frame.getWidth()/2) - ((board.getWidth()*25)/2), 30);		
		paintLokumPanel();

		gamePanel = constructGamePanel();
		gamePanel.addMouseListener(this);

		startMenu = constructStartMenu();
		startMenu.addMouseListener(this);

		gamePanel.add(constructTopMenu());
		startMenu.add(constructTopMenu());
		/**
		 * Default constructor of gameEngine.
		 * It is currently empty.
		 * Will be modified later.
		 * 
		 */
	}
	/**
	 * 
	 * 
	 */
	public int readGameState(){
		int result = 0;
		try {
			BufferedReader rd = new BufferedReader(new FileReader("gameState.txt"));
			result = Integer.parseInt(rd.readLine());
			rd.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void writeGameState(){
		try {
			BufferedWriter wr = new BufferedWriter(new FileWriter("gameState.txt"));
			wr.write(level.getlevelNumber());
			wr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 *  Method that saves current game.
	 *  @requires saveGame(name)!=null
	 */
	public void saveGame(){
		String fileName=JOptionPane.showInputDialog("Select your file name.");

		JFileChooser chooser = new JFileChooser(); 
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Choose the save directory");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		File dir=null;   
		if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) { 
			dir =  chooser.getSelectedFile();
		}
		else {
			JOptionPane.showMessageDialog(null,"No file Selected!");
			return;
		}

		File newXML=new File(dir.getAbsolutePath()+"//"+fileName+".xml");

		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newXML), "utf-8"));
			bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			bw.write("<game>\n");
			bw.write("<board>\n");
			bw.write("<lokums>\n");
			for (int i = 0; i < board.getHeight(); i++) {
				for (int j = 0; j < board.getWidth(); j++) {
					Lokum lokum = board.get(i, j);
					int xcoord = lokum.getPositionX();
					int ycoord = lokum.getPositionY();
					String color = lokum.getColor();
					String type = lokum.getType();
					bw.write("<lokum>\n");
					bw.write(String.format("<color c=\"%s\"/>\n",color));
					bw.write("<position>\n");
					bw.write(String.format("<xcoord x=\"%d\"/>",xcoord));
					bw.write(String.format("<ycoord y=\"%d\"/>",ycoord));
					bw.write("</position>\n");
					bw.write(String.format("<type t=\"%s\"/>",type));
					bw.write("</lokum>");
				}
			}
			bw.write("</lokums>\n");

			// Implementation does not have obstacles it is constructed for validation xsd file
			bw.write("<obstacles>\n");
			bw.write("<obstacle>\n");
			bw.write(String.format("<color> %s </color>\n","color"));
			bw.write("<position>\n");
			bw.write(String.format("<xcoord> %d </xcoord>\n",0));
			bw.write(String.format("<ycoord> %d </ycoord>\n",0));
			bw.write("</position>\n");
			bw.write("</obstacle>\n");
			bw.write("</obstacles>\n");
			bw.write("</board>\n");
			bw.write(String.format("<goalscore gscore=\"%d\"/>",level.getlevelRequirementScore()));
			bw.write(String.format("<currentscore cscore=\"%d\"/>",score));
			bw.write(String.format("<movesleft swaps=\"%d\"/>",swapsLeft));
			bw.write(String.format("<level levelNumber=\"%d\"/>",level.getlevelNumber()));
			bw.write("</game>\n");

			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param selectedLoad   selected save game's file which will be loaded.
	 * @requires It requires a saved game.
	 */

	public void loadGame (){
		JFileChooser chooser=new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setFileSelectionMode(JFileChooser.CUSTOM_DIALOG);
		File XMLFile = null;

		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
			XMLFile= chooser.getSelectedFile();
		}
		else {
			JOptionPane.showMessageDialog(null,"No file Selected!");
			XMLFile=null;
		}
		if(XMLFile==null){
			return;
		}

		setPreferredSize(new Dimension(200, 200));
		getContentPane().add(chooser);
		File XSDFile = new File("game.xsd");
		Document XMLDocument = XMLDocumentConstructor(XMLFile,XSDFile);
		XMLParser(XMLDocument);
	}
	private Document XMLDocumentConstructor(File XMLFile,File XSDFile){

		Document xmlDocument=null;

		//create document builder using document builder factory
		DocumentBuilderFactory builderFactory =
				DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = builderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace(); 
			JOptionPane.showMessageDialog(null,"ParserConsiguration Error!");
			System.exit(0);
		}

		//parse xml using builder
		try {
			xmlDocument = builder.parse(XMLFile);
		} catch (SAXException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"XML File is not proper! Please choose a valid XML file.");
			loadGame();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"XML filepath is invalid! Please choose a valid XML file.");
			loadGame();
		}

		// NOTE:Since validation no longer required we dont validate XML file

		return xmlDocument;		
	}

	public void XMLParser(Document XMLDoc){

		Element root = XMLDoc.getDocumentElement();
		NodeList nodes = root.getChildNodes();

		for (int i = 0; i < nodes.getLength(); i++) {

			Node node = nodes.item(i);

			if(node instanceof Element){
				Element child = (Element) node;

				if(child.getNodeName() == "board"){
					NodeList boardNodes = child.getChildNodes();
					for(int j = 0; j<boardNodes.getLength(); j++){

						Node childNode = boardNodes.item(j);
						if(childNode.getNodeName() == "lokums"){
							NodeList lokums = childNode.getChildNodes();
							for (int k = 0; k < lokums.getLength(); k++) {
								Lokum savedLokum = null;
								int coordX = 0;
								int coordY = 0;
								String type = "";
								String color = "";

								Node lokumNode = lokums.item(k);
								if(lokumNode instanceof Element){
									Element lokumElement = (Element) lokumNode;
									NodeList lokumNodes = lokumElement.getChildNodes();

									for (int l = 0; l < lokumNodes.getLength(); l++) {
										Node nodeT = lokumNodes.item(l);
										if(nodeT instanceof Element){
											Element	nodeTElem = (Element) nodeT;
											if(nodeTElem.getNodeName()=="color"){
												color = nodeTElem.getAttribute("c");
											}

											else if(nodeTElem.getNodeName() == "type"){
												type = nodeTElem.getAttribute("t");
											}

											else if(nodeTElem.getNodeName() == "position"){
												NodeList positionNodes = nodeTElem.getChildNodes();
												for (int m = 0; m < positionNodes.getLength(); m++) {
													Node position = positionNodes.item(m);
													if(position instanceof Element){
														Element positionElem = (Element) position;
														if(positionElem.getNodeName() == "xcoord"){
															coordX = Integer.parseInt(positionElem.getAttribute("x"));
														}
														if(positionElem.getNodeName() == "ycoord"){
															coordY = Integer.parseInt(positionElem.getAttribute("y"));
														}
													}
												}
											}
										}
									}
								}
								if(type.equals("NotSpecial")){
									savedLokum = new normalLokum(coordX,coordY,color , false);
									board.set(savedLokum.getPositionX(),savedLokum.getPositionY(),savedLokum);
								}else{
									savedLokum = new specialLokum(coordX,coordY,color,type);
									board.set(savedLokum.getPositionX(),savedLokum.getPositionY(),savedLokum);
								}
							}
						}
					}
				}
				else if(child.getNodeName() == "goalscore"){
					updateRequiredScoreLabel(Integer.parseInt(child.getAttribute("gscore")));
				}
				else if(child.getNodeName() == "currentscore"){
					score = Integer.parseInt(child.getAttribute("cscore"));
					setScore(score);
				}
				else if(child.getNodeName() == "movesleft"){
					setSwapsLeft(Integer.parseInt(child.getAttribute("swaps")));
				}
			}
		}
		frame.setContentPane(gamePanel);
		timer.start();
	}
	public void updateRequiredScoreLabel(int x){
		requiredScoreLabel.setText("Required Score:" + x);
	}

	/**
	 * @param a First selected lokum for swap.
	 * @param b Second selected lokum for swap.
	 * @requires a!=null b!=null
	 * @effects If a,b swap does not create any combination, then a,b will be unswapped.
	 * @modifies Board, a, b.
	 */
	public void swap (Lokum a, Lokum b){
		
		if(specialSwapSelecter.isSelected()){
			updateSpecialSwaps(-1);
		}
		else{
			updateSwapsLeft(-1);
		}
		board.setScore(0);
		board.setTime(0);
		board.swap(a,b);
		ArrayList<Combination> combinations = board.checkCombinations();

		if(!specialSwapSelecter.isSelected()){
			if(combinations.size() == 0){
				board.unswap(a, b);
				updateSwapsLeft(1);
			}
		}
		
		while(combinations.size() != 0){
			for(Combination c : combinations){
				board.eat(c);
			}
			board.FillEmptySpaces();
			combinations = board.checkCombinations();
		}
		increaseScore(board.getScore());
		updateTime(board.getTime());
	}
	/**
	 * 
	 */
	public void checkGameStatus(){
		if(isLevelRequirementReached()){
			timer.stop();
			gameTimer.stop();
			JOptionPane.showMessageDialog(null, "Level succeded test level is over returning start menu.");
			frame.setContentPane(startMenu);
		}
		if(isLevelFailed()){
			timer.stop();
			gameTimer.stop();
			JOptionPane.showMessageDialog(null,"Test Level failed returning start menu.");
			frame.setContentPane(startMenu);
		}
		if(isTimeFinished()){
			timer.stop();
			gameTimer.stop();
			JOptionPane.showMessageDialog(null,"Level failed(time finished) returning start menu.");
			frame.setContentPane(startMenu);
		}
		if(specialSwapsLeft== 0){
			specialSwapSelecter.setSelected(false);
			specialSwapSelecter.setEnabled(false);
		}
	}

	/**
	 * 
	 * @param x  the score
	 * @requires x!=0
	 * @modifies scoreLabel
	 * @effects If x equals zero, there won't be any action.
	 */
	public void increaseScore(int x){
		score = score + x;
		scoreLabel.setText("Score:" + Integer.toString(score));
	}

	public int getScore(){
		return Integer.parseInt(scoreLabel.getText());
	}
	public void setScore(int score){
		this.score=score;
		scoreLabel.setText("Score:" + Integer.toString(score));
	}
	private void setTime(long x) {
		time = x;
		timeLabel.setText("Time:" + Integer.toString((int) time));
	}


	/**
	 * @param x		x coordination of the location of the lokum. 
	 * @param y		y coordination of the location of the lokum.
	 * @requires x!=0 y!=0
	 */

	public Lokum getSelectedLokum (int x,int y){
		Lokum selected = null;
		int XcoordInGrid = x/25;
		int YcoordInGrid = y/22;

		selected = board.get(YcoordInGrid, XcoordInGrid);
		if(!specialSwapSelecter.isSelected()){
			if(sLokum1 != null){
				int Lok1X = sLokum1.getPositionX();
				int Lok1Y = sLokum1.getPositionY();
				int sLokX = selected.getPositionX();
				int sLoky = selected.getPositionY();
				if(Math.abs(Lok1X-sLokX)>1 || Math.abs(Lok1Y-sLoky)>1){	
					System.out.println("Select adjacent of lokum.");
					selected = null;
				}
			}
		}
		return selected;
	}
	/**
	 * @modifies swapsLeft
	 * @param x
	 */
	public void setSwapsLeft(int x){
		swapsLeft = x;
		swapsLeftLabel.setText("Swaps:" + Integer.toString(swapsLeft));
	}
	/**
	 * @modifies specialSwapLeft
	 * @param x
	 */
	public void setSpecialSwapsLeft(int x){
		specialSwapsLeft = x;
		specialSwapCountLabel.setText("SpecialSwaps:" + specialSwapsLeft);
	}
	/**
	 * @requires swapsLeft!=0
	 * @modifies scoreLabel
	 * @effects If there is no swapsLeft, then game will be finished.
	 */
	public void updateSwapsLeft(int x){
		swapsLeft = swapsLeft+x;
		swapsLeftLabel.setText("Swaps:" + Integer.toString(swapsLeft));
	}
	/**
	 * It updates the specialswap count.
	 * @param x
	 */
	public void updateSpecialSwaps(int x){
		specialSwapsLeft = specialSwapsLeft + x;
		specialSwapCountLabel.setText("SpecialSwaps:" + Integer.toString(specialSwapsLeft));
	}
	/**
	 * @requires time
	 * @param x
	 * @modifies timeLabel, time
	 */
	public void updateTime(long x){
		time = time + x;
		timeLabel.setText("Time:" + Integer.toString((int)time));
	}
	/**
	 * 
	 * @return returns true if the level requirement is reached in the game.
	 */
	public boolean isLevelRequirementReached(){
		if(score>=level.getlevelRequirementScore()){
			return true;	
		}
		return false;
	}
	/**
	 * 
	 * @return returns if time zero and level is timeBased return true else false.
	 */
	public boolean isTimeFinished(){
		if(level.isTimeBased() == true && time == 0){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @return returns false if level is failed.
	 */
	public boolean isLevelFailed(){
		if(swapsLeft==0){
			return true;
		}
		return false;
	}
	public void initGame(){
		frame.setContentPane(startMenu);
	} 

	/**
	 *  @modifies frame.
	 */
	public void paintLokumPanel(){

		lokumPanel.setSize(25*board.getWidth(),22*board.getHeight());
		lokumPanel.setOpaque(true);
		lokumPanel.setLayout(null);

		for (int i = 0; i < board.getHeight(); i++) {
			for (int j = 0; j < board.getWidth(); j++) {
				if(board.get(i,j) != null) {
					Lokum temp = board.get(i,j);
					JLabel lokumLabel = getLokumLabel(temp);
					lokumLabel.setSize(25, 22);
					lokumLabel.setLocation(25*j, 22*i);
					lokumPanel.add(lokumLabel);
				}
				else{
					JLabel emptyLabel = new JLabel();
					emptyLabel.setSize(25, 22);
					emptyLabel.setBackground(Color.blue);
					emptyLabel.setLocation(25*j+100, 22*i);
					lokumPanel.add(emptyLabel);
				}
			}
		}

	}
	public void repaint(){
		lokumPanel.removeAll();
		lokumPanel.repaint();
		paintLokumPanel();
	}
	public JLabel getLokumLabel(Lokum l){
		JLabel lokumLabel = new JLabel();
		if(l.getColor().equals("W")){
			if(l.getType().equals("HStriped")){
				lokumLabel = new JLabel(yellowHStriped);
			}
			else if(l.getType().equals("VStriped")){
				lokumLabel = new JLabel(yellowVStriped);
			}
			else if(l.getType().equals("Wrapped")){
				lokumLabel = new JLabel(yellowWrapped);
			}
			else{
				lokumLabel = new JLabel(yellow);
			}
		}
		else if(l.getColor().equals("B")){
			if(l.getType().equals("HStriped")){
				lokumLabel = new JLabel(blueHStriped);
			}
			else if(l.getType().equals("VStriped")){
				lokumLabel = new JLabel(blueVStriped);
			}
			else if(l.getType().equals("Wrapped")){
				lokumLabel = new JLabel(blueWrapped);
			}
			else{
				lokumLabel = new JLabel(blue);
			}
		}
		else if(l.getColor().equals("G")){
			if(l.getType().equals("HStriped")){
				lokumLabel = new JLabel(greenHStriped);
			}
			else if(l.getType().equals("VStriped")){
				lokumLabel = new JLabel(greenVStriped);
			}
			else if(l.getType().equals("Wrapped")){
				lokumLabel = new JLabel(greenWrapped);
			}
			else{
				lokumLabel = new JLabel(green);
			}
		}
		else if(l.getColor().equals("R")){
			if(l.getType().equals("HStriped")){
				lokumLabel = new JLabel(redHStriped);
			}
			else if(l.getType().equals("VStriped")){
				lokumLabel = new JLabel(redVStriped);
			}
			else if(l.getType().equals("Wrapped")){
				lokumLabel = new JLabel(redWrapped);
			}	
			else{
				lokumLabel = new JLabel(red);
			}
		}
		else if(l.getType().equals("BOMB")){
			lokumLabel = new JLabel(BOMB);
		}
		return lokumLabel;
	}
	/**
	 * Constructs start menu panel
	 * @return returns constructed menu panel
	 */
	public JPanel constructStartMenu(){
		JPanel startMenuPanel = new JPanel();
		startMenuPanel.setOpaque(true);
		startMenuPanel.setLayout(null);

		startButton = new JButton("Start");
		startButton.addMouseListener(this);
		levelChooser = new JComboBox<Integer>();
		for (int i = 1; i < 11; i++) {
			levelChooser.addItem(i);
		}
		startMenuPanel.setSize(frame.getHeight(),frame.getWidth());
		startButton.setLocation(startMenuPanel.getWidth()/2-55, startMenuPanel.getHeight()/2-85);
		levelChooser.setLocation(startMenuPanel.getWidth()/2-55, startMenuPanel.getHeight()/2-25);
		startButton.setSize(100,50);
		levelChooser.setSize(100,50);
		startMenuPanel.add(startButton);
		startMenuPanel.add(levelChooser);

		return startMenuPanel;
	}
	public JPanel constructGamePanel(){
		JPanel gamePanel = new JPanel();
		gamePanel.setOpaque(true);
		gamePanel.setLayout(null);
		gamePanel.setSize(frame.getHeight(),frame.getWidth());

		scoreLabel = new JLabel("Score:" + Integer.toString(score));
		scoreLabel.setLocation(frame.getWidth()/2-80,frame.getHeight()-50);
		scoreLabel.setSize(75,25);
		
		swapsLeftLabel = new JLabel("Swaps:"+Integer.toString(swapsLeft));
		swapsLeftLabel.setLocation(frame.getWidth()/2+10,frame.getHeight()-50);
		swapsLeftLabel.setSize(125,25);
		
		requiredScoreLabel = new JLabel("Required Score:" + level.getlevelRequirementScore());
		requiredScoreLabel.setLocation(frame.getWidth()/2-75,frame.getHeight()-75);
		requiredScoreLabel.setSize(160,25);

		specialSwapSelecter = new JCheckBox("SpecialSwap");
		specialSwapSelecter.setLocation(frame.getWidth()/2-250,frame.getHeight()-75);
		specialSwapSelecter.setSize(100,25);

		specialSwapCountLabel = new JLabel("SpecialSwaps:" + Integer.toString(specialSwapsLeft));
		specialSwapCountLabel.setLocation(frame.getWidth()/2-245,frame.getHeight()-55);
		specialSwapCountLabel.setSize(125,25);


		timeLabel = new JLabel("Time:" + Long.toString(time));
		timeLabel.setLocation(frame.getWidth()-100,frame.getHeight()-50);
		timeLabel.setSize(100,25);
		
		gamePanel.add(scoreLabel);
		gamePanel.add(swapsLeftLabel);
		gamePanel.add(requiredScoreLabel);
		gamePanel.add(lokumPanel);
		gamePanel.add(specialSwapSelecter);
		gamePanel.add(specialSwapCountLabel);
		gamePanel.add(timeLabel);
		return gamePanel;
	}
	public JToolBar constructTopMenu(){
		JToolBar topMenu = new JToolBar();
		topMenu.setVisible(true);
		topMenu.setSize(frame.getWidth(),30);

		JButton button1 = new JButton("StartMenu");
		button1.setToolTipText("Go back start menu(game won't be recorded)");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initGame();
			}
		});
		topMenu.add(button1);

		JButton button2 = new JButton("Load");
		button2.setToolTipText("Load an XML file");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadGame();
			}
		});
		topMenu.add(button2);

		JButton button3 = new JButton("Save");
		button3.setToolTipText("Save the program into an XML file");
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(frame.getContentPane() == startMenu){
					JOptionPane.showMessageDialog(null,"You can not save in start menu.");
				}
				else{
					saveGame();
				}
			}
		});
		topMenu.add(button3);

		JButton button4 = new JButton("Exit");
		button4.setToolTipText("Exit the program");
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		topMenu.add(button4);

		return topMenu;

	}

	public int getSelectedLevel(){
		return (int) levelChooser.getSelectedItem();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == startButton){
			board.removeAll();
			board.constructRandomBoard();
			swapsLeft = level.getswapAmount();
			specialSwapsLeft = level.getSpecialSwapAmount();
			time = level.getTime();
			setSwapsLeft(swapsLeft);
			setSpecialSwapsLeft(specialSwapsLeft);
			setScore(0);
			setTime(time);
			frame.setContentPane(gamePanel);
			repaint();
			timer.start();
			gameTimer.start();
		}
		if(e.getSource() == lokumPanel){
			System.out.println(e.getX() + " " + e.getY());
			if(sLokum1 == null){
				sLokum1=getSelectedLokum(e.getX(), e.getY());
				System.out.println("Lokum1 selected");
			}else if(sLokum2==null){
				sLokum2=getSelectedLokum(e.getX(), e.getY());
				if(sLokum2 == sLokum1){
					System.out.println("Lokum1 deselected");
					sLokum1 = null;
					sLokum2 = null;
				}

				if(sLokum1 !=null && sLokum2 != null){
					System.out.println("Lokum2 selected");
					swap(sLokum1, sLokum2);
					sLokum1=null;
					sLokum2=null;
				}
			}
		}


	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	// A class that implements ActionListener for repainting lokums
	class AnimationEventListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("repainted");
			repaint();
			checkGameStatus();
		}
	}
	class timeEventListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			updateTime(-1);
		}
	}

}	
