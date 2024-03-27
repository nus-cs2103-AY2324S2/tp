package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.language.ProgrammingLanguage;
import seedu.address.model.tag.Tag;

public class PersonInterviewTimeComparatorTest {

    private static final CompanyName VALID_COMPANY_NAME = BENSON.getCompanyName();
    private static final Name VALID_NAME = BENSON.getName();
    private static final Phone VALID_PHONE = BENSON.getPhone();
    private static final Email VALID_EMAIL = BENSON.getEmail();
    private static final Address VALID_ADDRESS = BENSON.getAddress();
    private static final InterviewTime VALID_INTERVIEWTIME = BENSON.getDateTime();
    private static final Salary VALID_SALARY = BENSON.getSalary();
    private static final Info VALID_INFO = BENSON.getInfo();
    private static final Set<ProgrammingLanguage> VALID_PROGRAMMING_LANG = BENSON.getProgrammingLanguages();
    private static final Set<Tag> VALID_TAGS = BENSON.getTags();

    @Test
    public void checkOrder() {
        PersonInterviewTimeComparator comparator = new PersonInterviewTimeComparator();
        //test earlier than
        assertTrue(comparator.compare(ALICE, BENSON) <= -1);
        //test equals
        assertTrue(comparator.compare(ALICE, ALICE) == 0);
        //test later than
        assertTrue(comparator.compare(BENSON, ALICE) >= 1);
    }
}
