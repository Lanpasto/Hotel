package controller.filters;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Log4j
@WebFilter(urlPatterns = "/controller")
public class SecurityFilter implements Filter {
    private static final ArrayList<String> guestRequired = new ArrayList<>
            (Arrays.asList("test","loginpage", "logout", "indexPage", "registerCommand", "registerPage", "loginCommand", "aboutUsPage", "changeLang", "orderRoomPage", "changeLanguage"));
    private static final ArrayList<String> userRequires = new ArrayList<>
            (Arrays.asList("makeOrder", "payForm", "makeRequest", "confirmRequestUser", "rejectRequestUser", "myReservePage", "requestPage", "ListOfRequest", "payForm", "payment", "changeLanguage"));
    private static final ArrayList<String> managerRequired = new ArrayList<>
            (Arrays.asList("confirmCommand", "adminRequestPage", "adminReservePage", "changeLanguage", "updateStatus", "confirmCommand", "changeLanguage","selectRoomReq"));

    private boolean isAuthorized(HttpServletRequest req) {
        String command = req.getParameter("action");
        if (command == null) {
            return true;
        }
        if (guestRequired.contains(command)) {
            return true;
        }
        if (req.getSession().getAttribute("role") == null) {
            return false;
        }
        int role = (int) req.getSession(false).getAttribute("role");

        if (2 == role && managerRequired.contains(command)) {
            return true;
        }
        return (1 == role && userRequires.contains(command));

    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        log.debug("Auth filter starts");
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        HttpServletResponse response = (HttpServletResponse) servletResponse;


        HttpSession session = ((HttpServletRequest) servletRequest).getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("role") != null);

        String loginURL = "login";
        boolean isLoginRequest = httpRequest.getParameter("action").equals(loginURL);

        if (isLoggedIn && isLoginRequest) {
            httpRequest.getRequestDispatcher("/").forward(servletRequest, servletResponse);
        } else if (isAuthorized(httpRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect("controller?action=indexPage");
        }
        log.debug("Auth filter finished");
    }

    public SecurityFilter() {
    }

    public void destroy() {
        log.debug("Auth filter destroyed");
    }

    public void init(FilterConfig fConfig) throws ServletException {
        log.debug("Auth filter initialized start");
        log.debug("Auth filter initialized finish");
    }


}

