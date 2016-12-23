/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gruchala.crud.utils;

import com.google.common.base.Charsets;
import com.google.common.hash.Funnel;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.google.common.hash.PrimitiveSink;
import eu.gruchala.crud.model.Product;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author elida
 */
public class Sha256Hasher1 {

    enum ProductFunnel implements Funnel<Product> {

        INSTANCE;

        @Override
        public void funnel(final Product Product, final PrimitiveSink into) {
            into.putString(Product.getCode(), Charsets.UTF_8).putString(Product.getDescription(), Charsets.UTF_8)
                    .putString(DateTime.now().toString(), Charsets.UTF_8);
        }
    }

    @Autowired
    public String get(Product from) {
        final HashFunction hashFunction = Hashing.sha256();
        final Hasher hasher = hashFunction.newHasher();
        ProductFunnel.INSTANCE.funnel(from, hasher);
        return hasher.hash().toString();
    }

}
