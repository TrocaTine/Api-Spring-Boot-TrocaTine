package com.example.apitrocatinesql.controllers;


import com.example.apitrocatinesql.models.DTO.requestDTO.FindPushByUserRequestDTO;
import com.example.apitrocatinesql.models.DTO.requestDTO.SavePushRequestDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.FindPushByUserResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.SavePushResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.StandardResponseDTO;
import com.example.apitrocatinesql.services.PushService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/push")
@AllArgsConstructor
public class PushController {

    private PushService pushService;

    @GetMapping("/find-push-user")
    public StandardResponseDTO findPushByUser(@Valid @RequestBody FindPushByUserRequestDTO request){
        List<FindPushByUserResponseDTO> listPush = pushService.findPushByUser(request.email());
        return new StandardResponseDTO(false, listPush);
    }

    @PostMapping("/save-push")
    public StandardResponseDTO savePush(@Valid @RequestBody SavePushRequestDTO request){
        SavePushResponseDTO save = pushService.savePush(request);
        return new StandardResponseDTO(false, save);
    }

}
