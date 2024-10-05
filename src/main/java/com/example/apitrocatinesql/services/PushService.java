package com.example.apitrocatinesql.services;

import com.example.apitrocatinesql.exception.NotFound;
import com.example.apitrocatinesql.exception.NotFoundUser;
import com.example.apitrocatinesql.models.DTO.requestDTO.FindPushByUserRequestDTO;
import com.example.apitrocatinesql.models.DTO.requestDTO.SavePushRequestDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.FindPushByUserResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.SavePushResponseDTO;
import com.example.apitrocatinesql.models.Push;
import com.example.apitrocatinesql.models.User;
import com.example.apitrocatinesql.repositories.PushRepository;
import com.example.apitrocatinesql.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PushService {

    private PushRepository pushRepository;
    private UserRepository userRepository;


    public List<FindPushByUserResponseDTO> findPushByUser(String email){
        User user = userRepository.findUserByEmail(email);
        if(user == null){
            throw new NotFoundUser("Not found user");
        }
        List<Push> listPush = pushRepository.findPushByUsersAndCreatedAtGreaterThanEqual(user, LocalDate.now().minusDays(60));
        if(listPush.size() == 0){
            throw new NotFound("Not found push");
        }
        List<FindPushByUserResponseDTO> listPushResponse = listPush.stream().map(
                push -> new FindPushByUserResponseDTO(push.getTitle(), push.getDescription(), push.getCreatedAt())).collect(Collectors.toList());
        return listPushResponse;
    }

    public SavePushResponseDTO savePush(SavePushRequestDTO savePushRequestDTO){
        Set<User> users = savePushRequestDTO.email().stream().map(email -> {
            User user = userRepository.findUserByEmail(email);
            if(user == null ){
                throw new NotFoundUser("Not found user, email: " + email);
            }
            return user;
        }).collect(Collectors.toSet());
        Push push = new Push();
        push.setTitle(savePushRequestDTO.title());
        push.setDescription(savePushRequestDTO.description());
        push.setCreatedAt(LocalDate.now());
        push.setUsers(users);
        users.forEach(user -> user.getPushes().add(push));
        Push savePush = pushRepository.save(push);
        if(savePush != null){
            return new SavePushResponseDTO(true);
        }
        return new SavePushResponseDTO(false);
    }


}
