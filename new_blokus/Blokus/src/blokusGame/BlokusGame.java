package blokusGame;

import blokus5100.MainMenu;
import blokus.Player;
import blokus.Board;
import blokus.BlokusTimer;
import blokus.GamePiece;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import models.Game;


public class BlokusGame{
    public static final int NUM_PLAYERS = 4;
    static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	static BlokusFrame bf;
	BlokusGame bg;
    public BlokusGame()
    {
    	bg = this;
    	bf = new BlokusFrame();
    	bf.setLocation((screenWidth -bf.getWidth())/2,(screenHeight-bf.getHeight())/2);
    }
    
    public BlokusGame(String p1Name, String p2Name, String p3Name, String p4Name, Game game){
        bg = this;
    	bf = new BlokusFrame(p1Name,p2Name,p3Name,p4Name,game);
    	bf.setLocation((screenWidth -bf.getWidth())/2,(screenHeight-bf.getHeight())/2);
    }
    
    public static class BlokusFrame extends JFrame{
        private Board board;
        private Player[] players;
        private int turn = -1;

        private int pieceIndex;
        private Point selected;

        private JPanel mainPanel;
        private JPanel topPanel;
        private JPanel botPanel;
        private JPanel leftSidePanel;
        private JPanel rightSidePanel;
        private JPanel topSidePanel;
        private JPanel bottomSidePanel;

        private JPanel currentPlayerPiecesPanel;
        private JPanel currentPlayerPanel;
        private JPanel showPiecePanel;

        private JPanel player1PiecesPanel;
        private JPanel player2PiecesPanel;
        private JPanel player3PiecesPanel;
        private JPanel player4PiecesPanel;

        private JPanel player1InfoPanel;
        private JPanel player2InfoPanel;
        private JPanel player3InfoPanel;
        private JPanel player4InfoPanel;

        private JPanel turnTimePanel;
        private JPanel gameTimePanel;

        private JLabel player1;
        private JLabel player2;
        private JLabel player3;
        private JLabel player4;

        private JPanel boardPanel;
        private JPanel centerPanel;
        private JLabel label;
        private JLabel turnTimerLabel;
        private JLabel gameTimerLabel;
        private JLabel gameInfoLabel;

        private JLabel blokusLogo;
        private ImageIcon boardImage;
        private JButton surrenderButton;

        private int turnTimeLimit;
        private int gameTimeLimit;
        private int turnTimeInSeconds;

        private BlokusTimer turnTimer;
        private BlokusTimer gameTimer;

        private String player1Name;
        private String player2Name;
        private String player3Name;
        private String player4Name;


        public BlokusFrame(){
            super("Blokus");
            this.setLayout(new BorderLayout());
            board = new Board();
            players = new Player[NUM_PLAYERS];
            players[0] = new Player(Board.BLUE, "Player 1");
            players[1] = new Player(Board.RED, "Player 2");
            players[2] = new Player(Board.ORANGE, "Player 3");
            players[3] = new Player(Board.GREEN, "Player 4");

            turnTimeLimit = 10;
            gameTimeLimit = 62;
            turnTimeInSeconds = 1;

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            initializeGUI();
            startNewTurn();
        }

        public BlokusFrame(String p1Name, String p2Name, String p3Name, String p4Name, Game game){
            super("Blokus");
            this.setLayout(new BorderLayout());
            board = new Board(game.getElements(), game.getSizeX());
            players = new Player[NUM_PLAYERS];
            players[0] = new Player(Board.BLUE, p1Name);
            players[1] = new Player(Board.RED, p2Name);
            players[2] = new Player(Board.ORANGE, p3Name);
            players[3] = new Player(Board.GREEN, p4Name);

            turnTimeLimit = game.getTurnTime();
            gameTimeLimit = game.getGameTime();
            turnTimeInSeconds = 1;

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            initializeGUI();
            startNewTurn();
        }
        
        public BlokusFrame(String p1Name, String p2Name, String p3Name, String p4Name, int turnTime, int gameTime, int boardSize){
            super("Blokus");
            this.setLayout(new BorderLayout());
            board = new Board(boardSize);
            players = new Player[NUM_PLAYERS];
            players[0] = new Player(Board.BLUE, p1Name);
            players[1] = new Player(Board.RED, p2Name);
            players[2] = new Player(Board.ORANGE, p3Name);
            players[3] = new Player(Board.GREEN, p4Name);

            turnTimeLimit = turnTime;
            gameTimeLimit = gameTime;
            turnTimeInSeconds = 1;

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            initializeGUI();
            startNewTurn();
        }
        

        class TurnOver extends TimerTask {
            public void run() {
                turnTimerLabel.setText( Integer.toString(turnTimeLimit - (turnTimeInSeconds % turnTimeLimit)));
                if(turnTimeInSeconds % turnTimeLimit == 0){
                    drawBoard();
                    timesUpTurn();
                    //gameInfoLabel.setText("TURN " + Integer.toString(turn));
                }
                turnTimeInSeconds++;
            }
        }

        class GameOver extends TimerTask {
            public void run() {
                gameTimeLimit--;
                int min = gameTimeLimit / 60;
                int sec = (gameTimeLimit % 60);
                String strMsg = String.format("%02d:%02d",min,sec);
                gameTimerLabel.setText(strMsg);
                if(gameTimeLimit == 0){
                    gameOver();
                }                
            }
        }

        private void initializeGUI(){
            class BoardClickListener implements MouseListener, MouseMotionListener, MouseWheelListener{
                public void mouseClicked(MouseEvent e){
                    if (e.getButton() == MouseEvent.BUTTON3){
                        flipPiece();
                    }
                    else{
                        try{
                            board.placePiece(players[turn].pieces.get(
                            pieceIndex), selected.x - GamePiece.SHAPE_CONTAINER_SIZE / 2,
                            selected.y - GamePiece.SHAPE_CONTAINER_SIZE / 2, players[turn].firstMove);

                            drawBoard();
                            players[turn].pieces.get(pieceIndex).setToUsed();
                            players[turn].firstMove = false;
                            players[turn].canPlay = players[turn].pieces.size() != 0;
                            turnTimeInSeconds = 1;
                            turnTimerLabel.setText(Integer.toString(turnTimeLimit));
                            startNewTurn();
                        }
                        catch (Board.IllegalMoveException ex){
                            displayMessage(ex.getMessage());
                        }
                    }
                }

                    public void mouseExited(MouseEvent e){
                        selected = null;
                        board.initializeOverlay();
                        drawBoard();
                    }

                    public void mouseMoved(MouseEvent e){
                        Point p = board.getCoordinates(e.getPoint(), Board.DEFAULT_RESOLUTION);
                        if (!p.equals(selected)){
                            selected = p;
                            board.overlay(players[turn].pieces.get(pieceIndex), selected.x, selected.y);
                            drawBoard();
                        }
                    }

                    public void mouseWheelMoved(MouseWheelEvent e){
                        if(e.getWheelRotation() > 0){
                            rotateClockwise();
                        }
                        else{
                            rotateCounterClockwise();
                        }
                    }

                    public void mousePressed(MouseEvent e){
                    }
                    public void mouseReleased(MouseEvent e){
                    }
                    public void mouseEntered(MouseEvent e){
                    }
                    public void mouseDragged(MouseEvent e){
                    }
            }

            class SurrenderListener implements ActionListener{
                public void actionPerformed(ActionEvent event){
                    players[turn].canPlay = false;
                    startNewTurn();
                }
            }

            mainPanel = new JPanel();
            currentPlayerPiecesPanel = new JPanel();
            botPanel = new JPanel();
            topPanel = new JPanel();

            botPanel.setLayout(new BoxLayout(botPanel, BoxLayout.PAGE_AXIS));
            topPanel.setLayout(new FlowLayout());
           // currentPlayerPiecesPanel.setLayout(new BoxLayout(currentPlayerPiecesPanel, BoxLayout.PAGE_AXIS));
            currentPlayerPiecesPanel.setLayout(new GridLayout(7,3));



            player1PiecesPanel = new JPanel();
            //player1PiecesPanel.setLayout(new BoxLayout(player1PiecesPanel, BoxLayout.LINE_AXIS));
            player1PiecesPanel.setLayout(new GridLayout(7,3));

            player2PiecesPanel = new JPanel();
            //player2PiecesPanel.setLayout(new BoxLayout(player2PiecesPanel, BoxLayout.LINE_AXIS));
            player2PiecesPanel.setLayout(new GridLayout(7,3));

            player3PiecesPanel = new JPanel();
            //player3PiecesPanel.setLayout(new BoxLayout(player3PiecesPanel, BoxLayout.LINE_AXIS));
            player3PiecesPanel.setLayout(new GridLayout(7,3));

            player4PiecesPanel = new JPanel();
            //player4PiecesPanel.setLayout(new BoxLayout(player4PiecesPanel, BoxLayout.LINE_AXIS));
            player4PiecesPanel.setLayout(new GridLayout(7,3));

            JScrollPane jsp = new JScrollPane(currentPlayerPiecesPanel);
            jsp.getVerticalScrollBar().setUnitIncrement(GamePiece.PIECE_PREVIEW_RESOLUTION / 3);
            jsp.setPreferredSize(new Dimension(Board.DEFAULT_RESOLUTION - 30, GamePiece.PIECE_PREVIEW_RESOLUTION - 80));
            //jsp.getVerticalScrollBar().setUnitIncrement(120 / 3);
            //jsp.setPreferredSize(new Dimension(40, 90));

            JScrollPane jsp1 = new JScrollPane(player1PiecesPanel);
            jsp1.getVerticalScrollBar().setUnitIncrement(GamePiece.PIECE_PREVIEW_RESOLUTION / 3);
            jsp1.setPreferredSize(new Dimension(GamePiece.PIECE_PREVIEW_RESOLUTION - 80, Board.DEFAULT_RESOLUTION - 30));

            JScrollPane jsp2 = new JScrollPane(player2PiecesPanel);
            jsp2.getVerticalScrollBar().setUnitIncrement(GamePiece.PIECE_PREVIEW_RESOLUTION / 3);
            jsp2.setPreferredSize(new Dimension(GamePiece.PIECE_PREVIEW_RESOLUTION - 80, Board.DEFAULT_RESOLUTION - 30));

            JScrollPane jsp3 = new JScrollPane(player3PiecesPanel);
            jsp3.getVerticalScrollBar().setUnitIncrement(GamePiece.PIECE_PREVIEW_RESOLUTION / 3);
            jsp3.setPreferredSize(new Dimension(Board.DEFAULT_RESOLUTION - 30,GamePiece.PIECE_PREVIEW_RESOLUTION - 80));

            JScrollPane jsp4 = new JScrollPane(player4PiecesPanel);
            jsp4.getVerticalScrollBar().setUnitIncrement(GamePiece.PIECE_PREVIEW_RESOLUTION / 3);
            jsp4.setPreferredSize(new Dimension(Board.DEFAULT_RESOLUTION - 30,GamePiece.PIECE_PREVIEW_RESOLUTION - 80));

            showPiecePanel = new JPanel();
            showPiecePanel.setLayout(new BoxLayout(showPiecePanel, BoxLayout.PAGE_AXIS));



            player1 = new JLabel(players[0].name);
            player1.setForeground(Color.BLUE);
            player1.setAlignmentX(Component.LEFT_ALIGNMENT);
            player1.setPreferredSize(new Dimension(GamePiece.PIECE_PREVIEW_RESOLUTION, 30));

            player2 = new JLabel(players[1].name);
            player2.setForeground(Color.RED);
            player2.setAlignmentX(Component.LEFT_ALIGNMENT);
            player2.setPreferredSize(new Dimension(GamePiece.PIECE_PREVIEW_RESOLUTION, 30));

            player3 = new JLabel(players[2].name);
            player3.setForeground(Color.ORANGE);
            player3.setAlignmentX(Component.LEFT_ALIGNMENT);
            player3.setPreferredSize(new Dimension(GamePiece.PIECE_PREVIEW_RESOLUTION, 30));

            player4 = new JLabel(players[3].name);
            player4.setForeground(new Color(0, 128, 0));
            player4.setAlignmentX(Component.LEFT_ALIGNMENT);
            player4.setPreferredSize(new Dimension(GamePiece.PIECE_PREVIEW_RESOLUTION, 30));

            ///////////////////////////////////////////////////////////
            player1InfoPanel = new JPanel();
            player1InfoPanel.setLayout(new BoxLayout(player1InfoPanel, BoxLayout.Y_AXIS));

            player1InfoPanel.add(player1);
            player1InfoPanel.add(player1PiecesPanel);
            player1PiecesPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            
            player2InfoPanel = new JPanel();
            player2InfoPanel.setLayout(new BoxLayout(player2InfoPanel, BoxLayout.Y_AXIS));

            player2InfoPanel.add(player2);
            player2InfoPanel.add(player2PiecesPanel);
            player2PiecesPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

            player3InfoPanel = new JPanel();
            player3InfoPanel.setLayout(new BoxLayout(player3InfoPanel, BoxLayout.Y_AXIS));

            player3InfoPanel.add(player3);
            player3InfoPanel.add(player3PiecesPanel);
            player3PiecesPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

            player4InfoPanel = new JPanel();
            player4InfoPanel.setLayout(new BoxLayout(player4InfoPanel, BoxLayout.Y_AXIS));

            player4InfoPanel.add(player4);
            player4InfoPanel.add(player4PiecesPanel);
            player4PiecesPanel.setAlignmentX(Component.LEFT_ALIGNMENT);


            ////////////////////////////////////////////////////////////////////////////////////

            surrenderButton = new JButton("Give Up");
            surrenderButton.setPreferredSize(new Dimension(120, 30));
            surrenderButton.addActionListener(new SurrenderListener());

            leftSidePanel = new JPanel();
            leftSidePanel.setLayout(new BoxLayout(leftSidePanel, BoxLayout.X_AXIS));

            rightSidePanel = new JPanel();
            rightSidePanel.setLayout(new BoxLayout(rightSidePanel, BoxLayout.X_AXIS));

            topSidePanel = new JPanel();
            topSidePanel.setLayout(new BoxLayout(topSidePanel, BoxLayout.X_AXIS));
            
            bottomSidePanel = new JPanel();
            bottomSidePanel.setLayout(new BoxLayout(bottomSidePanel, BoxLayout.X_AXIS));

            boardPanel = new JPanel();
            boardImage = new ImageIcon(board.render());

            label = new JLabel(boardImage);

            BoardClickListener bcl = new BoardClickListener();

            label.addMouseListener(bcl);
            label.addMouseMotionListener(bcl);
            label.addMouseWheelListener(bcl);

            boardPanel.add(label);
            //leftSidePanel.add(player1);
            leftSidePanel.add(player1InfoPanel);

            //rightSidePanel.add(player2);
            rightSidePanel.add(player2InfoPanel);

            //topSidePanel.add(player3);
            topSidePanel.add(player3InfoPanel);

            //bottomSidePanel.add(player4);
            bottomSidePanel.add(player4InfoPanel);
            
            JButton   quitButton = new JButton("Quit");
       		//b4.setFont(new Font("Bodoni MT Black", Font.BOLD, 22));
       		//b4.setBackground(Color.white);
       		//b4.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
       		quitButton.addActionListener(new ActionListener(){
       			public void actionPerformed(ActionEvent e)
       			{
       				bf.gameOver();
       				bf.dispose();
       				
       				MainMenu mm = new MainMenu();
       				mm.createAndShowGUI();
       			}       			
       		});

            gameTimerLabel = new JLabel("GAME TIME:");
            turnTimerLabel = new JLabel("TURN TIME:");
            gameTimerLabel.setFont(new Font("Serif", Font.PLAIN, 30));
            turnTimerLabel.setFont(new Font("Serif", Font.PLAIN, 30));
            gameInfoLabel = new JLabel("GAME INFORMATION GOES HERE.");
            gameInfoLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

            gameTimePanel = new JPanel();
            turnTimePanel = new JPanel();
            gameTimePanel.setLayout(new BorderLayout());
            turnTimePanel.setLayout(new BorderLayout());
            
            turnTimePanel.add(new JLabel("Turn Time:"),BorderLayout.PAGE_START);
            turnTimePanel.add(turnTimerLabel,BorderLayout.CENTER);
            
            gameTimePanel.add(new JLabel("Game Time:"),BorderLayout.PAGE_START);
            gameTimePanel.add(gameTimerLabel,BorderLayout.CENTER);
           
            botPanel.add(gameInfoLabel);
            botPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

//            botPanel.add(leftSidePanel);
//            botPanel.add(rightSidePanel);
//            botPanel.add(topSidePanel);
//            botPanel.add(bottomSidePanel);
         
            topPanel.add(turnTimePanel);
           
            topPanel.add(gameTimePanel);
            topPanel.add(Box.createHorizontalStrut(100));
            topPanel.add(quitButton);
          
            showPiecePanel.add(jsp);
            showPiecePanel.add(surrenderButton);

            centerPanel = new JPanel();
 ////////////////////////////////////////////////////////////////////////////
            JPanel showAllPiecePanel = new JPanel();
            showAllPiecePanel.setLayout(new GridBagLayout());

            GridBagConstraints pieceCons = new GridBagConstraints();
            pieceCons.gridx = 0;
            pieceCons.gridy = 0;
            showAllPiecePanel.add(leftSidePanel, pieceCons);
            pieceCons.gridx = 1;
            pieceCons.gridy = 0;
            showAllPiecePanel.add(rightSidePanel, pieceCons);
            pieceCons.gridx = 0;
            pieceCons.gridy = 1;
            showAllPiecePanel.add(topSidePanel, pieceCons);
            pieceCons.gridx = 1;
            pieceCons.gridy = 1;
            showAllPiecePanel.add(bottomSidePanel, pieceCons);
/////////////////////////////////////////////////////////////////////////////
            currentPlayerPanel = new JPanel();
            currentPlayerPanel.setLayout(new GridBagLayout());
            
            GridBagConstraints cons = new GridBagConstraints();
            cons.gridx = 0;
            cons.gridy = 0;
            currentPlayerPanel.add(currentPlayerPiecesPanel, cons);
            cons.gridx = 0;
            cons.gridy = 1;
            currentPlayerPanel.add(surrenderButton, cons);
            cons.insets = new Insets(0,5,5,5);
            
            centerPanel.add(boardPanel,cons);
            
            centerPanel.add(currentPlayerPanel,cons);
            //mainPanel.setLayout(new BorderLayout());
            mainPanel.setLayout(new GridBagLayout());
// this
//            mainPanel.add(topPanel,BorderLayout.PAGE_START);
//            mainPanel.add(currentPlayerPiecesPanel,BorderLayout.LINE_END);
//            //mainPanel.add(showPiecePanel,BorderLayout.LINE_END);
//            mainPanel.add(boardPanel,BorderLayout.CENTER);
//            mainPanel.add(botPanel,BorderLayout.PAGE_END);
// this
            GridBagConstraints c = new GridBagConstraints();
            //c.fill = GridBagConstraints.PAGE_START;
            c.gridx = 1;
            c.gridy = 0;
            mainPanel.add(topPanel,c);

            //c.fill = GridBagConstraints.CENTER;
//             c.gridx = 1;
//            c.gridy = 2;
//            mainPanel.add(currentPlayerPiecesPanel,c);
            //mainPanel.add(showPiecePanel,BorderLayout.LINE_END);

   //         c.fill = GridBagConstraints.CENTER;
            c.gridx = 1;
            c.gridy = 1;

            mainPanel.add(centerPanel,c);
  //          c.fill = GridBagConstraints.PAGE_END;
            c.gridx = 1;
            c.gridy = 2;
            mainPanel.add(gameInfoLabel,c);
            gameInfoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

           // c.fill = GridBagConstraints.LINE_START;
  
            c.gridx = 0;
            c.gridy = 1;
            mainPanel.add(showAllPiecePanel,c);


            getContentPane().add(mainPanel);
           // add(b4, BorderLayout.PAGE_END);
            //this.setPreferredSize(mainPanel.getSize());
            pack();
            setVisible(true);
            turnTimer = new BlokusTimer(new TurnOver());
            gameTimer = new BlokusTimer(new GameOver());
        }

        private void rotateClockwise(){
            players[turn].pieces.get(pieceIndex).rotateRight();
            board.overlay(players[turn].pieces.get(pieceIndex), selected.x, selected.y);
            drawBoard();
        }

        private void rotateCounterClockwise(){
            players[turn].pieces.get(pieceIndex).rotateLeft();
            board.overlay(players[turn].pieces.get(pieceIndex), selected.x, selected.y);
            drawBoard();
        }

        private void flipPiece(){
            players[turn].pieces.get(pieceIndex).flip();
            board.overlay(players[turn].pieces.get(pieceIndex), selected.x, selected.y);
            drawBoard();
        }

        private void drawBoard(){
            boardImage.setImage(board.render());
            label.repaint();
        }

        private void drawBorder(){
            JComponent piece = (JComponent) currentPlayerPiecesPanel.getComponent(pieceIndex);
            piece.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        }

        private void clearBorder(){
            JComponent piece = (JComponent) currentPlayerPiecesPanel.getComponent(pieceIndex);
            piece.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        }

        private void displayMessage(String message){
            gameInfoLabel.setText(message);
            gameInfoLabel.setForeground(Color.RED);
            //JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
        }

        private class PieceLabelClickListener implements MouseListener{
            public void mouseClicked(MouseEvent e){
                BlokusPieceLabel bp = (BlokusPieceLabel) e.getComponent();
                clearBorder();
                pieceIndex = bp.pieceIndex;
                drawBorder();
            }

            public void mousePressed(MouseEvent e){
            }
            public void mouseReleased(MouseEvent e){
            }
            public void mouseEntered(MouseEvent e){
            }
            public void mouseExited(MouseEvent e){
            }
        }

        private void timesUpTurn(){
            turn++;
            turn %= NUM_PLAYERS;

            gameInfoLabel.setText(players[turn].name + "'s" + " turn");
            gameInfoLabel.setForeground(Color.BLACK);

            if(isGameOver()){
                gameOver();
            }           

            if (!players[turn].canPlay){
                startNewTurn();
                return;
            }      
            
            currentPlayerPiecesPanel.removeAll();
            for (int i = 0; i < players[turn].pieces.size(); i++){
                if(!players[turn].pieces.get(i).isPieceUsed()){
                    BlokusPieceLabel pieceLabel =
                    new BlokusPieceLabel(i, players[turn].pieces.get(i), GamePiece.PIECE_PREVIEW_RESOLUTION);
                    pieceLabel.addMouseListener(new PieceLabelClickListener());
                    pieceLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                    currentPlayerPiecesPanel.add(pieceLabel);
                }
                else{
                    BlokusPieceLabel pieceLabel =
                    new BlokusPieceLabel(i, players[turn].pieces.get(i), GamePiece.PIECE_PREVIEW_RESOLUTION);
                    pieceLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                    currentPlayerPiecesPanel.add(pieceLabel);
                }
            }
        }

        private void startNewTurn(){
            turn++;
            turn %= NUM_PLAYERS;

            gameInfoLabel.setText(players[turn].name + "'s" + " turn");
            gameInfoLabel.setForeground(Color.BLACK);

            if(isGameOver()){
                gameOver();
            }           

            if (!players[turn].canPlay){
                startNewTurn();
                return;
            }           

            currentPlayerPiecesPanel.removeAll();
            for (int i = 0; i < players[turn].pieces.size(); i++){
                if(!players[turn].pieces.get(i).isPieceUsed()){
                    BlokusPieceLabel pieceLabel =
                    new BlokusPieceLabel(i, players[turn].pieces.get(i), GamePiece.PIECE_PREVIEW_RESOLUTION);
                    pieceLabel.addMouseListener(new PieceLabelClickListener());
                    pieceLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                    currentPlayerPiecesPanel.add(pieceLabel);
                }
                else{
                    BlokusPieceLabel pieceLabel =
                    new BlokusPieceLabel(i, players[turn].pieces.get(i), GamePiece.PIECE_PREVIEW_RESOLUTION);
                    pieceLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                    currentPlayerPiecesPanel.add(pieceLabel);
                }
            }
            
            player1PiecesPanel.removeAll();
            for (int i = 0; i < players[0].pieces.size(); i++){
                BlokusPieceLabel player1PieceLabel =
                new BlokusPieceLabel(i, players[0].pieces.get(i), GamePiece.PIECE_PREVIEW_RESOLUTION);
                player1PieceLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                player1PiecesPanel.add(player1PieceLabel);
            }

            player2PiecesPanel.removeAll();
            for (int i = 0; i < players[1].pieces.size(); i++){
                BlokusPieceLabel player2PieceLabel =
                new BlokusPieceLabel(i, players[1].pieces.get(i), GamePiece.PIECE_PREVIEW_RESOLUTION);
                player2PieceLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                player2PiecesPanel.add(player2PieceLabel);
            }

            player3PiecesPanel.removeAll();
            for (int i = 0; i < players[2].pieces.size(); i++){
                BlokusPieceLabel player3PieceLabel =
                new BlokusPieceLabel(i, players[2].pieces.get(i), GamePiece.PIECE_PREVIEW_RESOLUTION);
                player3PieceLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                player3PiecesPanel.add(player3PieceLabel);
            }

            player4PiecesPanel.removeAll();
            for (int i = 0; i < players[3].pieces.size(); i++){
                BlokusPieceLabel player4PieceLabel =
                new BlokusPieceLabel(i, players[3].pieces.get(i), GamePiece.PIECE_PREVIEW_RESOLUTION);
                player4PieceLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                player4PieceLabel.setBackground(Color.BLACK);
                player4PiecesPanel.add(player4PieceLabel);
            }

            pieceIndex = 0;

            drawBorder();
            player1PiecesPanel.repaint();
            player2PiecesPanel.repaint();
            player3PiecesPanel.repaint();
            player4PiecesPanel.repaint();
            currentPlayerPiecesPanel.repaint();

            //pack();
        }

        private boolean isGameOver(){
            for (int i = 0; i < NUM_PLAYERS; i++){
                if (players[i].canPlay) return false;
            }
            return true;
        }

        private void gameOver(){
            turnTimer.stopTimer();
            gameTimer.stopTimer();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < NUM_PLAYERS; i++){
                sb.append(Board.getColorName(getPlayerColor(i)));
                sb.append(": ");
                sb.append(players[i].getTotalScore());
                sb.append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "Game Over", JOptionPane.INFORMATION_MESSAGE );
            
            //System.exit(0);
        }

        private int getPlayerColor(int index){
            switch (index){
                case 0: return Board.BLUE;
                case 1: return Board.RED;
                case 2: return Board.ORANGE;
                case 3: return Board.GREEN;
                default: return 0;
            }
        }
    }

    public static class BlokusPieceLabel extends JLabel{
        public int pieceIndex;

        public BlokusPieceLabel(int pieceIndex, GamePiece gamePiece, int size){
            super(new ImageIcon(gamePiece.render(size)));
            this.pieceIndex = pieceIndex;
        }
    }

//     public static void main(String[] args){
////BlokusFrame(String p1Name, String p2Name, String p3Name, String p4Name, int turnTime, int gameTime, int[][] boardType, int boardSize)
//        //BlokusFrame bf = new BlokusFrame();
//        BlokusFrame bf = new BlokusFrame("Alex", "Kami", "Stacy", "Shujie", 5, 600, 20);
//    }
}