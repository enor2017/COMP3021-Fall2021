package hk.ust.cse.comp3021.lab12;

import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.*;

public class Task {

    public enum Type {
        READING, CODING, WRITING
    }

    @NotNull
    private final UUID id;
    @NotNull
    private final String title;
    @NotNull
    private final String description;
    @NotNull
    private final Type type;
    @NotNull
    private final Instant createdOn;
    @NotNull
    private final Set<String> tags = new HashSet<>();

    public Task(@NotNull final String title, @NotNull final Type type) {
        this(title, "", type);
    }

    public Task(@NotNull final String title, @NotNull final String description, @NotNull final Type type) {
        this(title, description, type, Instant.now());
    }

    public Task(@NotNull final String title,
                @NotNull final String description,
                @NotNull final Type type,
                @NotNull final Instant createdOn) {
        this(UUID.randomUUID(), title, description, type, createdOn);
    }

    Task(@NotNull final UUID id,
                @NotNull final String title,
                @NotNull final String description,
                @NotNull final Type type,
                @NotNull final Instant createdOn) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.createdOn = createdOn;
    }

    @NotNull
    public UUID getId() {
        return id;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    @NotNull
    public String getDescription() {
        return description;
    }

    @NotNull
    public Type getType() {
        return type;
    }

    @NotNull
    public Instant getCreatedOn() {
        return createdOn;
    }

    @NotNull
    public Task addTag(@NotNull final String... tag) {
        this.tags.addAll(Arrays.asList(tag));
        return this;
    }

    @NotNull
    public Set<String> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    @Override
    public String toString() {
        return "Task(id=" +
                getId() +
                ",title=" +
                getTitle() +
                ",type=" +
                getType() +
                ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(title, task.title) && Objects.equals(type, task.type);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
