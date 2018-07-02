package com.vala.places.places.verticles;

import com.vala.places.places.service.MetricService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.dropwizard.MetricsService;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Param;
import se.walkercrou.places.Prediction;

import java.util.List;

@Component
public class ServerVerticle extends AbstractVerticle {

    public static final String API_KEY = "AIzaSyARDPo0q1JGwZXXhnY8IL-JmBSQZSm5j84";
    public static final String COMPONENTS_PARAM_TYPE = "components";
    public static final String COUNTRY_COMPONENT = "country:";

    @Autowired
    private MetricService metricService;

    @Override
    public void start(Future<Void> fut) {
        // Create a router object.
        Router router = Router.router(vertx);

        router.route().handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.setChunked(true);
            metricService.increaseRequestCounter();
            routingContext.vertx().setTimer(2000, tid -> routingContext.next());
        });
        router.get("/api/places/country/:country/text/:text").blockingHandler(this::getPlacesAutocompletePredictions);
        router.get("/api/places/metric").blockingHandler(this::getMetric);
        router.get("/api/places/metrics").handler(this::getMetrics);

        // Create the HTTP server and pass the "accept" method to the request handler.
        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(
                        // Retrieve the port from the configuration,
                        // default to 8080.
                        config().getInteger("http.port", 5000),
                        result -> {
                            if (result.succeeded()) {
                                fut.complete();
                            } else {
                                fut.fail(result.cause());
                            }
                        }
                );
    }

    private void getMetric(RoutingContext routingContext) {

        routingContext.response().putHeader("content-type", "text/json")
                .end(Json.encodePrettily(metricService.getPlacesMetric()));

    }

    private void getPlacesAutocompletePredictions(RoutingContext routingContext) {

        String country = routingContext.request().getParam("country");
        String text = routingContext.request().getParam("text");

        metricService.increaseCountryCounter(country);

        GooglePlaces client = new GooglePlaces(API_KEY);
        Param components = new Param(COMPONENTS_PARAM_TYPE).value(COUNTRY_COMPONENT + country);
        List<Prediction> placePredictions = client.getPlacePredictions(text, components);

        routingContext.response().putHeader("content-type", "text/json")
                .end(Json.encodePrettily(placePredictions));
    }

    private void getMetrics(RoutingContext routingContext) {
        MetricsService metricsService = MetricsService.create(vertx);
        JsonObject metrics = metricsService.getMetricsSnapshot(vertx);
        routingContext.response().putHeader("content-type", "text/json")
                .end(Json.encodePrettily(metrics));
    }

}
