package ex05;

import java.util.Scanner;

public class Program {
    static int MAX_NUMBER_OF_STUDENT = 10;
    static int MAX_STUDENT_NAME=10;
    static int NUM_OF_FREE_HOURS_PER_DAY=5;
    static int NUM_DAYS_OF_WEEK=7;
    static int MAX_NUM_SUBJECTS_PER_WEEK=10;
    static int NUM_DAYS_IN_SEPTEMBER=30;
    public static void main(String[] args){
        String studentList[]=new String[MAX_NUMBER_OF_STUDENT];
        Scanner myScanner = new Scanner(System.in);
        int numOfStudent = ReadListOfStudent(myScanner,studentList);
        boolean timeTable [][] = new boolean[NUM_DAYS_OF_WEEK][NUM_OF_FREE_HOURS_PER_DAY];
        ReadTimeTable(timeTable,myScanner);
        int attendanceTable[][][]=new int[MAX_NUMBER_OF_STUDENT][NUM_DAYS_IN_SEPTEMBER][NUM_OF_FREE_HOURS_PER_DAY];
        ReadAttendanceTable(attendanceTable,timeTable,studentList,numOfStudent,myScanner);
        PrintResult(studentList,numOfStudent,timeTable,attendanceTable);
    }

    private static void PrintResult(String[] studentList, int numOfStudent, boolean[][] timeTable, int[][][] attendanceTable) {
        System.out.printf("          ");
        for(int i=0;i<NUM_DAYS_IN_SEPTEMBER;i++){
            for (int k = 0; k < NUM_OF_FREE_HOURS_PER_DAY; k++) {
                if(timeTable[i%NUM_DAYS_OF_WEEK][k]){
                    System.out.printf("%d:00 %s %2d|",k+1,GetNameDayByNumber(i+1),i+1);
                }
            }
        }
        System.out.print("\n");
        for(int i=0;i<numOfStudent;i++){
            System.out.printf("%10s",studentList[i]);
            for(int j=0;j<NUM_DAYS_IN_SEPTEMBER;j++){
                for (int k = 0; k < NUM_OF_FREE_HOURS_PER_DAY; k++) {
                    if(timeTable[j%NUM_DAYS_OF_WEEK][k]){
                        System.out.print((attendanceTable[i][j][k] == 1 ? "        -1" :
                                attendanceTable[i][j][k] == 2 ? "         1" : "          ") + "|");
                    }
                }
            }
            System.out.print('\n');
        }
    }

    private static String GetNameDayByNumber(int numDay) {
        switch ((numDay-1)%NUM_DAYS_OF_WEEK) {
            case 0 -> {
                return "TU";
            }
            case 1 -> {
                return "WE";
            }
            case 2 -> {
                return "TH";
            }
            case 3 -> {
                return "FR";
            }
            case 4 -> {
                return "SA";
            }
            case 5 -> {
                return "SU";
            }
            case 6 -> {
                return "MO";
            }
        }
        return null;
    }


    private static void ReadAttendanceTable(int[][][] attendanceTable, boolean[][] timeTable, String[] studentList, int numOfStudent, Scanner myScanner) {
        String name,attendance;
        int hour,numOfDay, indexStudent;
        while(true){
            name=myScanner.next();
            if(name.equals(".")) break;
            hour=myScanner.nextInt();
            numOfDay=myScanner.nextInt();
            attendance=myScanner.next();
            indexStudent=0;
            for(;indexStudent<numOfStudent&&(!studentList[indexStudent].equals(name));indexStudent++);
            if(!isCorrectAttendance(timeTable,indexStudent,numOfStudent,hour,numOfDay,attendance)) CorrectExit("Incorrect attendance!",myScanner);
            attendanceTable[indexStudent][numOfDay-1][hour-1]=attendance.equals("HERE")?2:1;
        }
    }

    private static boolean isCorrectAttendance(boolean[][] timeTable, int indexStudent, int numOfStudent, int hour, int numOfDay, String attendance) {
        if(!(attendance.equals("NOT_HERE")||attendance.equals("HERE"))) return false;
        if(indexStudent==numOfStudent) return false;
        if(numOfDay>NUM_DAYS_IN_SEPTEMBER||numOfDay<1) return false;
        return (timeTable[(numOfDay-1)%NUM_DAYS_OF_WEEK][hour-1]);
    }


    private static void ReadTimeTable(boolean[][] timeTable, Scanner myScanner){
        int hour;
        String dayOfWeek,temp;
        for(int i=0;i<MAX_NUM_SUBJECTS_PER_WEEK;i++){
            if(!myScanner.hasNextInt()) {
                temp=myScanner.next();
                if(temp.equals(".")) break;
                CorrectExit("Incorrect input!", myScanner);
            }
            hour=myScanner.nextInt();
            if(hour<1 || hour>5) CorrectExit("Incorrect input! Time > 5 or Time < 1",myScanner);
            if(!myScanner.hasNext()) CorrectExit("Incorrect name day of week!",myScanner);
            dayOfWeek=myScanner.next();
            SetSubjectByDayAndTime(timeTable,hour,dayOfWeek,myScanner);
        }
    }

    private static void SetSubjectByDayAndTime(boolean[][] timeTable, int hour, String dayOfWeek, Scanner myScanner){
        switch (dayOfWeek) {
            case "TU" -> timeTable[0][hour-1] = true;
            case "WE" -> timeTable[1][hour-1] = true;
            case "TH" -> timeTable[2][hour-1] = true;
            case "FR" -> timeTable[3][hour-1] = true;
            case "SA" -> timeTable[4][hour-1] = true;
            case "SU" -> timeTable[5][hour-1] = true;
            case "MO" -> timeTable[6][hour-1] = true;
            default -> CorrectExit("Incorrect name day of week!", myScanner);
        }
    }

    private static int ReadListOfStudent(Scanner myScanner,String[] studentList){
        String tempStr;
        int i;
        for(i = 0; i < MAX_NUMBER_OF_STUDENT && myScanner.hasNext(); i++){
            tempStr = myScanner.nextLine();
            if(tempStr.equals(".")) break;
            if(tempStr.length() > MAX_STUDENT_NAME) {
                CorrectExit("Incorrect length name of the student!",myScanner);
            }
            studentList[i]=tempStr;
        }
        return i;
    }

    private static void CorrectExit(String msg, Scanner scanner) {
        System.err.println(msg);
        scanner.close();
        System.exit(-1);
    }
}
