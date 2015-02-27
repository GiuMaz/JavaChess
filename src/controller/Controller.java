package controller;

import model.Position;

/**
 * Il controller è quella parte di programma adibita alla gestione degli eventi di click.
 * Essa legge e interpreta le azione dell'utente ( per esempio definisce la differenza
 * tra cliccare per selezionare o cliccare per muovere) e verifica che i movimenti siano
 * coerenti con il regolamento degli scacchi.
 * Deve anche permettere di creare nuove partite
 * 
 */
public interface Controller {
	
	/**
	 * viene chiamata dalla view quando una casella viene cliccata. il controllore decide
	 * come interpretare l'azione
	 * @param pos la posizione della casella cliccata
	 */
	public void onClick(Position pos);

	/**
	 * gestisce una richiesta di nuovo gioco. deve posizionare le pedine come descritto nel
	 * regolamento degli scacchi e setta il turno al giocatore bianco
	 */
	public void onNewGame();
	
}
