package com.dedalus.textanalyzer.service;

import com.dedalus.textanalyzer.model.Text;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Service for calculating how many times letter in given sentence appears.
 * It gives numbers either for vowels or for consonants based on program input.
 * Refactored Text Analyzer java class (https://gist.github.com/hobo1848/ba72fa5bd3715a7b56447b7f960c7d9f)
 */
@Service
public class TextAnalyzerService {

    public HashMap<Character, Integer> analyze(Text text) {

        HashMap<Character, Integer> report = new HashMap<>();
        String type = text.getType().toLowerCase();
        String input = text.getText().toUpperCase();
        HashMap<Character, Integer> vowels = new HashMap<>();
        vowels.put('A', 0);
        vowels.put('E', 0);
        vowels.put('I', 0);
        vowels.put('O', 0);
        vowels.put('U', 0);

        if (input != null && !input.trim().isEmpty()) {
            if (type.equals("vowels") ) {
                return count_vowels(vowels, input);
            } else if (type.equals("consonants")) {
                return count_consonants(vowels, input);
            }
        } else {
            throw new NullPointerException("No text to analyze!");
        }
        return report;
    }

    private HashMap<Character, Integer> count_vowels(HashMap<Character, Integer> vowels, String inputText) {
        System.out.println("Counting Vowels");
        for (int i = 0; i < inputText.length(); i++) {
            if (vowels.containsKey(inputText.charAt(i))) {
                int count = vowels.get(inputText.charAt(i));
                vowels.put(inputText.charAt(i), ++count);
            }
        }
        return vowels;
    }

    private HashMap<Character, Integer> count_consonants(HashMap<Character, Integer> vowels, String inputText) {
        System.out.println("Counting Consonants");
        HashMap<Character, Integer> consonants = new HashMap<>();
        for (int i = 0; i < inputText.length(); i++) {
            if (!vowels.containsKey(inputText.charAt(i)) && Character.isLetter(inputText.charAt(i))) {
                if (consonants.containsKey(inputText.charAt(i))) {
                    int count = consonants.get(inputText.charAt(i));
                    consonants.put(inputText.charAt(i), ++count);
                } else {
                    consonants.put(inputText.charAt(i),1);
                }
            }
        }
        return consonants;
    }
}
