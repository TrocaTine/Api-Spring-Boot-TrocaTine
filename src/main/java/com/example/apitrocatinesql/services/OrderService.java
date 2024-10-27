package com.example.apitrocatinesql.services;

import com.example.apitrocatinesql.exception.NotFound;
import com.example.apitrocatinesql.exception.SelfProduct;
import com.example.apitrocatinesql.models.*;
import com.example.apitrocatinesql.models.DTO.requestDTO.FinishedOrderRequestDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.FinishedOrderResponseDTO;
import com.example.apitrocatinesql.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class OrderService {

    private HistoryShoppingCartRepository historyShoppingCartRepository;
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private CardRepository cardRepository;
    private ProductRepository productRepository;



    public FinishedOrderResponseDTO finallyOrder(FinishedOrderRequestDTO request){
        User user = userRepository.findUserByEmail(request.email());
        if (user == null) {
            throw new NotFound("User not found");
        }

        Product product = productRepository.findProductByIdProduct(request.idProduct());
        if (product == null) {
            throw new NotFound("Product not found");
        }

        SavedCard card = cardRepository.findSavedCardByCardNumberAndUser(request.numberCard(), user);
        if (card == null) {
            throw new NotFound("Product not card");
        }


        Order order = new Order();

        HistoryShoppingCart historyShoppingCart = historyShoppingCartRepository.findHistoryShoppingCartByProductAndUser(product, user);
        if(historyShoppingCart != null ){
            order.setShoppingCartOld(historyShoppingCart);
        }else{
            order.setProduct(product);
            if(product.getUser() == user){
                throw new SelfProduct("You can't buy your product");
            }
        }
        order.setAccountedAt(LocalDate.now());
        order.setSavedCard(card);
        order.setPaymentType(request.paymentType());
        order.setTotalValue(request.value());
        Order orderSave = orderRepository.save(order);
        if(orderSave != null){
            return new FinishedOrderResponseDTO(true);
        }
        return new FinishedOrderResponseDTO(false);
    }

}
