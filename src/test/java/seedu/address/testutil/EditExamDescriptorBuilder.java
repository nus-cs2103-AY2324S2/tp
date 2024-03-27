package seedu.address.testutil;

import seedu.address.logic.commands.EditExamCommand.EditExamDescriptor;
import seedu.address.model.exam.Exam;
import seedu.address.model.person.Score;

/**
 * A utility class to help with building EditExamDescriptor objects.
 */
public class EditExamDescriptorBuilder {

    private EditExamDescriptor descriptor;

    public EditExamDescriptorBuilder() {
        descriptor = new EditExamDescriptor();
    }

    public EditExamDescriptorBuilder(EditExamDescriptor descriptor) {
        this.descriptor = new EditExamDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditExamDescriptor} with fields containing {@code exam}'s details
     */
    public EditExamDescriptorBuilder(Exam exam) {
        descriptor = new EditExamDescriptor();
        descriptor.setName(exam.getName());
        descriptor.setMaxScore(exam.getMaxScore());
    }

    /**
     * Sets the {@code Name} of the {@code EditExamDescriptor} that we are building.
     */
    public EditExamDescriptorBuilder withName(String name) {
        descriptor.setName(name);
        return this;
    }

    /**
     * Sets the {@code Score} of the {@code EditExamDescriptor} that we are building.
     */
    public EditExamDescriptorBuilder withScore(Score score) {
        descriptor.setMaxScore(score);
        return this;
    }

    public EditExamDescriptor build() {
        return descriptor;
    }
}
