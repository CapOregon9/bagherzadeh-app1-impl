package baseline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditItemControllerTest {

    EditItemController editItemController;

    @BeforeEach
    void createObjects() {
        editItemController = new EditItemController();
    }

    @Test
    void descriptionFieldOverMaxValidation() {
        //False due to being over 256 characters
        assertFalse(editItemController.descriptionFieldMaxValidation("dsjgnhasvoowarengosadfgnasfobvaenboasfgnaoslgnsadogsadn" +
                "gaiwrgnbfslkjgbnfasdkjlgnasljfdasganiwkegijadsjgnhasvoowarengosadfgnasfobvaenboasfgnaoslgnsadogsadngaiwrgnbfslkj" +
                "gbnfasdkjlgnasljfdasganiwkegijadsjgnhasvoowarengosadfgnasfobvaenboasfgnaoslgnsadogsadsdgasda"));
    }

    @Test
    void descriptionFieldUnderMaxValidation() {
        //True due to being under 256 characters
        assertTrue(editItemController.descriptionFieldMaxValidation("This is an item description."));
    }

    @Test
    void isDescriptionFieldEmpty() {
        //True if the field is empty
        assertTrue(editItemController.isDescriptionFieldEmpty(""));
    }


}