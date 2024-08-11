export CLASSPATH=$CLASSPATH:/home/mirlin/ITUniversity/librairies/postgresql-42.3.5.jar

javac -d . generator/parser/FileParser.java

javac -d . generator/dao/DbConnection.java generator/parser/FileParser.java
javac -d . generator/mapping/ClassMapping.java generator/dao/DbConnection.java generator/parser/FileParser.java

javac -d . generator/utils/ObjectUtility.java generator/mapping/ClassMapping.java generator/dao/DbConnection.java generator/parser/FileParser.java
javac -d . generator/utils/ExtractSubstring.java
javac -d . generator/service/DbService.java generator/utils/ObjectUtility.java generator/mapping/ClassMapping.java generator/dao/DbConnection.java generator/parser/FileParser.java
javac -d . generator/service/DotnetGenerationService.java generator/service/DbService.java generator/utils/ObjectUtility.java generator/mapping/ClassMapping.java generator/dao/DbConnection.java generator/parser/FileParser.java
javac -d . generator/service/JavaGenerationService.java generator/service/DotnetGenerationService.java generator/service/DbService.java generator/utils/ObjectUtility.java generator/mapping/ClassMapping.java generator/dao/DbConnection.java generator/parser/FileParser.java
javac -d . generator/service/JavaGenerationServiceForController.java generator/service/DotnetGenerationService.java generator/service/DbService.java generator/utils/ObjectUtility.java generator/mapping/ClassMapping.java generator/dao/DbConnection.java generator/parser/FileParser.java
javac -d . generator/service/JavaGenerationServiceForService.java generator/service/DotnetGenerationService.java generator/service/DbService.java generator/utils/ObjectUtility.java generator/mapping/ClassMapping.java generator/dao/DbConnection.java generator/parser/FileParser.java

javac -d . generator/CodeGenerator.java generator/service/JavaGenerationService.java generator/service/DotnetGenerationService.java generator/service/DbService.java generator/utils/ObjectUtility.java generator/mapping/ClassMapping.java generator/dao/DbConnection.java generator/parser/FileParser.java
javac -d . generator/CLIReader.java generator/CodeGenerator.java generator/service/JavaGenerationService.java generator/service/DotnetGenerationService.java generator/service/DbService.java generator/utils/ObjectUtility.java generator/mapping/ClassMapping.java generator/dao/DbConnection.java generator/parser/FileParser.java

javac -d . generator/Main.java generator/CodeGenerator.java generator/CLIReader.java generator/service/JavaGenerationService.java generator/service/DotnetGenerationService.java generator/service/DbService.java generator/utils/ObjectUtility.java generator/mapping/ClassMapping.java generator/dao/DbConnection.java generator/parser/FileParser.java

java generator.Main
