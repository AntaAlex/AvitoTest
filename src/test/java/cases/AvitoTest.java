package cases;

import org.junit.Test;
import pages.MainPage;
import utils.BaseHooks;

public class AvitoTest extends BaseHooks {

    @Test
    public void HomeWorkPageObject() {
        MainPage mainPage = new MainPage(driver);

        mainPage.openPage()
                .clickTownYes()
                .clickWithPhoto()
                .fillFieldSearch()
                .clickBtnFind()
                .selectSortByDate()
                .takeDataAndWriteFile();
    }
}
