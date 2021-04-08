package com.dedalus.textanalyzer;

import com.dedalus.textanalyzer.model.Text;
import com.dedalus.textanalyzer.service.TextAnalyzerService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;

@RunWith(MockitoJUnitRunner.class)
public class TextAnalyzerServiceTest {

    @InjectMocks
    private TextAnalyzerService textAnalyzerRefactored;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void analyze_returnCorrectOccurrencesOfVowels() {
        Text text = new Text();
        text.setType("vowels");
        text.setText("Hello Dedalus!");
        HashMap<Character, Integer> report = new HashMap<>();
        report = textAnalyzerRefactored.analyze(text);
        Assertions.assertEquals(5, report.size());
        Assertions.assertEquals(1, report.get('A'));
        Assertions.assertEquals(2, report.get('E'));
        Assertions.assertEquals(0, report.get('I'));
    }

    @Test
    public void analyze_returnCorrectOccurrencesOfConsonants() {
        Text text = new Text();
        text.setType("consonants");
        text.setText("Hello Dedalus!");
        HashMap<Character, Integer> report = new HashMap<>();
        report = textAnalyzerRefactored.analyze(text);
        Assertions.assertEquals(4, report.size());
        Assertions.assertEquals(1, report.get('H'));
        Assertions.assertEquals(3, report.get('L'));
        Assertions.assertEquals(2, report.get('D'));
        Assertions.assertEquals(1, report.get('S'));
    }

    @Test
    public void analyze_withEmptyText_throwsNullPointerException() {
        Text text = new Text();
        text.setType("consonants");
        text.setText("");
        HashMap<Character, Integer> report = new HashMap<>();
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("No text to analyze!");
        report = textAnalyzerRefactored.analyze(text);
        Assertions.assertEquals(0, report.size());
    }

}
