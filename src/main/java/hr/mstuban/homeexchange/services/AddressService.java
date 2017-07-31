package hr.mstuban.homeexchange.services;

import hr.mstuban.homeexchange.domain.Address;

import java.util.List;

/**
 * Created by mstuban on 14.05.17..
 */
public interface AddressService {

    List<Address> findAll();

    List<Address> findAddressesBySearchParameter(String parameter);

    Address save(Address address);

    void deleteById(Long id);
}
