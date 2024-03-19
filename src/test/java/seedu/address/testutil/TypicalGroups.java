package seedu.address.testutil;

import static seedu.address.testutil.TypicalCourseMates.ALICE;
import static seedu.address.testutil.TypicalCourseMates.AMY;
import static seedu.address.testutil.TypicalCourseMates.BENSON;
import static seedu.address.testutil.TypicalCourseMates.BOB;
import static seedu.address.testutil.TypicalCourseMates.CARL;
import static seedu.address.testutil.TypicalCourseMates.DANIEL;
import static seedu.address.testutil.TypicalCourseMates.ELLE;
import static seedu.address.testutil.TypicalCourseMates.FIONA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.GroupList;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.QueryableCourseMate;
import seedu.address.model.group.Group;

/**
 * A utility class containing a list of {@code Group} objects to be used in tests.
 */
public class TypicalGroups {
    public static final Set<CourseMate> SAMPLE_MEMBER_SET_1 = new HashSet<>(Arrays.asList(ALICE, BENSON));
    public static final Set<CourseMate> SAMPLE_MEMBER_SET_2 = new HashSet<>(Arrays.asList(CARL, DANIEL));
    public static final Set<CourseMate> SAMPLE_MEMBER_SET_3 = new HashSet<>(Arrays.asList(ELLE, FIONA));

    // These contacts are not in the typicalContactList
    public static final Set<CourseMate> SAMPLE_INVALID_MEMBER_SET_1 = new HashSet<>(Arrays.asList(AMY, BOB));

    public static final Set<QueryableCourseMate> SAMPLE_QUERYABLE_SET_1 = getQueries(SAMPLE_MEMBER_SET_1);
    public static final Set<QueryableCourseMate> SAMPLE_QUERYABLE_SET_2 = getQueries(SAMPLE_MEMBER_SET_2);
    public static final Set<QueryableCourseMate> SAMPLE_QUERYABLE_SET_3 = getQueries(SAMPLE_MEMBER_SET_3);

    public static final Set<QueryableCourseMate> SAMPLE_UNQUERYABLE_SET_1 = getQueries(SAMPLE_INVALID_MEMBER_SET_1);

    public static final Name SAMPLE_GROUP_NAME_1 = new Name("Group 1");
    public static final Name SAMPLE_GROUP_NAME_2 = new Name("Group 2");
    public static final Name SAMPLE_GROUP_NAME_3 = new Name("Group 3");


    public static final Group SAMPLE_GROUP_1 = new Group(SAMPLE_GROUP_NAME_1, SAMPLE_MEMBER_SET_1);
    public static final Group SAMPLE_GROUP_2 = new Group(SAMPLE_GROUP_NAME_2, SAMPLE_MEMBER_SET_2);
    public static final Group SAMPLE_GROUP_3 = new Group(SAMPLE_GROUP_NAME_3, SAMPLE_MEMBER_SET_3);

    /**
     * Prevent instantiation.
     */
    private TypicalGroups() {
    }

    /**
     * Returns an {@code GroupList} with all the typical course mates.
     */
    public static GroupList getTypicalGroupList() {
        GroupList groupList = new GroupList();
        for (Group group : getTypicalGroups()) {
            groupList.addGroup(group);
        }
        return groupList;
    }

    /**
     * Returns an {@code ArrayList<Group>} with all the typical course mates.
     */
    public static ArrayList<Group> getTypicalGroups() {
        return new ArrayList<>(Arrays.asList(SAMPLE_GROUP_1, SAMPLE_GROUP_2, SAMPLE_GROUP_3));
    }

    private static Set<QueryableCourseMate> getQueries(Set<CourseMate> memberSet) {
        return memberSet
                .stream()
                .map(courseMate -> new QueryableCourseMate(courseMate.getName()))
                .collect(Collectors.toSet());
    }
}
