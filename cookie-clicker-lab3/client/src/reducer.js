import Generator from './models/generator'; 

export default function reducer (state, action) {
	switch (action.type) {

	case 'EXAMPLE_MUTATION':

		state.example = action.payload;
		return state;
	
	case 'BUY_GENERATOR':

		state.generators.forEach(function(item) {
		    //going through every generator!
		    if(action.payload.name == item.name){
		    	//Got the right generator!
		    	if(state.counter >= item.baseCost) {
		    		
		    		const generator = new Generator(Object.assign({}, item));

		    		state.counter = state.counter - item.baseCost;
					item.quantity = item.quantity + action.payload.quantity;
					
					item.baseCost = generator.getCost();
					console.log("Generator now costs " + item.baseCost + " bits.")
				}
				else {
					console.log("You can't afford this generator! It costs " + item.baseCost + " bits!");
				}
		    }
		});
		return state;

	case 'COUNTER_UP':
		//my own implementation for increasing the counter
		state.counter = state.counter+action.payload.quantity;
		return state;

	default:
		return state;
	}
}