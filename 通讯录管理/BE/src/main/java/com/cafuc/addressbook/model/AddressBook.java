package com.cafuc.addressbook.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressBook {
    private Integer id;
    private String name;
    private String tel;
}
