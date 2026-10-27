package org.threeboystudios.vibium.engine;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import org.threeboystudios.vibium.configuration.BrowserConfig;

public class PlaywrightEngine implements AutoCloseable {

    private final Playwright playwright;
    private final Browser browser;
    private final BrowserContext context;
    private final Page page;

    public PlaywrightEngine() {
        this(BrowserConfig.defaultConfig());
    }

    public PlaywrightEngine(BrowserConfig config) {
        playwright = Playwright.create();

        BrowserManager browserManager = new BrowserManager();

        browser = browserManager.launch(
                playwright,
                config
        );

        context = browser.newContext();
        page = context.newPage();
    }

    public Page getPage() {
        return page;
    }

    @Override
    public void close() {
        context.close();
        browser.close();
        playwright.close();
    }
}