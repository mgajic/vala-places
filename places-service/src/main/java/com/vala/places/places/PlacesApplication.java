package com.vala.places.places;

import com.vala.places.places.verticles.ServerVerticle;
import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Configuration
public class PlacesApplication {

    @Autowired
    private ServerVerticle serverVerticle;

    /*  @Autowired
      private RecipientVerticle serviceVerticle;
  */
    public static void main(String[] args) {
        SpringApplication.run(PlacesApplication.class, args);
    }

    @PostConstruct
    public void deployVerticle() {
        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(serverVerticle);
        //  vertx.deployVerticle(serviceVerticle);
    }
}
