call mvn clean install -Dmaven.test.skip=true
call javapackager.exe -createjar -nocss2bin -appclass tests.idea.DebudTest -srcdir target/classes -outdir target/packager_jar -outfile sct.jar -v -manifestAttrs "Specification-Version=1.0,Implementation-Version=SNAPSHOT,Specification-Title=sct Migration Studio for MySQL"
call javapackager.exe -deploy -srcdir target/packager_jar -outdir target/production -outfile sct -width 800 -height 600 -name "sct" -appclass com.amazon.sct.App -native -title "sct" -vendor "amazon.com" -description "sct" -v
pause