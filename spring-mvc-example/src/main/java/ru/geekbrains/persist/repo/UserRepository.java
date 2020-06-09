package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.enity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
