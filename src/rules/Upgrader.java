package rules;

import view.View;
import model.Configuration;

/**
 * Classe che cerca eventuali pedoni che possono fare un upgrade.
 * Se ne trova gestisce la situazione.
 *
 */
public class Upgrader {

	private Configuration conf;
	private View view;
	
	public Upgrader(Configuration conf, View view){
		this.conf = conf;
		this.view = view;
	}
	
	/**
	 * costruisce una nuova configurazione con i pezzi promossi,
	 * se non ci sono pezzi da promuovere ritorna la configurazione immutata
	 * @return
	 */
	public Configuration pieceUpgrade() {
		for( Piece p : conf.friendPieces() )
			if( p instanceof UpgradablePiece && ((UpgradablePiece) p).isInUpgradePosition())
				return upgrade(p);
		return conf;
	}

	private Configuration upgrade(Piece p) {
		int selection = view.onPieceUpgrade();
		switch (selection) {
		case 0:
			return conf.add(new Queen(p.getPosition(), p.getColor()));
		case 1:
			return conf.add(new Knight(p.getPosition(), p.getColor()));
		case 2:
			return conf.add(new Bishop(p.getPosition(), p.getColor()));
		case 3:
			return conf.add(new Rook(p.getPosition(), p.getColor()));
		default:
			return conf;
		}
		
	}
	
	
}
