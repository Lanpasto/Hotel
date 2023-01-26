package controller.listenerUtil;

import model.dao.OrdersDao;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.sql.SQLException;

public class DateCheck implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        OrdersDao orderDao = new OrdersDao();
        orderDao.checkDateOut();
        try {
            orderDao.checkDateBooked();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
