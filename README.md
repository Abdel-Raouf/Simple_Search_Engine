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
- We used `SqliteDB`as it is convenient for it's ease of use and the small scale we work on:
  - Created a database called -> `search_engine.db`.
  - Created table -> `CREATE TABLE search_engine (id INTEGER PRIMARY KEY AUTOINCREMENT, word VARCHAR, docs VARCHAR, sorting_score FLOAT)`.
  - Created an `index` on the `word field` -> Due to we will always `filter` based on the `word` (`WHERE word=='word we need to filter with'`).
  - our `search_engine.db Sqlite file` is under the `resources directory` -> inside the `database directory`.

## Testing Using `Junit`:

#### Unit Testing:
1. We unit tested our `EnglishDocumentProcessor Class` by Passing `list of documents` to it's `processDocument Method` (to give us Actual Result).
2. we implemented an `Array List`, then give it the `words` with their `documents` and `tf*idf sorting score` for each word related to a specfici document (to be the Expected Result).
3. Used `Junit AssertEquals Method` to compare the `Expected Result` and `Actual Result`. 
#### Integration Testing:
- We make an integration testing to our `EnglishDocumentProcessor Class` and `SqliteIndexWriter Class` by testing if the `insertion` of the result returned from the `EnglishDocumentProcessor Class -> processDocument Method`  will be successful to our `SqliteDB` or not.

#### Functional Testing:
- We make a functional testing by querying our `SqliteDB` using `SqliteIndexReader Class -> read Method`:
  - `read Method` accepts `word` we need to query from `SqliteDB` as a parameter.
  - Then issue an SQL statement -> `SELECT docs FROM search_engine WHERE word=='brown' ORDER BY sorting_score DESC`.
  - We then implemented an `Array List` and add to it the Expected Result -> `[document 1, document 2]`
  - Then comparing the `Expected Result` to the `Actual Result` using `assertEquals`.
