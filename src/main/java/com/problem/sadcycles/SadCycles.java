package com.problem.sadcycles;

import java.io.IOException;
import java.util.Properties;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SadCycles extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        if (req.getParameter("n") != null && req.getParameter("b") != null){
            if (!req.getParameter("n").equals("") && !req.getParameter("b").equals("")){
                long n = Long.parseLong(req.getParameter("n"));
                long b = Long.parseLong(req.getParameter("b"));
                String json = toJSON(getNumPattern(n,b));
                resp.getWriter().println(json);
            }
            else {
                resp.getWriter().println("");
            }
        }
        else {
            resp.getWriter().println("");
        }
    }

    /**
     * I do not want to import a whole libary just to convert something to json
     * @param  pattern the digits that make up the pattern
     * @return         the json string representation of the arraylist
     */
    private static String toJSON(ArrayList<Long> pattern){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < pattern.size(); i++){
            sb.append(pattern.get(i));
            if (i < pattern.size()-1){
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * The main function that receives n and b
     * @param  n the number to split
     * @param  b the power to raise
     * @return   the ArrayList pattern
     */
    private static ArrayList<Long> getNumPattern(long n, long b){
        /* Keep finding the sums until we reach an already recorded number */
        ArrayList<Long> recordedNums = new ArrayList<>();
        long nextNum = getSumOfDigits( getDigits(n), b );
        while (!recordedNums.contains(nextNum)){
            recordedNums.add(nextNum);
            nextNum = getSumOfDigits( getDigits(nextNum), b );
        }

        int patternStart = recordedNums.indexOf(nextNum);
        ArrayList<Long> pattern = new ArrayList<>();

        /* Get the subarray */
        for (int i = patternStart; i < recordedNums.size(); i++){
            pattern.add(recordedNums.get(i));
        }

        return pattern;
    }

    /**
     * Get the digits of an long
     * @param  the number
     * @return ArrayList of integers (in reverse order)
     */
    private static ArrayList<Long> getDigits(long num){
        ArrayList<Long> digits = new ArrayList<>();
        while (num > 0){
            digits.add(num % 10);
            num /= 10;
        }
        return digits;
    }

    /**
     * [getSumOfDigits description]
     * @param  List of digits, power to raise each digit
     * @return Sum of the digits raised to power of b
     */
    private static long getSumOfDigits(ArrayList<Long> digits, long b){
        long sum = 0;
        for (long digit : digits){
            sum += Math.pow(digit, b);
        }
        return sum;
    }
}