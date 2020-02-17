import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class NoPageObjectExample {

    @Test
    public void noPageObjectExample(){

        //Sets configurations for the browser/Selenium
        Configuration.holdBrowserOpen=true;
        Configuration.headless = true;
        Configuration.browser = "firefox";
        Configuration.timeout = 5000; //5 second timeout when waiting for an element to appear
        Configuration.pollingInterval = 200; //looks for the elements every half second
        Configuration.startMaximized = true;

        //Open the browser to a Tic Tac Toe Game
        open("https://web.archive.org/web/20200103004726/https://playtictactoe.org/");

        //Define elements to be used
        ElementsCollection squares = $$(".square");
        SelenideElement restart = $(".restart");

        //Keep Clicking empty squares until the restart element appears or you've clicked 6 squares, which shouldn't happen
        for(int i=0;i<6||restart.is(not(appears));i++){
            //Loop through the squares until you find one that does not contain an x or o class
            for (SelenideElement square : squares) {
                if (!square.$(".x").exists() && !square.$(".o").exists()) {
                    //Click the empty square
                    square.click();
                }
                //if the restart button is displayed stop clicking squares
                try{
                    restart.waitUntil(visible,1000);
                    break;
                } catch (Throwable ignore) {
                }
            }
        }
        //Assert that the restart button appears
        restart.shouldBe(appears);

    }
}
