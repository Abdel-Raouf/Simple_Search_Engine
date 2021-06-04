## Object Oriented Design:

#### SOLID:
Applying `SOLID` principles to the application design, as a result we can scale easily:
1. By making each `class` has a single job to do only (single responsibility principle) as the `EnglishDocumentProcessor Class` that is responsible for processing of english documents only, but if we need to process `spanish language documents` we will need to make a new class that handles spanish only. .
2. Our `EnglishDocumentProcessor Class` doesn't depend on the database engine (open-closed principle), so no matter the type of database our application uses, we cann connect to the database without any problems.
3. Every `Class` implements the interface that it needs it's method only (Interface segregation principle).
4. All `Classes` Depend on abstraction, not on concretions (Dependency inversion principle), there isn't any `Class` we implement that need to depend on the instantiation of another class, which allows for `Decoupling`.

## Classes Functionality:
1. `TextDocumentReader` (under the `documentreader` Package): This is a `TODO Class` that we can handle the scale of our search engine with, by parsing text documents and pass it to our language processors. We can also implmenet similiar `Classes` that handle the parsing of special type only as `PdfDocumentReader` and `DoxDocumentReader`.
2. `EnglishDocumentProcessor` (under the `langdocumentprocessors` Package): This is the class responsible for implementing our `inverted index`, `term frequency` , `inverse document frequency` and sorting based on `multiplication of termFreq and inverseDocFreq` for english documents only.
3. `SqliteIndexWriter` (under the `storagewriter` Package): This class handles the insertion of words, documents and their ranking based on the sorting mechanism we use to our `SqliteDB`.
4. `SqliteIndexReader` (under the `storagereader` Package): this class handles the querying of the SqliteDB.

## Database:


## Testing Using `Junit`:

#### Unit Testing:

#### Integration Testing:

#### Functional Testing:
