package hr.mstuban.homeexchange.services.impl;

import hr.mstuban.homeexchange.domain.Address;
import hr.mstuban.homeexchange.repositories.AddressRepository;
import hr.mstuban.homeexchange.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return addressRepository.findAll();
    }

    @Override
    public List<Address> findAddressesBySearchParameter(String parameter) {
        return addressRepository.findAddressesBySearchParameter(parameter);
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void deleteById(Long id) {
        addressRepository.delete(id);
    }

    @Override
    public List<Address> getAddressesByHome_User_UserName(String username) {
        return addressRepository.getAddressesByHome_User_UserName(username);
    }

    @Transactional
    @Override
    public void deleteByHome_HomeId(Long id) {
        addressRepository.deleteByHome_HomeId(id);
    }

    @Transactional
    @Override
    public void editAddress(String city, String country, Long postalCode, String street, Long homeId) {
        addressRepository.editAddress(city, country, postalCode, street, homeId);
    }

    public Address findByHome_HomeId(Long id) {
        return addressRepository.findByHome_HomeId(id);
    }

    @Override
    public List<String> getByCountryIsUnique() {
        return addressRepository.getByCountryIsUnique();
    }

}

