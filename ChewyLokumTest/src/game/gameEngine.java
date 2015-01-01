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
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * gameEngine is a class that opens game,
 * handles players actions and contains the graphics 
 * of the game.
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
	private Timer timer2;
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
	private ImageIcon nullSpace= new ImageIcon("Lokums/NULLSPACE.png");

	/**
	 * @param sLokum1   the first selected lokum. 
	 * @param sLokum2 	the second selected lokum.
	 * @param swapsLeft the amount of swaps that is left after player's moves.
	 * @param timer 	The thread that used for repaint lokumPanel.
	 * @param gameTimer The thread that used for decreasing level time.
	 */

	public gameEngine() {
		//		Level l = new Level(new Board(3,8),2,15,2,75000,20);
		//		createLevelXML(l);
		frame = new JFrame("ChewyLokumLegend");
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.addMouseListener(this);

		timer = new Timer(100,new AnimationEventListener());
		gameTimer = new Timer(1000,new timeEventListener());

		lokumPanel = new JPanel();
		lokumPanel.addMouseListener(this);

		startMenu = constructStartMenu();
		startMenu.addMouseListener(this);
		startMenu.add(constructTopMenu());

		/**
		 * Default constructor of gameEngine.
		 * 
		 * 
		 */
	}
	/**
	 *  This method is used for reading last game state(level that leftover) by using txt file.
	 * 	@return returns the level that user can select 
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
	/**
	 *  This method is used for writing game state (current level number) by using txt file.
	 */
	public void writeGameState(){
		try {
			BufferedWriter wr = new BufferedWriter(new FileWriter("gameState.txt"));
			System.out.println(level.getlevelNumber());
			if(level.getlevelNumber() <= 5) wr.write(Integer.toString(level.getlevelNumber()));
			wr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		levelChooser.removeAllItems();;
		for (int i = 1; i < readGameState()+1; i++) {
			levelChooser.addItem(i);
		}
	}

	/**
	 *  Method that saves current game by creating an xml file.
	 *  @requires load button must be pushed by user
	 */
	public void saveGame(){
		gameTimer.stop();
		timer.stop();

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
			gameTimer.start();
			timer.start();
			return;
		}

		File newXML=new File(dir.getAbsolutePath()+"//"+fileName+".xml");

		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newXML), "utf-8"));
			bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			bw.write("<game>\n");
			bw.write("<board>\n");
			bw.write(String.format("<width w=\"%d\"/>\n",board.getWidth()));
			bw.write(String.format("<height h=\"%d\"/>\n",board.getHeight()));
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
			bw.write("</board>\n");
			bw.write(String.format("<currentscore score=\"%d\"/>",score));
			bw.write(String.format("<requiredScore score=\"%d\"/>",level.getlevelRequirementScore()));
			bw.write(String.format("<movesleft swaps=\"%d\"/>",swapsLeft));
			bw.write(String.format("<specialMovesLeft specialSwaps=\"%d\"/>",specialSwapsLeft));
			bw.write(String.format("<level levelNumber=\"%d\"/>",level.getlevelNumber()));
			bw.write(String.format("<time t=\"%d\"/>",time));
			bw.write("</game>\n");

			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gameTimer.start();
		timer.start();
	}
	public void createLevelXML(Level l){
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(String.format(("Level%d.xml"),l.getlevelNumber())), "utf-8"));
			bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			bw.write("<level>\n");
			bw.write("<board>\n");
			bw.write(String.format("<width w=\"%d\"/>\n",l.getBoard().getWidth()));
			bw.write(String.format("<height h=\"%d\"/>\n",l.getBoard().getHeight()));
			bw.write("</board>\n");
			bw.write(String.format("<requiredScore score=\"%d\"/>",l.getlevelRequirementScore()));
			bw.write(String.format("<swapAmount swaps=\"%d\"/>",l.getswapAmount()));
			bw.write(String.format("<specialSwapAmount specialSwaps=\"%d\"/>",l.getSpecialSwapAmount()));
			bw.write(String.format("<level levelNumber=\"%d\"/>",l.getlevelNumber()));
			bw.write(String.format("<time t=\"%d\"/>",l.getTime()));
			bw.write("</level>\n");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Level readLevelXML(int levelNumber){
		Level result;
		int requiredScore=0,swapAmount=0,specialSwapAmount=0,width = 0,height = 0,time=0;
		Board board = null;

		File XSDFile =new File("Levels/Level.xsd");
		File XMLFile =new File(String.format("Levels/Level%d.xml",levelNumber));

		Document XMLDocument = XMLDocumentConstructor(XMLFile,XSDFile);

		Element root = XMLDocument.getDocumentElement();
		NodeList nodes = root.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if(node instanceof Element){
				Element childElement = (Element) node;
				if(childElement.getNodeName() == "board"){

					NodeList boardNodes = childElement.getChildNodes();
					for(int j = 0; j<boardNodes.getLength(); j++){

						Node childNode = boardNodes.item(j);

						if(childNode.getNodeName() == "width"){
							Element widthElem = (Element) childNode;
							width = Integer.parseInt(widthElem.getAttribute("w"));
						}
						if (childNode.getNodeName() == "height"){
							Element heightElem = (Element) childNode;
							height = Integer.parseInt(heightElem.getAttribute("h"));

							board = new Board(height,width);		
						}
					}
				}
				else if(childElement.getNodeName() == "requiredScore"){
					requiredScore = Integer.parseInt(childElement.getAttribute("score"));
				}
				else if(childElement.getNodeName() == "swapAmount"){
					swapAmount = Integer.parseInt(childElement.getAttribute("swaps"));
				}
				else if(childElement.getNodeName() == "specialSwapAmount"){
					specialSwapAmount = Integer.parseInt(childElement.getAttribute("specialSwaps"));
				}
				else if((childElement.getNodeName() == "time")){
					time = Integer.parseInt(childElement.getAttribute("t"));
				}
			}
		}
		result = new Level(board,levelNumber,swapAmount,specialSwapAmount,requiredScore,time);
		return result;
	}
	/**
	 * 
	 * @requires It requires a saved game xml file to be choosen for load.
	 * @requires load button must be pushed by user
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
	/**
	 * This method takes two file objects and creates a document for xmlparsing 
	 * after validation succeeded.
	 * @param XMLFile XML file that will used for construction Document object
	 * @param XSDFile XSD schema file that will be used for validation of XML
	 * @return Document file that will be used for parsing
	 */
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
		Schema xmlSchema = null;
		try {
			String xmlLang = XMLConstants.W3C_XML_SCHEMA_NS_URI;
			SchemaFactory factory = SchemaFactory.newInstance(xmlLang);
			xmlSchema = factory.newSchema(XSDFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Validator validator = xmlSchema.newValidator();
		try {
			validator.validate(new DOMSource(xmlDocument));
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xmlDocument;		
	}
	/**
	 * This medhod takes document and parses its nodes.Finds variables and 
	 * matches with game engines fields then starts game with updated fileds.
	 * @param XMLDoc Document that will be parsed.
	 */
	public void XMLParser(Document XMLDoc){

		Element root = XMLDoc.getDocumentElement();
		NodeList nodes = root.getChildNodes();

		int width = 0,height = 0,levelNumber = 0,swapsLeft = 0, specialSwapsLeft = 0,time = 0,requiredScore = 0;

		for (int i = 0; i < nodes.getLength(); i++) {

			Node node = nodes.item(i);

			if(node instanceof Element){
				Element childElement = (Element) node;

				if(childElement.getNodeName() == "board"){
					NodeList boardNodes = childElement.getChildNodes();

					for(int j = 0; j<boardNodes.getLength(); j++){

						Node childNode = boardNodes.item(j);

						if(childNode.getNodeName() == "width"){
							Element widthElem = (Element) childNode;
							width = Integer.parseInt(widthElem.getAttribute("w"));
						}
						if (childNode.getNodeName() == "height"){
							Element heightElem = (Element) childNode;
							height = Integer.parseInt(heightElem.getAttribute("h"));
							board = new Board(height,width);
						}

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
				else if(childElement.getNodeName() == "currentscore"){
					score = Integer.parseInt(childElement.getAttribute("score"));
				}
				else if(childElement.getNodeName() == "requiredScore"){
					requiredScore = Integer.parseInt(childElement.getAttribute("score"));
					
				}
				else if(childElement.getNodeName() == "movesleft"){
					swapsLeft = Integer.parseInt(childElement.getAttribute("swaps"));
				}
				else if(childElement.getNodeName() == "specialMovesLeft"){
					specialSwapsLeft = Integer.parseInt(childElement.getAttribute("specialSwaps"));
				}
				else if(childElement.getNodeName() == "time"){
					time = Integer.parseInt(childElement.getAttribute("t"));
				}
				else if(childElement.getNodeName() == "level"){
					levelNumber = Integer.parseInt(childElement.getAttribute("levelNumber"));
				}
			}
		}
		Level l = new Level(new Board(height,width),levelNumber,swapsLeft,specialSwapsLeft,requiredScore,time);
		initLevel(l,true);
	}
	/**
	 * This method modifies requiredScoreLabels text
	 * @param x The score that will be set for label
	 * @modifies requiredScoreLabel
	 */
	public void setRequiredScoreLabel(int x){
		requiredScoreLabel.setText("Required Score:" + x);
	}

	/**
	 * @param a First selected lokum for swap.
	 * @param b Second selected lokum for swap.
	 * @requires a!=null b!=null
	 * @effects If a,b swap does not create any combination, then a,b will be unswapped.
	 * @modifies board, a, b.
	 */
	public void swap (Lokum a, Lokum b){

		if(specialSwapSelecter.isSelected()){
			updateSpecialSwaps(-1);
		}
		else{
			updateSwapsLeft(-1);
		}
		board.setScore(0);
		if(level.isTimeBased()) board.setTime(0);
		board.swap(a,b);
		ArrayList<Combination> combinations = board.checkCombinations();

		if(!specialSwapSelecter.isSelected()){
			if(combinations.size() == 0){
				board.unswap(a, b);
				updateSwapsLeft(1);
				System.out.println("Unswapped both selected lokums deselected");
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
		if(level.isTimeBased()) updateTime(board.getTime());
	}
	/**
	 * This method check for whether the level in finished or ongoing. It checks whether the level is succeeded, failed, time finished and it also checks whether special swaps are finished or not.
	 * @modifies timer, gameTimer, frame
	 * @effects If required score is reached, it stops the timer and print level succeed.
	 * @effects	If time is finished or required score could not achieve,then level is failed. 			
	 */
	public void checkGameStatus(){
		if(isLevelRequirementReached()){
			timer.stop();
			gameTimer.stop();

			if(level.getlevelNumber()==5){
				JOptionPane.showMessageDialog(null, "Last level finished GAME OVER.");
				frame.setContentPane(startMenu);
			}
			else{
				JOptionPane.showMessageDialog(null, "Level succeded press OK for next level.");
				initLevel(readLevelXML(level.getlevelNumber()+1),false);	
				writeGameState();
			}
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
	 * It increases the score by adding the gaining point.
	 * @param x  the score
	 * @requires x!=null
	 * @modifies scoreLabel
	 * @effects If x equals zero, there won't be any change.
	 */
	public void increaseScore(int x){
		score = score + x;
		scoreLabel.setText("Score:" + Integer.toString(score));
	}
	/**
	 * @return Returns the score.
	 */
	public int getScore(){
		return Integer.parseInt(scoreLabel.getText());
	}
	/**
	 * @param score
	 */
	public void setScore(int score){
		this.score=score;
		scoreLabel.setText("Score:" + Integer.toString(score));
	}
	/**
	 * @param x
	 */
	private void setTime(long x) {
		time = x;
		timeLabel.setText("Time:" + Integer.toString((int) time));
	}


	/**
	 * @param x		x coordination of the location of the lokum. 
	 * @param y		y coordination of the location of the lokum.
	 * @requires    x!=null y!=null
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
	 * @modifies swapsLeft,swapsLeftLabel
	 * @param x  The new swap amount for set.
	 */
	public void setSwapsLeft(int x){
		swapsLeft = x;
		swapsLeftLabel.setText("Swaps:" + Integer.toString(swapsLeft));
	}
	/**
	 * @modifies specialSwapLeft,specialSwapCountLabel
	 * @param x	The new special swap amount for set
	 */
	public void setSpecialSwapsLeft(int x){
		specialSwapsLeft = x;
		specialSwapCountLabel.setText("SpecialSwaps:" + specialSwapsLeft);
	}
	/**
	 * It updates the swap counts.
	 * @requires swapsLeft!=null
	 * @modifies swapsLeft,swapsLeftLabel
	 * @effects If there is no swapsLeft, then game will be finished.
	 */
	public void updateSwapsLeft(int x){
		swapsLeft = swapsLeft+x;
		swapsLeftLabel.setText("Swaps:" + Integer.toString(swapsLeft));
	}
	/**
	 * It updates the special swaps.
	 * @param x The change in specialSwaps
	 * @modifies specialSwapsLeft,specialSwapCountLabel
	 */
	public void updateSpecialSwaps(int x){
		specialSwapsLeft = specialSwapsLeft + x;
		specialSwapCountLabel.setText("SpecialSwaps:" + Integer.toString(specialSwapsLeft));
	}
	/**
	 * It updates the time.
	 * @requires time
	 * @param x The change in time
	 * @modifies timeLabel, time
	 */
	public void updateTime(long x){
		time = time + x;
		timeLabel.setText("Time:" + Integer.toString((int)time));
	}
	/**
	 * @return Returns true if the score is higher than levelRequirementScore.
	 */
	public boolean isLevelRequirementReached(){
		if(score>=level.getlevelRequirementScore()){
			return true;	
		}
		return false;
	}
	/**
	 * @return If time zero and level is timeBased return true else false.
	 */
	public boolean isTimeFinished(){
		if(level.isTimeBased() == true && time == 0){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @return Returns true if there is no swapsLeft.
	 */
	public boolean isLevelFailed(){
		if(swapsLeft==0){
			return true;
		}
		return false;
	}
	/**
	 * Method for initiating game.
	 * @modifies frame
	 */
	public void initGame(){
		frame.setContentPane(startMenu);
		timer.stop();
		gameTimer.stop();
	} 
	public void initLevel(Level level,boolean loadedGame){
		this.level = level;

		if(!loadedGame){
			board =level.getBoard();
			board.constructRandomBoard();
		}

		gamePanel = constructGamePanel();
		gamePanel.add(constructTopMenu());


		setSwapsLeft(level.getswapAmount());
		setSpecialSwapsLeft(level.getSpecialSwapAmount());
		setTime(level.getTime());
		if(!loadedGame) setScore(0);
		else setScore(score);

		frame.setContentPane(gamePanel);

		timer.start();
		if(level.isTimeBased()){
			gameTimer.start();
		}
	}

	/**
	 *  Method that paints lokum panel with boards lokum sequence.
	 *  @modifies frame,gamePanel.
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
					System.out.println("NULL");
					JLabel emptyLabel = new JLabel(nullSpace);
					emptyLabel.setSize(25, 22);
					emptyLabel.setLocation(25*j, 22*i);
					lokumPanel.add(emptyLabel);
				}
			}
		}
	}
	/**
	 * Method that repaints lokumPanel by removing all object and painting it again.
	 * @modifies frame,gamePanel
	 */
	public void repaint(){
		lokumPanel.removeAll();
		lokumPanel.repaint();
		paintLokumPanel();
	}
	/**
	 * This method takes a lokum and finds its label and returns it.
	 * @param l Lokum that will be used for founding its ImageIcon(label).
	 * @return JLabel presentation of lokum
	 */
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
		for (int i = 1; i < readGameState()+1; i++) {
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
	/**
	 * This method constructs game frame.
	 * @return A JPanel
	 */
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

		scoreLabel.addMouseListener(this);

		paintLokumPanel();
		lokumPanel.setLocation((frame.getWidth()/2) - ((board.getWidth()*25)/2), 30);

		gamePanel.add(scoreLabel);
		gamePanel.add(swapsLeftLabel);
		gamePanel.add(requiredScoreLabel);
		gamePanel.add(lokumPanel);
		gamePanel.add(specialSwapSelecter);
		gamePanel.add(specialSwapCountLabel);
		gamePanel.add(timeLabel);
		gamePanel.addMouseListener(this);

		return gamePanel;
	}
	/**
	 * This method creates top menu for frame.
	 * @return JToolbar with necessary buttons
	 */
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
	/**
	 * @return Returns the selected level.
	 */
	public int getSelectedLevel(){
		return (int) levelChooser.getSelectedItem();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		if(e.getSource() == startButton){
			initLevel(readLevelXML((int)levelChooser.getSelectedItem()),false);
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
		if(e.getSource() == scoreLabel){
			increaseScore(10000);
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
