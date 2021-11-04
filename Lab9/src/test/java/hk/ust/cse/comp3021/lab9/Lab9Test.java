package hk.ust.cse.comp3021.lab9;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class Lab9Test {

    private static final File java5File = getTestFile("Assert.class");
    private static final File java11File = getTestFile("MainCommand.class");
    private static final File java17File = getTestFile("ClassFileParser.class");
    private static final File emptyFile = getTestFile("Test2.class");
    private static final File nonClassFile = getTestFile("Test3.class");

    private static File getTestFile(String fileName) {
        try {
            return new File(Objects.requireNonNull(Lab9Test.class.getResource("/" + fileName)).toURI());
        } catch (URISyntaxException e) {
            return null;
        }
    }

    @Test
    public void testIsJavaFile() {
        assertTrue(ClassFileParser.isClassFile(java5File));
        assertTrue(ClassFileParser.isClassFile(java11File));
        assertTrue(ClassFileParser.isClassFile(java17File));
        assertFalse(ClassFileParser.isClassFile(emptyFile));
        assertFalse(ClassFileParser.isClassFile(nonClassFile));
    }

    @Test
    public void testGetVersion() {
        assertEquals(49, ClassFileParser.getClassFileVersion(java5File));
        assertEquals(55, ClassFileParser.getClassFileVersion(java11File));
        assertEquals(61, ClassFileParser.getClassFileVersion(java17File));
        assertEquals(-1, ClassFileParser.getClassFileVersion(emptyFile));
        assertEquals(-1, ClassFileParser.getClassFileVersion(nonClassFile));
    }
}
