
## Installation

### Requirements

- Java Runtime Environment (JRE) 17  
- Google Cloud service account credentials (JSON key)  
- Internet connection for API calls  
- Apache Maven 4.0.0
- JUnit 5.0

### Guide

1. ```cmd
    git clone https://github.com/BabboTommaso04/NoSenseGenerator.git
   ```
2. Export GOOGLE_APPLICATION_CREDENTIALS="INSERT API .JSON DIRECTORY” 
   (Tip: after writing the “=“ sign drag and drop the .json file on the terminal window to insert the path)
3. ```cmd
   mvn clean package
   ```
4. ```cmd
   java -jar target/NoSenseGenerator-1.0-SNAPSHOT.jar
   ```

### JSON API File

1. Go to https://console.cloud.google.com and log in with you Google account
2. Click on “Select a project” on the top left of the page
3. Click on “New project” on the top right of the new opened window
4. Name the project “RandomGenerator” or as you like and then click “create”
5. Now you should be redirected to the home of google cloud 
6. On the top left you should now see the name of the newly created project
7. Click on the 3 horizontal bars
8. ![Schermata da 2025-05-28 09-20-46](https://github.com/user-attachments/assets/ff83d6be-b6ba-4b6c-9a04-e8187a92be63)
9. Go to “IAM & Admin”
10. Select “service account”
11. Select “create service account” at the top
12. Give the account a name you like and then click on “create and continue”
13. Select a role by choosing: role -> basic -> owner (to grant full access)
14. Click “continue” and then “done”
15. On the right, click on the three dots under “Actions” and select “Manage Keys”
16. ![Schermata da 2025-05-28 09-23-51](https://github.com/user-attachments/assets/7de918ea-d5cd-4d67-b2fc-5d1094ebeca8)
17. Now click on “Add key” and then “Create a new key”
18. ![image](https://github.com/user-attachments/assets/285d69ee-d33f-4071-bc5f-0506429806b8)
19. Select the .json format and confirm

Now your Api file will be downloaded
