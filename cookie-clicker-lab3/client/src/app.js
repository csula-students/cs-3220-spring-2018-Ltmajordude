import '@webcomponents/webcomponentsjs';

import '../../dest/app.bundle.js';

import {loop} from './game';
import Store from './store';
import reducer from './reducer';

import ButtonComponent from './views/button';
import CounterComponent from './views/counter';
import ExampleComponent from './views/example';
import GeneratorComponent from './views/generator';
import StoryBookComponent from './views/story-book';

/**
 * Data flow diagram
 +----------------------------------------------------+
 | +------------------+          +------------------+ |
 | |                  |          |                  | |
++-|       Loop       |<---------|    Generator     | |
|| |                  |          |                  | |
|| +------------------+          +------------------+ |
||G          ^                                        |
||a          +-----------------------------+          |
||m                                        |          |
||e                                        |          |
||                               +------------------+ |
||                               |                  | |
||                               |     Stories      | |
||                               |                  | |
||                               +------------------+ |
|+----------------------------------------------------+
+------------------------------------------------------------+
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|       +----------------------------------------------------+----------+
|       | +------------------+                     +------------------+ |
|       | |                  |        Mutates      |                  | |
|       | |     Reducer      |-------------------->|      State       | |
|       | |                  |                     |                  | |
|       | +------------------+                     +------------------+ |
|       |S          ^                                        |          |
|       |t          |                                        |          |
|       |o          |                                        |          |
|       |r          | Triggers                     Notifies  |          |
|       |e          |                                        |          |
|       |           |                                        v          |
|       | +------------------+                     +------------------+ |
|       | |                  |                     |                  | |
+-------+>|      Action      |                     |    Listeners     | |
        | |                  |                     |                  | |
        | +------------------+                     +------------------+ |
        +-----------^----------------------------------------+----------+
                    |                                        |
                    |                                        |
                    |                                        |
                    |                                        |
                    | Dispatches                             |
                    |                                        |
                    |                                        |
          +------------------+                               |
          |                  |                               |
          |      Views       |              Notifies changes |
          |    Components    |<------------------------------+
          |                  |
          +------------------+
 */

main();

// main function wraps everything at top level
function main () {

	// TODO: fill the blank based on the theme you have choosen
	const initialState = {
		example: 'Pony Hotel',
		counter: 0,
		generators: [
			{
	            name: 'Ponyville',
	            description: "Home of Princess Twilight Sparkle of Friendship, the Ambassadors of Harmony, Twilight Sparkle's apprentice Starlight Glimmer and Spike the Dragon, hero of the Crystal Empire.",
	            rate: 5,
	            baseCost: 10,
	            quantity: 0,
	            unlockValue: 10
			}
			{
	            name: 'Canterlot',
	            description: "Home of Princess Celestia of the Sun and Princess Luna of the Moon.",
	            rate: 10,
	            baseCost: 100,
	            quantity: 0,
	            unlockValue: 25
			}
			{
	            name: 'Crystal Empire',
	            description: "Home of Princess Cadance of Love and Captain Shining Armor, commander of the Royal Guard.",
	            rate: 20,
	            baseCost: 500,
	            quantity: 0,
	            unlockValue: 50
			}
		],
		story: []
	};

	// initialize store
	const store = new Store(reducer, initialState);
	console.log(ExampleComponent(store));

	//console.log("test");

	// define web components
	window.customElements.define('component-example', ExampleComponent(store));
	// no longer used
	window.customElements.define('game-button', ButtonComponent(store));
	window.customElements.define('game-counter', CounterComponent(store));
	// lab 3
	window.customElements.define('game-generator', GeneratorComponent(store));
	// homework 1
	window.customElements.define('game-story-book', StoryBookComponent(store));

	// For ease of debugging purpose, we will expose the critical store under window
	// ps: window is global
	window.store = store;

	// start game loop
	loop(store);
}
