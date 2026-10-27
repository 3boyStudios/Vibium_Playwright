package org.threeboystudios.vibium.controls;

import org.junit.jupiter.api.Test;
import org.threeboystudios.vibium.engine.PlaywrightEngine;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ButtonTest {

    @Test
    void shouldClickButton() {

        try (PlaywrightEngine engine = new PlaywrightEngine()) {

            engine.getPage().setContent("<html>\n" +
                                        "    <body>\n" +
                                        "        <button id=\"submit\">Submit</button>\n" +
                                        "        <div id=\"result\"></div>\n" +
                                        "\n" +
                                        "        <script>\n" +
                                        "            document.getElementById('submit')\n" +
                                        "                .addEventListener('click', function() {\n" +
                                        "                    document.getElementById('result')\n" +
                                        "                        .textContent = 'Clicked';\n" +
                                        "                });\n" +
                                        "        </script>\n" +
                                        "    </body>\n" +
                                        "</html>\n");

            Button submitButton = new Button(
                    engine.getPage(),
                    "#submit",
                    "Submit Button"
            );

            submitButton.click();

            assertEquals(
                    "Clicked",
                    engine.getPage()
                            .locator("#result")
                            .textContent()
            );
        }
    }
}