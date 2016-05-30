package documentation.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
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

  @Test
  public void customerCreationDocumentation() {

    final ObjectMapper mapper = new ObjectMapper();
    final ObjectNode request = mapper.createObjectNode()
        .put("nickname", "some-nickname")
        .put("email", "some@email.com")
        .put("phone-number", "1234");


    RestAssured.given()
        .port(port)
        .log().all()
        .contentType(ContentType.JSON)
        .body(request)
    .when()
        .post("/api/users")
    .then()
        .statusCode(200);
  }

}
