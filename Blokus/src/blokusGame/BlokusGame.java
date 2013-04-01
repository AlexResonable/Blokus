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
        private JPanel showPiecePanel;
        private JPanel player1PiecesPanel;
        private JPanel player2PiecesPanel;
        private JPanel player3PiecesPanel;
        private JPanel player4PiecesPanel;

        private JPanel turnTimePanel;
        private JPanel gameTimePanel;

        private JLabel player1;
        private JLabel player2;
        private JLabel player3;
        private JLabel player4;

        private JPanel boardPanel;
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

        public BlokusFrame(){
            super("Blokus");
            this.setLayout(new BorderLayout());
            board = new Board();
            players = new Player[NUM_PLAYERS];
            players[0] = new Player(Board.BLUE);
            players[1] = new Player(Board.RED);
            players[2] = new Player(Board.ORANGE);
            players[3] = new Player(Board.GREEN);

            turnTimeLimit = 10;
            gameTimeLimit = 62;
            turnTimeInSeconds = 1;

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            initializeGUI();
            startNewTurn();
        }
        
        public BlokusFrame(int turnTime, int gameTime){
            
        }
        
        

        class TurnOver extends TimerTask {
            public void run() {
                turnTimerLabel.setText( Integer.toString(turnTimeLimit - (turnTimeInSeconds % turnTimeLimit)));
                if(turnTimeInSeconds % turnTimeLimit == 0){
                    startNewTurn();
                    gameInfoLabel.setText("TURN " + Integer.toString(turn));
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
                            //displayMessage(ex.getMessage(), "Illegal Move!");
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
            currentPlayerPiecesPanel.setLayout(new BoxLayout(currentPlayerPiecesPanel, BoxLayout.PAGE_AXIS));

            player1PiecesPanel = new JPanel();
            player1PiecesPanel.setLayout(new BoxLayout(player1PiecesPanel, BoxLayout.LINE_AXIS));

            player2PiecesPanel = new JPanel();
            player2PiecesPanel.setLayout(new BoxLayout(player2PiecesPanel, BoxLayout.LINE_AXIS));

            player3PiecesPanel = new JPanel();
            player3PiecesPanel.setLayout(new BoxLayout(player3PiecesPanel, BoxLayout.LINE_AXIS));

            player4PiecesPanel = new JPanel();
            player4PiecesPanel.setLayout(new BoxLayout(player4PiecesPanel, BoxLayout.LINE_AXIS));

            JScrollPane jsp = new JScrollPane(currentPlayerPiecesPanel);
            jsp.getVerticalScrollBar().setUnitIncrement(120 / 3);
            jsp.setPreferredSize(new Dimension(40, 90));

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
            

            player1 = new JLabel("Player1");
            player1.setPreferredSize(new Dimension(GamePiece.PIECE_PREVIEW_RESOLUTION, 30));

            player2 = new JLabel("Player2");
            player2.setPreferredSize(new Dimension(GamePiece.PIECE_PREVIEW_RESOLUTION, 30));

            player3 = new JLabel("Player3");
            player3.setPreferredSize(new Dimension(GamePiece.PIECE_PREVIEW_RESOLUTION, 30));

            player4 = new JLabel("Player4");
            player4.setPreferredSize(new Dimension(GamePiece.PIECE_PREVIEW_RESOLUTION, 30));

            surrenderButton = new JButton("Give Up");
            surrenderButton.setPreferredSize(new Dimension(120, 30));
            surrenderButton.addActionListener(new SurrenderListener());

            leftSidePanel = new JPanel();
            leftSidePanel.setLayout(new BoxLayout(leftSidePanel, BoxLayout.PAGE_AXIS));

            rightSidePanel = new JPanel();
            rightSidePanel.setLayout(new BoxLayout(rightSidePanel, BoxLayout.PAGE_AXIS));

            topSidePanel = new JPanel();
            topSidePanel.setLayout(new BoxLayout(topSidePanel, BoxLayout.PAGE_AXIS));
            
            bottomSidePanel = new JPanel();
            bottomSidePanel.setLayout(new BoxLayout(bottomSidePanel, BoxLayout.PAGE_AXIS));

            boardPanel = new JPanel();
            boardImage = new ImageIcon(board.render());

            label = new JLabel(boardImage);

            BoardClickListener bcl = new BoardClickListener();

            label.addMouseListener(bcl);
            label.addMouseMotionListener(bcl);
            label.addMouseWheelListener(bcl);

            boardPanel.add(label);
            
            leftSidePanel.add(player1PiecesPanel);
            rightSidePanel.add(player2PiecesPanel);
            topSidePanel.add(player3PiecesPanel);
            bottomSidePanel.add(player4PiecesPanel);
            
            JButton   b4 = new JButton("Quit");
       		b4.setFont(new Font("Bodoni MT Black", Font.BOLD, 22));
       		//b4.setBackground(Color.white);
       		//b4.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
       		b4.addActionListener(new ActionListener(){
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

            gameTimePanel = new JPanel();
            turnTimePanel = new JPanel();
            gameTimePanel.setLayout(new BorderLayout());
            turnTimePanel.setLayout(new BorderLayout());
            
            turnTimePanel.add(new JLabel("Turn Time:"),BorderLayout.PAGE_START);
            turnTimePanel.add(turnTimerLabel,BorderLayout.CENTER);
            

            gameTimePanel.add(new JLabel("Game Time:"),BorderLayout.PAGE_START);
            gameTimePanel.add(gameTimerLabel,BorderLayout.CENTER);
           

            botPanel.add(gameInfoLabel);
            botPanel.add(leftSidePanel);
            botPanel.add(rightSidePanel);
            botPanel.add(topSidePanel);
            botPanel.add(bottomSidePanel);
            
         
          
            topPanel.add(turnTimePanel);
           
            topPanel.add(gameTimePanel);
            topPanel.add(Box.createHorizontalStrut(100));
            topPanel.add(b4);
          
            showPiecePanel.add(jsp);
            showPiecePanel.add(surrenderButton);


            mainPanel.setLayout(new BorderLayout());

            mainPanel.add(topPanel,BorderLayout.PAGE_START);
            mainPanel.add(showPiecePanel,BorderLayout.LINE_END);
            mainPanel.add(boardPanel,BorderLayout.CENTER);
            mainPanel.add(botPanel,BorderLayout.PAGE_END);


            getContentPane().add(mainPanel);
           // add(b4, BorderLayout.PAGE_END);
          //  pack();
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

        private void displayMessage(String message, String title){
            JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
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

        private void startNewTurn(){
            turn++;
            turn %= NUM_PLAYERS;

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
                    new BlokusPieceLabel(i, players[turn].pieces.get(i), 120);
                    pieceLabel.addMouseListener(new PieceLabelClickListener());
                    pieceLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                    currentPlayerPiecesPanel.add(pieceLabel);
                }
            }

            player1PiecesPanel.removeAll();
            for (int i = 0; i < players[3].pieces.size(); i++){
                BlokusPieceLabel pieceLabel3 =
                new BlokusPieceLabel(i, players[3].pieces.get(i), GamePiece.PIECE_PREVIEW_RESOLUTION);
                pieceLabel3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                player1PiecesPanel.add(pieceLabel3);
            }

            player2PiecesPanel.removeAll();
            for (int i = 0; i < players[2].pieces.size(); i++){
                BlokusPieceLabel pieceLabel2 =
                new BlokusPieceLabel(i, players[2].pieces.get(i), GamePiece.PIECE_PREVIEW_RESOLUTION);
                pieceLabel2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                player2PiecesPanel.add(pieceLabel2);
            }

            player3PiecesPanel.removeAll();
            for (int i = 0; i < players[1].pieces.size(); i++){
                BlokusPieceLabel pieceLabel1 =
                new BlokusPieceLabel(i, players[1].pieces.get(i), GamePiece.PIECE_PREVIEW_RESOLUTION);
                pieceLabel1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                player3PiecesPanel.add(pieceLabel1);
            }

            player4PiecesPanel.removeAll();
            for (int i = 0; i < players[0].pieces.size(); i++){
                BlokusPieceLabel pieceLabel0 =
                new BlokusPieceLabel(i, players[0].pieces.get(i), GamePiece.PIECE_PREVIEW_RESOLUTION);
                pieceLabel0.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                pieceLabel0.setBackground(Color.BLACK);
                player4PiecesPanel.add(pieceLabel0);
            }

            pieceIndex = 0;

            drawBorder();
            player1PiecesPanel.repaint();
            player2PiecesPanel.repaint();
            player3PiecesPanel.repaint();
            player4PiecesPanel.repaint();
            currentPlayerPiecesPanel.repaint();

            pack();
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

   
}