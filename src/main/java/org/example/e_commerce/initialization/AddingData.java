package org.example.e_commerce.initialization;

import lombok.AllArgsConstructor;
import org.example.e_commerce.model.*;
import org.example.e_commerce.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.example.e_commerce.util.HashPasswordUser.generateSalt;
import static org.example.e_commerce.util.HashPasswordUser.hashPassword;

@Component("addingData")
@Profile("dev")
@AllArgsConstructor
public class AddingData implements CommandLineRunner {

    private CartItemRepository cartItemRepository;
    private CartRepository cartRepository;
    private OrderItemRepository orderItemRepository;
    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        Instant instantDebut = Instant.now();

        System.out.println("============= Ajout des donnee start ===============");

        createUser();
        createCategory();
        createProduct();
        createProducts();


        Instant instantFin = Instant.now();

        System.out.println("Ajouts effectués en " + (instantFin.toEpochMilli() - instantDebut.toEpochMilli()) + " ms");


        System.out.println("====================================================");

    }

    private void createCategory() {
        // Catégorie parente
        Category parentCategory = new Category();
        parentCategory.setName("Paysage");

        // Sous-catégorie 1
        Category mountainCategory = new Category();
        mountainCategory.setName("Montagnes");
        mountainCategory.setParent(parentCategory);

        // Sous-catégorie 2
        Category beachCategory = new Category();
        beachCategory.setName("Plages");
        beachCategory.setParent(parentCategory);

        // Sous-catégorie 3
        Category forestCategory = new Category();
        forestCategory.setName("Forêts");
        forestCategory.setParent(parentCategory);

        // Ajouter les sous-catégories à la liste de la catégorie parente
        parentCategory.getSubCategories().add(mountainCategory);
        parentCategory.getSubCategories().add(beachCategory);
        parentCategory.getSubCategories().add(forestCategory);

        // Sauvegarder la catégorie parente dans la base de données
        categoryRepository.save(parentCategory);
    }


    private void createUser() {

        User user = new User();
        user.setFirstName("José");
        user.setLastName("Vale Costa");
        user.setRole("ROLE_USER");
        user.setEmail("test@test.fr");

        try {
            String password = "test";
            // Générer un salt
            byte[] salt = generateSalt();
            // Hacher le mot de passe avec le salt
            String hashedPassword = hashPassword(password, salt);
            user.setPassword(hashedPassword);
            user.setSalt(salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        userRepository.save(user);

    }

    private void createProduct() {

        Product product = new Product();
        Category category = categoryRepository.findByName("Plages");

        product.setName("Vue sur mer");
        product.setStock(25);
        product.setPrice(29.99);
        product.setImageUrl("https://www.vuesurmer.fr/images/plage-saleccia.jpg");
        product.setDescription("Une vue côtière à couper le souffle dévoile une mer d'un bleu turquoise éclatant qui s'étend à perte de vue jusqu'à l'horizon.");


        product.setCategory(category);

        productRepository.save(product);


        Product product2 = new Product();

        product2.setName("Vue sur mer   ");
        product2.setStock(0);
        product2.setPrice(49.99);
        product2.setImageUrl("https://www.photo-paysage.com/albums/userpics/10001/thumb_Un_bouleau_au_bord_d_un_chemin_dans_les_Alpes.jpg");
        product2.setDescription("Une vue côtière à couper le souffle dévoile une mer d'un bleu turquoise éclatant qui s'étend à perte de vue jusqu'à l'horizon.");

        product2.setCategory(category);

        productRepository.save(product2);


        Product product3 = new Product();

        product3.setName("Vue sur mer    ");
        product3.setStock(55);
        product3.setPrice(49.99);
        product3.setImageUrl("https://static.vecteezy.com/ti/vecteur-libre/t1/3623626-coucher-de-soleil-lac-paysage-illustration-gratuit-vectoriel.jpg");
        product3.setDescription("Une vue côtière à couper le souffle dévoile une mer d'un bleu turquoise éclatant qui s'étend à perte de vue jusqu'à l'horizon.");
        product3.setCategory(category);

        productRepository.save(product3);

    }

    private void createProducts() {
        // Récupérer les catégories
        Category mountainCategory = categoryRepository.findByName("Montagnes");
        Category beachCategory = categoryRepository.findByName("Plages");
        Category forestCategory = categoryRepository.findByName("Forêts");

        // Produit 1 : Montagnes
        Product mountainProduct = new Product();
        mountainProduct.setName("Mont Everest");
        mountainProduct.setDescription("Une vue époustouflante du Mont Everest.");
        mountainProduct.setPrice(200.0);
        mountainProduct.setStock(10);
        mountainProduct.setImageUrl("https://cdn.futura-sciences.com/sources/images/everest-himalaya-montagne.jpeg");
        mountainProduct.setCategory(mountainCategory);

        // Produit 2 : Plages
        Product beachProduct = new Product();
        beachProduct.setName("Plage des Maldives");
        beachProduct.setDescription("Une plage paradisiaque aux eaux cristallines.");
        beachProduct.setPrice(150.0);
        beachProduct.setStock(20);
        beachProduct.setImageUrl("https://www.voyageursdumonde.fr/voyage-sur-mesure/img/mag/201602/H%C3%B4tel-Lux-South-Ari-Atoll.jpg");
        beachProduct.setCategory(beachCategory);

        // Produit 3 : Forêts
        Product forestProduct = new Product();
        forestProduct.setName("Forêt Amazonienne");
        forestProduct.setDescription("Un aperçu de la biodiversité incroyable de l'Amazonie.");
        forestProduct.setPrice(120.0);
        forestProduct.setStock(15);
        forestProduct.setImageUrl("https://www.voyage-amazonie.com/sites/default/files/img_page_amazonie/faune/foret-amazonienne.jpg");
        forestProduct.setCategory(forestCategory);

        // Sauvegarder les produits dans la base de données
        productRepository.save(mountainProduct);
        productRepository.save(beachProduct);
        productRepository.save(forestProduct);
    }


}
