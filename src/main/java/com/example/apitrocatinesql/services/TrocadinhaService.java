package com.example.apitrocatinesql.services;

import com.example.apitrocatinesql.exception.NotFoundUser;
import com.example.apitrocatinesql.models.DTO.responseDTO.AddingTrodinhaResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.FindRankingTrocadinhaResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.FindTrocadinhaCountResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.RetiredTrocadinhaResponseDTO;
import com.example.apitrocatinesql.models.Trocadinha;
import com.example.apitrocatinesql.models.User;
import com.example.apitrocatinesql.repositories.TrocadinhaRepository;
import com.example.apitrocatinesql.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TrocadinhaService {

    private TrocadinhaRepository trocadinhaRepository;
    private UserRepository userRepositor;

    public FindTrocadinhaCountResponseDTO findTrocadinhaCount(String email){
        int countTrocadinha = 0;
        User user = userRepositor.findUserByEmail(email);
        if (user == null){
            throw new NotFoundUser("Not found user");
        }
        Trocadinha trocadinha = trocadinhaRepository.findByUserAndExpirationDateGreaterThanEqual(user, LocalDate.now());
        if(trocadinha != null){
            countTrocadinha = trocadinha.getNumberTrocadinha();
        }
        return new FindTrocadinhaCountResponseDTO(email, countTrocadinha);
    }

    public List<FindRankingTrocadinhaResponseDTO> findRankingTrocadinha(){
        List<Trocadinha> ranking = trocadinhaRepository
                .findTrocadinhaByExpirationDateGreaterThanEqual(LocalDate.now());
        ranking.sort(Comparator.comparingInt(Trocadinha::getNumberTrocadinha).reversed());
        List<FindRankingTrocadinhaResponseDTO> rankingList = ranking.stream().map(i ->
                new FindRankingTrocadinhaResponseDTO(i.getUser().getEmail(), i.getUser().getNickname(), i.getNumberTrocadinha())).collect(Collectors.toList());
        return rankingList;
    }

    public AddingTrodinhaResponseDTO addingTrodinha(String email, int amount){
        int countTrocadinha = amount;
        User user = userRepositor.findUserByEmail(email);
        if (user == null){
            throw new NotFoundUser("Not found user");
        }
        Trocadinha trocadinha = trocadinhaRepository.findByUserAndExpirationDateGreaterThanEqual(user, LocalDate.now());
        if(trocadinha != null){
            countTrocadinha += trocadinha.getNumberTrocadinha();
            trocadinha.setNumberTrocadinha(countTrocadinha);
            trocadinha.setLastAtualization(LocalDate.now());

        }else{
            trocadinha = new Trocadinha().builder().user(user).lastAtualization(LocalDate.now())
                    .numberTrocadinha(countTrocadinha).expirationDate(LocalDate.now().plusDays(20)).build();
        }

        trocadinhaRepository.save(trocadinha);
        return new AddingTrodinhaResponseDTO(countTrocadinha);
    }

    public RetiredTrocadinhaResponseDTO retiredTrocadinha(String email, int amount){
        int countTrocadinha = 0;
        User user = userRepositor.findUserByEmail(email);
        if (user == null){
            throw new NotFoundUser("Not found user");
        }
        Trocadinha trocadinha = trocadinhaRepository.findByUserAndExpirationDateGreaterThanEqual(user, LocalDate.now());
        if(trocadinha != null){
            countTrocadinha = trocadinha.getNumberTrocadinha() - amount;
            if(countTrocadinha < 0){
                countTrocadinha = 0;
            }
        }
        trocadinha.setNumberTrocadinha(countTrocadinha);
        trocadinhaRepository.save(trocadinha);
        return new RetiredTrocadinhaResponseDTO(countTrocadinha);
    }

}
