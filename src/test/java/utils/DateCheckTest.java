package utils;

import controller.listenerUtil.DateCheck;
import model.dao.OrdersDao;
import org.junit.Test;
import org.mockito.Mockito;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.sql.SQLException;

public class DateCheckTest {

    @Test
    public void testExecute() throws JobExecutionException, SQLException {
        OrdersDao orderDao = Mockito.mock(OrdersDao.class);
        JobExecutionContext jobExecutionContext = Mockito.mock(JobExecutionContext.class);
        DateCheck dateCheck = new DateCheck();

        dateCheck.execute(jobExecutionContext);

    }
}
