package be.unamur.ct.data.dao;

import be.unamur.ct.download.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerDao extends JpaRepository<Server, Integer> {

    Server findById(long id);

    boolean existsByUrl(String url);

}
