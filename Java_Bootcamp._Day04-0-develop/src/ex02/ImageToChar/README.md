#1. Create directory:
- mkdir target && mkdir lib

#2. Download libraries:
-    curl https://repo1.maven.org/maven2/com/beust/jcommander/1.72/jcommander-1.72.jar -o lib/jcommander-1.72.jar
-    curl https://repo1.maven.org/maven2/com/diogonunes/JCDP/4.0.0/JCDP-4.0.0.jar -o lib/JCDP-4.0.0.jar

#3. Extract files and replace to directory 'target':
- cd target
- jar xf ../lib/jcommander-1.72.jar
- jar xf ../lib/JCDP-4.0.0.jar
- cd ..


#4. Compile files to the 'target' directory:
- javac -cp lib/\\* src/java/edu/school21/printer/app/App.java src/java/edu/school21/printer/logic/PrinterBlackWhiteImages.java src/java/edu/school21/printer/logic/Args.java -d ./target

#5. Copy resources:
- cp -r src/resources target/.

#6. Create jar:
- jar cfm ./target/images-to-chars-printer.jar src/manifest.txt -C target .

#7. Run program:
- java -cp target/ edu.school21.printer.app.App --white=RED --black=GREEN
