| Number | Check list                                                                         | Status |
|:------:|:-----------------------------------------------------------------------------------|:------:|
|   1    | Password complexity compliance with all requirements                               |  Pass  |
|   2    | Minimum character limit without special or digit characters                        |  Pass  |
|   3    | Minimum character limit with special and digit characters                          |  Pass  |
|   4    | Prevention of password submission with missing digital characters                  |  Pass  |
|   5    | Prevention of password submission with missing special characters                  |  Pass  |
|   6    | Prevention of password submission with missing both digital and special characters |  Pass  |
|   --   | ____                                                                               |  ---   |

|   --   | 9 Homework                                                                                         |      |
|:------:|:---------------------------------------------------------------------------------------------------|:-----|
|   --   | Testing Purpose: Test order deletion with various positive and negative scenarios                  |      |
|   --   | -----------                                                                                        |      | 
|   --   | Tools used: Java, RestAssured, JUnit, Maven documentation, Swagger documentation                   |      |
|   --   | -----------                                                                                        |      |
| Number | Check List                                                                                         |      |
|   1    | Positive test: Successful deletion of an order with a valid data (204 status code)                 | Pass |
|   2    | Negative test: Bad request response for an invalid order ID (400 status code)                      | Pass |
|   3    | Negative test: Not supported requests by the target resource (405 status code)                     | Pass |
|   4    | Negative test: Order that was not found (404 Status code)                                          | Pass |
|   5    | Negative test: Unauthorized user (401 Status code)                                                 | Pass |
|  ---   | -----                                                                                              | ---  |
|        | 10 Homework                                                                                        |      |
|   1    | Positive scenario for login credentials (alphanumeric characters)                                  | Pass |
|   2    | Positive scenario for login credentials (alphanumeric and special credentials)                     | Pass |
|   3    | Positive scenario for login credentials (Upper/Lowercase letters, numerics and special characters) | Pass |
|   4    | Positive scenario for login credentials (Uppercase letters and special characters)                 | Pass |
|   5    | Positive scenario for login credentials (numerics)                                                 | Pass |
|   --   | -----                                                                                              | ---  |







