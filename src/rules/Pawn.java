package rules;

import java.util.HashSet;
import java.util.Set;

import main.ChessColor;
import main.PieceRappresentation;
import model.Configuration;
import model.Position;

/**
 * implementa il pezzo del pedone.
 * Il pedone ha movimenti differenti in base al colore.
 * Il nero può solo scendere mentre il bianco può solo salire.
 * entrambi avanzano solo se la posizione in fronte è vuota, mentre
 * possono mangiare solo nelle posizioni adiacenti frontali sulle diagonali.
 * 
 * È l'unico pezzo che può essere promosso, se arriva nel lato opposto della scacchiera
 * 
 */
public final class Pawn extends AbstractPiece implements UpgradablePiece {

	public Pawn(Position position, ChessColor color)
	{
		super(position,color);
	}
	
	@Override
	public PieceRappresentation getRapresentation() {
		return color == ChessColor.WHITE ? PieceRappresentation.PAWN_WHITE : PieceRappresentation.PAWN_BLACK;
	}
	
	@Override
	public Piece move(Position p) {
		return new Pawn(p, this.color);
	}

	@Override
	public Set<Position> allPseudoLegalMove(Configuration conf) {
		Set<Position> moves = new HashSet<>();
		Position index = goAhead(position);
		if( isMyEnemy( conf.at(index.left())) )
			moves.add(index.left());
		if( isMyEnemy(conf.at(index.right())))
			moves.add(index.right());
		if( index.isInBound() && conf.isEmpty(index) ) {
			moves.add(index);
			index = goAhead(index);
			if( isInStartRow() && index.isInBound() && conf.isEmpty(index) )
				moves.add(index);
		}
		return moves;
	}
	
	private boolean isInStartRow() {
		if (color == ChessColor.WHITE)
			return position.getRow() == 6;
		else
			return position.getRow() == 1;
	}

	private Position goAhead(Position old) {
		if (color == ChessColor.WHITE)
			return old.up();
		else
			return old.down();
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof Pawn && ((Pawn) other).getColor() == color 
				&& ((Pawn) other).getPosition().equals(position); 
	}

	@Override
	public boolean isInUpgradePosition() {
		if( color == ChessColor.WHITE )
			return position.getRow() == 0;
		else
			return position.getRow() == 7;
	}
}
