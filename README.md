# Robocode@it-economics

![Gradle Build](https://github.com/it-economics-rhein-ruhr/robocode/workflows/Gradle%20Compile/badge.svg)

## Robocode How-To
1. fork [Github](https://github.com/it-economics-rhein-ruhr/robocode.git) project
2. clone the forked repo into your favourite IDE (currently Eclipse and IntelliJ are automatically supported)
3. Checkout the package structure
4. in `com.ite.robocode` you will find a Java class `MyFirstIteRobot`
5. rename this class to whatever you like (e.g. `SuperRobot`). Please remember to also rename the class file! This name will be your robots name. Choose wisely! 
6. basic robots extend the `Robot` class. All methods can be manually overwritten. To find some inspiration you can checkout the `Sample` package. 
7. let the fun begin and program your own robot behaviour. 
8. you can test your robots behaviour by performing `gradle run`. In the pop up window press `STRG + N`, choose the package `com.ite.robocode` and add your robot. Add a sample robot from the sample package, hit `Start Battle` and watch your robot perform
9. once you finished or time runs out you need to create a `Pull Request`
10. [ENTER HOWTO PICTURE HERE]
11. you're done. Relax or cheer for your robot while watching the performance against the others!