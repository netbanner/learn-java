package offer;

public class ReplaceSpace {


    public String replace(String s){
        StringBuffer sb = new StringBuffer();
        for(char c:s.toCharArray()){
            if(String.valueOf(c).equals(" ")){
                sb.append("%20");
            }else{
                sb.append(c);
            }
        }
       return sb.toString();
    }
}
