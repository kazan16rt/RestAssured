import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import io.qameta.allure.TmsLink;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredGetAllureTest {

    String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MmI5YzcxMGQzYjg2YTAwM2Q2N2M4MDAiLCJpYXQiOjE2NjEyNTM3MDAsImV4cCI6MTY2MTg1ODUwMH0.Kx9AViAekQkEyf6bshyaZ8n9DcPdIgJJPDSdFUkosGI";

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
    }

    @Test
    @DisplayName("Check status code of /users/me") // имя теста
    @Description("Basic test for /users/me endpoint") // описание теста
    public void getMyInfoStatusCode() {
        given()
                .auth().oauth2(bearerToken)
                .get("/api/users/me")
                .then().statusCode(200);
    }

    @Test
    @DisplayName("Check user name") // имя теста
    @Description("Basic test for /users/me endpoint") // описание теста
    public void checkUserName() {
        given()
                .auth().oauth2(bearerToken)
                .get("/api/users/me")
                .then().assertThat().body("data.name",equalTo("Василий Васильев"));
    }

    @Test
    @DisplayName("Check user name and print response body") // имя теста
    @Description("This is a more complicated test with console output") // описание теста
    @TmsLink("TestCase-112") // ссылка на тест-кейс
    @Issue("BUG-985") // ссылка на баг-репорт
    public void checkUserNameAndPrintResponseBody() {

        Response response = sendGetRequestUsersMe();
        // отправили запрос и сохранили ответ в переменную response - экземпляр класса Response

        compareUserNameToText(response, "Василий Васильев");
        // проверили, что в теле ответа ключу name соответствует нужное имя пользователя

        printResponseBodyToConsole(response); // вывели тело ответа на экран

//        Response response =given().auth().oauth2(bearerToken).get("/api/users/me");
//        // отправили запрос и сохранили ответ в переменную response - экземпляр класса Response
//
//        response.then().assertThat().body("data.name",equalTo("Василий Васильев"));
//        // проверили, что в теле ответа ключу name соответствует нужное имя пользователя
//
//        System.out.println(response.body().asString()); // вывели тело ответа на экран

    }

    // метод для шага "Отправить запрос":
    @Step("Send GET request to /api/users/me")
    public Response sendGetRequestUsersMe(){
        Response response =given().auth().oauth2(bearerToken).get("/api/users/me");
        return response;
    }

    // метод для шага "Сравнить имя пользователя с заданным":
    @Step("Compare user name to something")
    public void compareUserNameToText(Response response, String username){
        response.then().assertThat().body("data.name",equalTo(username));
    }

    // метод для шага "Вывести тело ответа в консоль":
    @Step("Print response body to console")
    public void printResponseBodyToConsole(Response response){
        System.out.println(response.body().asString());
    }



//     <properties>
//        <maven.compiler.source>11</maven.compiler.source>
//        <maven.compiler.target>11</maven.compiler.target>
//        <!-- версия Aspectj -->
//        <aspectj.version>1.9.7</aspectj.version>
//        <!-- версия Allure -->
//        <allure.version>2.15.0</allure.version>
//    </properties>
//    <build>
//        <pluginManagement>
//            <plugins>
//                <plugin>
//                    <!-- настройка плагина maven-surefire-plugin -->
//                    <groupId>org.apache.maven.plugins</groupId>
//                    <artifactId>maven-surefire-plugin</artifactId>
//                    <version>2.22.2</version>
//                    <configuration>
//                        <testFailureIgnore>false</testFailureIgnore>
//                        <argLine>
//                            -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/${aspectj.version}/aspectjweaver-${aspectj.version}.jar"
//                        </argLine>
//                        <properties>
//                            <property>
//                                <name>listener</name>
//                                <value>io.qameta.allure.junit4.AllureJunit4</value>
//                            </property>
//                        </properties>
//                        <systemProperties>
//                            <property>
//                                <!-- папка, в которую будут генерироваться отчёты Allure -->
//                                <name>allure.results.directory</name>
//    <value>${project.build.directory}/allure-results</value>
//                            </property>
//                        </systemProperties>
//                    </configuration>
//                    <dependencies>
//                        <dependency>
//                            <groupId>org.aspectj</groupId>
//                            <artifactId>aspectjweaver</artifactId>
//    <version>${aspectj.version}</version>
//                        </dependency>
//                    </dependencies>
//                </plugin>
//                <!-- подключение плагина allure-maven для вызова функций Allure с помощью Maven -->
//                <plugin>
//                    <groupId>io.qameta.allure</groupId>
//                    <artifactId>allure-maven</artifactId>
//                    <version>2.11.2</version>
//                    <configuration>
//                        <reportVersion>2.15.0</reportVersion>
//                    </configuration>
//                </plugin>
//            </plugins>
//        </pluginManagement>
//    </build>
//    <dependencies>
//        <!-- https://mvnrepository.com/artifact/io.qameta.allure/allure-junit4 -->
//        <dependency>
//            <groupId>io.qameta.allure</groupId>
//            <artifactId>allure-junit4</artifactId>
//            <version>2.19.0</version>
//            <scope>test</scope>
//        </dependency>
//        <!-- https://mvnrepository.com/artifact/io.qameta.allure/allure-rest-assured -->
//        <dependency>
//            <groupId>io.qameta.allure</groupId>
//            <artifactId>allure-rest-assured</artifactId>
//            <version>2.19.0</version>
//        </dependency>
//        <dependency>
//            <groupId>io.rest-assured</groupId>
//            <artifactId>rest-assured</artifactId>
//            <version>5.1.1</version>
//        </dependency>
//        <dependency>
//            <groupId>junit</groupId>
//            <artifactId>junit</artifactId>
//            <version>4.13.1</version>
//            <scope>compile</scope>
//        </dependency>
//        <!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->
//        <dependency>
//            <groupId>com.google.code.gson</groupId>
//            <artifactId>gson</artifactId>
//            <version>2.9.1</version>
//        </dependency>
//        <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
//        <dependency>
//            <groupId>com.fasterxml.jackson.core</groupId>
//            <artifactId>jackson-databind</artifactId>
//            <version>2.13.3</version>
//        </dependency>
//
//    </dependencies>
}
