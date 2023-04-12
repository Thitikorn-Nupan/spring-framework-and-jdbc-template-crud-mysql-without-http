package net.spring.coding.repository;

import java.util.HashMap;
import java.util.List;

public interface CustomerRepository<T> {
    public HashMap<?,?> create(T object);
    public HashMap<?, List<T>> reads();
    public HashMap<?,T > read(Long id);
    public HashMap<?,T > read2(Long id);
    public HashMap<?,T> update(T object , Long id);
    public HashMap<?,T > delete(Long id);
}
