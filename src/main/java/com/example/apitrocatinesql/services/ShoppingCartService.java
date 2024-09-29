package com.example.apitrocatinesql.services;

import com.example.apitrocatinesql.exception.NotFound;
import com.example.apitrocatinesql.exception.SelfProductAddition;
import com.example.apitrocatinesql.models.DTO.requestDTO.AddProductShoppingCartResquestDTO;
import com.example.apitrocatinesql.models.DTO.requestDTO.FindProductShoppingCartResquestDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.AddProductShoppingCartResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.FindProductByUserResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.FindProductShoppingCartResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.FindPushByUserResponseDTO;
import com.example.apitrocatinesql.models.Product;
import com.example.apitrocatinesql.models.ShoppingCart;
import com.example.apitrocatinesql.models.User;
import com.example.apitrocatinesql.repositories.ProductRepository;
import com.example.apitrocatinesql.repositories.ShoppingCartRepository;
import com.example.apitrocatinesql.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.ClientInfoStatus;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShoppingCartService {

    private ShoppingCartRepository shoppingCartRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;

    public AddProductShoppingCartResponseDTO addProductShoppingCart(AddProductShoppingCartResquestDTO request){
        Product product = productRepository.findProductByIdProduct(request.idProduct());
        if(product == null){
            throw new NotFound("Not found product");
        }
        User user =  userRepository.findUserByEmail(request.email());
        if (user == null){
            throw new NotFound("Not found user");
        }
        if(product.getUser() == user){
            throw new SelfProductAddition("you can't self product addition");
        }

        BigDecimal totalValue = product.getValue().multiply(BigDecimal.valueOf(request.quantity()));

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setProduct(product);
        shoppingCart.setUser(user);
        shoppingCart.setQuantity(request.quantity());
        shoppingCart.setValue(totalValue);

        ShoppingCart shoppingCartAdd = shoppingCartRepository.save(shoppingCart);
        if (shoppingCartAdd == null){
            return new AddProductShoppingCartResponseDTO(false);
        }
        return new AddProductShoppingCartResponseDTO(true);
    }

    public List<FindProductShoppingCartResponseDTO> findProductShoppingCart (FindProductShoppingCartResquestDTO resquest){
        User user = userRepository.findUserByEmail(resquest.email());
        if (user == null){
            throw new NotFound("Not found user");
        }
        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findShoppingCartByUser(user);
        if (shoppingCarts.size() == 0){
            throw new NotFound("Not found product in shopping cart");
        }
        List<FindProductShoppingCartResponseDTO> findProduct = shoppingCarts.stream().map(product ->
                new FindProductShoppingCartResponseDTO(product.getProduct().getName(), product.getValue(),
                        product.getQuantity())).collect(Collectors.toList());
        return findProduct;
    }

}
