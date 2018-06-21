package com.vala.places.places.verticles;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import org.springframework.stereotype.Component;

@Component
public class RecipientVerticle extends AbstractVerticle {

    public static final String GET_PLACES_PREDICTIONS = "get.places.predictions";

    private final ObjectMapper mapper = Json.mapper;

    @Override
    public void start() throws Exception {
        super.start();
        vertx.eventBus()
                .<String>consumer(GET_PLACES_PREDICTIONS)
                .handler(getPlacesPredictions());
    }

    private Handler<Message<String>> getPlacesPredictions() {

        //Call to Repository will be here
        return msg -> vertx.<String>executeBlocking(future -> {
        }, result -> {
            msg.reply("Hello World!");
        });
    }
}