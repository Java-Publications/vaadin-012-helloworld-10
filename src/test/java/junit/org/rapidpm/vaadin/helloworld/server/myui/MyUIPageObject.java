package junit.org.rapidpm.vaadin.helloworld.server.myui;

import static org.rapidpm.vaadin.helloworld.server.MyUI.BUTTON_ID;
import static org.rapidpm.vaadin.helloworld.server.MyUI.INPUT_ID_A;
import static org.rapidpm.vaadin.helloworld.server.MyUI.INPUT_ID_B;
import static org.rapidpm.vaadin.helloworld.server.MyUI.OUTPUT_ID;

import java.util.function.Supplier;

import org.openqa.selenium.WebDriver;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.TextFieldElement;
import junit.org.rapidpm.vaadin.helloworld.server.junit5.vaadin.AbstractVaadinPageObject;
import junit.org.rapidpm.vaadin.helloworld.server.junit5.vaadin.WithID;

/**
 *
 */
public class MyUIPageObject extends AbstractVaadinPageObject {


  public MyUIPageObject(WebDriver webDriver) {
    super(webDriver);
  }

  @Override
  public String toString() {
    return "MyUIPageObject{" + this.getDriver().getClass().getSimpleName() + '}';
  }


  public WithID<TextFieldElement> textField = (id) -> $(TextFieldElement.class).id(id);
  public WithID<ButtonElement> btn = (id) -> $(ButtonElement.class).id(id);

  public Supplier<ButtonElement> button = () -> btn.id(BUTTON_ID);
  public Supplier<TextFieldElement> output = () -> textField.id(OUTPUT_ID);
  public Supplier<TextFieldElement> inputA = () -> textField.id(INPUT_ID_A);
  public Supplier<TextFieldElement> inputB = () -> textField.id(INPUT_ID_B);

}
