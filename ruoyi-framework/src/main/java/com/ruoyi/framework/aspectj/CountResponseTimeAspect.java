package com.ruoyi.framework.aspectj;


import com.ruoyi.common.utils.TimeUuidUtil;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.system.domain.CostTime;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 计算统计服务器响应时间
 * @author lws
 *
 */
@Aspect
@Component
public class CountResponseTimeAspect {

    public static final int CONDITION_TIME = 500;
    /**
     * 当使用ThreadLocal维护变量的时候 为每一个使用该变量的线程提供一个独立的变量副本，
     * 即每个线程内部都会有一个该变量，这样同时多个线程访问该变量并不会彼此相互影响，
     * 因此他们使用的都是自己从内存中拷贝过来的变量的副本， 这样就不存在线程安全问题，
     * 也不会影响程序的执行性能。
     * public T get() { } // 用来获取ThreadLocal在当前线程中保存的变量副本
     * public void set(T value) { } //set()用来设置当前线程中变量的副本
     * public void remove() { } //remove()用来移除当前线程中变量的副本
     * protected T initialValue() { } //initialValue()是一个protected方法，一般是用来在使用时进行重写的
     */
	ThreadLocal<Long> startTime = new ThreadLocal<>();
	String className = null;
	String methodName = null;

    // 配置织入点
	//第一个*代表任意返回值；controller..代表controller包下及子包下；第二个*代表所有类；*(..)代表任意参数的所有方法
    @Pointcut("execution(* com.ruoyi..controller..*.*(..))")
    public void webLog(){}
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        //joinPoint连接点，getTarget获取被代理的对象
        className = joinPoint.getTarget().getClass().getName();
        //joinPoint连接点，getSignature获取封装了署名信息的对象,在该对象中可以获取到目标方法名,所属类的Class等信息
        methodName = joinPoint.getSignature().getName()+"()";
    }
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        long spendTime = System.currentTimeMillis() - startTime.get();
        if(spendTime>CONDITION_TIME){
            CostTime costTime = new CostTime();
            costTime.setId(TimeUuidUtil.get16UUID());
            costTime.setClassName(className);
            costTime.setMethodName(methodName);
            costTime.setSpendTime(spendTime);
            AsyncManager.me().execute(AsyncFactory.recordCostTime(costTime));
        }
    }
}
