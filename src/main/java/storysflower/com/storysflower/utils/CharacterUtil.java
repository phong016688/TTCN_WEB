package storysflower.com.storysflower.utils;


import storysflower.com.storysflower.defines.CharacterDefine;

public class CharacterUtil {
    public static boolean isValidCharacterSpecial(String str) {
        for (String item : CharacterDefine.CHARS) {
            if (str.contains(item)) {
                return true;
            }
        }
        return false;
    }

    public static String show() {
        String str = "";
        for (int i = 0; i < CharacterDefine.CHARS.length; i++) {
            str = (i != (CharacterDefine.CHARS.length - 1)) ? str.concat(CharacterDefine.CHARS[i]).concat("-") : str.concat(CharacterDefine.CHARS[i]);
        }
        return str;
    }

}
