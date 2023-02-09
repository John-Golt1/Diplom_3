package ui.user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ui.Client;
import ui.data.AuthorizationData;
import ui.data.User;

import static io.restassured.RestAssured.given;
import static ui.Constants.*;

public class UserClient extends Client {
    @Step("Create new user in system")
    public ValidatableResponse createUser(User user) {
        return given().log().all()
                .spec(getSpec())
                .body(user)
                .when()
                .post(CREATE_NEW_USER_URL)
                .then();
    }
    @Step("Login user in system with email and password")
    public ValidatableResponse loginUser(AuthorizationData authorizationData) {
        return given().log().all()
                .spec(getSpec())
                .body(authorizationData)
                .when()
                .post(LOGIN_USER_URL)
                .then();
    }
    @Step("Delete user by token")
    public ValidatableResponse deleteUser(String accessToken) {
        return given().log().all()
                .spec(getSpec())
                .auth().oauth2(accessToken)
                .when()
                .delete(USER_DATA_URL)
                .then();
    }
}