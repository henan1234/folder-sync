package taursus.folderSync.generators;

import java.util.UUID;

public class PublicKeyGenerator implements IKeyGenerator {
    
    protected static class PublicKeyGeneratorLoader {
        private static final PublicKeyGenerator INSTANCE = new PublicKeyGenerator();
    }

    protected PublicKeyGenerator() {
        if (PublicKeyGeneratorLoader.INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }

    public static PublicKeyGenerator getInstance() {
        return PublicKeyGeneratorLoader.INSTANCE;
    }
    
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
