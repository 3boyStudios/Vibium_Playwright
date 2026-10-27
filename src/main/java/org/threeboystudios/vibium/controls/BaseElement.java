package org.threeboystudios.vibium.controls;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import org.threeboystudios.vibium.contracts.Element;
import org.threeboystudios.vibium.contracts.Waitable;

public abstract class BaseElement implements Element, Waitable {

    protected final Page page;
    protected final Locator locator;
    private final String name;

    protected BaseElement(Page page, String selector, String name) {
        this.page = page;
        this.locator = page.locator(selector);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
public boolean exists() {
        return locator.count() > 0;
    }

    @Override
    public boolean isVisible() {
        return locator.isVisible();
    }

    @Override
    public boolean isEnabled() {
        return locator.isEnabled();
    }

    @Override
    public void waitUntilVisible() {
        locator.waitFor();
    }

    @Override
    public void waitUntilHidden() {
        locator.waitFor(
                new Locator.WaitForOptions()
                        .setState(
                                com.microsoft.playwright.options.WaitForSelectorState.HIDDEN
                        )
        );
    }
}