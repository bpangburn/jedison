# ReadMe file for the Java port of the EDISON image segmentation software

## BACKGROUND

This software is a partial Java port of the EDISON image segmentation
system developed by Chris M. Christoudias and Bogdan Georgescu at the
Robust Image Understanding Laboratory at Rutgers University
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

## BUILDING AND RUNNING

The project already uses Maven; see [BUILDING.md](BUILDING.md) for commands,
CI/test coverage, Java compatibility, and security checks, or
[IDE_SETUP.md](IDE_SETUP.md) for IDE imports.

```sh
git clone https://github.com/bpangburn/jedison.git
cd jedison
mvn -B -ntp clean verify
java -jar target/jedison-1.1.0.jar sample 0 true 6.5 7 20 0
```

The sample command writes `segment.png` to the current working directory.
The JAR manifest invokes `com.greatmindsworking.jedison.SegTest`.
The seven required arguments are: input path (or `sample`), EDISON port
version (`0` or `1`), display flag, color radius, spatial radius,
minimum region size, and speedup mode (`0`, `1` or `2`).
Mode `2` additionally requires a speed factor. See [TODO.md](TODO.md) for
a known legacy high-speed CLI argument parsing issue.

## MAVEN DEPENDENCY

```xml
<dependency>
    <groupId>com.greatmindsworking.jedison</groupId>
    <artifactId>jedison</artifactId>
    <version>1.1.0</version>
</dependency>
```

## ADDITIONAL INFORMATION

For more information, on EDISON, see the following web sites:
  http://www.caip.rutgers.edu/riul/research/code/EDISON/index.html
  http://www.caip.rutgers.edu/riul/research/robust.html
  http://www.caip.rutgers.edu/riul/
  
For specific questions regarding the Java port, send e-mail to
Brian E. Pangburn (segment@greatmindsworking.com)