package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.parser.ClearCommandParser;


public class ClearCommandParserTest {
  private ClearCommandParser parser = new ClearCommandParser();

  @Test
  public void parse_allFieldsPresent_success() {
    assertParseSuccess(parser, " --force", new ClearCommand(true));
  }

  @Test
  public void parse_noFieldsPresent_success() {
    assertParseSuccess(parser, "", new ClearCommand(false));
  }

  @Test
  public void parse_invalidFieldsPresent_success() {
    assertParseSuccess(parser, " --force --force", new ClearCommand(true));
  }

  @Test
  public void parse_invalidFieldsPresent_success2() {
    assertParseSuccess(parser, " -force", new ClearCommand(false));
  }

  @Test
  public void parse_invalidFieldsPresent_success3() {
    assertParseSuccess(parser, " -f", new ClearCommand(false));
  }

  
}
