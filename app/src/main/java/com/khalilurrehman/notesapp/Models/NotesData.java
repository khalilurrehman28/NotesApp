package com.khalilurrehman.notesapp.Models;

public class NotesData {

    String ID;
    String TITLE;
    String NOTES;
    String DATE;

    public NotesData(String ID, String TITLE, String NOTES, String DATE) {
        this.ID = ID;
        this.TITLE = TITLE;
        this.NOTES = NOTES;
        this.DATE = DATE;
    }

    public String getID() {
        return ID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public String getNOTES() {
        return NOTES;
    }

    public String getDATE() {
        return DATE;
    }
}
