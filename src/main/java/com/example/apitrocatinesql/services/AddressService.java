package com.example.apitrocatinesql.services;

import com.example.apitrocatinesql.exception.NotFound;
import com.example.apitrocatinesql.models.Address;
import com.example.apitrocatinesql.models.DTO.requestDTO.FindUserAddressRequestDTO;
import com.example.apitrocatinesql.models.DTO.requestDTO.SaveAddressRequestDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.FindUserAddressResponseDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.SaveAddressResponseDTO;
import com.example.apitrocatinesql.models.User;
import com.example.apitrocatinesql.repositories.AddressRepository;
import com.example.apitrocatinesql.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddressService {

    private AddressRepository addressRepository;
    private UserRepository userRepository;

    public List<FindUserAddressResponseDTO> findUserAddress(FindUserAddressRequestDTO request){
        User user = userRepository.findUserByEmail(request.email());
        if (user == null){
            throw new NotFound("Not found user");
        }
        List<Address> listAddress = addressRepository.findAddressByUsers(user);
        List<FindUserAddressResponseDTO> responseDTO = listAddress.stream().map(address -> new FindUserAddressResponseDTO(
                address.getStreet(), address.getNumber(), address.getCity(), address.getState(), address.getComplement(), address.getCep())).collect(Collectors.toList());
        return responseDTO;
    }

    public SaveAddressResponseDTO saveAddress(SaveAddressRequestDTO request){
        User user = userRepository.findUserByEmail(request.email());
        if (user == null){
            throw new NotFound("Not found user");
        }
        Address address = new Address();
        address.setUsers(Set.of(user));
        address.setCep(request.address().cep());
        address.setCity(request.address().city());
        address.setComplement(request.address().complement());
        address.setState(request.address().state());
        address.setStreet(request.address().street());
        address.setNumber(request.address().number());

        Address addressSave = addressRepository.save(address);
        if (addressSave == null){
            return new SaveAddressResponseDTO(false);
        }
        return new SaveAddressResponseDTO(true);

    }



}
