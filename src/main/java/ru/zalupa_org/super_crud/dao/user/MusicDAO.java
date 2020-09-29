package ru.zalupa_org.super_crud.dao.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.zalupa_org.super_crud.model.Music;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MusicDAO extends CrudRepository<Music,Long> {

}
