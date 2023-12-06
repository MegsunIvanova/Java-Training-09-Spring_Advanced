package bg.softuni.mobilele.service.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* bg.softuni.mobilele.service.OfferService.getAllOffers(..))")
    public void  trackOfferSearch(){}
    //този метод е празен, целта му е в анотацията @Pointcut на org.aspectj да се дефинират joint points

    @Pointcut("@annotation(WarnIfExecutionExceeds)")
    public void warnIfExecutionExceeds(){}

}
