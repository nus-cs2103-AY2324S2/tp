package seedu.address.model.interview;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.interview.exceptions.DuplicateInterviewException;
import seedu.address.model.interview.exceptions.InterviewNotFoundException;

import java.util.Iterator;
import java.util.List;

/**
 * A list of interviews that enforces uniqueness between its elements and does not allow nulls.
 * An interview is considered unique by comparing using {@code Interview#isSameInterview(Interview)}. As such, adding and updating of
 * interviews uses Interview#isSameInterview(Interview) for equality so as to ensure that the interview being added or updated is
 * unique in terms of identity in the UniqueInterviewList. However, the removal of an interview uses Interview#equals(Object) so
 * as to ensure that the interview with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Interview#isSameInterview(Interview)
 */
public class UniqueInterviewList implements Iterable<Interview> {

    private final ObservableList<Interview> internalList = FXCollections.observableArrayList();
    private final ObservableList<Interview> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent interview as the given argument.
     */
    public boolean contains(Interview toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameInterview);
    }

    /**
     * Adds an interview to the list.
     * The interview must not already exist in the list.
     */
    public void add(Interview toAdd) {
        requireNonNull(toAdd);
//        if (contains(toAdd)) {
//            throw new DuplicateInterviewException();
//        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the interview {@code target} in the list with {@code editedInterview}.
     * {@code target} must exist in the list.
     * The interview identity of {@code editedInterview} must not be the same as another existing interview in the list.
     */
    public void setInterview(Interview target, Interview editedInterview) throws DuplicateInterviewException, InterviewNotFoundException {
        requireAllNonNull(target, editedInterview);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new InterviewNotFoundException();
        }

        if (!target.isSameInterview(editedInterview) && contains(editedInterview)) {
            throw new DuplicateInterviewException();
        }

        internalList.set(index, editedInterview);
    }

    /**
     * Removes the equivalent interview from the list.
     * The interview must exist in the list.
     */
    public void remove(Interview toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new InterviewNotFoundException();
        }
    }

    public void setInterviews(UniqueInterviewList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code interviews}.
     * {@code interviews} must not contain duplicate interviews.
     */
    public void setInterviews(List<Interview> interviews) throws DuplicateInterviewException {
        requireAllNonNull(interviews);
        if (!interviewsAreUnique(interviews)) {
            throw new DuplicateInterviewException();
        }

        internalList.setAll(interviews);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Interview> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Interview> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof UniqueInterviewList
                && internalList.equals(((UniqueInterviewList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code interviews} contains only unique interviews.
     */
    private boolean interviewsAreUnique(List<Interview> interviews) {
        for (int i = 0; i < interviews.size() - 1; i++) {
            for (int j = i + 1; j < interviews.size(); j++) {
                if (interviews.get(i).isSameInterview(interviews.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

