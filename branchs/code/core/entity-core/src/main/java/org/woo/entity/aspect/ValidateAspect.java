package org.woo.entity.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.woo.entity.annotation.ValidateFiled;
import org.woo.entity.annotation.ValidateGroup;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/3/24.
 */

@Aspect
public final class ValidateAspect {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ValidateAspect.class);

	@SuppressWarnings({ "finally", "rawtypes" })
	@Around("@annotation(ValidateGroup)")
	public Object validateAround(ProceedingJoinPoint joinPoint) throws Throwable {
		boolean flag = false;
		ValidateGroup an;
		Object[] args;
		Method method = null;
		Object target;
		String methodName;
		try {
			methodName = joinPoint.getSignature().getName();
			target = joinPoint.getTarget();
			method = getMethodByClassAndName(target.getClass(), methodName);
			args = joinPoint.getArgs();
			an = (ValidateGroup) getAnnotationByMethod(method, ValidateGroup.class);
			flag = validateFiled(an.fileds(), args);
			logger.debug("");
		} catch (Exception e) {
			flag = false;
		} finally {
			if (flag) {
				return joinPoint.proceed();
			} else {
				System.out.println("noaccessed");
				Class returnType = method.getReturnType();
				if (returnType == String.class) {
					return "/error.jsp";
				} else {
					return null;
				}
			}
		}
	}

	private boolean validateFiled(ValidateFiled[] valiedatefiles, Object[] args) throws SecurityException,
			IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		for (ValidateFiled validateFiled : valiedatefiles) {
			Object arg;
			if ("".equals(validateFiled.filedName())) {
				arg = args[validateFiled.index()];
			} else {
				arg = getFieldByObjectAndFileName(args[validateFiled.index()], validateFiled.filedName());
			}

			if (validateFiled.notNull()) {
				if (arg == null)
					return false;
			} else {
				if (arg == null)
					return true;
			}

			if (validateFiled.maxLen() > 0) {
				if (((String) arg).length() > validateFiled.maxLen())
					return false;
			}

			if (validateFiled.minLen() > 0) {
				if (((String) arg).length() < validateFiled.minLen())
					return false;
			}

			if (validateFiled.maxVal() != -1) {
				if ((Integer) arg > validateFiled.maxVal())
					return false;
			}

			if (validateFiled.minVal() != -1) {
				if ((Integer) arg < validateFiled.minVal())
					return false;
			}

			if (!"".equals(validateFiled.regStr())) {
				if (arg instanceof String) {
					if (!((String) arg).matches(validateFiled.regStr()))
						return false;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	private Object getFieldByObjectAndFileName(Object targetObj, String fileName) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String tmp[] = fileName.split("\\.");
		Object arg = targetObj;
		for (int i = 0; i < tmp.length; i++) {
			Method methdo = arg.getClass().getMethod(getGetterNameByFiledName(tmp[i]));
			arg = methdo.invoke(arg);
		}
		return arg;
	}

	private String getGetterNameByFiledName(String fieldName) {
		return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}

	private Annotation getAnnotationByMethod(Method method, Class<ValidateGroup> annoClass) {
		Annotation all[] = method.getAnnotations();
		for (Annotation annotation : all) {
			if (annotation.annotationType() == annoClass) {
				return annotation;
			}
		}
		return null;
	}

	private Method getMethodByClassAndName(Class<? extends Object> c, String methodName) {
		Method[] methods = c.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				return method;
			}
		}
		return null;
	}

}
