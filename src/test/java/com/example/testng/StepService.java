package com.example.testng;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class StepService {

    private final Playwright playwright;
    private final Browser browser;

    public StepService() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                (new BrowserType.LaunchOptions())
                        .setHeadless(false)
                        .setTimeout(120000.0)
                        .setSlowMo(500.0)
        );
    }

    public Playwright getPlaywright() {
        return playwright;
    }

    public Browser getBrowser() {
        return browser;
    }

}
