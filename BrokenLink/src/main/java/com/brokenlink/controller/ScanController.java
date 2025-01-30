package com.brokenlink.controller;

import com.brokenlink.model.ScanResult;
import com.brokenlink.service.ScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ScanController {

    @Autowired
    private ScanService scanService;

    @GetMapping("/scan")
    public ScanResult scan(@RequestParam String url) {
        return scanService.scanWebsite(url);
    }
}
