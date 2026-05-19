package iut.gon.othello.model.factory;

import iut.gon.othello.model.state.IState;

public interface IFactory {

		public IState testState();
		public IState stateForBlackLinesTest();
		public IState stateForWhiteLinesTest();
		public IState emptyState();
		public IState doubleLineStateTest();
		
}
