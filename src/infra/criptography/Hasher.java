package infra.criptography;

import data.contracts.criptography.IHasher;

public class Hasher implements IHasher {
    @Override
    public String hash(String value) {
        return value + "-hashed";
    }
}
