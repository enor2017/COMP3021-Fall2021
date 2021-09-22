package hk.ust.cse.comp3021.lab4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VariableNameValidatorTest {

    private VariableNameValidator validator;
    private String varName;

    @BeforeEach
    void setUp() {
        validator = new VariableNameValidator();
    }

    @Test
    void testFooBar() {
        varName = "foo";
        assertTrue(validator.validate(varName));

        varName = "bar";
        assertTrue(validator.validate(varName));
    }

    @Test
    void testLoopVar() {
        varName = "i";

        assertTrue(validator.validate(varName));
    }

    @Test
    void testStaticFieldNaming() {
        varName = "STATIC_FIELD";

        assertTrue(validator.validate(varName));
    }

    @Test
    void testNameWithUnderscore() {
        varName = "unconventional_var_name";

        assertTrue(validator.validate(varName));
    }

    @Test
    void testNameWithDollarSign() {
        varName = "lambda$1";

        assertTrue(validator.validate(varName));
    }

    @Test
    void testNameStartingWithDigit() {
        varName = "1abc";

        assertFalse(validator.validate(varName));
    }

    @Test
    void testEmpty() {
        varName = "";

        assertFalse(validator.validate(varName));
    }

    @Test
    void testWithWhitespace() {
        varName = "variable name";

        assertFalse(validator.validate(varName));
    }

    @Test
    void testWithInvalidCharacters() {
        varName = "i-wanna-be-a-variable";

        assertFalse(validator.validate(varName));
    }

    @AfterEach
    void tearDown() {
        varName = null;
        validator = null;
    }
}
