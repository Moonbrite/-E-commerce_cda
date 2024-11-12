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

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        Instant instantDebut = Instant.now();

        System.out.println("============= Ajout des donnee start ===============");

        createUser();
        createProduct();
        createCart();
        createCartItem();
        createOrder();
        createOrderItem();
        createPayment();

        Instant instantFin = Instant.now();

        System.out.println("Ajouts effectués en " + (instantFin.toEpochMilli() - instantDebut.toEpochMilli()) + " ms");


        System.out.println("====================================================");

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

        product.setName("Vue sur mer");
        product.setStock(25);
        product.setPrice(29.99);
        product.setImageUrl("https://www.vuesurmer.fr/images/plage-saleccia.jpg");
        product.setDescription("Une vue côtière à couper le souffle dévoile une mer d'un bleu turquoise éclatant qui s'étend à perte de vue jusqu'à l'horizon.");

        productRepository.save(product);


        Product product2 = new Product();

        product2.setName("Vue sur mer   ");
        product2.setStock(0);
        product2.setPrice(49.99);
        product2.setImageUrl("https://www.photo-paysage.com/albums/userpics/10001/thumb_Un_bouleau_au_bord_d_un_chemin_dans_les_Alpes.jpg");
        product2.setDescription("Une vue côtière à couper le souffle dévoile une mer d'un bleu turquoise éclatant qui s'étend à perte de vue jusqu'à l'horizon.");

        productRepository.save(product2);


        Product product3 = new Product();

        product3.setName("Vue sur mer    ");
        product3.setStock(55);
        product3.setPrice(49.99);
        product3.setImageUrl("https://static.vecteezy.com/ti/vecteur-libre/t1/3623626-coucher-de-soleil-lac-paysage-illustration-gratuit-vectoriel.jpg");
        product3.setDescription("Une vue côtière à couper le souffle dévoile une mer d'un bleu turquoise éclatant qui s'étend à perte de vue jusqu'à l'horizon.");

        productRepository.save(product3);

    }

    private void createCart() {

        Cart cart = new Cart();

        User user = userRepository.findByEmail("test@test.fr");

        cart.setUser(user);
        user.setCart(cart);

        userRepository.save(user);

    }

    private void createCartItem() {

        int quantity = 1;

        Cart cart = cartRepository.findById(1L).orElse(null);
        Product product = productRepository.findById(1L).orElse(null);

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        assert product != null;
        cartItem.setPrice(product.getPrice() * quantity);

        cartItemRepository.save(cartItem);

    }

    private void createOrder(){

        User user = userRepository.findByEmail("test@test.fr");


        Order order = new Order();

        order.setOrderDate(LocalDateTime.now());
        order.setUser(user);
        order.setPaymentStatus("Paye");
        order.setTotalPrice(236.0);

        orderRepository.save(order);


    }

    private void  createOrderItem() {

        int quantity = 1;

        List<OrderItem> itemList = new ArrayList<>();
        Product product = productRepository.findById(1L).orElse(null);
        Order order = orderRepository.findById(1L).orElse(null);

        OrderItem orderItem = new OrderItem();

        orderItem.setProduct(product);
        orderItem.setOrder(order);

        assert product != null;
        orderItem.setPrice(product.getPrice() * quantity);
        orderItem.setQuantity(quantity);

        itemList.add(orderItem);

        orderItemRepository.save(orderItem);


        OrderItem orderItem2 = new OrderItem();

        orderItem2.setProduct(product);
        orderItem2.setOrder(order);

        assert product != null;
        orderItem2.setPrice(product.getPrice() * quantity);
        orderItem2.setQuantity(quantity);

        itemList.add(orderItem2);

        orderItemRepository.save(orderItem2);



        assert order != null;
        order.setItems(itemList);
        orderRepository.save(order);


    }

    private void createPayment() {

        Payment payment = new Payment();
        Order order = orderRepository.findById(1L).orElse(null);

        payment.setPaymentIntentId("idStripeHere");
        payment.setOrder(order);

        List<OrderItem> orderItemList = orderItemRepository.findByOrderId(1L);

        Double amountTotal = orderItemList.stream()
                .mapToDouble(OrderItem::getPrice)
                .sum();

        payment.setAmount(amountTotal);
        payment.setStatus("paye");
        payment.setCurrency("euro");

        paymentRepository.save(payment);
    }

}
