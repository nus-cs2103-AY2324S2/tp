package staffconnect.model.person;

import static java.util.Objects.requireNonNull;
import static staffconnect.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Represents a Person's faculty in the staff book.
 * Guarantees: immutable; is valid as declared in
 * {@link #isValidFaculty(String)}
 */
public class Faculty {
    public static final String MESSAGE_CONSTRAINTS =
            "The content should be a String representing a real faculty of NUS";

    /**
     * For this version, a valid faculty value should match exactly the full name of the faculty,
     * or the value is invalid. The enum class serves as the purpose for parsing user strings into the faculty name.
     */
    public enum FacultyName {
        ARTS_AND_SOCIAL_SCIENCES("Faculty of Arts of Social Sciences", "Arts and Social Sciences", "FASS"),
        BUSINESS("Business School", "Business", "Biz School", "Biz"),
        COMPUTING("School of Computing", "Computing", "SoC"),
        CONTINUING_AND_LIFELONG_EDUCATION("School of Continuing and Lifelong Education",
                "Continuing and Lifelong Education", "SCALE"),
        DENTISTRY("Faculty of Dentistry", "Dentistry"),
        DESIGN_AND_ENVIRONMENT("School of Design and Environment", "Design and Environment", "SDE"),
        DUKE_NUS_MEDICAL_SCHOOL("Duke-NUS Medical School", "Duke-NUS"),
        ENGINEERING("Faculty of Engineering", "Engineering", "FoE"),
        INTEGRATIVE_SCIENCES_AND_ENGINEERING("Integrative Sciences and Engineering", "ISEP"),
        LAW("Faculty of Law", "Law"),
        MEDICINE("Yong Loo Lin School of Medicine", "Medicine", "School of Medicine"),
        MUSIC("Yong Siew Toh Conservatory of Music", "Music", "YST Conservatory of Music"),
        PUBLIC_HEALTH("Saw Swee Hock School of Public Health", "Public Health"),
        PUBLIC_POLICY("Public Policy", "Lee Kuan Yew School of Public Policy", "LKY School of Public Policy"),
        SCIENCE("Faculty of Science", "Science", "FoS"),
        UNIVERSITY_SCHOLARS_PROGRAMME("University Scholars Programme", "USP"),
        YALE_NUS_COLLEGE("Yale-NUS College", "Yale-NUS");

        private final String[] facultyNameValues;

        FacultyName(String... facultyNames) {
            this.facultyNameValues = facultyNames;
        }

        /**
         * Links enum member to its name.
         *
         * @return name of the faculty
         */
        public String getFacultyName() {
            return facultyNameValues[0];
        }
    }
    public final FacultyName value;

    /**
     * Constructs a {@code Faculty}.
     *
     * @param faculty A valid faculty.
     */
    public Faculty(String faculty) {
        requireNonNull(faculty);
        checkArgument(isValidFaculty(faculty), MESSAGE_CONSTRAINTS);
        value = fromString(faculty); // can be extended
    }

    /**
     * Verifies if a faculty name is valid.
     *
     * @param test string representing the faculty name
     * @return True if the input matches any faculty name.
     */
    public static boolean isValidFaculty(String test) {
        requireNonNull(test, "faculty cannot be null");

        return Stream.of(FacultyName.values())
                .flatMap(member -> Arrays.stream(member.facultyNameValues))
                .anyMatch(value -> value.equalsIgnoreCase(test));
    }

    private static FacultyName fromString(String name) {
        Optional<FacultyName> match = Stream.of(FacultyName.values())
                .filter(member -> Arrays.stream(member.facultyNameValues)
                        .anyMatch(value -> value.equalsIgnoreCase(name)))
                .findFirst();
        if (match.isPresent()) {
            return match.get();
        } else {
            throw new IllegalArgumentException("No enum constant matches the provided name: " + name);
        }
    }

    @Override
    public String toString() {
        return value.getFacultyName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Faculty)) {
            return false;
        }

        Faculty otherFaculty = (Faculty) obj;
        return this.value.equals(otherFaculty.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
