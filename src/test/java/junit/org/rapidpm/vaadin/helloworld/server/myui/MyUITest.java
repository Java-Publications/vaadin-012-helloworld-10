package junit.org.rapidpm.vaadin.helloworld.server.myui;

import java.util.function.Supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import junit.org.rapidpm.vaadin.helloworld.server.junit5.extensions.pageobject.PageObject;
import junit.org.rapidpm.vaadin.helloworld.server.junit5.vaadin.VaadinTest;

/**
 *
 */

@VaadinTest
public class MyUITest {

  @DisplayName("functional style")
  @Test
  void test001(@PageObject Supplier<MyUIPageObject> pageObjectSupplier) {

    final MyUIPageObject pageObject = pageObjectSupplier.get();

    pageObject.loadPage();
    pageObject.inputA.get().setValue("5");
    pageObject.inputB.get().setValue("5");

    pageObject.button.get().click();
    String value = pageObject.output.get().getAttribute("value");
    Assertions.assertEquals("55", value);
  }
}
