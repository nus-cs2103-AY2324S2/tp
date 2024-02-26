package seedu.address.storage;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

public class UniquePersonListDeserializer extends StdDeserializer<UniquePersonList> {

    public UniquePersonListDeserializer() {
        this(null);
    }

    public UniquePersonListDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public UniquePersonList deserialize(JsonParser parser,
                              DeserializationContext context) throws IOException, JsonProcessingException {
        List<Person> persons = context.readValue(parser,
                context.getTypeFactory().constructCollectionType(List.class, Person.class));
        UniquePersonList upl = new UniquePersonList();
        upl.setPersons(persons);
        return upl;
    }

}
