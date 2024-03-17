package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ClassBook;
import seedu.address.model.ReadOnlyClassBook;
import seedu.address.model.person.Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JsonRootName(value = "classbook")
class JsonSerializableClassBook {

    public static final String MESSAGE_DUPLICATE_CLASS = "Class list contains duplicate class(es).";

    private final List<JsonAdaptedClass> classes = new ArrayList<>();

    @JsonCreator
    public JsonSerializableClassBook(@JsonProperty("classes") List<JsonAdaptedClass> classes) {
        this.classes.addAll(classes);
    }

    public JsonSerializableClassBook(ReadOnlyClassBook source) {
        classes.addAll(source.getClassList().stream().map(JsonAdaptedClass::new).collect(Collectors.toList()));
    }

    public ClassBook toModelType() throws IllegalValueException {
        ClassBook classBook = new ClassBook();
        for (JsonAdaptedClass jsonAdaptedClass : classes) {
            Classes classes = jsonAdaptedClass.toModelType();
            if (classBook.hasClass(classes)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CLASS);
            }
            classBook.createClass(classes);
        }
        return classBook;
    }
}
