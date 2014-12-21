import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
 * (buttons) of the game.
 * 
 *
 */

public class gameEngine extends JFrame implements MouseListener{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int score;
	private int swapsLeft;
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
	private Lokum[][] lokumGrid; 
	private JPanel gamePanel; 
	private JPanel startMenu; 
	private JPanel lokumPanel; 
	private Timer timer; 
	private AnimationEventListener eventListener; 
	
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

		eventListener = new AnimationEventListener();
		timer = new Timer(100,eventListener);

		lokumGrid = new Lokum[8][8];
		board = new Board(lokumGrid);
		
		level= new Level(1,20,100000,board.getLokumGrid(),0);
		swapsLeft = level.getswapAmount();
		score = 0;


		lokumPanel = new JPanel();
		lokumPanel.addMouseListener(this);
		lokumPanel.setLocation((frame.getWidth()/2) - ((lokumGrid[0].length*25)/2), 30);		
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
	 *  Method that saves current game.
	 *
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
			bw.write("<player>\n");
			bw.write("<id>" + 0 + "</id>\n");
			bw.write("<name>" +"Yigit"+"</name>\n");
			bw.write("</player>\n");
			bw.write("<board>\n");
			bw.write("<lokums>\n");
			for (int i = 0; i < lokumGrid.length; i++) {
				for (int j = 0; j < lokumGrid[0].length; j++) {
					Lokum lokum = lokumGrid[i][j];
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
			bw.write(String.format("<level lev=\"%d\"/>",level.getlevelNumber()));
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
									savedLokum = new normalLokum(coordX,coordY,color);
									lokumGrid[savedLokum.getPositionX()][savedLokum.getPositionY()] = savedLokum;
								}else{
									savedLokum = new specialLokum(coordX,coordY,color,type);
									lokumGrid[savedLokum.getPositionX()][savedLokum.getPositionY()] = savedLokum;
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
	 * @requires 2 selected lokums (in this case; a, b)
	 * @modifies lokum a, lokum b.
	 * @effects board.
	 */
	public void swap (Lokum a, Lokum b){

		updateSwapsLeft(-1);
		board.setScore(0);
		board.swap(a,b);
		ArrayList<Combination> combinations = board.checkCombinations();

		if(a.isSpecial() && b.isSpecial()){
			ArrayList<Lokum> spLokums = new ArrayList<Lokum>();
			spLokums.add(a);
			spLokums.add(b);
			Combination special = new Combination("Special",spLokums);
			combinations.add(special);
		}else if(a.getType().equals("BOMB") && !b.isSpecial() 
				|| !a.isSpecial() && b.getType().equals("BOMB")){
			ArrayList<Lokum> Lokums = new ArrayList<Lokum>();
			Lokums.add(a);
			Lokums.add(b);
			Combination special = new Combination("Special",Lokums);
			combinations.add(special);
		}
		if(combinations.size() == 0){
			board.unswap(a, b);
			updateSwapsLeft(1);
		}
		while(combinations.size() != 0){
			for(Combination c : combinations){
				board.eat(c);

			}
			board.FillEmptySpaces();
			combinations = board.checkCombinations();
		}
		increaseScore(board.getScore());

		checkGameStatus();
	}
	public void checkGameStatus(){
		if(isLevelRequirementReached()){
			JOptionPane.showMessageDialog(null, "Level succeded test level is over returning start menu.");
			frame.setContentPane(startMenu);
			timer.stop();
		}
		if(isLevelFailed()){
			JOptionPane.showMessageDialog(null,"Test Level failed returning start menu.");
			frame.setContentPane(startMenu);
			timer.stop();
		}
	}

	/**
	 * 
	 * @param x  the score
	 * @modifies scoreLabel
	 */
	public void increaseScore(int x){
		score = score + x;
		scoreLabel.setText(Integer.toString(score));
	}

	public int getScore(){
		return Integer.parseInt(scoreLabel.getText());
	}
	public void setScore(int score){
		this.score=score;
		scoreLabel.setText(Integer.toString(score));
	}



	/**
	 * 
	 * @param x		x coordination of the location of the lokum. 
	 * @param y		y coordination of the location of the lokum.
	 */

	public Lokum getSelectedLokum (int x,int y){
		Lokum selected = null;
		int XcoordInGrid = x/25;
		int YcoordInGrid = y/22;
		
		selected = lokumGrid[YcoordInGrid][XcoordInGrid];
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
		return selected;
	}
	public void setSwapsLeft(int x){
		swapsLeft = x;
		swapsLeftLabel.setText(Integer.toString(swapsLeft));
	}
	/**
	 * @effects scoreLabel
	 */
	public void updateSwapsLeft(int x){
		swapsLeft = swapsLeft+x;
		swapsLeftLabel.setText(Integer.toString(swapsLeft));
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
		
		lokumPanel.setSize(25*lokumGrid[0].length,22*lokumGrid.length);
		lokumPanel.setOpaque(true);
		lokumPanel.setLayout(null);

		for (int i = 0; i < lokumGrid.length; i++) {
			for (int j = 0; j < lokumGrid[0].length; j++) {
				if(lokumGrid[i][j] != null) {
					Lokum temp = lokumGrid[i][j];
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

		scoreLabel = new JLabel(Integer.toString(score));
		JLabel scoreText = new JLabel("Score:");
		requiredScoreLabel = new JLabel("Required Score:" + level.getlevelRequirementScore());
		swapsLeftLabel = new JLabel(Integer.toString(swapsLeft));
		JLabel moveCountText = new JLabel("Moves:");

		scoreText.setLocation(frame.getWidth()/2-85,frame.getHeight()-50);
		scoreLabel.setLocation(frame.getWidth()/2-40,frame.getHeight()-50);
		scoreText.setSize(50,25);
		scoreLabel.setSize(50,25);

		moveCountText.setLocation(frame.getWidth()/2+40,frame.getHeight()-50);
		swapsLeftLabel.setLocation(frame.getWidth()/2+80,frame.getHeight()-50);
		moveCountText.setSize(50,25);
		swapsLeftLabel.setSize(50,25);

		requiredScoreLabel.setLocation(frame.getWidth()/2-75,frame.getHeight()-75);
		requiredScoreLabel.setSize(160,25);

		gamePanel.add(scoreText);
		gamePanel.add(scoreLabel);
		gamePanel.add(moveCountText);
		gamePanel.add(swapsLeftLabel);
		gamePanel.add(requiredScoreLabel);
		gamePanel.add(lokumPanel);

		return gamePanel;
	}
	public JToolBar constructTopMenu(){
		JToolBar topMenu = new JToolBar();
		topMenu.setVisible(true);
		topMenu.setSize(frame.getWidth(),30);
		JButton button = null;

		button = new JButton("StartMenu");
		button.setToolTipText("Go back start menu(game won't be recorded)");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initGame();
			}
		});
		topMenu.add(button);

		button = new JButton("Load");
		button.setToolTipText("Load an XML file");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadGame();
			}
		});
		topMenu.add(button);

		button = new JButton("Save");
		button.setToolTipText("Save the program into an XML file");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(frame.getContentPane() == startMenu){
				JOptionPane.showMessageDialog(null,"You can not save in start menu.");
				}
				else{
				saveGame();
				}
			}
		});
		topMenu.add(button);

		button = new JButton("Exit");
		button.setToolTipText("Exit the program");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		topMenu.add(button);

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
			setSwapsLeft(swapsLeft);
			setScore(0);
			frame.setContentPane(gamePanel);
			repaint();
			timer.start();
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
			repaint();
		}
	}

}	
