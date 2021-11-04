package hk.ust.cse.comp3021.lab9;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ClassFileParser {

    /**
     * Tells whether a file is a Java class file.
     *
     * @param theFile The input file
     * @return A boolean value indicating whether it is a Java class file.
     */
    public static boolean isClassFile(@NotNull File theFile) {
        return false;
    }

    /**
     * Gets the class file version of the class file.
     *
     * @param theFile The input file.
     * @return The major version of the class file (e.g, 55 for Java 11). Return -1 if it is not a class file or the major version can not be found.
     */
    public static int getClassFileVersion(@NotNull File theFile) {
        return -1;
    }

}
