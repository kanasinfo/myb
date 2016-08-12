package com.myb.portal.util;

import static com.myb.portal.util.StringUtils.encodeToLine;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class EntityOperator {
	
	private static final Logger logger = LoggerFactory.getLogger(EntityOperator.class);
	public static final String SEPERATOR_METHOD= "_$M$_";
	public static final String SEPERATOR_VALUE= "_$V$_";
	
	private Map<Class<?>,Set<CachedField>>cachedModificationDeclaration = new HashMap<Class<?>,Set<CachedField>>();
	
	private EntityOperator instance = null;
	private EntityOperator(){
		
	}
	
	public EntityOperator getInstance(){
		if (instance == null) instance = new EntityOperator();
		return instance;
	}
	
	public String checkModification(Object source, Object target){
		Assert.notNull(source, "The source object must not be null.");
		Assert.notNull(target, "The target object must not be null.");
		Class<? extends Object> clazz = source.getClass();
		Assert.isTrue(clazz.equals(target.getClass()),"The source and target must be generated from the same Class.");
		if(cachedModificationDeclaration.containsKey(clazz)){
			return checkDiff(cachedModificationDeclaration.get(clazz),source,target);
		}else{
			Set<CachedField> cachedFields= new HashSet<CachedField>();
			Method m;
			CachedField cf;
			CheckModification ano;
			for(Field f:clazz.getDeclaredFields()){
				ano = f.getAnnotation(CheckModification.class);
				if(ano == null) continue;
				try {
					m = clazz.getDeclaredMethod("get"+f.getName().substring(0, 1).toUpperCase()+f.getName().substring(1));
					cf = new CachedField();
					cf.setAnnotation(ano);
					cf.setMethod(m);
					cf.setField(f);
					cachedFields.add(cf);
				} catch (SecurityException e) {
					ExceptionHelper.loggingError(e, logger);
				} catch (NoSuchMethodException e) {
					ExceptionHelper.loggingError(e, logger);
				}
			}
			cachedModificationDeclaration.put(clazz, cachedFields);
			return checkDiff(cachedFields,source,target);
		}
	}
	
	private String checkDiff(Set<CachedField> fields, Object source, Object target){
		boolean beginning = true;
		Object sourceProperty;
		Object targetProperty;
		StringBuffer returnString = new StringBuffer();
		CheckModification ano;
		Field f;
		Method m;
		for(CachedField cf: fields){
			m = cf.getMethod();
			f = cf.getField();
			ano = cf.getAnnotation();
			try{
				sourceProperty = m.invoke(source);
				targetProperty = m.invoke(target);
				if(sourceProperty != null && targetProperty == null){
					if(!beginning)
						returnString.append(SEPERATOR_METHOD);
					else
						beginning = false;
					returnString.append(f.getName()).append(":remove");
					if(ano.value())returnString.append("[").append(encodeToLine(sourceProperty.toString())).append("]");
				}else if(sourceProperty == null && targetProperty != null){
					if(!beginning)
						returnString.append(SEPERATOR_METHOD);
					else
						beginning = false;
					returnString.append(f.getName()).append(":create");
					if(ano.value())returnString.append("[").append(encodeToLine(targetProperty.toString())).append("]");
				}else if(sourceProperty != null && targetProperty != null){
					if(!beginning)
						returnString.append(SEPERATOR_METHOD);
					else
						beginning = false;
					returnString.append(f.getName()).append(":modify");
					if(ano.value()){
						returnString.append("[").append(encodeToLine(sourceProperty.toString()));
						returnString.append(SEPERATOR_VALUE).append(encodeToLine(targetProperty.toString())).append("]");
					}
				}
			} catch (IllegalAccessException e) {
				ExceptionHelper.loggingError(e, logger);
			} catch (InvocationTargetException e) {
				ExceptionHelper.loggingError(e, logger);
			}
		}
		if(logger.isDebugEnabled()){
			logger.debug(returnString.toString());
		}
		return returnString.toString();
	}
	
	private class CachedField{
		private Method method;
		private Field field;
		private CheckModification annotation;
		public Method getMethod() {
			return method;
		}
		public void setMethod(Method method) {
			this.method = method;
		}
		public Field getField() {
			return field;
		}
		public void setField(Field field) {
			this.field = field;
		}
		public CheckModification getAnnotation() {
			return annotation;
		}
		public void setAnnotation(CheckModification annotation) {
			this.annotation = annotation;
		}
	}
}
