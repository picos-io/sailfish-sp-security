package io.picos.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {

    @GetMapping("/foo")
    @ResponseBody
    public HelloWorld sayHelloWorld() {
        return new HelloWorld("Hello world");
    }

    static class HelloWorld {
        private String message;

        public HelloWorld() {
        }

        public HelloWorld(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}

