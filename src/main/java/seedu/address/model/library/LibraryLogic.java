package seedu.address.model.library;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import seedu.address.model.book.Book;

public class LibraryLogic {

    public final String FILE_PATH = "./library.txt";
    String filePath;
    ArrayList<Book> loadedBooks;

    /**
     * Constructs a Storage object with the given file path.
     *
     * @param filePath the file path where books are stored
     */
    public LibraryLogic(String filePath) {
        this.filePath = filePath;
        this.loadedBooks = new ArrayList<>();
    }

    public LibraryLogic() {
        this.loadedBooks = new ArrayList<>();
        this.filePath = "./library.txt";
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
        File file = new File(FILE_PATH);
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

    public void loadBooksFromFile() {
        File file = new File(FILE_PATH);
        createFileIfNotExists();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (checkValidBook(line.trim())) {
                    // todo throw an exception here
                    System.out.println("Error found: Bad input");
                }
                Book currentBook = new Book(line.trim());
                loadedBooks.add(currentBook);
            }
        } catch (IOException e) {
            // todo throw an exception here
            System.err.println("Error loading book(s) from file: " + e.getMessage());
        }
    }

    /**
     * Loads books from the file.
     *
     * @return the list of books loaded from the file
     */
    public ArrayList<Book> getAvailableBooksArrayList() {
        this.loadBooksFromFile();
        return loadedBooks;
    }

    /**
     * Saves books to the file.
     *
     * @param library the list of books to be saved
     * @throws IOException if an I/O error occurs while saving the books
     */
    public void saveBooksToFile(ArrayList<Book> library) throws IOException {
        createFileIfNotExists();
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Book availableBook : library) {
                writer.println(availableBook);
            }
        } catch (IOException e) {
            // todo throw an exception here
            System.err.println("Error saving books to file: " + e.getMessage());
        }
    }
}
