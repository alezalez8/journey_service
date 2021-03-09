package org.hillel.context;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class AppContext {
    private AppContext() {
    }

    private static Map<String, Object> beanStorage = new HashMap<>();
    private static Properties properties = new Properties();

    public static void load(final String filename) {
        if(filename == null) throw new IllegalArgumentException("filename must be to set");
        try (InputStream is = AppContext.class.getClassLoader().getResourceAsStream(filename)) {
        properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static <T> T getBean(final String beanName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if(beanStorage.containsKey(beanName)) return (T) beanStorage.get(beanName);
        final String property = properties.getProperty(beanName);
        if (property == null)
            throw new IllegalArgumentException("bean with name " + beanName + " not found");
            final T bean = (T)Class.forName(beanName).newInstance();
            beanStorage.put(beanName, bean);
            return bean;
        }

}
