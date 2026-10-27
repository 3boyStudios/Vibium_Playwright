package org.threeboystudios.vibium.engine;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.threeboystudios.vibium.configuration.BrowserConfig;

public class BrowserManager {

    public Browser launch(
            Playwright playwright,
            BrowserConfig config) {

        BrowserType.LaunchOptions options =
                new BrowserType.LaunchOptions()
                        .setHeadless(config.isHeadless());

        if (config.getChannel() != null) {
            options.setChannel(config.getChannel());
        }

        return switch (config.getBrowserType()) {

            case CHROMIUM ->
                    playwright.chromium().launch(options);

            case FIREFOX ->
                    playwright.firefox().launch(options);

            case WEBKIT ->
                    playwright.webkit().launch(options);
        };
    }
}