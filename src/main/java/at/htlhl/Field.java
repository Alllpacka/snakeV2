package at.htlhl;

public enum Field {
    HEAD, BODY, APPLE, STONE, EMPTY;


    /**
     * @param field
     * translates Field enum into printable characters
     * @return char
     * */
    public char translate(Field field) {
        switch (field) {
            case EMPTY -> {
                return ' ';
            }
            case HEAD -> {
                return '#';
            }
            case BODY -> {
                return '*';
            }
            case APPLE -> {
                return '$';
            }
            case STONE -> {
                return 'X';
            }
        }
        return ' ';
    }
}