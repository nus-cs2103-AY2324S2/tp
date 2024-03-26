package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRODUCT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_QUANTITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRODUCT_DOUGHNUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_THREE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_TWO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_QUANTITY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditOrderCommand;
import seedu.address.model.order.Product;
import seedu.address.model.order.Quantity;
import seedu.address.testutil.EditOrderDescriptorBuilder;

public class EditOrderCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditOrderCommand.MESSAGE_USAGE);

    private EditOrderCommandParser parser = new EditOrderCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no field specified
        assertParseFailure(parser, " " + PREFIX_ORDER + "1", EditOrderCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, " " + PREFIX_ORDER + "1" + INVALID_PRODUCT_DESC,
                Product.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, " " + PREFIX_ORDER + "1" + INVALID_QUANTITY_DESC,
                Quantity.MESSAGE_CONSTRAINTS); // invalid phone

        // invalid product followed by valid quantity
        assertParseFailure(parser, " " + PREFIX_ORDER + "1" + INVALID_PRODUCT_DESC
                + PREFIX_PRODUCT_QUANTITY + " " + VALID_QUANTITY_ONE, Product.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, " " + PREFIX_ORDER + "1" + INVALID_PRODUCT_DESC
                + INVALID_QUANTITY_DESC, Product.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_bothFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = " " + PREFIX_ORDER + targetIndex.getOneBased() + " " + PREFIX_PRODUCT_NAME + " "
                + VALID_PRODUCT_DOUGHNUT + " " + PREFIX_PRODUCT_QUANTITY + " " + VALID_QUANTITY_TWO;

        EditOrderCommand.EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder()
                .withProduct(VALID_PRODUCT_DOUGHNUT)
                .withQuantity(Integer.parseInt(VALID_QUANTITY_TWO))
                .build();

        EditOrderCommand expectedCommand = new EditOrderCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_failure() {
        // product only
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = " " + PREFIX_ORDER + targetIndex.getOneBased()
                + " " + PREFIX_PRODUCT_NAME + VALID_PRODUCT_DOUGHNUT;
        EditOrderCommand.EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder()
                .withProduct(VALID_PRODUCT_DOUGHNUT).build();
        assertParseFailure(parser, userInput, EditOrderCommand.MESSAGE_NOT_EDITED);

        // quantity only
        userInput = " " + PREFIX_ORDER + targetIndex.getOneBased()
                + " " + PREFIX_PRODUCT_QUANTITY + VALID_QUANTITY_THREE;
        descriptor = new EditOrderDescriptorBuilder()
                .withQuantity(Integer.parseInt(VALID_QUANTITY_THREE)).build();
        assertParseFailure(parser, userInput, EditOrderCommand.MESSAGE_NOT_EDITED);
    }
}
