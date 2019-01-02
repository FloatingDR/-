package com.cafuc.addressbook.service;

import com.cafuc.addressbook.model.AddressBook;

import java.util.List;

public interface AddressBookService {

    List<AddressBook> getAll();
    List<AddressBook> getById(Integer id);
    int deleteById(Integer id);
    int insert(String name,String tel);
    int sort();
}
