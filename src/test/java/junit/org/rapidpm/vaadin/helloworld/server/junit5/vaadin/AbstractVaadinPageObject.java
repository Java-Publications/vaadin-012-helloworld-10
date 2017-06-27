package junit.org.rapidpm.vaadin.helloworld.server.junit5.vaadin;

import org.openqa.selenium.WebDriver;
import com.vaadin.testbench.TestBenchTestCase;

/**
 *
 */
public abstract class AbstractVaadinPageObject
    extends TestBenchTestCase
    implements VaadinPageObject {


  public AbstractVaadinPageObject(WebDriver webDriver) {
    setDriver(webDriver);
  }

  public void switchToDebugMode() {
    getDriver().get(url().get() + "?debug&restartApplication");
  }

  public void restartApplication() {
    getDriver().get(urlRestartApp().get());
  }

  public void loadPage() {
    getDriver().get(url().get());
  }

}
