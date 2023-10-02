package chapter2.agent_AB;

import java.util.Random;

import chapter2.agent_AB.Environment.LocationState;

public class AgentProgram {

	public Action execute(Percept p) {// location, status
		if (p.getLocationState() == LocationState.DIRTY) {
			return Environment.SUCK_DIRT;
		} else if (p.getAgentLocation() == Environment.LOCATION_A) {
			return Environment.MOVE_LEFT;
		} else if (p.getAgentLocation() == Environment.LOCATION_B) {
			return Environment.MOVE_RIGHT;
		}
		return NoOpAction.NO_OP;

	}

	public Action excute_2(Percept p) {
		if (p.getLocationState() == LocationState.DIRTY) {
			return Environment.SUCK_DIRT;

		} else if (p.getLocationState() == LocationState.CLEAN) {
			Action[] list = { Environment.MOVE_LEFT, Environment.MOVE_DOWN, Environment.MOVE_RIGHT,
					Environment.MOVE_UP };
			Random rd = new Random();
			int i = rd.nextInt(4);
			Action action = list[i];
			return action;

		}
		return NoOpAction.NO_OP;
	}
}