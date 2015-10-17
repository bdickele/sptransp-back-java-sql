package org.bdickele.sptransp.exception;

import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Created by Bertrand DICKELE
 */
public class SpTranspErrorTest {

    @Test
    public void should_return_formatted_message() {
        String result = SpTranspBizError.AGR_RULE_ALREADY_EXISTS.getFormattedMessage("A", 10);
        assertThat(result).isEqualTo("BIZ0100 - Agreement rule already exists for destination [A] and good [10]");
    }

    @Test
    public void should_return_raw_message_when_args_are_incorrect() {
        String result = SpTranspBizError.AGR_RULE_ALREADY_EXISTS.getFormattedMessage("A");
        assertThat(result).isEqualTo("BIZ0100 - Agreement rule already exists for destination [%s] and good [%s]. Error with args: A");
    }
}
