#1. Create directory:
- mkdir target

#2. Compile files to the 'target' directory:
- javac src/java/edu/school21/printer/app/App.java  src/java/edu/school21/printer/logic/PrinterBlackWhiteImages.java -d ./target

#3. Copy resources:
- cp -r src/resources target/.

#4. Create jar:
- jar cfm ./target/images-to-chars-printer.jar src/manifest.txt -C target .

#5. Run program:
- java -cp target/ edu.school21.printer.app.App . O  /Users/jcraster/Java_Bootcamp/Java_Bootcamp._Day04-0-develop/src/ex01/ImageToChar/src/resources/it.bmp
