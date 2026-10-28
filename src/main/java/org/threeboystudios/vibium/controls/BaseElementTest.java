package org.threeboystudios.vibium.controls;

import org.junit.jupiter.api.Test;
import org.threeboystudios.vibium.engine.PlaywrightEngine;

import static org.junit.jupiter.api.Assertions.*;

class BaseElementTest {

    @Test
    void shouldIdentifyElementState() {

        try (PlaywrightEngine engine = new PlaywrightEngine()) {

            engine.getPage().setContent("<html>\n" +
                                        "    <body>\n" +
                                        "        <button id=\"active\">Submit</button>\n" +
                                        "        <button id=\"disabled\" disabled>Disabled</button>\n" +
                                        "        <button id=\"hidden\" style=\"display:none\">\n" +
                                        "            Hidden\n" +
                                        "        </button>\n" +
                                        "    </body>\n" +
                                        "</html>\n");

            BaseElement activeButton = new BaseElement(
                    engine.getPage(),
                    "#active",
                    "Active Button"
            ) {};

            BaseElement disabledButton = new BaseElement(
                    engine.getPage(),
                    "#disabled",
                    "Disabled Button"
            ) {};

            BaseElement hiddenButton = new BaseElement(
                    engine.getPage(),
                    "#hidden",
                    "Hidden Button"
            ) {};

            BaseElement missingButton = new BaseElement(
                    engine.getPage(),
                    "#missing",
                    "Missing Button"
            ) {};

            assertFalse(missingButton.exists());

            System.out.println("Active Button: " + activeButton.getName() + ", Exists: " + activeButton.exists() + ", Visible: " + activeButton.isVisible() + ", Enabled: " + activeButton.isEnabled());
            System.out.println("Disabled Button: " + disabledButton.getName() + ", Exists: " + disabledButton.exists() + ", Visible: " + disabledButton.isVisible() + ", Enabled: " + disabledButton.isEnabled());
            System.out.println("Hidden Button: " + hiddenButton.getName() + ", Exists: " + hiddenButton.exists() + ", Visible: " + hiddenButton.isVisible() + ", Enabled: " + hiddenButton.isEnabled());

            assertTrue(activeButton.exists());
            assertTrue(activeButton.isVisible());
            assertTrue(activeButton.isEnabled());

            assertTrue(disabledButton.exists());
            assertTrue(disabledButton.isVisible());
            assertFalse(disabledButton.isEnabled());

            assertTrue(hiddenButton.exists());
            assertFalse(hiddenButton.isVisible());

            assertEquals("Active Button", activeButton.getName());
            assertEquals("Disabled Button", disabledButton.getName());
            assertEquals("Hidden Button", hiddenButton.getName());
        }
    }

    @Test
    void shouldWaitUntilElementBecomesVisible() {

        try (PlaywrightEngine engine = new PlaywrightEngine()) {

            engine.getPage().setContent("<html>\n" +
                                        "    <body>\n" +
                                        "        <button id=\"delayed\" style=\"display:none\">\n" +
                                        "            Ready\n" +
                                        "        </button>\n" +
                                        "\n" +
                                        "        <script>\n" +
                                        "            setTimeout(() => {\n" +
                                        "                document.getElementById('delayed')\n" +
                                        "                    .style.display = 'block';\n" +
                                        "            }, 300);\n" +
                                        "        </script>\n" +
                                        "    </body>\n" +
                                        "</html>\n");

            BaseElement element = new BaseElement(
                    engine.getPage(),
                    "#delayed",
                    "Delayed Button"
            ) {};

            element.waitUntilVisible();

            assertTrue(element.isVisible());
        }
    }

    @Test
    void shouldWaitUntilElementBecomesHidden() {

        try (PlaywrightEngine engine = new PlaywrightEngine()) {

            engine.getPage().setContent("<html>\n" +
                                        "    <body>\n" +
                                        "        <button id=\"temporary\">Processing</button>\n" +
                                        "\n" +
                                        "        <script>\n" +
                                        "            setTimeout(() => {\n" +
                                        "                document.getElementById('temporary')\n" +
                                        "                    .style.display = 'none';\n" +
                                        "            }, 300);\n" +
                                        "        </script>\n" +
                                        "    </body>\n" +
                                        "</html>\n");

            BaseElement element = new BaseElement(
                    engine.getPage(),
                    "#temporary",
                    "Temporary Button"
            ) {};

            element.waitUntilHidden();

            assertFalse(element.isVisible());
        }
    }
}