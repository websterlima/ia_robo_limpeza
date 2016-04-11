// CArtAgO artifact code for project aula2

package aula2;

import java.util.Random;

import cartago.*;

public class Rooms extends Artifact {
	private boolean rooms[] = null;
	private int robotAt = 0;
	private Random rnd = new Random(System.currentTimeMillis());
	private int direction = 1;

	void init(int numberOfRooms) {
		rooms = new boolean[numberOfRooms];
		defineObsProperty("at", 0);

		for (int i = 0; i < numberOfRooms; i++) {
			rooms[i] = false;
		}

		execInternalOp("run");
	}

	@INTERNAL_OPERATION
	void run() {
		while (true) {
			if (rnd.nextDouble() <= 0.7) {
				rooms[rnd.nextInt(rooms.length)] = true;
			}
			
			await_time(4000);
		}
	}

	@OPERATION
	void goLeft() {
		if (robotAt - 1 >= 0) {
			robotAt--;
			getObsProperty("at").updateValue(robotAt);

			if (rooms[robotAt])
				signal("dirty");
		} else {
			signal("border");
		}
	}

	@OPERATION
	void goRight() {
		if (robotAt + 1 < rooms.length) {
			robotAt++;
			getObsProperty("at").updateValue(robotAt);

			if (rooms[robotAt])
				signal("dirty");
		} else {
			signal("border");
		}
	}

	@OPERATION
	void clean() {
		if (rooms[robotAt]) {
			rooms[robotAt] = false;
		} else {
			signal("noDirty");
		}
	}
	
	@OPERATION
	void changeDirection() {
		direction *= -1;
	}
}
