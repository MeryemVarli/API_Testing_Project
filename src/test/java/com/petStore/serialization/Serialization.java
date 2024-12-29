package com.petStore.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petStore.pojo.Category;
import com.petStore.pojo.PetPojo;
import com.petStore.pojo.Tags;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.HttpsRequests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Serialization {



  @Test(description = "TC05")
  public void writeIntoJsonPet() throws IOException {

      PetPojo petPojo = new PetPojo();
      petPojo.setId(160);

      Category category = new Category();
      category.setId(1200);
      category.setName("Lilia");

      petPojo.setCategory(category);

      petPojo.setName("Kurt");

      List<String> photoUrls= new ArrayList<>();
      photoUrls.add("a dog image");
      photoUrls.add("another dog image");
      photoUrls.add("another dog image");

      petPojo.setPhotoUrls(photoUrls);

      List<Tags> dogTags= new ArrayList<>();
      Tags tags = new Tags();
      tags.setId(555);
      tags.setName("Roxy");
      dogTags.add(tags);

      petPojo.setTags(dogTags);

      petPojo.setStatus("available");


      ObjectMapper objectMapper = new ObjectMapper(); ////  ObjectMapper: This is from the Jackson library,
                                                        // which is used to serialize (convert) Java objects into JSON format.


      File file =  new File("src/test/resources/petStore/createPet2.json");
      objectMapper.writeValue(file,petPojo);




      RestAssured.baseURI="https://petstore.swagger.io";
      RestAssured.basePath="v2/pet/";

      Response response = RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON)
              .body(file).when().post()
              .then().statusCode(200).extract().response();











  }




}
