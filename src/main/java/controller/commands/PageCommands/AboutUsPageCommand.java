package controller.commands.PageCommands;

import controller.commands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.sql.SQLException;

import static controller.Path.PAGE_ABOUTUS;

@Log4j
public class AboutUsPageCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        log.info("page loaded");
        return PAGE_ABOUTUS;
    }}