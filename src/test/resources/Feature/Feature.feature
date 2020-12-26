Feature: Testing a REST API
  Users should be able to perform CRUD operation.

  Scenario: Book Upload
    When user upload book data
    Then the book is added
    
  Scenario: Book retrieval
    When to get information about all the books
    Then return all the books data
    
   Scenario: Book Deletion
    When I remove a book from my reading list
    Then The book is removed
    
   Scenario: Book Update
    When users what to update a book
    Then the service should handle it and return the updated book