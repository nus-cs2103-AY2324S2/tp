package seedu.address.logic.parser;

import java.util.HashMap;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     *  Parses the given {@code String} of arguments in the context of the AddCommand
     *  and returns an AddCommand object for execution.
     *  Command format: add /attibuteName1 attributeValue1 /attributeName2 attributeValue2 ...
     *  Examples:
     *  add
     *  add /Name Alice /Phone 123456 /address 123 ABC Street
     */
    public AddCommand parse(String args) throws ParseException {
        args = args.trim();
        String[] parts = args.split("/", -1);
        parts = ParserUtil.removeFirstItemFromStringList(parts);
        HashMap<String, String> attributeMap = ParserUtil.getAttributeHashMapFromAttributeStrings(parts);
        return new AddCommand(attributeMap);
    }




}
