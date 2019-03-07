package com.lemzki.tools.interests.translations;

import java.util.List;

public interface PhraseService {

    List<Phrase> getAllWords();

    Phrase saveWord(Phrase phrase);

    Phrase findWord(long id);

    void deleteWord(long id);

    void updateWord(long id, Phrase phrase);

    //seeds db for from resources
    List seedDatabase(String filename);

}
