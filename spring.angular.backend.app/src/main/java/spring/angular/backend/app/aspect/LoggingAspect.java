package spring.angular.backend.app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	@Before("execution(* spring.angular.backend.app.business.*.*(..))")
	public void logBeforeMethod(JoinPoint joinPoint) {
		System.out.println("Executing method: " + joinPoint.getSignature().getName());
	}

	@AfterReturning(pointcut = "execution(* spring.angular.backend.app.business.*.*(..))", returning = "result")
	public void logAfterMethod(JoinPoint joinPoint, Object result) {
		System.out.println("Method executed: " + joinPoint.getSignature().getName() + ", Return value: " + result);
	}
}
