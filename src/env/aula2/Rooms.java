// CArtAgO artifact code for project aula2

package aula2;

import java.util.Random;

import cartago.*;

public class Rooms extends Artifact {
	private boolean rooms[][] = null;
	private boolean roomsOpen[][] = null;
	private int robotAt = 0;
	private Random rnd = new Random(System.currentTimeMillis());

	void init(int roomsX, int roomsY) {
		rooms = new boolean[roomsX][roomsY];
		roomsOpen = new boolean[roomsX][roomsY];
		defineObsProperty("at", 0);
		defineObsProperty("direction", "right");

		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < rooms[i].length; j++) {
				rooms[i][j] = false;
				roomsOpen[i][j] = true;
			}
		}

		execInternalOp("run");
	}

	@INTERNAL_OPERATION
	void run() {
		while (true) {
			if (rnd.nextDouble() <= 0.7) {
				rooms[rnd.nextInt(rooms.length)][rnd.nextInt(rooms[0].length)] = true;
				roomsOpen[rnd.nextInt(rooms.length)][rnd.nextInt(rooms[0].length)] = false;
				roomsOpen[rnd.nextInt(rooms.length)][rnd.nextInt(rooms[0].length)] = true;
			}
			
			await_time(500);
		}
	}

	@OPERATION
	void goLeftDirection() {
		robotAt--;
		getObsProperty("at").updateValue(robotAt);
		
		if (rooms[robotAt / rooms.length][robotAt % rooms.length])
			signal("dirty");
		
		if (robotAt - 1 < 0) {
			getObsProperty("direction").updateValue("right");
		}
	}

	@OPERATION
	void goRightDirection() {
		robotAt++;
		getObsProperty("at").updateValue(robotAt);
		
		if (!roomsOpen[robotAt / rooms.length][robotAt % rooms.length]) {
			signal("closed");
		} else if (rooms[robotAt / rooms.length][robotAt % rooms.length]) {
			signal("dirty");
		}
		
		if (robotAt + 1 >= rooms.length * rooms[0].length) {
			getObsProperty("direction").updateValue("left");
		}
	}

	@OPERATION
	void clean() {
		if (rooms[robotAt / rooms.length][robotAt % rooms.length]) {
			rooms[robotAt / rooms.length][robotAt % rooms.length] = false;
		} else {
			signal("noDirty");
		}
	}
}
