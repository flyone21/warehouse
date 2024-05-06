Pentru rularea aplicatiei JAVA "warehouse" trebuie sa urmati aceste instructiuni doar daca nu le aveti instalate.
Utilizatorul trebuie sa aiba instalat si sa fie pornit mysql workbench, Xamp si postman 
in MySQL Workbench trebuie sa existe o conexiune facuta, hostanme:127.0.0.1 , port 3306, username: root si parola: "".
In Xampp se va da START la MySql si la Apache.
Se va insera urmatorul script in MySql workbench:
CREATE DATABASE IF NOT EXISTS warehouse;
USE warehouse;
CREATE TABLE IF NOT EXISTS products(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL,
	price DOUBLE,
    quantity INT
);
In resurssa https://start.sprin.io  se selecteaza : 
Project: Maven , Language: Java Sprin Boot: 3.2.5 , Packaging: jar, Project Metadata: Group: com.itFactory, Artifact: warehouse, Name: project, Description: itFactory final project, package name: com.sebastian 
Ulterior se va asigura ca exista si urmatoarele dependinte: -Spring Web; -Mysql driver.
Se va accesa butonul GENERATE PROJECT. Se va genera un folder cu extensia zip. Se va dezarhiva si se va deschide IntelliJIdea iar linkul de adresa a folderului se va trece in optiunea OPEN din IntelliJ.
Dupa incarcare folderului, se va deschide structura proiectului si se va verfica fisierul pom.xml unde se va verifica <dependecy> daca este ultima versiune. In cazul in care nu este se poate descarca ultima versiune din https://mvnrepository.com/artifact/com.mysql/mysql-connector-j si se va copia scriptul ultimei versiuni stabile.
In IntelliJ in main in com.itFactory vom gasi "WarehouseApplication" unde la rularea acestuia vom verifica conexiunea cu baza de date.
Aplicatia WAREHOUSE are ca scop sa inserezein baza de date un produs, sa listeze lista de produse din baza de date, sa actualizeze (update) pretul sau cantitatea unui produs si sa stearga un produs din baza de date.






