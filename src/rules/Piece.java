package rules;


import java.util.Set;

import main.ChessColor;
import main.PieceRappresentation;
import model.Configuration;
import model.Position;

/**
 * Interfaccia che rappresenta un pezzo generico.
 * Ogni pezzo conosce la propria posizione e colore;
 * conosce solo informazioni su se stesso, quindi per verificare la fattibilità
 * di una certa mossa deve essere informato sull'ambiente circostante.
 * Può vedere gli altri pezzi come amici ( uguale colore) o
 * nemici (colore opposto).
 * 
 * l'interfaccia permette di:
 * - creare un nuovo pezzo identico a lui ma spostao in una nuova posizione
 * - identificare se una specifica mossa è legale e pseudo legale
 * - ritornare tutte le sue mosse legali o pseudo legali
 * - ritornare un codice identificativo per rappresentare graficamente il pezzo
 *
 * Una mossa è detta:
 * - pseudo legale se il pezzo si può muovere nella nuova posizione soddisfando le sue
 *   regole, indipendentemente dalle condizioni del proprio re.
 * - legale se è pseudo legale e non lascia in scacco il proprio re
 *	
 */
public interface Piece {
	
	/**
	 * riconosce se un'altra pedina è mia nemica
	 * @param other altra pedina
	 * @return true se e solo se l'altra pedina è mia nemica
	 */
	public boolean isMyFriend(Piece other);
	
	/**
	 * riconosce se un'altra pedina è mia amica
	 * @param other altra pedina
	 * @return true se e solo se l'altra pedina è mia amica
	 */
	public boolean isMyEnemy(Piece other);
	
	/**
	 * ritorna true se è il re
	 * @return true se e solo se questa pedina è un re
	 */
	public boolean isKing();
	
	/**
	 * ritorna il codice identifica necessario per rappresentarla graficamente
	 * @return
	 */
	public PieceRappresentation getRapresentation();
	
	/**
	 * ritorna il colore della pedina
	 * @return color bianco o nero
	 */
	public ChessColor getColor();
	
	/** 
	 * ritorna la posizione della pedina
	 * @return position posizione della pedina
	 */
	public Position getPosition();
	
	/**
	 * ritorna un nuovo pezzo mosso in un altra posizione
	 * @param p nuova posizione
	 * @return nuovo pezzo
	 */
	public Piece move(Position p);
	
	/**
	 * ritorna vero se, data una certa configurazione, lo spostarsi nella
	 * nuova posizione è una mossa legale
	 * @param conf 
	 * @param pos nuova posizione
	 * @return vero se e solo se la mossa è legale
	 */
	public boolean isLegalMove(Configuration conf, Position pos);
	
	/**
	 * ritorna vero se, data una certa configurazione, lo spostarsi nella
	 * nuova posizione è una mossa pseudo legale
	 * @param conf 
	 * @param pos nuova posizione
	 * @return vero se e solo se la mossa è pseudo legale
	 */
	public boolean isPseudoLegalMove(Configuration conf, Position pos);
	
	/**
	 * ritorna, data una certa configurazione, tutte le mosse pseudo legali 
	 * @param conf
	 * @return le mosse pseudolegali
	 */
	public Set<Position> allPseudoLegalMove(Configuration conf);
	
	/**
	 * ritorna, data una certa configurazione, tutte le mosse legali 
	 * @param conf
	 * @return le mosse legali
	 */
	public Set<Position> allLegalMove(Configuration conf);
}