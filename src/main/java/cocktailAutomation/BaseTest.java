package cocktailAutomation;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
  protected RequestSpecification spec;
    @BeforeMethod
    public void setUp(){
        spec   = new RequestSpecBuilder()
                .setBaseUri("https://www.thecocktaildb.com/api/json/v1/1/search.php").build();

    }
}
