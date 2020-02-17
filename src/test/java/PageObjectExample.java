import com.codeborne.selenide.Config;
import com.codeborne.selenide.Configuration;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PageObjectExample {

    @BeforeTest
    public void setup(){
        Configuration.browser="chrome";
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 1000;
    }

    @Test
    public void testMethod() {
        TicTacToePage ticTacToePage = new TicTacToePage();

        while(!ticTacToePage.isGameOver()){
            ticTacToePage.clickASquare();
        }

        Assert.assertTrue(ticTacToePage.isGameOver());


    }
}
