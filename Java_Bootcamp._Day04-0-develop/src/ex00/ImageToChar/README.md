#1. Create directory:
- mkdir target

#2. Compile files to the 'target' directory:
- javac "ImageToChar_Folder"/src/java/edu/school21/printer/app/App.java  "ImageToChar_Folder"/src/java/edu/school21/printer/logic/PrinterBlackWhiteImages.java -d ./target

#3. Run program:
- java -cp target/ edu.school21.printer.app.App . O  /Users/sshera/Desktop/Smago_Piscine_Java/Day04/it.bmp
