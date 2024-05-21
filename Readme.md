# Warehouse app
<hr>

### Descriere proiect:

 Warehouse app este o aplicatie ce efectueaza operatii CRUD pe o baza de dare MYSQL.
 Scopul aplicatiei este de a simula partea de back-end a a unui site ce gestioneaza anumite
 produse.
<br></br>
 Practic un user poate sa apeleze endpoint-urile aplicatiei si sa creeze noi produse, sa
 updateze pretul produselor, sa vada ce produse avem in baza de date cat si sa stearga
 produsele din baza de date.

### Tehnologii utilizate
 
 * Spring Framework 6
 * Spring boot 3.2.5
   - spring-boot-starter-web
   - spring-boot-starter-test
   - mysql-connector-j
 * MySQL
 * SQL
 * Java 21

#### Dependente:

> 1.  spring-boot-starter-web: aduce tranzitiv toate dependentele necesare pentru a porni proiectul
      inclusiv serverul de Tomcat
> 2.  spring-boot-starter-test: contine dependente neceasre pentru testare, cum ar fi:
      JUnit
> 3. mysql-connector-j: dependenta necesara pentru a putea conecta aplicatia de baza de date MySQL
     si pentru crearea statemen-urilor.
> 4. MySQL: baza de date relationala folosita in cadrul proiectului
> 5. SQL: Structured Query Language - folosit pentru crearea query-urilor ce vor rula pe baza de date
 
 Toate aceste dependente vor fi adaugate in in fisierul **pom.xml** si folosite in proiect.

![img.png](./img/pom.png)

<hr>

### Initializare proiect:

Pentru creare proiect trebuie accesata pagina: **[start.spring.io](https://start.spring.io/)**
<br>
Din pagina de mai jos se vor selecta urmatoarele pentru crearea proiectului spring boot:
* Project: Maven
* Language: Java
* Spring Boot: 3.2.5 (versiunea)
* Project Medatada:
  - group, artifact, name, description, package name: pot fi definite in functie de tipul proiectului
  - packagin: jar
  - java: 21 (versiunea)
  
<br></br>
![img.png](./img/initializer.png)

<br></br>

## Utilizare aplicatie:

Pentru utilizarea aplicatiei si efectiv pentru a lucra cu ea trebuie apelate o serie de endpoint-uri REST.

### POST /v1/product
```
    @PostMapping("/product")
    public ResponseEntity<Void> saveProduct(@RequestBody ProductRequest productRequest) {
        productService.saveProduct(productRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

```
- salveaza un produs in baza de date, dor daca aces produs exista. Daca produsul cu acea denumire exista aplicatia va
  returna un raspuns de eroare cu status 400(Bad Request). Daca reusim sa salvam produslul in baza de date vom returna
  un http status cod de 200.
<hr>

### GET /v1/product/{id}
```
 @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable int id) {
        ProductResponse productResponse = productService.getProductById(id);
        return ResponseEntity.ok().body(productResponse);
    }
```
- returneaza un produs din baza de date cu respectivul **id**. In cazul in care nu se va gasi prodisul cu acel **id**,
  aplicatia va returna un http status 404 (Not Found).
<hr>

### GET /v1/products

```
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> productResponseList = productService.getAllProducts();
        return ResponseEntity.ok().body(productResponseList);
    }
```
- returneaza o lista de produs din baza de date. In cazul in care in baza de date nu se afla nici un produs va
  va returna o lista goala. De fiecare data vom returna http status 200.

<hr>

### PUT /v1/product/price

````
    public ResponseEntity<String> updateProductPrice(@RequestParam String productName,
                                                     @RequestParam double price) {
        String productResponse = productService.updateProductPrice(productName, price);
        return ResponseEntity.ok().body(productResponse);
    }
````
- cauta un produs in baza de date dupa nume si face update la pret. Daca produsul nu exista returnam hppt status 404.
  Daca nu reusim sa facem update la pret returnam http status 500 (Internal server error)
<hr>

### PUT /v1/product/quantity

```
    @PutMapping("/product/quantity")
    public ResponseEntity<String> updateProductQuantity(@RequestParam String productName,
                                                        @RequestParam int quantity) {
        String productResponse = productService.updateProductQuantity(productName, quantity);
        return ResponseEntity.ok().body(productResponse);
    }
```

- cautam un produs in baza de date dupa nume si facem update la cantitate. Daca produsul nu exista returnam 404.
  Daca pretul nu este updatat returnam 500.
<hr>

### DELETE /v1/product/{id}

```  
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable int id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
```

- sterge un produs din baza de date dupa **id**. Daca produsul nu exista returnam 404. Daca produsul nu este sters
  vom returna un http status 500.
<hr>

## Testare

 * testele pot fi rulate cu urmatoare comanda:
```
1. mvn test
sau
2. ./mvnw test (folosim maven wrapper daca nu avem instalat maven)
```
* rezultat teste:

![img.png](./img/teste.png)

## Pornire aplicatie

Rularea aplicatiei se poate face fie din IntelliJ IDEA sau folosind comenzile de maven:
````
1.  mvn spring-boot:run
sau
2.  ./mvnw spring-boot:run (folosim maven wrapper daca nu avem instalat maven)
````



