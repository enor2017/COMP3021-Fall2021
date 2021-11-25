# Lab 12: Lambdas

This lab will introduce you to the following topics:

- Lambdas
- Streams, Higher-Order Functions
- Method References

Jump to the [Submission](#submission) section for information on what you need to do.

## Background

Lambdas (or anonymous functions) are first introduced in Java 8, with the goal of allowing developers to write code in a
more functional style.

You may remember that you were taught lambdas in C++, with this weird and clunky syntax:

```cpp
int j = /* ... */;
auto lambda = [&j](int i) {
  return i + j;
};
```

With Java, you can write lambdas in a more concise way:

```java
final int j = /* ... */;
final Function<Integer, Integer> lambda = i -> i + j;
```

However, you are more likely to use lambdas to implement lazily-evaluated functionality, such as event listeners (which
are only triggered on specific events) or in Java's Streams API (where lambdas are only executed on-demand).

### Functional Interface

Lambdas can be used where a variable or parameter expects a *function object*, i.e. an object which implements a 
functional interface.

More specifically, a functional interface is an `interface` which only one abstract method. A good practice is to mark 
these interfaces with the `@FunctionalInterface` annotation.

```java
@FunctionalInterface
interface MyOwnRunnable {
    void run();
}
```

In Java, the following set of interfaces are marked as `FunctionalInterface` (see the full list here 
[here](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/package-summary.html):

- `Consumer<T>`: Used to consume or operate on objects.

  ```java
  public class Printer {
      
      private static final Consumer<Object> printer = o -> System.out.println(o);

      public static void printElemsInList(@NotNull final List<?> list) {
          for (final var e : list) {
              printer.accept(e);
          }
      }
  }
  ```

- `Function<T, R>`: Used to transform objects from a source type to a target type.

  ```java
  public class ListConverter {
  
      private static final Function<String, Integer> stoi = s -> Integer.valueOf(s);

      public static List<Integer> stringsToInts(@NotNull final List<? extends String> list) {
          final var intList = new ArrayList<Integer>(list.size());
          for (final var s : list) {
              intList.add(stoi.apply(s));
          }
          return intList;
      }
  }
  ```

- `Predicate<T>`: Used to check whether an object satisfies a condition.

  ```java
  public class ListFilterer {

      private static final Predicate<Object> isNotNull = o -> o != null;
  
      public static <E> List<E> filterNotNullElems(@NotNull final List<E> list) {
          final var newList = new ArrayList<E>();
          for (final var e : list) {
              if (isNotNull.test(e)) {
                  newList.add(e);
              }
          }
          return newList;
      }
  }
  ```

- `Supplier<T>`: Used to create objects.

  ```java
  public class ListCreator {
  
      private static final Random rand = new Random();
      private static final Supplier<Integer> intGenerator = () -> rand.nextInt();
  
      public static List<Integer> generateRandomInts(final long count) {
          final var newList = new ArrayLList<Integer>(count);
          for (long i = 0; i < count; ++i) {
              newList.add(intGenerator.get());
          }
          return newList;
      }
  }
  ```

### Scoping Rules

- Lambda expressions share the same scope as its enclosing scope. This means that no parameter or variable in the lambda
  can be named as any local variable outside the lambda.

  ```java
  double x = 0.0;
  f(x -> x + x);  // error: variable 'x' already declared in scope
  f(y -> {
      int x = y;  // error: variable 'x' already declared in scope
      y + y
  });
  ```

- Local variables can be accessed in lambdas, but they must be final or effectively final (i.e. the variable is never 
  changed after its declaration).

  *This is to prevent dangling references, which could happen in C++ lambdas.*

  ```java
  double x = 0.0;
  f(y -> x = 3.4);  // error: Variable used in lambda expression should be final or effectively final
  ```

- Accessing and modifying instance or static variables in lambdas are OK.

  ```java
  class MyClass {
      private double x = 0;
      void foo(){
          f(y -> x = 3.4);  // modifying instance var is OK
          f(x -> x + this.x);  // OK; x refers to lambda parameter, this.x refers to MyClass.x
      }
  }
  ```

### Method References

Method references are a syntactical sugar to express a single method. There are four kinds of method references:

- Reference to a static method (`ClassName::staticMethod`)

  ```java
  @FunctionalInterface
  public interface IntBinaryOperator {
      int applyAsInt(int left, int right);
  }

  final IntBinaryOperator compareInts = Integer::compare;
  // which is equivalent to
  final IntBinaryOperator compareInts = (left, right) -> Integer.compare(left, right);
  
  compareInts.applyAsInt(1, 2);
  ```

- Reference to an instance method (`classInstance::instanceMethod`)

  ```java
  @FunctionalInterface
  public interface DoubleSupplier {
      double getAsDouble();
  }
  
  final var i = Integer.valueOf(0);
  final DoubleSupplier ds = i::doubleValue;
  // which is equivalent to
  final DoubleSupplier ds = () -> i.doubleValue();

  ds.getAsDouble();
  ```

- Reference to an instance method of an arbitrary object of a particular type (`ContainingType::instanceMethod`)

  ```java
  public static <T, R> List<R> transformList(@NotNull final List<T> list, @NotNull final Function<T, R> transform) {
      final var newList = new ArrayList<R>(list.size());
      for (final var e : list) {
          newList.add(transform.apply(e));
      }
      return newList;
  }

  final var origList = List.of(0, 1, 2, 3, 4);
  final var origListToString = transformList(origList, Integer::doubleValue);
  // which is equivalent to
  final var origListToString = transformList(origList, i -> i.doubleValue());
  ```

- Reference to a constructor

  ```java
  @FunctionalInterface
  public interface Supplier<T> {
      T get();
  }
  
  final Supplier<String> createNewString = String::new;
  // which is equivalent to
  final Supplier<String> createNewString = () -> new String();

  createNewString.get();
  ```

### Higher-Order Functions
)
Higher-order functions are methods which accepts other methods or lambdas as arguments, or returning methods or lambdas
as its result. For instance, using the `ListFilterer.filterNotNullElems` method from the above example, if we want to 
adapt it to be able to filter a list using a `Predicate` from a parameter, you can do this:

```java

public class ListFilterer {

    public static <E> List<E> filterBy(@NotNull final List<E> list, @NotNull final Predicate<E> predicate) {
        final var newList = new ArrayList<E>();
        for (final var e : list) {
            if (predicate.test(e)) {
                newList.add(e);
            }
        }
        return newList;
    }

    public static void main(String[] args) {
        final var list = List.of(null, 0, 1, 2, 3, 4);

        final var listWithoutNulls = filterBy(list, e -> e != null);
        final var listWithoutZeroOrNulls = filterBy(list, e != null && e != 0);
        final var listWithoutOddNumbers = filterBy(list, e != null && e % 2 != 1);
    }
}
```

The `Stream` API uses higher-order functions extensively. See the next section.

### `java.util.stream.Stream`

Streams are a sequence of elements which can be operated upon. Unlike containers (such as `List<E>`), operations on 
streams are generally pipelined and lazily evaluated to improve the efficiency of operations on the elements of the 
stream.

[This article](https://www.baeldung.com/java-8-streams-introduction) is a good introduction to streams.

In general, there are three stages to a stream:

- Creation

  Streams can be created in one of the following ways:

  - From a collection, e.g. using `List<E>.stream()`
  - From a fixed set of elements, using `Stream.of(T...)` or `Stream.builder()`
  - From a generator lambda, using `Stream.generate(Supplier<T>)` or `Stream.iterate(T, UnaryOperator<T>)`

- Intermediate Operations

  Intermediate operations mutate the elements in the stream or the stream itself. The goal is to convert the stream (or 
  its elements) into the form we want.

  For instance, given a `Stream<Integer>` that we would like to take the first 5 even numbers and convert them to 
  strings, we would do something like this:

  ```java
  stream
    .filter(n -> n % 2 == 0)
    .limit(5)
    .map(n -> n.toString());  // This will become a Stream<String>
  ```

- Terminal Operations

  Terminal operations convert a `Stream` back to some other data type, which depends on what you want to do. In general,
  there are several resultant data types:

  - Containers, by using `Stream.collect`
  - Booleans, by applying a predicate using `allMatch`, `anyMatch`, `noneMatch`
  - Integer, by counting the number of elements using `count`
  - `T`, by finding an element (`findAny` or `findFirst`), or finding the maximum or minimum element (`max` or `min`)
  - `U` (which may not be the same type as the type of element in the `Stream`, `T`), by performing a reduction 
    operation (using `reduce`)

## Submission

You will need to complete all `TODO` in `TaskUtils.java`. The file will be located in `
src/main/java/hk/ust/cse/comp3021/lab12`. Read the Javadoc for information on what each method should do.

After completing the implementation, you may run the test cases by using the provided "Test" configuration. Note that
implementing `TaskUtils.associateTagsWithTask` is optional.

Once you are done, only submit `TaskUtils.java` to CASS. The deadline is 2021-11-30 23:59 HKT.
