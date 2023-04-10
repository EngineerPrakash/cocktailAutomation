package cocktailAutomation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class GetIngredientsByName extends BaseTest {

    @DataProvider(name = "ingredientsNames")
    public Object[][] getIngredientsNames() {
        return new Object[][] {
                {"vodka"},
                {"Lemonade"}
        };
    }

    @Test(description = "Verify that the status code is 200 for ingredients api",dataProvider = "ingredientsNames")
    public void getIngredientStatus(String ingredientsName) {

        Response response = RestAssured.given(spec).queryParam("i", ingredientsName)
                .get();
        response.print();

        //Verify response is 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is 200 but it is not present");


    }

    @Test(description = "Verify the response time of the ingredients api is within specified limit",dataProvider = "ingredientsNames")
    public void getResponseTimeIngredientByName(String ingredientsName) {
        Response response = RestAssured.given(spec).queryParam("i", ingredientsName)
                .get();
        response.print();
        long responseTime = response.getTime();
        //Verify response is 200
        Assert.assertTrue(responseTime<5000,"response time is greater than specified limit (5 sec)");

    }

    @Test(description = "Verify that the Alcohol field is yes and ABV field is not null for an alcoholic ingredient",dataProvider = "ingredientsNames")
    public void verifyAlcoholicValuesIngredients(String ingredientName) {

        Response response = RestAssured.given(spec).queryParam("i", ingredientName)
                .get();
        response.print();
        String strAlcohol= response.jsonPath().getString("ingredients.strAlcohol");
        if (strAlcohol.equals("[No]")) {
            response.then()
                    .assertThat()
                    .body("ingredients.find { it.strIngredient = 'ingredientName' }.strAlcohol", equalTo("No"))
                    .body("ingredients.find { it.strIngredient = 'ingredientName' }.strABV", equalTo(null));
        }
        else{
            response.then()
                    .assertThat()
                    .body("ingredients.find { it.strIngredient = 'ingredientName' }.strAlcohol", equalTo("Yes"))
                    .body("ingredients.find { it.strIngredient = 'ingredientName' }.strABV", notNullValue());
        }

    }

    @Test(description = "Verify the response contains the expected fields",dataProvider = "ingredientsNames")
    public void verifyResponseStructure(String ingredientName) {

        Response response = RestAssured.given(spec).queryParam("i", ingredientName)
                .get();
        response.print();
        String strAlcohol= response.jsonPath().getString("ingredients.strAlcohol");
        if (strAlcohol.equals("[No]")){
            response.then()
                    .assertThat()
                    .body("ingredients[0].idIngredient", notNullValue())
                    .body("ingredients[0].strIngredient", notNullValue())
                    .body("ingredients[0].strDescription", notNullValue())
                    .body("ingredients[0].strType", notNullValue())
                    .body("ingredients[0].strAlcohol", notNullValue())
                    .body("ingredients[0].strABV", equalTo(null));

        }
        else{
            response.then()
                    .body("ingredients[0].idIngredient", notNullValue())
                    .body("ingredients[0].strIngredient", notNullValue())
                    .body("ingredients[0].strDescription", notNullValue())
                    .body("ingredients[0].strType", notNullValue())
                    .body("ingredients[0].strAlcohol", notNullValue())
                    .body("ingredients[0].strABV", notNullValue());
        }


    }

}
