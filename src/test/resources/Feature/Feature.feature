Feature: Testing a REST API
  Users should be able to submit GET and POST requests to a web service, 
  represented by WireMock

  Scenario: Book Upload
    When users upload a book data
    Then the server should handle it and return a success status
    
  Scenario: Book retrieval
    When users want to get information about all the books
    Then return all the books data
    
   Scenario: Book Deletion
    When I remove a book from my reading list
    Then The book is removed
    
   Scenario: Book Update
    When users what to update a book
    Then the server should handle it and return a success status