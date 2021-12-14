Example queries for Mongo Compass:

``` { lastName: "Bundy" }  ```

Or make a ```/GET``` request to ```/favourite/number```.
This way you can pass your query criterias as JSON body and 
the program will execute the respective query method for UserService:
```
    @Query("{ 'favouriteNumber' : ?0 }")
    List<User> findUsersByFavouriteNumber(Integer favouriteNumber);
```
