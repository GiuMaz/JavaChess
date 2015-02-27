package rules;

import java.util.HashSet;
import java.util.Set;

import main.ChessColor;
import main.PieceRappresentation;
import model.Configuration;
import model.Position;

/**
 * implementa il pezzo del re.
 * può muoversi solo nelle 8 caselle adiacenti
 */
public class King extends AbstractPiece {

	public King(Position position, ChessColor color)
	{
		super(position,color);
	}
	
	@Override
	public PieceRappresentation getRapresentation() {
		return color == ChessColor.WHITE ? PieceRappresentation.KING_WHITE : PieceRappresentation.KING_BLACK;
	}

	@Override
	public Piece move(Position p) {
		return new King(p,color);
	}

	@Override
	public boolean isKing()
	{
		return true;
	}

	@Override
	public Set<Position> allPseudoLegalMove(Configuration conf) {
		Set<Position> moves = new HashSet<>();
		if( isInBoundAndNotFriend(conf,position.up()) )
			moves.add(position.up());
		if( isInBoundAndNotFriend(conf,position.down()) )
			moves.add(position.down());
		if( isInBoundAndNotFriend(conf,position.right()) )
			moves.add(position.right());
		if( isInBoundAndNotFriend(conf,position.left()) )
			moves.add(position.left());
		if( isInBoundAndNotFriend(conf,position.up().right()) )
			moves.add(position.up().right());
		if( isInBoundAndNotFriend(conf,position.up().left()) )
			moves.add(position.up().left());
		if( isInBoundAndNotFriend(conf,position.down().left()) )
			moves.add(position.down().left());
		if( isInBoundAndNotFriend(conf,position.down().right()) )
			moves.add(position.down().right());
		return moves;
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof King && ((King) other).getColor() == color 
				&& ((King) other).getPosition().equals(position); 
	}

}
