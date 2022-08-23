import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.hamcrest.MatcherAssert;

public class Praktikum1 {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
    }

    @Test
    public void checkUserName() {
        User user = given()
                .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MmI5YzcxMGQzYjg2YTAwM2Q2N2M4MDAiLCJpYXQiOjE2NjA1OTM4OTUsImV4cCI6MTY2MTE5ODY5NX0.J8yyKCn1-WURksL88u3F4uHeGuHEWWyQMSzL7XPCKeM")
                .get("/api/users/me")
                .body().as(User.class); // напиши код для десериализации ответа в объект типа User
        // выбери подходящий assert
        MatcherAssert.assertThat(user, notNullValue());
    }
}