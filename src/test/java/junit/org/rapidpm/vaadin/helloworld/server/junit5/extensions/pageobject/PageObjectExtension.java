package junit.org.rapidpm.vaadin.helloworld.server.junit5.extensions.pageobject;

import static junit.org.rapidpm.vaadin.helloworld.server.junit5.extensions.ExtensionFunctions.ipSupplierLocalIP;
import static junit.org.rapidpm.vaadin.helloworld.server.junit5.extensions.testcontainers.TestcontainersExtension.webdriver;
import static junit.org.rapidpm.vaadin.helloworld.server.junit5.vaadin.VaadinPageObject.KEY_VAADIN_SERVER_IP;

import java.util.function.Supplier;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ContainerExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import junit.org.rapidpm.vaadin.helloworld.server.myui.MyUIPageObject;

/**
 *
 */
public class PageObjectExtension implements ParameterResolver, BeforeAllCallback {

  @Override
  public boolean supports(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
    return parameterContext.getParameter().isAnnotationPresent(PageObject.class);
  }

  @Override
  public Supplier<MyUIPageObject> resolve(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
    return () -> new MyUIPageObject(webdriver().apply(extensionContext).get());
  }

  @Override
  public void beforeAll(ContainerExtensionContext context) throws Exception {
    System.setProperty(KEY_VAADIN_SERVER_IP, ipSupplierLocalIP.get());
  }
}
