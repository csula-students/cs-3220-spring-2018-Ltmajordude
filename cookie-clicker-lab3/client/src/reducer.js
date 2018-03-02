export default function reducer (state, action) {
	switch (action.type) {
	case 'BUY_GENERATOR':
		//state.example = action.payload;
		if(state.hotelCount==null) {state.hotelCount = 0;}
		state.hotelCount = state.hotelCount + 1;
		return state;
	/*
	case 'COUNTER_UP':
		if(state.counter==null) {state.counter = 0;}
		state.counter = state.counter + 1;
		return state;
	*/
	default:
		return state;
	}
}

