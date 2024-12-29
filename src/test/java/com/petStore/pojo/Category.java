package com.petStore.pojo;

import lombok.Getter;
import lombok.Setter;

//these are from the dependency we put lombok one
//these one take care of getter and setter,will create automatiically whatever variables you have in the class
@Getter
@Setter
public class Category {


     private int id;

     private String name;


}
