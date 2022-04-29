javac src/nutriscoremfiakka/*.java -d classes/nutriscoremfiakka/
echo Main-Class: NutriscoreMFIAKKA > classes/manifest.txt
jar cvfm Nutriscore.jar classes/manifest.txt
