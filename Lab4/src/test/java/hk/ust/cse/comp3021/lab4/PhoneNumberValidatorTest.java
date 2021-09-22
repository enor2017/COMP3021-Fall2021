package hk.ust.cse.comp3021.lab4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhoneNumberValidatorTest {

    private PhoneNumberValidator validator;
    private String phoneNum;

    @BeforeEach
    void setUp() {
        validator = new PhoneNumberValidator();
    }

    @Test
    void testEmergencyNumber() {
        phoneNum = "999";

        assertTrue(validator.validate(phoneNum));
    }

    @Test
    void testIllegalStartChar() {
        phoneNum = "-12345678";

        assertFalse(validator.validate(phoneNum));
    }

    @Test
    void testNumberWithoutPrefix() {
        phoneNum = "2025550118";

        assertTrue(validator.validate(phoneNum));
    }

    @Test
    void testNumberWithPrefix() {
        phoneNum = "+85220003000";

        assertTrue(validator.validate(phoneNum));
    }

    @Test
    void testNumberWithoutPrefixLimit() {
       phoneNum = "123456789012";

       assertTrue(validator.validate(phoneNum));
    }

    @Test
    void testNumberWithoutPrefixTooLong() {
        phoneNum = "1234567890123";

        assertFalse(validator.validate(phoneNum));
    }

    @Test
    void testNumberWithPrefixLimit() {
        phoneNum = "+123456789012345";

        assertTrue(validator.validate(phoneNum));
    }

    @Test
    void testNumberWithPrefixTooLong() {
        phoneNum = "+1234567890123456";

        assertFalse(validator.validate(phoneNum));
    }

    @Test
    void testNumberIllegalChar() {
        phoneNum = "500-500-123";

        assertFalse(validator.validate(phoneNum));
    }

    @Test
    void testMultiplePlusesWithPrefix() {
        phoneNum = "+123+123";

        assertFalse(validator.validate(phoneNum));
    }

    @Test
    void testMultiplePlusesWithoutPrefix() {
        phoneNum = "123+123";

        assertFalse(validator.validate(phoneNum));
    }

    @AfterEach
    void tearDown() {
        phoneNum = null;
        validator = null;
    }
}
