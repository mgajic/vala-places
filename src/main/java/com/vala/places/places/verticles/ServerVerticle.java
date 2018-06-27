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

    @Autowired
    private MetricService metricService;

    @Override
    public void start(Future<Void> fut) {
        // Create a router object.
        Router router = Router.router(vertx);

        // Bind "/" to our hello message - so we are still compatible.
        router.route("/").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response
                    .putHeader("content-type", "text/html")
                    .end("<h1>Hello from my first Vert.x 3 application</h1>");
        });

        router.get("/api/places/country/:country/text/:text").handler(this::getPlacesAutocompletePredictions);
        router.get("/api/places/metric").handler(this::getMetric);
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

    }

    private void getPlacesAutocompletePredictions(RoutingContext routingContext) {
        String country = routingContext.request().getParam("country");
        String text = routingContext.request().getParam("text");

        GooglePlaces client = new GooglePlaces("AIzaSyARDPo0q1JGwZXXhnY8IL-JmBSQZSm5j84");
        Param components = new Param("components").value("country:" + country);
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
