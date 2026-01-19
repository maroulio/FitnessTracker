# FitnessTracker

    Attention! Open this .md file by IntelliJ IDEA application or Notepad with Formatted Markdown View, please!

## Compilation Instructions

1. Open a terminal or CLI (Command Line Interface)
2. Go to ~/FitnessTracker/FitnessManager directory
3. Type 'mvn clean package' or 'mvn package' command
4. Press 'ENTER'

   And the code would be compiled successfully!

## Execution Instructions
Part 1
1. Type 'java -jar ./target/FitnessManager-1.0.0.jar' command
2. Add at least one .tcx file after inserting a space (SPACE) next to the command, for instance:

    java -jar ./target/FitnessManager-1.0.0.jar cycling_activity_1.tcx

    Or add more .tcx files by inserting spaces (SPACE) between them, as follows:

    java -jar ./target/FitnessManager-1.0.0.jar cycling_activity_1.tcx running_activity_1.tcx

    Or insert diverse features, such as the weight, the age or the gender, by adding flags to the command, flag -w for weight, flag -a for age, flag -g for gender, which by default can only take the values of 'm' & 'f', and inserting spaces (SPACE) between them, so that the total calories burnt by an activity are calculated and popped onto the screen, as follows:

    java -jar ./target/FitnessManager-1.0.0.jar -w 65.9 -a 20 -g f cycling_activity_1.tcx running_activity_1.tcx


    Attention!
    - For the calories to show up on the screen, at least the feature of a positive valid weight is
    required! Nevertheless, in case it is crucial that the calories computation is as precise as possible,
    the addition of the features of age and gender is highly recommended!
    - For the .tcx files to be correctly imported in the execution command line, it is compulsory that the
    absolute directory path where they can be found needs to be indicated! All the execution commands in the
    present file, by default, have no specific path defined!

3. Press 'ENTER'

   And the code would run successfully!

Part 2
1. Type 'java -cp ./target/classes/gr.hua.dit.fitnessmanager.GUI' command
2. Press 'ENTER'

    And the window would open successfully!
## Assignment Application Implementation

Part 1

1. The retrieval of data by the .tcx files and their forwarding to the program code is done!
2. The presentation of the total statistics of every different activity imported by the .tcx files is done!
3. The calculation and presentation of every activity's burned calories in the occasion of at least the weight feature insertion is done!

    Consequently, the whole Part 1 of the assignment is successfully completed and the requests are all done!

Part 2

1. ...

## Program Separation - Classes Functionality

1. Activity.java

* Contains the activity's name every time a new Activity.java object is created in TCXParser.java
* Computes the total statistics of every activity by Laps.java and forwards them to main class FitnessManager.java
* Returns total calories burnt if asked

2. ActivityFactory.java

* Creates and returns a new object which type is one of the Activity.java subclasses, Cycling.java, Other.java, Running.java, Swimming.java or Walking.java, for every new activity

3. CaloriesCalculator.java

* Serves mostly as an interface to support one same method that is used both in SimpleCaloriesCalculator.java and HRCaloriesCalculator.java

4. CaloriesFactory.java

* Consists of an enumeration for the specification of the calories calculation kind in accordance with the command flags values
* Creates and returns a new object which type is one of the CaloriesCalculator.java subclasses, HRCaloriesCalculator.java or SimpleCaloriesCalculator.java, for every new calories calculation needed

5. Cycling.java

* Constructs a new Cycling.java object by calling the superclass Activity.java constructor

6. FitnessManager.java

* Creates a new UserProfile.java object
* Implements necessary checks for the flags -w, -a, -g in args table and args table contents respectively
* Forwards the values of flags into UserProfile.java
* Creates a new File.java object for every new existing .tcx file in the command executed
* Creates a new TCXParser.java object to forward for parsing every new existing .tcx file in the command
* Display the activity's statistics and calories burned if requested and available

7. HRCaloriesCalculator.java

* Calculates total calories burnt by using the relation which has a higher precision where not only weight but also age and gender are required

8. Laps.java

* Retrieves the necessary statistics by TCXParser.java
* Computes the statistics of every lap by Tracks.java and forwards them to Activity.java
* Returns every lap's calories burned if needed

9. Other.java

* Constructs a new Other.java object by calling the superclass Activity.java constructor

10. Running.java

* Constructs a new Running.java object by calling the superclass Activity.java constructor

11. SimpleCaloriesCalculator.java

* Defines the value of the multiplier needed for the computation
* Calculates total calories burnt by using the relation which has a lower accuracy where only weight is used

12. Swimming.java

* Constructs a new Swimming.java object by calling the superclass Activity.java constructor

13. TCXParser.java

* Receives a .tcx file
* Checks the existence and validity of the file received
* Parse the existent data from the .tcx file
* Forward the useful data to the suitable classes every time, that is into Activity.java, Laps.java, Tracks.java & Trackpoints.java

14. Trackpoints.java

* Retrieves basically the appropriate statistics by TCXParser.java

15. Tracks.java

* Computes the statistics of every track by Trackpoints.java and forwards them to Laps.java

16. UserProfile.java

* Contains the attributes weight, age & gender received from the command line
* Initializes the attributes provided from the command line by incorrect default values so as to be manageable

17. Walking.java

* Constructs a new Walking.java object by calling the superclass Activity.java constructor

## Program Use Successful Scenarios

  After compilation, some scenarios in which the program code runs with success are the following:

* java -jar ./target/FitnessManager-1.0.0.jar running_activity_2.tcx

![](running_activity_2.png)
* java -jar ./target/FitnessManager-1.0.0.jar cycling_activity_1.tcx running_activity_1.tcx

![](cycling_activity_1%20running_activity_1.png)
* java -jar ./target/FitnessManager-1.0.0.jar swim_activity_1.tcx pool_swim-activity_1.tcx

![](swim_activity_1%20pool_swim-activity_1.png)
* java -jar ./target/FitnessManager-1.0.0.jar cycling_activity_1.tcx running_activity_1.tcx running_activity_2.tcx pool_swim-activity_1.tcx swim_activity_1.tcx walking_activity_1.tcx

![](cycling_activity_1%20running_activity_1%20running_activity_2%20pool_swim-activity_1%20swim_activity_1%20walking_activity_1.png)
* java -jar ./target/FitnessManager-1.0.0.jar -w 65.9 walking_activity_1.tcx running_activity_2.tcx

![](-w%2065.9%20walking_activity_1%20running_activity_2.png)
* java -jar ./target/FitnessManager-1.0.0.jar -w 65.9 -a 20 -g m cycling_activity_1.tcx running_activity_1.tcx

![](-w%2065.9%20-a%2020%20-g%20m%20cycling_activity_1%20running_activity_1.png)
* java -jar ./target/FitnessManager-1.0.0.jar -w 65.9 -a 20 -g f cycling_activity_1.tcx running_activity_1.tcx

![](-w%2065.9%20-a%2020%20-g%20f%20cycling_activity_1%20running_activity_1.png)
## Auxiliary .tcx Files Examples

Auxiliary .tcx files the program code executes successfully with, by default, are the following:

* cycling_activity_1.tcx
* pool_swim-activity_1.tcx
* running_activity_1.tcx
* running_activity_2.tcx
* swim_activity_1.tcx
* walking_activity_1.tcx