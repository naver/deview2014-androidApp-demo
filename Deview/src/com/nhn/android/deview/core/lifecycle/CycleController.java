package com.nhn.android.deview.core.lifecycle;

import java.util.Vector;

public class CycleController implements CycleControllable {
	Vector<CycleControllable> mList = new Vector<CycleControllable>();
	class State {
		final static int NONE = 0;
		final static int STARTED = 0;
		final static int RESUMED = 1;
		final static int PAUSED = 2;
		final static int STOPPED = 4;
		
		public int state;
		public CycleControllable control;
	}
	
	public void register(CycleControllable object,int callState) {		
		if (mList.add(object) == true) {
			if (state >= State.STARTED) {
				object.onStart();
			}
			if (state >= State.RESUMED) {
				object.onResume();
			}
			if (state >= State.PAUSED) {
				object.onPause();
			}
			if (state >= State.STOPPED) {
				object.onStop();
			}
		}
	}

	int state = State.NONE;
			
	public void unregister(CycleControllable object,boolean invokeDestroy) {
		if (mList.remove(object) == true) {
			if (state >= State.STARTED) {
				onStart();
			}
			if (state >= State.RESUMED) {
				onResume();
			}
			
			if (invokeDestroy == true) {
				onDestroy();
			}
		}
	}
	
	public void finish() {
		for(CycleControllable c:mList) {
			c.onDestroy();
		}
		mList.removeAllElements();
	}

	@Override
	public void onStart() {
		state = State.STARTED;
		for(CycleControllable c:mList) {
			c.onStart();
		}
	}

	@Override
	public void onResume() {
		state = State.RESUMED;
		for(CycleControllable c:mList) {
			c.onResume();
		}		
	}

	@Override
	public void onPause() {	
		state = State.PAUSED;
		for(CycleControllable c:mList) {
			c.onPause();
		}
	}

	@Override
	public void onStop() {		
		state = State.STOPPED;
		for(CycleControllable c:mList) {
			c.onStop();
		}
	}

	@Override
	public void onDestroy() {
		state = State.STOPPED;
		for(CycleControllable c:mList) {
			c.onDestroy();
		}
	}

	@Override
	public void onActivityResult() {
	}
}
