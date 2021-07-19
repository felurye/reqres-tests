package in.reqres.Utils;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

import org.junit.jupiter.api.BeforeAll;

public class BaseAPI {

	@BeforeAll
	public static void preCondition() {
		baseURI = "https://reqres.in";
		basePath = "/api";
	}

}
