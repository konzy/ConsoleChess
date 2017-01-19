package Console;

import Chess.Tuple;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The InputHandler class handles all code associated with validating and processing user input
 */

public class InputHandler {
    //contains code for what a validMove should be
    private final static Pattern validMove = Pattern.compile("([a-hA-H][1-8])([-])([a-hA-H][1-8])", Pattern.CASE_INSENSITIVE);
    private BoardMapper mapper;

    public InputHandler(){
        mapper = new BoardMapper();
    }
    /**
     * parse returns the coordinates (Tuple) of chessboard array using user's input
     *
     * @param val is the input from user
     * @return Tuple coordinates
     */
    private Tuple parse(String val){
        int x = mapper.map(val.charAt(0));
        int y = mapper.map(Integer.parseInt(String.valueOf(val.charAt(1))));

        return new Tuple(x, y);
    }
    /**
     * getFrom returns the first half of the input from the user in modified form
     *
     * @param val is the input from user
     */
    public Tuple getFrom(String val){
        Matcher matcher = validMove.matcher(val);
        matcher.matches();
        String coords = matcher.group(1);

        return parse(coords);
    }
    /**
     * getTo returns the second half of the input from the user in modified form
     *
     * @param val is the input from user
     */
    public Tuple getTo(String val){
        Matcher matcher = validMove.matcher(val);
        matcher.matches();
        String coords =  matcher.group(3);

        return parse(coords);
    }
    /**
     * isValid makes sure input is in valid form (eg: a2-a3)
     *
     * @param val is the input from user
     */
    public boolean isValid(String val){
        Matcher matcher = validMove.matcher(val);

        return matcher.matches();
    }
}
