# ReadMe file for the Java port of the EDISON image segmentation software

## BACKGROUND

This software is a partial Java port of the EDISON system developed by
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

## EDISON PORT

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

## COMPILATION

jEDISON has been tested with Java 1.8 or later.

Git/Maven:
  `git clone https://github.com/bpangburn/jedison.git`

  After cloning, you can use an IDE, e.g. Eclipse or NetBeans, to compile/run.
  Or you can use mvn directly and then run as shown here. Note that compiled
  jar files will be in the ./target subdirectory.
  
  jEDISON Library:
    `cd ./jedison/`
    `mvn clean package -Prelease`

## DEPENDENCY INTEGRATION

  Add jEDISON to the Maven dependencies in your POM file:  

    <dependency>
        <groupId>com.nqadmin.jedison</groupId>
        <artifactId>jedison</artifactId>
        <version>x.y.z</version>
    </dependency>

## SAMPLE/DEMO PROGRAMS

  1. Navigate to the folder containing the jEDISON jar
  2. Type: `java -jar jedison-x.y.z.jar {comma-separated paramters}`
  
  Usage: java -jar jedison-x.y.z.jar <source image ('sample' for demo)> <version: 0=04-25-2002, 1=04-14-2003> <display: true/false> <color radius> <spatial radius> <min region> <speedup: 0=none, 1=medium, 2=high> <for speedup of 2, speed factor: 0.0=better quality, 1.0=better speed>
  
  To quickly run jEDISON on a sample image packaged in the jar, type: `java -jar jedison-x.y.z.jar sample 0 true 6.5 7 20 0`

## ADDITIONAL INFORMATION

For more information, on EDISON, see the following web sites:
  http://www.caip.rutgers.edu/riul/research/code/EDISON/index.html
  http://www.caip.rutgers.edu/riul/research/robust.html
  http://www.caip.rutgers.edu/riul/
  
For specific questions regarding the Java port, send e-mail to
Brian E. Pangburn (segment@greatmindsworking.com)