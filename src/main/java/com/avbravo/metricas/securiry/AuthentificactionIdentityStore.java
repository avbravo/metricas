/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.metricas.securiry;

import com.avbravo.jmoordb.util.JmoordbUtil;
import com.avbravo.metricas.producer.AuthentificationProducer;
import static java.util.Arrays.asList;
import java.util.HashSet;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import javax.security.enterprise.identitystore.IdentityStore;

/**
 *
 * @author avbravo
 */
@ApplicationScoped
public class AuthentificactionIdentityStore implements IdentityStore {
    String directoryLogger = JmoordbUtil.isLinux()?JmoordbUtil.userHome() + JmoordbUtil.fileSeparator() + "autentificacion" + JmoordbUtil.fileSeparator() + "logs" + JmoordbUtil.fileSeparator() + "logger.json": "C:\\autentificacion\\logs\\logger.json";
Authentification authentification= new Authentification();
   @Inject
AuthentificationProducer authentificationProducer;
    public CredentialValidationResult validate(UsernamePasswordCredential usernamePasswordCredential) {
        try {
           
                 authentification=  authentificationProducer.readAuthentificationProperties();


            if (usernamePasswordCredential.compareTo(authentification.getUser(), authentification.getPassword())) {
                return new CredentialValidationResult(authentification.getUser(), new HashSet<>(asList("admin", "testing")));
            }
        } catch (Exception e) {   JmoordbUtil.appendTextToLogErrorFile(this.directoryLogger , JmoordbUtil.nameOfClass(), JmoordbUtil.nameOfMethod(), e.getLocalizedMessage(), e);
            System.out.println("CredentialValidationResult " + e.getLocalizedMessage());
        }

        return INVALID_RESULT;
    }

}
