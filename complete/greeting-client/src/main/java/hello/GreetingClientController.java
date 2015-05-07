package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/hello")
public class GreetingClientController {

    private RestTemplate rest;

    @Autowired
    public GreetingClientController(RestTemplate rest) {
      this.rest = rest;
    }

    // tag::helloMethod[]
    @RequestMapping(method=RequestMethod.GET)
    @HystrixCommand(fallbackMethod="goodbye")
    public String hello() {
      Greeting greeting = rest.getForObject("http://GreetingService/greeting", Greeting.class);
      return greeting.getContent();
    }
    // end::helloMethod[]

    // tag::goodbyeMethod[]
    public String goodbye() {
      return "Goodbye";
    }
    // end::goodbyeMethod[]

}
