export default function (store) {

	return class CounterComponent extends window.HTMLElement {

		constructor () {

			super();
			this.store = store;

			this.onStateChange = this.handleStateChange.bind(this);
			
		}

		handleStateChange (newState) {
			//console.log('CounterComponent#stateChange', this, newState);
			this.innerHTML = "Bits: " + this.store.state.counter;
		}

		connectedCallback () {
			console.log('CounterComponent#onConnectedCallback');
			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback () {
			console.log('CounterComponent#onDisconnectedCallback');
			this.store.unsubscribe(this.onStateChange);
		}
	};
}

