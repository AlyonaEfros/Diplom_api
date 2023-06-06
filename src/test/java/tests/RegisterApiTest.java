package tests;

import lombok.LoginBodyLombokModel;
import lombok.LoginResponseLombokModel;
import org.junit.jupiter.api.Test;

import static specs.LoginSpecs.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class RegisterApiTest {
    @Test
    void registerSuccessfulTest() {
        LoginBodyLombokModel data = new LoginBodyLombokModel();
        data.setEmail("eve.holt@reqres.in");
        data.setPassword("pistol");

        LoginResponseLombokModel response = given(request)
                .body(data)
                .when()
                .post("/register")
                .then()
                .log().status()
                .spec(response200)
                .extract().as(LoginResponseLombokModel.class);
        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");

    }

    @Test
    void registerUnsuccessfulTest() {
        LoginBodyLombokModel data = new LoginBodyLombokModel();
        data.setEmail("sydney@fife");

        LoginResponseLombokModel response = given(request)
                .body(data)
                .when()
                .post("/register")
                .then()
                .log().status()
                .spec(response400)
                .extract().as(LoginResponseLombokModel.class);
        assertThat(response.getError()).isEqualTo("Missing password");
    }
}
