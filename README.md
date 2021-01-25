# jedison
This software is a partial port of the EDISON system developed by
Chris M. Christoudias and Bogdan Georgescu at the Robust Image
Understanding Laboratory at Rutgers University
(http://www.caip.rutgers.edu/riul/).

EDISON is available from:
http://www.caip.rutgers.edu/riul/research/code/EDISON/index.html

It is based on the following references:

[1] D. Comanicu, P. Meer: "Mean shift: A robust approach toward feature
    space analysis". IEEE Trans. Pattern Anal. Machine Intell., May 2002.

[2] P. Meer, B. Georgescu: "Edge detection with embedded confidence".
    IEEE Trans. Pattern Anal. Machine Intell., 28, 2001.

[3] C. Christoudias, B. Georgescu, P. Meer: "Synergism in low level vision".
    16th International Conference of Pattern Recognition, Track 1 - Computer
    Vision and Robotics, Quebec City, Canada, August 2001.

The above cited papers are available from:
http://www.caip.rutgers.edu/riul/research/robust.html

==============================================================================

This program is a Java port of the mean shift image segmentation portion
of the EDISON system developed by the Robust Image Understanding Laboratory
at Rutgers University.  It is more of a hack than an attempt at software
engineering.

The port involved the following general steps:
  1. consolidate header files (.h) and class files (.cpp) into Java
     classes (.java)
  2. consolidate existing documentation following JavaDoc conventions
  3. eliminate pointers
  4. tinker with any other data structures and constructs not compatible
     with Java until the code compiled
  5. move the code into the Java package com.greatmindsworking.EDISON.segm
  
We've added an executable class called SegTest that can be used to segment
an image from the command line.

The port was done so that the mean shift image segmentation algorithms
in EDISON could be incorporated into a separate Java software system called
Experience-Based Language Acquisition (EBLA).  EBLA allows a computer to
acquire a simple language of nouns and verbs based on a series of visually
perceived "events".  The segmentation algorithms form the backbone for EBLA's
vision system.  For more information on EBLA, visit
http://www.greatmindsworking.com

This release of jEDISON allow for segmentation based on either the 04-25-2002
or the 04-14-2003 release of the C++ EDISON code.

==============================================================================

This code has been tested using the Java SE 7

This release of jEDISON is being distributed as a single JAR file
(jEDISON_1.0.jar) containing the source, binaries, and JavaDoc
documentation.

There is a changelog.txt file, which contains changes for both jEDISON & EBLA.

To extract jEDISON, place the JAR file where you would like it installed (e.g.
"c:\temp\" or "/home/<username>/") and issue the command:

jar -xf jEDISON_1.0.jar

Note that except for the demo, SegTest.java, jEDISON is part of the package:
com.greatmindsworking.EDISON.segm so most of the files for jEDISON will
be located in that subdirectory.

To run the SegTest demo for jEDISON:
  1. change to the directory containing this file, SegText.java,
     and my_image.png (e.g. "cd ./jedison")
  2. compile SegTest.java using:
     "javac -classpath ./bin SegTest.java" (Linux)
     "javac -classpath .\bin SegTest.java" (Windows)
  3. get command line options using "java SegTest"
  4. run SegTest against my_image.png using appropriate command line options:
     "java -classpath ./bin:. SegTest my_image.png 0 true 6.5 7 20 0" (Linux)
     "java -classpath .\bin;. SegTest my_image.png 0 true 6.5 7 20 0" (Windows)
     
To recompile jEDISON:
  1. change to the directory containing this file, SegText.java,
     and my_image.png (e.g. "cd ./jedison")
  2. compile using:
     "javac -d ./bin -classpath . ./src/com/greatmindsworking/EDISON/segm/*.java" (Linux)
     "javac -d .\bin -classpath . .\src\com\greatmindsworking\EDISON\segm\*.java" (Windows)

To rebuild jEDISON JavaDocs:
  1. change to the directory containing this file, SegText.java,
     and my_image.png (e.g. "cd ./jedison")
  2. generate JavaDocs using:
     "javadoc -d ./docs -classpath . ./src/com/greatmindsworking/EDISON/segm/*.java" (Linux)
     "javadoc -d .\docs -classpath . .\src\com\greatmindsworking\EDISON\segm\*.java" (Windows)

==============================================================================

For more information, on EDISON, see the following web sites:
  http://www.caip.rutgers.edu/riul/research/code/EDISON/index.html
  http://www.caip.rutgers.edu/riul/research/robust.html
  http://www.caip.rutgers.edu/riul/
  
For specific questions regarding the Java port, send e-mail to
Brian E. Pangburn (segment@greatmindsworking.com)