# WordLadder
This short program takes two words of equal length and attempts to find a path between the two words by only changing one letter at a time. Each word in the ladder must be a real word, which is verified in the words.txt file. All words in ladder are printed, if no path is found an error message is printed. This program makes use of recursion and file I/O.
## Example Outputs: <br />
 -findWord("cop", "cat"); --> cop, cap, cat <br />
 -findWord("cope", "cat"); --> Please enter words of the same length <br />
 -findWord("show", "pets"); --> show, chow, chaw, chas, No word ladder.
