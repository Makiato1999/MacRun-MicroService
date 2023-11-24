# Build and Deploy the Docker Image

## Build the Docker Image

1. Clone the GitHub repo onto your computer.
2. From the main directory, run the following command:
   ```bash
   mvn package -Dskiptests
   ```
   This will build jars of our services.
3. Next, we'll go into the `deployment` directory and start our Docker containers.
   ```bash
   cd deployment
   docker-compose up
   ```
   The necessary files will be downloaded and your services should start running!

## Deploying your container to Dockerhub

1. Clone the GitHub repo onto your computer. Go to https://hub.docker.com/signup and sign up. In the rest of these
   steps, replace
   all references to `myuser` with your actual DockerHub username.
2. Inside the `docker-compose.yml` file, add a new line to each of the three services, in the following
   format:
   ```yml
   image: myuser/servicename
   ```
3. Run `docker-compose build` to build the images.
2. Run `docker login` to log into your DockerHub account.
3. Run `docker-compose push` to push your images to your DockerHub account.
4. Log into your DockerHub account. You should see the new image repositories.
5. Lastly, we want to run these from DockerHub. But we want to delete the local copies to ensure we can access.
   the DockerHub ones. First, go into Docker Desktop, click Containers, and delete the containers.
6. Then, go into the Images, and delete all the images by first pressing "Clean up...'
7. Finally, inside the `deployment` folder, run `docker-compose -f docker-compose-prod.yml up`. The images should be
   downloaded and will start running!
8. Go to Postman and create GET/Post request with the
   link `http://localhost:8080/user/register?email=user@email.com&userName=user`, you can see the request is successful!

## App
1. User Register -> Trail Allocation -> Game Center
   - start up user-opt service, trail-center service, game-center service by ```mvn spring-boot:run```
   - send a POST request such as
     ```
     url: http://localhost:8075/user/register
     body:
     {
        "email": "Daryl@mcmaster.ca",
        "userName": "Daryl"
     }
     ```
2. User Operation -> Game Center (Attack Decision)
   - start up user-opt service and game-center service by ```mvn spring-boot:run```
   - send a POST request such as
     ```
     url: http://localhost:8075/user/opt
     body:
     {
        "optID": "2"
     }
     ```
3. Eureka service discovery
   - run eureka server by ```mvn spring-boot:run```
     - access the web UI by `http://localhost:8761/`
   - run gateway service by ```mvn spring-boot:run```
   - run service by ```mvn spring-boot:run```
      - open a new terminal and create another instance by ```mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=9091 --server.instance.id=2"```