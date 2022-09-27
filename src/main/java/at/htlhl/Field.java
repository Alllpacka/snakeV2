package at.htlhl;

public enum Field {
    HEAD, BODY, APPLE, EMPTY;

    /**
     *
     * Translates the field
     * @param field
     * @return
     */
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
        }
        return ' ';
    }
}