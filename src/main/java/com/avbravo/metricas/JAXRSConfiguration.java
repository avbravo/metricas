package com.avbravo.metricas;

import com.avbravo.jmoordb.configuration.JmoordbConnection;
import java.util.Set;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configures JAX-RS for the application.
 * @author Juneau
 */
@ApplicationPath("resources")
@BasicAuthenticationMechanismDefinition(realmName = "admin-realm")

public class JAXRSConfiguration extends Application {
//    @Inject
//    private Config config;
//    @Inject
//    @ConfigProperty(name="mongodbsrv", defaultValue="")
//    private String mongodbsrv;
    
    
   @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        try {
            //Configuracion de la base de datos
//            JmoordbConnection jmc = new JmoordbConnection.Builder()
//                    .withUri(mongodbsrv)
//                    .build();
           
            JmoordbConnection jmc = new JmoordbConnection.Builder()
                    .withSecurity(false)
                    .withDatabase("general")
                    .withHost("")
                    .withPort(0)
                    .withUsername("")
                    .withPassword("")
                    .build();
        } catch (Exception e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }

        return resources;
    }
    
}
