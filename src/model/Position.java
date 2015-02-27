package model;

/**
 * 
 * Classe che rappresenta una posizione nella scacchiera.
 * È possibile costruire posizioni fuori dalla scacchiera,
 * in quel caso il metodo isInBound deve restiuire false.
 * 
 * la posizione (0,0) è in alto a sinistra, quella (7,7) in basso a destra
 * i metodi up,down,left e right restituiscono nuove posizioni cooerenti con
 * questo sistema di coordinate, anche se non necessariamente nei limiti 
 * della scacchiera.
 * 
 * La classe è immutabile, i metodi di movimento restituiscono nuovi oggetti.
 * 
 * @author Bonfante Pietro e Mazzi Giulio
 *
 */
public interface Position {
	
	/**
	 * restituisce il valore della riga
	 * @return row numero della riga
	 */
	public int getRow();
	
	/**
	 * restituisce il valore della colonna
	 * @return column numero della colonna
	 */
	public int getColumn();
	
	/**
	 * restiuice una nuova posizione spostata in alto di una casella
	 * il valore della riga è decrementato di uno, quello della colonna è invariato
	 * @return up nuova posizione sopra di me
	 */
	public Position up();
	
	/**
	 * restiuice una nuova posizione spostata in basso di uno
	 * il valore della riga è aumentato di uno, quello della colonna è invariato
	 * @return down nuova posizione sotto di me
	 */
	public Position down();
	
	/**
	 * restiuice una nuova posizione spostata a sinistra di uno
	 * il valore della colonna è decrementato di uno, quello della riga è invariato
	 * @return left nuova posizione alla mia sinistra
	 */
	public Position left();
	
	/**
	 * restiuice una nuova posizione spostata a destra di uno
	 * il valore della colonna è aumentato di uno, quello della riga è invariato
	 * @return right nuova posizione alla mia destra
	 */
	public Position right();
	
	/**
	 * Verifica che una posizione stia all'interno della scacchiera, ovvero 
	 * sia la sua riga che la sua colonna abbiano valori compresi tra zero 
	 * e sette inclusi 
	 * @return true se e solo se la posizione è interna alla scacchiera
	 */
	public boolean isInBound();
}