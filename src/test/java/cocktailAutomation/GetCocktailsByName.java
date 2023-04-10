package cocktailAutomation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.notNullValue;


public class GetCocktailsByName extends BaseTest {
    @DataProvider(name = "cocktailNames")
    public Object[][] getCocktailNames() {
        return new Object[][] {
                {"margarita"},
                {"mojito"},
                {"cosmopolitan"}
        };
    }
    @Test(description = "Verify that the status code is 200 for cocktail api",dataProvider = "cocktailNames")
    public void getCocktailStatus(String cocktailName) {

        Response response = RestAssured.given(spec).queryParam("s", cocktailName)
                .get();
        response.print();

        //Verify response is 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is 200 but it is not present");


    }

    @Test(description = "Verify the response time of the cocktail api is within specified limit",dataProvider = "cocktailNames")
    public void getResponseTimeCocktailByName(String cocktailName) {
        Response response = RestAssured.given(spec).queryParam("s", cocktailName)
                .get();
        response.print();
        long responseTime = response.getTime();
        //Verify response is 200
        Assert.assertTrue(responseTime < 5000, "response time is greater than specified limit (5 sec)");

    }

    @Test(description = "Verify that drinks is not Null",dataProvider = "cocktailNames")
    public void verifyDrinksStatus(String cocktailName) {

        Response response = RestAssured.given(spec).queryParam("s", cocktailName)
                .get();
        response.print();
        response.then()
                .assertThat().body("drinks", notNullValue());

    }

    @Test(description = "verify that the response is case-insensitive",dataProvider = "cocktailNames")
    public void verifyCaseInSensitiveIngredients(String cocktailName) {
        Response response = RestAssured.given(spec).queryParam("s", cocktailName)
                .get();
        response.print();
        String responseBody = response.getBody().asString();
        String cocktailNameLowerCase = cocktailName.toLowerCase();
        assert responseBody.toLowerCase().contains(cocktailNameLowerCase);

    }

    @Test(description = "Verify the response contains the expected fields for cocktail api",dataProvider = "cocktailNames")
    public void verifyResponseStructure(String cocktailName) {

        Response response = RestAssured.given(spec).queryParam("s", cocktailName)
                .get();
        response.print();
        response.then().assertThat().body("drinks.strDrink", notNullValue());
        response.then().assertThat().body("drinks.strDrinkAlternate", notNullValue());
        response.then().assertThat().body("drinks.strTags", notNullValue());
        response.then().assertThat().body("drinks.strVideo", notNullValue());
        response.then().assertThat().body("drinks.strCategory", notNullValue());
        response.then().assertThat().body("drinks.strIBA", notNullValue());
        response.then().assertThat().body("drinks.strAlcoholic", notNullValue());
        response.then().assertThat().body("drinks.strGlass", notNullValue());
        response.then().assertThat().body("drinks.strInstructions", notNullValue());
        response.then().assertThat().body("drinks.strDrinkThumb", notNullValue());
        response.then().assertThat().body("drinks.dateModified", notNullValue());
    }

}
