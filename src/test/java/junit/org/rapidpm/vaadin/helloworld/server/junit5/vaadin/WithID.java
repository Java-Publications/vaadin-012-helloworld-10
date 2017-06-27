package junit.org.rapidpm.vaadin.helloworld.server.junit5.vaadin;

import com.vaadin.testbench.elementsbase.AbstractElement;

/**
 *
 */
@FunctionalInterface
public interface WithID<T extends AbstractElement> {
  T id(String id);
}
