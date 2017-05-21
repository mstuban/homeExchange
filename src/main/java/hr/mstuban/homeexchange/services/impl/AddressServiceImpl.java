package hr.mstuban.homeexchange.services.impl;

import hr.mstuban.homeexchange.domain.Address;
import hr.mstuban.homeexchange.repositories.AddressRepository;
import hr.mstuban.homeexchange.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mstuban on 14.05.17..
 */
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> findAll() {
        return (List<Address>) addressRepository.findAll();
    }

    @Override
    public List<Address> findAvailableAddressesBySearchParameter(String parameter) {
        return addressRepository.findAddressesBySearchParameter(parameter);
    }
}

