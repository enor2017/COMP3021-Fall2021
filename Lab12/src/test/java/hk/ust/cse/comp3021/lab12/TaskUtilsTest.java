package hk.ust.cse.comp3021.lab12;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TaskUtilsTest {

    private String generateRandomString(final int len) {
        final var random = new Random();

        // https://www.baeldung.com/java-random-string#java8-alphanumeric
        //
        // I want to use Apache Commons Lang, but introducing a dependency for this seems to be overkill...
        return random.ints('0', 'z' + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(len)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private Task.Type getRandomTaskType() {
        final var random = new Random();

        return Task.Type.values()[random.nextInt(0, Task.Type.values().length)];
    }

    private Stream<Task> createEmptyStream() {
        return Stream.empty();
    }

    private Stream<Task> createLimitedRandomStream(final long len) {
        final var sb = Stream.<Task>builder();
        for (long i = 0; i < len; ++i) {
            sb.accept(new Task(generateRandomString(32), getRandomTaskType()));
        }
        return sb.build();
    }

    private Stream<Task> createUnlimitedRandomStream() {
        return Stream.generate(() -> new Task(generateRandomString(32), getRandomTaskType()));
    }

    private Stream<Task> stream;

    @Test
    void testGetDistinctSetOfTags_emptyStream() {
        stream = createEmptyStream();

        final var actual = TaskUtils.getDistinctSetOfTags(stream);
        assertTrue(actual.isEmpty());
    }

    @Test
    void testGetDistinctSetOfTags_noTags() {
        final var tasks = List.of(
                new Task(generateRandomString(16), getRandomTaskType()),
                new Task(generateRandomString(16), getRandomTaskType()),
                new Task(generateRandomString(16), getRandomTaskType()),
                new Task(generateRandomString(16), getRandomTaskType()),
                new Task(generateRandomString(16), getRandomTaskType()),
                new Task(generateRandomString(16), getRandomTaskType())
        );

        stream = tasks.stream();

        final var actual = TaskUtils.getDistinctSetOfTags(stream);
        assertTrue(actual.isEmpty());
    }

    @Test
    void testGetDistinctSetOfTags_collectsAll() {
        final var tasks = List.of(
                new Task(generateRandomString(16), getRandomTaskType()).addTag("a"),
                new Task(generateRandomString(16), getRandomTaskType()).addTag("b"),
                new Task(generateRandomString(16), getRandomTaskType()).addTag("c"),
                new Task(generateRandomString(16), getRandomTaskType()).addTag("d"),
                new Task(generateRandomString(16), getRandomTaskType()).addTag("e"),
                new Task(generateRandomString(16), getRandomTaskType()).addTag("f")
        );
        final var expected = List.of("a", "b", "c", "d", "e", "f");

        stream = tasks.stream();

        final var actual = TaskUtils.getDistinctSetOfTags(stream);
        assertEquals(6, actual.size());
        assertTrue(actual.containsAll(expected));
    }

    @Test
    void testGetDistinctSetOfTags_deduplicates() {
        final var tasks = List.of(
                new Task(generateRandomString(16), getRandomTaskType()).addTag("a"),
                new Task(generateRandomString(16), getRandomTaskType()).addTag("a"),
                new Task(generateRandomString(16), getRandomTaskType()).addTag("a"),
                new Task(generateRandomString(16), getRandomTaskType()).addTag("a"),
                new Task(generateRandomString(16), getRandomTaskType()).addTag("a"),
                new Task(generateRandomString(16), getRandomTaskType()).addTag("a")
        );
        final var expected = List.of("a");

        stream = tasks.stream();

        final var actual = TaskUtils.getDistinctSetOfTags(stream);
        assertEquals(1, actual.size());
        assertTrue(actual.containsAll(expected));
    }

    @Test
    void testGetDistinctSetOfTitles_emptyStream() {
        stream = createEmptyStream();

        final var actual = TaskUtils.getDistinctSetOfTitles(stream);
        assertTrue(actual.isEmpty());
    }

    @Test
    void testGetDistinctSetOfTitles_noDuplicates() {
        final var tasks = List.of(
                new Task("Task 1", Task.Type.CODING),
                new Task("Task 1", Task.Type.READING)
        );

        stream = tasks.stream();

        final var actual = TaskUtils.getDistinctSetOfTitles(stream);
        assertEquals(1, actual.size());
        assertTrue(actual.contains("Task 1"));
    }

    @Test
    void testGetDistinctSetOfTitles_duplicates() {
        final var tasks = List.of(
                new Task("Task 1", Task.Type.CODING),
                new Task("Task 1", Task.Type.READING),
                new Task("Task 2", Task.Type.CODING),
                new Task("Task 2", Task.Type.READING),
                new Task("Task 3", Task.Type.CODING),
                new Task("Task 3", Task.Type.READING)
        );

        stream = tasks.stream();

        final var actual = TaskUtils.getDistinctSetOfTitles(stream);
        assertEquals(3, actual.size());
        assertTrue(actual.contains("Task 1"));
        assertTrue(actual.contains("Task 2"));
        assertTrue(actual.contains("Task 3"));
    }

    @Test
    void testGetFirstTasks_emptyStream() {
        stream = createEmptyStream();

        final var actual = TaskUtils.getFirstTasks(stream, 10);
        assertTrue(actual.isEmpty());
    }

    @Test
    void testGetFirstTasks_streamSmallerThanLength() {
        stream = createLimitedRandomStream(5);

        final var actual = TaskUtils.getFirstTasks(stream, 10);
        assertEquals(5, actual.size());
    }

    @Test
    @Timeout(value = 2)
    void testGetFirstTasks_unlimitedStream() {
        stream = createUnlimitedRandomStream();

        final var actual = TaskUtils.getFirstTasks(stream, 10);
        assertEquals(10, actual.size());
    }

    @Test
    void testFilterTasksByCustomPredicateAndTakeSome_emptyStream() {
        stream = createEmptyStream();

        final var actual = TaskUtils.filterTasksByCustomPredicateAndTakeSome(stream, t -> true, 1);
        assertTrue(actual.isEmpty());
    }

    @Test
    void testFilterTasksByCustomPredicateAndTakeSome_limitedStreamWithTruePredicate() {
        final var list = createLimitedRandomStream(20).toList();

        final var actual = TaskUtils.filterTasksByCustomPredicateAndTakeSome(list.stream(), t -> true, 10);
        assertEquals(10, actual.size());
        for (int i = 0; i < 10; ++i) {
            assertEquals(list.get(i), actual.get(i));
        }
    }

    @Test
    void testFilterTasksByCustomPredicateAndTakeSome_limitedStreamWithFalsePredicate() {
        final var list = createLimitedRandomStream(20).toList();

        final var actual = TaskUtils.filterTasksByCustomPredicateAndTakeSome(list.stream(), t -> false, 10);
        assertTrue(actual.isEmpty());
    }

    @Test
    @Timeout(value = 2)
    void testFilterTasksByCustomPredicateAndTakeSome_unlimitedStreamWithTruePredicate() {
        final var firstElems = createLimitedRandomStream(10).toList();
        final var stream = Stream.concat(firstElems.stream(), createUnlimitedRandomStream());

        final var actual = TaskUtils.filterTasksByCustomPredicateAndTakeSome(stream, t -> true, 10);
        assertEquals(10, actual.size());
        for (int i = 0; i < 10; ++i) {
            assertEquals(firstElems.get(i), actual.get(i));
        }
    }

    @Test
    void testCountTasksWithEmptyDesc_emptyStream() {
        stream = createEmptyStream();

        final var actual = TaskUtils.countTasksWithEmptyDesc(stream);
        assertEquals(0, actual);
    }

    @Test
    void testCountTasksWithEmptyDesc_nonEmptyStreamWithBlankDescriptions() {
        final var blankDescTasks = List.of(
                new Task(generateRandomString(16), Task.Type.WRITING),
                new Task(generateRandomString(16), Task.Type.WRITING),
                new Task(generateRandomString(16), Task.Type.WRITING),
                new Task(generateRandomString(16), Task.Type.WRITING)
        );

        stream = blankDescTasks.stream();

        final var actual = TaskUtils.countTasksWithEmptyDesc(stream);
        assertEquals(4, actual);
    }

    @Test
    void testCountTasksWithEmptyDesc_nonEmptyStreamWithSomeBlankDescriptions() {
        final var tasks = List.of(
                new Task(generateRandomString(16), Task.Type.WRITING),
                new Task(generateRandomString(16), generateRandomString(32), Task.Type.WRITING),
                new Task(generateRandomString(16), Task.Type.WRITING),
                new Task(generateRandomString(16), generateRandomString(32), Task.Type.WRITING)
        );

        stream = tasks.stream();

        final var actual = TaskUtils.countTasksWithEmptyDesc(stream);
        assertEquals(2, actual);
    }

    @Test
    void testCountTasksWithEmptyDesc_nonEmptyStreamWithNoBlankDescriptions() {
        final var tasks = List.of(
                new Task(generateRandomString(16), generateRandomString(32), Task.Type.WRITING),
                new Task(generateRandomString(16), generateRandomString(32), Task.Type.CODING),
                new Task(generateRandomString(16), generateRandomString(32), Task.Type.WRITING),
                new Task(generateRandomString(16), generateRandomString(32), Task.Type.READING)
        );

        stream = tasks.stream();

        final var actual = TaskUtils.countTasksWithEmptyDesc(stream);
        assertEquals(0, actual);
    }

    @Test
    void testAreAllSpecificTypedTaskCreatedAfterTime_emptyStream() {
        stream = createEmptyStream();

        final var actual = TaskUtils.areAllSpecificTypedTaskCreatedAfterTime(stream, Task.Type.CODING, Instant.MIN);
        assertTrue(actual);
    }

    @Test
    void testAreAllSpecificTypedTaskCreatedAfterTime_streamContainsNoMatchingTaskTypes() {
        final var tasks = List.of(
                new Task(generateRandomString(16), "", Task.Type.READING, Instant.MIN),
                new Task(generateRandomString(16), "", Task.Type.WRITING, Instant.MIN)
        );

        stream = tasks.stream();

        final var actual = TaskUtils.areAllSpecificTypedTaskCreatedAfterTime(stream, Task.Type.CODING, Instant.EPOCH);
        assertTrue(actual);
    }

    @Test
    void testAreAllSpecificTypedTaskCreatedAfterTime_streamContainsSameTypedTasksMatchingPredicate() {
        final var tasks = List.of(
                new Task(generateRandomString(16), "", Task.Type.CODING, Instant.MAX),
                new Task(generateRandomString(16), "", Task.Type.CODING, Instant.MAX),
                new Task(generateRandomString(16), "", Task.Type.CODING, Instant.MAX),
                new Task(generateRandomString(16), "", Task.Type.CODING, Instant.MAX),
                new Task(generateRandomString(16), "", Task.Type.CODING, Instant.MAX)
        );

        stream = tasks.stream();

        final var actual = TaskUtils.areAllSpecificTypedTaskCreatedAfterTime(stream, Task.Type.CODING, Instant.EPOCH);
        assertTrue(actual);
    }

    @Test
    void testAreAllSpecificTypedTaskCreatedAfterTime_streamContainsSameTypedTasksNotMatchingPredicate() {
        final var tasks = List.of(
                new Task(generateRandomString(16), "", Task.Type.CODING, Instant.MAX),
                new Task(generateRandomString(16), "", Task.Type.CODING, Instant.MAX),
                new Task(generateRandomString(16), "", Task.Type.CODING, Instant.MIN),
                new Task(generateRandomString(16), "", Task.Type.CODING, Instant.MAX),
                new Task(generateRandomString(16), "", Task.Type.CODING, Instant.MAX)
        );

        stream = tasks.stream();

        final var actual = TaskUtils.areAllSpecificTypedTaskCreatedAfterTime(stream, Task.Type.CODING, Instant.EPOCH);
        assertFalse(actual);
    }

    @Test
    void testAreAllSpecificTypedTaskCreatedAfterTime_streamContainsDifferentTypedTasksMatchingPredicate() {
        final var tasks = List.of(
                new Task(generateRandomString(16), "", Task.Type.CODING, Instant.MAX),
                new Task(generateRandomString(16), "", Task.Type.READING, Instant.MIN),
                new Task(generateRandomString(16), "", Task.Type.CODING, Instant.MAX),
                new Task(generateRandomString(16), "", Task.Type.WRITING, Instant.MIN),
                new Task(generateRandomString(16), "", Task.Type.CODING, Instant.MAX)
        );

        stream = tasks.stream();

        final var actual = TaskUtils.areAllSpecificTypedTaskCreatedAfterTime(stream, Task.Type.CODING, Instant.EPOCH);
        assertTrue(actual);
    }

    @Test
    void testAreAllSpecificTypedTaskCreatedAfterTime_streamContainsDifferentTypedTasksNotMatchingPredicate() {
        final var tasks = List.of(
                new Task(generateRandomString(16), "", Task.Type.CODING, Instant.MAX),
                new Task(generateRandomString(16), "", Task.Type.READING, Instant.MIN),
                new Task(generateRandomString(16), "", Task.Type.CODING, Instant.MIN),
                new Task(generateRandomString(16), "", Task.Type.WRITING, Instant.MIN),
                new Task(generateRandomString(16), "", Task.Type.CODING, Instant.MAX)
        );

        stream = tasks.stream();

        final var actual = TaskUtils.areAllSpecificTypedTaskCreatedAfterTime(stream, Task.Type.CODING, Instant.EPOCH);
        assertFalse(actual);
    }

    @Test
    void testGetFirstTaskWithTag_emptyStream() {
        stream = createEmptyStream();

        final var actual = TaskUtils.getFirstTaskWithTag(stream, "a");
        assertNull(actual);
    }

    @Test
    void testGetFirstTaskWithTag_zeroTaskWithMatchingTag() {
        final var tasks = List.of(
                new Task(generateRandomString(16), Task.Type.CODING),
                new Task(generateRandomString(16), Task.Type.READING),
                new Task(generateRandomString(16), Task.Type.CODING),
                new Task(generateRandomString(16), Task.Type.WRITING),
                new Task(generateRandomString(16), Task.Type.CODING)
        );

        stream = tasks.stream();

        final var actual = TaskUtils.getFirstTaskWithTag(stream, "a");
        assertNull(actual);
    }

    @Test
    void testGetFirstTaskWithTag_someTaskWithMatchingTag() {
        final var tasks = List.of(
                new Task(generateRandomString(16), Task.Type.CODING),
                new Task(generateRandomString(16), Task.Type.READING),
                new Task(generateRandomString(16), Task.Type.CODING).addTag("a"),
                new Task(generateRandomString(16), Task.Type.WRITING),
                new Task(generateRandomString(16), Task.Type.CODING)
        );

        stream = tasks.stream();

        final var actual = TaskUtils.getFirstTaskWithTag(stream, "a");
        assertNotNull(actual);
        assertSame(tasks.get(2), actual);
    }

    @Test
    void testGetFirstTaskWithTag_allTaskWithMatchingTag() {
        final var tasks = List.of(
                new Task(generateRandomString(16), Task.Type.CODING).addTag("a"),
                new Task(generateRandomString(16), Task.Type.READING).addTag("a"),
                new Task(generateRandomString(16), Task.Type.CODING).addTag("a"),
                new Task(generateRandomString(16), Task.Type.WRITING).addTag("a"),
                new Task(generateRandomString(16), Task.Type.CODING).addTag("a")
        );

        stream = tasks.stream();

        final var actual = TaskUtils.getFirstTaskWithTag(stream, "a");
        assertNotNull(actual);
        assertSame(tasks.get(0), actual);
    }

    @Test
    void testGetOldestTask_emptyStream() {
        stream = createEmptyStream();

        final var actual = TaskUtils.getOldestTask(stream);
        assertNull(actual);
    }

    @Test
    void testGetOldestTask_oldestTaskFirst() {
        final var tasks = List.of(
                new Task(generateRandomString(16), "", Task.Type.CODING, Instant.MIN),
                new Task(generateRandomString(16), "", Task.Type.CODING, Instant.EPOCH),
                new Task(generateRandomString(16), "", Task.Type.CODING, Instant.EPOCH),
                new Task(generateRandomString(16), "", Task.Type.CODING, Instant.EPOCH),
                new Task(generateRandomString(16), "", Task.Type.CODING, Instant.EPOCH)
        );

        stream = tasks.stream();

        final var actual = TaskUtils.getOldestTask(stream);
        assertNotNull(actual);
        assertSame(tasks.get(0), actual);
    }

    @Test
    void testGetOldestTask_oldestTaskLast() {
        final var tasks = List.of(
                new Task(generateRandomString(16), "", Task.Type.CODING, Instant.EPOCH),
                new Task(generateRandomString(16), "", Task.Type.CODING, Instant.EPOCH),
                new Task(generateRandomString(16), "", Task.Type.CODING, Instant.EPOCH),
                new Task(generateRandomString(16), "", Task.Type.CODING, Instant.EPOCH),
                new Task(generateRandomString(16), "", Task.Type.CODING, Instant.MIN)
        );

        stream = tasks.stream();

        final var actual = TaskUtils.getOldestTask(stream);
        assertNotNull(actual);
        assertSame(tasks.get(4), actual);
    }

    @Test
    void testSortTasksByDescriptionLengthDesc_emptyStream() {
        stream = createEmptyStream();

        final var actual = TaskUtils.sortTasksByDescriptionLengthDesc(stream);
        assertTrue(actual.isEmpty());
    }

    @Test
    void testSortTasksByDescriptionLengthDesc_alreadySorted() {
        final var tasks = List.of(
                new Task(generateRandomString(16), "abc", getRandomTaskType()),
                new Task(generateRandomString(16), "ab", getRandomTaskType()),
                new Task(generateRandomString(16), "a", getRandomTaskType()),
                new Task(generateRandomString(16), "", getRandomTaskType())
        );

        stream = tasks.stream();

        final var actual = TaskUtils.sortTasksByDescriptionLengthDesc(stream);
        assertEquals(tasks.size(), actual.size());
        for (int i = 0; i < tasks.size(); ++i) {
            assertSame(tasks.get(i), actual.get(i));
        }
    }

    @Test
    void testSortTasksByDescriptionLengthDesc_revSorted() {
        final var tasks = List.of(
                new Task(generateRandomString(16), "", getRandomTaskType()),
                new Task(generateRandomString(16), "a", getRandomTaskType()),
                new Task(generateRandomString(16), "ab", getRandomTaskType()),
                new Task(generateRandomString(16), "abc", getRandomTaskType())
        );

        stream = tasks.stream();

        final var actual = TaskUtils.sortTasksByDescriptionLengthDesc(stream);
        assertEquals(tasks.size(), actual.size());
        for (int i = 0; i < tasks.size(); ++i) {
            assertSame(tasks.get(tasks.size() - i - 1), actual.get(i));
        }
    }

    @Test
    void testAssociateByUUID_emptyStream() {
        stream = createEmptyStream();

        final var actual = TaskUtils.associateByUUID(stream);
        assertTrue(actual.isEmpty());
    }

    @Test
    void testAssociateByUUID_nonEmptyStream() {
        final var tasks = createLimitedRandomStream(20).toList();

        stream = tasks.stream();

        final var actual = TaskUtils.associateByUUID(stream);
        assertEquals(tasks.size(), actual.size());
        for (final var t : tasks) {
            assertSame(t, actual.get(t.getId()));
        }
    }

    @Test
    void testAssociateTagsWithTask() {
        final var t1 = new Task(generateRandomString(16), getRandomTaskType()).addTag("a");
        final var t2 = new Task(generateRandomString(16), getRandomTaskType()).addTag("b");
        final var t3 = new Task(generateRandomString(16), getRandomTaskType()).addTag("a", "b");

        stream = Stream.of(t1, t2, t3);

        final var actual = TaskUtils.associateTagsWithTask(stream);

        assertTrue(actual.containsKey("a"));
        assertTrue(actual.containsKey("b"));

        final var keyA = actual.get("a");
        assertEquals(2, keyA.size());
        assertTrue(keyA.contains(t1));
        assertTrue(keyA.contains(t3));

        final var keyB = actual.get("b");
        assertEquals(2, keyB.size());
        assertTrue(keyB.contains(t2));
        assertTrue(keyB.contains(t3));
    }

    @AfterEach
    void tearDown() {
        stream = null;
    }
}
