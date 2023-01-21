package controller.commands;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

public class ChangeLangCommand extends Command{
    private static final String LOCALE = "lang";
    private static final String SESSION_LOCALE = "lang";

    private ArrayList<String> supportedLanguages = new ArrayList<>();
    private static final String ENGLISH = "en";
    private static final String UKRAINE = "ua";

    public ChangeLangCommand() {
        supportedLanguages.add(ENGLISH);
        supportedLanguages.add(UKRAINE);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String locale =  request.getParameter(LOCALE);
        HttpSession session = request.getSession(false);
        if(locale != null){
            if(!supportedLanguages.contains(locale)){
                locale = ENGLISH;

            }
            session.setAttribute(SESSION_LOCALE,locale);
        }
        String url = request.getHeader("Referer");
        return "redirect:" + url.replace("http://localhost:8080/hotel/","");
    }
}
