/*
From there, your app.js (that was created back in lab 2) 
   should be able to take this meta information (containing 
   of events and generator data) to render events and 
   generators correctly.
*/

// PubSub is single object for publish data to multiple subscribers
class PubSub {
	constructor () {
		this.subscribers = [];
	}
	// subscribe allows a new subscriber to listen for changes by providing
	// callback function in the parameter
	subscribe (fn) {
		this.subscribers.push(fn);
	}
	// one can publish any data to subscribers
	publish (data) {
		this.subscribers.forEach(subscriber => {
			subscriber(data);
		});
	}
}

//-------------------------------------------------------------------------

const initialState = window.game.state;

const aBTN = document.querySelector('actionButton');
const pubSub = new PubSub();

function increaseCounter() {
	pubSub.publish({
		type: 'INCREASE_COUNTER',
		payload: window.incrementalGame.state
	});
}

window.incrementalGame = {
	state: {
		counter: 0
	}
};

pubSub.subscribe(action => {
	if (action.type !== 'INCREASE_COUNTER') {
		return;
	}
	window.incrementalGame.state.counter ++;
	console.log(window.incrementalGame.state.counter);
	document.getElementById("bits").innerHTML = "Bits: " + window.incrementalGame.state.counter;
	
});

aBTN.addEventListener('click', increaseCounter());
