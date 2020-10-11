package ru.zalupa_org.super_crud.dao.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.zalupa_org.super_crud.model.Customer;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface CustomerDAO extends CrudRepository<Customer, Long> {

    @Query("select distinct c from Customer c,Music m join Music on m.customer.id = :id and c.id = :id")
    Customer findByIdWithMusicList(@Param("id") Long id);

    @Query("select distinct c from Customer c, Music m left join Music on m.customer.id = c.id")
    Iterable<Customer> findAllWithMusicList();

    Optional<Customer> findByLogin(String login);
}
