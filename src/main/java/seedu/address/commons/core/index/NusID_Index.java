package seedu.address.commons.core.index;

import seedu.address.model.person.NusId;
import seedu.address.model.person.Person;

import java.util.List;

public class NusID_Index {

    public static Person getPerson(List<Person> lastShownList, NusId toGroup) {
        for(int x = 0; x < lastShownList.size(); x++){
            //System.out.println(lastShownList.get(x).getNusId());

            if(lastShownList.get(x).getNusId().equals(toGroup)){
                return lastShownList.get(x);
            }
        }
        System.out.println("error");
        return null;
    }
}
