import com.alibaba.fastjson.JSON;

/**
 * 字符串相乘
 */
public class StrMultiply {

    public static void main(String[] args) {
        System.out.println(multiply("99","22"));
    }

    public static String multiply(String num1, String num2) {
        if(num1 == null || num1.length()==0 || num2==null||num2.length()==0){
            return "";
        }
        if(num1.equals("0") || num2.equals("0")){
            return "0";
        }

        int inx1 = num1.length()-1;
        int inx2 = num2.length()-1;

        int[] muls = new int[num1.length()+num2.length()];
        for(int i=inx1;i>=0;i--){
            for(int j=inx2;j>=0;j--){
                int mul = (num1.charAt(i)-'0') * (num2.charAt(j)-'0');
                mul = mul + muls[i+j+1];
                muls[i+j+1] = mul%10;
                muls[i+j] += mul/10;
                System.out.println(JSON.toJSONString(muls));
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < muls.length; i++) {
            result.append(muls[i]);
        }
        return result.toString();
    }

}
