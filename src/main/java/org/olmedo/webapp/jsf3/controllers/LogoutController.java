package org.olmedo.webapp.jsf3.controllers;

import jakarta.enterprise.inject.Model;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@Model
public class LogoutController {

    @Inject
    private FacesContext facesContext;

    public String logout() throws ServletException {
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        request.logout(); //cerramos session con todo lo relacionado al autenticacion
        request.getSession().invalidate(); // elimina todo lo relacionado al usuario con la session
        facesContext.addMessage(null, new FacesMessage("Haz cerrado sesion con exito!"));
        return "login.xhtml";
    }
}
