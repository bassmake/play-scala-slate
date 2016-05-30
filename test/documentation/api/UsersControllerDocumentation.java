package documentation.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.http.Method;
import com.jayway.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.templates.TemplateFormats;
import play.test.WithServer;

import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

public class UsersControllerDocumentation extends WithServer {

  private RequestSpecification spec;

  @Rule
  public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

  @Before
  public void setUp() {
    spec = new RequestSpecBuilder().addFilter(
      documentationConfiguration(restDocumentation).snippets().withTemplateFormat(TemplateFormats.markdown())
    ).build();
  }

  @Test
  public void userCreationDocumentation() {
    RestAssured.given(spec)
      .port(port)
      .log().all()
    .filter(document("index"))
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

    RestAssured.given(spec)
        .port(port)
        .log().all()
        .contentType(ContentType.JSON)
        .filter(document("user-creation", requestFields(
            fieldWithPath("nickname").type(JsonFieldType.STRING).description("Nickname of the user"),
            fieldWithPath("email").type(JsonFieldType.STRING).description("Email of the user"),
            fieldWithPath("phone-number").type(JsonFieldType.STRING).description("Phone number of the user")
        )))
        .body(request)
    .when()
        .post("/api/users")
    .then()
        .statusCode(200);
  }

}
