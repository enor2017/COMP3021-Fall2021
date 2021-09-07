# Lab 1: IntelliJ + Git Quick Start Guide

This lab covers the following topics:

- Installing and running IntelliJ IDEA 2021.x with Java 16
- Using Gradle to build and test projects
- Using Git with GitHub for version control

## JDK 16 Download and Installation

Unless you want to do this yourself, Gradle automatically downloads an appropriate JDK for your project, saving you the
hassle of having to install and tell IntelliJ IDEA (and Gradle) where to find your JDK.

If you want to download JDK 16 by yourself, we suggest
[Adoptium](https://adoptium.net/?variant=openjdk16&jvmVariant=hotspot).

*Note: In order to run Gradle, you will still need a JDK installed in your system. Since IntelliJ IDEA already bundles a
JDK distribution, you may try to use that. The path to the IntelliJ-bundled JDK is under `<PATH_TO_IDEA>/jbr`, where
`<PATH_TO_IDEA>` is the location of your IntelliJ IDEA installation.*

## IntelliJ IDEA Installation

IntelliJ IDEA comes in two versions, Community and Ultimate. The Community version is good enough for this course, but
you can apply for a student Ultimate license [here](https://www.jetbrains.com/student/). Follow the download and
installation instructions [here](https://www.jetbrains.com/idea/download/).

## Hello World in IntelliJ IDEA

This is usually the part where you will create a project from scratch using the "New Project" functionality of IntelliJ
IDEA. However, configuring Gradle is outside of the syllabus of this course. As such, you should just download the
skeleton code accompanying this lab and open it in IntelliJ IDEA. Gradle will sort out the rest for you.

Step-by-Step Instructions:

1. Launch IntelliJ IDEA.
2. Open this project in IntelliJ IDEA.
3. Wait for Gradle to download JDK and the Gradle distribution.
4. In the `Project` Panel (at the left-hand side), go to `src/main/java/hk.ust.cse.comp3021.lab1/Main`.
5. Click on the `Run` button beside `public class Main {` (the green arrow).
    - You may also click on the `Run` button beside `public static void main(String[] main)`.
6. After waiting a bit, you should see the `Run` Panel (at the bottom) show the following:

    ```
    18:59:25: Executing task ':Main.main()'...

    > Task :compileJava
    > Task :processResources NO-SOURCE
    > Task :classes

    > Task :Main.main()
    This is a demo project.
    BUILD SUCCESSFUL in 224ms
    2 actionable tasks: 2 executed
    18:59:26: Task execution finished ':Main.main()'.
    ```

   If you see `This is a demo project.` after `> Task :Main.main()`, your installation works and you are good to go!

### Testing Java 16 (Preview) Features

To make sure that syntax highlighting and linting works with the Java 16 preview features, do the following:

1. Under `hk.ust.cse.comp3021.lab1`, add a new class named `BinaryValue`.
2. Replace the contents of the new file with the following:

    ```java
    package hk.ust.cse.comp3021.lab1;
   
    public sealed class BinaryValue {
        
        public static final class Zero extends BinaryValue {}
        public static final class One extends BinaryValue {}
    }
    ```

   There should be no errors in this file after typing everything.

3. In the `main` method in `Main.java`, try to create an instance of `BinaryValue.Zero` or `BinaryValue.One`:

    ```java
    public static void main(String[] args) {
        // ...
        final var zero = new BinaryValue.Zero();
        final var one = new BinaryValue.One();
    }
    ```

4. Re-run the `Main` target, and make sure there are no compilation errors.

### Useful IntelliJ Keyboard Shortcuts

These key bindings may differ between operating systems.

- `Alt + Enter`: Contextual suggestions, including fixes for errors (if available)
- `Ctrl + Alt + L`: Formats your code automatically
- `Ctrl + B`: Go to implementation
- `Ctrl + Q`: Quick documentation

Other shortcuts can be found under `Help > Keymap Reference`.

## Git and GitHub

We will be using Git for version control, and GitHub to host our code remotely. While GitHub now allows the creation of
private code repositories, you will still need to register for a free student pack because your TAs will need access to
your repository.

You can register for a free student pack [here](https://help.github.com/articles/applying-for-a-student-developer-pack/)
. Note that you should get this set up by the deadline of PA1, so that you may share your repository to us for grading.

Either way, create a private repository using the instructions outlined
[here](https://help.github.com/articles/create-a-repo/). Afterwards, note down the URL to your repository, e.g.
`https://github.com/lamngok/helloworld.git`.

There are two ways of managing a Git repository - Either via IntelliJ IDEA or using the `git` command-line tool. The
following sections will show how to *commit* to the Git repository, and how to *push* your local changes to GitHub.

### Git via IntelliJ

If you want to use IntelliJ's Git integration, follow these steps:

1. In the top menu bar, go to `VCS > Enable Version Control Integration...`.
2. In the pop-up window, select `Git` and click `OK`. You should see most files in the Project panel turn red.
3. Hover over the icon on the bottom-left with a square and two vertical lines around it, and select `Commit`.
    - You will see that the `Changes` section is empty, and there are a lot of files in `Unversioned Files`.
4. Select all files in the `Unversioned Files` section, and drag them all into the `Changes` section.
    - You can just drag the `Unversioned Files` header directly into `Changes`, and it will add all files.
    - The end result should be that all files should now be under the `Changes` section, and all filenames should become
      yellow-green color.
5. Check the checkbox beside `Changes`.
    - The checkbox beside all files should also be checked.
6. Type a commit message in the `Commit Message` text box.
    - Commit messages are generally used to note that the changes you have made.
    - Since this is the first commit in this Git repository, we usually put "Initial Commit" by convention.
7. Click the `Commit` button at the bottom of the `Commit Message` text box.
    - Congratulations! You have just made your first commit for this project!
8. In the top menu bar, go to `Git > Manage Remotes...`.
9. In the pop-up window, click on the add button (`+`), and in the `URL` text box, paste the URL of your newly created
   repository.
    - When you click `OK`, IntelliJ will test whether the repository is valid, and may ask you to enter your username
      and password for GitHub.
10. In the top menu bar (last time, trust me), go to `Git > Push...`.
11. In the pop-up window, click `Push` at the bottom-right of the window.
    - After clicking on the button, there will be a bar at the bottom indicating that the push operation is ongoing.
    - You may need to enter your GitHub username and/or password again.
12. When the push operation is done, a popup will appear at the bottom of IntelliJ, showing the message "Push
    successful".
    - Congratulations again! You have just pushed your first local commit on GitHub!
    - If the push operations fails, a popup will appear at the bottom-right corner of IntelliJ. You may click on the
      popup to show the full message.
13. *(Optional)* Go to your repository using a web browser, and see your files in GitHub!

### Git via Command Line

If you prefer to use the Git command-line, follow these steps:

1. Hover over the icon on the bottom-left with a square and two vertical lines around it, and select `Terminal`.
    - Alternatively, open your preferred terminal, and change the working directory to the project location. *(We trust
      that you know how to change directories in your preferred shell.)*
2. Run the following commands in sequence:
    - `git init`, which creates a Git repository at the current working directory.
    - `git add .`, which tells Git to start tracking all files under the current working directory.
    - `git commit -m "Initial Commit"`, which tells Git to commit all changed files (which is all files in this case).
    - `git remote add origin <URL_TO_REPO>`, which tells Git where your remote repository is, and naming it `origin`
      (by convention). Substitute `<URL_TO_REPO>` with the URL to your repository.
    - `git push -u origin master`, which *pushes* your `master` branch to the `origin` repository, in a branch also
      named `master`.
3. Congratulations! You just committed and pushed your first commit!
    - *(Optional)* Go to your repository using a web browser, and see your files in GitHub!

### Extra: Gitignore

Gitignore is a file which tells Git which specific file or file patterns to ignore in the repository.

The common use for Gitignore is to exclude files that do not or should not be shared with other developers of the 
repository. For instance, we generally would not share build files (e.g. `.class` files in Java or `.o` files in C++),
as these files can be (and should be) generated when you build a project.

A Gitignore file is already bundled with this lab (named `.gitignore`), so you do not need to write/generate your own.

### Extra: Committing and Pushing

So what exactly is *committing* and *pushing*? Let's take a look at the manual...

> ```
> NAME
>       git-commit - Record changes to the repository
> 
> DESCRIPTION
>       Create a new commit containing the current contents of the index and the given log message describing the changes. 
>       The new commit is a direct child of HEAD, usually the tip of the current branch, and the branch is updated to 
>       point to it (unless no branch is associated with the working tree, in which case HEAD is "detached" as described
>       in git-checkout(1)).
> 
> ...
> 
> NAME
>       git-push - Update remote refs along with associated objects
> 
> DESCRIPTION
>       Updates remote refs using local refs, while sending objects necessary to complete the given refs.
> ```

(Source: [git-commit](https://git-scm.com/docs/git-commit), [git-push](https://git-scm.com/docs/git-push))

Well... That's not very helpful :(

In simpler terms, `git commit` creates a new "version" of your repository with the changes you have `git add`-ed in a 
previous step. This can be thought of as the version history of Google Docs or Office 365, where each change you make to
a document will create a new revision; The only difference is that Git requires you to manually add/commit your changes.

On the other hand, `git push` updates the remote repository by uploading all missing commits from your local repository.
The opposite operation is `git pull`, which updates your local repository by downloading all missing commits from the
remote repository.

## Extra: Gradle

*Recall in COMP2012 (or COMP2012H), you were taught Makefiles. You were taught that Makefiles simplify the compilation
process by removing the need to compile individual files on your own, knowing only to recompile dependent targets,
etc...*

In Java, build systems like GNU Make also exists. In this lab (and subsequent labs, as well as assignments), we will be 
using a build system called [Gradle](https://gradle.org/).

Like Make, Gradle helps developers manage projects by simplifying the compilation process and caching compilation
results. However, Gradle can do a lot more than Make can, for instance:

- Download external libraries from the web
- Run test cases and generate reports
- Use plugins to add even more functionality

Also like Make, Gradle projects organizes itself into tasks. For example:

- To compile all Java source files, run the task `assemble`
- To compile a project into a JAR, run the task `jar`
- To clean the build environment, run the task `clean`

However, you will mostly be interacting with Gradle via IntelliJ IDEA.

Gradle is configured using buildscript files written either in Groovy or Kotlin (both are languages which can run on the
Java VM). The corresponding files can be found in `settings.gradle.kts` and `build.gradle.kts` in this lab.

You are not expected to learn nor understand how Gradle works in this course. However, if you are interested in
developing larger Java projects or Android applications, you may be interested in reading more about Gradle. 
[Here](https://docs.gradle.org/current/userguide/what_is_gradle.html) is a good place to start.

## Lab 1 Submission

1. Import this project into IntelliJ, take a **screenshot**.
2. Create a **private** repository on GitHub.
3. Create a local Git repository for your Hello World project.
4. Add your GitHub repository as a remote repository for your Hello World project.
5. Push your Hello World to your private repository on GitHub, take a **screenshot** of your GitHub repository.
6. Submit a Word or PDF file including a **screenshot** of your imported IntelliJ project and a **screenshot** of your 
    created Git repository on GitHub to [CASS](https://course.cse.ust.hk/cass).
