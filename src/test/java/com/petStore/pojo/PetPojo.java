package com.petStore.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PetPojo {


    private int id;

    private String name;

    private String status;

   private Category category;

   private List<String> photoUrls; //do not have variables that's why you do not have to create new class for that

   private List<Tags> tags;










}
