package org.creditclub.restserver;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
class MessageRestController {

    @Value("${message:Hello default}")
    private String message;

    @RequestMapping("/hello")
    public String hello() {
        return this.message;
    }

    @RequestMapping("/hello-user")
    public String helloUser(Principal principal) {
        return "Hello, " + principal.getName();
    }
}
