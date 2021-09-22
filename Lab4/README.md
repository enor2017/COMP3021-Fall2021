# Lab 4: String Manipulation

This lab will introduce you to the following topics:

- Creating and manipulating strings
- Common string operations
- `StringBuilder`
- `Character`

Jump to the [Submission](#submission) section for information on what you need to do.

## Creating Strings

To create a `String`, the most common way is to just declare it:

```java
final String s = "This is a string!";
```

Note that due to how strings are immutable and interned, creating the same string multiple times using the previous 
method will only create a single instance.

```java
final String s1 = "a";
final String s2 = "a";

assert s1.equals(s2);   // true
assert s1 == s2;        // true
```

To create explicit copies of string, use the `String` constructor:

```java
final String s1 = "a";
final String s2 = new String("a");

assert s1.equals(s2);   // true
assert s1 == s2;        // false
```

However, this is usually discouraged, as string interning provides the benefit of both improving performance and 
reducing memory usage. See [the extra section](#extra-how-does-string-interning-improve-performance) for more 
information.

## Manipulating Strings

There are many operations that can manipulate a string. A small subset can be found below:

- `String.toLowerCase`: Converts all characters in the string to lowercase
- `String.toUpperCase`" Converts all characters in the string to uppercase
- `String.concat` (or `+`): Concatenates two strings together
- `String.length`: Returns the length of the string
- `String.substring`: Returns a portion of the original string
- `String.indexOf`: Returns the index of the first character matching the specified character
- `String.equals`: Compares the contents of two strings
- `String.charAt`: Returns the character at the specified offset
- `String.contains`: Finds whether the specified substring exists in this string

You can find the documentation for `java.lang.String` 
[here](https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/lang/String.html).

## `StringBuilder`

As mentioned in the lectures, string concatenation using the `+` operator creates a lot of temporary strings during the
concatenation process.

Consider the following example:

```java
final String s = "Hello" + " " + "World" + "!";
```

This will create 7 `String` objects:

- `"Hello"`
- `" "`
- `"World"`
- `"!"`
- `"Hello "`
- `"Hello World"`
- `"Hello World!`

This is obviously not good for memory usage, as we have created 2 redundant string objects.

The solution to this is to use a `StringBuffer` instead. A `StringBuffer` works by using an internal buffer (`byte[]`) 
to store intermediate results during the building of a `String`.

Using the same example as before:

```java
final StringBuilder sb = new StringBuilder();
sb.append("Hello");
sb.append(" ");
sb.append("World");
sb.append("!");
final String s = sb.toString();
```

This will instead create 5 `String` objects.

- `"Hello"`
- `" "`
- `"World"`
- `"!"`
- `"Hello World!`

## `Character`

By this point, you should be familiar with the difference between primitive types and their wrapper counterparts. You 
will also have known that all wrapper types provide `static` methods to implement helpful functionality which are 
otherwise not provided.

This is also the case for the `Character` class, which is the wrapper counterpart to the `char` primitive type. Since
`char` represents a character, the corresponding static methods in `Character` are all utilities related to characters.

A small subset of useful methods are listed below. The full list can be found
[here](https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/lang/Character.html).

- `Character.isAlphabetic`: Checks whether a `char` is an alphabet
- `Character.isDigit`: Checks whether a `char` is a digit
- `Character.isIdeographic`: Checks whether a `char` is a Chinese/Japanese/Korean/Vietnamese character
- `Character.isLowerCase`: Checks whether a `char` is in lower case
- `Character.isUpperCase`: Checks whether a `char` is in lower case
- `Character.isWhitespace`: Checks whether a `char` represents a whitespace

## Extra: How does String Interning improve performance?

As mentioned in the [Creating Strings](#creating-strings) section, String interning improves performance and reduces
memory usage. But how?

Reducing memory usage is intuitive. When you do not need to create new `String` objects representing the same string
(i.e. you have one `String` instance per string, you save memory.

As for improving performance, consider how a string comparison is usually done:

```java
public static void stringEquals(final String lhs, final String rhs) {
    if (lhs.length() != rhs.length()) {
        return false;
    }

    for (int i = 0; i < lhs.length(); ++i) {
        if (lhs.codePointAt(i) != rhs.codePointAt(i)) {
            return false;
        }
    }

    return true;
}
```

A key observation is that given two strings of the same length, longer strings will slow this comparison down, because
the number of comparisons increases linearly to the length of the String. (For those of you who know algorithmic 
complexities, this is `O(n)`, where `n` is the length of the `String`)

However, with string interning , we suddenly have a shortcut to performing this comparison:

```java
public static void stringEquals(final String lhs, final String rhs) {
    if (lhs == rhs) {
        return true;
    }

    // original implementation below...
}
```

This works because string interning ensures that all strings with the same character-sequence will be the same instance,
meaning that if both instances are the same, the content of the string must be the same, and thus they must be equal.

*Note that the original implementation still needs to be included, as Java still allows `String` instances to be created
but not interned.*

## Extra: "Code Point" vs "Char"

In both the `String` and `Character` classes, you will often see two variants of methods: Ones accepting a `char ch` and
ones accepting an `int codePoint`. What is the difference?

You may recall that in Java, `char` are two bytes and stores characters in UTF-16. However, some characters in Unicode
(for example most emojis) require 3 bytes to be represented. In order to represent these 3-byte variation of Unicode
characters, Java allows two ways of representing Unicode characters (and thus two ways of accessing Strings).

- `char` and `Character`: These will always be 2-bytes, so a 3-byte Unicode character will require 2 `char`s to be
  properly represented
- `int`: These will always be 4-bytes, so a 3-byte Unicode character will fit in the `int` with no problems

For instance, for the `String` `"😂" :

- `"😂".charAt(0) == '\uD83D'`
- `"😂".charAt(1) == '\uDE02'`
- `"😂".codePointAt(0) == 128514` (which is the UTF-32 encoding for this emoji)

## Submission

You will need to complete all `TODO` in `StringUtils.java`. The file will be located in `
src/main/java/hk/ust/cse/comp3021/lab4`. Read the Javadoc for information on what each method should do.

After completing the implementation, you may run the test cases by using the provided "Test" configuration. Note that
all tests will be testing the correctness of the different `Validator` classes, which has been implemented for you but 
utilizes your implementation of the `StringUtils` methods.

Once you are done, only submit `StringUtils.java` to CASS. The deadline is 2021-09-30 23:59 HKT.
