package org.brightworks.friflow.imm.service;

import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.stereotype.Service;

/**
 * @author kyel
 */
@Service
public class PasswordEncoderImpl{

    private StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();

    public String encode(CharSequence rawPassword) {
        return passwordEncryptor.encryptPassword(rawPassword.toString());
    }

    public boolean matches(CharSequence rawPassword,String encodedPassword) {
        try{
            return passwordEncryptor.checkPassword(rawPassword.toString(), encodedPassword);
        }catch (EncryptionOperationNotPossibleException encryptionOperationNotPossibleException){
            return  false;
        }
    }
}
