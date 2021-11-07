package cn.goduck.kl.design.pattern.behavior.mediator;

/**
 * Desc:
 * Author: Kon
 * Date: 2021/11/7 18:03
 */
public class Note {

    private String name;
    private String text;

    public Note() {
        name = "New note";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return getName();
    }

}
