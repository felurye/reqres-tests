package in.reqres.Tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import in.reqres.Data.Data;
import in.reqres.Model.UserModel;
import in.reqres.Utils.BaseAPI;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateTest extends BaseAPI{

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
	 * Realiza a requisição com o uso de uma Model, ondem os parâmetros
	 * informados são gerados aleatoriamente com usa da biblioteca Faker.
	 */
	@Test
	@DisplayName("Create User Successfully With Model")
	public void createUserSuccessfullyWithModel() {
		final Faker faker = new Faker();
		UserModel user = new UserModel();
		
		user.setName(faker.funnyName().name()); 
		user.setJob(faker.job().title()); 
		
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
			.body("name", containsString(user.getName()))
			.body("job", containsString(user.getJob()))
			.statusCode(HttpStatus.SC_CREATED);
		
		user.setId(res.jsonPath().getString("id"));
		user.setCreatedAt(res.jsonPath().getString("createdAt"));
	}
	
	
	/**
	 * Realiza a requisição com o uso de uma Model, ondem os parâmetros
	 * informados são gerados aleatoriamente com usa da biblioteca Faker
	 * com adição de caracteres para validar aceitação.
	 */
	@Test
	@DisplayName("Create User Successfully With Caracter")
	public void createUserSuccessfullyWithCaracter() {
		final Faker faker = new Faker();
		UserModel user = new UserModel();
		
		user.setName(faker.name().name() + "*&$%'"); 
		user.setJob(faker.job().title() + "*&$%'"); 
		
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
			.body("name", containsString(user.getName()))
			.body("job", containsString(user.getJob()))
			.statusCode(HttpStatus.SC_CREATED);
		
		user.setId(res.jsonPath().getString("id"));
		user.setCreatedAt(res.jsonPath().getString("createdAt"));
	}
	
	
	/**
	 * Realiza a requisição com o uso de uma Model, ondem os parâmetros
	 * informados são gerados aleatoriamente com usa da biblioteca Faker
	 * e o parâmentro name é passado nulo para validar aceitação.
	 */
	@Test
	@DisplayName("Validate Register Without Name")
	public void validateRegisterWithoutName() {
		final Faker faker = new Faker();
		UserModel user = new UserModel();
		
		user.setName(null); 
		user.setJob(faker.job().title()); 
				
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
			.body("job", containsString(user.getJob()))
			.statusCode(HttpStatus.SC_CREATED);
	}
}
