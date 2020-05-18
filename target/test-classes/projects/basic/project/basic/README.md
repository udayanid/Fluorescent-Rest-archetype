# qr-code-for-me-server

QR Code for Me Server Implementation

#Create a database in you rdbms: 
    mention the db and its credentials in the properties file located in src/main/resources

    Note: dev profile is the default one and it is configured in pom.xml

# Property change 
    change the property 'spring.jpa.hibernate.ddl-auto' from crate to update after the first execution 
    and comment the block of code present in the run method of LoadInitialDataCommandLineRunner class
 