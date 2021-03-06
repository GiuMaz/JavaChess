package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import main.PieceRappresentation;
import model.ChessConfiguration;
import model.ChessModel;
import model.Configuration;
import model.Model;
import model.Position;

import controller.ChessController;
import controller.Controller;

/**
 * Implementazione dell'interfaccia View.
 * Consiste in un interfaccia grafica basata su Swing
 * La scacchiera è vista come una matrice di bottoni
 * 
 * Per la descrizione dei singoli metodi si rimanda all'interfaccia
 *  
 */
public class ChessView extends JFrame implements View {

	private Model model = new ChessModel( new ChessConfiguration());
	private Controller controller = new ChessController(this);
	
	// setto colori per evidenzizione
	private static final Color DARK_GREEN = Color.decode("#00A300");
	private static final Color LIGHT_GREEN = Color.decode("#7CFC00");
	
	private static final long serialVersionUID = 1L;

	JButton[][] board = new JButton[8][8];
	private static final ImageIcon[][] images = new ImageIcon[2][6];
	
	public ChessView()
	{
		createImages();
		model.setView(this);
		add(createBoard(),BorderLayout.CENTER);
		add(controlPanel(),BorderLayout.EAST);
		controller.onNewGame();
		pack();
		setResizable(false);
	}
	
	private JPanel controlPanel() {
		JPanel newPanel = new JPanel();
		JButton newGame = new JButton("nuovo gioco");
		newGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(controller != null)
					onNewGameRequest();
			}
		});
		newPanel.add(newGame);
		return newPanel;
	}

	private JPanel createBoard() {
		
		JPanel newBoard = new JPanel();
		newBoard.setLayout(new GridLayout(8,8));
		newBoard.setPreferredSize(new Dimension(640,640));
		
		for( int row = 0; row < 8; row++ )
			for( int col = 0; col < 8; col++)
				newBoard.add(board[row][col] = mkButton(model.getConfiguration().makePosition(row, col)));
		
		onConfigurationChange();
		return newBoard;
	}

	private void createImages() {
		try {
			BufferedImage image = ImageIO.read(new File("img/chess_final.png"));
			for(int i=0; i<2;i++)
				for(int j=0;j<6;j++)
					images[i][j] = new ImageIcon(image.getSubimage(60*j, 60*i, 60, 60));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private JButton mkButton(final Position pos) {
		JButton newButton = new JButton();
		newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if ( controller != null ) {
					controller.onClick( pos );
				}
			}
		});
		
		newButton.setPreferredSize(new Dimension(80,80) );
		if( pos.isLightColor() )
		{
			newButton.setBackground(Color.LIGHT_GRAY);
			newButton.setBorder(new LineBorder(LIGHT_GREEN,4));
		}
		else {
			newButton.setBackground(Color.DARK_GRAY);
			newButton.setBorder(new LineBorder(DARK_GREEN,4));
		}
		
		newButton.setBorderPainted(false);
		newButton.setFocusable(false);
		return newButton;
	}

	@Override
	public void onConfigurationChange() {
		Configuration conf = model.getConfiguration();
		for( Position pos : model.allBoardPosition() )
		{
			if ( conf.at( pos ) != null)
				buttonAt(pos).setIcon( getImage(conf.at(pos).getRapresentation()) );
			else
				buttonAt(pos).setIcon(null);
		}
	}

	private Icon getImage(PieceRappresentation rapr) {
		switch(rapr)
		{
		case PAWN_WHITE: return images[0][5];
		case PAWN_BLACK: return images[1][5];
		case ROOK_WHITE: return images[0][4];
		case ROOK_BLACK: return images[1][4];
		case KNIGTH_WHITE: return images[0][3];
		case KNIGTH_BLACK: return images[1][3];
		case BISHOP_WHITE: return images[0][2];
		case BISHOP_BLACK: return images[1][2];
		case QUEEN_WHITE: return images[0][1];
		case QUEEN_BLACK: return images[1][1];
		case KING_WHITE: return images[0][0];
		case KING_BLACK: return images[1][0];
		default: return null;
		}
	}

	@Override
	public void setController(Controller controller) {
		this.controller = controller;

	}

	@Override
	public void onGameOver() {
		int answer = JOptionPane.showConfirmDialog(this, "You win! Do you want to start a new game?",
				"Game over",JOptionPane.YES_NO_OPTION);
		if(answer == JOptionPane.OK_OPTION)
			controller.onNewGame();
	}

	@Override
	public void onNewGameRequest() {
		int answer = JOptionPane.showConfirmDialog(this, "Do you want to start a new game?",
				"New Game",JOptionPane.YES_NO_OPTION);
		if(answer == JOptionPane.OK_OPTION)
			controller.onNewGame();

	}

	@Override
	public Model getModel()  {
		return model;
	}

	@Override
	public void onSelectionChange(){
		
		for(Position pos : model.allBoardPosition())
		{
			if ( model.isSelected() &&  model.getSelection().equals(pos) )
				buttonAt(pos).setBorderPainted(true);
			else
				buttonAt(pos).setBorderPainted(false);
			
			if( pos.isLightColor() )
			{
				if (model.getSelectionPossibleMove().contains(pos))
					buttonAt(pos).setBackground(LIGHT_GREEN);
				else
					buttonAt(pos).setBackground(Color.LIGHT_GRAY);
			}
			else {
				if (model.getSelectionPossibleMove().contains(pos))
					buttonAt(pos).setBackground(DARK_GREEN);
				else
					buttonAt(pos).setBackground(Color.DARK_GRAY);
			}
		}	
	}

	@Override
	public int onPieceUpgrade() {
		Object[] options = {"Queen","Knight","Bishop","Rook"};
		int selection = JOptionPane.showOptionDialog(this, "Select an upgrade",
				"Upgrade Piece",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
		if( selection == JOptionPane.DEFAULT_OPTION)
			return onPieceUpgrade();
		else 
			return selection;
	}
	
	private JButton buttonAt(Position pos) {
		if(pos.isInBound())
			return board[pos.getRow()][pos.getColumn()];
		else
			return null;
	}
}
