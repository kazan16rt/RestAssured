import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;

public class Praktikum {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
    }

//    @Test
//    public void checkUserName() {
//        given()
//                .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MmI5YzcxMGQzYjg2YTAwM2Q2N2M4MDAiLCJpYXQiOjE2NjA1OTM4OTUsImV4cCI6MTY2MTE5ODY5NX0.J8yyKCn1-WURksL88u3F4uHeGuHEWWyQMSzL7XPCKeM")
//                .get("/api/users/me")
//                .then().assertThat().body("data.name", equalTo("Аристарх Сократович"));
//    }

    File json = new File("src/test/resources/newCard.json");
    @Test
    public void addNewCard() {
        Response response = given()
                .header("Content-type", "application/json")
                .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MmI5YzcxMGQzYjg2YTAwM2Q2N2M4MDAiLCJpYXQiOjE2NjA1OTM4OTUsImV4cCI6MTY2MTE5ODY5NX0.J8yyKCn1-WURksL88u3F4uHeGuHEWWyQMSzL7XPCKeM")
                .and()
                .body(json)
                .when()
                .post("/api/cards");
        response.then().assertThat().body("data._id", notNullValue())
                .and()
                .statusCode(201);
    }


} 