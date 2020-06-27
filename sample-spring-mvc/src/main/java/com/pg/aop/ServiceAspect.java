package com.pg.aop;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class ServiceAspect {

	Logger logger;
    /**
    * Before Advice - JointPoint
    * @param jointPoint
    */
    @Before("execution(* com.pg..*.*(..)) && !within(com.pg.aop.*)")
    public void getBeforeAdvice(JoinPoint jointPoint){
    	logger = Logger.getLogger(jointPoint.getTarget().getClass().getName());
    	logger.info("Entered the method : "+jointPoint.getSignature() + " : " + printArguments(jointPoint.getArgs()) );
    }
    
    /**
    * After Advice - JointPoint
    * @param jointPoint
    */
    @After("execution(* com.pg..*.*(..)) && !within(com.pg.aop.*)")
    public void getAfterAdvice(JoinPoint jointPoint){
    	logger = Logger.getLogger(jointPoint.getTarget().getClass().getName());
    	logger.info("Exiting the method : "+jointPoint.getSignature() + " : " + printArguments(jointPoint.getArgs()) );
    }

    private String printArguments(Object[] args){
        StringBuilder  inputArguments = new StringBuilder();
        for(Object arg: args){
                        inputArguments.append(arg + " , ");
        }
        return inputArguments.toString();
    }
    
   /* @Pointcut("execution(* com.pg..*.*(..))")
    public void getAllPackagePointCut(){
                    
    }*/
   

}
