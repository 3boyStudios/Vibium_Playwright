package org.threeboystudios.vibium.controls;

import com.microsoft.playwright.Page;

import org.threeboystudios.vibium.contracts.Clickable;

public class Button extends BaseElement implements Clickable {

    public Button(
            Page page,
            String selector,
            String name) {

        super(page, selector, name);
    }

    @Override
    public void click() {
        locator.click();
    }

    @Override
    public void doubleClick() {
        locator.dblclick();
    }
}