package junit.org.rapidpm.vaadin.helloworld.server.junit5.extensions.testcontainers;

import static junit.org.rapidpm.vaadin.helloworld.server.junit5.extensions.ExtensionFunctions.ipSupplierLocalIP;
import static junit.org.rapidpm.vaadin.helloworld.server.junit5.extensions.ExtensionFunctions.store;
import static junit.org.rapidpm.vaadin.helloworld.server.junit5.extensions.ExtensionFunctions.storeObjectIn;
import static junit.org.rapidpm.vaadin.helloworld.server.junit5.vaadin.VaadinPageObject.KEY_VAADIN_SERVER_IP;
import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL;

import java.io.File;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExtensionContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import junit.org.rapidpm.vaadin.helloworld.server.junit5.extensions.ExtensionFunctions;

/**
 *
 */
public class TestcontainersExtension
    implements BeforeEachCallback, AfterEachCallback {

  public static final String WEBDRIVER = "webdriver";
  public static final String TESTCONTAINER = "testcontainer";

  public static Function<ExtensionContext, BrowserWebDriverContainer> testcontainer() {
    return (context) -> store().apply(context).get(TESTCONTAINER, BrowserWebDriverContainer.class);
  }

  public static BiConsumer<ExtensionContext, BrowserWebDriverContainer> storeTestcontainer() {
    return (context, webDriver) -> store().apply(context).put(TESTCONTAINER, webDriver);
  }

  public static Consumer<ExtensionContext> removeTestcontainer() {
    return (context) -> store().apply(context).remove(TESTCONTAINER);
  }

  public static Function<ExtensionContext, Supplier<WebDriver>> webdriver() {
    return (context) -> store().apply(context).get(WEBDRIVER, Supplier.class);
  }

  public static BiConsumer<ExtensionContext, Supplier<WebDriver>> storeWebDriver() {
    return (context, webDriver) -> store().apply(context).put(WEBDRIVER, webDriver);
  }

  public static Consumer<ExtensionContext> removeWebDriver() {
    return (context) -> store().apply(context).remove(WEBDRIVER);
  }

  @Override
  public void beforeEach(TestExtensionContext context) throws Exception {
    BrowserWebDriverContainer webDriverContainer
        = new BrowserWebDriverContainer()
        .withDesiredCapabilities(DesiredCapabilities.chrome()); // only one per container

    webDriverContainer.start();

    Slf4jLogConsumer logConsumer = new Slf4jLogConsumer(LoggerFactory.getLogger(this.getClass()));
    webDriverContainer.followOutput(logConsumer);

    storeTestcontainer().accept(context, webDriverContainer);
    storeWebDriver().accept(context, webDriverContainer::getWebDriver);
  }


  @Override
  public void afterEach(TestExtensionContext context) throws Exception {
    testcontainer().apply(context).stop();
    removeTestcontainer().accept(context);
    removeWebDriver();
  }
}
