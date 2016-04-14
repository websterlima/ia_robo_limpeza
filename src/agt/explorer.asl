// Agent sample_agent in project aula2

/* Initial beliefs and rules */


/* Initial goals */

!start.

/* Plans */

+!start <- makeArtifact("rooms", "aula2.Rooms", [4, 4], ID);
                  focus(ID);
                  !searchTrash.
                  
+!searchTrash <- .drop_all_intentions;
                 !move;
                 .wait(1000);
                 !searchTrash.
                 
+!move : at(X) & direction("right") <- .println("Moving to room ", X + 1);
                          goRightDirection.

+!move : at(X) & direction("left") <- .println("Moving to room ", X - 1);
                          goLeftDirection.

+dirty : at(X) <- .send(cleaner, achieve, clearRoom(X)).

+closed : at(X) <- .print("Room ", X, " is closed").

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have a agent that always complies with its organization  
//{ include("$jacamoJar/templates/org-obedient.asl") }
