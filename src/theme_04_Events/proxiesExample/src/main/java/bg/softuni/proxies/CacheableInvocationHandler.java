package bg.softuni.proxies;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheableInvocationHandler implements InvocationHandler {

    private final Object realObject;
    private final Map<String, Object> cache = new ConcurrentHashMap<>();

    public CacheableInvocationHandler(Object realObject) {
        this.realObject = realObject;
    }

    @Override
    public Object invoke(Object proxy,
                         Method method,
                         Object[] args) throws Throwable {
        //Проверява дали метода, които се вика има @Cacheable анотация:
        Cacheable cacheableAnnotation = realObject
                .getClass()
                .getMethod(method.getName(), method.getParameterTypes())
                .getAnnotation(Cacheable.class);

        if (cacheableAnnotation != null) {
            String cashKey = cacheableAnnotation.value();
            if (cache.containsKey(cashKey)) {
                return cache.get(cashKey);
            } else {
                Object value = method.invoke(realObject, args);
                cache.put(cashKey, value);
                return value;
            }
        } else {
            return method.invoke(realObject, args);
        }
    }
}
