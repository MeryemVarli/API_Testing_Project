package com.petStore.get;

import com.petStore.pojo.PetPojo;
import com.petStore.pojo.Tags;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.HttpsRequests;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PetStore {



 @Test (description = "TC01")
 public void getDogWithPojo(){

   //parsing the response with Pojo

     RestAssured.baseURI="https://petstore.swagger.io";
     RestAssured.basePath="v2/pet/14";

     Response response = HttpsRequests.GET();

     PetPojo parsedResponse = response.as(PetPojo.class); //kinda creating object from petPojo then i can talk to properties in there

     int id = parsedResponse.getId();
     String dogName = parsedResponse.getName();
     String status = parsedResponse.getStatus();

     Assert.assertTrue(id==14,"Failed to validate the id");

     System.out.println("id : "+id+" name is "+dogName+" "+status);

     int categoryId = parsedResponse.getCategory().getId();
     String categoryName = parsedResponse.getCategory().getName();

     List<String> photoUrls = parsedResponse.getPhotoUrls();

     List<Tags> tags = parsedResponse.getTags();


     for (int i = 0; i < tags.size(); i++) {


         if (tags.get(i).getName().equals("Roxy")){

             System.out.println(tags.get(i).getName());
             System.out.println(tags.get(i).getId());
         }

     }


 }




 @Test (description = "TC02")
    public void getDogWithJsonPath(){

     //JsonPath to deserialize the response

     RestAssured.baseURI="https://petstore.swagger.io";
     RestAssured.basePath="v2/pet";

     Response response = HttpsRequests.GET("/14");

     JsonPath parsedResponse = response.jsonPath();

   String name =  parsedResponse.get("category.name");
   Assert.assertTrue(name.equals("Lilia"));

  int id= parsedResponse.get("id");
     System.out.println(id);

  List<String> photoUrls = parsedResponse.getList("photoUrls");
     System.out.println(photoUrls);


    List<Map<String,Objects>> tags = parsedResponse.getList("tags");
   Map<String,Objects> map = tags.get(0);
     String name2 = String.valueOf(map.get("name"));

     Assert.assertTrue(name2.equals("Bowie"),"Failed to validate the name");


     String status  = parsedResponse.get("status");
     System.out.println(status);


 }

























}
