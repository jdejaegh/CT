package be.unamur.ct.data.dao;

import be.unamur.ct.download.model.Server;
import be.unamur.ct.download.model.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SliceDao extends JpaRepository<Slice, Integer> {

    @Transactional
    long deleteById(long id);

    boolean existsById(long id);

    List<Slice> findByServerOrderByEndSliceDesc(Server server);

    List<Slice> findByServerOrderByStartSlice(Server server);
}

