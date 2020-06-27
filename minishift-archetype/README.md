# Softwares required

		JDK 8
		Maven
		Tortoise Git client
		Eclipse Kepler
		Oracle VirtualBox
		Minishift

# Steps to check if the Minishift Platform is running
1. Try to access the Minishift URL

		https://192.168.99.100:8443/console/
		Credentials to be used : developer/developer

2. If the above URL is not accessible, then we need to start the Minishift VM
	
		Go inside the minishift installation
			cd C:\Users\pg939j\minishift

		Start the minishift
			minishift start --vm-driver=virtualbox --memory=4096

		Check the status from the command line
			minishift status

		Try to access the URL again
			https://192.168.99.100:8443/console/


# Steps to Create a new module
1. Create a new workspace in you local C drive or use an existing workspace if already present.
	
	mkdir newWorkspace

2. Create a new module from the Archetype : Give the -DgroupId and -DartifactId parameter 
	
		mvn archetype:generate  -DarchetypeGroupId=com.pg -DarchetypeArtifactId=minishift-archetype -DarchetypeVersion=1.0-SNAPSHOT -DgroupId=com.pg -DartifactId=testocenewproject
		
		DarchetypeGroupId - Refers to the Group Id of the Archetype project . This will always remain constant.
		DarchetypeArtifactId - Refers to the Artifact Id of the Archetype project . This will always remain constant.
		DarchetypeVersion - Refers to the Version of the Archetype project . This will always remain constant.
		DgroupId - Refers to the Group Id of the new project to be created. This can follow a predefined namespace like com.pg
		DartifactId - Refers to the Module name of the new project to be created

3. Commit this new module to the git hub repository
	
		git init
		git add <moduleName>
		git commit -m "initial commit"
		git remote add origin https://github.com/prakashg1984/workplace.git
		git pull https://github.com/prakashg1984/workplace.git master --allow-unrelated-histories
		git push -u origin master


4. Now Create a new ci project in mini shift. Or if already a CI Project is present, we can reuse the same.
	
		oc login 192.168.99.100:8443 -u developer -p developer
		oc new-project <ciProjectName>

		ciProjectName - Refers to the new CI Project that needs to be created in minishift. Each project needs to have an individual CI project also in minishift to configure the pipeline builds

		eg : oc new-project springbootci

5. Give permission to this project. This is required to do the pipeline build. 
		
		**Change the <ciProjectName> value with the actual CI project create on step 4**
		oc login -u system:admin
		oc adm policy add-cluster-role-to-user cluster-admin system:serviceaccount:<ciProjectName>:jenkins -n <ciProjectName>

		ciProjectName - Refers to the new project created in Minishift in step 4


6. Run the OC Create command to create the pipleline. This should be the raw url from Github repository
	
		Login to the minishift as developer user

			oc login 192.168.99.100:8443 -u developer -p developer

		Go inside the newly created CI Project in step 4

			oc project <ciProjectName>

		Create the Pipeline build by using the Pipeline.yml file generated using the Archtype

			oc create -f <RawgitURL for pipeline.yml>
			eg : oc create -f https://raw.githubusercontent.com/oceatt/oceattworkspace/master/testocenew/pipeline.yml
		
	To generate the raw GitURL for pipeline.yml
		
		Go inside the git hub repository https://github.com/oceatt/oceattworkspace
		Go inside the newly created project in step 2
		Open the pipeline.yml file
			eg : https://github.com/oceatt/oceattworkspace/blob/master/testocenew/pipeline.yml
		Click on the Raw button to generate the Raw url for the pipeline.yml
			eg : https://raw.githubusercontent.com/oceatt/oceattworkspace/master/testocenew/pipeline.yml
		

7. Goto the Openshift console and go inside the pipeline project

	https://192.168.99.100:8443/console/
	Credentials : developer/developer

8. On the left hand side click on Builds/Pipelines 

9. Start the newly created Pipeline project and monitor the build progress in Jenkins by clicking on view log in the pipeline page

10 Once the build is complete, open the actual project from the Projects list in the Openshift console
	
	eg : https://192.168.99.100:8443/console/project/helloworld-msa/overview
	
11 On the overview page, click on the Test URL given for the deployment
	
	eg : http://aloha-helloworld-msa.192.168.99.100.nip.io/
	
	
# Steps to Import this project in Eclipse
1. In Eclipse, right-click on the Project explorer and Click on Import

2. Select the option Existing Maven Projects

3. Give the complete local path of the module created
	
	eg : C:/newWorkspace/newProject
