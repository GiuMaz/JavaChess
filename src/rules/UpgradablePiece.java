package rules;

/**
 * interfaccia che identifica un pezzo che può essere promosso ad un altro pezzo.
 */
public interface UpgradablePiece {

	/**
	 * ritrona vero se il pezzo è in posizione di promozione
	 * @return true se e solo se il pezzo è in posizione di promozione
	 */
	public boolean isInUpgradePosition();
}
