import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selenide.*;

public class TicTacToePage {

    private ElementsCollection squares = $$(".square");
    private SelenideElement restart = $(".restart");


    public TicTacToePage() {
        open("https://web.archive.org/web/20200103004726/https://playtictactoe.org/");
    }

    public void clickASquare() {
        for (SelenideElement square : squares) {
            if (!square.$(".x").exists() && !square.$(".o").exists()) {
                square.click();
                return;
            }
        }
        throw new RuntimeException("No Squares to click");
    }

    public boolean isGameOver(){
        try {
            restart.waitWhile(not(Condition.visible), 500);
        } catch(Throwable ignore){}
        return restart.isDisplayed();
    }
}
