package model;

import java.util.HashSet;
import java.util.Set;

import main.ChessColor;

import rules.Piece;

/**
 * Implementazione dell'Interfaccia Configuration.
 *  
 *  È caratterizzata dall'uso di memoria extra per semplificare i calcoli
 *  secondo i criteri della programmazione dinamica.
 *  
 *  La rappresentazione è pezzocentrica, ovvero viene vista principalmente
 *  come un insieme di pezzi (dotati di posizione) da cui si derivano le altre
 *  informazioni
 *  
 *  Per la descrizione dei singoli metodi si rimanda all'interfaccia
 *
 */
public final class ChessConfiguration implements Configuration {

	private final Set<Piece> whites;
	private final Set<Piece> blacks;
	
	private Piece blackKing;
	private Piece whiteKing;
	
	private final ChessColor turn;
	
	private final Piece[][] board = new Piece[8][8];
	
	public ChessConfiguration() {
		whites = new HashSet<>();
		blacks = new HashSet<>();
		turn = ChessColor.WHITE;
		fillMatrix();
	}
	
	public ChessConfiguration(Set<Piece> pieces, ChessColor turn)
	{
		whites = new HashSet<>();
		blacks = new HashSet<>();
		for(Piece p : pieces)
		{
			if( p.getColor() == ChessColor.WHITE )
				whites.add(p);
			else
				blacks.add(p);
		}
		this.turn = turn;
		fillMatrix();
	}
	
	public ChessConfiguration(Set<Piece> pieces)
	{
		this(pieces, ChessColor.WHITE);
	}
	
	// veloce ma richiede la certezza di conoscere bianchi e neri
	private ChessConfiguration(Set<Piece> whites, Set<Piece> blacks, ChessColor turn)
	{
		this.whites = whites;
		this.blacks = blacks;
		this.turn = turn;
		fillMatrix();
	}
	
	@Override
	public Piece at(Position p) {
		if( p.isInBound())
			return board[p.getRow()][p.getColumn()];
		else
			return null;
	}
	
	// una casella fuori dalla scacchiera ritorna SEMPRE FALSE
	@Override
	public boolean isEmpty(Position p) {
		return p.isInBound() && board[p.getRow()][p.getColumn()] == null ;
	}

	@Override
	public boolean isEnemy(Position p) {
		return !isEmpty(p) && board[p.getRow()][p.getColumn()].getColor() != this.turn;
	}

	@Override
	public boolean isFriend(Position p) {
		return !isEmpty(p) && board[p.getRow()][p.getColumn()].getColor() == this.turn;
	}

	@Override
	public Set<Piece> friendPieces() {
		return turn == ChessColor.WHITE ? whites : blacks;
	}

	@Override
	public Set<Piece> enemyPieces() {
		return turn == ChessColor.WHITE ? blacks : whites;
	}

	@Override
	public Piece enemyKing() {
		return turn == ChessColor.WHITE ? blackKing : whiteKing;
	}

	@Override
	public Piece friendKing() {
		return turn == ChessColor.WHITE ? whiteKing : blackKing;
	}

	@Override
	public ChessConfiguration add(Piece newPiece) {
		
		HashSet<Piece> newWhite = new HashSet<>();
		for( Piece p: whites)
			if( !p.getPosition().equals(newPiece.getPosition()) )
				newWhite.add(p);
		
		HashSet<Piece> newBlacks = new HashSet<>();
		for( Piece p: blacks)
			if( !p.getPosition().equals(newPiece.getPosition()) )
				newBlacks.add(p);
		
		if( newPiece.getColor() == ChessColor.WHITE )
			newWhite.add(newPiece);
		else
			newBlacks.add(newPiece);
			
		return new ChessConfiguration(newWhite,newBlacks,this.turn);
	}

	@Override
	public ChessConfiguration buildFromPieces(Set<Piece> pieces) {
		return new ChessConfiguration(pieces);
	}

	@Override
	public ChessConfiguration move(Position from, Position to) {
		
		if(at(from) == null || ! to.isInBound() )
			throw new IllegalArgumentException();
		
		HashSet<Piece> newWhite = new HashSet<>(whites);
		newWhite.remove(at(from));
		if( at(to) != null )
			newWhite.remove(at(to));
		
		HashSet<Piece> newBlacks = new HashSet<>(blacks);
		newBlacks.remove(at(from));
		if( at(to) != null )
			newBlacks.remove(at(to));
		
		if(at(from).getColor() == ChessColor.WHITE)
			newWhite.add(at(from).move(to));
		else
			newBlacks.add(at(from).move(to));
		
		return new ChessConfiguration(newWhite,newBlacks,this.turn);
	}
	
	
	private void fillMatrix() {
		for(Piece p : whites ) {
			board[p.getPosition().getRow()][p.getPosition().getColumn()] = p;
			if(p.isKing())
				whiteKing = p;
		}
		for(Piece p : blacks ) {
			board[p.getPosition().getRow()][p.getPosition().getColumn()] = p;
			if(p.isKing())
				blackKing = p;
		}
	}

	@Override
	public ChessConfiguration changeTurn() {
		return new ChessConfiguration(new HashSet<>(whites), new HashSet<>(blacks), turn == ChessColor.WHITE ? ChessColor.BLACK : ChessColor.WHITE);
	}

	@Override
	public ChessColor getTurn() {
		return turn;
	}

	@Override
	public Position makePosition(int row, int col) {
		return new ChessPosition(row,col);
	}

	@Override
	public Set<Piece> whitePieces() {
		return whites;
	}

	@Override
	public Set<Piece> blackPieces() {
		return blacks;
	}

	@Override
	public Piece whiteKing() {
		return whiteKing;
	}

	@Override
	public Piece blackKing() {
		return blackKing;
	}
	
}