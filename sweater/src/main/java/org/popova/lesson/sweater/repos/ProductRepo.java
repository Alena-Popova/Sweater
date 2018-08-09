package org.popova.lesson.sweater.repos;

import org.popova.lesson.sweater.domain.Product;
import org.popova.lesson.sweater.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Long> {
    List<Product> findByTag(String tag);

    List<Product> findByAuthor(User user);
}
