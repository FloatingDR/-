package com.cafuc.addressbook.service.impl;

import com.cafuc.addressbook.mapper.AddressBookMapper;
import com.cafuc.addressbook.model.AddressBook;
import com.cafuc.addressbook.service.AddressBookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AddressBookServiceImpl implements AddressBookService{
    @Resource
    AddressBookMapper adbkMapper;
    @Override
    public List<AddressBook>getAll(){
        return adbkMapper.searchAll();
    }
    @Override
    public List<AddressBook>getById(Integer id){
        return adbkMapper.getById(id);
    }
    @Override
    public int deleteById(Integer id){
        return adbkMapper.deleteById(id);
    }
    @Override
    public int insert(String name,String tel){
        return adbkMapper.insert(name,tel);
    }
    @Override
    public int sort(){return 200;}
}
