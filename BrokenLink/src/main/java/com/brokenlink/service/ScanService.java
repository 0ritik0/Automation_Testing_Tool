package com.brokenlink.service;

import com.brokenlink.model.ScanResult;
import com.brokenlink.util.LogGenerator;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ScanService {

    @Autowired
    private WebDriver driver;

    public ScanResult scanWebsite(String url) {
        ScanResult result = new ScanResult();
        result.setUrl(url);
        List<String> brokenLinks = new ArrayList<>();
        List<String> warnings = new ArrayList<>();
        List<String> nonUniqueTitles = new ArrayList<>();
        boolean gtmMissing = false;

        try {
            driver.get(url);
            String pageTitle = driver.getTitle();

            // 1️⃣ Check for Non-Unique Titles
            Set<String> pageTitles = new HashSet<>();
            if (!pageTitles.add(pageTitle)) {
                nonUniqueTitles.add(pageTitle);
            }

            // 2️⃣ Find all links and check if they are broken
            List<WebElement> links = driver.findElements(By.tagName("a"));
            for (WebElement link : links) {
                String href = link.getAttribute("href");
                if (href != null && !isValidLink(href)) {
                    brokenLinks.add(href);
                }
            }

            // 3️⃣ Check for Console Errors (Warnings)
            List<LogEntry> logs = driver.manage().logs().get(LogType.BROWSER).getAll();
            for (LogEntry entry : logs) {
                warnings.add(entry.getMessage());
            }

            // 4️⃣ Check for GTM (Google Tag Manager) Script
            List<WebElement> scripts = driver.findElements(By.tagName("script"));
            boolean foundGTM = false;
            for (WebElement script : scripts) {
                String scriptContent = script.getAttribute("innerHTML");
                if (scriptContent != null && scriptContent.contains("googletagmanager")) {
                    foundGTM = true;
                    break;
                }
            }
            if (!foundGTM) {
                gtmMissing = true;
            }

            // 5️⃣ Set Results
            result.setBrokenLinks(brokenLinks);
            result.setWarnings(warnings);
            result.setNonUniqueTitles(nonUniqueTitles);
            result.setGtmMissing(gtmMissing);

            // 6️⃣ Generate Log Report
            LogGenerator.generateLog(result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private boolean isValidLink(String link) {
        return link.startsWith("http") || link.startsWith("https");
    }
}

