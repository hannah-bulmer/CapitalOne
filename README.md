# Coding Challenge for Capital One (1.0.0)

### Summary of Challenge
Create a program that takes a file as input and returns some statements about the program's comments;  how many, what types, how many `TODOs`, etc. I created my solution in Java. The main code can be found in [Solution.java](https://github.com/hannah-bulmer/CapitalOne/blob/master/src/Solution.java). The test suite can be found in [SolutionTest.java](https://github.com/hannah-bulmer/CapitalOne/blob/master/src/SolutionTest.java).

After creating a JAR file, this program can be run in any folder by typing
```bash
java -jar path/to/jar.jar
```
It will then wait for input. Please enter a relative or absolute path to the file you want to analyse.

Statistics such as the following will then be printed to the console:
```
Total # of lines: 52
Total # of comment lines: 5
Total # of single line comments: 2
Total # of comment lines within block comments: 3
Total # of block line comments: 1
Total # of TODO’s: 0
```

### Assumptions I Made In This Task
1. Lines that contain multiple `TODO` would be considered as one `TODO`. E.g.

```Java
// TODO: Write up all the TODOs we have to complete this week
```
2. Multiline comments in Java/C++/Javascript-type syntax should be made using `/* */`. Comments that appear in the form
```Java
// hello
// I am a comment
```
or
```Java
code() // comment about code
moreCode() // comment about more code
```
count as multiple single line comments

### Known specifications and limitations
This program does not handle comments in languages such as Scheme, that use `;;` for their comment schemes.

This program counts lines such as
```Java
System.out.println("// A comment");
```
as comments at the given time.
