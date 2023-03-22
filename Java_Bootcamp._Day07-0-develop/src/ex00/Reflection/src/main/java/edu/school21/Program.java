package edu.school21;

import edu.school21.reflection.ReflectionChecker;
import edu.school21.reflection.ReflectionEditor;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Program {
    private static List<Class<?>> classes;
    private static final Scanner scanner=new Scanner(System.in);
    private static final ReflectionChecker reflectionChecker = new ReflectionChecker();
    private static final ReflectionEditor reflectionEditor = new ReflectionEditor();
    private static Class<?> currentClass=null;
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        classes = reflectionChecker.getAllClassesFrom(Program.class.getPackageName()+".classes");
        PrintClassesName();
        FindClassByName();
        Object object = reflectionEditor.CreateObject(scanner, currentClass);
        System.out.println(object);
        reflectionEditor.ChangeObject(scanner, object,currentClass);
        reflectionEditor.CallMethods(scanner, object,currentClass);
    }

    private static void FindClassByName() {
        String className = scanner.nextLine();
        for(Class<?> clazz: classes){
            if(clazz.getName().substring(clazz.getPackageName().length()+1).equals(className)){
                currentClass= clazz;
            }
        }
        if(currentClass == null)
            throw new RuntimeException("Not found!");
        reflectionChecker.PrintClassFields(currentClass);
        reflectionChecker.PrintClassMethods(currentClass);
    }

    public static void PrintClassesName(){
        System.out.println("Classes:");
        for(Class<?> clazz: classes){
            System.out.println("\t- "+ clazz.getName());
        }
    }

}
