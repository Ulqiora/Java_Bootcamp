package edu.school21.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ReflectionEditor {


    public void CallMethods(Scanner scanner, Object object, Class<?> clazz) throws InvocationTargetException, IllegalAccessException {
        while (true) {
            List<Object> parameters = new ArrayList<>();
            System.out.println("Enter name of the method for call:");
            String name = scanner.nextLine().trim();
            List<Method> list = Arrays.stream(clazz.getMethods())
                    .filter(m -> name.equalsIgnoreCase(m.getName()))
                    .collect(Collectors.toList());
            if (list.isEmpty()) {
                System.out.println("Method not found: " + name);
            } else {
                Method method = list.get(0);

                if (!method.isAccessible())
                    method.setAccessible(true);

                for (Class<?> param : method.getParameterTypes()) {
                    addParameters("Enter " + getValue(param.getName()) + " value:",
                            param, parameters,scanner);
                }
                returnedValue(method, method.invoke(object, parameters.toArray()));
                break;
            }
        }
    }
    private String getValue(String str) {
        if (str.contains(".")) {
            str = str.substring(str.lastIndexOf('.') + 1);
        }
        return str;
    }

    public void ChangeObject(Scanner scanner,Object object, Class<?> clazz) throws IllegalAccessException {
        while (true) {
            System.out.println("Enter name of the field for changing:");
            String name = scanner.nextLine().trim();
            List<Field> list = Arrays.stream(clazz.getDeclaredFields())
                    .filter(f -> name.equalsIgnoreCase(f.getName()))
                    .collect(Collectors.toList());

            if (list.isEmpty()) {
                System.out.println("Field not found: " + name);
            } else {
                Field field = list.get(0);
                field.setAccessible(true);
                System.out.println("Enter " + field.getType().getName() + " value:");
                String value = scanner.nextLine().trim();
                String str = field.getType().getName();

                if (str.contains("String")) {
                    field.set(object, value);
                } else if (str.contains("int") || str.contains("Integer")) {
                    field.set(object, Integer.parseInt(value));
                } else if (str.contains("boolean") || str.contains("Boolean")) {
                    field.set(object, Boolean.parseBoolean(value));
                } else if (str.contains("double") || str.contains("Double")) {
                    field.set(object, Double.parseDouble(value));
                } else if (str.contains("long") || str.contains("Long")) {
                    field.set(object, Long.parseLong(value));
                } else {
                    System.out.println("UNKNOWN PARAMETER TYPE");
                    System.exit(-1);
                }
                System.out.println("Object updated: " + object);
                break;
            }
        }
    }

    public Object CreateObject(Scanner scanner, Class<?> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Field[] fields=clazz.getDeclaredFields();
        Constructor<?> constructor = Arrays.stream(clazz.getConstructors()).filter(c->c.getParameterCount()==fields.length).collect(Collectors.toList()).get(0);
        List<Object> parameters = new ArrayList<>();
        for (Field field : fields) {
            addParameters(field.getName() + ":", field.getType(), parameters,scanner);
        }
        return constructor.newInstance(parameters.toArray());
    }
    private static void addParameters(String title, Class<?> param, List<Object> parameters,Scanner scanner) {
        while (true) {
            System.out.println(title);
            String value = scanner.nextLine().trim();
            try {
                if (param.getName().contains("String")) {
                    parameters.add(value);
                } else if (param.getName().contains("int") || param.getName().contains("Integer")) {
                    parameters.add(Integer.parseInt(value));
                } else if (param.getName().contains("boolean") || param.getName().contains("Boolean")) {
                    parameters.add(Boolean.parseBoolean(value));
                } else if (param.getName().contains("double") || param.getName().contains("Double")) {
                    parameters.add(Double.parseDouble(value));
                } else if (param.getName().contains("long") || param.getName().contains("Long")) {
                    parameters.add(Long.parseLong(value));
                } else {
                    System.out.println("UNKNOWN PARAMETER TYPE");
                    System.exit(-1);
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid value: " + e.getMessage());
            }
        }
    }
    private void returnedValue(Method method, Object obj) {
        if (!method.getReturnType().getName().equalsIgnoreCase("void")) {
            System.out.println("Method returned: ");
            System.out.println(obj);
        }
    }

}
