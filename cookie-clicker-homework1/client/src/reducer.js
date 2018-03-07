import Generator from './models/generator'; 
import Story from './models/story'; 

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
					
				}
		    }
		});
		return state;

	case 'INCREMENT':
		state.counter = state.counter+action.payload;
		return state;

	case 'CHECK_STORY':
		
		state.story.forEach(function(item) {

			const storyItem = new Story(Object.assign({}, item));
			
			if(storyItem.isUnlockYet(state.counter)){
				storyItem.unlock();
				item.state = storyItem.state;
			}

		});

		return state;

	default:
		return state;
	}
}