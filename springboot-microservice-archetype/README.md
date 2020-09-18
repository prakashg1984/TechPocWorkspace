# Softwares required

		JDK 8
		Maven
		Tortoise Git client
		Eclipse Kepler
		
# Steps to Create a new module
1. Create a new module from the Archetype : Give the -DgroupId and -DartifactId parameter 
	
		mvn archetype:generate  -DarchetypeGroupId=com.pg -DarchetypeArtifactId=springboot-microservice-archetype -DarchetypeVersion=1.0-SNAPSHOT -DgroupId=com.test.demo -DartifactId=testnewproject
		
		DarchetypeGroupId - Refers to the Group Id of the Archetype project . This will always remain constant.
		DarchetypeArtifactId - Refers to the Artifact Id of the Archetype project . This will always remain constant.
		DarchetypeVersion - Refers to the Version of the Archetype project . This will always remain constant.
		DgroupId - Refers to the Group Id of the new project to be created. This can follow a predefined namespace.

2. Commit this new module to the git hub repository
	
		git init
		git add <moduleName>
		git commit -m "initial commit"
		git remote add origin https://github.com/pg/pgworkspace.git
		git pull https://github.com/pg/pgworkspace.git master --allow-unrelated-histories
		git push -u origin master

		
# Steps to Import this project in Eclipse
1. In Eclipse, right-click on the Project explorer and Click on Import

2. Select the option Existing Maven Projects

3. Give the complete local path of the module created

	eg : C:/newWorkspace/newProject
