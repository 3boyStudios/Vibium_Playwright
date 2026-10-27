package org.threeboystudios.vibium.configuration;

public final class BrowserConfig {

    public enum BrowserType {
        CHROMIUM,
        FIREFOX,
        WEBKIT
    }

    private final BrowserType browserType;
    private final boolean headless;
    private final String channel;

    private BrowserConfig(
            BrowserType browserType,
            boolean headless,
            String channel) {

        this.browserType = browserType;
        this.headless = headless;
        this.channel = channel;
    }

    public static BrowserConfig defaultConfig() {
        return new BrowserConfig(
                BrowserType.CHROMIUM,
                true,
                null
        );
    }

    public static Builder builder() {
        return new Builder();
    }

    public BrowserType getBrowserType() {
        return browserType;
    }

    public boolean isHeadless() {
        return headless;
    }

    public String getChannel() {
        return channel;
    }

    public static class Builder {

        private BrowserType browserType = BrowserType.CHROMIUM;
        private boolean headless = true;
        private String channel = null;

        public Builder browserType(BrowserType browserType) {
            this.browserType = browserType;
            return this;
        }

        public Builder headless(boolean headless) {
            this.headless = headless;
            return this;
        }

        public Builder channel(String channel) {
            this.channel = channel;
            return this;
        }

        public BrowserConfig build() {
            return new BrowserConfig(
                    browserType,
                    headless,
                    channel
            );
        }
    }
}