Please go through the following steps to run the application.

Install Docker (Docker-Compose) from https://www.docker.com/products/docker-desktop

Once Docker is installed , following steps will start the application

#Step1 : Add permissions to run the bash file

chmod 755 start.sh 

#Step2 :

Run Bash File start.sh "./start.sh" (the process takes around 5 mins to build the environment)

#Step3 :

Implemented Application using Apache-Wicket, Spring Boot, Jquery-UI and DB using REDIS

Redis Database runs on port 7000 and Application runs on 8080. Make sure those ports are not being used when running this.

Access the application on http://localhost:8080 

Works best in Chrome

Following functionalities are implemented.

1. Create, Read, Update and Delete Functionality of Contacts.
2. Search and Filter Contacts.
3. Upload and Download contacts in CSV File.
4. There is an attachment of contacts file "download.csv" please use that for importing data into the application.

#Step4 :

To Cleanup your environment, run cleanup.sh which will cleanup the whole docker environment
