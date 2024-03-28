package seedu.address.model.library;

import static seedu.address.commons.util.StringUtil.isInteger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyLibrary;
import seedu.address.model.book.Book;

/**
 * The LibraryLogic Class manages the loading and saving of available books to a txt file.
 */
public class LibraryLogic {
    //TODO Refactor LibraryLogic to LibraryStorage and change file location to under storage package
    private String filePath;
    private ArrayList<Book> availableBooks;
    private Threshold threshold;

    /**
     * Constructs a LibraryLogic object with the given file path.
     *
     * @param filePath the file path where books are stored
     */
    public LibraryLogic(String filePath) {
        this.filePath = filePath;
        this.availableBooks = new ArrayList<>();
        this.threshold = new Threshold();
    }

    /**
     * Constructs a LibraryLogic object with default file path
     */
    public LibraryLogic() {
        this.filePath = "data\\library.txt";
        this.availableBooks = new ArrayList<>();
        this.threshold = new Threshold();
    }

    /**
     * Validates if the book title is present.
     *
     * @param bookTitle the book title of a book
     * @return true if the book title is valid, false otherwise
     */
    private static boolean isValidBook(String bookTitle) {
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
     * Loads threshold books from the file and store it inside the availableBooks.
     */
    public void loadLibraryFromFile() throws DataLoadingException {
        File file = new File(filePath);
        createFileIfNotExists();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            // load first line as threshold
            line = reader.readLine();

            if (line == null) {
                throw new IllegalValueException("Error loading library data from file: Missing data");
            }

            if (!isInteger(line.trim())) {
                throw new IllegalValueException("Error loading threshold from file: Bad threshold input");
            }
            threshold = new Threshold(Integer.parseInt(line));

            // load rest as books
            while ((line = reader.readLine()) != null) {
                if (!isValidBook(line.trim())) {
                    throw new IllegalValueException("Error loading book(s) from file: Bad book input");
                }
                Book currentBook = new Book(line.trim());
                availableBooks.add(currentBook);
            }
        } catch (IOException e) {
            // todo throw an exception here
            System.err.println("Error loading book(s) from file: " + e.getMessage());
        } catch (IllegalValueException ive) {
            throw new DataLoadingException(ive);
        }
    }

    /**
     * Get the loaded available books.
     *
     * @return the list of books loaded from the file
     */
    public ArrayList<Book> getAvailableBooks() {
        return availableBooks;
    }

    public Threshold getThreshold() {
        return threshold;
    }

    public boolean hasNoAvailableBooks() {
        return availableBooks.isEmpty();
    }

    /**
     * Saves threshold and books to the file.
     *
     * @param library the list of books to be saved
     * @throws IOException if an I/O error occurs while saving the books
     */
    public void saveBooksToFile(ReadOnlyLibrary library) throws IOException {
        createFileIfNotExists();
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println(threshold.toString());
            for (Book availableBook : library.getBookList()) {
                writer.println(availableBook);
            }
        } catch (IOException e) {
            // todo throw an exception here
            System.err.println("Error saving books to file: " + e.getMessage());
        }
    }

    public String getLibraryFilePath() {
        return filePath;
    }
}
