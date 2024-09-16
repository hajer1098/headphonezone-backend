package com.hajer.Headphone.Repositories;

import com.hajer.Headphone.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {


    Optional<User> findByEmail(String email);
    //select *from user where firstname="hajer"

    //List<User> findAllByFirstname (String firstname);

    //select *from user where firstname like "Hajer"

   // List<User> findAllByFirstnameContaining (String firstname);


    //select *from user where firstname like "Hajer"

   // List<User> findAllByFirstnameContainingIgnoreCase (String firstname);

    //select  * from user where firstname ="hajer and email=""

    //User findByFirstnameContainingAndEmail (String firstname,String email );

   // @Query("from User where firstname =:fn")
    //List<User> searchFirstname (@Param("fn") String firstname);

    //@Query("from User where firstname = ':%fn%'")
    //select *from user where firstname like "Hajer"

    //List<User> searchFirstnameContaining (String firstname);



}
