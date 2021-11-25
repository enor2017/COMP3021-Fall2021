package hk.ust.cse.comp3021.lab12;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskUtils {

    /**
     * Get all unique tags from all tasks in the stream.
     *
     * <p>
     * For instance, given three tasks with the tags of <code>{"a"}</code>, <code>{"b"}</code>, and
     * <code>{"a", "b"}</code>, this method will return a {@link Set} containing <code>{"a", "b"}</code>.
     * </p>
     * <p>
     * <b>Hint:</b> {@link Stream#flatMap(Function)}, {@link Stream#distinct()}, {@link Collectors#toUnmodifiableSet()}.
     * </p>
     *
     * @param stream The input stream.
     * @return A {@link Set} of {@link String} containing all distinct tags in all tasks.
     */
    @NotNull
    public static Set<String> getDistinctSetOfTags(@NotNull final Stream<Task> stream) {
        // TODO
        return Collections.emptySet();
    }

    /**
     * Get all unique task names from all tasks in the stream.
     *
     * <p>
     * For instance, give three tasks with names <code>Task 1</code>, <code>Task 1</code>, <code>Task 2</code>, this
     * method will return a {@link Set} containing <code>{"Task 1", "Task 2"}</code>
     * </p>
     * <p>
     * <b>Hint:</b> {@link Stream#map(Function)}, {@link Stream#distinct()}, {@link Collectors#toUnmodifiableSet()}.
     * </p>
     *
     * @param stream The input stream.
     * @return A {@link Set} of {@link String} containing all distinct task names of all tasks.
     */
    @NotNull
    public static Set<String> getDistinctSetOfTitles(@NotNull final Stream<Task> stream) {
        // TODO
        return Collections.emptySet();
    }

    /**
     * Returns the first <i>N</i> tasks from the stream.
     *
     * <p>
     * <b>Hint:</b> {@link Stream#limit(long)}, {@link Stream#toList()}.
     * </p>
     *
     * @param stream The input stream.
     * @param count The number of tasks to obtain from the stream.
     * @return A {@link List} of {@link Task} containing the first <code>count</code> tasks.
     */
    @NotNull
    public static List<Task> getFirstTasks(@NotNull final Stream<Task> stream, final long count) {
        // TODO
        return Collections.emptyList();
    }

    /**
     * Filters tasks by an input predicate, and take up to a specified amount of filtered tasks.
     *
     * <p>
     * <b>Hint:</b> {@link Stream#filter(Predicate)}, {@link Stream#limit(long)}, {@link Stream#toList()}.
     * </p>
     *
     * @param stream The input stream.
     * @param predicate The predicate of which, if a {@link Task} evaluates to {@code true}, should be kept in the
     *                  stream.
     * @param count The number of elements matching the given predicate to take from the stream.
     * @return A {@link List} of {@link Task} matching the given predicate, containing up to {@code count} number of
     * elements.
     */
    @NotNull
    public static List<Task> filterTasksByCustomPredicateAndTakeSome(
            @NotNull final Stream<Task> stream,
            @NotNull final Predicate<Task> predicate,
            final long count
    ) {
        // TODO
        return Collections.emptyList();
    }

    /**
     * Counts the number of tasks with an empty description.
     *
     * <p>
     * <b>Hint:</b> {@link Stream#filter(Predicate)}, {@link Stream#count()}.
     * </p>
     *
     * @param stream The input stream.
     * @return The number of tasks with an empty description.
     */
    public static long countTasksWithEmptyDesc(@NotNull final Stream<Task> stream) {
        // TODO
        return -1;
    }

    /**
     * Checks whether all tasks with the specified {@link Task.Type} are created after the provided time.
     *
     * <p>
     * If there are no tasks of the specified type, return {@code true}.
     * </p>
     * <p>
     * <b>Hint:</b> {@link Stream#filter(Predicate)}, {@link Stream#allMatch(Predicate)}, {@link Instant#isAfter(Instant)}.
     * </p>
     *
     * @param stream The input stream.
     * @param type The type of task to check the creation time.
     * @param minTime The time which all tasks should be created after.
     * @return Whether all tasks with the specified taska are created after {@code minTime}.
     */
    public static boolean areAllSpecificTypedTaskCreatedAfterTime(
            @NotNull final Stream<Task> stream,
            @NotNull final Task.Type type,
            @NotNull final Instant minTime
    ) {
        // TODO
        return false;
    }

    /**
     * Returns first {@link Task} which has the given tag associated.
     *
     * <p>
     * If there are no tasks matching the predicate, return {@code null}.
     * </p>
     * <p>
     * <b>Hint:</b> {@link Stream#filter(Predicate)}, {@link Stream#findAny()}, {@link Optional#orElse(Object)}.
     * </p>
     *
     * @param stream The input stream.
     * @param tag The tag to search for.
     * @return First task from the stream with the given tag, or {@code null} if there are no tasks.
     */
    @Nullable
    public static Task getFirstTaskWithTag(@NotNull final Stream<Task> stream, @NotNull final String tag) {
        // TODO
        return null;
    }

    /**
     * Returns the oldest task in the stream.
     *
     * <p>
     * If there are no tasks in the stream, return {@code null}.
     * </p>
     * <p>
     * <b>Hint:</b> {@link Stream#min(Comparator)}, {@link Comparator#comparing(Function)},
     * {@link Optional#orElse(Object)}.
     * </p>
     *
     * @param stream The input stream.
     * @return The oldest created task in the stream, or {@code null} if there are no tasks.
     */
    @Nullable
    public static Task getOldestTask(@NotNull final Stream<Task> stream) {
        // TODO
        return null;
    }

    /**
     * Sorts the stream based on the length of {@link Task#getDescription()}, in descending order.
     *
     * <p>
     * In other words, tasks with longer description text should appear at the front of the list.
     * </p>
     * <p>
     * <b>Hint:</b> {@link Stream#sorted(Comparator)}, {@link Comparator#comparing(Function)},
     * {@link Comparator#reversed()}, {@link Stream#toList()}.
     * </p>
     *
     * @param stream The input stream.
     * @return A list of all tasks from the stream, sorted by description length in descending order.
     */
    @NotNull
    public static List<Task> sortTasksByDescriptionLengthDesc(@NotNull final Stream<Task> stream) {
        // TODO
        return Collections.emptyList();
    }

    /**
     * Associates the tasks by its UUID, creating a {@link Map} of task ID to its task.
     *
     * <p>
     * Note: A {@link Map} is a data structure implemented using balanced trees. Maps allow accelerated lookup of
     * elements based on a specific <i>key</i> (or <i>index</i>). Each key must uniquely map to one element.
     * </p>
     * <p>
     * In this case, we are creating a Map of {@link UUID}s mapped to {@link Task}s. This means that if we have the UUID
     * of a task, we can lookup a corresponding task faster than using, for instance, a {@link List}.
     * </p>
     * <p><b>Hint:</b> {@link Collectors#toMap(Function, Function)}, {@link Function#identity()}.</p>
     *
     * @param stream The input stream.
     * @return A {@link Map} mapping the {@link UUID} of each task to the {@link Task} instance.
     */
    @NotNull
    public static Map<UUID, Task> associateByUUID(@NotNull final Stream<Task> stream) {
        // TODO
        return Collections.emptyMap();
    }

    /**
     * Associates the tasks by the tag it contains.
     *
     * <p>
     * For instance, given a task with tags {@code a} and {@code b}, the resulting map should have {@code a} and
     * {@code b} as its keys, and the task should exist under both keys.
     * </p>
     * <p>
     * The goal of this method is to create a {@link Map} to quickly lookup all tasks with a given tag.
     * </p>
     * <p>
     * <b>Hint:</b> {@link Stream#reduce(Object, BiFunction, BinaryOperator)}.
     * </p>
     * <p>
     * <b>Note:</b> You are not required to implement this method to complete this lab. However, we still encourage you
     * to try to solve this to learn how {@link Stream#reduce(Object, BiFunction, BinaryOperator)} works.
     * </p>
     *
     * @param stream The input stream.
     * @return A {@link Map} mapping each tag to a {@link List} of {@link Task} containing the tag.
     */
    @NotNull
    public static Map<String, List<Task>> associateTagsWithTask(@NotNull final Stream<Task> stream) {
        // TODO
        return Collections.emptyMap();
    }
}
