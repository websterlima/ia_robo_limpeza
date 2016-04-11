// Agent sample_agent in project aula2

/* Initial beliefs and rules */

/* Initial goals */

rooms(20).

!start.

/* Plans */

+!start : rooms(X) <- makeArtifact("rooms", "aula2.Rooms", [X], ID);
                  focus(ID);
                  !searchTrash.
                  
+!searchTrash <- .drop_all_intentions;
                 !move;
                 .wait(1000);
                 !searchTrash.
                 
+!move: at(X) & X == 0 <- .println("Moving to room ", X + 1);
                          goRight.

+!move: at(X) & X == 1 <- .println("Moving to room ", X - 1);
                          goLeft.
                          
+dirty: at(X) <- .println("Cleaning room ", X);
                 clean.

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have a agent that always complies with its organization  
//{ include("$jacamoJar/templates/org-obedient.asl") }
