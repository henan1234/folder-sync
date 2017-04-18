package taursus.folderSync.generators;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class PrivateKeyGenerator implements IKeyGenerator {
    
    protected static class PublicKeyGeneratorLoader {
        private static final PrivateKeyGenerator INSTANCE = new PrivateKeyGenerator();
    }

    protected PrivateKeyGenerator() {
        if (PublicKeyGeneratorLoader.INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }

    public static PrivateKeyGenerator getInstance() {
        return PublicKeyGeneratorLoader.INSTANCE;
    }
    
    public String generate() {
        BigInteger randomInt = new BigInteger(130, new SecureRandom());
        MessageDigest md = null;
        
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
        
        return (new HexBinaryAdapter()).marshal(md.digest(randomInt.toByteArray()));
    }
}
