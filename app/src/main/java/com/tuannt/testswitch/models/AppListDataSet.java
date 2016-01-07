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
public class AppListDataSet {
    private List<App> apps = new ArrayList<>();
    private boolean isMultiSelected;
    private int numOfSelected = 0;

    public String getName(int position) {
        return apps.get(position).getName();
    }

    public int getSize() {
        return apps.size();
    }

    public void enableMultiSelected(boolean enable) {
        isMultiSelected = enable;
    }

    public void toggleSelectItem(int position) {
        App app = apps.get(position);
        app.setSelected(!app.isSelected());
        if (app.isSelected()) {
            numOfSelected++;
        } else {
            numOfSelected--;
        }
    }

    public boolean isSelectedItem(int position) {
        return apps.get(position).isSelected();
    }

    public void addTagIntoSelectedContact(int position, Tag tag) {
        apps.get(position).addTag(tag);
    }

    public void addTagIntoSelectedApps(Tag tag) {
        for (App app : apps) {
            if (app.isSelected()) {
                app.addTag(tag);
            }
        }
    }

    public void clearSelectedApps() {
        numOfSelected = 0;
        for (App app : apps) {
            app.setSelected(false);
        }
    }

    public void clearSelectedContacts() {
        numOfSelected = 0;
        for (App app : apps) {
            app.setSelected(false);
        }
    }

}
