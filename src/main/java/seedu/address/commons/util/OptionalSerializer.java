package seedu.address.commons.util;

import java.io.IOException;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * A Serializer for handling Optional values and converting them into
 * proper JSON strings with correct null formatting.
 */
public class OptionalSerializer extends JsonSerializer<Optional<?>> {
    @Override
    public void serialize(Optional<?> optional, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        if (optional.isPresent()) {
            jsonGenerator.writeObject(optional.get());
        } else {
            jsonGenerator.writeNull();
        }
    }
    /**
     * Avoids directly specifying Optional.class with generics
     */
    public Class<Optional<?>> handledType() {
        return (Class<Optional<?>>) (Class<?>) Optional.class;
    }
}
