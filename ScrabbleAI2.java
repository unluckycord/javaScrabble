
//a code I found that might help with score or word finding from
//https://github.com/TomBrennan91/ScrabbleBot/commit/538251560e9fd01212816306cbadfc2148d105e3
//Below line 340 is a bonus checker code, but it uses images instead of text

public class ScrabbleAI2 {

    /*
     * 
     * import java.util.ArrayList;
     * public class AI implements Constants {
     * 
     * Player bot;
     * int maxScore ;
     * ArrayList<Tile> bestWord;
     * Anchor currentAnchor;
     * public AI(Player bot) {
     * this.bot = bot;
     * }
     * 
     * boolean makeFirstMove(){
     * 
     * bestWord = new ArrayList<Tile>();
     * bestWord = new ArrayList<>();
     * getStartingWord( bot.letterRack.tiles , bestWord, "", 0);
     * 
     * if (maxScore == 0){
     * System.err.println("ai couldnt move with starting tiles \n" +
     * bot.letterRack.toString() );
     * bot.swapTiles();
     * 
     * return false;
     * }
     * 
     * Move move = new Move(bestWord , 7 , 7 - (bestWord.size() / 2) , true ,
     * maxScore , bot);
     * move.execute(Board.getInstance().tileArr);
     * 
     * bot.letterRack.refill();
     * return true;
     * 
     * }
     * 
     * boolean makeSubsequentMove(){
     * maxScore = 0;
     * bestWord = new ArrayList<Tile>();
     * for (Anchor anchor : findAnchors()){
     * ArrayList<Tile> inputTiles = new ArrayList<Tile>(bot.letterRack.tiles);
     * inputTiles.add(anchor.anchorTile);
     * findHighestScoringWord(inputTiles, new ArrayList<Tile>(), "", 0, anchor);
     * }
     * if (bestWord == null || bestWord.size() == 0){
     * bot.swapTiles();
     * return false;
     * } else {
     * 
     * int startCol;
     * int startRow;
     * if (currentAnchor.across){
     * startCol = currentAnchor.col - getAnchorPosition(currentAnchor, bestWord);
     * startRow = currentAnchor.row;
     * } else {
     * startCol = currentAnchor.col;
     * startRow = currentAnchor.row - getAnchorPosition(currentAnchor, bestWord);
     * }
     * 
     * Move move = new Move(bestWord , startRow , startCol ,currentAnchor.across,
     * maxScore , bot);
     * move.execute(Board.getInstance().tileArr);
     * //System.out.println(move.toString());
     * 
     * Board.getInstance().repaint();
     * }
     * return true;
     * }
     * 
     * private int getAnchorPosition(Anchor anchor, ArrayList<Tile> word){
     * //ToDo: allow for case of multiple anchor positions
     * for (int c = 0 ; c < word.size() ; c++){
     * if (word.get(c).letter == anchor.anchorTile.letter){
     * return c;
     * }
     * }
     * return -1000;
     * }
     * 
     * private boolean fitsOnBoard(Anchor anchor, ArrayList<Tile> word){
     * //check if word would cause spilling off the edge of the board
     * int anchorPos = getAnchorPosition(anchor, word);
     * int prefixLength = anchorPos;
     * int posfixLength = word.size() - anchorPos - 1 ;
     * 
     * if (anchor.prefixCap >= prefixLength && anchor.postfixCap >= posfixLength){
     * return true;
     * } else {
     * return false;
     * }
     * }
     * 
     * private void findHighestScoringWord(ArrayList<Tile> inputTiles,
     * ArrayList<Tile> tilesToBeUsed, String currentWord, int score, Anchor anchor){
     * for (int tileNo = 0 ; tileNo < inputTiles.size() ; tileNo++){
     * Tile curTile = inputTiles.get(tileNo);
     * if (curTile == null) break;
     * if (isValidPrefix(currentWord + curTile.letter) ){
     * ArrayList<Tile> remainingTiles = new ArrayList<Tile>( inputTiles);
     * ArrayList<Tile> tilesInWord = new ArrayList<Tile>(tilesToBeUsed);
     * remainingTiles.remove(tileNo);
     * tilesInWord.add(curTile);
     * findHighestScoringWord(remainingTiles, tilesInWord ,currentWord +
     * curTile.letter, score + curTile.points , anchor);
     * 
     * if (currentWord.length() >= 7){
     * score += 50;
     * }
     * 
     * 
     * 
     * //need to check if anchor is in the word before we propose it as an answer
     * if (tilesToBeUsed.contains(anchor.anchorTile) ||
     * curTile.equals(anchor.anchorTile)){
     * if (isValidWord(currentWord + curTile.letter)){
     * if (fitsOnBoard(anchor, tilesInWord)){
     * if (maxScore < score + curTile.points){
     * maxScore = score + curTile.points;
     * bestWord = tilesInWord;
     * currentAnchor = anchor;
     * }
     * }
     * }
     * }
     * }
     * }
     * }
     * 
     * boolean isValidPrefix(String prefix){
     * if (Scrabble.hardMode.isSelected()){
     * if (Dictionary.bigTrie.searchPrefix(prefix)){
     * return true;
     * } else {
     * return false;
     * }
     * } else {
     * if (Dictionary.smallTrie.searchPrefix(prefix)){
     * return true;
     * } else {
     * return false;
     * }
     * }
     * }
     * 
     * boolean isValidWord(String word){
     * if (Scrabble.hardMode.isSelected()){
     * if (Dictionary.bigTrie.searchWord(word)){
     * return true;
     * } else {
     * return false;
     * }
     * } else {
     * if (Dictionary.smallTrie.searchWord(word)){
     * return true;
     * } else {
     * return false;
     * }
     * }
     * }
     * 
     * void getStartingWord(ArrayList<Tile> inputTiles, ArrayList<Tile>
     * tilesToBeUsed, String currentWord, int score){
     * for (int tileNo = 0 ; tileNo < inputTiles.size() ; tileNo++){
     * Tile curTile = inputTiles.get(tileNo);
     * 
     * if (isValidPrefix(currentWord + curTile.letter)){
     * 
     * ArrayList<Tile> remainingTiles = new ArrayList<Tile>( inputTiles);
     * ArrayList<Tile> tilesInWord = new ArrayList<Tile>(tilesToBeUsed);
     * remainingTiles.remove(tileNo);
     * tilesInWord.add(curTile);
     * getStartingWord(remainingTiles, tilesInWord ,currentWord + curTile.letter,
     * score + curTile.points );
     * 
     * if (currentWord.length() >= 6){
     * score += 50;
     * }
     * 
     * if (isValidWord(currentWord + curTile.letter)){
     * if (maxScore < score + curTile.points){
     * maxScore = score + curTile.points;
     * bestWord = tilesInWord;
     * }
     * }
     * }
     * }
     * }
     * 
     * ArrayList<Anchor> findAnchors(){
     * ArrayList<Anchor> anchors = new ArrayList<Anchor>();
     * Tile[][] tileArr = Board.getInstance().tileArr;
     * for (int row = 0 ; row < tileArr.length ; row ++){
     * for (int col = 0 ; col < tileArr[0].length ; col ++){
     * if (tileArr[row][col].letter != ' '){
     * int startCol = col;
     * int endCol = col;
     * 
     * //check how far left the word can go without collisions
     * if (col > 0 && tileArr[row][col - 1].letter < 53){
     * while (startCol > 0){
     * if (row != BOARD_DIMENSIONS - 1 && tileArr[row + 1][startCol - 1].letter != '
     * '){
     * break;
     * }
     * if ( row != 0 && tileArr[row - 1][startCol - 1].letter != ' '){
     * break;
     * }
     * if (startCol == 1){
     * startCol--;
     * break;
     * }
     * if (tileArr[row ][startCol -2].letter != ' '){
     * break;
     * }
     * startCol--;
     * }
     * }
     * 
     * //check how far right the word can go without collisions
     * if (col < BOARD_DIMENSIONS - 1 && tileArr[row][col + 1].letter == ' '){
     * while (endCol < BOARD_DIMENSIONS -1 ){
     * 
     * if (row != BOARD_DIMENSIONS - 1 && tileArr[row + 1][endCol + 1].letter != '
     * '){
     * break;
     * }
     * if ( row != 0 && tileArr[row - 1][endCol + 1].letter != ' '){
     * break;
     * }
     * if (endCol == BOARD_DIMENSIONS - 2){
     * endCol++;
     * break;
     * }
     * 
     * if (tileArr[row ][endCol + 2].letter != ' '){
     * break;
     * }
     * 
     * endCol++;
     * }
     * }
     * 
     * //add the horizontal anchors
     * if (col - startCol > 0 && endCol - col > 0){ // words that can go left or
     * right
     * anchors.add(new Anchor(row, col, tileArr[row][col], col - startCol, endCol -
     * col , true));
     * } else {
     * //if only one then we need to do additional checks
     * if (col - startCol > 0){
     * if (col < BOARD_DIMENSIONS - 1 && tileArr[row][col + 1].letter == ' '){ //
     * words that can only go left
     * anchors.add(new Anchor(row, col, tileArr[row][col], col - startCol, endCol -
     * col , true));
     * }
     * }
     * if (endCol - col > 0){
     * if (col > 0 && tileArr[row][col - 1 ].letter == ' '){ // words that can only
     * go right
     * anchors.add(new Anchor(row, col, tileArr[row][col], col - startCol, endCol -
     * col , true));
     * }
     * }
     * }
     * 
     * //check not at edges - have to re-think algorithm for edges.
     * int startRow = row;
     * int endRow = row;
     * 
     * //check how high the word can go without collisions
     * if (row > 0 && tileArr[row - 1][col].letter == ' '){
     * while (startRow > 0){
     * if (col < BOARD_DIMENSIONS - 1 && tileArr[startRow - 1][col + 1].letter != '
     * '){
     * break;
     * }
     * if (col > 0 && tileArr[startRow - 1][col - 1].letter != ' '){
     * break;
     * }
     * if (startRow == 1){
     * startRow--;
     * break;
     * }
     * if (tileArr[startRow - 2][col ].letter != ' '){
     * break;
     * }
     * startRow--;
     * }
     * 
     * }
     * 
     * //check how low the word can go without collisions
     * if (row < BOARD_DIMENSIONS - 1 && tileArr[row + 1][col].letter == ' '){
     * while (endRow < BOARD_DIMENSIONS -1){
     * if (col < BOARD_DIMENSIONS - 1 && tileArr[endRow + 1][col + 1].letter != '
     * '){
     * break;
     * }
     * if (col > 0 && tileArr[endRow + 1][col - 1].letter != ' '){
     * break;
     * }
     * if (endRow == BOARD_DIMENSIONS - 2){
     * endRow++;
     * break;
     * }
     * if(tileArr[endRow + 2][col].letter != ' '){
     * break;
     * }
     * 
     * endRow++;
     * }
     * }
     * 
     * if (row - startRow > 0 && endRow - row > 0){
     * anchors.add(new Anchor(row, col, tileArr[row][col], row - startRow, endRow -
     * row, false));
     * } else{//if only one then we need to do additional checks
     * if (row - startRow > 0){ //words that can only go up
     * if (row < BOARD_DIMENSIONS-1 && tileArr[row+1][col].letter == ' '){
     * anchors.add(new Anchor(row, col, tileArr[row][col], row - startRow, endRow -
     * row, false));
     * }
     * }
     * if (endRow - row > 0){ //words that can only go down
     * if (row > 0 && tileArr[row-1][col].letter == ' '){
     * anchors.add(new Anchor(row, col, tileArr[row][col], row - startRow, endRow -
     * row, false));
     * }
     * }
     * }
     * 
     * 
     * }
     * }
     * }
     * return anchors;
     * }
     * 
     * }
     * }
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * import java.awt.Image;
     * 
     * import javax.swing.ImageIcon;
     * 
     * public class BonusChecker implements Constants{
     * 
     * 
     * public static int[][] Arr;
     * private static Image doubleLetter;
     * private static Image doubleWord;
     * private static Image tripleLetter;
     * private static Image tripleWord;
     * 
     * private static final BonusChecker instance;
     * 
     * static{ instance = new BonusChecker();}
     * 
     * public static BonusChecker getInstance(){
     * return instance;
     * }
     * public static Image getImage(int bonus){
     * ImageIcon picture = new ImageIcon("images/bonus/" + bonus +".jpg");
     * java.awt.Image TileImage = picture.getImage();
     * java.awt.Image scaledImage = TileImage.getScaledInstance(50, 50,
     * java.awt.Image.SCALE_SMOOTH);
     * return scaledImage;
     * }
     * 
     * public static Image findImage(int row, int col){
     * int bonus = check(row, col);
     * 
     * switch (bonus) {
     * case 1: return doubleLetter;
     * case 2: return tripleLetter;
     * case 3: return doubleWord;
     * case 4: return tripleWord;
     * default: return null;
     * }
     * }
     * 
     * public static int letterMultiplier(int row, int col){
     * if (check(row, col) == 1) return 2;
     * if (check(row, col) == 2) return 3;
     * return 1;
     * }
     * 
     * public static int wordMultiplier(int row, int col){
     * if (check(row, col) == 3) return 2;
     * if (check(row, col) == 4) return 3;
     * return 1;
     * }
     * 
     * public static void RemovePlayedBonuses(){
     * Tile[][] tileArr = Board.getInstance().tileArr;
     * 
     * 
     * for (int row = 0 ; row < BOARD_DIMENSIONS ; row++){
     * for (int col = 0 ; col < BOARD_DIMENSIONS ; col++){
     * if(tileArr[row][col].letter != ' ') {
     * Arr[row][col] = 0;
     * }
     * }
     * }
     * }
     * 
     * public BonusChecker() {
     * 
     * doubleLetter = getImage(1);
     * tripleLetter = getImage(2);
     * doubleWord = getImage(3);
     * tripleWord = getImage(4);
     * 
     * 
     * Arr = new int[15][15];
     * 
     * for(int i = 1 ; i < BOARD_DIMENSIONS - 1 ; i++){
     * Arr[i][i] = 3;
     * Arr[BOARD_DIMENSIONS - i - 1][i] = 3;
     * }
     * 
     * Arr[6][6] = 1;
     * Arr[6][8] = 1;
     * Arr[8][6] = 1;
     * Arr[8][8] = 1;
     * 
     * Arr[11][7] = 1;
     * Arr[7][11] = 1;
     * Arr[3][7] = 1;
     * Arr[7][3] = 1;
     * 
     * Arr[2][6] = 1;
     * Arr[6][2] = 1;
     * Arr[2][8] = 1;
     * Arr[8][2] = 1;
     * 
     * Arr[12][6] = 1;
     * Arr[6][12] = 1;
     * Arr[12][8] = 1;
     * Arr[8][12] = 1;
     * 
     * Arr[11][0] = 1;
     * Arr[0][11] = 1;
     * Arr[11][14] = 1;
     * Arr[14][11] = 1;
     * 
     * Arr[3][0] = 1;
     * Arr[0][3] = 1;
     * Arr[3][14] = 1;
     * Arr[14][3] = 1;
     * 
     * Arr[5][1] = 2;
     * Arr[1][5] = 2;
     * Arr[5][13] = 2;
     * Arr[13][5] = 2;
     * 
     * Arr[9][1] = 2;
     * Arr[1][9] = 2;
     * Arr[9][13] = 2;
     * Arr[13][9] = 2;
     * 
     * Arr[5][5] = 2;
     * Arr[5][9] = 2;
     * Arr[9][5] = 2;
     * Arr[9][9] = 2;
     * 
     * 
     * 
     * Arr[0][0] = 4;
     * Arr[14][14] = 4;
     * Arr[0][14] = 4;
     * Arr[14][0] = 4;
     * 
     * Arr[7][0] = 4;
     * Arr[14][7] = 4;
     * Arr[7][14] = 4;
     * Arr[0][7] = 4;
     * 
     * }
     * 
     * public static Integer check(int row, int col){
     * return Arr[row][col];
     * }
     * 
     * }
     * 
     * 
     * class Bonus {
     * int row;
     * int col;
     * 
     * public boolean equals(Bonus other) {
     * return (row == other.row && col == other.col);
     * }
     * 
     * public Bonus(int row, int col) {
     * this.row = row;
     * this.col = col;
     * }
     * }
     * 
     * 
     * 
     * 
     * 
     */
}