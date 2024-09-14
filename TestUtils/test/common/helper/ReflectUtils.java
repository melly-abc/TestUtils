package common.helper;

import java.lang.reflect.Field;

/**
 * リフレクションテストユーティリティ
 */
public class ReflectUtils {
	
	/**
	 * フィールド値セット処理
	 * @param targetInstance
	 * @param fieldName
	 * @param value
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
    public static void setValueInField(Object targetInstance, String fieldName, Object value) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
    	Field field = targetInstance.getClass().getDeclaredField(fieldName);
    	field.setAccessible(true);
    	field.set(targetInstance, value);
    }
    
    /**
     * フィールド値取得処理
     * @param targetInstance
     * @param fieldName
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws SecurityException
     */
    public static Object getValueInField(Object targetInstance, String fieldName) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
    	Field field = targetInstance.getClass().getDeclaredField(fieldName);
    	field.setAccessible(true);
    	return field.get(targetInstance);
    }
}
