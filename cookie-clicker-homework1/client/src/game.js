import Generator from './models/generator';  
// default interval as 1 second
const interval = 1000;

/**
 * loop is main loop of the game, which will be executed once every second (
 * based on the interval variable configuration)
 */

//var test = 0;

export function loop (store) {

	var bits = 0;

	store.state.generators.forEach(function(item) {
		const generator = new Generator(Object.assign({}, item));
		bits += generator.generate();
	});

	document.getElementById("auto").innerHTML = "Bits earned automatically per second: " + bits;

	store.dispatch({
		type: 'INCREMENT',
		payload: bits
	});

	//============================================================================================================

	// TODO: triggers stories from story to display state if they are passed
	//       the `triggeredAt` points
	// hint: use store.dispatch to send event for changing events state
	
	store.dispatch({
		type: 'CHECK_STORY'
	});
	

	//============================================================================================================

	// recursively calls loop method every second
	setTimeout(loop.bind(this, store), interval);
	//test++;
	//console.log("time_interval: " + test);
}

//=============================================================================
//=============================================================================
//=============================================================================

// from lab-3:
/*
export function loop (store) {
	// TODO: increment counter based on the generators in the state
	// hint: read how many "generators" in store and iterate through them to
	//       count how many value to increment to "resource"


	// TODO: triggers stories from story to display state if they are passed
	//       the `triggeredAt` points
	setTimeout(loop.bind(this, store), interval);
}

export function increment (state, modifier = 1) {
	return state.counter + 1 * modifier;
}
*/