package seedu.address.model.person;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class SponsorTest {
    @Test
    public void isSamePerson() {
        // same object -> returns true
        Sponsor sponsor = (Sponsor) new PersonBuilder().withCategory("SPONSOR").build();
        assert (sponsor.isSamePerson(sponsor));


        assert (!sponsor.isSamePerson(null));


        Staff staff = (Staff) new PersonBuilder().withCategory("STAFF").build();
        assert (!sponsor.isSamePerson(staff));

        Sponsor differentSponsor = (Sponsor) new PersonBuilder().withCategory("SPONSOR").withName("john").build();
        assert (!sponsor.isSamePerson(differentSponsor));
    }

}
