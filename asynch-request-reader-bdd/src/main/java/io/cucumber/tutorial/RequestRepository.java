package io.cucumber.tutorial;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by vic on 2018/01/14.
 */
public interface RequestRepository extends CrudRepository<Request, Long> {
}
