# Spring Boot Application connected to MySQL server
----------------------------
## Spring Boot Application, utilizing Spring Data JPA to interact with MySQL DB server. It represents a model of geographical units with relationships between them. The project is structured, following the Controller, Service, Repository pattern with the corresponding packages. 

- controller, containing the REST APIs
- service, containing the business logic
- repository, containing the data access logic
- model, containing the Entity Classes
- dto, containing the DTO Records
- GeographicalUnitsApplication Class, the launcher of the App
- resources, containing the application.properties - the app configuration file
- pom.xml, the project-build configuration file
----------------------------

## Structure

- Three Model classes with bidirectional relationships, managed by Hibernate, stored as DB entities
- Each Entity class is served by it's own RestController and Service classes, DTO records and Repository interface, which together provide the needed functionality to process the data and interact with the DB 
- Three helper classes - mappers, placed in the service package, perform the mapping DTO to Entity and Entity to ResponseDTO
- In the application.properties file, the necessary configuration regarding the connectivity to a remote MySQL server can be found
- In the pom.xml file, all the necessary dependencies and plugins can be found
----------------------------
### The Application works with data in JSON format and multipart files. It supports the CRUD operations.

> The hierarchy of the entities and their interactions are described here.
> Continent is a parent entity, containing one-to-many relationship with Country/many countries as child entity/entities.
> Country has many-to-one relationship with it's parent entity - Continent and one-to-one relationship with it's child entity - Flag.
> Flag has one-to-one relationship with it's parent entity Country.
> @JsonManagedReference and @JsonBackReference are used to avoid infinite recursion.
> Cascading is used to ensure the child entity is affected by the same action as it's parent. More functionality is added to the child side, to make it possible to independently remove child entity.
> The Flag(MultipartFile) is stored in and retrieved from separate storage. In the database only details about the file are stored. Hence two endpoints for retrieval are added in the Flag's RestController - getFlagByName for file downloading from the storage and getFlagDetails for retrieving only the details from the database.

### Next Steps
Additional features, regarding security, like login controller, database user authentication, password encryption will be integrated into this project or developed as a separate more advanced one.
