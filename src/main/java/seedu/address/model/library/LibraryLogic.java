package seedu.address.model.library;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import seedu.address.model.book.Book;

/**
 * The LibraryLogic Class manages the loading and saving of available books to a txt file.
 */
public class LibraryLogic {

    private String filePath;
    private ArrayList<Book> availableBooks;

    /**
     * Constructs a LibraryLogic object with the given file path.
     *
     * @param filePath the file path where books are stored
     */
    public LibraryLogic(String filePath) {
        this.filePath = filePath;
        this.availableBooks = new ArrayList<>();
    }

    /**
     * Constructs a LibraryLogic object with default file path
     */
    public LibraryLogic() {
        this.filePath = "./library.txt";
        this.availableBooks = new ArrayList<>();
    }

    /**
     * Validates if the start date is before the end date.
     *
     * @param bookTitle the book title of a book
     * @return true if the start date is before the end date, false otherwise
     */
    private static boolean checkValidBook(String bookTitle) {
        if (bookTitle == null || bookTitle == "") {
            return false;
        }
        return true;
    }

    private void createFileIfNotExists() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    // todo return a String instead
                    System.out.println("File created: " + file.getName());
                } else {
                    // todo return a String instead
                    System.out.println("File creation failed.");
                }
            } catch (IOException e) {
                // todo create an exception here
                System.out.println("An error occurred while creating the file.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Loads books from the file and store it inside the availableBooks.
     */
    public void loadBooksFromFile() {
        File file = new File(filePath);
        createFileIfNotExists();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (checkValidBook(line.trim())) {
                    // todo throw an exception here
                    System.out.println("Error found: Bad input");
                }
                Book currentBook = new Book(line.trim());
                availableBooks.add(currentBook);
            }
        } catch (IOException e) {
            // todo throw an exception here
            System.err.println("Error loading book(s) from file: " + e.getMessage());
        }
    }

    /**
     * Get the loaded available books.
     *
     * @return the list of books loaded from the file
     */
    public ArrayList<Book> getAvailableBooksFromFile() {
        this.loadBooksFromFile();
        return availableBooks;
    }

    /**
     * Saves books to the file.
     *
     * @param library the list of books to be saved
     * @throws IOException if an I/O error occurs while saving the books
     */
    public void saveBooksToFile(ArrayList<Book> library) throws IOException {
        createFileIfNotExists();
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Book availableBook : library) {
                writer.println(availableBook);
            }
        } catch (IOException e) {
            // todo throw an exception here
            System.err.println("Error saving books to file: " + e.getMessage());
        }
    }
}
