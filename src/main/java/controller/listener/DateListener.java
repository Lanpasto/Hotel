package controller.listener;

import controller.listenerUtil.DateCheck;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.ScheduledExecutorService;

    @WebListener
    public class DateListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
        private ScheduledExecutorService scheduler;

        public DateListener() {
        }

        @Override
        public void contextInitialized(ServletContextEvent sce) {
            JobDetail job = JobBuilder.newJob(DateCheck.class)
                    .withIdentity("anyJobName", "group1").build();
            try {
                Trigger trigger = TriggerBuilder
                        .newTrigger()
                        .withIdentity("anyTriggerName", "group1")
                        .withSchedule(
                                CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
                        .build();

                Scheduler scheduler = new StdSchedulerFactory().getScheduler();
                scheduler.start();
                scheduler.scheduleJob(job, trigger);
            }catch (SchedulerException e){
                e.printStackTrace();
            }
        }

        @Override
        public void contextDestroyed(ServletContextEvent sce) {

        }

        @Override
        public void sessionCreated(HttpSessionEvent se) {
            /* Session is created. */
        }

        @Override
        public void sessionDestroyed(HttpSessionEvent se) {
            /* Session is destroyed. */
        }

        @Override
        public void attributeAdded(HttpSessionBindingEvent sbe) {
            /* This method is called when an attribute is added to a session. */
        }

        @Override
        public void attributeRemoved(HttpSessionBindingEvent sbe) {
            /* This method is called when an attribute is removed from a session. */
        }

        @Override
        public void attributeReplaced(HttpSessionBindingEvent sbe) {
            /* This method is called when an attribute is replaced in a session. */
        }
    }


