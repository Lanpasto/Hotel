package controller.commands.PageCommands;

import controller.commands.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j;
import model.dao.RoomDao;
import model.entity.Room_of_type;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static controller.Path.PAGE_REQUEST;
@Log4j
public class RequestPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        log.info("page loaded");
        RoomDao orderDao = new RoomDao();
        List<Room_of_type> listCategory = orderDao.roomOfTypesList();
        request.setAttribute("listCategory", listCategory);
       // System.out.println(listCategory.get(0).getType_of_room());
        return PAGE_REQUEST;
    }
}
