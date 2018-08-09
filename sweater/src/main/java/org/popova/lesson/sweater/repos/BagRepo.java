package org.popova.lesson.sweater.repos;

import org.popova.lesson.sweater.domain.Bag;
import org.popova.lesson.sweater.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BagRepo  extends CrudRepository<Bag, Long> {
    List<Bag> findByAuthor(User user);
}
