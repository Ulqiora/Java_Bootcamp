package edu.school21.reflection;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public class ReflectionChecker {
    public void PrintClassFields(Class<?> object) {
        Field[] fields=object.getDeclaredFields();
        System.out.println("Fields:");
        for(Field field: fields){
            String[] typename=field.getType().getName().split("\\.");
            System.out.printf("\t %10s : %10s\n",typename[typename.length-1],field.getName());
        }
    }
    public void PrintClassMethods(Class<?> object) {
        Method[] methods=object.getDeclaredMethods();
        for(Method method: methods){
            Class<?>[] typesArg = method.getParameterTypes();
            String[] returnTypeSplit =method.getReturnType().getName().split("\\.");
            System.out.printf("\t%10s %s(",returnTypeSplit[returnTypeSplit.length-1],method.getName());
            for(int i=0;i< typesArg.length;i++){
                String[] typename=typesArg[i].getName().split("\\.");
                System.out.print(typename[typename.length-1]);
                if(i!=(typesArg.length-1)) System.out.print(", ");
            }
            System.out.printf(");\n");
        }
    }
    public ArrayList<Class<?>> getAllClassesFrom(String packageName) throws IOException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class<?>> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }
    private List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
