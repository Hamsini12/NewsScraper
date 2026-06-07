package com.example;

import com.example.scraper.HinduNewsScraper;
import com.example.scraper.NewsScraper;

import java.util.*;

public class App
{
    private static Map<String, String> getStateUrlMap()
    {
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
        stateUrlMap.put("MAHARASHTRA", "maharashtra");
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

        return stateUrlMap;
    }
    private static List<String> getStates() {
        return Arrays.asList(
                "ANDHRA PRADESH",
                "KARNATAKA",
                "KERALA",
                "TAMILNADU",
                "TELANGANA",
                "ANDAMAN AND NICOBAR ISLANDS",
                "ARUNACHAL PRADESH",
                "ASSAM",
                "BIHAR",
                "CHANDIGARH",
                "CHHATTISGARH",
                "DAMAN,DIU,DADRA AND NAGAR HAVELI",
                "GOA",
                "GUJARAT",
                "HARYANA",
                "HIMACHAL PRADESH",
                "JAMMU AND KASHMIR",
                "JHARKHAND",
                "LAKSHADWEEP",
                "LADAKH",
                "MADHYA PRADESH",
                "MAHARASHTRA",
                "MANIPUR",
                "MEGHALAYA",
                "MIZORAM",
                "NAGALAND",
                "ODISHA",
                "PUNJAB",
                "RAJASTHAN",
                "SIKKIM",
                "TRIPURA",
                "UTTAR PRADESH",
                "UTTARAKHAND",
                "WEST BENGAL"
        );
    }
    public static void main(String[] args)
    {
        NewsScraper scraper = new HinduNewsScraper();
        Scanner sc = new Scanner(System.in);

        List<String> states = getStates();
        Map<String, String> stateUrlMap = getStateUrlMap();
        boolean continueScraping = true;

        while (continueScraping)
        {
            System.out.println("\nSelect a state to view top headlines:");
            for (int i = 0; i < states.size(); i++)
            {
                System.out.println((i + 1) + ". " + states.get(i));
            }

            try
            {
                System.out.print("\nEnter state number (1-" + states.size() + "): ");
                int stateNumber = Integer.parseInt(sc.nextLine());
                if (stateNumber < 1 || stateNumber > states.size())
                {
                    System.out.println("Invalid state number.");
                    continue;
                }

                String selectedState = states.get(stateNumber - 1);
                String stateUrl = stateUrlMap.get(selectedState);

                scraper.scrape(stateUrl);
            }
            catch (NumberFormatException e)
            {
                System.out.println("Please enter a valid number.");

            }
            catch (Exception e)
            {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.print("\nDo you want to view another state? (yes/no): ");
            String choice = sc.nextLine().trim().toLowerCase();

            continueScraping = choice.equals("yes");
        }
        sc.close();
        System.out.println("\nThank you for using News Scraper!");
    }
}