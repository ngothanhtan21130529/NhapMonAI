package chapter2.agent_AB;

import chapter2.agent_AB.Environment.LocationState;

public class AgentProgram {

	public Action execute(Percept p) {// location, status
		if(p.getLocationState()==LocationState.DIRTY) {
			return Environment.SUCK_DIRT;
		}else if(p.getAgentLocation()==Environment.LOCATION_A) {
			return Environment.MOVE_LEFT;
		}else if(p.getAgentLocation()==Environment.LOCATION_B) {
			return Environment.MOVE_RIGHT;
		}
		return NoOpAction.NO_OP;
		
	}
}