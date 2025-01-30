package com.brokenlink.model;

import java.util.List;

public class ScanResult {
    private String url;
    private List<String> brokenLinks;
    private List<String> warnings;
    private List<String> nonUniqueTitles;
    private boolean gtmMissing;

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public List<String> getBrokenLinks() { return brokenLinks; }
    public void setBrokenLinks(List<String> brokenLinks) { this.brokenLinks = brokenLinks; }

    public List<String> getWarnings() { return warnings; }
    public void setWarnings(List<String> warnings) { this.warnings = warnings; }

    public List<String> getNonUniqueTitles() { return nonUniqueTitles; }
    public void setNonUniqueTitles(List<String> nonUniqueTitles) { this.nonUniqueTitles = nonUniqueTitles; }

    public boolean isGtmMissing() { return gtmMissing; }
    public void setGtmMissing(boolean gtmMissing) { this.gtmMissing = gtmMissing; }
}
