package fr.tpillon.sampleactivities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.tpillon.sampleactivities.Models.Exceptions.EmptyTextException;
import fr.tpillon.sampleactivities.Logic.Services.TextService;


public class TextServiceTest {
    private TextService textService;

    @Before
    public void init(){
        textService = new TextService();
    }

    @Test
    public void TextService_replaceSpace() throws EmptyTextException{
        String result = textService.formatString("text with spaces");
        Assert.assertEquals("TextWithSpaces", result);
    }

    @Test
    public void TextService_KeepUpper() throws EmptyTextException{
        String result = textService.formatString("TextWithUpper");
        Assert.assertEquals("TextWithUpper", result);
    }

    @Test
    public void TextService_removeSpacesAtExtremities() throws EmptyTextException{
        String result = textService.formatString("  TextWithSpaceAtExtremities   ");
        Assert.assertEquals("TextWithSpaceAtExtremities", result);
    }

    @Test
    public void TextService_exceptionOnEMptyText() {
        Assert.assertThrows(EmptyTextException.class,()-> textService.formatString("  "));
    }
}