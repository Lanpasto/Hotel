package controller.commands.User;

import controller.Path;
import controller.commands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j;
import model.dao.OrdersDao;
import model.dao.UserDao;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import static controller.Path.PAGE_PAYMENT;
import static controller.validation.Validation.PayValidation;

@Log4j
public class PaymentCommand extends Command {
    private static final String SENDER_EMAIL = "bikinibottomhotel@gmail.com";
    private static final String SENDER_PASSWORD1 = "bikinihotel123";
    private static final String SENDER_PASSWORD = "oedtxeidavpblqzk";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession();
        int orderId = (int) session.getAttribute("orderId");
       if (PayValidation(request, request.getParameter("name"),
                request.getParameter("card"), request.getParameter("yearAndMonth"),
              request.getParameter("cvv"))) {
             log.info("PaymentCommand validation failed");
           return PAGE_PAYMENT;
        }
        OrdersDao ordersDao = new OrdersDao();
        ordersDao.updateOrder(orderId);
        int idUser = (int) session.getAttribute("currentUserId");
        UserDao userDao = new UserDao();
        String name = String.valueOf(userDao.findFullNameById(idUser));
        String email = userDao.findEmailById(idUser);
        String subject = "Your Reserve";
        String message = name+" your payment was successful, we are waiting for you\n";
        // Send the email
        try {
            sendEmail(email, subject, message);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to send email: " + e.getMessage());
            return Path.PAGE_LISTOFREQUEST;
        }
        // Return a success message
        request.setAttribute("success", "Email sent successfully!");
        return Path.PAGE_LISTOFREQUEST;
    }

    private void sendEmail(String to, String subject, String message) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
                    }
                });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(SENDER_EMAIL));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        msg.setSubject(subject);
        msg.setText(message);
        Transport.send(msg);
    }}

