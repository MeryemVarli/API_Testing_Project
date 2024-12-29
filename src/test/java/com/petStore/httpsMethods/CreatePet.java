package com.petStore.httpsMethods;

import com.petStore.payloads.Payloads;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class CreatePet {





 @Test(description = "TC03")
 public void createPetWithJsonFile(){

    File petFile= new File("src/test/resources/petStore/createPet.json");

     RestAssured.baseURI="https://petstore.swagger.io";
     RestAssured.basePath="v2/pet";


   //create a pet using jsonFile for body
     RestAssured.given().accept("application/json").contentType(ContentType.JSON)
             .body(petFile).when().post()
             .then().statusCode(200)
             .extract().response();



 //Retrieve the pet record with id
   Response response = RestAssured.given().accept(ContentType.JSON).when()
          .get("/16").then().statusCode(200)
          .body("id", Matchers.is(16))
          .body("name", Matchers.equalTo("Bowie"))
          .extract().response();




 //deserialization with jsonPath & validation
  JsonPath parsedResponse = response.jsonPath();

     int id = parsedResponse.get("id");
     Assert.assertTrue(id==16,"failed to validate the id");
     System.out.println(id);

  String expectedName="Bowie";
  String petName=   parsedResponse.get("name");
  Assert.assertEquals(petName,expectedName,"Failed to validate the name");
  System.out.println(petName);



  //updating the existing pet with the id
  String updatedPetName = "Pamuk";
  RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON)
          .body(Payloads.getPetPayload(16,updatedPetName))
          .when().put()
          .then()
          .statusCode(200)
          .body("id",Matchers.is(16))
          .body("name",Matchers.equalTo(updatedPetName))
          .extract().response();

  //Delete the pet
  RestAssured.given().accept(ContentType.JSON).when().delete("/16").then().statusCode(200).extract().statusCode();

  //get method to make sure to get 404 error after deleting the pet
  RestAssured.given().accept(ContentType.JSON).when().get("/16").then().statusCode(404).extract().response();


 }





 @Test(description = "TC04")
 public void createPetWithPayload(){

  RestAssured.baseURI="https://petstore.swagger.io";
  RestAssured.basePath="v2/pet";

//create a pet with using reusable method for the body
  Response response = RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON).body(Payloads.getPetPayload(120, "Lily"))
          .when().post().then().statusCode(200)
          .extract().response();

  System.out.println(response.prettyPrint());


//get information about the pet & parsing to validate with Groovy
  Response response2 = RestAssured.given().accept("application/json").when()
          .get("/120").then().statusCode(200).extract().response();


  int expectedId= 120;
  int id=  response2.path("id");
  Assert.assertEquals(id,expectedId,"failed to validate the id");
  System.out.println(id);


 }

























}
