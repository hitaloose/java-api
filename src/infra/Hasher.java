package infra;

import data.contracts.IHasher;

public class Hasher implements IHasher {
    @Override
    public String hash(String value) {
        return value + "-hashed";
    }
}
