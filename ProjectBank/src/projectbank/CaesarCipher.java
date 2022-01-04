package projectbank;

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int theKey;
    
    public CaesarCipher(int key) {
        theKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        shiftedAlphabet = alphabet.substring(key) +
                            alphabet.substring(0,key);
        alphabet = alphabet + alphabet.toLowerCase();
        shiftedAlphabet = shiftedAlphabet + shiftedAlphabet.toLowerCase();
    }
    
    private char transformLetter(char c, String from, String to) {
        int idx = from.indexOf(c);
        if (idx != -1) {
            return to.charAt(idx);
        }
        return c;
    }
    
    public char encryptLetter(char c) {
        return transformLetter(c, alphabet, shiftedAlphabet);
    }
    
    public char decryptLetter(char c) {
        return transformLetter(c, shiftedAlphabet, alphabet);
    }
    
    private String transform(String input, String from, String to){
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            c = transformLetter(c, from, to);
            sb.setCharAt(i, c);
        }
        return sb.toString();
    }
    
    public String encrypt(String input) {
        return transform(input, alphabet, shiftedAlphabet).toLowerCase();
    }
    
    public String decrypt(String input) {
        return transform(input, shiftedAlphabet, alphabet).toLowerCase();
    }
    
    public String toString() {
        return "" + theKey;
    }
    
    public static void main(String[] args)
    {
    	CaesarCipher cc = new CaesarCipher(11);
    	String result = cc.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?");
    	result = cc.decrypt("vl2x5rdbbd");
    	System.out.println(result);
    }
    
}
