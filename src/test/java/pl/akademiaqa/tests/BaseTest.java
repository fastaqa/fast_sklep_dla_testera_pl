package pl.akademiaqa.tests;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import pl.akademiaqa.factory.BrowserFactory;
import pl.akademiaqa.utils.Properties;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static pl.akademiaqa.utils.StringUtils.removeRoundBrackets;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    private BrowserFactory browserFactory;
    private Browser browser;

    protected BrowserContext browserContext;
    protected Page page;

    @BeforeAll
    void launchBrowser() {

        System.out.println("READING SECRETS FROM GT: " + System.getProperty("API_KEY"));
        System.out.println("READING VARIABLES FROM GT: " + System.getProperty("UI_URL"));

        browserFactory = new BrowserFactory();
        browser = browserFactory.getBrowser();
    }

    @BeforeEach
    void createContextAndPage() {
        browserContext = browser.newContext();

        if (isTracingEnabled()) {
            browserContext.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));
        }

        page = browserContext.newPage();
        page.setViewportSize(1920, 1080);
        page.navigate(Properties.getProperty("app.url"));
    }

    @AfterEach
    void closeContext(TestInfo testInfo) {
        if (isTracingEnabled()) {
            String traceName = "traces/trace_"
                    + removeRoundBrackets(testInfo.getDisplayName())
                    + "_" + LocalDateTime.now().format(DateTimeFormatter
                    .ofPattern(Properties.getProperty("tracing.date.format"))) + ".zip";
            browserContext.tracing().stop(new Tracing.StopOptions().setPath(Paths.get(traceName)));
        }

        browserContext.close();
    }

    @AfterAll
    void closeBrowser() {
        browserFactory.getPlaywright().close();
    }

    private boolean isTracingEnabled() {
        return Boolean.parseBoolean(Properties.getProperty("tracing.enabled"));
    }
}
