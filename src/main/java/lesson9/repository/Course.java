package lesson9.repository;


import java.util.Objects;

public class Course {
    private final int id;
    private static int countId = 0;
    private final String name;

    public Course(String name) {
        this.id = ++countId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return name.equalsIgnoreCase(((Course) o).getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
