package com.tuannt.testswitch.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Contact list data set
 *
 * @author TuanNT
 */
@Data
public class ContactListDataSet {
    private List<Contact> contacts = new ArrayList<>();
    private boolean isMultiSelected;

    public String getName(int position) {
        return contacts.get(position).getFirstName();
    }

    public String getPhoneNumber(int position) {
        return contacts.get(position).getPhoneNumber();
    }

    public int getSize() {
        return contacts.size();
    }
}
