
import com.google.devtools.common.options.OptionsParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestHtmlUnitDriver {

    public static void main(String[] args) {


        String input = "";
        OptionsParser parser = OptionsParser.newOptionsParser(Options.class);
        parser.parseAndExitUponError(args);
        Options options = parser.getOptions(Options.class);

        try {
            input = args[0];

        } catch (Exception e_str) {
            System.out.println("False search input.");
            printUsage(parser);
            System.exit(0);

        }


        if (options.results < 0 || options.results > 6 || options.search.isEmpty()) {
            printUsage(parser);
            System.exit(0);
            return;
        }


        TestHtmlUnitDriver example = new TestHtmlUnitDriver();

        example.testHtmlUnitDriver(input, options.results, options.search);

    }

    private static void printUsage(OptionsParser parser) {
        System.out.println("Usage: java -jar googleme.jar OPTIONS");
        System.out.println(parser.describeOptions(Collections.<String, String>emptyMap(),
                OptionsParser.HelpVerbosity.LONG));
    }

    public void testHtmlUnitDriver(String input, int result, String search) {
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.OFF);

        if (search.equals("google")) {
            google(input, result);
        } else if (search.equals("bing")) {
            bing(input, result);
        } else {
            System.out.println("Wrong search engine.");
        }
    }

    public void google(String input, int results) {
        WebDriver driver = null;

        try {

            driver = new HtmlUnitDriver();
            driver.get("http://www.google.com");
            //System.out.println("Title of page: " + driver.getTitle());
            By byNameSearchInput = By.name("q");
            WebElement searchInput = driver.findElement(byNameSearchInput);
            if (searchInput != null) {
                // Type search keyword in search text box.
                searchInput.sendKeys(input);
            }
            //System.out.println("Searching for ... " + input);
            // No need to locate/find the submit button
            searchInput.submit();

            // wait until the google page shows the result
            WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));

            // Amount of results
            By byIdResultsOutput = By.id("resultStats");
            WebElement resultsOutput = driver.findElement(byIdResultsOutput);
            System.out.println(resultsOutput.getText() + ". Die ersten " + results + " sind:");
            // Get search result list in first result page by xpath.
            //By byXPathResultList = By.xpath(".//*[@id='rso']//div//h3/a");
            //List<WebElement> resultElementList = driver.findElements(byXPathResultList);
            List<WebElement> resultElementList = driver.findElements(By.cssSelector("h3.r > a"));
            if (resultElementList.size() != 0) {
                // Loop the search result list.
                int size = resultElementList.size();
                for (int i = 0; i < results; i++) {
                    WebElement resultElement = resultElementList.get(i);
                    String str = resultElement.getAttribute("href");
                    String result = str.substring(str.indexOf("?q=") + 3, str.indexOf("&"));
                    System.out.println("- " + result);

                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (driver != null) {
                //System.out.println("DONE");
                driver.close();
                driver = null;
                System.exit(0);
            }
        }

    }

    public void bing(String input, int results) {
        WebDriver driver = null;

        try {
            // Initiate HtmlUnitDriver object.
            driver = new HtmlUnitDriver();

            driver.get("http://www.bing.com");

            //System.out.println("Title of page: " + driver.getTitle());

            // Get input search text box.
            By byIdSearchInput = By.id("sb_form_q");
            WebElement searchInput = driver.findElement(byIdSearchInput);
            if (searchInput != null) {
                // Type search keyword in search text box.
                searchInput.sendKeys(input);
                //System.out.println("Searching for ... " + input);
            }

            searchInput.submit();

            // wait until the google page shows the result
            WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.id("b_tween")));

            // Amount of results
            By byXpathResultsOutput = By.xpath("//*[@id=\"b_tween\"]/span[@class=\"sb_count\"]");
            WebElement resultsOutput = driver.findElement(byXpathResultsOutput);
            //String str = resultsOutput.getText();
            String[] strSplit = resultsOutput.getText().split(" ");
            String output = "Ungefähr " + strSplit[0] + " Ergebnisse";
            System.out.println(output + ". Die ersten " + results + " sind:");

            // Get search result list in first result page by xpath.
            By byXPathResultList = By.xpath("//*[@id=\"b_results\"]/li[@class=\"b_algo\"]");
            List<WebElement> resultElementList = driver.findElements(byXPathResultList);

            if (resultElementList.size() != 0) {
                // Loop the search result list.
                int size = resultElementList.size();
                for (int i = 0; i < results; i++) {
                    WebElement resultElement = resultElementList.get(i);

                    By byXPathCite = By.xpath(".//cite");
                    WebElement citeElement = resultElement.findElement(byXPathCite);
                    String cite = citeElement.getText();
                    System.out.println("- " + cite);

                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (driver != null) {
                //System.out.println("DONE");
                driver.close();
                driver = null;
                System.exit(0);
            }
        }
    }


}