package junit.org.rapidpm.vaadin.helloworld.server.junit5;

import static junit.org.rapidpm.vaadin.helloworld.server.junit5.extensions.ExtensionFunctions.ipSupplierLocalIP;
import static junit.org.rapidpm.vaadin.helloworld.server.junit5.vaadin.VaadinPageObject.KEY_VAADIN_SERVER_IP;
import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL;
import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_FAILING;

import java.io.File;
import java.util.List;

import com.github.dockerjava.api.model.VolumesFrom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.rapidpm.vaadin.helloworld.server.Main;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.BrowserWebDriverContainer;
import junit.org.rapidpm.vaadin.helloworld.server.myui.MyUIPageObject;
import org.testcontainers.containers.output.Slf4jLogConsumer;

/**
 *
 */
public class TestContainerTest {

    private BrowserWebDriverContainer webDriverContainer;

    @BeforeEach
    void setUp() {
        Main.start();

        webDriverContainer = new BrowserWebDriverContainer()
                .withDesiredCapabilities(DesiredCapabilities.chrome());

        webDriverContainer.start();

        Slf4jLogConsumer logConsumer = new Slf4jLogConsumer(LoggerFactory.getLogger(this.getClass()));
        webDriverContainer.followOutput(logConsumer);
    }

    @AfterEach
    void tearDown() {
        webDriverContainer.stop();
        Main.shutdown();
    }


    @Test
    void test001() {

        String workingDirectory = webDriverContainer.getWorkingDirectory();
        System.out.println("workingDirectory = " + workingDirectory);

        System.setProperty(KEY_VAADIN_SERVER_IP, ipSupplierLocalIP.get());

        RemoteWebDriver webDriver = webDriverContainer.getWebDriver();
        MyUIPageObject pageObject = new MyUIPageObject(webDriver);
        pageObject.loadPage();

        pageObject.inputA.get().sendKeys("5");
        pageObject.inputB.get().sendKeys("5");

        pageObject.button.get().click();
        String value = pageObject.output.get().getAttribute("value");
        //Assertions.assertEquals("10", value);
        //Assertions.assertEquals("55", value);

    }
}
