package com.devsuperior.dscommerce.tests;

import com.devsuperior.dscommerce.entities.Category;
import com.devsuperior.dscommerce.entities.Product;

public class ProductFactory {
    public static Product createProduct() {
        Category category = CategoryFactory.createCategory(1L, "Videogames");
        Product product = new Product(1L, "Nintendo Switch OLED", "Jogue em casa na TV ou em qualquer lugar com uma tela OLED vibrante de 7 polegadas com o console Nintendo Switch – Modelo OLED. Além de uma nova tela com cores vivas e contraste nítido, o Nintendo Switch – Modelo OLED inclui um amplo acessório de suporte ajustável que permite ângulos de visualização mais confortáveis, uma base com porta para cabo LAN para o modo TV (cabo LAN vendido separadamente), 64 GB de armazenamento interno* e áudio aprimorado nos modos portátil e semiportátil usando os alto-falantes do console.", 2249.10, "https://assets.nintendo.com/image/upload/f_auto/q_auto/dpr_1.0/c_scale,w_600/ncom/en_US/products/hardware/nintendo-switch-oled-model-white-set/115461-switch-oled-white-console-front-1200x675");
        product.getCategories().add(category);
        return product;
    }

    public static Product createProduct(String name) {
        Product product = createProduct();
        product.setName(name);
        return product;
    }
}
