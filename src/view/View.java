package view;

import model.Model;
import controller.Controller;

/**
 * Classe che gestisce la rappresentazione grafica del gioco
 * 
 * I suoi metodi la aggiornano quando cambia la configurazione della scacchiera o
 * cambia il pezzo selezionato dal giocatore.
 * Deve anche rappresentare gli eventi di nuovo gioco e upgrade di
 * un pezzo
 * 
 */
public interface View {

	/**
	 * ritorna il modello associato alla view
	 * @return model il modello associato
	 */
	public Model getModel();
	
	/**
	 * Aggiorna la view in base alla configurazione corrente nel model
	 */
	public void onConfigurationChange();
	
	/**
	 * aggiorna la view in base alla selezione fatta dal giocatore
	 */
	public void onSelectionChange();
	
	/**
	 * setta un cotroller
	 * @param controller nuvo controllore
	 */
	public void setController(Controller controller);

	/**
	 * rappresenta una schermata di game over e propone una nuova partita
	 */
	public void onGameOver();
	
	/**
	 * se il giocatore vuole iniziare un nuovo gioco apre un popup di conferma
	 */
	public void onNewGameRequest();
	
	/**
	 * se un pedone può evolversi presenta una schermata in cui
	 * è possibile selezionare il pezzo in cui evolversi
	 * @return
	 */
	public int onPieceUpgrade();
}
