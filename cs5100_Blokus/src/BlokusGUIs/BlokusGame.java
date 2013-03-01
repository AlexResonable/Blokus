package BlokusGUIs;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

public class BlokusGame{
    public static final int NUM_PLAYERS = 4;
    
    public BlokusGame()
    {
    	BlokusFrame bf = new BlokusFrame();
    }

    public static class BlokusFrame extends JFrame{
    	static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    	static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        private Board board;
        private Player[] players;
        private int turn = -1;

        private int pieceIndex;
        private int pieceIndex2;
        private int pieceIndex3;
        private int pieceIndex4;
        private Point selected;

        private JPanel mainPanel;
        private JPanel leftSidePanel;
        private JPanel rightSidePanel;
        private JPanel topSidePanel;
        private JPanel bottomSidePanel;

        private JPanel piecesPanel1;
        private JPanel piecesPanel2;
        private JPanel piecesPanel3;
        private JPanel piecesPanel4;

        private JLabel player1;
        private JLabel player2;
        private JLabel player3;
        private JLabel player4;

        private JPanel boardPanel;
        private JLabel label;
        private ImageIcon boardImage;
        private JButton surrender;
        private JFrame fm;

        public BlokusFrame(){
            super("Blokus");
            fm = this;
            this.setLocation((screenWidth -800)/2,(screenHeight-800)/2);
            this.setSize(1000, 1000);
           this.setLayout(new BorderLayout());
            board = new Board();
            players = new Player[NUM_PLAYERS];
            players[0] = new Player(Board.BLUE);
            players[1] = new Player(Board.RED);
            players[2] = new Player(Board.YELLOW);
            players[3] = new Player(Board.GREEN);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            initializeGUI();
            startNewTurn();
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
                            players[turn].pieces.remove(pieceIndex);
                            players[turn].firstMove = false;
                            players[turn].canPlay = players[turn].pieces.size() != 0;
                            startNewTurn();
                        }
                        catch (Board.IllegalMoveException ex){
                            displayMessage(ex.getMessage(), "Illegal Move!");
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

            piecesPanel1 = new JPanel();
            piecesPanel1.setLayout(new BoxLayout(piecesPanel1, BoxLayout.PAGE_AXIS));

            piecesPanel2 = new JPanel();
            piecesPanel2.setLayout(new BoxLayout(piecesPanel2, BoxLayout.PAGE_AXIS));

            piecesPanel3 = new JPanel();
            piecesPanel3.setLayout(new BoxLayout(piecesPanel3, BoxLayout.LINE_AXIS));

            piecesPanel4 = new JPanel();
            piecesPanel4.setLayout(new BoxLayout(piecesPanel4, BoxLayout.LINE_AXIS));

            JScrollPane jsp1 = new JScrollPane(piecesPanel1);
            jsp1.getVerticalScrollBar().setUnitIncrement(GamePiece.DEFAULT_RESOLUTION / 3);
            jsp1.setPreferredSize(new Dimension(GamePiece.DEFAULT_RESOLUTION - 80, Board.DEFAULT_RESOLUTION - 30));

            JScrollPane jsp2 = new JScrollPane(piecesPanel2);
            jsp2.getVerticalScrollBar().setUnitIncrement(GamePiece.DEFAULT_RESOLUTION / 3);
            jsp2.setPreferredSize(new Dimension(GamePiece.DEFAULT_RESOLUTION - 80, Board.DEFAULT_RESOLUTION - 30));

            JScrollPane jsp3 = new JScrollPane(piecesPanel3);
            jsp3.getVerticalScrollBar().setUnitIncrement(GamePiece.DEFAULT_RESOLUTION / 3);
            jsp3.setPreferredSize(new Dimension(Board.DEFAULT_RESOLUTION - 30,GamePiece.DEFAULT_RESOLUTION - 80));

            JScrollPane jsp4 = new JScrollPane(piecesPanel4);
            jsp4.getVerticalScrollBar().setUnitIncrement(GamePiece.DEFAULT_RESOLUTION / 3);
            jsp4.setPreferredSize(new Dimension(Board.DEFAULT_RESOLUTION - 30,GamePiece.DEFAULT_RESOLUTION - 80));

            player1 = new JLabel("Player1");
            player1.setPreferredSize(new Dimension(GamePiece.DEFAULT_RESOLUTION, 30));

            player2 = new JLabel("Player2");
            player2.setPreferredSize(new Dimension(GamePiece.DEFAULT_RESOLUTION, 30));

            player3 = new JLabel("Player3");
            player3.setPreferredSize(new Dimension(GamePiece.DEFAULT_RESOLUTION, 30));

            player4 = new JLabel("Player4");
            player4.setPreferredSize(new Dimension(GamePiece.DEFAULT_RESOLUTION, 30));

            surrender = new JButton("Surrender");
            surrender.setPreferredSize(new Dimension(GamePiece.DEFAULT_RESOLUTION, 30));
            surrender.addActionListener(new SurrenderListener());

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

            leftSidePanel.add(jsp1);
            rightSidePanel.add(jsp2);
            topSidePanel.add(jsp3);
            bottomSidePanel.add(jsp4);

            
           leftSidePanel.setPreferredSize(new Dimension(GamePiece.DEFAULT_RESOLUTION+20,520));
           rightSidePanel.setPreferredSize(new Dimension(GamePiece.DEFAULT_RESOLUTION+20,520));
           topSidePanel.setPreferredSize(new Dimension(520,GamePiece.DEFAULT_RESOLUTION+20));
           bottomSidePanel.setPreferredSize(new Dimension(520,GamePiece.DEFAULT_RESOLUTION+20));

            //leftSidePanel.add(player1);
            //rightSidePanel.add(player2);
            //topSidePanel.add(player3);
            //bottomSidePanel.add(player4);
           
          JButton   b4 = new JButton("Quit");
   		b4.setFont(new Font("Bodoni MT Black", Font.BOLD, 22));
   		b4.setBackground(Color.white);
   		b4.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
   		b4.addActionListener(new ActionListener(){
   			public void actionPerformed(ActionEvent e)
   			{
   				fm.dispose();
   				MainMenu mm = new MainMenu();
   				mm.createAndShowGUI();
   			}
   			
   		});
    		
            mainPanel.setLayout(new BorderLayout());
            
           
            mainPanel.add(leftSidePanel,BorderLayout.LINE_START);
           
            mainPanel.add(rightSidePanel,BorderLayout.LINE_END);
           
            mainPanel.add(boardPanel,BorderLayout.CENTER);
           
            
            mainPanel.add(topSidePanel,BorderLayout.PAGE_START);
           
            mainPanel.add(bottomSidePanel,BorderLayout.PAGE_END);
           
            
            
            getContentPane().add(mainPanel, BorderLayout.PAGE_START);
           // mainPanel.setBounds(0,0,800, 800);
            getContentPane().add(b4,BorderLayout.PAGE_END );
           // pack();
            setVisible(true);
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
            JComponent piece1 = (JComponent) piecesPanel1.getComponent(pieceIndex);
            piece1.setBorder(BorderFactory.createLineBorder(Color.GREEN));

            JComponent piece2 = (JComponent) piecesPanel2.getComponent(pieceIndex);
            piece2.setBorder(BorderFactory.createLineBorder(Color.GREEN));

            JComponent piece3 = (JComponent) piecesPanel3.getComponent(pieceIndex);
            piece3.setBorder(BorderFactory.createLineBorder(Color.GREEN));

            JComponent piece4 = (JComponent) piecesPanel4.getComponent(pieceIndex);
            piece4.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        }

        private void clearBorder(){
            JComponent piece1 = (JComponent) piecesPanel1.getComponent(pieceIndex);
            piece1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            JComponent piece2 = (JComponent) piecesPanel2.getComponent(pieceIndex);
            piece1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            JComponent piece3 = (JComponent) piecesPanel3.getComponent(pieceIndex);
            piece3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            JComponent piece4 = (JComponent) piecesPanel4.getComponent(pieceIndex);
            piece4.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
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

            piecesPanel1.removeAll();            
            for (int i = 0; i < players[3].pieces.size(); i++){
                BlokusPieceLabel pieceLabel3 =
                new BlokusPieceLabel(i, players[3].pieces.get(i), GamePiece.DEFAULT_RESOLUTION);
                pieceLabel3.addMouseListener(new PieceLabelClickListener());
                pieceLabel3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                piecesPanel1.add(pieceLabel3);
            }

            piecesPanel2.removeAll();
            for (int i = 0; i < players[2].pieces.size(); i++){
                BlokusPieceLabel pieceLabel2 =
                new BlokusPieceLabel(i, players[2].pieces.get(i), GamePiece.DEFAULT_RESOLUTION);
                pieceLabel2.addMouseListener(new PieceLabelClickListener());
                pieceLabel2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                piecesPanel2.add(pieceLabel2);
            }

            piecesPanel3.removeAll();
            for (int i = 0; i < players[1].pieces.size(); i++){
                BlokusPieceLabel pieceLabel1 =
                new BlokusPieceLabel(i, players[1].pieces.get(i), GamePiece.DEFAULT_RESOLUTION);
                pieceLabel1.addMouseListener(new PieceLabelClickListener());
                pieceLabel1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                piecesPanel3.add(pieceLabel1);
            }

            piecesPanel4.removeAll();
            for (int i = 0; i < players[0].pieces.size(); i++){
                BlokusPieceLabel pieceLabel0 =
                new BlokusPieceLabel(i, players[0].pieces.get(i), GamePiece.DEFAULT_RESOLUTION);
                pieceLabel0.addMouseListener(new PieceLabelClickListener());
                pieceLabel0.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                piecesPanel4.add(pieceLabel0);
            }

            pieceIndex = 0;
            pieceIndex2 = 0;
            pieceIndex3 = 0;
            pieceIndex4 = 0;

            drawBorder();
            piecesPanel1.repaint();
            piecesPanel2.repaint();
            piecesPanel3.repaint();
            piecesPanel4.repaint();
            pack();
        }

        private boolean isGameOver(){
            for (int i = 0; i < NUM_PLAYERS; i++){
                if (players[i].canPlay) return false;
            }
            return true;
        }

        private void gameOver()
        {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < NUM_PLAYERS; i++){
                sb.append(Board.getColorName(getPlayerColor(i)));
                sb.append(": ");
                sb.append(players[i].getTotalScore());
                sb.append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "Game Over", JOptionPane.INFORMATION_MESSAGE );
            System.exit(0);
        }

        private int getPlayerColor(int index){
            switch (index){
                case 0: return Board.BLUE;
                case 1: return Board.RED;
                case 2: return Board.YELLOW;
                case 3: return Board.GREEN;
                default: return 0;
            }
        }
    }

    public static class BlokusPieceLabel extends JLabel{
        public int pieceIndex;

        public BlokusPieceLabel(int pieceIndex, GamePiece bp, int size){
            super(new ImageIcon(bp.render(size)));
            this.pieceIndex = pieceIndex;
        }
    }

  /*  public static void main(String[] args){
        BlokusFrame bf = new BlokusFrame();
    }*/
}