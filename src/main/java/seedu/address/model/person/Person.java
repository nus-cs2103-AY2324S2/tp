package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.book.Book;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final MeritScore meritScore;
    private final ArrayList<Book> bookList;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Constructs a person without any books borrowed.
     * Every field must be present and not null.
     */

    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.meritScore = new MeritScore(0);
        this.bookList = new ArrayList<>();
    }

    /**
     * Constructs a person with books borrowed.
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address,
                  MeritScore meritScore, ArrayList<Book> bookList, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.meritScore = meritScore;
        this.bookList = bookList;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * @return the merit score of the person
     */
    public MeritScore getMeritScore() {
        return meritScore;
    }

    /**
     * Returns an immutable book list, consisting of books in the person's book list.
     *
     * @return The book list.
     */
    public ArrayList<Book> getBookList() {
        return new ArrayList<>(Collections.unmodifiableList(this.bookList));
    }

    /**
     * Returns an immutable book list, consisting of books in the person's book list
     * without the book passed in.
     *
     * @param book The book to be removed.
     * @return An immutable copy of the person's book list without the book.
     */
    public ArrayList<Book> getBookListWithoutBook(Book book) {
        ArrayList<Book> mutableCopy = new ArrayList<>(this.getBookList());
        mutableCopy.remove(book);
        return new ArrayList<>(Collections.unmodifiableList(mutableCopy));
    }

    /**
     * Returns an immutable book list, consisting of books in the person's book list
     * with an additional book, which is the book passed in.
     *
     * @param book The book to be added.
     * @return An immutable copy of the person's book list with the new book.
     */
    public ArrayList<Book> getBookListWithNewBook(Book book) {
        ArrayList<Book> mutableCopy = new ArrayList<>(this.getBookList());
        mutableCopy.add(book);
        return new ArrayList<>(Collections.unmodifiableList(mutableCopy));
    }

    public String getBookListToString() {
        this.bookList.sort(Comparator.comparing(book -> book.bookTitle));
        String result = "";
        for (int i = 0; i < bookList.size(); i++) {
            result += this.bookList.get(i).bookTitle.toString();
            if (i != bookList.size() - 1) {
                result += "\n";
            }
        }
        return result;
    }

    public String getBookListToStringWithIndex() {
        this.bookList.sort(Comparator.comparing(book -> book.bookTitle));
        String result = "";
        for (int i = 0; i < bookList.size(); i++) {
            result += (i + 1) + ". " + this.bookList.get(i).bookTitle.toString();
            if (i != bookList.size() - 1) {
                result += "\n";
            }
        }
        return result;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags)
                && meritScore.equals(otherPerson.meritScore)
                && bookList.equals(otherPerson.bookList);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, meritScore, bookList, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .add("Merit score", meritScore)
                .add("book borrowed", bookList)
                .toString();
    }

}
