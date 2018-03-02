export default function reducer (state, action) {
	switch (action.type) {

	case 'EXAMPLE_MUTATION':
		state.example = 'mutated';
		return state;

	case 'BUY_GENERATOR':
		
		//payload

		
		if(state.counter >= state.generators.baseCost) {
			if(state.generators.quantity==null) {state.generators.quantity = 0;}
			state.counter = state.counter - state.generators.baseCost;
			state.generators.quantity++;
			state.generators.baseCost = getCost();
		}
		

		return state;

	default:
		return state;
	}
}