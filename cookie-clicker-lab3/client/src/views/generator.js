export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {

		constructor () {

			super();

			//console.log("test");

			this.store = store;

			// TODO: render generator initial view

			// TODO: subscribe to store on change event

			// TODO: add click event

            this.onStateChange = this.handleStateChange.bind(this);

            this.addEventListener('click', () => {
                this.store.dispatch({
                    type: 'BUY_GENERATOR',
                });

            });

		}

		handleStateChange (newState) {

            //console.log("id = " + this.dataset.id + " : You bought a hotel! You own " + this.store.state.hotelCount + " hotel(s).")
            //console.log('HotelComponent#stateChange', this, this.store.state);

            console.log("You bought a hotel!");
            console.log("");

        }

        connectedCallback () {
            //console.log("test");
            console.log('GeneratorComponent#onConnectedCallback');
            this.store.subscribe(this.onStateChange);
        }

        disconnectedCallback () {
            //console.log("test");
            console.log('GeneratorComponent#onDisconnectedCallback');
            this.store.unsubscribe(this.onStateChange);
        }

	};
}
