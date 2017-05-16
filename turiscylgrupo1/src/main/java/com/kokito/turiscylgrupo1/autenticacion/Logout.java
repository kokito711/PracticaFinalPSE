/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kokito.TurisCyLGrupo1.autenticacion;

/**
 *
 * @author coco7
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "logout", urlPatterns = {"/logout"})
public class Logout extends HttpServlet {

  private static final long serialVersionUID = 1L;
  
  @Override
  protected void doGet(HttpServletRequest request,
    HttpServletResponse response) 
      throws ServletException, IOException {
    
    // Invalidate current HTTP session.
    // Will call JAAS LoginModule logout() method
    request.getSession().invalidate();
    
    // Redirect the user to the secure web page.
    // Since the user is now logged out the
    // authentication form will be shown
    response.sendRedirect(request.getContextPath());
    
  }
   


}
