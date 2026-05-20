package iut.gon.othello.model.factory;

import iut.gon.othello.model.state.IState;

public interface IFactory {

	/**
	 * Crée un état de test standard avec des pions et anneaux.
	 * @return IState état de test
	 */
	public IState testState();

	/**
	 * Crée un état configuré pour tester les lignes noires.
	 * @return IState état pour test de lignes noires
	 */
	public IState stateForBlackLinesTest();

	/**
	 * Crée un état configuré pour tester les lignes blanches.
	 * @return IState état pour test de lignes blanches
	 */
	public IState stateForWhiteLinesTest();

	/**
	 * Crée un état vide (plateau sans aucun token).
	 * @return IState plateau vide
	 */
	public IState emptyState();

	/**
	 * Crée un état avec deux lignes de pions pour les tests.
	 * @return IState état avec lignes doubles
	 */
	public IState doubleLineStateTest();
		
}
