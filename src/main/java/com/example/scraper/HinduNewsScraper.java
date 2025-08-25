package com.example.scraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;

public class HinduNewsScraper implements NewsScraper{

    public void scrapper(String stateUrl) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ctph3\\Desktop\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String fullUrl = "https://www.thehindu.com/news/national/" + stateUrl + "/";
        driver.get(fullUrl);

        try {
            Thread.sleep(3000); // Wait for page to load
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }

        try {
            List<WebElement> headlineElements = driver.findElements(By.tagName("h3"));
            Set<String> uniqueHeadlines = new LinkedHashSet<>();

            for (WebElement headline : headlineElements) {
                String text = headline.getText().trim();
                if (!text.isEmpty()) {
                    uniqueHeadlines.add(text);
                }
            }

            System.out.println("\n Top Unique Headlines from: " + stateUrl.toUpperCase().replace("-", " "));

            if (uniqueHeadlines.isEmpty()) {
                System.out.println("No headlines found for this state.");
            } else {
                for (String headline : uniqueHeadlines) {
                    System.out.println("• " + headline);
                }
            }


        } catch (Exception e) {
            System.out.println("Error while scraping headlines: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    public static void main(String[] args) {
        NewsScraper scraper = new HinduNewsScraper();
        Scanner sc = new Scanner(System.in);

        List<String> states = Arrays.asList(
                "ANDHRA PRADESH", "KARNATAKA", "KERALA", "TAMILNADU", "TELANGANA",
                "ANDAMAN AND NICOBAR ISLANDS", "ARUNACHAL PRADESH", "ASSAM", "BIHAR", "CHANDIGARH",
                "CHHATTISGARH", "DAMAN,DIU,DADRA AND NAGAR HAVELI", "GOA", "GUJARAT", "HARYANA",
                "HIMACHAL PRADESH", "JAMMU AND KASHMIR", "JHARKHAND", "LAKSHADWEEP", "LADAKH",
                "MADHYA PRADESH", "MAHARASTRA", "MANIPUR", "MEGHALAYA", "MIZORAM", "NAGALAND",
                "ODISHA", "PUNJAB", "RAJASTHAN", "SIKKIM", "TRIPURA", "UTTAR PRADESH",
                "UTTARAKHAND", "WEST BENGAL"
        );

        Map<String, String> stateUrlMap = new HashMap<>();
        stateUrlMap.put("ANDHRA PRADESH", "andhra-pradesh");
        stateUrlMap.put("KARNATAKA", "karnataka");
        stateUrlMap.put("KERALA", "kerala");
        stateUrlMap.put("TAMILNADU", "tamil-nadu");
        stateUrlMap.put("TELANGANA", "telangana");
        stateUrlMap.put("ANDAMAN AND NICOBAR ISLANDS", "andaman-and-nicobar-islands");
        stateUrlMap.put("ARUNACHAL PRADESH", "arunachal-pradesh");
        stateUrlMap.put("ASSAM", "assam");
        stateUrlMap.put("BIHAR", "bihar");
        stateUrlMap.put("CHANDIGARH", "chandigarh");
        stateUrlMap.put("CHHATTISGARH", "chhattisgarh");
        stateUrlMap.put("DAMAN,DIU,DADRA AND NAGAR HAVELI", "daman-diu-dadra-and-nagar-haveli");
        stateUrlMap.put("GOA", "goa");
        stateUrlMap.put("GUJARAT", "gujarat");
        stateUrlMap.put("HARYANA", "haryana");
        stateUrlMap.put("HIMACHAL PRADESH", "himachal-pradesh");
        stateUrlMap.put("JAMMU AND KASHMIR", "jammu-and-kashmir");
        stateUrlMap.put("JHARKHAND", "jharkhand");
        stateUrlMap.put("LAKSHADWEEP", "lakshadweep");
        stateUrlMap.put("LADAKH", "ladakh");
        stateUrlMap.put("MADHYA PRADESH", "madhya-pradesh");
        stateUrlMap.put("MAHARASTRA", "maharashtra");
        stateUrlMap.put("MANIPUR", "manipur");
        stateUrlMap.put("MEGHALAYA", "meghalaya");
        stateUrlMap.put("MIZORAM", "mizoram");
        stateUrlMap.put("NAGALAND", "nagaland");
        stateUrlMap.put("ODISHA", "odisha");
        stateUrlMap.put("PUNJAB", "punjab");
        stateUrlMap.put("RAJASTHAN", "rajasthan");
        stateUrlMap.put("SIKKIM", "sikkim");
        stateUrlMap.put("TRIPURA", "tripura");
        stateUrlMap.put("UTTAR PRADESH", "uttar-pradesh");
        stateUrlMap.put("UTTARAKHAND", "uttarakhand");
        stateUrlMap.put("WEST BENGAL", "west-bengal");

        boolean continueScraping = true;

        while (continueScraping) {
            System.out.println("\n Select a state to view top headlines:");
            for (int i = 0; i < states.size(); i++) {
                System.out.println((i + 1) + ". " + states.get(i));
            }

            try {
                System.out.print("Enter state number (1–34): ");
                int stateNumber = Integer.parseInt(sc.nextLine());

                if (stateNumber < 1 || stateNumber > states.size()) {
                    System.out.println(" Invalid number. Please select between 1 and " + states.size());
                    continue;
                }

                String selectedState = states.get(stateNumber - 1);
                String stateUrl = stateUrlMap.get(selectedState.toUpperCase());

                if (stateUrl != null) {
                    scraper.scrapper(stateUrl);
                } else {
                    System.out.println(" URL not mapped for selected state.");
                }

            } catch (NumberFormatException e) {
                System.out.println(" Please enter a valid number.");
            } catch (Exception e) {
                System.out.println(" Error occurred: " + e.getMessage());
            }

            System.out.print("\nDo you want to view headlines for another state? (yes/no): ");
            String choice = sc.nextLine().trim().toLowerCase();
            continueScraping = choice.equals("yes");
        }

        System.out.println("\n Thank you for using News Scraper. Have a nice Day!");
    }
}
