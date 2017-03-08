import domain.Action;
import org.apache.commons.lang3.StringUtils;
import utils.ActionContainer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;



public class Struts {

    private static ActionContainer actionContainer = new ActionContainer();

    public static View runAction(String actionName, Map<String,String> parameters) {
        View view = new View();
        try {
            Action action = actionContainer.getAction(actionName);
            String classPath = action.getClassPath();
            final Class<?> actionClass = Class.forName(classPath);
            Object aAction = actionClass.newInstance();
            parameters.keySet().forEach(key -> {
                try {
                    actionClass.getMethod("set" + StringUtils.capitalize(key), parameters.get(key).getClass()).invoke(aAction, parameters.get(key));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            });
            String returnValue = (String)actionClass.getMethod("execute").invoke(aAction);
            String jsp = action.getResult(returnValue).getValue();
            view.setJsp(jsp);
            Map<String, String> viewParamMap = new HashMap<>(3);
            for (Method method : actionClass.getMethods()) {
                String methodName = method.getName();
                if (StringUtils.containsIgnoreCase(methodName, "get") && method.getReturnType() == String.class) {
                    String fieldValue = (String)method.invoke(aAction);
                    String fieldName = StringUtils.substring(methodName, 3).toLowerCase();
                    viewParamMap.put(fieldName, fieldValue);
                }
            }
            view.setParameters(viewParamMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return view;
    }    

}
