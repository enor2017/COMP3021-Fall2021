# Lab 10: Multithreading
This lab will cover the following topics:
* Basic Java Multithreading
* Multithreading in JavaFX
* Bindings and properties in JavaFX

## Background
A process can be considered as a program in execution. The process itself can be divided into several threads, each running concurrently with other threads. For instance, a word editor process could have a thread dedicated to running spell checking, and another dedicated to synchronizing with the cloud.

## Creating a thread
There are two common ways of creating a thread: extending the class (not preferred), and passing the thread constructor an object which implements the Runnable interface. We will focus on the second way.

### 1. Creating a regular class that implements Runnable
```
// TaskClass.java
// Custom task class
public class TaskClass implements Runnable {
  ...
  public TaskClass(...) { ... }
  // Implement the run method in Runnable interface
  public void run() {
    ...
  }
}

// Client.java
public class Client {
  ...
  public void someMethod() {
    ...
	// Create an instance of TaskClass
	TaskClass task = new TaskClass(...);
	
	// Create a thread
	Thread thread = new Thread(task);
	
	// Start a thread
	thread.start();
	...
  }
  ...
}
```

### 2. Anonymous class
```
// Client.java
public class Client {
  ...
  public void someMethod() {
    ...
    // Create an instance of TaskClass
    TaskClass task = new TaskClass(...);
	
    // Create a thread
    Thread thread = new Thread(new Runnable() {
      // Implement the run method in Runnable interface
      public void run() {
        ...
      }
    });
	
    // Start a thread
    thread.start();
    ...
  }
  ...
}
```

### 3. Lambda expression
```
// Client.java
public class Client {
  ...
  public void someMethod() {
    ...
    // Create an instance of TaskClass
    TaskClass task = new TaskClass(...);
	
    // Create a thread
    Thread thread = new Thread(() -> {      
      ...
    });
	
    // Start a thread
    thread.start();
    ...
  }
  ...
}
```

Just like in lab 10, we prefer using lambda expressions because it requires less typing and is easier to read.

## Complete example
```
// TaskThreadDemo.java

// The task for printing a specified character in specified times
class PrintChar implements Runnable {
  private char charToPrint; // The character to print
  private int times;        // The times to repeat
  
  /** Construct a task with specified character and number of
   *  times to print the character
   */
  public PrintChar(char c, int t) {
    charToPrint = c;
    times = t;
  }
  @Override
  /** Override the run() method to tell the system
   * what the task to perform
   */
  public void run() {
    for (int i = 0; i < times; i++)
      System.out.print(charToPrint);
  }
}

// The task class for printing number from 1 to n for a given n
class PrintNum implements Runnable {
  private int lastNum;
  /** Construct a task for printing 1, 2, ... i */
  public PrintNum(int n) {
    lastNum = n;
  }
  @Override
  /** Tell the thread how to run */
  public void run() {
    for (int i = 1; i <= lastNum; i++) {
      System.out.print(" " + i);
    }
  }
}

public class TaskThreadDemo {
  public static void main(String[] args) {
    // Create tasks
    Runnable printA = new PrintChar(a, 10);
    Runnable printB = new PrintChar(b, 10);
    Runnable print10 = new PrintNum(10);
    // Create threads
    Thread thread1 = new Thread(printA);
    Thread thread2 = new Thread(printB);
    Thread thread3 = new Thread(print10);
    // Start threads
    thread1.start();
    thread2.start();
    thread3.start();
  }
}
```

If we execute the program 4 times, we might get:
```
aaaaaaaaaa 1 2bbbbbbbbbb 3 4 5 6 7 8 9 10
aaaaaaaaaa 1bbb 2bbbbbbb 3 4 5 6 7 8 9 10
aaaaaaaaaa 1b 2bbbbbbbbb 3 4 5 6 7 8 9 10
aaaaaaaaaa 1b 2 3 4 5bbbbbbbbb 6 7 8 9 10
```
The outputs are different because it's up to the scheduler to decide which thread runs when! In order to implement more granular controls, we will need to do it ourselves using locks and semaphores (not covered in this lab). 

### Useful methods in the Thread class
* Thread(Runnable task): constructor
* void start(): start the thread, invoking the Runnable's run()
* boolean isAlive(): if the thread is currently running
* void setPriority(int p): set priority of thread (1 to 10)
* void join(): wait for this thread to finish
* void sleep(long milliseconds): put the thread to sleep for the specified time
* void yield(): cause a thread to pause temporarily, allowing other threads to execute
* void interrupt(): interrupt the thread

### Timer class
The timer class allows us to schedule tasks for future execution as a fixed interval. For the purposes of this lab, we will only be using the function `scheduleAtFixedRate`, which accepts a TimerTask object that override the `run()` method. It is inside this method that we will perform any updates to the UI via `Platform.runLater`.
Read more [here](https://docs.oracle.com/javase/8/docs/api/java/util/Timer.html). 


## What you need to do

Your task in this lab is to implement a parallel string merger, which provides a method to merge strings in parallel (with threads).

An array of string segments will be given as input, e.g., `["hlo", "el!]`, the `merge` method should create a set of threads whose size is the same as the number of segments. 
In this example, you should create `thread1` for `"hlo"` and `thread2` for `"el!"`. 
Then, all threads take turns to merge the segments character by character.

A writer is provided for threads to write the character. 
The expected behavior for the example is:
- `thread1` writes `h`
- `thread2` writes `e`
- `thread1` writes `l`
- `thread2` writes `l`
- `thread1` writes `o`
- `thread2` writes `!`

Eventually, the writer will get `h`, `e`, `l`, `l`, `o`, `!`, written by the two threads.

The last thing you need to make sure is that all threads should exit before `merge` method returns.

## Submission

Make sure you can pass all tests.

Submit your project to CASS - Lab10 by **18 Nov. 2021**.
