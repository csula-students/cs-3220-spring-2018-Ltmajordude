export default function (store) {
	return class StoryBookComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;
			// TODO: initial DOM rendering of story itself

			this.onStateChange = this.handleStateChange.bind(this);
		}

		handleStateChange (newState) {

			var html_TEXT = this.innerHTML;

			this.store.state.story.forEach(function(item) {
				if (item.state == "visible") {
					html_TEXT = item.description;
				}
			});

			this.innerHTML = html_TEXT;

		}

		connectedCallback () {
			console.log('StoryBookComponent#onConnectedCallback');
			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback () {
			console.log('StoryBookComponent#onDisconnectedCallback');
			this.store.unsubscribe(this.onStateChange);
		}
	};
}
