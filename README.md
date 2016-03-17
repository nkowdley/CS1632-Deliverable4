# Testing Arrays.Sort with JUnit
This is Deliverable 4 for CS1632: Software Quality Assurance


## Project Description:
Taken from https://github.com/laboon/cs1632/blob/master/deliverables/4/deliverable4.md

Write your own JUnit-based property-based test to check Arrays.sort(int[] arr) method in Java

For the JUnit-based property-based tests, generate a minimum of 100 different random arrays of different sizes, and test different properties (many examples were discussed in the lecture on property-based testing) of sorting them. You may use Java's built-in Arrays.sort() method. You should test at least three different properties of each sorted array. You should use traditional JUnit test procedures (e.g., use assertions, don't use System.out.println during normal execution, etc.) Since we are testing a built-in Java method, I don't expect any failures, but who knows, you may be the one to find a bug in Java's own libraries!

You may do this either all in one JUnit test method, or with one method per property.

## Build/Run Instructions
*Compiling and Running these tests have only been tested on Eclipse.*

To build and run these tests in eclipse, you need to import the project, and add 2 external libraries to the build path:
1. JUnit 4.12 (https://github.com/junit-team/junit/wiki/Download-and-Install)
2. Apache Commons Lang (http://commons.apache.org/proper/commons-lang/download_lang.cgi)

Then compile and run the project as a JUnit Test
