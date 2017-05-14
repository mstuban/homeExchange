package hr.mstuban.homeexchange.services;

import hr.mstuban.homeexchange.domain.Address;
import hr.mstuban.homeexchange.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mstuban on 14.05.17..
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public List<Address> findAll() {
        return (List<Address>) addressRepository.findAll();
    }

    @Override
    public List<Address> findByParameter(String parameter) {
        return addressRepository.findByStreetOrCityOrCountry(parameter);
    }
}

