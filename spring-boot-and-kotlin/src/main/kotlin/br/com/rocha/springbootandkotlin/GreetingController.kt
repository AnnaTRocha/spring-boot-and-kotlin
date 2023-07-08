package br.com.rocha.springbootandkotlin

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class GreetingController {

    val counter: AtomicLong = AtomicLong()

    @RequestMapping("/greeting")
    fun greeting(@RequestParam(value="name", defaultValue = "World") name: String? ): Greeting {
        // "?" means accept null
        return Greeting(counter.incrementAndGet(), "Hello, $name!")
    }
}