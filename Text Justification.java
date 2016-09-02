/*Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. 
Pad extra spaces ' ' when necessary so that each line has exactly L characters.
Extra spaces between words should be distributed as evenly as possible. 
If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.
*/

public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
          List<String> res = new ArrayList<String>();
          int prevStart = 0;
          int start = 0;
          int len = words.length;
          int curlen = 0;
          int curNumberOfWords = 0;
       
          while(start < len)
          {   
              if(curNumberOfWords == 0)
              {
                  prevStart = start;
              }
              int space =(curNumberOfWords == 0)? 0 : 1;
              if(curlen + words[start].length() + space > maxWidth)
              {
                  curNumberOfWords = 0;
                  curlen = 0;
                  res.add(lineOfWords(words,prevStart,start - 1,maxWidth));
                  continue;
              }
              else if (curlen + words[start].length() + space == maxWidth)
              {
                  curNumberOfWords = 0;
                  curlen = 0;
                  res.add(lineOfWords(words,prevStart,start,maxWidth));
                  start++;
                  continue;
              }
              else
              {
                  curNumberOfWords++;
                  curlen += words[start].length() + space;
                  start++;
              }
              
          }
          if(curNumberOfWords != 0)
          {
              res.add(lineOfWords(words,prevStart, words.length - 1,maxWidth));
          }
          
          String str = res.get(res.size() - 1);
          res.remove(res.size() - 1);
          res.add(lastLine(str,maxWidth));
          return res;
    }
    public String lastLine(String last,int maxWidth)
    {
        last = last.replaceAll("\\s+"," ");
        for(int i = last.length(); i < maxWidth;i++)
        {
            last = last + " ";
        }
        return last;
    }
    public String lineOfWords(String[] words,int start, int end,int maxWidth)
    {
        StringBuilder str = new StringBuilder();
        int numOfWords = end - start  + 1;
        int curWordLen = 0;
        for(int i = start; i <= end;i++)
        {
            curWordLen += words[i].length();
        }
        int diff = maxWidth - curWordLen;
        if(numOfWords == 1)
        {
            StringBuilder oneWordsInLine = new StringBuilder();
            oneWordsInLine.append(words[end]);
            oneWordsInLine.append(generateSpace(maxWidth - words[end].length()));
            return oneWordsInLine.toString();
        }
        
        if(diff%(numOfWords - 1) == 0)
        {
            String temp = generateSpace(diff/(numOfWords - 1));
            for(int i = start; i <= end; i++)
            {
                if(i == end)
                {
                    str.append(words[i]);      
                }
                else
                {
                    str.append(words[i]).append(temp);
                }
            }
            return str.toString();
        }
        else
        {
           String leftSpaces = generateSpace(diff/(numOfWords - 1) + 1);
           String rightSpaces = generateSpace(diff/(numOfWords - 1));
           int numOfLeftSpaces = diff % (numOfWords - 1);
           for(int i = start; i <= end; i++)
            {
                if(i == end)
                {
                    str.append(words[i]);      
                }
                else
                {
                    if(numOfLeftSpaces > 0)
                    {
                        str.append(words[i]).append(leftSpaces);
                        numOfLeftSpaces--;
                    }
                    else
                    {
                         str.append(words[i]).append(rightSpaces);
                    }
                }
            }
            return str.toString();
        }
        
    }
    public String generateSpace(int num)
    {
        StringBuilder spaces = new StringBuilder();
        for(int i = 0; i < num; i++)
        {
            spaces.append(" ");
        }
        return spaces.toString();
    }
}