package Project;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//Please know this code is copied and needs a lot of editting
//This is not a working code with our own
//A lot will be changed and removed if unnecessary
//This is just a reference right now and nothing more

public class ScrabbleAI {
    // main method

    String hand;

    public static void main(String[] args) {

        new ScrabbleGUI();

        Scanner input = new Scanner(System.in);

        // creates a dictionary from txt file to be accessed
        Set<String> dictionary = createDictionary();

        // initialize the board with 2D Tile array
        Tile[][] board = new Tile[15][15];
        startEmptyBoard(board);

        int button = 0;
        while (button != 5) {

            System.out.println("Enter 1 if this is the first turn\n" +
                    "Enter 2 if this is any other turn\n" +
                    "Enter 3 to add a word to the board\n" +
                    "Enter 4 to print the board to the screen\n" +
                    "Enter 5 to end the game\n");

            try {
                button = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Input Mismatch Error! Try again. ");
                input.next();
            }

            if (button == 1) {
                Scanner input1 = new Scanner(System.in);
                System.out.println("Enter your hand: ");
                String hand = input1.nextLine();
                Set<String> noDups1 = findChoices(hand);
                Iterator<String> iterator1 = noDups1.iterator();
                Set<String> combos = new HashSet<String>();
                while (iterator1.hasNext()) {
                    recursiveMethod(iterator1.next(), "", combos);
                }
                System.out.println("Here are all the possible combinations: \n\n" + combos);
                Set<String> words = new HashSet<String>();
                Iterator<String> iterator2 = combos.iterator();
                while (iterator2.hasNext()) {
                    String possibleWord = iterator2.next();
                    if (dictionary.contains(possibleWord))
                        words.add(possibleWord);
                }
                System.out.println("\n\nThe valid words of your hand are: " + words);
                System.out.println("\n\n" + playFirst(words, board));
                button = 0;
            }

            else if (button == 2) {
                Scanner input2 = new Scanner(System.in);
                System.out.println("Enter your hand: ");
                String hand = input2.nextLine();
                Set<String> noDups1 = findChoices(hand);
                Iterator<String> iterator1 = noDups1.iterator();
                Set<String> combos = new HashSet<String>();
                while (iterator1.hasNext()) {
                    recursiveMethod(iterator1.next(), "", combos);
                }
                System.out.println("Here are all the possible combinations: \n\n");
                Set<String> handPerms = new HashSet<String>();
                recursiveMethod(hand, "", handPerms);
                System.out.println("\n\n" + play(hand, handPerms, combos, board, dictionary));
                button = 0;
            }

            else if (button == 3) {
                Scanner input3 = new Scanner(System.in);
                System.out.println("Which word will you be adding to the board?: ");
                String word = input3.nextLine();
                try {
                    System.out.println("At which i index will this word be played?: ");
                    int i = input3.nextInt();
                    System.out.println("At which j index will this word be played?: ");
                    int j = input3.nextInt();
                    Scanner input4 = new Scanner(System.in);
                    System.out.println(
                            "What will be the orientation of this word? (Enter 'H' for horizontal or 'V' for vertical): ");
                    String orientation = input4.nextLine();
                    if (orientation.equals("H"))
                        addHorizontalWordToBoard(board, word, i, j);

                    else if (orientation.equals("V"))
                        addVerticalWordToBoard(board, word, i, j);
                    else
                        System.out.println("\nOrientation not recognized. Try again\n");
                } catch (InputMismatchException e) {
                    System.out.println("Input Mismatch Error! Try again.\n");
                }
                button = 0;
            } else if (button == 4) {
                printBoard(board);
                button = 0;
            } else if (button == 5) {
                button = 5;
            } else {
                System.out.println("\nYou must enter an integer button in the range of 1 through 5!\n");
                button = 0;
            }
        }
        System.out.println("Game Over.");
    }

    // this method will be called if this program is used on the first play of
    // scrabble
    public static String playFirst(Set<String> noDups3, Tile[][] board) {

        // allows to go through permutation set one-by-one
        Iterator<String> iterator3 = noDups3.iterator();
        // if there is a next permutation in the hash set
        List<StoredPlay> plays = new ArrayList<StoredPlay>();
        while (iterator3.hasNext()) {
            String str = iterator3.next();
            int length = str.length();
            Tile[] wordArray = new Tile[length];
            for (int i = 1; i < 8; i++) {
                Boolean test = false;
                int column = 0;
                for (int j = 0; j < length; j++) {
                    if (j == 0) {
                        column = i + j;
                    }

                    // put those tiles into a word array
                    // wordArray[j] = board[7][i+j]; //original line written by Adam

                    // create a copy of the tiles on the board for the wordArray
                    // so we can change the letters w/o changing the game board
                    Tile copy = duplicateTile(board[7][i + j]);
                    wordArray[j] = copy;

                    // check to see if midde tile is contained
                    if (wordArray[j].getDoubleWordScore())
                        test = true;

                    wordArray[j].setLetter(str.charAt(j));
                    // add tiles to a string possibleWord

                }

                if (test) {
                    int score = wordScore(wordArray);
                    StoredPlay play = new StoredPlay(str, score, 7, column, 'H');
                    plays.add(play);
                }
            }
        }
        if (plays.size() == 0) {
            return "No plays found. Do you have only constonants? Also, be sure everything is capitalized.";
        }
        StoredPlay best = plays.get(0);
        for (int n = 0; n < plays.size() - 1; n++) {
            if (plays.get(n + 1).getScore() > best.getScore())
                best = plays.get(n + 1);
        }
        return "The best word to play is: " + best.getWord() + " at index [7]["
                + Integer.toString(best.getjIndex()) + "] with orientation H for "
                + Integer.toString(best.getScore()) + " points\n";
    }

    public static String play(String hand, Set<String> handPerms, Set<String> permutations, Tile[][] board,
            Set<String> dictionary) {
        // stores horizontal consecutive characters with their starting and ending
        // indices
        System.out.println("\nHorizontals: ");
        List<Records> recordsH = findAlphaOmegaH(board);

        // stores vertical consecutive characters with their starting and ending indices
        System.out.println("\nVerticals: ");
        List<Records> recordsV = findAlphaOmegaV(board);

        List<StoredPlay> totalPlays = new ArrayList<StoredPlay>();

        Set<StoredPlay> playsH = getPlaysH(recordsH, recordsV, board, dictionary, permutations);

        Set<StoredPlay> playsV = getPlaysV(recordsH, recordsV, board, dictionary, permutations);

        Iterator<StoredPlay> playsHIterator = playsH.iterator();
        while (playsHIterator.hasNext()) {
            totalPlays.add(playsHIterator.next());
        }

        Iterator<StoredPlay> playsVIterator = playsV.iterator();
        while (playsVIterator.hasNext()) {
            totalPlays.add(playsVIterator.next());
        }

        if (totalPlays.size() == 0) {
            return "No plays found";
        }
        StoredPlay best = totalPlays.get(0);
        for (int n = 0; n < totalPlays.size() - 1; n++) {
            if (totalPlays.get(n + 1).getScore() > best.getScore())
                best = totalPlays.get(n + 1);
        }
        return "The best word to play is: " + best.getWord() + " at index ["
                + Integer.toString(best.getiIndex()) + "]["
                + Integer.toString(best.getjIndex()) + "] with orientation "
                + Character.toString(best.getOrientation()) + " for "
                + Integer.toString(best.getScore()) + " points\n";

    }

    public static Set<StoredPlay> getPlaysH(List<Records> recordsH, List<Records> recordsV, Tile[][] board,
            Set<String> dictionary, Set<String> permutations) {
        System.out.println("getting H plays...");

        Set<StoredPlay> playsH = new HashSet<StoredPlay>();

        // for all horizontal records on the board
        for (int n = 0; n < recordsH.size(); n++) {

            Set<BeforeAndAfterWord> beforeAndAfterWordsH = new HashSet<BeforeAndAfterWord>();

            Iterator<String> permIterator = permutations.iterator();
            String record = recordsH.get(n).getRecord();
            while (permIterator.hasNext()) {
                String perm = permIterator.next();
                for (int k = 0; k <= perm.length(); k++) {
                    String prefix = perm.substring(0, k);
                    String suffix = perm.substring(k, perm.length());
                    BeforeAndAfterWord beforeAndAfterWord = new BeforeAndAfterWord(prefix + record + suffix, prefix,
                            suffix);
                    beforeAndAfterWordsH.add(beforeAndAfterWord);
                }
            }

            int iIndex1 = recordsH.get(n).getiIndex1();
            int jIndex1 = recordsH.get(n).getjIndex1();
            int iIndex2 = recordsH.get(n).getiIndex2();
            int jIndex2 = recordsH.get(n).getjIndex2();

            Set<StoredPlay> beforeAndAfterPlaysH = getBeforeAndAfterPlaysH(beforeAndAfterWordsH, iIndex1, jIndex1,
                    iIndex2, jIndex2, record, recordsH, recordsV, board, dictionary, permutations);
            Iterator<StoredPlay> beforeAndAfterPlaysHIterator = beforeAndAfterPlaysH.iterator();
            while (beforeAndAfterPlaysHIterator.hasNext()) {
                playsH.add(beforeAndAfterPlaysHIterator.next());
            }

        }
        return playsH;
    }

    public static Set<StoredPlay> getPlaysV(List<Records> recordsH, List<Records> recordsV, Tile[][] board,
            Set<String> dictionary, Set<String> permutations) {

        System.out.println("getting V plays...");

        Set<StoredPlay> playsV = new HashSet<StoredPlay>();

        // for all horizontal records on the board
        for (int n = 0; n < recordsV.size(); n++) {

            Set<BeforeAndAfterWord> beforeAndAfterWordsV = new HashSet<BeforeAndAfterWord>();

            Iterator<String> permIterator = permutations.iterator();
            String record = recordsV.get(n).getRecord();
            while (permIterator.hasNext()) {
                String perm = permIterator.next();
                for (int k = 0; k <= perm.length(); k++) {
                    String prefix = perm.substring(0, k);
                    String suffix = perm.substring(k, perm.length());
                    BeforeAndAfterWord beforeAndAfterWord = new BeforeAndAfterWord(prefix + record + suffix, prefix,
                            suffix);
                    beforeAndAfterWordsV.add(beforeAndAfterWord);
                }

            }

            int iIndex1 = recordsV.get(n).getiIndex1();
            int jIndex1 = recordsV.get(n).getjIndex1();
            int iIndex2 = recordsV.get(n).getiIndex2();
            int jIndex2 = recordsV.get(n).getjIndex2();

            Set<StoredPlay> beforeAndAfterPlaysV = getBeforeAndAfterPlaysV(beforeAndAfterWordsV, iIndex1, jIndex1,
                    iIndex2, jIndex2, record, recordsH, recordsV, board, dictionary, permutations);
            Iterator<StoredPlay> beforeAndAfterPlaysVIterator = beforeAndAfterPlaysV.iterator();
            while (beforeAndAfterPlaysVIterator.hasNext()) {
                playsV.add(beforeAndAfterPlaysVIterator.next());
            }
        }
        return playsV;
    }

    public static Set<StoredPlay> getBeforeAndAfterPlaysH(Set<BeforeAndAfterWord> beforeAndAfterWordsH, int iIndex1,
            int jIndex1, int iIndex2, int jIndex2, String record, List<Records> recordsH, List<Records> recordsV,
            Tile[][] board, Set<String> dictionary, Set<String> permutations) {

        Set<StoredPlay> beforeAndAfterPlaysH = new HashSet<StoredPlay>();
        Iterator<BeforeAndAfterWord> beforeAndAfterIteratorH = beforeAndAfterWordsH.iterator();

        outerloop: while (beforeAndAfterIteratorH.hasNext()) {
            BeforeAndAfterWord info = beforeAndAfterIteratorH.next();
            String prefix = info.getPrefix();
            String suffix = info.getSuffix();

            int size1 = prefix.length();
            int counter1 = 1;
            String newPrefixH = "";

            while (size1 != 0 && jIndex1 - counter1 >= 0) {
                char letter = board[iIndex1][jIndex1 - counter1].getLetter();
                if (letter == 0) {
                    newPrefixH = Character.toString(prefix.charAt(size1 - 1)) + newPrefixH;
                    size1--;
                } else if (letter != 0) {
                    newPrefixH = Character.toString(letter) + newPrefixH;
                }
                counter1++;
            }

            int size2 = 0;
            int counter2 = 1;
            String newSuffixH = "";
            while (size2 != suffix.length() && jIndex2 + counter2 <= 14) {
                char letter = board[iIndex1][jIndex2 + counter2].getLetter();
                if (letter == 0) {
                    newSuffixH = newSuffixH + Character.toString(suffix.charAt(size2));
                    size2++;
                } else if (letter != 0) {
                    newSuffixH = newSuffixH + Character.toString(letter);
                }
                counter2++;
            }

            int alphaJ = jIndex1 - newPrefixH.length();
            Boolean test1 = true;
            // while index to left is in bounds and index to left is non-empty
            while (alphaJ - 1 >= 0 && test1) {
                char letter = board[iIndex1][alphaJ - 1].getLetter();
                if (letter != 0) {
                    newPrefixH = Character.toString(letter) + newPrefixH;
                    alphaJ = alphaJ - 1;
                } else if (letter == 0) {
                    test1 = false;
                }

            }
            int omegaJ = jIndex2 + newSuffixH.length();
            Boolean test2 = true;
            // while index to the right is in bounds and non empty
            while (omegaJ + 1 <= 14 && test2) {
                char letter = board[iIndex1][omegaJ + 1].getLetter();
                if (letter != 0) {
                    newSuffixH = newSuffixH + Character.toString(letter);
                    omegaJ = omegaJ + 1;
                } else if (letter == 0) {
                    test2 = false;
                }
            }
            String possibleWordH = newPrefixH + record + newSuffixH;

            if (dictionary.contains(possibleWordH)) {
                String wordH = possibleWordH;
                int score = 0;

                Tile[] wordArrayH = new Tile[wordH.length()];
                for (int p = 0; p < wordH.length(); p++) {
                    wordArrayH[p] = new Tile(wordH.charAt(p), board[iIndex1][alphaJ + p].getDoubleLetterScore(),
                            board[iIndex1][alphaJ + p].getTripleLetterScore(),
                            board[iIndex1][alphaJ + p].getDoubleWordScore(),
                            board[iIndex1][alphaJ + p].getTripleWordScore());
                }

                for (int k = 0; k < wordH.length(); k++) {
                    // if row above is in bounds and tile above is filled or if row below is in
                    // bounds and tile below is filled
                    if (((iIndex1 - 1 >= 0) && (board[iIndex1 - 1][k + alphaJ].getLetter() != 0)) ||
                            ((iIndex1 + 1 <= 14) && (board[iIndex1 + 1][k + alphaJ].getLetter() != 0))) {
                        int alphaI = iIndex1;
                        // while tile above is in bounds and not empty
                        while ((alphaI - 1 >= 0) && (board[alphaI - 1][k + alphaJ].getLetter() != 0)) {
                            alphaI = alphaI - 1;
                        }
                        int omegaI = iIndex2;
                        // while tile below is in bounds and not empty
                        while ((omegaI + 1 <= 14) && (board[omegaI + 1][k + alphaJ].getLetter() != 0)) {
                            omegaI = omegaI + 1;
                        }
                        int diffV = omegaI - alphaI;
                        Tile[] wordArrayV = new Tile[diffV + 1];
                        String possibleWordV = "";
                        for (int q = 0; q < diffV + 1; q++) {
                            char letter = board[q + alphaI][k + alphaJ].getLetter();
                            if (letter != 0) {
                                wordArrayV[q] = new Tile(letter, board[q + alphaI][k + alphaJ].getDoubleLetterScore(),
                                        board[q + alphaI][k + alphaJ].getTripleLetterScore(),
                                        board[q + alphaI][k + alphaJ].getDoubleWordScore(),
                                        board[q + alphaI][k + alphaJ].getTripleWordScore());
                                possibleWordV = possibleWordV + Character.toString(letter);
                            } else {
                                wordArrayV[q] = new Tile(wordH.charAt(k),
                                        board[q + alphaI][k + alphaJ].getDoubleLetterScore(),
                                        board[q + alphaI][k + alphaJ].getTripleLetterScore(),
                                        board[q + alphaI][k + alphaJ].getDoubleWordScore(),
                                        board[q + alphaI][k + alphaJ].getTripleWordScore());
                                possibleWordV = possibleWordV + wordH.charAt(k);
                            }

                        }
                        Records possibleRecordV = new Records(possibleWordV, alphaI, k + alphaJ, omegaI, k + alphaJ);

                        Boolean testRecord = false;
                        for (int q = 0; q < recordsV.size(); q++) {
                            if (possibleRecordV.getRecord().equals(recordsV.get(q).getRecord()) &&
                                    possibleRecordV.getiIndex1() == (recordsV.get(q).getiIndex1()) &&
                                    possibleRecordV.getjIndex1() == (recordsV.get(q).getjIndex1()) &&
                                    possibleRecordV.getiIndex2() == (recordsV.get(q).getiIndex2()) &&
                                    possibleRecordV.getjIndex2() == (recordsV.get(q).getjIndex2())) {
                                testRecord = true;
                            }
                        }

                        if ((dictionary.contains(possibleWordV)) && (testRecord == false)) {
                            score = score + wordScore(wordArrayV);
                        } else if (!dictionary.contains(possibleWordV)) {
                            continue outerloop;
                        }
                    }
                }
                score = score + wordScore(wordArrayH);
                StoredPlay play = new StoredPlay(wordH, score, iIndex1, alphaJ, 'H');
                beforeAndAfterPlaysH.add(play);
            } else
                continue outerloop;
        }
        return beforeAndAfterPlaysH;
    }

    public static Set<StoredPlay> getBeforeAndAfterPlaysV(Set<BeforeAndAfterWord> beforeAndAfterWordsV, int iIndex1,
            int jIndex1, int iIndex2, int jIndex2, String record, List<Records> recordsH, List<Records> recordsV,
            Tile[][] board, Set<String> dictionary, Set<String> permutations) {

        Set<StoredPlay> beforeAndAfterPlaysV = new HashSet<StoredPlay>();
        Iterator<BeforeAndAfterWord> beforeAndAfterIteratorV = beforeAndAfterWordsV.iterator();

        outerloop: while (beforeAndAfterIteratorV.hasNext()) {
            BeforeAndAfterWord info = beforeAndAfterIteratorV.next();
            String prefix = info.getPrefix();
            String suffix = info.getSuffix();

            int size1 = prefix.length();
            int counter1 = 1;
            String newPrefixV = "";

            while (size1 != 0 && iIndex1 - counter1 >= 0) {
                char letter = board[iIndex1 - counter1][jIndex1].getLetter();
                if (letter == 0) {
                    newPrefixV = Character.toString(prefix.charAt(size1 - 1)) + newPrefixV;
                    size1--;
                } else if (letter != 0) {
                    newPrefixV = Character.toString(letter) + newPrefixV;
                }
                counter1++;
            }

            int size2 = 0;
            int counter2 = 1;
            String newSuffixV = "";
            while (size2 != suffix.length() && iIndex2 + counter2 <= 14) {
                char letter = board[iIndex2 + counter2][jIndex1].getLetter();
                if (letter == 0) {
                    newSuffixV = newSuffixV + Character.toString(suffix.charAt(size2));
                    size2++;
                } else if (letter != 0) {
                    newSuffixV = newSuffixV + Character.toString(letter);
                }
                counter2++;
            }

            int alphaI = iIndex1 - newPrefixV.length();
            Boolean test1 = true;

            while (alphaI - 1 >= 0 && test1) {
                char letter = board[alphaI - 1][jIndex1].getLetter();
                if (letter != 0) {
                    newPrefixV = Character.toString(letter) + newPrefixV;
                    alphaI = alphaI - 1;
                } else if (letter == 0) {
                    test1 = false;
                }
            }

            int omegaI = iIndex2 + newSuffixV.length();
            Boolean test2 = true;
            // while index to the right is in bounds and non empty
            while (omegaI + 1 <= 14 && test2) {
                char letter = board[omegaI + 1][jIndex1].getLetter();
                if (letter != 0) {
                    newSuffixV = newSuffixV + Character.toString(letter);
                    omegaI = omegaI + 1;
                } else if (letter == 0) {
                    test2 = false;
                }
            }

            String possibleWordV = newPrefixV + record + newSuffixV;

            if (dictionary.contains(possibleWordV)) {
                String wordV = possibleWordV;
                int score = 0;

                Tile[] wordArrayV = new Tile[wordV.length()];
                for (int p = 0; p < wordV.length(); p++) {
                    wordArrayV[p] = new Tile(wordV.charAt(p), board[alphaI + p][jIndex1].getDoubleLetterScore(),
                            board[alphaI + p][jIndex1].getTripleLetterScore(),
                            board[alphaI + p][jIndex1].getDoubleWordScore(),
                            board[alphaI + p][jIndex1].getTripleWordScore());
                }

                for (int k = 0; k < wordV.length(); k++) {
                    // if column to the left is in bounds and tile to the left is filled or if
                    // column to the right is in bounds and tile to the right is filled
                    if (((jIndex1 - 1 >= 0) && (board[k + alphaI][jIndex1 - 1].getLetter() != 0)) ||
                            ((jIndex1 + 1 <= 14) && (board[k + alphaI][jIndex1 + 1].getLetter() != 0))) {
                        int alphaJ = jIndex1;
                        // while tile to the left is in bounds and not empty
                        while ((alphaJ - 1 >= 0) && (board[k + alphaI][alphaJ - 1].getLetter() != 0)) {
                            alphaJ = alphaJ - 1;
                        }
                        int omegaJ = jIndex2;
                        // while tile below is in bounds and not empty
                        while ((omegaJ + 1 <= 14) && (board[k + alphaI][omegaJ + 1].getLetter() != 0)) {
                            omegaJ = omegaJ + 1;
                        }
                        int diffH = omegaJ - alphaJ;
                        Tile[] wordArrayH = new Tile[diffH + 1];
                        String possibleWordH = "";
                        for (int q = 0; q < diffH + 1; q++) {
                            char letter = board[k + alphaI][q + alphaJ].getLetter();
                            if (letter != 0) {
                                wordArrayH[q] = new Tile(letter, board[k + alphaI][q + alphaJ].getDoubleLetterScore(),
                                        board[k + alphaI][q + alphaJ].getTripleLetterScore(),
                                        board[k + alphaI][q + alphaJ].getDoubleWordScore(),
                                        board[k + alphaI][q + alphaJ].getTripleWordScore());
                                possibleWordH = possibleWordH + Character.toString(letter);
                            } else {
                                wordArrayH[q] = new Tile(wordV.charAt(k),
                                        board[k + alphaI][q + alphaJ].getDoubleLetterScore(),
                                        board[k + alphaI][q + alphaJ].getTripleLetterScore(),
                                        board[k + alphaI][q + alphaJ].getDoubleWordScore(),
                                        board[k + alphaI][q + alphaJ].getTripleWordScore());
                                possibleWordH = possibleWordH + wordV.charAt(k);
                            }

                        }
                        Records possibleRecordH = new Records(possibleWordH, k + alphaI, alphaJ, k + alphaI, omegaJ);

                        Boolean testRecord = false;
                        for (int q = 0; q < recordsH.size(); q++) {
                            if (possibleRecordH.getRecord().equals(recordsH.get(q).getRecord()) &&
                                    possibleRecordH.getiIndex1() == (recordsH.get(q).getiIndex1()) &&
                                    possibleRecordH.getjIndex1() == (recordsH.get(q).getjIndex1()) &&
                                    possibleRecordH.getiIndex2() == (recordsH.get(q).getiIndex2()) &&
                                    possibleRecordH.getjIndex2() == (recordsH.get(q).getjIndex2())) {
                                testRecord = true;
                            }
                        }

                        if ((dictionary.contains(possibleWordH)) && (testRecord == false)) {
                            score = score + wordScore(wordArrayH);
                        } else if (!dictionary.contains(possibleWordH)) {
                            continue outerloop;
                        }
                    }
                }
                score = score + wordScore(wordArrayV);
                StoredPlay play = new StoredPlay(wordV, score, alphaI, jIndex1, 'V');
                beforeAndAfterPlaysV.add(play);
            } else
                continue outerloop;
        }
        return beforeAndAfterPlaysV;
    }

    public static List<Records> findAlphaOmegaH(Tile[][] board) {
        List<Records> records = new ArrayList<Records>();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (board[i][j].getLetter() != 0) {
                    String word = "";
                    int iIndex1 = i;
                    int jIndex1 = j;

                    // if next index is in bounds
                    outerloop: while ((board[i][j].getLetter() != 0)) {
                        word = word + Character.toString(board[i][j].getLetter());
                        j++;
                        if (j > 14) {
                            break outerloop;
                        }
                    }

                    int iIndex2 = i;
                    int jIndex2 = j - 1;
                    Records record = new Records(word, iIndex1, jIndex1, iIndex2, jIndex2);
                    records.add(record);
                }
            }
        }
        for (int i = 0; i < records.size(); i++) {
            System.out.println(records.get(i).getRecord() + " [" + records.get(i).getiIndex1() + "]["
                    + records.get(i).getjIndex1() + "] ["
                    + records.get(i).getiIndex2() + "][" + records.get(i).getjIndex2() + "]");
        }
        return records;
    }

    public static List<Records> findAlphaOmegaV(Tile[][] board) {
        List<Records> records = new ArrayList<Records>();
        for (int j = 0; j < 15; j++) {
            for (int i = 0; i < 15; i++) {
                if (board[i][j].getLetter() != 0) {
                    String word = "";
                    int iIndex1 = i;
                    int jIndex1 = j;

                    // if next index is in bounds
                    outerloop: while ((board[i][j].getLetter() != 0)) {
                        word = word + Character.toString(board[i][j].getLetter());
                        i++;
                        if (i > 14) {
                            break outerloop;
                        }
                    }

                    int iIndex2 = i - 1;
                    int jIndex2 = j;
                    Records record = new Records(word, iIndex1, jIndex1, iIndex2, jIndex2);
                    records.add(record);
                }
            }
        }
        for (int n = 0; n < records.size(); n++) {
            System.out.println(records.get(n).getRecord() + " [" + records.get(n).getiIndex1() + "]["
                    + records.get(n).getjIndex1() + "] ["
                    + records.get(n).getiIndex2() + "][" + records.get(n).getjIndex2() + "]");
        }
        return records;
    }

    public static Set<String> findChoices(String hand) {
        Set<String> choices = new HashSet<String>();
        // possCombs is the amount of nCr choices of a string
        int possCombs = (int) Math.pow(2, hand.length());
        for (int i = 1; i < possCombs; i++) {
            String answer = "";
            String binaryRep = Integer.toBinaryString(i);
            while (binaryRep.length() < hand.length())
                binaryRep = "0" + binaryRep;
            for (int j = binaryRep.length() - 1; j >= 0; j--) {
                // if a specific index equals 1
                if (binaryRep.charAt(j) == '1') {
                    // we set a character equal to the corresponding character at the same index in
                    // the user input str
                    char ch = hand.charAt(j);
                    // we then convert that character to a string
                    String cheese = String.valueOf(ch);
                    // we then attach the new string to the original answer
                    // For example, if we enter "abcd", on the first iteration, it goes from ""->"d"
                    answer = answer + cheese;
                }
            }
            // put answer in to the hash set. Hash set removes duplicates. If we enter"aabb"
            // there would be duplicates
            choices.add(answer);
        }
        return choices;
    }

    // this method uses recursion to find the permutations and store them into
    // noDups2
    public static void recursiveMethod(String str, String answer, Set<String> perms) {
        // condition 1
        if (str.length() == 0) {
            // base case puts answer into noDups2
            perms.add(answer);
            return;
        }
        // else condition 2
        for (int i = 0; i < str.length(); i++) {

            // ith character of str
            char ch = str.charAt(i);

            // Rest of the string after excluding the ith character
            String rest = str.substring(0, i) + str.substring(i + 1);

            // Recurvise case
            recursiveMethod(rest, answer + ch, perms);
        }
    }

    public static Tile duplicateTile(Tile original) {
        Tile duplicate = new Tile(); // create duplicate tile

        duplicate.setLetter(original.getLetter()); // copy letter

        // copy word/letter bonuses
        duplicate.setDoubleLetterScore(original.getDoubleLetterScore());
        duplicate.setTripleLetterScore(original.getTripleLetterScore());
        duplicate.setDoubleWordScore(original.getDoubleWordScore());
        duplicate.setTripleWordScore(original.getTripleWordScore());

        return duplicate;
    }

    public static void addHorizontalWordToBoard(Tile[][] board, String word, int i, int j) {
        int n = 0;
        while (n < word.length()) {
            char letter = word.charAt(n);
            board[i][j + n] = new Tile(letter, 1, 1, false, false);
            n++;
        }
    }

    public static void addVerticalWordToBoard(Tile[][] board, String word, int i, int j) {
        int n = 0;
        while (n < word.length()) {
            char letter = word.charAt(n);
            board[i + n][j] = new Tile(letter, 1, 1, false, false);
            n++;
        }
    }

    public static int wordScore(Tile[] wordArray) {
        int score = 0;
        for (int n = 0; n < wordArray.length; n++) {
            score = score + charValue(wordArray[n].getLetter()) * wordArray[n].getDoubleLetterScore()
                    * wordArray[n].getTripleLetterScore();
        }
        for (int k = 0; k < wordArray.length; k++) {
            if (wordArray[k].getDoubleWordScore() == true)
                score = score * 2;
            else if (wordArray[k].getTripleWordScore() == true)
                score = score * 3;
        }
        return score;
    }

    public static Set<String> createDictionary() {
        Set<String> dictionary = new HashSet<String>();
        try {
            // creates an input object to read the txt file
            BufferedReader in = new BufferedReader(new FileReader("words.txt"));
            // stores the first word of the dictionary into a string
            String str = in.readLine();
            // as long as the string is not null
            while (str != null) {
                dictionary.add(str);
                str = in.readLine();
            }
            // close input
            in.close();
            // catch and potential errors such as file not found
        } catch (IOException e) {
            System.out.println("an error occurred");
        }
        return dictionary;
    }

    // print board into console
    public static void printBoard(Tile[][] board) {
        System.out.println("  00 01 02 03 04 05 06 07 08 09 10 11 12 13 14"); // number columns
        for (int i = 0; i < board.length; i++) {
            System.out.print((i < 10 ? "0" : "") + i); // number rows
            for (int j = 0; j < board[i].length; j++) {
                char letter = board[i][j].getLetter();
                // if letter isn't set, then just print a blank space, otherwise print letter to
                // row
                System.out.print(" " + (letter != '0' ? letter : " ") + " ");
                // boolean isATile = board[i][j] instanceof Tile; //DEBUG - used to ensure tile
                // objects are at board[i][j]
                // System.out.print(isATile);

            }
            System.out.println(); // new line for next row
        }
    }

    // This method will take in a character and return its associated scrabble score
    public static int charValue(char ch) {
        int value = 0;
        if (ch == 'A')
            value = 1;
        else if (ch == 'B')
            value = 3;
        else if (ch == 'C')
            value = 3;
        else if (ch == 'D')
            value = 2;
        else if (ch == 'E')
            value = 1;
        else if (ch == 'F')
            value = 4;
        else if (ch == 'G')
            value = 2;
        else if (ch == 'H')
            value = 4;
        else if (ch == 'I')
            value = 1;
        else if (ch == 'J')
            value = 8;
        else if (ch == 'K')
            value = 5;
        else if (ch == 'L')
            value = 1;
        else if (ch == 'M')
            value = 3;
        else if (ch == 'N')
            value = 1;
        else if (ch == 'O')
            value = 1;
        else if (ch == 'P')
            value = 3;
        else if (ch == 'Q')
            value = 10;
        else if (ch == 'R')
            value = 1;
        else if (ch == 'S')
            value = 1;
        else if (ch == 'T')
            value = 1;
        else if (ch == 'U')
            value = 1;
        else if (ch == 'V')
            value = 4;
        else if (ch == 'W')
            value = 4;
        else if (ch == 'X')
            value = 8;
        else if (ch == 'Y')
            value = 4;
        else if (ch == 'Z')
            value = 10;
        else if (ch == '*')
            value = 0;
        return value;
    }

    // fill board with empty tile objects
    public static void startEmptyBoard(Tile[][] board) {

        for (int i = 0; i < board.length; i++) {
            // System.out.print(i + " - "); //DEBUG
            for (int j = 0; j < board[i].length; j++) {
                Tile emptyTile = new Tile();
                board[i][j] = emptyTile;
                // System.out.print(j + " "); //DEBUG
            }
            // System.out.println(); //DEBUG
        }

        board[0][0].setTripleWordScore(true);
        board[0][7].setTripleWordScore(true);
        board[0][14].setTripleWordScore(true);
        board[7][0].setTripleWordScore(true);
        board[7][14].setTripleWordScore(true);
        board[14][0].setTripleWordScore(true);
        board[14][7].setTripleWordScore(true);
        board[14][14].setTripleWordScore(true);

        // set DoubleWord Tiles
        board[1][1].setDoubleWordScore(true);
        board[1][13].setDoubleWordScore(true);
        board[2][2].setDoubleWordScore(true);
        board[2][12].setDoubleWordScore(true);
        board[3][3].setDoubleWordScore(true);
        board[3][11].setDoubleWordScore(true);
        board[4][4].setDoubleWordScore(true);
        board[4][10].setDoubleWordScore(true);
        board[7][7].setDoubleWordScore(true);
        board[10][4].setDoubleWordScore(true);
        board[10][10].setDoubleWordScore(true);
        board[11][3].setDoubleWordScore(true);
        board[11][11].setDoubleWordScore(true);
        board[12][2].setDoubleWordScore(true);
        board[12][12].setDoubleWordScore(true);
        board[13][1].setDoubleWordScore(true);
        board[13][13].setDoubleWordScore(true);

        // set TripleLetter Tiles
        board[1][5].setTripleLetterScore(3);
        board[1][9].setTripleLetterScore(3);
        board[5][1].setTripleLetterScore(3);
        board[5][5].setTripleLetterScore(3);
        board[5][9].setTripleLetterScore(3);
        board[5][13].setTripleLetterScore(3);
        board[9][1].setTripleLetterScore(3);
        board[9][5].setTripleLetterScore(3);
        board[9][9].setTripleLetterScore(3);
        board[9][13].setTripleLetterScore(3);
        board[13][5].setTripleLetterScore(3);
        board[13][9].setTripleLetterScore(3);

        // set DoubleLetter Tiles
        board[0][3].setDoubleLetterScore(2);
        board[0][11].setDoubleLetterScore(2);
        board[2][6].setDoubleLetterScore(2);
        board[2][8].setDoubleLetterScore(2);
        board[3][0].setDoubleLetterScore(2);
        board[3][7].setDoubleLetterScore(2);
        board[3][14].setDoubleLetterScore(2);
        board[6][2].setDoubleLetterScore(2);
        board[6][6].setDoubleLetterScore(2);
        board[6][8].setDoubleLetterScore(2);
        board[6][12].setDoubleLetterScore(2);
        board[7][3].setDoubleLetterScore(2);
        board[7][11].setDoubleLetterScore(2);
        board[8][2].setDoubleLetterScore(2);
        board[8][6].setDoubleLetterScore(2);
        board[8][8].setDoubleLetterScore(2);
        board[8][12].setDoubleLetterScore(2);
        board[11][0].setDoubleLetterScore(2);
        board[11][7].setDoubleLetterScore(2);
        board[11][14].setDoubleLetterScore(2);
        board[12][6].setDoubleLetterScore(2);
        board[12][8].setDoubleLetterScore(2);
        board[14][3].setDoubleLetterScore(2);
        board[14][11].setDoubleLetterScore(2);
    }
}