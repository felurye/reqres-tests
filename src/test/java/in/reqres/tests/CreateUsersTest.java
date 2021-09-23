package in.reqres.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import java.util.Map;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import in.reqres.data.Data;
import in.reqres.factories.UserDataFactory;
import in.reqres.pojo.UserPojo;
import in.reqres.utils.BaseAPI;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUsersTest extends BaseAPI{

	/**
	 * Realiza a requisição com o uso do método Map do java.util
	 * usando parâmetros já definidos na classe.
	 */
	@Test
	@SuppressWarnings("rawtypes")
	@DisplayName("Create User Successfully With Map")
	public void createUserSuccessfullyWithMap() {
		Data data = new Data();
		Map dataUser = data.createUser();
				
		given()
			.contentType(ContentType.JSON)
			.body(dataUser)
		.when()
			.post("/users")
		.then()
			.log().all()
			.body("id", notNullValue())
			.body("createdAt", notNullValue())
			.statusCode(HttpStatus.SC_CREATED);
	}
	
	/**
	 * Realiza a requisição com o uso de um Pojo, ondem os parâmetros
	 * informados são gerados aleatoriamente com usa da biblioteca Faker.
	 */
	@Test
	@DisplayName("Create User Successfully With Pojo")
	public void createUserSuccessfullyWithPojo() {
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
	
	
	/**
	 * Realiza a requisição com o uso de um Pojo, ondem os parâmetros
	 * informados são gerados aleatoriamente com usa da biblioteca Faker
	 * com adição de caracteres para validar aceitação.
	 */
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
	
	
	/**
	 * Realiza a requisição com o uso de um Pojo, ondem os parâmetros
	 * informados são gerados aleatoriamente com usa da biblioteca Faker
	 * e o parâmentro name é passado nulo para validar aceitação.
	 */
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
