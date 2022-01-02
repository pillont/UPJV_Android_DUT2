package fr.tpillon.sampleactivities.Logic.Services;

import fr.tpillon.sampleactivities.Models.Exceptions.EmptyTextException;

public class TextService {

    public String formatString(String text) throws EmptyTextException {
        String trimText =text.trim();
        if("".equals(trimText) ){
            throw new EmptyTextException();
        }

        StringBuilder builder = buildPascalCaseSentence(trimText);
        return builder.toString();
    }

    private StringBuilder buildPascalCaseSentence(String trimText) {
        StringBuilder builder = new StringBuilder();

        String[] splitBySpaces = trimText.split("\\s+");
        for(String word: splitBySpaces) {
            addWordAsPascalCase(word, builder);
        }

        return  builder;
    }

    private void addWordAsPascalCase(String str, StringBuilder builder) {
        char firstChar = str.charAt(0);
        char firstAsUpper = Character.toUpperCase(firstChar);
        builder.append(firstAsUpper);

        String end = str.substring(1);
        builder.append(end);
    }
}
