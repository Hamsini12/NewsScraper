package com.example.scraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class HinduNewsScraper implements NewsScraper
{
    @Override
    public void scrape(String stateUrl)
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);

        try
        {
            String fullUrl = "https://www.thehindu.com/news/national/" + stateUrl + "/";
            driver.get(fullUrl);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h3")));
            List<WebElement> headlineElements = driver.findElements(By.tagName("h3"));

            Set<String> uniqueHeadlines = new LinkedHashSet<>();
            for (WebElement headline : headlineElements)
            {
                String text = headline.getText().trim();
                if (!text.isEmpty())
                {
                    uniqueHeadlines.add(text);
                }
            }
            System.out.println("\nTop Unique Headlines from: " + stateUrl.toUpperCase().replace("-", " ") + "\n");
            if (uniqueHeadlines.isEmpty())
            {
                System.out.println("No headlines found.");
            }
            else
            {
                int count = 1;
                for (String headline : uniqueHeadlines) {
                    System.out.println(count + ". " + headline);
                    count++;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Error while scraping headlines: " + e.getMessage());
        }
        finally
        {
            driver.quit();
        }
    }
}