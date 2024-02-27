package graph.tests;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import graph.resources.TestDataBuild;
import graph.resources.Utils;
import io.restassured.specification.RequestSpecification;

public class GrapghQlTest extends Utils {

	static int locId;
	static int charId;
	static int epiId;
	
	
	@Test
	public void mutation() throws Exception {
		
		RequestSpecification reqMutBody = given()
				.spec(getRequestSpecification()).body(TestDataBuild.mutationPayload());
		
		String response = reqMutBody.when().post("https://rahulshettyacademy.com/gq/graphql")
						.then().assertThat().statusCode(200).extract().response().asString();
		
		locId = Integer.parseInt(getJsonPath(response , "data.createLocation.id"));
		charId = Integer.parseInt(getJsonPath(response , "data.createCharacter.id"));
		epiId = Integer.parseInt(getJsonPath(response , "data.createEpisode.id"));
	}
	
	@Test(dependsOnMethods ={"mutation"})
	public void query() throws Exception {
		
		List<Integer> epiIds = Arrays.asList(epiId,4000, 5000);
		
		RequestSpecification reqQueBody = given()
				.spec(getRequestSpecification())
				.body(TestDataBuild.queryPayload(locId, charId, epiIds));
		
		String response = reqQueBody.when().get("https://rahulshettyacademy.com/gq/graphql")
		.then().extract().response().asString();
		
		String characterName = getJsonPath(response,"data.character.name");
		
		Assert.assertEquals(characterName, "Asta");
	}
}
