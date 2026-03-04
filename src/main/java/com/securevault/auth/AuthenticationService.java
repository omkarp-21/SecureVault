package com.securevault.auth;

import com.securevault.user.UserManager;
import com.securevault.utils.exceptions.InvalidUsernameException;
import com.securevault.utils.exceptions.UserAlreadyExistsException;
import com.securevault.utils.exceptions.WeakPasswordException;

public class AuthenticationService
{
    PasswordHasher passwordHasher;
    UserManager userManager;

    public void register(String username, String password)
    {
        String regexPattern = "^[a-zA-Z0-9]{3,20}$";
        if (!username.matches(regexPattern)) throw new InvalidUsernameException();
        if (userManager.userExists(username)) throw new UserAlreadyExistsException();
        if (!checkPasswordStrength(password)) throw new WeakPasswordException();

        byte[] userSalt = passwordHasher.generateSalt();
        String userPasswordHashed = passwordHasher.hashPassword(password, userSalt);
    }

    boolean checkPasswordStrength(String password)
    {
        int passwordLength = password.length();
        boolean upper = false, lower = false, digits = false, special = false;
        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) upper = true;
            else if (Character.isLowerCase(ch)) lower = true;
            else if (Character.isDigit(ch)) digits = true;
            else special = true;
        }
        if (upper && lower && digits && special
                && (passwordLength >= 8))
        {
            return true; // Strong
        }
        else return (lower || upper || special)
                && (passwordLength >= 6);   // Moderate -> True | Weak -> False
    }
}
