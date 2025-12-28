## Project Setup Guide
Local Setup Instructions

To run the file locally you need docker desktop in windows or docker engine.

1) Install Docker

Install Docker-engine/Docker-desktop for windows.

2) Environment Configuration

Create a .env file with values

<div> <pre> <code>POSTGRES_USER=your_username<br>POSTGRES_PASSWORD=password<br>POSTGRES_CONNECTION_URL=connection url Example jdbc:&lt;sub-protocol&gt;://&lt;hostname&gt;:&lt;port&gt;/&lt;db_name&gt;<br>POSTGRES_DB=db_name<br>JWT_SECRET=your jwt secret 32bit code else there will be an error.<br></code> </pre> </div>
3) Run the Application

Run the command, before that ensure docker services are up and running.

<div> <pre> <code> docker compose build </code> </pre> </div> <div> <pre> <code> docker compose up </code> </pre> </div>

Your application is now running. The data will be erased once the service is down. If you don't want that use docker volumes.

Happy coding...

# Algorithmic Approach

- Install Docker-engine/Docker-desktop for windows.
- Create a `.env` file with required values.
- Ensure docker services are up and running.
- Run `docker compose build`.
- Run `docker compose up`.
- Application starts and runs with temporary data storage unless docker volumes are configured.
