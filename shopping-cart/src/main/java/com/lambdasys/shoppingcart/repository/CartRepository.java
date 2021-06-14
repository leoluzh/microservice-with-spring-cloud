package com.lambdasys.shoppingcart.repository;

import com.lambdasys.shoppingcart.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart,String> {

}
