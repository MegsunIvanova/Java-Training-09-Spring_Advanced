package bg.softuni.mobilele.service.aop;

import bg.softuni.mobilele.service.MonitoringService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

@Aspect
@Component
public class MonitoringAspect {
    private final MonitoringService monitoringService;
    private static Logger LOGGER = LoggerFactory.getLogger(MonitoringAspect.class);

    public MonitoringAspect(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    @Before("Pointcuts.trackOfferSearch()")//да не се бърка с анотацията @Before от JUnit
    public void logOfferSearch() {
        monitoringService.logOfferSearch();
    }

    @Around("Pointcuts.warnIfExecutionExceeds()")
    public Object logExecutionTime(ProceedingJoinPoint pjp) throws Throwable {

        WarnIfExecutionExceeds annotation = getAnnotation(pjp);
        long timeout = annotation.timeInMillis();

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        var returnValue = pjp.proceed();

        stopWatch.stop();

        if (stopWatch.getLastTaskTimeMillis() > timeout) {
            LOGGER.warn("The method {} ran for {} millis which is more than the expected {} millis.",
                    pjp.getSignature(),
                    stopWatch.getLastTaskTimeMillis(),
                    timeout);
        }

        return returnValue;
    }

    private static WarnIfExecutionExceeds getAnnotation(ProceedingJoinPoint pjp) {

        Method method = ((MethodSignature) pjp.getSignature()).getMethod();

        try {
            return pjp
                    .getTarget()
                    .getClass()
                    .getMethod(method.getName(), method.getParameterTypes())
                    .getAnnotation(WarnIfExecutionExceeds.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
