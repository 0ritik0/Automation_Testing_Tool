package com.brokenlink.util;

import com.brokenlink.model.ScanResult;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogGenerator {

    public static void generateLog(ScanResult result) throws IOException {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String logFileName = "logs/ScanLog_" + timestamp + ".txt";
        
        FileWriter writer = new FileWriter(logFileName);
        writer.write("Scan Report for URL: " + result.getUrl() + "\n");
        writer.write("----------------------------------------\n");
        
        writer.write("Broken Links Found: " + result.getBrokenLinks().size() + "\n");
        for (String link : result.getBrokenLinks()) {
            writer.write("❌ " + link + "\n");
        }

        writer.write("\nWarnings Found: " + result.getWarnings().size() + "\n");
        for (String warning : result.getWarnings()) {
            writer.write("⚠️  " + warning + "\n");
        }

        writer.write("\nNon-Unique Titles: " + result.getNonUniqueTitles().size() + "\n");
        for (String title : result.getNonUniqueTitles()) {
            writer.write("📌 " + title + "\n");
        }

        writer.write("\nGTM Script Missing: " + (result.isGtmMissing() ? "YES ❌" : "NO ✅") + "\n");

        writer.close();
    }
}
