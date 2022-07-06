package doubleCheck.practice.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import doubleCheck.practice.annotation.AttachLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LoggerAspect {

    Logger log = LoggerFactory.getLogger(LoggerAspect.class);


    @Around("@annotation(doubleCheck.practice.annotation.AttachLog)")
    public Object wrapControllerLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName  = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().getClass().toString();
        Object[] args = proceedingJoinPoint.getArgs();
        long start = System.currentTimeMillis();
        log.info("Log Request: ClassName "+className+" method "+methodName+" request: "+mapper.writeValueAsString(args));
        Object obj = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis() - start;
        log.info("Log Response: ClassName "+className+" method "+methodName+" response: "+mapper.writeValueAsString(args)+" response time: "+end+" ms");
        return obj;
    }

}
