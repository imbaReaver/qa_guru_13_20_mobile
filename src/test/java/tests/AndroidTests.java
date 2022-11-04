package tests;

import com.codeborne.selenide.CollectionCondition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class AndroidTests extends TestBase {

    @Test
    @DisplayName("Wikipedia search test")
    void searchTest() {

        step("Skip onboarding", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click());

        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))
                    .sendKeys("BrowserStack");
        });

        step("Verify search content found", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }


    @Test
    @DisplayName("Open article test")
    void openArticleTest() {

        step("Skip onboarding", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click());

        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))
                    .sendKeys("JUnit");
        });

        step("Verify search content found", () -> {
            $$(AppiumBy.id("org.wikipedia.alpha:id/search_results_list")).shouldHave(
                    CollectionCondition.sizeGreaterThan(0));
        });

        step("Open article", () -> {
            $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).first().click();
        });

        step("Verify article content found", () ->
                $(AppiumBy.xpath("//android.widget.TextView[@resource-id='pcs-edit-section-title-description']"))
                        .shouldHave(text("Testing software")));

    }
}
