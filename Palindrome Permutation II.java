
/*
    Problem StateMent
Given a string s, return all the palindromic permutations (without duplicates) of it. 
Return an empty list if no palindromic permutation could be form.

For example:
Given s = "aabb", return ["abba", "baab"].
Given s = "abc", return [].

*/
public class Solution {
    public List<String> generatePalindromes(String s) {
        List<String> palindromesList = new ArrayList<String>();
        int []dict = new int[256];
        boolean isValid = canBePalindrome(s,dict);
        int len = s.length();
        if(!isValid)
        {
            return palindromesList;
        }
        Character oddCharacter = null;
        if(len % 2 == 1)
        {
            for(int i = 0; i < 256; i++)
            {
                if(dict[i]%2 == 1)
                {
                    oddCharacter = (char)i;
                    break;
                }
            }
        }
        char []halfOfPalindrome = getHalfOfPalindrome(dict,len);
        dfsGetPermutations(palindromesList,oddCharacter,0,halfOfPalindrome);
        return palindromesList;
    }
    //do dfs for permutation
    private void dfsGetPermutations(List<String> palindromesList, Character oddCharacter,
    int index, char[] halfOfPalindrome)
    {
        if(index == halfOfPalindrome.length)
        {
            palindromesList.add(combineTwoPart(halfOfPalindrome,oddCharacter));
            return;
        }
        HashSet<Character> visited = new HashSet<Character>(); 
        for(int i = index; i < halfOfPalindrome.length; i++)
        {
            if(visited.add(halfOfPalindrome[i]))
            {
                swap(halfOfPalindrome,index,i);
                dfsGetPermutations(palindromesList,oddCharacter,index + 1, halfOfPalindrome);
                swap(halfOfPalindrome,index,i);
            }
        }
    }
    //combine the two part of palindromes, abc + cba 
    private String combineTwoPart(char []halfOfPalindrome, Character oddCharacter)
    {
        String firstHalf = new String(halfOfPalindrome);
        StringBuilder secondHalfStrBuilder = new StringBuilder(firstHalf);
        String secondHalf = secondHalfStrBuilder.reverse().toString();
        StringBuilder palindrome = new StringBuilder();
        palindrome.append(firstHalf);
        if(oddCharacter != null)
        {
            palindrome.append(oddCharacter);
        }
        palindrome.append(secondHalf);
        return palindrome.toString();
    }
    private void swap(char []arr, int i, int j)
    {
        char c = arr[i];
        arr[i] = arr[j];
        arr[j] = c;
    }
    //get Half Palindrome.
    private char[] getHalfOfPalindrome(int []dict,int len)
    {
        char []halfOfPalindrome = new char[len/2];
        int index = 0;
        for(int i = 0; i < 256; i++)
        {
            for(int j = 0; j < dict[i]/2;j++)
            {
                halfOfPalindrome[index++] = (char)i;
            }
        }
        return halfOfPalindrome;
    }
    private boolean canBePalindrome(String s,int []dict)
    {
        int len = s.length();
        int count = 0;
        for(int i = 0; i < len; i++)
        {
            dict[s.charAt(i)]++;
        }
        for(int i = 0; i < 256; i++)
        {
            if(dict[i]%2 == 1)
            {
                count++;
            }
        }
        //any palindrome must have at most 
        //one odd number of the same character
        return count <= 1;
    }
}