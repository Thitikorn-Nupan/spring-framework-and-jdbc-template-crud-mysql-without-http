package net.spring.coding.repository;

import java.util.HashMap;
import java.util.List;

public interface ModelRepository<T> {
    HashMap<?,?> create(T object);
    HashMap<?, List<T>> reads();
    HashMap<?,T > read(Long id);
    HashMap<?,T > read2(Long id);
    HashMap<?,T> update(T object , Long id);
    HashMap<?,T > delete(Long id);
}
