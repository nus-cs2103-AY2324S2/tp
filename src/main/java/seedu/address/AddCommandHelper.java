package seedu.address;


import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.ui.Status;


/**
 * A helper class that prompts the user to enter certain fields
 * and displays the final command in the specified format
 * so the user can copy over to addressbook.
 */
public class AddCommandHelper {

    private Status status;

    private Name name;
    private Number number;
    private Email email;

    private Address address;
    private Phone phone;
    private Set<Tag> tags;

    /**
     * Creates a new instance of AddCommandHelper
     */
    public AddCommandHelper() {
        this.status = Status.GET_NAME;
        this.tags = new HashSet<>();
    }


    /**
     * Takes the input of the user and provides a reply based on the input
     * @param text full user input string
     * @return the string representing the result of processing the user's input
     * @throws ParseException if the information the user entered is invalid
     */
    public String getResponse(String text) throws ParseException {
        if (this.status == Status.COMPLETE) {
            return formattedCommand();
        } else {
            return processInput(text);
        }
    }

    /**
     * Processes the input of the user and prompts the user when necessary
     * for more information.
     * @param input full user input string
     * @return the string representing the result of processing the user's input
     * @throws ParseException if the input entered by the user is invalid.
     */
    private String processInput(String input) throws ParseException {
        String output = "";
        switch (this.status) {
        case GET_NAME:
            processName(input);
            output = this.status.toString();
            setStatus(this.status);
            break;
        case GET_NUMBER:
            processNumber(input);
            output = this.status.toString();
            setStatus(this.status);
            break;
        case GET_EMAIL:
            processEmail(input);
            output = this.status.toString();
            setStatus(this.status);
            break;
        case GET_ADDRESS:
            processAddress(input);
            output = this.status.toString();
            setStatus(this.status);
            break;
        case GET_TAG:
            processTag(input);
            output = this.status.toString();
            setStatus(this.status);
            break;
        default:
            assert false : "The status should only be those values";

        }

        return output;
    }

    /**
     * Sets the status of the CommandHelper to choose the right
     * type of validity checks based on the information the user is trying
     * to enter.
     * @param status the current type of information the user is trying to enter.
     */
    private void setStatus(Status status) {
        switch (this.status) {
        case GET_NAME:
            this.status = Status.GET_NUMBER;
            break;
        case GET_NUMBER:
            this.status = Status.GET_EMAIL;
            break;
        case GET_EMAIL:
            this.status = Status.GET_ADDRESS;
            break;
        case GET_ADDRESS:
            this.status = Status.GET_TAG;
            break;
        case GET_TAG:
            this.status = Status.COMPLETE;
            break;

        default:
            assert false : "The status should only be those values";
        }
    }

    private String processName(String name) throws ParseException {
        Name processedName = ParserUtil.parseName(name);
        this.name = processedName;
        return processedName.toString();
    }

    private String processNumber(String num) throws ParseException {
        Phone processedNum = ParserUtil.parsePhone(num);
        this.phone = processedNum;
        return processedNum.toString();
    }

    private String processEmail(String email) throws ParseException {
        Email processedEmail = ParserUtil.parseEmail(email);
        this.email = processedEmail;
        return processedEmail.toString();
    }

    private String processAddress(String address) throws ParseException {
        Address processedAddress = ParserUtil.parseAddress(address);
        this.address = processedAddress;
        return processedAddress.toString();
    }

    private String processTag(String tag) throws ParseException {
        String processedTag = tag.trim();
        if (processedTag.length() > 0) {
            Tag t = ParserUtil.parseTag(tag);
            this.tags.add(t);
            return t.toString();
        } else {
            return "";
        }

    }




    private String formattedCommand() {
        assert this.status != Status.COMPLETE : "Command should have been compeleted before this function is called.";
        Person p = new Person(name, phone, email, address, tags);
        return p.getFormattedCommand();
    }

}



