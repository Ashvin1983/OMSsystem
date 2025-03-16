package com.YB.OMSsystem.repository;
import java.util.*;
import java.io.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.YB.OMSsystem.model.*;
 
 
@Component
public interface Busrepository extends CrudRepository<Bus,Long>{
 
 
 }
