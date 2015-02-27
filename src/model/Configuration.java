package model;

import java.util.Set;

import main.ChessColor;

import rules.Piece;


/**
 * Classe immutabile che implementa una configurazione di pezzi sulla scacchiera
 * Permette di vedere la scacchiera sia come insieme di pezzi sia come 
 * una griglia di caselle 8x8.
 * 
 * Mantiene anche le informazioni sul turno di gioco e permette un identificazione
 * relativa di pezzi amici e  nemici.
 * Permette anche un identificazione assoluta di pezzi bianchi e neri.
 * 
 * 
 * permette operazioni che ritornano nuove configurazioni con:
 * - il turno cambiato
 * - un pezzo mosso, indipendentemente dalle regole. (Se si muove su
 *   di un altro pezzo esso verrà mangiato)
 * - un nuovo pezzo aggiunto ( Se aggiunto su di un altro pezzo esso 
 *   verrà mangiato)
 *   
 * permette anche di costruire nuove configurazioni a partire da una lista
 * di pezzi, e permette alle altre classi di costruire Position consistenti
 * con il suo modo di rappresentare le cose
 * 
 */
public interface Configuration {
	
	/**
	 * Ritorna il pezzo nella posizione indicata
	 * @param p posizione
	 * @return pezzo nella posizione
	 */
	public Piece at(Position p);
	
	/**
	 *  chiede se la posizione indicata non contiene pezzi.
	 *  Se interrogato su una posizione fuori dalla scacchiera
	 *  ritorna sempre false
	 * @param p posizione
	 * @return true se e solo se la posizione indicata è vuota
	 */
	public boolean isEmpty(Position p);
	
	/**
	 *  chiede se la posizione indicata contiene un pezzo nemico
	 *  (rispetto al turno corrente).
	 *  Se interrogato su una posizione fuori dalla scacchiera
	 *  ritorna sempre false
	 * @param p posizione
	 * @return true se e solo se la posizione indicata contiene un nemico
	 */
	public boolean isEnemy(Position p);
	
	/**
	 *  chiede se la posizione indicata contiene un pezzo amico
	 *  (rispetto al turno corrente).
	 *  Se interrogato su una posizione fuori dalla scacchiera
	 *  ritorna sempre false
	 * @param p posizione
	 * @return true se e solo se la posizione indicata contiene un amico
	 */
	public boolean isFriend(Position p);
	
	/**
	 * ritorna l'insieme dei pezzi amici  (rispetto al turno corrente).
	 * @return friend
	 */
	public Set<Piece> friendPieces();
	
	/**
	 * ritorna l'insieme dei pezzi nemici  (rispetto al turno corrente).
	 * @return enemy
	 */
	public Set<Piece> enemyPieces();
	
	/**
	 * ritorna l'insieme dei pezzi bianchi
	 * @return whites
	 */
	public Set<Piece> whitePieces();
	
	/**
	 * ritorna l'insieme dei pezzi neri
	 * @return blacks
	 */
	public Set<Piece> blackPieces();
	
	/**
	 * ritorna il re nemico, se assente ritorna null
	 * @return posizione del re nemico
	 */
	public Piece enemyKing();
	
	/**
	 * ritorna il re amico, se assente ritorna null
	 * @return posizione del re amico
	 */
	public Piece friendKing();
	
	/**
	 * ritorna il re bianco, se assente ritorna null
	 * @return posizione del re bianco
	 */
	public Piece whiteKing();
	
	/**
	 * ritorna il re nero, se assente ritorna null
	 * @return posizione del re nero
	 */
	public Piece blackKing();
	
	/**
	 * ritorna una nuova configurazione con un pezzo spostato dalla
	 * posizione di partenza a quella di arrivo indipendentemente 
	 * dalle regole. Se si muove su di un altro pezzo esso viene rimosso
	 * @param from posizione di partenza
	 * @param to posizione di arrivo
	 * @return nuova configurazione
	 */
	public Configuration move(Position from , Position to);
	
	/**
	 * ritorna una nuova configurazione con un pezzo aggiunto nella
	 * posizione specificata indipendentemente dalle regole. 
	 * Se viene aggiunto su di un altro pezzo esso viene rimosso.
	 * @param Piece il pezzo da aggiungere
	 * @return nuova configurazione
	 */
	public Configuration add(Piece piece);
	
	/**
	 * crea una nuova configurazuine che contiene i pezzi specificati
	 * nell'insieme, e setta il turno al bianco
	 * @param pieces i pezzi della nuova scacchiera
	 * @return
	 */
	public Configuration buildFromPieces( Set<Piece> pieces );

	/**
	 * ritorna una nuova configurazione con il turno cambiato
	 * @return nuova configurazione
	 */
	public Configuration changeTurn();
	
	/**
	 * ritorna il turno del giocatore corrente, bianco o nero
	 * @return colore del giocatore
	 */
	public ChessColor getTurn();
	
	/**
	 * crea una nuova Position con le coordinate specificate.
	 * Serve a classi fuori dal Model per costruire posizioni in
	 * maniera consistente con la Configuration
	 * @param row riga
	 * @param column colonna
	 * @return nuova posizione
	 */
	public Position makePosition(int row, int column);
}