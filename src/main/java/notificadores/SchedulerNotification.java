package notificadores;

import java.util.concurrent.CountDownLatch;

import org.quartz.*;

public class SchedulerNotification {

  public static void main(String[] args) throws Exception {
    SchedulerNotification schedulerNotificador = new SchedulerNotification();
    schedulerNotificador.comenzar();
  }


  public void comenzar() throws SchedulerException {

    // Creacion del scheduler
    SchedulerFactory schedFactory = new org.quartz.impl.StdSchedulerFactory();
    Scheduler scheduler = schedFactory.getScheduler();
    scheduler.start();

    // Construccion de JobDetail
    JobBuilder jobBuilder = JobBuilder.newJob(Notificador.class);
    JobDataMap data = new JobDataMap();

    JobDetail jobDetail = jobBuilder
          .withIdentity("unJob", "gr")
          .usingJobData(data)
          .build();

      // Construccion de Trigger
      Trigger trigger = TriggerBuilder.newTrigger()
          .withIdentity("unTrigger")
          .startNow()
          .withSchedule(SimpleScheduleBuilder
              .simpleSchedule()
              .withIntervalInSeconds(60))
          .build();

      // Asignacion del job y el trigger a la inst de scheduler
      scheduler.scheduleJob(jobDetail, trigger);

      scheduler.shutdown();
    }

}

