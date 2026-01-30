package org.example.render;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RenderHangman {
    private final List<String> hangman = new ArrayList<>(Arrays.asList(
            """
                    _____
                    |    
                    |    
                    |   
                    |   
                    |_____    
                           
                   """,
            """
                               _____
                               |    |
                               |    
                               |   
                               |   
                               |_____

                               """,
            """
                               _____
                               |    |
                               |    0
                               |   
                               |   
                               |_____

                               """,
            """
                               _____
                               |    |
                               |    0
                               |   /
                               |   
                               |_____

                               """,
            """
                    _____
                    |    |
                    |    0
                    |   /|
                    |   
                    |_____

                    """, """
                    _____
                    |    |
                    |    0
                    |   /|\\
                    |   
                    |_____

                    ""","""
                    _____
                    |    |
                    |    0
                    |   /|\\
                    |   /
                    |_____

                    ""","""
                    _____
                    |    |
                    |    0
                    |   /|\\
                    |   / \\
                    |_____



                    """


    ));

    public List<String> getHangman() {
        return hangman;
    }
}
