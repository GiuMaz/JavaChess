package rules;

import java.util.ArrayList;
import java.util.HashSet;

import main.ChessColor;
import model.Configuration;
import model.Position;

/**
 * Rules è una classe usata solo dal controllore per verificare che le azioni
 * siano consistenti con il regolamento delgi scacchi.
 * 
 */
public class Rules {
	
	private Configuration configuration;
	
	public Rules(Configuration configuration)
	{
		this.configuration = configuration;
	}
	
	/**
	 * costruisce una configurazione di inizio paritita come da regolamento
	 * @return configuazione di inizio
	 */
	public Configuration newGame()
	{
		HashSet<Piece> pieces = new HashSet<>(32);
		
		pieces.add(new Rook( configuration.makePosition(0, 0) , ChessColor.BLACK ));
		pieces.add(new Knight( configuration.makePosition(0, 1) , ChessColor.BLACK ));
		pieces.add(new Bishop( configuration.makePosition(0, 2) , ChessColor.BLACK ));
		pieces.add(new Queen( configuration.makePosition(0, 3) , ChessColor.BLACK ));
		pieces.add(new King( configuration.makePosition(0, 4) , ChessColor.BLACK ));
		pieces.add(new Bishop( configuration.makePosition(0, 5) , ChessColor.BLACK ));
		pieces.add(new Knight( configuration.makePosition(0, 6) , ChessColor.BLACK ));
		pieces.add(new Rook( configuration.makePosition(0, 7) , ChessColor.BLACK ));
		for(int i = 0; i < 8; i++)
			pieces.add(new Pawn( configuration.makePosition(1, i) , ChessColor.BLACK ));
		
		pieces.add(new Rook( configuration.makePosition(7, 0) , ChessColor.WHITE ));
		pieces.add(new Knight( configuration.makePosition(7, 1) , ChessColor.WHITE ));
		pieces.add(new Bishop( configuration.makePosition(7, 2) , ChessColor.WHITE ));
		pieces.add(new Queen( configuration.makePosition(7, 3) , ChessColor.WHITE ));
		pieces.add(new King( configuration.makePosition(7, 4) , ChessColor.WHITE ));
		pieces.add(new Bishop( configuration.makePosition(7, 5) , ChessColor.WHITE ));
		pieces.add(new Knight( configuration.makePosition(7, 6) , ChessColor.WHITE ));
		pieces.add(new Rook( configuration.makePosition(7, 7) , ChessColor.WHITE ));
		for(int i = 0; i < 8; i++)
			pieces.add(new Pawn( configuration.makePosition(6, i) , ChessColor.WHITE ));
		
		
		return configuration.buildFromPieces(pieces);
	}

	/**
	 * verifica se il re amico ( secondo il turno corrente) è in scacco
	 * @return true se e solo se il re amico è in scacco
	 */
	public boolean isFriendKingInCheck() {
		for(Piece piece : configuration.enemyPieces() )
			if(piece.isPseudoLegalMove(configuration, configuration.friendKing().getPosition()))
				return true;
		return false;
	}
	
	/**
	 * verifica se il re nemico ( secondo il turno corrente) è in scacco
	 * @return true se e solo se il re nemico è in scacco
	 */
	public boolean isEnemyKingInCheck() {
		for(Piece piece : configuration.friendPieces() )
			if(piece.isPseudoLegalMove(configuration, configuration.enemyKing().getPosition()))
				return true;
		return false;
	}
	
	/**
	 * decreta se una mossa è legale
	 * @param from posizione di partenza
	 * @param to posizione di arrivo
	 * @return true se e solo se la mossa è legale
	 */
	public boolean isLegalMove(Position from, Position to) {
		return configuration.at(from).isLegalMove(configuration, to);
	}

	/**
	 * verifica se il re nemico ( secondo il turno corrente) è in scacco matto
	 * il re nemico è in scacco matto se:
	 * - è in scacco
	 * - non può salvarsi muovendosi
	 * - non si possono bloccare gli attaccanti
	 * 
	 * Se gli attaccanti sono più di uno non possono essere bloccati.
	 * Se l'attaccante è uno solo può essere bloccato se mangiato da
	 * uno dei pezzi del re sotto scacco con una mossa legale.
	 * Se l'attaccante è a raggio si può anche provare a bloccare il raggio di attacco
	 * sempre con una mossa legale.
	 * 
	 * @return true se e solo se il re nemico è in scacco matto
	 */
	public boolean isCheckmate()
	{
		// se il re non è sotto scacco
		if( ! isEnemyKingInCheck() )
			return false;
		// se il re può salvarsi muovendosi
		if( ! configuration.enemyKing().allLegalMove(configuration).isEmpty() )
		
			return false;
		// trovo gli attaccanti
		ArrayList<Piece> attackers = new ArrayList<>();
		
		for( Piece p : configuration.friendPieces() )
			if( p.isPseudoLegalMove(configuration, configuration.enemyKing().getPosition()))
				attackers.add(p);
		
		// se sono più di due l'unica speranza era muovere il re
		if(attackers.size() >= 2)
			return true;
		else
		{
			// se posso mangiare l'attaccante e togliere lo scacco
			for(Piece p : configuration.enemyPieces() )
				if(isLegalMove(p.getPosition(), attackers.get(0).getPosition() ) )
				
					return false;	
			if(attackers.get(0) instanceof AbstractRayAttacckerPiece )
				for( Position rayPos : ((AbstractRayAttacckerPiece)attackers.get(0)).getRayAttack(configuration, configuration.enemyKing().getPosition()) )
					for(Piece defender : configuration.enemyPieces() )
						if(isLegalMove(defender.getPosition(), rayPos ) )
							return false;
			return true;
		}
	}
}