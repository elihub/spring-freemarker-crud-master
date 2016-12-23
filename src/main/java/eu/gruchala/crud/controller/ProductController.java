/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gruchala.crud.controller;

import eu.gruchala.crud.model.Product;
import eu.gruchala.crud.model.Products;
import eu.gruchala.crud.model.ProductsDao;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author elida
 */
@Controller
@SessionAttributes({"editableProduct"})
public class ProductController {

    private final static Logger L = LoggerFactory.getLogger(MainController.class);
    private static final String DATE_FORMAT = "dd-MM-yyyy";
    private Products productsDao;

    @Inject
    public ProductController(final ProductsDao productsDao) {
        this.productsDao = productsDao;
    }

    @ModelAttribute("products")
    public List<Product> getProducts() {
        return productsDao.getAll();
    }

    @ModelAttribute("product")
    public Product getProduct() {
        return new Product();
    }

    @ModelAttribute("editableProduct")
    public Product getEditableProducts() {
        return new Product();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
    public ModelAndView savePerson(@Validated @ModelAttribute("product") final Product product, final BindingResult result) {
        if (result.hasErrors()) {
            L.warn("Found errors.\n" + result.getAllErrors());
            return new ModelAndView("index");
        }
        productsDao.create(product);
        L.info("Added product: " + product);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/editProduct", method = RequestMethod.POST)
    public ModelAndView editPerson(@Validated @ModelAttribute("editableProduct") final Product product, final BindingResult result) {
        if (result.hasErrors()) {
            L.warn("Found errors.\n" + result.getAllErrors());
            final ModelAndView view = new ModelAndView("index");
            view.addObject("editWithErrors", "true");
            return view;
        }
        productsDao.update(product);
        L.info("Edited product: " + product);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/removePerson/{hash}", method = RequestMethod.POST)
    public ModelAndView removePerson(@PathVariable("hash") final String hash) {
        productsDao.delete(hash);
        L.debug("Attempting to remove product with hash: {}", hash);
        return new ModelAndView("redirect:/");
    }

    /*@InitBinder
     public void initBinder(final WebDataBinder binder) {
     final CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat(DATE_FORMAT), true);
     binder.registerCustomEditor(Date.class, editor);
     }*/
}
