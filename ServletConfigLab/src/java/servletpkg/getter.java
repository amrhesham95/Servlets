/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletpkg;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author AmrHesham
 */
public class getter implements Servlet{
ServletConfig config;
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config=config;
    }

    @Override
    public ServletConfig getServletConfig() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        String name=config.getInitParameter("name");
        res.setContentType("text/html");
        res.getWriter().write("<p>"+name+"</p>");
        
        ServletContext context= config.getServletContext();
        res.getWriter().write(context.getInitParameter("globalName"));
        res.getWriter().write((String)context.getAttribute("attr"));
        
       // res.setContentType("application/msword");
       // res.getWriter().write("this should be displayed in word application");
    }

    @Override
    public String getServletInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
