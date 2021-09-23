package in.reqres.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import in.reqres.factories.UserDataFactory;
import in.reqres.pojo.UserPojo;
import in.reqres.utils.BaseAPI;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUsersTest extends BaseAPI{

	@Test
	@DisplayName("Create User Successfully")
	public void createUserSuccessfully() {
		UserPojo user = new UserDataFactory().user();
		
		Response res =
		given()
			.contentType(ContentType.JSON)
			.body(user)
		.when()
			.post("/users");
		res.then()
			.log().all()
			.body("id", notNullValue())
			.body("createdAt", notNullValue())
			.body("name", equalTo(user.getName()))
			.body("job", equalTo(user.getJob()))
			.statusCode(HttpStatus.SC_CREATED);
	}
	
	@Test
	@DisplayName("Create User Successfully With Caracter")
	public void createUserSuccessfullyWithCaracter() {
		UserPojo user = new UserDataFactory().userWithCaracter();
	
		Response res =
		given()
			.contentType(ContentType.JSON)
			.body(user)
		.when()
			.post("/users");
		res.then()
			.log().all()
			.body("id", notNullValue())
			.body("createdAt", notNullValue())
			.body("name", equalTo(user.getName()))
			.body("job", equalTo(user.getJob()))
			.statusCode(HttpStatus.SC_CREATED);
	}

	@Test
	@DisplayName("Validate Register Without Name")
	public void validateRegisterWithoutName() {
		UserPojo user = new UserDataFactory().userWithoutName();
		given()
			.contentType(ContentType.JSON)
			.body(user)
		.when()
			.post("/users")
		.then()
			.log().all()
			.body("id", notNullValue())
			.body("createdAt", notNullValue())
			.body("name", nullValue())
			.body("job", equalTo(user.getJob()))
			.statusCode(HttpStatus.SC_CREATED);
	}
}
