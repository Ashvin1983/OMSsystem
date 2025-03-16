package com.YB.OMSsystem.model;
import java.util.*;
import org.springframework.stereotype.Component;
import java.util.Objects;
import jakarta.persistence.*;
 
@Entity
@Table(name = "car")
public class Car{
 
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
 
  @Column(name = "mydata")
  private String   mydata;
 
  @Column(name = "lisdata")
  private List<String>   lisdata;
 
public  Car(){}
 
public Car( String mydata, List<String> lisdata){
     this.mydata=mydata;
     this.lisdata=lisdata;
  }
  public String getMydata()
   {
     return mydata;
  }
   
  public void setMydata(String mydata )
  {
     this.mydata=mydata;
  }
  public List<String> getLisdata()
   {
     return lisdata;
  }
   
  public void setLisdata(List<String> lisdata )
  {
     this.lisdata=lisdata;
  }
@Override 
public String toString() { 
 return "Car[id="+id+",mydata="+mydata+",lisdata="+lisdata+"]";
  }
 
 
}
