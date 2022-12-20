package com.perry.ClassicNotes.Validators;

public class UserEntryValidators {

    public static boolean isValidateEntryTitle(String title) {
        return title.length() <= 150;
    }

    public static boolean isValidateEntryBody(String body) {
        return body.length() <= 2000;
    }

}
