public enum Letters {
    
    A('A', 1, 9),
    B('B', 1, 2),
    C('C', 1, 2),
    D('D',1,4);
    private final char letter;
    private final int score;
    private final int count;

    Letters(final char letter, final int score, final int count){
        this.letter = letter; 
        this.score = score;
        this.count = count;
    }
}
