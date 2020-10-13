/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.metricas.resources;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;

/**
 *
 * @author avbravo
 */
@ApplicationScoped
@Path("/metricas")
public class HelloWorldEndpoint {
    @Timed(name = "helloMessageProcessed",
            description = "Monitor the time helloMessageProcessed Method takes",
            unit = MetricUnits.MILLISECONDS,
            absolute = true)
    @GET
    @Path("/hello-message-processed")
    public Response helloMessageProcessed() {
        return Response.ok().build();
    }

    @Metered(name = "todosGet", 
            unit = MetricUnits.MILLISECONDS, 
            description = "Monitor the rate events occured", 
            absolute = true)
    @GET
    @Path("/todos-get")
    public Response todosGet() {
        return Response.ok().build();
    }

    @Counted(unit = MetricUnits.NONE,
            name = "helloGet",
            absolute = true,
            displayName = "hello get",
            description = "Monitor how many times helloGet method was called")
    @GET
    @Path("/hello-get")
    public Response helloGet() {
        return Response.ok().build();
    }

    @GET
    @Path("/get-int-value")
    @Gauge(unit = MetricUnits.NONE, name = "intValue", absolute = true)
    public int getIntValue() {
        return 3;
    }

}
