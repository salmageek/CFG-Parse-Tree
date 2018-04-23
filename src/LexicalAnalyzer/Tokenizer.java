package LexicalAnalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.*;

public class Tokenizer {

    public static void runTokenizer() throws FileNotFoundException {

        String target = new Scanner(new File("/Users/Eslam/Desktop/CFG-Parse-Tree/src/input.txt")).useDelimiter("\\Z").next();

        Scanner s = new Scanner(new File("/Users/Eslam/Desktop/CFG-Parse-Tree/src/LexicalAnalyzer/RE.txt"));

        ArrayList<Match> matches = new ArrayList<>();
        ArrayList<Match> allMatches = new ArrayList<>();

        while (s.hasNext()) {

            String token = s.next();
            String RE = s.next();

            Pattern pattern = Pattern.compile(RE);
            Matcher matcher = pattern.matcher(target);

            while (matcher.find()) {

                Match obj = new Match(token, matcher.group(), matcher.start(), matcher.end(), matcher.end() - matcher.start());
                matches.add(obj);

            }
        }



        matches.sort((Match o1, Match o2)-> (o2.range - o1.range));

        Match obj = new Match(matches.get(0).token, matches.get(0).value, matches.get(0).startPosition, matches.get(0).endPosition);
        allMatches.add(obj);
        matches.remove(0);

        while (!matches.isEmpty()) {

            Boolean found = false;

            for (int i = 0; i < allMatches.size() && !matches.isEmpty(); i++) {

                if ((matches.get(0).startPosition >= allMatches.get(i).startPosition)
                        && (matches.get(0).endPosition <= allMatches.get(i).endPosition)) {
                    found = true;
                    matches.remove(0);
                }
            }

            if (found) {
                continue;
            }
            if (matches.isEmpty()) {
                break;
            }

            Match obj1 = new Match(matches.get(0).token, matches.get(0).value, matches.get(0).startPosition, matches.get(0).endPosition);
            allMatches.add(obj1);
            matches.remove(0);

        }

        allMatches.sort((Match o1, Match o2) -> o1.startPosition - o2.startPosition);
        PrintWriter writer = new PrintWriter("/Users/Eslam/Desktop/CFG-Parse-Tree/src/output.txt");

        for (int i = 0; i < allMatches.size(); i++) {

            String temp = "";
            for (int j = 0; j < allMatches.get(i).value.length(); j++) {
                temp += " ";
            }

            StringBuilder buf = new StringBuilder(target);
            buf.replace(allMatches.get(i).startPosition, allMatches.get(i).endPosition, temp);
            target = buf.toString();

            if (allMatches.get(i).token.equals("EOL")) {
                allMatches.get(i).value = "\\n";
            }
            writer.println(""+allMatches.get(i).token+" : " + allMatches.get(i).value);
//            System.out.println("" + allMatches.get(i).token + " : " + allMatches.get(i).value);
        }
        writer.println("EOF "+": "+"eof");
        writer.close();

        String temp = target;
        if (temp.trim().length() != 0) {

            temp = temp.trim();
            String[] temps = temp.split("\\s+");

            for (String temp1 : temps) {
                int index = target.indexOf(temp1);
                System.out.println("ERROR: " + temp1 + " This token did not match any RE @ index " + index);
            }

        }

    }

    public static void main(String[] args) throws IOException {
        runTokenizer();
    }
}
