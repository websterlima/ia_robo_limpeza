// Agent cleaner in project aula2

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */

+!clearRoom(X) <- .print("Cleaning room ", X);
                  clean.

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have a agent that always complies with its organization  
//{ include("$jacamoJar/templates/org-obedient.asl") }
