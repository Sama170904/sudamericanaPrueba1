package com.example.crudrapido.repository;

import com.example.crudrapido.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
        select t from Token t inner join Administrador a on t.administrador.id = a.id
        where a.id = :adminId and (t.expired = false or t.revoked = false)
        """)
    List<Token> findAllValidTokensByUser(Integer adminId);

    Optional<Token> findByToken(String token);
}