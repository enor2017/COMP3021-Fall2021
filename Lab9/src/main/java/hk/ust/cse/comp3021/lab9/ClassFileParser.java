package hk.ust.cse.comp3021.lab9;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Scanner;

public class ClassFileParser {

    /**
     * Tells whether a file is a Java class file.
     *
     * @param theFile The input file
     * @return A boolean value indicating whether it is a Java class file.
     */
    public static boolean isClassFile(@NotNull File theFile) {
        // first check if it is a file, and if ends with '.class'
        if (!theFile.isFile() || !theFile.getName().endsWith(".class")) {
            return false;
        }

        // then check the magic field '0xCAFEBABE'
        try(var stream = new DataInputStream(new FileInputStream(theFile))) {
            if (stream.readUnsignedShort() == 51966 &&
                    stream.readUnsignedShort() == 47806) {
                return true;
            }
        } catch (IOException ignored) {}
        return false;
    }

    /**
     * Gets the class file version of the class file.
     *
     * @param theFile The input file.
     * @return The major version of the class file (e.g, 55 for Java 11). Return -1 if it is not a class file or the major version can not be found.
     */
    public static int getClassFileVersion(@NotNull File theFile) {
        // first check if it is a class file
        if (!isClassFile(theFile)) {
            return -1;
        }
        try(var stream = new DataInputStream(new FileInputStream(theFile))) {
            // first read two magic fields and one minor version
            stream.readUnsignedShort();
            stream.readUnsignedShort();
            stream.readUnsignedShort();
            // then check major version
            var version = stream.readUnsignedShort();
            if (version >= 45 && version <= 61) {
                return version;
            } else {
                return -1;
            }
        } catch (IOException ignored) {}
        return -1;
    }

}
