package hk.ust.cse.comp3021.lab4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// All tests taken from sample emails in Wikipedia
// https://en.wikipedia.org/wiki/Email_address
public class EmailValidatorTest {

    private EmailValidator validator;
    private String emailAddress;

    @BeforeEach
    void setUp() {
        validator = new EmailValidator();
    }

    @Test
    void testSimple() {
        emailAddress = "simple@example.com";

        assertTrue(validator.validate(emailAddress));
    }

    @Test
    void testWithDot() {
        emailAddress = "very.common@example.com";

        assertTrue(validator.validate(emailAddress));
    }

    @Test
    void testWithPlus() {
        emailAddress = "disposable.style.email.with+symbol@example.com";

        assertTrue(validator.validate(emailAddress));
    }

    @Test
    void testWithHyphen() {
        emailAddress = "other.email-with-hyphen@example.com";

        assertTrue(validator.validate(emailAddress));
    }

    @Test
    void testAllHyphens() {
        emailAddress = "fully-qualified-domain@example.com";

        assertTrue(validator.validate(emailAddress));
    }

    @Test
    void testTag() {
        emailAddress = "user.name+tag+sorting@example.com";

        assertTrue(validator.validate(emailAddress));
    }

    @Test
    void testSingleLetter() {
        emailAddress = "x@example.com";

        assertTrue(validator.validate(emailAddress));
    }

    @Test
    void testHyphenInDomain() {
        emailAddress = "example-indeed@strange-example.com";

        assertTrue(validator.validate(emailAddress));
    }

    @Test
    void testSlashes() {
        emailAddress = "test/test@test.com";

        assertTrue(validator.validate(emailAddress));
    }

    @Test
    void testNoTLD() {
        emailAddress = "admin@mailserver1";

        assertTrue(validator.validate(emailAddress));
    }

    @Test
    void testExampleTLD() {
        emailAddress = "example@s.example";

        assertTrue(validator.validate(emailAddress));
    }

    @Test
    void testBangifiedHostRoute() {
        emailAddress = "mailhost!username@example.org";

        assertTrue(validator.validate(emailAddress));
    }

    @Test
    void testEscapedRoute() {
        emailAddress = "user%example.com@example.org";

        assertTrue(validator.validate(emailAddress));
    }

    @Test
    void testMissingAt() {
        emailAddress = "Abc.example.com";

        assertFalse(validator.validate(emailAddress));
    }

    @Test
    void testTooManyAt() {
        emailAddress = "A@b@c@example.com";

        assertFalse(validator.validate(emailAddress));
    }

    @Test
    void testInvalidChars() {
        emailAddress = "a\"b(c)d,e:f;g<h>i[j\\k]l@example.com";

        assertFalse(validator.validate(emailAddress));
    }

    @Test
    void testLocalTooLong() {
        emailAddress = "1234567890123456789012345678901234567890123456789012345678901234+x@example.com";

        assertFalse(validator.validate(emailAddress));
    }

    @Test
    void testUnderscoreInDomain() {
        emailAddress = "i_like_underscore@but_its_not_allowed_in_this_part.example.com";

        assertFalse(validator.validate(emailAddress));
    }

    @Test
    void testAdjacentPeriods() {
        emailAddress = "John..Doe@example.com";

        assertFalse(validator.validate(emailAddress));
    }

    @Test
    void testBeginsWithDot() {
        emailAddress = ".John.Doe@example.com";

        assertFalse(validator.validate(emailAddress));
    }

    @Test
    void testEndsWithDot() {
        emailAddress = "John.Doe.@example.com";

        assertFalse(validator.validate(emailAddress));
    }

    @AfterEach
    void tearDown() {
        emailAddress = null;
        validator = null;
    }
}
