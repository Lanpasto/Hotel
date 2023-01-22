package controller.commands.User;

import controller.Path;
import controller.commands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

public class PaymentCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession();
        int orderId = Integer.parseInt(request.getParameter("orderIdForConfirm"));
        session.setAttribute("orderId",orderId);
        System.out.println(orderId);
       // OrdersDao ordersDao= new OrdersDao();
      //  System.out.println(orderId);
     //   int idUser = (int) session.getAttribute("currentUserId");
        // ordersDao.DeleteOrderRequest(orderReqId);
        //ordersDao.updateOPay();
     //   ordersDao.updateStatus(orderId);
        return Path.PAGE_LISTOFREQUEST;
    }
}
