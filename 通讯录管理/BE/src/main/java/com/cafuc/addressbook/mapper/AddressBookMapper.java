package com.cafuc.addressbook.mapper;

import com.cafuc.addressbook.model.AddressBook;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;
@Mapper
public interface AddressBookMapper {
    List<AddressBook> searchAll();
    List<AddressBook> getById(Integer id);
    int deleteById(Integer id);
    int insert(String name,String tel);
    int sort();

}
