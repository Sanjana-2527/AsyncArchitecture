package com.sanjana.springbootawssns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude={ContextStackAutoConfiguration.class, ContextRegionProviderAutoConfiguration.class})
@RestController

public class SpringbootAwsSnsApplication {

    @Autowired
    private AmazonSNSClient snsClient;

    String TOPIC_ARN="";

    @GetMapping("/addSubscription/{email}")
    public String addSubscription(@PathVariable String email){
        SubscribeRequest request= new SubscribeRequest(TOPIC_ARN, "email", email);
        snsClient.subscribe(request);
        return "Check email" + email;
    }

    @GetMapping("/sendNotification")
    public String publishMessageToTopic(){
        PublishRequest publishRequest=new PublishRequest(TOPIC_ARN, buildEmailBody(), subject="Netwrok issue")
         snsClient.publish(publishRequest);
        return "Notification send Successfully !";
    }


    private String buildEmailBody(){
        return "Server Down";
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringbootAwsSnsApplication.class, args);
    }

}
