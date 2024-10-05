package com.example.apitrocatinesql.services;

import com.example.apitrocatinesql.exception.NotFound;
import com.example.apitrocatinesql.exception.ProductAlreadyFavoritedException;
import com.example.apitrocatinesql.models.DTO.HighlightDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.FindProductFavoriteResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.SaveFavoriteProductResponseDTO;
import com.example.apitrocatinesql.models.Favorite;
import com.example.apitrocatinesql.models.FavoriteId;
import com.example.apitrocatinesql.models.Product;
import com.example.apitrocatinesql.models.User;
import com.example.apitrocatinesql.repositories.FavoriteRepository;
import com.example.apitrocatinesql.repositories.ProductRepository;
import com.example.apitrocatinesql.repositories.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FavoriteService {

    private FavoriteRepository favoriteRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;


    public List<FindProductFavoriteResponseDTO> findProductFavorite(String email){
        boolean highlightB = true;
        User user = userRepository.findUserByEmail(email);
        if(user == null){
            throw new NotFound("Not found user");
        }
        List<Favorite> favorite = favoriteRepository.findFavoriteByUser(user);
        if(favorite.size() == 0){
            throw new NotFound("Not found favorite product");
        }
        List<FindProductFavoriteResponseDTO> favoriteFinally = favorite.stream().map(product -> {return new FindProductFavoriteResponseDTO(product.getProduct().getIdProduct(),
                    product.getProduct().getValue(), product.getProduct().getCreatedAt(), product.getProduct().getName(), product.getProduct().getDescription(),
                    product.getProduct().getTags().stream().map(tag -> tag.getName()).collect(Collectors.toList()), product.getProduct().getHighlights().stream().map(highlight -> new HighlightDTO(highlight.getExpirantionAt(),highlightB )).collect(Collectors.toList()),
                product.getProduct().getFlagTrade());}).collect(Collectors.toList());

        return favoriteFinally;
    }

    public SaveFavoriteProductResponseDTO savefavoriteProduct(String email, Long idProduct){
        User user = userRepository.findUserByEmail(email);
        if(user == null){
            throw new NotFound("Not found user");
        }
        Product product = productRepository.findProductByIdProduct(idProduct);
        if(product == null){
            throw new NotFound("Not found this product");
        }
        List<Favorite> favoriteUserList = favoriteRepository.findFavoriteByUser(user);
        for (int i = 0; i < favoriteUserList.size(); i++) {
            if(favoriteUserList.get(i).getProduct().equals(product)){
                throw new ProductAlreadyFavoritedException("This product has already been favorited");
            }
        }
        FavoriteId favoriteId = new FavoriteId(user.getIdUser(), product.getIdProduct());
        Favorite favoriteSave = favoriteRepository.save(new Favorite(favoriteId, user, product));
        if(favoriteSave == null){
            return new SaveFavoriteProductResponseDTO(false);
        }
        return new SaveFavoriteProductResponseDTO(true);

    }



}
