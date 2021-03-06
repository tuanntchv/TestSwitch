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
    private int numOfSelected = 0;

    public String getName(int position) {
        return contacts.get(position).getFirstName();
    }

    public String getPhoneNumber(int position) {
        return contacts.get(position).getPhoneNumber();
    }

    public int getSize() {
        return contacts.size();
    }

    public void enableMultiSelected(boolean enable) {
        isMultiSelected = enable;
    }

    public void toggleSelectItem(int position) {
        Contact contact = contacts.get(position);
        contact.setSelected(!contact.isSelected());
        if (contact.isSelected()) {
            numOfSelected++;
        } else {
            numOfSelected--;
        }
    }

    public boolean isSelectedItem(int position) {
        return contacts.get(position).isSelected();
    }

    public void addTagIntoSelectedContact(int position, Tag tag) {
        contacts.get(position).addTag(tag);
    }

    public void addTagIntoSelectedContacts(Tag tag) {
        for (Contact contact : contacts) {
            if (contact.isSelected()) {
                contact.addTag(tag);
            }
        }
    }

    public void clearSelectedContacts() {
        numOfSelected = 0;
        for (Contact contact : contacts) {
            contact.setSelected(false);
        }
    }
}
