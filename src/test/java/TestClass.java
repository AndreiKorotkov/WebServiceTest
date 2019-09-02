import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestClass {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
    }

    @Test
    public void webStatusTest() {
        Response response = RestAssured.when()
                .get("/users")
                .andReturn();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void webHeaderTest() {
        Response response = RestAssured.when()
                .get("/users")
                .andReturn();
        String responseHeaderContentType = response.getHeader("Content-type");
        Assert.assertNotNull(responseHeaderContentType);
        Assert.assertEquals(responseHeaderContentType, "application/json; charset=utf-8");
    }

    @Test
    public void webBodyTest() {
        Response response = RestAssured.when()
                .get("/users")
                .andReturn();
        User[] users = response.getBody().as(User[].class);
        Assert.assertEquals(users.length, 10);
    }
}
