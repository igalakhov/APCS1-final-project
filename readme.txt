# APCS1-final-project

Final Project for APCS1 

Code finished as of 3:00 AM 1/21/2018

## Setup instructions:
To compile, set your current directory to the src folder of this project and run:
```
javac -cp twitter4j.jar;watson.jar; TwitterShell.java
```
To run, run: (not including the classpath arguments might cause a classnotfoundexception)
```
java -cp twitter4j.jar;watson.jar; TwitterShell
```

## Description
  This is a project that allows the user to analyze how twitter feels about certain topcs. It is an imporoved version of a [project](https://github.com/igalakhov/Hack-River-Dell-II) I made for a 24 hour hackathon around november. 

## How to use
  This project runs in the form of a shell, allowing the user to input commands in sequence. You can find a list of all avaliable commands with their descriptions and patterns by inputting
```
> help
```
  into the shell. 
 
## Example command sequence
```
> loadtweets #computerscience 50
```
```
> analyzetweets
```
```
> showinfo benchmarks
```

  