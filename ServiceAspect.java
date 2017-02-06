package com.sibu.sixin.utils.sql;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 控制层切面,在控制层添加错误处理 
 * @author caishiyu
 */
@Aspect
@Component
public class ServiceAspect {

	@Pointcut("@annotation(com.sibu.sixin.utils.sql.DataSource)")
	public void service() {
	}

	@Before("service()")
	public void aroundService(JoinPoint joinPoint) throws Throwable {
		// 这里需要先拿到目标对象，再拿到具体方法，
		// ((MethodSignature)joinPoint.getSignature()).getMethod()得到的是接口中的方法
		Class<?> target = joinPoint.getTarget().getClass();
		String methodName = joinPoint.getSignature().getName();
		Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
		Method targetMethod = target.getMethod(methodName, parameterTypes);
		String dataSourceName = targetMethod.getAnnotation(DataSource.class).value();
		DynamicDataSource.setDataSourceName(dataSourceName);
	}
}
