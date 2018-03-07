import Generator from '../models/generator'; 
export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {

		constructor () {

			super();
			this.store = store;

            this.onStateChange = this.handleStateChange.bind(this);
  
            this.addEventListener('click', () => {

                this.store.dispatch({
                    type: 'BUY_GENERATOR',
                    payload: {
                        name: this.store.state.generators[this.dataset.id].name,
                        quantity: 1
                    },
                });

                document.getElementById("price-" + this.dataset.id).innerHTML = this.store.state.generators[this.dataset.id].baseCost;
                document.getElementById("quantity-" + this.dataset.id).innerHTML = this.store.state.generators[this.dataset.id].quantity;

            });


		}

		handleStateChange (newState) {

            //console.log('GeneratorComponent#stateChange', this, newState);

        }

        connectedCallback () {
            console.log('GeneratorComponent#onConnectedCallback');
            this.store.subscribe(this.onStateChange);
        }

        disconnectedCallback () {
            console.log('GeneratorComponent#onDisconnectedCallback');
            this.store.unsubscribe(this.onStateChange);
        }

	};
}
