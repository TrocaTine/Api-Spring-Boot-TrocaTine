package com.example.apitrocatinesql.services;

import com.example.apitrocatinesql.exception.NotFound;
import com.example.apitrocatinesql.models.DTO.requestDTO.FindCardUserRequestDTO;
import com.example.apitrocatinesql.models.DTO.requestDTO.SaveInformactionCardRequestDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.FindCardUserResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.SaveFavoriteProductResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.SaveInformactionCardResponseDTO;
import com.example.apitrocatinesql.models.SavedCard;
import com.example.apitrocatinesql.models.User;
import com.example.apitrocatinesql.repositories.CardRepository;
import com.example.apitrocatinesql.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class CardService {

    CardRepository cardRepository;
    UserRepository userRepository;

    public SaveInformactionCardResponseDTO saveInformactionCard (SaveInformactionCardRequestDTO request){
        User user = userRepository.findUserByEmail(request.email());
        if(user == null){
            throw new NotFound("Not found user");
        }

        SavedCard card = new SavedCard();
        card.setCardNumber(request.cardNumber());
        card.setCvv(request.cvv());
        card.setExpirationDate(request.expirationDate());
        card.setUser(user);

        cardRepository.save(card);
        return new SaveInformactionCardResponseDTO(true);
    }

    public List<FindCardUserResponseDTO> findCardByUser(FindCardUserRequestDTO request){
        User user = userRepository.findUserByEmail(request.email());

        if(user == null){
            throw new NotFound("Not found user");
        }

        List<FindCardUserResponseDTO> findCardUser = cardRepository.findSavedCardByUser(user).stream().map(savedCard -> new FindCardUserResponseDTO(savedCard.getCardNumber(), savedCard.getExpirationDate(), savedCard.getCvv())).collect(Collectors.toList());

        return findCardUser;
    }



}
