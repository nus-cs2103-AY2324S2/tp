package seedu.address.model.exam;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.exam.exceptions.DuplicateExamException;
import seedu.address.model.exam.exceptions.ExamNotFoundException;

/**
 * A list of exams that enforces uniqueness between its elements and does not allow nulls.
 */
public class UniqueExamList implements Iterable<Exam> {

    private final ObservableList<Exam> internalList = FXCollections.observableArrayList();
    private final ObservableList<Exam> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent exam as the given argument.
     */
    public boolean contains(Exam toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameExam);
    }

    /**
     * Adds an exam to the list.
     * The exam must not already exist in the list.
     */
    public void add(Exam toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateExamException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the exam {@code target} in the list with {@code editedExam}.
     */
    public void setExam(Exam target, Exam editedExam) {
        requireAllNonNull(target, editedExam);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ExamNotFoundException();
        }

        if (!target.isSameExam(editedExam) && contains(editedExam)) {
            throw new DuplicateExamException();
        }

        internalList.set(index, editedExam);
    }

    /**
     * Removes the equivalent exam from the list.
     * The exam must exist in the list.
     */
    public void remove(Exam toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ExamNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code exams}.
     */
    public void setExams(UniqueExamList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    public void setExams(List<Exam> exams) {
        requireAllNonNull(exams);
        if (!examsAreUnique(exams)) {
            throw new DuplicateExamException();
        }

        internalList.setAll(exams);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Exam> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Exam> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UniqueExamList)) {
            return false;
        }

        UniqueExamList otherUniqueExamList = (UniqueExamList) other;
        return internalList.equals(otherUniqueExamList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if the list contains only unique exams.
     */
    private boolean examsAreUnique(List<Exam> exams) {
        for (int i = 0; i < exams.size() - 1; i++) {
            for (int j = i + 1; j < exams.size(); j++) {
                if (exams.get(i).isSameExam(exams.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
