package documentation.api;

import com.jayway.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Test;
import play.test.WithServer;

public class UsersControllerDocumentation extends WithServer {

  @Test
  public void userCreationDocumentation() {
    RestAssured.given()
      .port(port)
      .log().all()
    .when()
      .get("/")
    .then()
      .body(Matchers.equalTo("Play API documentation with Slate"));
  }

}
