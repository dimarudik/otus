package ru.otus.docker.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.docker.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
