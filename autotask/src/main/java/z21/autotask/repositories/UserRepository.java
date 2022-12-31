package z21.autotask.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import z21.autotask.orm.User;


@Transactional @Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query
    List<User> findByUserId(Integer userId);

    @Query
    List<User> findByLogin(String login);

    @Query
    List<User> findByPassword(String password);

    @Query
    List<User> findByRole(String role);

    @Query
    List<User> findByMail(String mail);

    @Modifying @Query(value = "INSERT INTO users (login,  password, role, mail) VALUES (:login, :password, :role, :mail )", nativeQuery = true)
    void insertUser(@Param("login") String login, @Param("password")  String password, @Param("role") String role,
                        @Param("mail") String mail);

}