package model;

import java.util.List;
import java.util.Set;

import view.View;

/**
 * Interfaccia al modello che contiene lo stato attuale del programma
 * 
 * È una classe mutabile che contiene oggetti immutabili che codificano lo stato attuale
 * È formata dalla configuration più le scelte fatte dall'utente.
 * 
 */
public interface Model {
	
	/**
	 * vero se l'utente ha selezionato una delle sue pedine
	 * @return true se e solo se una pedina è attualmente selezionata
	 */
	public boolean isSelected();
	
	/**
	 * setta una posizione come attualmente selezionata
	 * @param pos la posizione da salvare
	 */
	public void setSelection(Position pos);
	
	/**
	 * annulla la selezione
	 */
	public void deSelect();
	
	/**
	 * ritorna la posizione precedentemente selezionata
	 * @return pos la posizione selezionata
	 */
	public Position getSelection();
	
	/**
	 * ritorna la configurazione dell'attuale situazione
	 * modifiche alla configurazione non influiscono il modello finchè
	 * non vengono espressamente assegnate ad esso
	 * @return conf la configurazione attuale
	 */
	public Configuration getConfiguration();
	
	/**
	 * setta una nuova configurazione come configurazione attuale
	 * @param configuration la nuova configurazione
	 */
	public void setConfiguration(Configuration configuration);
	
	/**
	 * setta una determinata view legata al modello.
	 * Se assegnata un cambiamento nel modello comporterà un cambiamento nella view
	 * @param view
	 */
	public void setView(View view);
	
	/**
	 * @return true se e solo se il gioco è stato settato come finito
	 */
	public boolean isGameOver();
	
	/**
	 * setta il gioco come finito oppure no
	 * @param gameOver true se e solo se il gioco è finito
	 */
	public void setGameOver(boolean gameOver);

	/**
	 * restiruisce una lista iterabile di tutte le posizioni della scacchiera
	 * sono ordinate dall'alto verso il basso, da sinistra verso destra.
	 * @return
	 */
	public List<Position> allBoardPosition();
	
	/**
	 * se un pezzo è selezionato ritorna l'insieme delle posizioni legali in cui
	 * muoversi.
	 * Se nulla è selezionato ritorna un insieme vuoto
	 * @return le possibili mosse, se presenti
	 */
	public Set<Position> getSelectionPossibleMove();
}
