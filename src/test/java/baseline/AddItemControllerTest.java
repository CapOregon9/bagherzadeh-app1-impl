package baseline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddItemControllerTest {

    AddItemController addItemController;

    @BeforeEach
    void createObjects() {
        addItemController = new AddItemController();
    }

    @Test
    void descriptionFieldOverMaxValidation() {
        //False due to being over 256 characters
        assertFalse(addItemController.descriptionFieldMaxValidation("dsjgnhasvoowarengosadfgnasfobvaenboasfgnaoslgnsadogsadn" +
                "gaiwrgnbfslkjgbnfasdkjlgnasljfdasganiwkegijadsjgnhasvoowarengosadfgnasfobvaenboasfgnaoslgnsadogsadngaiwrgnbfslkj" +
                "gbnfasdkjlgnasljfdasganiwkegijadsjgnhasvoowarengosadfgnasfobvaenboasfgnaoslgnsadogsadsdgasda"));
    }

    @Test
    void descriptionFieldUnderMaxValidation() {
        //True due to being under 256 characters
        assertTrue(addItemController.descriptionFieldMaxValidation("This is an item description."));
    }

    @Test
    void isDescriptionFieldEmpty() {
        //True if the field is empty
        assertTrue(addItemController.isDescriptionFieldEmpty(""));
    }
}