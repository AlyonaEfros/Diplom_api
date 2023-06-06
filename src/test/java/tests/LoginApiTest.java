package tests;

import lombok.LoginResponseLombokModel;
import lombok.LoginBodyLombokModel;
import org.junit.jupiter.api.Test;

import static specs.LoginSpecs.request;
import static specs.LoginSpecs.response400;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginApiTest {
    @Test
    void loginUnsuccessfulTest() {
        LoginBodyLombokModel data = new LoginBodyLombokModel();
        data.setEmail("peter@klaven");

        LoginResponseLombokModel response = given(request)
                .body(data)
                .when()
                .post("/login")
                .then()
                .log().status()
                .spec(response400)
                .extract().as(LoginResponseLombokModel.class);
        assertThat(response.getError()).isEqualTo("Missing password");
    }
}
