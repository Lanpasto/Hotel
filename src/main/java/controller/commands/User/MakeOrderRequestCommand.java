package controller.commands.User;

import controller.Path;
import controller.commands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j;
import model.dao.OrdersDao;
import model.entity.Orders_request;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

import static controller.commands.User.MakeOrderCommand.date;
import static controller.validation.Validation.OrderRequestValidator;

@Log4j
public class MakeOrderRequestCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        log.info("MakeOrderRequestCommand started");
        String forward;
        HttpSession session = request.getSession();
        String guests = request.getParameter("numberOfPerson");
        String classOfRoom = request.getParameter("classOfRoom");
        String allDate = request.getParameter("datefilter");
        if (OrderRequestValidator(request, guests, allDate, classOfRoom)) {
            log.info("MakeOrderRequestCommand validation failed");
            return Path.PAGE_REQUEST;
        }
        int guestsInt = Integer.parseInt(guests);
        String delimiter = " - ";
        String[] temp = allDate.split(delimiter);
        String firstDate = temp[0];
        String secondDate = temp[1];
        Timestamp DateOfSettlement = date(firstDate);
        Timestamp DateOfOut = date(secondDate);
        int idUser = (int) session.getAttribute("currentUserId");
        Orders_request ordersRequest = Orders_request.builder()
                .userId(idUser)
                .guests(guestsInt)
                .dateOfSettlement(DateOfSettlement)
                .dateOfOut(DateOfOut)
                .dateOfCreateRequest(Timestamp.from(Instant.now()))
                .type_of_room(classOfRoom)
                .status("Pending")
                .build();
        OrdersDao ordersDao = new OrdersDao();
        ordersDao.addOrderRequest(ordersRequest);
        forward = Path.PAGE_INDEX;
        log.info("MakeOrderRequestCommand successfully added");
        return forward;
    }
}
