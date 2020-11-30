package com.example.springbootapi.service;

import com.example.springbootapi.dto.RESULT;

import java.util.List;



/**
 * Pollen result creator class.
 *
 * @author Naoto Wada
 */
public class PollenResultCreator {

    /**
     * <p>
     * Create pollen result word from input boolean list.
     *
     * @param results Match list
     * @return
     *         <ol>
     *         <li>All match</li>
     *         <ul>
     *         <li>Very strong feel pollen</li>
     *         </ul>
     *         <li>At least one match</li>
     *         <ul>
     *         <li>Slightly feel pollen</li>
     *         </ul>
     *         <li>None match</li>
     *         <ul>
     *         <li>Very comfortable cause None feel pollen</li>
     *         </ul>
     *         </ol>
     */
    public static String create(List<Boolean> results) {
        int count = (int) results.stream().filter(s -> s.booleanValue()).count();

        if (count == 0) {

            return RESULT.LOW.getDispWord();
        } else if (count == 3) {

            return RESULT.HIGH.getDispWord();
        } else {

            return RESULT.MID.getDispWord();
        }
    }

}