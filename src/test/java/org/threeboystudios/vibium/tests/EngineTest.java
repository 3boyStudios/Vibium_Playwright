package org.threeboystudios.vibium.tests;

import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Test;
import org.threeboystudios.vibium.engine.PlaywrightEngine;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EngineTest {

    @Test
    void shouldLaunchBrowser() {

        try (PlaywrightEngine engine = new PlaywrightEngine()) {

            Page page = engine.getPage();

            page.navigate("https://example.com");

            assertEquals(
                    "Example Domain",
                    page.title()
            );
        }
    }
}