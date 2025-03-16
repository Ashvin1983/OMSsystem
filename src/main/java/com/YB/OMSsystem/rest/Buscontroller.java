package  com.YB.OMSsystem.rest;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import com.YB.OMSsystem.model.*;
import com.YB.OMSsystem.services.*;
import com.YB.OMSsystem.services.Imp.*;
 
 
 @RestController
 @RequestMapping("/api")
 @CrossOrigin(origins ="*")
 public class Buscontroller{
 
 
      @Autowired
      public BusService  busservice;
 
 
 
 
}
