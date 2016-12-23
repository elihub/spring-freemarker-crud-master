/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gruchala.crud.model;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author elida
 */
@Transactional
public interface Products {

    List<Product> getAll();

    void create(Product product);

    void update(Product product);

    void delete(String hash);

}
