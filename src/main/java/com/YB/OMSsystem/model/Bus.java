package com.YB.OMSsystem.model;
import java.util.*;

import jakarta.persistence.*;
 
@Entity
@Table(name = "bus")
public class Bus{
 
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
 
  @Column(name = "prodctname")
  private String   prodctname;
 
  @Column(name = "prodprice")
  private List<String>   prodprice;
 
public  Bus(){}
 
public Bus( String prodctname, List<String> prodprice){
     this.prodctname=prodctname;
     this.prodprice=prodprice;
  }
  public String getProdctname()
   {
     return prodctname;
  }
   
  public void setProdctname(String prodctname )
  {
     this.prodctname=prodctname;
  }
  public List<String> getProdprice()
   {
     return prodprice;
  }
   
  public void setProdprice(List<String> prodprice )
  {
     this.prodprice=prodprice;
  }
@Override 
public String toString() { 
 return "Bus[id="+id+",prodctname="+prodctname+",prodprice="+prodprice+"]";
  }
 
 
}
