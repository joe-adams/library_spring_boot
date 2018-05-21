package hello.model;

import java.util.Objects;

public class Book {
    private Long id;
    private String title;
    private long authorId;

    public Book(String title, long authorId) {
        this.title = title;
        this.authorId = authorId;
    }

    public Book(long id, String title, long authorId) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public long getAuthorId() {
        return authorId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        if (book.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), book.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + getId() +
                ", title='" + getTitle() + "'" +
                "}";
    }
}
