package com.hatim.app;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.PrintWriter;
import java.io.IOException;

public class SecurityFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("security filter init");
    }

    public void destroy() {
        System.out.println("security filter destroyed");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chainObj)
            throws IOException, ServletException {
        System.out.println("IN SECURITY FILTER");

      
        RequestDispatcher rdObj = null;
        PrintWriter out = resp.getWriter();

        String yourName = req.getParameter("your_name");
        String restrictedUser = "RACHID";
        
        if (!restrictedUser.equals(yourName.toUpperCase())) {
            /***** Send Request To Next Resource *****/
            chainObj.doFilter(req, resp);
        } else {
            out.write("<html><body><div  style='text-align: center;'>");
            out.write("<h1>" + restrictedUser + " you are not allowed in!</h1>");
            out.write("<h1>Please contact the administrator.</h1>");
            out.write("</div></body></html>");
        }

        out.close();
    }

}
