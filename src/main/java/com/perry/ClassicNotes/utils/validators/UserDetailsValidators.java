package com.perry.ClassicNotes.utils.validators;

public class UserDetailsValidators {
   public static boolean isValidPassword(String password) {
       return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
   }

   public static boolean isValidPhoneNumber(String phoneNumber) {
       return phoneNumber.matches("^(\\\\+\\\\d{1,3}( )?)?((\\\\(\\\\d{1,3}\\\\))|\\\\d{1,3})[- .]?\\\\d{3,4}[- .]?\\\\d{4}$");
   }

   public static boolean isValidEmailAddress(String email) {
       return email.matches("(.+)@(\\S+)");
   }

}
