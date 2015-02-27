package rules;

import java.util.HashSet;
import java.util.Set;

import main.ChessColor;
import main.PieceRappresentation;
import model.Configuration;
import model.Position;

/**
 * implementa il pezzo del cavallo.
 * È l'unico pezzo che salta altri pezzi.
 * Il cavallo può muoversi ad L, due lungo una direzione e una lungo l'ortogonale
 * 
 */
public class Knight extends AbstractPiece {

	public Knight(Position position, ChessColor color)
	{
		super(position,color);
	}
	
	@Override
	public PieceRappresentation getRapresentation() {
		return color == ChessColor.WHITE ? PieceRappresentation.KNIGTH_WHITE : PieceRappresentation.KNIGTH_BLACK;
	}

	@Override
	public Piece move(Position p) {
		return new Knight(p,color);
	}

	@Override
	public Set<Position> allPseudoLegalMove(Configuration conf) {
		Set<Position> moves = new HashSet<>();
		if( isInBoundAndNotFriend(conf,position.up().up().left()) )
			moves.add(position.up().up().left());
		if( isInBoundAndNotFriend(conf,position.up().up().right()) )
			moves.add(position.up().up().right());
		if( isInBoundAndNotFriend(conf,position.right().right().up()) )
			moves.add(position.right().right().up());
		if( isInBoundAndNotFriend(conf,position.right().right().down()) )
			moves.add(position.right().right().down());
		if( isInBoundAndNotFriend(conf,position.down().down().right()) )
			moves.add(position.down().down().right());
		if( isInBoundAndNotFriend(conf,position.down().down().left()) )
			moves.add(position.down().down().left());
		if( isInBoundAndNotFriend(conf,position.left().left().down()) )
			moves.add(position.left().left().down());
		if( isInBoundAndNotFriend(conf,position.left().left().up()) )
			moves.add(position.left().left().up());
		return moves;
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof Knight && ((Knight) other).getColor() == color 
				&& ((Knight) other).getPosition().equals(position); 
	}
	
	



}
