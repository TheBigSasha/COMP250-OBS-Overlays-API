package ca.sashaphoto.streamtools;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class GreetingController {
    private Topic topic = new Topic("None");

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/currentTopic")
    public String topic(@RequestParam(value = "name", defaultValue = "World") String name) {
        return this.topic.getContent();
    }

    @GetMapping("/currentTopicJSON")
    public Topic topicJSON(@RequestParam(value = "name", defaultValue = "World") String name) {
        return this.topic;
    }


    @PostMapping("/setTopic")
    public String saveTopic(@RequestBody String topic){
        if(topic.startsWith(String.valueOf((char) 34))){
            topic = topic.substring(1,topic.length() - 1);
            topic = topic.replace(String.valueOf((char) 34),""); //TODO: BAD
        }
        this.topic = new Topic(topic);
        return "hello, " + this.topic.getContent();
    }

}