package cn.goduck.kl.design.pattern.behavior.mediator;

import cn.goduck.kl.design.pattern.behavior.mediator.component.Component;

import javax.swing.*;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/7 18:03
 */
public interface Mediator {

    void addNewNote(Note note);
    void deleteNote();
    void getInfoFromList(Note note);
    void saveChanges();
    void markNote();
    void clear();
    void sendToFilter(ListModel listModel);
    void setElementsList(ListModel list);
    void registerComponent(Component component);
    void hideElements(boolean flag);
    void createGUI();

}
