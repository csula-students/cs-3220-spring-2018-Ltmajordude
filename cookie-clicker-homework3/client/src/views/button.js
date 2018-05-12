export default function (store) {

	return class ButtonComponent extends window.HTMLElement {

		constructor () {

			super();
			this.store = store;

            //this.textContent = this.store.state.example;

			this.onStateChange = this.handleStateChange.bind(this);

			this.addEventListener('click', () => {
                this.store.dispatch({
                	type: 'INCREMENT',
                	payload: 1
                });
            });

		}

		handleStateChange (newState) {

			//console.log('ButtonComponent#stateChange', this, newState);

        }

        connectedCallback () {
            console.log('ButtonComponent#onConnectedCallback');
            this.store.subscribe(this.onStateChange);
        }

        disconnectedCallback () {
            console.log('ButtonComponent#onDisconnectedCallback');
            this.store.unsubscribe(this.onStateChange);
        }

	};
}
