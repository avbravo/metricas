/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.metricas.producer;


import com.avbravo.jmoordb.util.JmoordbUtil;
import com.avbravo.metricas.securiry.Authentification;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author avbravo
 */
@Named
@ApplicationScoped
public class AuthentificationProducer implements Serializable {
String directoryLogger = JmoordbUtil.isLinux()?JmoordbUtil.userHome() + JmoordbUtil.fileSeparator() + "autentification" + JmoordbUtil.fileSeparator() + "logs" + JmoordbUtil.fileSeparator() + "logger.json": "C:\\fiscalserver\\logs\\logger.json";
    private String user = null;
    private String password = null;
    Authentification authentification = new Authentification("", "");

    // <editor-fold defaultstate="collapsed" desc="readAuthentification()">
    /**
     * Lee los archivos de configuracion de los properties
     *
     * @return
     */
    public Authentification readAuthentificationProperties() {

        try {

            if (user == null) {

                InputStream inputStream = getClass()
                        .getClassLoader().getResourceAsStream("authentification.properties");
                Properties prop = new Properties();

                if (inputStream != null) {

                    prop.load(inputStream);
                    if (prop.getProperty("user") == null) {
                        System.out.println("Advertencia: " + "Consulte al desarrollador no se encuentra el usuario de autentificacion");
                    } else {
                        user = prop.getProperty("user");
                        user = JmoordbUtil.desencriptar(user);
                    }
                    if (prop.getProperty("password") == null) {
                        System.out.println("Advertencia: " + "Consulte al desarrollador no se encuentra el password del usuario de autentificacion");
                    } else {
                        password = prop.getProperty("password");

                        password = JmoordbUtil.desencriptar(password);
                    }

                    authentification.setUser(user);
                    authentification.setPassword(password);
                    
                } else {
                    System.out.println("No se puede cargar el archivo authentification.properties");
                }

            }
        } catch (Exception e) {   JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger , JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("readAuthentificationProperties() " + e.getLocalizedMessage());
        }
        return authentification;
    }
    // </editor-fold>

}
