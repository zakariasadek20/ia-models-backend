package com.models.ai.infra.config.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PerformanceAspect {

    final String NO_TIMING_CONDITION = "&& (!@annotation(com.models.ai.infra.util.annotation.NoTiming) "
            + "|| !@target(com.models.ai.infra.util.annotation.NoTiming))";

    /** Pointcut for execution of methods on {@link Controller} annotation */
    @Pointcut("execution(public * (@org.springframework.stereotype.Controller com.models.ai..*).*(..))"
            + NO_TIMING_CONDITION)
    public void restAnnotation() {
        // AOP for Rest Anotation : Controller
    }

    @Pointcut("execution(public * (@org.springframework.web.bind.annotation.RestController com.models.ai..*).*(..))"
            + NO_TIMING_CONDITION)
    public void restAnnotation2() {
        // AOP for Rest Anotation : RestController
    }

    /** Pointcut for execution of methods on {@link Service} annotation */
    @Pointcut("execution(public * (@org.springframework.stereotype.Service com.models.ai..*).*(..))"
            + NO_TIMING_CONDITION)
    public void serviceAnnotation() {
        // AOP for Service Anotation : Service
    }

    /** Pointcut for execution of methods on {@link Repository} annotation */
    @Pointcut("execution(public * (@org.springframework.stereotype.Repository com.models.ai..*).*(..))"
            + NO_TIMING_CONDITION)
    public void repositoryAnnotation() {
        // AOP for Repository Anotation : Repository
    }

    /** Pointcut for execution of methods on {@link JpaRepository} interfaces */
    @Pointcut("execution(public * org.springframework.data.jpa.repository.JpaRepository+.*(..))" + NO_TIMING_CONDITION)
    public void jpaRepository() {
        // AOP for JpaRepository Anotation : JpaRepository
    }

    @Pointcut(
            "restAnnotation() || restAnnotation2() ||serviceAnnotation() || repositoryAnnotation() || jpaRepository()")
    public void performanceMonitor() {
        // AOP performance Monitor
    }
}
